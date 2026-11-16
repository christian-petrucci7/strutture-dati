package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
// import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Concrete set implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data>{ // Must extend VChainBase and implements SortedChain

  public VSortedChain(){ super(); }

  public VSortedChain(VSortedChain<Data> chn) { super(chn); }

  public VSortedChain(TraversableContainer<Data> con){  

    con.TraverseForward(dat -> { Insert(dat); return false; });

  }

  protected VSortedChain(DynVector<Data> vec){ super(vec); }

  protected VChainBase<Data> NewChain(DynVector<Data> vec){ return new VSortedChain<>(vec); }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {

    if(dat == null) return false;
    Natural pred = SearchPredecessor(dat);
    Natural pos = (pred == null) ? Natural.ZERO : pred.Increment();
    vec.ShiftRight(pos);
    vec.SetAt(dat, pos);
    return true;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  @Override
  public boolean InsertIfAbsent(Data dat){

    if(dat == null) return false;
    Natural pred = SearchPredecessor(dat);
    Natural pos = (pred == null) ? Natural.ZERO : pred.Increment();
    if(IsInBound(pos) && vec.GetAt(pos).equals(dat)) return false;
    vec.ShiftRight(pos);
    vec.SetAt(dat, pos);
    return true;

  }

  @Override
  public void RemoveOccurrences(Data dat){

    if(dat == null) return;
    Natural pred = SearchPredecessor(dat);
    long current = (pred == null) ? 0 : pred.ToLong() + 1;
    long ini = current;
    long size = vec.Size().ToLong();
    for(; current < size && vec.GetAt(Natural.Of(current)).compareTo(dat) == 0; current++){}
    long del = current - ini;
    vec.ShiftLeft(Natural.Of(ini), Natural.Of(del));

  }

  
}
