package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends RemovableAtSequence<Data>, Set<Data> { // Must extend RemovableAtSequence

  default boolean InsertIfAbsent(Data dat){

    if(!Exists(dat)) { Insert(dat); return true; }

    return false;

  }

  default void RemoveOccurrences(Data dat){

    while(Exists(dat)) { Remove(dat); }

  }

  default Chain<Data> SubChain(Natural from, Natural to){

    return (Chain<Data>) SubSequence(from, to);

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  default Natural Search(Data dat){

    if(dat == null) return null;
    return RemovableAtSequence.super.Search(dat);

  }

}
