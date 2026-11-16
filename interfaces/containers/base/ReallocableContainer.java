package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

/** Interface: ClearableContainer che è anche Reallocable. */
public interface ReallocableContainer extends ClearableContainer, Reallocable { // Must extend ClearableContainer, Reallocable

  double GROW_FACTOR = 2.0; // Must be strictly greater than 1.
  double SHRINK_FACTOR = 2.0; // Must be strictly greater than 1.

  Natural Capacity();

  default void Grow(){

    long newcapacity = (long) (Capacity().ToLong() * GROW_FACTOR);

    Realloc(Natural.Of(newcapacity));

  };

  default void Grow(Natural nat){

    long newcapacity;

    if(nat != null && nat.IsNotZero()){

      newcapacity = Capacity().ToLong() + nat.ToLong();

      Realloc(Natural.Of(newcapacity));

    }

  };

  default void Shrink(){

    long newcapacity;

    if(Capacity().ToLong() != 0){

      newcapacity = (long) (Capacity().ToLong() / SHRINK_FACTOR);

      Realloc(Natural.Of(newcapacity));
    }

  };

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  default Natural Size(){

    return Capacity();

  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  default void Clear(){

    Realloc(Natural.ZERO);

  }

}
