package apsd.interfaces.containers.sequences;

import apsd.interfaces.containers.base.SortedIterableContainer;
import apsd.classes.utilities.Natural;


/** Interface: Sequence & SortedIterableContainer. */
public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data> { // Must extend Sequence and SortedIterableContainer

  /* ************************************************************************ */
  /* Override specific member functions from MembershipContainer              */
  /* ************************************************************************ */

  default boolean Exists(Data dat){

    long i = 0;
    long j = Size().ToLong();

    while (i < j){

    long middle = (i + j) / 2;

    if (GetAt(Natural.Of(middle)).compareTo(dat) > 0) { j = middle; }

    else if (GetAt(Natural.Of(middle)).compareTo(dat) < 0) { i = middle + 1; }

    else return true;

    }

    return false;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  default Natural Search(Data dat){

    long i = 0;
    long j = Size().ToLong();

    while (i < j){

    long middle = (i + j) / 2;

    if (GetAt(Natural.Of(middle)).compareTo(dat) > 0) { j = middle; }

    else if (GetAt(Natural.Of(middle)).compareTo(dat) < 0) { i = middle + 1; }

    else return Natural.Of(middle);

    }

    return null;

  }

}
