package apsd.classes.containers.collections.concretecollections.bases;

// import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Abstract chain base implementation on linked-list. */
abstract public class LLChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  public LLChainBase() { }

  protected LLChainBase(long quantity, LLNode<Data> head, LLNode<Data> tail) {

    this.size.Assign(quantity);
    headref.Set(head);
    tailref.Set(tail);

  }

  public LLChainBase(TraversableContainer<Data> con) {
    size.Assign(con.Size());
    final Box<Boolean> first = new Box<>(true);
    con.TraverseForward(dat -> {
      LLNode<Data> node = new LLNode<>(dat);
      if (first.Get()) {
        headref.Set(node);
        first.Set(false);
      } else {
        tailref.Get().SetNext(node);
      }
      tailref.Set(node);
      return false;
    });
  }

  /* ************************************************************************ */
  /* Specific member functions from LLChainBase                               */
  /* ************************************************************************ */

  protected abstract LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail);

  protected class ListReFIterator implements MutableForwardIterator<Box<LLNode<Data>>>{ 

    protected Box<LLNode<Data>> ref = headref; 
    
    public ListReFIterator() {}

    public ListReFIterator( ListReFIterator itr ) { ref = itr.ref; }

    @Override
    public boolean IsValid() {

      return ref != null && ref.Get() != null;

    }

    @Override
    public void Reset() {

      ref = headref;

    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {

      if(!IsValid()) throw new IllegalStateException(" Iterator is not valid");
      return ref;

    }

    @Override
    public void SetCurrent(Box<LLNode<Data>> dat) {
      
      if(dat == null) throw new NullPointerException("Dat cannot be null");
      ref.Set(dat.Get());

    }

    @Override
    public void Next(){ 

      if(!IsValid()) throw new IllegalStateException("Iterator terminetade");

      ref = ref.Get().GetNext();

    }

    @Override
    public Box<LLNode<Data>> DataNNext() {

      Box<LLNode<Data>> oldRef = ref;

      Next();

      return oldRef;

    }


  }

  protected MutableForwardIterator<Box<LLNode<Data>>> FRefIterator(){ return new ListReFIterator(); }

  protected class ListRefBIterator implements MutableBackwardIterator<Box<LLNode<Data>>>{ 

    protected Box<LLNode<Data>> ref = tailref; 
    
    public ListRefBIterator() {}

    public ListRefBIterator( ListRefBIterator itr ) { ref = itr.ref; }

    @Override
    public boolean IsValid() {

      return ref != null && ref.Get() != null;

    }

    @Override
    public void Reset() {

      ref = tailref;

    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {

      if(!IsValid()) throw new IllegalStateException(" Iterator is not valid");
      return ref;

    }

    @Override
    public void SetCurrent(Box<LLNode<Data>> dat) {
      
      if(dat == null) throw new NullPointerException("Dat cannot be null");
      ref.Set(dat.Get());

    }

    @Override
    public void Prev(){ 

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");

      MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

      if(ref != headref) {

        while(f_iter.IsValid()){

          if(f_iter.GetCurrent().Get().GetNext().Get() == ref.Get()){
  
            ref = f_iter.GetCurrent();
    
          } else { f_iter.Next(); }
  
        }

      } else { ref = null; }

    }

    @Override
    public Box<LLNode<Data>> DataNPrev() {

      Box<LLNode<Data>> oldRef = ref;

      Prev();

      return oldRef;

    }


  }

  protected MutableBackwardIterator<Box<LLNode<Data>>> BRefIterator(){ return new ListRefBIterator(); }


  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size(){

    return Natural.Of(size);

  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear(){

    headref.Set(null);
    tailref.Set(null);
    size.Assign(0);

  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  @Override
  public boolean Remove(Data dat) {
    if (dat == null) return false;
    final Box<LLNode<Data>> prd = new Box<>();
    return FRefIterator().ForEachForward(cur -> {
      LLNode<Data> node = cur.Get();
      if (node.Get().equals(dat)) {
        cur.Set(node.GetNext().Get());
        if (tailref.Get() == node) { tailref.Set(prd.Get()); }
        size.Decrement();
        return true;
      }
      prd.Set(node);
      return false;
    });
  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  public class ListFIterator implements ForwardIterator<Data> {

    protected MutableForwardIterator<Box<LLNode<Data>>> ref = FRefIterator(); 
    
    public ListFIterator() {}

    public ListFIterator( ListFIterator itr ) { ref = itr.ref; }

    @Override
    public boolean IsValid() { 

      return ref.IsValid();

    }

    @Override
    public void Reset() {

      ref.Reset();

    }

    @Override
    public Data GetCurrent() {

      if(!IsValid()) throw new IllegalStateException(" Iterator is not valid");
      return ref.GetCurrent().Get().Get();

    }

    @Override
    public void Next(){

      ref.Next();

    }

    @Override
    public Data DataNNext() {
      
      Data oldData = ref.GetCurrent().Get().Get();

      Next();

      return oldData;

    }
  }

  public ForwardIterator<Data> FIterator(){ return new ListFIterator(); }
  

  public class ListBIterator implements BackwardIterator<Data> {

    protected MutableBackwardIterator<Box<LLNode<Data>>> ref = BRefIterator(); 
    
    public ListBIterator() {}

    public ListBIterator( ListBIterator itr ) { ref = itr.ref; }

    @Override
    public boolean IsValid() { 

      return ref.IsValid();

    }

    @Override
    public void Reset() {

      ref.Reset();

    }

    @Override
    public Data GetCurrent() {

      if(!IsValid()) throw new IllegalStateException(" Iterator is not valid");
      return ref.GetCurrent().Get().Get();

    }

    @Override
    public void Prev(){

      ref.Prev();

    }

    @Override
    public Data DataNPrev() {
      
      Data oldData = ref.GetCurrent().Get().Get();

      Prev();

      return oldData;

    }
  }

  public BackwardIterator<Data> BIterator(){ return new ListBIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  public Data GetFirst(){

    if(this.IsEmpty()) throw new IndexOutOfBoundsException("List is empty");

    return headref.Get().Get();

  }

  public Data GetLast(){

    if(this.IsEmpty()) throw new IndexOutOfBoundsException("List is empty");

    return tailref.Get().Get();

  }

  public Sequence<Data> SubSequence(Natural from, Natural to){

    long start = ExcIfOutOfBound(from);
    long end = ExcIfOutOfBound(to);
    long newSize = end - start + 1;

    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

    for (long i = 0; i < start; i++) {
      f_iter.Next();
    }

    LLNode<Data> newChainHead = null;
    LLNode<Data> newChainTail = null;

    for(long i = 0; i < newSize; i++){

      Data newdat = f_iter.GetCurrent().Get().Get();

      LLNode<Data> newNode = new LLNode<>(newdat);

      if(newChainHead == null){

        newChainHead = newNode;
        newChainTail = newNode;

      } else {

        newChainTail.SetNext(newNode);
        newChainTail = newNode;

      }

      f_iter.Next();

    }

    return NewChain(newSize, newChainHead, newChainTail);

  }


  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  public Data AtNRemove(Natural pos){

    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

    if(pos.ToLong() != 0){

      for(long i = 0; i < ExcIfOutOfBound(pos) - 1; i++){

        f_iter.Next();
  
      }
  
      Data oldData = f_iter.GetCurrent().Get().GetNext().Get().Get();
      f_iter.GetCurrent().Get().SetNext(f_iter.GetCurrent().Get().GetNext().Get().GetNext().Get());

      size.Decrement();
  
      return oldData;

    } else { Data oldData = GetFirst(); RemoveFirst(); size.Decrement(); return oldData; }

  }

  public void RemoveFirst(){

    if(this.IsEmpty()) { throw new IndexOutOfBoundsException("List is empty"); }

    headref.Set(headref.Get().GetNext().Get());

    if(headref.Get() == null) { tailref.Set(null);  }

    size.Decrement();

  }

  public void RemoveLast(){

    if(this.IsEmpty()) { throw new IndexOutOfBoundsException("List is empty"); }
    else if(tailref.Get() == headref.Get()) { RemoveFirst(); }

    MutableBackwardIterator<Box<LLNode<Data>>> iter_b = BRefIterator();
    iter_b.Prev();

    tailref.Set(iter_b.GetCurrent().Get());
    tailref.Get().SetNext(null);

    size.Decrement();

  }

  public Data FirstNRemove(){

    Data oldDat = GetFirst();
    RemoveFirst();

    return oldDat;

  }

  public Data LastNRemove(){

    Data oldDat = GetLast();
    RemoveLast();

    return oldDat;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  public boolean Filter(Predicate<Data> fun) {
    MutableNatural oldsize = this.size;
    if (fun != null) {
      ForwardIterator<Box<LLNode<Data>>> itr = FRefIterator();
      LLNode<Data> pred = null;
      while (itr.IsValid()) {
        Box<LLNode<Data>> cur = itr.GetCurrent();
        LLNode<Data> node = cur.Get();
        if (fun.Apply(node.Get())) {
          itr.Next();
        } else {
          cur.Set(node.GetNext().Get());
          if (tailref.Get() == node) { tailref.Set(pred); }
          size.Decrement();
        }

        pred = node;
      }
    }
    return !size.equals(oldsize);
  }

}
