package apsd.classes.containers.collections.abstractcollections;

import apsd.interfaces.containers.collections.SortedChain;
import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.classes.containers.collections.concretecollections.LLSortedChain;

/** Object: Wrapper ordered set implementation via ordered chain. */
public class WOrderedSet<Data extends Comparable<Data>> extends WOrderedSetBase<Data, SortedChain<Data>> { // Must extend WOrderedSetBase

  public WOrderedSet(){ super(); }

  public WOrderedSet(LLSortedChain<Data> chn){ super(chn); }

  public WOrderedSet(TraversableContainer<Data> con){ super(con); }

  public WOrderedSet(SortedChain<Data> chn, TraversableContainer<Data> con){ super(chn, con); }

  protected void ChainAlloc() { chn = new VSortedChain<>(); }

}
