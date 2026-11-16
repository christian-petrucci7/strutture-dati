package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
// import apsd.interfaces.traits.Predicate;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore all'indietro. */
public interface BackwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

  default void Prev() { DataNPrev(); }
  default void Prev(long stp) { for(; stp > 0; --stp, Prev()); }
  default void Prev(Natural stp) { Prev(stp.ToLong()); }

  Data DataNPrev();

  default boolean ForEachBackward(Predicate<Data> fun) {
    
     if (fun != null) {
       while (IsValid()) {
         if (fun.Apply(DataNPrev())) { return true; }
       }
     }
     return false;
   }

}
