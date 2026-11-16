package apsd.classes.containers.deqs;

import apsd.classes.utilities.Natural;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data> {  

  protected final List<Data> lst;

  public WStack(){ lst = new VList<>(); }

  public WStack(List<Data> lst){ this.lst = lst; }

  public WStack(TraversableContainer<Data> con) { lst = new VList<>(con); }

  public WStack(List<Data> lst, TraversableContainer<Data> con){ 

    if(lst == null) throw new NullPointerException("List cannot be null");

    con.TraverseForward( val -> { lst.InsertLast(val); return false; }  );

    this.lst = lst;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  public Natural Size(){

    return lst.Size();

  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  public void Clear(){

    lst.Clear();

  }

  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */

  public Data Top(){

    if(IsEmpty()) return null;

    return lst.GetLast();

  }

  public void Pop(){

    if(!IsEmpty()) { lst.Remove(Top()); }

  }

  public Data TopNPop(){

    Data oldData = Top();
    Pop();
    return oldData;

  }

  public void SwapTop(Data dat){

    Pop();
    Push(dat);

  }

  public Data TopNSwap(Data dat){

    Data oldData = Top();
    SwapTop(dat);
    return oldData;

  }

  public void Push(Data dat){

    lst.InsertLast(dat);

  }

}
