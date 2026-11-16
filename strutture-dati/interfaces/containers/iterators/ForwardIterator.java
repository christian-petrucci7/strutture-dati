package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

/** Interface: Iteratore in avanti. */
public interface ForwardIterator<Data> extends Iterator<Data> { // Must extend Iterator

  default void Next() { DataNNext(); }
  default void Next(long stp) { for(; stp > 0; --stp, Next()) {} }
  default void Next(Natural stp) { Next(stp.ToLong()); }

  Data DataNNext();

  default boolean ForEachForward(Predicate<Data> fun) {
     if (fun != null) {
       while (IsValid()) {
         if (fun.Apply(DataNNext())) { return true; }
       }
     }
     return false;
   }

}
