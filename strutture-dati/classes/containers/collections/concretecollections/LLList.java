package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
// import apsd.classes.containers.collections.concretecollections.bases.LLNode;
// import apsd.classes.utilities.Box;
// import apsd.classes.utilities.Natural;
// import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
// import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
// import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Sequence;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> { // Must extend LLChainBase and implement List

  public LLList() { }

  public LLList(TraversableContainer<Data> con){ super(con); }

  protected LLList(long size, LLNode<Data> head, LLNode<Data> tail) {super(size, head, tail); }

  protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) { return new LLList<>(size, head, tail); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  public class MutFIterator extends ListFIterator implements MutableForwardIterator<Data>{
    
    public MutFIterator() {}

    public MutFIterator( MutFIterator itr ) { super(itr); }

    @Override
    public void SetCurrent(Data dat){

      if(!IsValid()) throw new IllegalStateException(" Iterator is not valid");
      ref.GetCurrent().Get().Set(dat);

    }

  }

  @Override
  public MutableForwardIterator<Data> FIterator() { return new MutFIterator(); }

  public class MutBIterator extends ListBIterator implements MutableBackwardIterator<Data>{
    
    public MutBIterator() {}

    public MutBIterator( MutBIterator itr ) { super(itr); }

    @Override
    public void SetCurrent(Data dat){

      if(!IsValid()) throw new IllegalStateException(" Iterator is not valid");
      ref.GetCurrent().Get().Set(dat);

    }

  }

  @Override
  public MutableBackwardIterator<Data> BIterator() { return new MutBIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural pos){

    MutableForwardIterator<Data> f_iter = FIterator();

    if(IsEmpty()) throw new IndexOutOfBoundsException("List is empty");

    else if(pos.ToLong() != 0){

      for(long i = 0; i < ExcIfOutOfBound(pos); i++){

        f_iter.Next();
  
      }

      f_iter.SetCurrent(dat);

    } else { SetFirst(dat); }

  }

  @Override
  public void SetFirst(Data dat){

    if(IsEmpty()) throw new IndexOutOfBoundsException("List is empty");

    headref.Get().Set(dat);

  }

  @Override
  public void SetLast(Data dat){

    if(IsEmpty()) throw new IndexOutOfBoundsException("List is empty");

    tailref.Get().Set(dat);

  }

  @Override
  public MutableSequence<Data> SubSequence(Natural from, Natural to){

    Sequence<Data> sub = super.SubSequence(from, to);

    return (MutableSequence<Data>) sub;

  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  public void InsertAt(Data newdat, Natural pos) {

    LLNode<Data> newNode = new LLNode<>(newdat);

    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

    if(IsEmpty() || pos.ToLong() == 0){ InsertFirst(newdat); }

    else if(pos.ToLong() == size.ToLong()) { InsertLast(newdat); }

    else {

      for(long i = 0; i < ExcIfOutOfBound(pos) - 1 ; i++){

        f_iter.Next();
  
      }

      LLNode<Data> currentNode = f_iter.GetCurrent().Get();
      LLNode<Data> oldNextNode = currentNode.GetNext().Get();

      currentNode.SetNext(newNode);
      newNode.SetNext(oldNextNode);

      size.Increment();

    } 

  }

  @Override
  public void InsertFirst(Data newdat){

    LLNode<Data> newNode = new LLNode<>(newdat);

    if(IsEmpty()){

      headref.Set(newNode);
      tailref.Set(newNode);

    } else {

      LLNode<Data> oldNextNode = headref.Get();
      headref.Set(newNode);
      newNode.SetNext(oldNextNode);

    }

    size.Increment();

  }

  @Override 
  public void InsertLast(Data newdat){

    LLNode<Data> newNode = new LLNode<>(newdat);

    if(IsEmpty()){ 
      headref.Set(newNode);
      tailref.Set(newNode);
    }

    else {

      tailref.Get().SetNext(newNode);
      tailref.Set(newNode);

    }

    size.Increment();

  }

} 
