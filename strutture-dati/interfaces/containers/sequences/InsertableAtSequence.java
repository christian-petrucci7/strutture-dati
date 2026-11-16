package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto all'inserimento di un dato tramite posizione. */
public interface InsertableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

  void InsertAt(Data newdat, Natural position);

  default void InsertFirst(Data newdat){

    InsertAt(newdat, Natural.ZERO);

  }

  default void InsertLast(Data newdat){

    InsertAt(newdat, IsEmpty() ? Natural.ZERO : Natural.Of(Size().ToLong()));

  }

}
