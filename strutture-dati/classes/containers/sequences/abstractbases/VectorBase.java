package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
//import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;

/** Object: Abstract vector base implementation. */
abstract public class VectorBase<Data> implements Vector<Data> { // Must implement Vector

  protected Data[] arr = null;

  public VectorBase() { ArrayAlloc(Natural.ZERO); }


  protected VectorBase(Data[] arr){

    this.arr = arr;

  }

  public VectorBase(Natural inicapacity){

    if (inicapacity == null) throw new NullPointerException("Natural cannot be null");
    ArrayAlloc(inicapacity);

  }

  public VectorBase(TraversableContainer<Data> con){

    if(con == null) throw new NullPointerException("Il contenitore passato al costruttore non può essere null.");
    ArrayAlloc(con.Size());
    final MutableNatural idx = new MutableNatural();
    con.TraverseForward(dat -> { SetAt(dat, idx.GetNIncrement()); return false; });


  }

  abstract protected VectorBase<Data> NewVector(Data[] arr);

  @SuppressWarnings("unchecked")
  protected void ArrayAlloc(Natural newcapacity) {
    long size = newcapacity.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    arr = (Data[]) new Object[(int) size];
  }

  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */

  @Override
  public void Clear() { ArrayAlloc(Natural.ZERO); }

  /* ************************************************************************ */
  /* Override specific member functions from ResizableContainer               */
  /* ************************************************************************ */

  @Override
  public Natural Capacity(){ return Natural.Of(arr.length); }

  /* ************************************************************************ */
  /* Override specific member functions from IterableContainer                */
  /* ************************************************************************ */

  protected class VectorFIterator implements MutableForwardIterator<Data>{

    protected long currentIdx = 0L;

    @Override
    public boolean IsValid(){ return currentIdx < Size().ToLong(); }

    @Override
    public void Reset(){ currentIdx = 0L; }

    @Override
    public Data GetCurrent(){

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");
      return GetAt(Natural.Of(currentIdx));

    }

    @Override 
    public void SetCurrent(Data dat){

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");
      SetAt(dat, Natural.Of(currentIdx));

    }

    @Override
    public Data DataNNext(){

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");

      Data dat = GetCurrent();
      currentIdx++;

      return dat;

    }

  }

  public MutableForwardIterator <Data> FIterator(){ return new VectorFIterator(); }

  protected class VectorBIterator implements MutableBackwardIterator<Data>{

    protected long currentIdx = Size().ToLong() - 1;

    @Override
    public boolean IsValid(){ return currentIdx >= 0; }

    @Override
    public void Reset() { currentIdx = Size().ToLong() - 1; }

    @Override
    public Data GetCurrent() {

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");
      return GetAt(Natural.Of(currentIdx));

    }

    @Override
    public void SetCurrent(Data dat){

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");
      SetAt(dat, Natural.Of(currentIdx));

    }

    @Override
    public Data DataNPrev(){

      if(!IsValid()) throw new IllegalStateException("Iterator terminated");
      Data dat = GetCurrent();
      currentIdx--;

      return dat;
    }

  }

  public MutableBackwardIterator <Data> BIterator(){ return new VectorBIterator(); }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  public abstract Data GetAt(Natural position);

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  public abstract void SetAt(Data dat, Natural position);

  @SuppressWarnings("unchecked")
  public MutableSequence<Data> SubSequence(Natural from, Natural to){

    int size = (int) ((to.ToLong() - from.ToLong()) + 1);
    Data[] newarr = (Data[]) new Object [size] ;

    MutableForwardIterator<Data> iter_forward = FIterator();

    iter_forward.Next(from);

    long start = 0;

    while(start < size){

      newarr[(int) start] = iter_forward.GetCurrent();
      start++;
      iter_forward.Next();

    }

    return NewVector(newarr);

  }

}
