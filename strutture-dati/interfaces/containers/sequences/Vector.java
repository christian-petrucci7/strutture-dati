package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends  ReallocableContainer, MutableSequence<Data> { // Must extend ReallocableContainer and MutableSequence

  default void ShiftLeft(Natural pos) { ShiftLeft(pos, Natural.ONE); }

  default void ShiftLeft(Natural pos, Natural num) {
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len > 0) {
      long iniwrt = idx;
      long wrt = iniwrt;
      for (long rdr = wrt + len; rdr < size; rdr++, wrt++) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; wrt - iniwrt < len; wrt++) {
        SetAt(null, Natural.Of(wrt));
      }
    }
  }

  default void ShiftFirstLeft () { ShiftLeft(Natural.ZERO); }

  default void ShiftLastLeft () { ShiftLeft(IsEmpty() ? Natural.ZERO : Natural.Of(Size().ToLong() - 1)); }

  default void ShiftRight(Natural pos) { ShiftRight(pos, Natural.ONE); }

  default void ShiftRight(Natural pos, Natural num){
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    len = (len <= size - idx) ? len : size - idx;
    if (len > 0) {
      long iniwrt = size - 1;
      long wrt = iniwrt;
      for (long rdr = wrt - len; rdr >= idx; rdr--, wrt--) {
        Natural natrdr = Natural.Of(rdr);
        SetAt(GetAt(natrdr), Natural.Of(wrt));
        SetAt(null, natrdr);
      }
      for (; iniwrt - wrt < len; wrt--) {
        SetAt(null, Natural.Of(wrt));
      } }
    
    }

  default void ShiftFirstRight() { ShiftRight(Natural.ZERO); }

  default void ShiftLastRight() { ShiftRight(IsEmpty() ? Natural.ZERO : Natural.Of(Size().ToLong() - 1)); }

  default Vector<Data> SubVector(Natural from, Natural to){ return (Vector<Data>) SubSequence(from, to); }

  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */

  default Natural Size(){

    return ReallocableContainer.super.Size();

  } 

}
