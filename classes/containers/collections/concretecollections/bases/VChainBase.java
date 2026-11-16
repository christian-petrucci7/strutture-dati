package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract list base implementation on (dynamic circular) vector. */
abstract public class VChainBase<Data> implements Chain<Data> { // Must implement Chain

  protected final DynVector<Data> vec;

  public VChainBase() { vec = new DynCircularVector<>(); }

  public VChainBase(DynVector<Data> vec){ this.vec = vec; }

  public VChainBase(TraversableContainer<Data> con){ vec = new DynCircularVector<>(con); }

  protected abstract VChainBase<Data> NewChain(DynVector<Data> vec);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  public Natural Size(){
    
    return vec.Size();

  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  public void Clear(){

    vec.Clear();

  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  public boolean Remove(Data dat){

    if(IsEmpty()|| dat == null ) return false;

    Natural pos = vec.Search(dat);

    if(pos == null) return false;

    vec.ShiftLeft(pos);

    Size().Decrement();

    return true;

  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  public ForwardIterator<Data> FIterator(){ return vec.FIterator(); }


  public BackwardIterator<Data> BIterator(){ return vec.BIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural pos){

    return vec.GetAt(pos);

  }

  public Sequence<Data> SubSequence(Natural from, Natural to){
    
    long size = ((to.ToLong() - from.ToLong()) + 1);
    DynVector<Data> newvec = new DynCircularVector<>(Natural.Of(size));

    ForwardIterator<Data> iter_forward = FIterator();

    iter_forward.Next(from);

    long start = 0;

    while(start < size){

      vec.SetAt(iter_forward.GetCurrent(), Natural.Of(start));
      start++;
      iter_forward.Next();

    }

    return NewChain(newvec);

  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  public Data AtNRemove(Natural pos) { 

    if(IsEmpty()) throw new IndexOutOfBoundsException("List is empty");

    Data oldData = GetAt(pos);

    Remove(oldData);

    Size().Decrement();

    return oldData;

   }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  @Override
  public boolean Filter(Predicate<Data> fun) {

    long del = 0;
    if(fun != null){

      MutableForwardIterator<Data> iter = vec.FIterator();
      for(; iter.IsValid(); iter.Next()){
        if(!fun.Apply(iter.GetCurrent())) {

          del++;
          iter.SetCurrent(null);

        }
      }
      iter.Reset();
      MutableForwardIterator<Data> iter2 = vec.FIterator();
      for(; iter2.IsValid(); iter2.Next()){

        if(iter2.GetCurrent() != null){

          Data dat = iter2.GetCurrent();
          iter2.SetCurrent(null);
          iter.SetCurrent(dat);
          iter.Next();

        }
      }
      vec.Reduce(Natural.Of(del));
    }
    return (del > 0);
  }

}
