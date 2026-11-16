package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.traits.Predicate;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> { // Must implement Set; Chn must extend Chain

  protected Chn chn;

  public WSetBase() { ChainAlloc(); }

  public WSetBase(Chn chn){ 

    if (chn == null) throw new NullPointerException("chain cannot be null");
    this.chn = chn;
    
  }

  public WSetBase(TraversableContainer<Data> con){

    ChainAlloc();
    con.TraverseForward(dat -> { chn.InsertIfAbsent(dat); return false; });

  }

  public WSetBase(Chn chn, TraversableContainer<Data> con){

    this.chn = chn;
    con.TraverseForward(dat -> { chn.InsertIfAbsent(dat); return false; });

  }

  protected abstract void ChainAlloc( );

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  public Natural Size(){

    return chn.Size();

  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  public void Clear(){

    chn.Clear();

  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  public boolean Insert(Data dat){

    return chn.InsertIfAbsent(dat);

  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  public boolean Remove(Data dat){

    if(IsEmpty() || dat == null ) return false;

    return chn.Remove(dat);

  }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  public ForwardIterator<Data> FIterator(){ 
    return chn.FIterator(); 
  }

  public BackwardIterator<Data> BIterator(){  
    return chn.BIterator(); 
  }

  /* ************************************************************************ */
  /* Override specific member functions from Collection                       */
  /* ************************************************************************ */

  public boolean Filter(Predicate<Data> fun){

    return chn.Filter(fun);

  }

  /* ************************************************************************ */
  /* Override specific member functions from Set                              */
  /* ************************************************************************ */

  // ...

}
