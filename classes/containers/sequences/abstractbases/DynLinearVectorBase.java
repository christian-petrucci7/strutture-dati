package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

/** Object: Abstract dynamic linear vector base implementation. */
abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> { // Must extend LinearVectorBase and implement DynVector

  protected long size = 0L;

  public DynLinearVectorBase() { }

  public DynLinearVectorBase(Natural inicapacity) { super(inicapacity); }

  public DynLinearVectorBase( TraversableContainer<Data> con ) { super(con); size = con.Size().ToLong(); }

  protected DynLinearVectorBase(Data[] arr) { this.arr = arr; size = arr.length; }

  @Override
  protected void ArrayAlloc(Natural newcapacity) {
    if (newcapacity == null) throw new NullPointerException("Natural cannot be null");
    final long min_cap = 1L;
    super.ArrayAlloc((newcapacity.ToLong() < min_cap) ? Natural.Of(min_cap) : newcapacity);
  }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  public Natural Size(){

    return Natural.Of(this.size);

  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  public void Clear(){

    super.Clear();
    size = 0L;

  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newcapacity){

    super.Realloc(newcapacity);
    size = (size < arr.length) ? size : arr.length;

  }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  public void Expand(Natural nat){

    //if(nat == null) throw new NullPointerException("Natural number cannot be null");

    DynVector.super.Grow(nat);
    size += nat.ToLong();

  }

  public void Reduce(Natural nat){

    if(nat == null) throw new NullPointerException("Natural number cannot be null");
    if(size < nat.ToLong()) throw new ArithmeticException("Underflow: size cannot be negative");

    size -= nat.ToLong();
    DynVector.super.Shrink();

  }

}
