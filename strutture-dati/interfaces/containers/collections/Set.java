package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Box;
import apsd.interfaces.containers.base.IterableContainer;
//import apsd.interfaces.containers.iterators.ForwardIterator;

public interface Set<Data> extends Collection<Data> { // Must extend Collection

  default void Union(Set<Data> set){ set.TraverseForward(dat -> { Insert(dat); return false; }); }

  default void Difference(Set<Data> set){ set.TraverseForward(dat -> { Remove(dat); return false; }); }

  default void Intersection(Set<Data> set) { Filter(dat -> set.Exists(dat)); }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  default boolean IsEqual(IterableContainer <Data> iterCon){

    if (this == iterCon) return true;
    if (this.Size() != iterCon.Size()) return false;

    final Box<Boolean> bool = new Box<>(true);

    if(iterCon != null) { TraverseForward(dat -> { 
      bool.Set( iterCon.Exists(dat) && bool.Get() ); 
      if ( bool.Get() ) return false;
      else return true; }); 
    }

    return bool.Get();

  }

}
