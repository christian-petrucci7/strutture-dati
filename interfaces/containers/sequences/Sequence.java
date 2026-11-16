package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data> { // Must extend IterableContainer

  default Data GetAt(Natural nat){

    if(IsEmpty()){ throw new IndexOutOfBoundsException(); }

    if (IsInBound(nat)){

      ForwardIterator <Data> iter = FIterator();

      iter.Next(nat);

      return iter.GetCurrent();

    }

    return null;

  }

  default Data GetFirst(){

    return GetAt(Natural.ZERO);

  }
  
  default Data GetLast(){

    return GetAt(IsEmpty() ? Natural.ZERO : Natural.Of(Size().ToLong() - 1));

  }

  default Natural Search(Data dat){

    if(IsEmpty()) throw new IndexOutOfBoundsException("empty");

    final Box<Long> location = new Box<>(-1L);

    if(TraverseForward(val -> { 

      location.Set(location.Get() + 1); 
      return (val == null && dat == null || val != null && dat != null && val.equals(dat)); 
    
    })) 
    
      { return Natural.Of(location.Get()); }

    return null;

  }

  default boolean IsInBound(Natural num){

    if (num == null) throw new NullPointerException("Natural number cannot be null!");
    long idx = num.ToLong();
    return (idx < Size().ToLong());

  }

  default long ExcIfOutOfBound(Natural num) {
    if (num == null) throw new NullPointerException("Natural number cannot be null!");
    long idx = num.ToLong();
    if (idx >= Size().ToLong()) throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
    return idx;
  }

  Sequence<Data> SubSequence(Natural start, Natural end);

}
