package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> { // Must extend ClearableContainer and InsertableContainer

  Data Top();
  void Pop();

  default Data TopNPop(){

    Data top = Top();
    Pop();

    return top;

  }

  default void SwapTop(Data dat){

    Pop();
    Push(dat);

  }
  default Data TopNSwap(Data dat){

    Data top = Top();
    SwapTop(dat);

    return top;

  }

  void Push(Data dat);

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  default void Clear(){

    while(!(IsEmpty())){

      Pop();

    }

  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  default boolean Insert(Data dat){

    Push(dat);

    return true;

  }

}
