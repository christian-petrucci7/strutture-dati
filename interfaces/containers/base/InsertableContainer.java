package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto all'inserimento di un dato. */
public interface InsertableContainer<Data> extends Container{ // Must extend Container

  boolean Insert(Data dat);

  default boolean InsertAll(TraversableContainer <Data> trav){

    final Box<Boolean> all = new Box<>(true);
    if(trav != null) trav.TraverseForward(dat -> {all.Set(all.Get() && Insert(dat)); return false;});
    return all.Get();

  }

  default boolean InsertSome(TraversableContainer <Data> trav){

    final Box<Boolean> some = new Box<>(false);
    if(trav != null) trav.TraverseForward(dat-> {some.Set(some.Get() || Insert(dat)); return false;});
    return some.Get();

  }

}
