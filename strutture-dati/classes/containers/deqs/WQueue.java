package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper queue implementation. */
public class WQueue<Data> implements Queue<Data>{ // Must implement Queue

  protected final List<Data> lst;

  public WQueue(){ lst = new VList<>(); }

  public WQueue(List<Data> lst) { 
    if(lst == null) throw new NullPointerException("List cannot be null");
    this.lst = lst; 
  }

  public WQueue(TraversableContainer<Data> con){ 

    lst = new VList<>(con);
  
  }

  public WQueue(List<Data> lst, TraversableContainer<Data> con){ 

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
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */

  public Data Head(){

    if(IsEmpty()) return null;

    return lst.GetFirst();

  }

  @Override
  public void Dequeue() {
    
    //if(IsEmpty()) throw new NullPointerException("List cannot be empty");

    if(!IsEmpty()) { lst.Remove(Head()); }

  }

  @Override
  public void Enqueue(Data dat) {
    
     lst.InsertAt(dat, Natural.Of(Size().ToLong()));

  }

  public Data HeadNDequeue (Data dat){

    Data oldData = Head();

    Dequeue();

    return oldData;

  }

}
