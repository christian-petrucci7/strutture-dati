package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;

/** Interface: Container con supporto alla rimozione di un dato. */
public interface RemovableContainer<Data> extends Container { // Must extend Container

boolean Remove(Data dat);

default boolean RemoveAll(TraversableContainer<Data> trav){

  final Box<Boolean> all = new Box<>(true);
  if(trav != null) trav.TraverseForward(dat -> {all.Set(all.Get() && Remove(dat)); return false;});
  return all.Get();

}

default boolean RemoveSome(TraversableContainer<Data> trav){

  Box<Boolean> some = new Box<>(false);
  if(trav != null) trav.TraverseForward(dat -> {some.Set(some.Get() || Remove(dat)); return false;});
  return some.Get();

}
  
}
