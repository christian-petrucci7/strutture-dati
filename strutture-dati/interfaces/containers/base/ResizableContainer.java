package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer { // Must extend ReallocableContainer

  double THRESHOLD_FACTOR = 2.0; // Must be strictly greater than 1.

  default void Expand(){

    Expand(Natural.ONE);

  }

  void Expand(Natural nat);

  default void Reduce(){

    Reduce(Natural.ONE);

  }

  void Reduce(Natural nat);

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  @Override
  abstract Natural Size();

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  default void Grow(Natural nat) {

    if (Capacity().ToLong() == Integer.MAX_VALUE){
      
      throw new ArithmeticException("Overflow: capacity cannot overcome Integer.MAX_VALUE");

    }
    if (Size().ToLong() + nat.ToLong() >= Capacity().ToLong()){

      ReallocableContainer.super.Grow(nat);

    }

  }

  @Override
  default void Shrink() {

    if ((long) (THRESHOLD_FACTOR * SHRINK_FACTOR * Size().ToLong()) <= Capacity().ToLong()) ReallocableContainer.super.Shrink();

  }

}
