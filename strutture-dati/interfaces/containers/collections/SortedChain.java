package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data> { // Must extend OrderedChain and SortedSequence

  default Natural SearchPredecessor(Data dat){

    if(dat == null) return null;

    long pred = -1;
    long left = 0;
    long right = Size().ToLong() - 1;

    while(left <= right){

      long middle = (left + right) / 2;
      Natural natmid = Natural.Of(middle);
      Data val = GetAt(natmid);

      if(val.compareTo(dat) < 0) { pred = middle; left = middle + 1; }

      else { right = middle - 1; }
      
    }

    return (pred > -1) ? Natural.Of(pred) : null;

  }

  default Natural SearchSuccessor(Data dat){

    if(dat == null) return null;

    long succ = -1;
    long left = 0;
    long right = Size().ToLong() - 1;

    while(left <= right){

      long middle = (left + right) / 2;
      Natural natmid = Natural.Of(middle);
      Data val = GetAt(natmid);

      if(val.compareTo(dat) > 0) { succ = middle; right = middle - 1; }

      else { left = middle + 1; }
      
    }

    return (succ > -1) ? Natural.Of(succ) : null;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  default Natural Search(Data dat){

    if(dat == null) return null;
    return SortedSequence.super.Search(dat);
    
  }

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  default void Intersection(SortedChain<Data> chn) {
    Natural i = Natural.ZERO, j = Natural.ZERO;
    while (i.compareTo(Size()) < 0 && j.compareTo(chn.Size()) < 0) {
      int cmp = GetAt(i).compareTo(chn.GetAt(j));
      if (cmp < 0) {
        RemoveAt(i);
      } else {
        j = j.Increment();
       if (cmp == 0) { i = i.Increment(); }
      }
    }
    while (i.compareTo(Size()) < 0) {
      RemoveAt(i);
    }
  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  default Data Min(){ return GetAt(Natural.ZERO); }
  default Data Max() { return GetAt(IsEmpty() ? Natural.ZERO : Natural.Of(Size().ToLong() - 1)); }
  default void RemoveMin() { RemoveFirst(); }
  default void RemoveMax() { RemoveLast(); }

  default Data MinNRemove(){

    if(Size().IsZero()) return null;
    return FirstNRemove();

  }

  default Data MaxNRemove(){

    if(Size().IsZero()) return null;
    return LastNRemove();
    
  }

  default Data Successor(Data dat){

    Natural location = SearchSuccessor(dat);
    return (location == null) ? null : GetAt(location);

  }

  default Data Predecessor(Data dat){

    Natural location = SearchPredecessor(dat);
    return (location == null) ? null : GetAt(location);

  }

  default void RemovePredecessor(Data dat){ Remove(Predecessor(dat)); }
  default void RemoveSucccessor(Data dat){ Remove(Successor(dat)); }

  default Data PredecessorNRemove(Data dat){

    Data oldPred = Predecessor(dat);
    Remove(oldPred);
    return oldPred;

  }

  default Data SuccessorNRemove(Data dat){

    Data oldSucc = Successor(dat);
    Remove(oldSucc);
    return oldSucc;

  }

}


