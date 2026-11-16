package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) linear vector base implementation. */
abstract public class LinearVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  public LinearVectorBase(){ }

  protected LinearVectorBase(Data[] arr) { super(arr); }

  public LinearVectorBase(Natural inicapacity) { super(inicapacity); }

  public LinearVectorBase(TraversableContainer<Data> con) { super(con); }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  @Override
  public void Realloc(Natural newcapacity){

    if(newcapacity == null) { throw new NullPointerException("Natural cannot be null"); }

    Data[] oldarr = arr;

    ArrayAlloc(newcapacity);
    
    if (oldarr == null) {
      return; 
    }

    int limit = (oldarr.length < this.arr.length) ? oldarr.length : this.arr.length;

    for(int idx = 0; idx < limit; idx++){

      this.arr[idx] = oldarr[idx];

    }

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural position){ 

    return this.arr[(int) ExcIfOutOfBound(position)]; 
  
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural position) { this.arr[(int) ExcIfOutOfBound(position)] = dat;  }

}
