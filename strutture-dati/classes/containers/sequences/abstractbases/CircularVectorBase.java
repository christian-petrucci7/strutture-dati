package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

/** Object: Abstract (static) circular vector base implementation. */
abstract public class CircularVectorBase<Data> extends VectorBase<Data> { // Must extend VectorBase

  protected long start = 0L;

  public CircularVectorBase() { }

  public CircularVectorBase( Data[] arr ) { super (arr); }

  public CircularVectorBase ( Natural inicapacity ) { super(inicapacity); }

  public CircularVectorBase ( TraversableContainer<Data> con ) { super(con); }

  @Override
  public void ArrayAlloc(Natural newcapacity){ 

    super.ArrayAlloc(newcapacity);
    start = 0L;

  }

  /* ************************************************************************ */
  /* Override specific member functions from ReallocableContainer             */
  /* ************************************************************************ */

  public void Realloc(Natural newcapacity){ 

    if (newcapacity == null) { throw new NullPointerException("Natural cannot be null"); }
    Data[] oldarr = arr; 
    long oldstart = start;

    ArrayAlloc(newcapacity);

    int limit = (oldarr.length < this.arr.length) ? oldarr.length : this.arr.length;

    for (int i = 0; i < limit; i++) {

      this.arr[i] = oldarr[ (int) ((oldstart + i) % oldarr.length) ];

    }

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  @Override
  public Data GetAt(Natural position){

    if (this.IsEmpty()) {
      throw new IndexOutOfBoundsException();
   } else { return this.arr[ ((int) (ExcIfOutOfBound(position) + start)) % arr.length ]; }


  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  public void SetAt(Data dat, Natural position){

    this.arr[ ((int) (ExcIfOutOfBound(position) + start)) % arr.length ] = dat; 

  }

  /* ************************************************************************ */
  /* Specific member functions of Vector                                      */
  /* ************************************************************************ */

  @Override
  public void ShiftLeft(Natural pos, Natural num) {

    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();

    len = (len <= size - idx) ? len : size - idx;
    if(idx < size - (idx + len)){

      long iniwrt = idx - 1 + len;
      long wrt = iniwrt;
      for( long rdr = wrt - len; rdr >= 0; rdr--, wrt--){
        
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);

      }
      for(; iniwrt - wrt < len; wrt--){

        SetAt(null, Natural.Of(wrt));

      }
      start = (start + len) % arr.length;

    } else { super.ShiftLeft(pos, num); }

  }

  @Override
  public void ShiftRight(Natural pos, Natural num) {

    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();

    len = (len <= size - idx) ? len : size - idx;
    if( idx < size - (idx - len)) {

      start = (arr.length + start - len) % arr.length;
      long iniwrt = 0;
      long wrt = iniwrt;

      for(long rdr = wrt + len; rdr < idx + len; rdr++, wrt++){

        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }

      for(; wrt - iniwrt < len; wrt++){

        SetAt(null, Natural.Of(wrt));

      }

    } else { super.ShiftRight(pos, num); }

  }

}
