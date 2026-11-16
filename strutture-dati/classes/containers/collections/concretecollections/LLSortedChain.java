package apsd.classes.containers.collections.concretecollections;

//import javax.xml.crypto.Data;

//import javax.xml.crypto.Data;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
// import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

/** Object: Concrete sorted chain implementation on linked-list. */
public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data>{

  public LLSortedChain() {  }

  public LLSortedChain(LLSortedChain<Data> chn) { 

    if (chn == null) {
        throw new IllegalArgumentException("La lista sorgente non può essere null");
    }

    if (chn.IsEmpty()) {
        return;
    }

    LLNode<Data> currentSourceNode = chn.headref.Get();

    while (currentSourceNode != null) {

      LLNode<Data> newNode = new LLNode<>(currentSourceNode.Get());
      
      if (IsEmpty()) {
          headref.Set(newNode);
          tailref.Set(newNode);
      } else {
          tailref.Get().SetNext(newNode); 
          tailref.Set(newNode);           
      }
      size.Increment();
      currentSourceNode = currentSourceNode.GetNext().Get();
    }
  
  }

  public LLSortedChain(TraversableContainer<Data> con){ super(con); }

  protected LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail){ super(size, head, tail); }

  @Override
  protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) { return new LLSortedChain<>(size, head, tail); }

  /* ************************************************************************ */
  /* Specific member functions of LLSortedChain                               */
  /* ************************************************************************ */

  protected LLNode<Data> PredFind(Data dat){

    if(dat == null) return null;

    LLNode<Data> candidatePred = null;
    long left = 0;
    long right = Size().ToLong() - 1;

    while(left <= right){

      long middle = (left + right) / 2;
   
      MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();
      for(long i = 0; i < middle; i++){

        f_iter.Next();

      }

      LLNode<Data> nodo = f_iter.GetCurrent().Get();

      if(nodo.Get().compareTo(dat) < 0) { candidatePred = nodo ; left = middle + 1; }

      else { right = middle - 1; }
      
    }

    return (candidatePred == null) ? null : candidatePred;

  }

  protected LLNode<Data> PredPredFind(Data dat){
    
    long left = 0;
    long right = Size().ToLong() - 1;
    LLNode<Data> candidatePred = null;

    while (left <= right) {
      
      long middle = (left + right) / 2;

      MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();
      for (long i = 0; i < middle; i++) {
        
        f_iter.Next();
      }

      LLNode<Data> currentNode = f_iter.GetCurrent().Get();
      LLNode<Data> nextNode = (currentNode.GetNext() != null) ? currentNode.GetNext().Get() : null;
      if (nextNode != null && nextNode.Get().compareTo(dat) >= 0) {
        right = middle - 1;
      } else {
        candidatePred = currentNode;
        left = middle + 1;
      }
    }

  return candidatePred;

  }

  protected LLNode<Data> PredSuccFind(Data dat){
    
    long left = 0;
    long right = Size().ToLong() - 1;
    LLNode<Data> candidatePred = null;

    while (left <= right) {
      
      long middle = (left + right) / 2;

      MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();
      for (long i = 0; i < middle; i++) {
        
        f_iter.Next();
      }

      LLNode<Data> currentNode = f_iter.GetCurrent().Get();
      LLNode<Data> nextNode = (currentNode.GetNext() != null) ? currentNode.GetNext().Get() : null;
      if (nextNode != null && nextNode.Get().compareTo(dat) <= 0) {
        left = middle + 1;
      } else {
        candidatePred = currentNode;
        right = middle - 1;
      }
    }

    return candidatePred;

  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @SuppressWarnings("null")
  @Override
  public boolean Insert(Data dat) {

    LLNode<Data> pred = PredFind(dat);
    LLNode<Data> newNode = new LLNode<>(dat);

    if(IsEmpty()){ headref.Set(newNode); tailref.Set(newNode); }
    else if(pred == null){ 

      LLNode<Data> oldNextNode = headref.Get();
      headref.Set(newNode);
      newNode.SetNext(oldNextNode);

    } else if (pred == tailref.Get()) { 

      tailref.Get().SetNext(newNode);
      tailref.Set(newNode);

    } else { 

      LLNode<Data> oldNextNode = pred.GetNext().Get();
      pred.SetNext(newNode);
      newNode.SetNext(oldNextNode);

     }

    size.Increment();

    return true;

  }

  /* ************************************************************************ */
  /* Override specific member functions from RemovableContainer               */
  /* ************************************************************************ */

  public boolean Remove(Data dat){

    if (dat == null || IsEmpty()) return false;
    LLNode<Data> pred = PredFind(dat);

    if(pred == null){

      LLNode<Data> nodeToRemove = headref.Get();

      if(!nodeToRemove.Get().equals(dat)) return false;

      LLNode<Data> newNext = nodeToRemove.GetNext().Get();
      headref.Set(newNext);

      if (newNext == null) {
        tailref.Set(null);
      }

    } 

    else if(pred.GetNext().Get() == tailref.Get()){

      LLNode<Data> nodeToRemove = tailref.Get();

      if(!nodeToRemove.Get().equals(dat)) return false;

      pred.SetNext(null);
      tailref.Set(pred);

    } else {

      LLNode<Data> nodeToRemove = pred.GetNext().Get();

      if(!nodeToRemove.Get().equals(dat)) return false;

      LLNode<Data> newNext = nodeToRemove.GetNext().Get();
      pred.SetNext(newNext);

    }

    size.Decrement();
    return true;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Sequence                         */
  /* ************************************************************************ */

  public Natural Search(Data dat) {

    if (dat == null) return null;

    long left = 0;
    long right = size.ToLong() - 1;
    
    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

    while (left <= right) {
        
        f_iter.Reset(); 
        
        long middle = (left + right) / 2;

        for (long i = 0; i < middle; i++) {
            f_iter.Next();
        }

        LLNode<Data> nodo = f_iter.GetCurrent().Get();

        if (nodo.Get().compareTo(dat) == 0) {
            return Natural.Of(middle); 
        }
        
        else if (nodo.Get().compareTo(dat) > 0) {
            right = middle - 1;
        } 

        else {
            left = middle + 1;
        }
    }

    return null;
  }

  /* ************************************************************************ */
  /* Override specific member functions from SortedChain                      */
  /* ************************************************************************ */

  public Natural SearchPredecessor(Data dat){

    if(dat == null) return null;

    long pred = -1;
    long left = 0;
    long right = Size().ToLong() - 1;
    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

    while(left <= right){

      long middle = (left + right) / 2;
   
      for(long i = 0; i < middle; i++){

        f_iter.Next();

      }

      LLNode<Data> nodo = f_iter.GetCurrent().Get();

      if(nodo.Get().compareTo(dat) < 0) { pred = middle; left = middle + 1; f_iter.Reset(); }

      else { right = middle - 1; f_iter.Reset(); }
      
    }

    return (pred > -1) ? Natural.Of(pred) : null;

  }

  public Natural SearchSuccessor(Data dat){

    if(dat == null) return null;

    long succ = -1;
    long left = 0;
    long right = Size().ToLong() - 1;
    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();

    while(left <= right){

      long middle = (left + right) / 2;
   
      for(long i = 0; i < middle; i++){

        f_iter.Next();

      }

      LLNode<Data> nodo = f_iter.GetCurrent().Get();

      if(nodo.Get().compareTo(dat) > 0) { succ = middle; right = middle - 1; f_iter.Reset(); }

      else { left = middle + 1; f_iter.Reset(); }
      
    }

    return (succ > -1) ? Natural.Of(succ) : null;

  }

  /* ************************************************************************ */
  /* Override specific member functions from OrderedSet                       */
  /* ************************************************************************ */

  public Data Successor(Data dat){

    if (dat == null) return null;

    long n = size.ToLong();
    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();
    Data candidateSucc = null;

    while (n > 0) {
        long middle = n / 2;
 
        for (long i = 0; i < middle; i++) {
            f_iter.Next();
        }

        LLNode<Data> currentNode = f_iter.GetCurrent().Get();

        if (currentNode.Get().compareTo(dat) > 0) {
            candidateSucc = currentNode.Get(); 
            n = middle;    
            f_iter.Reset();
        } else {
            n = n - middle - 1; 
            f_iter.Next();     
        }

    }

    return candidateSucc;

  }

  public Data Predecessor(Data dat){

    if (dat == null) return null;

    long n = size.ToLong();
    MutableForwardIterator<Box<LLNode<Data>>> f_iter = FRefIterator();
    Data candidatePred = null;

    while (n > 0) {
        long middle = n / 2;
 
        for (long i = 0; i < middle; i++) {
            f_iter.Next();
        }

        LLNode<Data> currentNode = f_iter.GetCurrent().Get();

        if (currentNode.Get().compareTo(dat) < 0) {
            candidatePred = currentNode.Get(); 
            n = n - middle - 1;    
            f_iter.Next();
        } else {
            n = middle; 
            f_iter.Reset();     
        }

    }

    return candidatePred;

  }

  public void RemovePredecessor(Data dat){ 
    
    if (dat == null) throw new IllegalStateException("dat cannot be null");
    
    Remove(Predecessor(dat)); 
  }

  public void RemoveSuccessor(Data dat){ 
    
    if (dat == null) throw new IllegalStateException("dat cannot be null");
    
    Remove(Successor(dat)); 

  }

  public Data PredecessorNRemove(Data dat){

    Data oldData = Predecessor(dat);

    Remove(oldData);

    return oldData;

  }

  public Data SuccessorNRemove(Data dat){

    Data oldData = Successor(dat);

    Remove(oldData);

    return oldData;

  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  @Override
  public boolean InsertIfAbsent(Data dat){

    if(dat == null) return false;

    if(IsEmpty()){ Insert(dat); return true; }

    LLNode<Data> pred = PredFind(dat);

    if(pred == null){

      if(headref.Get().Get().equals(dat)) return false;

    } else if( pred.GetNext().Get() != null && pred.GetNext().Get().Get().equals(dat)) return false; 
    
    Insert(dat); return true;

  }
    
  @Override
  public void RemoveOccurrences(Data dat) {
    if (dat == null || IsEmpty()) return;

    LLNode<Data> pred = PredFind(dat);
    LLNode<Data> start = null;

    if (pred == null) {
      start = headref.Get();
    } else {
      start = pred.GetNext().Get();
    }

    while (start != null && start.Get().equals(dat)) {  
      LLNode<Data> newNext = start.GetNext().Get();

      if (pred == null) {
        headref.Set(newNext);    
        if (newNext == null) {
          tailref.Set(null);
          }
      } else {
        pred.SetNext(newNext); 
        if (start == tailref.Get()) {
            tailref.Set(pred);
          }
      }

      start = newNext;
      size.Decrement();

    }

  }


}
