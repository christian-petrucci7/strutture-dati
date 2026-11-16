package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Interface: TraversableContainer con supporto all'iterazione. */
public interface IterableContainer<Data> extends TraversableContainer<Data> { // Must extend TraversableContainer

  ForwardIterator <Data> FIterator();
  BackwardIterator <Data> BIterator();

  default boolean IsEqual(IterableContainer <Data> iterCon){

    if (this == iterCon) return true;
    if (this.Size() != iterCon.Size()) return false;

    ForwardIterator<Data> first_iter = FIterator();
    ForwardIterator<Data> second_iter = iterCon.FIterator();

    while(first_iter.IsValid() && second_iter.IsValid()){

      if(!first_iter.GetCurrent().equals(second_iter.GetCurrent())) return false;
      first_iter.Next();
      second_iter.Next();

    } 
    return !(first_iter.IsValid() || second_iter.IsValid());

  }

  /* ************************************************************************ */
  /* Override specific member functions from TraversableContainer             */
  /* ************************************************************************ */

  @Override
  default boolean TraverseForward(Predicate<Data> pred) { return FIterator().ForEachForward(pred); }

  @Override
  default boolean TraverseBackward(Predicate<Data> pred) { return BIterator().ForEachBackward(pred); }

}
