package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Interface: Sequence & MutableIterableContainer con supporto alla scrittura tramite posizione. */
public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> { // Must extend Sequence and MutableIterableContainer

  default void SetAt(Data newdat, Natural location){

    MutableForwardIterator <Data> iter = FIterator();

    iter.Next(location);

    iter.SetCurrent(newdat);

  }

  default Data GetNSetAt(Data newdat, Natural location){

    Data oldDat = GetAt(location);
    SetAt(newdat, location);

    return oldDat;

  }

  default void SetFirst(Data newdat){

    SetAt(newdat, Natural.ZERO);

  }

  default Data GetNSetFirst(Data newdat){

    Data oldDat = GetFirst();
    SetFirst(newdat);

    return oldDat;

  }

  default void SetLast(Data newdat){

    if(IsEmpty()) { SetFirst(newdat); }
    else { SetAt(newdat, Natural.Of(Size().ToLong() - 1)); }

  }

  default Data GetNSetLast(Data newdat){

    Data oldDat = GetLast();
    SetLast(newdat);

    return oldDat;

  }

  default void Swap(Natural first, Natural second){

    Data first_num = GetAt(first);
    Data second_num = GetAt(second);

    SetAt(first_num, second);
    SetAt(second_num, first);

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  MutableSequence<Data> SubSequence(Natural start, Natural end);

}
