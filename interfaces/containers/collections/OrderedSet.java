package apsd.interfaces.containers.collections;

public interface OrderedSet<Data extends Comparable<? super Data>> extends Set<Data>{ // Must extend Set

  default Data Min(){ 
    return FoldForward( 

      (dat, min) -> { return (min == null || dat.compareTo(min) < 0) ? dat : min; },
      null

    );  
  }

  default void RemoveMin() { Remove(Min()); }

  default Data MinNRemove(){

    Data oldMin = Min();
    Remove(oldMin);
    return oldMin;

  }

  default Data Max(){

    return FoldForward( 

      (dat, max) -> { return (max == null || dat.compareTo(max) > 0) ? dat : max; },
      null

    );  

  }

  default void RemoveMax(){ Remove(Max()); }

  default Data MaxNRemove(){

    Data oldMax = Max();
    Remove(oldMax);
    return oldMax;

  }

  default Data Predecessor(Data dat){

    return FoldForward( 

      (cur, acc) -> { return (cur.compareTo(dat) < 0 && (acc == null || cur.compareTo(acc) > 0)) ? cur : acc; },
      null

    );  
  }

  

  default void RemovePredecessor(Data dat){ Remove(Predecessor(dat)); }

  default Data PredecessorNRemove(Data dat) {

    Data oldPred = Predecessor(dat);
    Remove(oldPred);
    return oldPred;

  }

  default Data Successor(Data dat){

    return FoldForward(

      (cur, acc) -> { return (cur.compareTo(dat) > 0 && (acc == null || cur.compareTo(acc) < 0)) ? cur : acc; }, 
      null

    );

  }

  default void RemoveSuccessor(Data dat) { Remove(Successor(dat)); }

  default Data SuccessorNRemove(Data dat) {

    Data oldSucc = Successor(dat);
    Remove(oldSucc);
    return oldSucc;

  }
}

