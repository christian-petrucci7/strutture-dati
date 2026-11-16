package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

/** Interface: Sequence con supporto alla rimozione di un dato tramite posizione. */
public interface RemovableAtSequence<Data> extends Sequence<Data> { // Must extend Sequence

  default void RemoveAt(Natural location){ if(IsInBound(location)) AtNRemove(location); }

  Data AtNRemove(Natural nat);

  default void RemoveFirst(){ 
    if(IsEmpty()) throw new IndexOutOfBoundsException("Sequence empty");  
    RemoveAt(Natural.ZERO); 
  }

  default Data FirstNRemove(){ return AtNRemove(Natural.ZERO); }

  default void RemoveLast() { 

    if(IsEmpty()) {

      throw new IndexOutOfBoundsException("Sequence empty");

    } else {

      RemoveAt(Natural.Of(Size().ToLong() - 1)); 

    }
  
  }
  default Data LastNRemove(){

    if (IsEmpty()){

      return AtNRemove(Natural.ZERO);

    } else {

      return AtNRemove(Natural.Of(Size().ToLong() - 1));

    }

  }

}
