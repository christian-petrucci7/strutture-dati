package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.OrderedSet;
import apsd.interfaces.containers.collections.SortedChain;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WOrderedSetBase<Data extends Comparable<? super Data>, Chn extends SortedChain<Data>> extends WSetBase<Data, Chn> implements OrderedSet<Data>{ // Must extend WSetBase and implement OrderedSet; Chn must extend SortedChain

  public WOrderedSetBase() { ChainAlloc(); }

  public WOrderedSetBase(Chn chn){ 
    
    if(chn == null) throw new NullPointerException("Chain cannot be null");
    this.chn = chn;

  }

  public WOrderedSetBase(TraversableContainer<Data> con){

    ChainAlloc();
    con.TraverseForward(dat -> { chn.InsertIfAbsent(dat); return false; });

  }

  public WOrderedSetBase(Chn chn, TraversableContainer<Data> con){

    if(chn == null) throw new NullPointerException("Chain cannot be null");

    this.chn = chn;
    con.TraverseForward(dat -> { chn.InsertIfAbsent(dat); return false; });

  }

  protected abstract void ChainAlloc();

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  public boolean IsEqual(IterableContainer<Data> iterCon){ return chn.IsEqual(iterCon); }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  public Data Min(){ if(IsEmpty()) return null; return chn.Min(); }
  public Data Max() { if(IsEmpty()) return null; return chn.Max(); }

  public void RemoveMin(){ chn.RemoveMin(); }
  public void RemoveMax(){ chn.RemoveMax(); }

  public Data MinNRemove(){ return chn.MinNRemove(); }
  public Data MaxNRemove(){ return chn.MaxNRemove(); }

  public Data Predecessor(Data dat){ return chn.Predecessor(dat); }
  public Data Successor(Data dat){ return chn.Successor(dat); }

  public void RemovePredecessor(Data dat){ chn.RemovePredecessor(dat); }
  public void RemoveSuccessor(Data dat){ chn.RemoveSuccessor(dat); }

  public Data PredecessorNRemove(Data dat){ return chn.PredecessorNRemove(dat); }
  public Data SuccessorNRemove(Data dat){ return chn.SuccessorNRemove(dat); }

}
