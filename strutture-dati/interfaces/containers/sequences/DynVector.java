package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data>{ // Must extend ResizableContainer, InsertableAtSequence, RemovableAtSequence, and Vector

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  default void InsertAt(Data dat, Natural location){

    if(location.ToLong() == Size().ToLong()) Expand(Natural.ONE); else ShiftRight(location);
    SetAt(dat, location);

  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableAtSequence              */
  /* ************************************************************************ */

  @Override
  default Data AtNRemove(Natural location){

    Data oldData = GetAt(location);
    ShiftLeft(location);

    return oldData;

  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                       */
  /* ************************************************************************ */

  @Override
  default void ShiftLeft(Natural pos, Natural num){

    Vector.super.ShiftLeft(pos, num);
    Reduce(num);

  }

  @Override 
  default void ShiftRight(Natural pos, Natural num){

    Expand(num);
    Vector.super.ShiftRight(pos, num);

  }

  @Override
  default DynVector<Data> SubVector(Natural from, Natural to){ return (DynVector<Data>) Vector.super.SubVector(from, to); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  abstract Natural Size();

}
