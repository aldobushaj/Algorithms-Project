
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/**
 * Questa classe è un implementazione di una coda con priorità con un heap massimo 
 * @param <K>: tipo generico per indicare le chiavi degli elementi nella coda 
 * @param <V>: tipo generico che indica gli elementi della coda
 */
public class PriorityQueue<K, V> 
{ 
  protected ArrayList<PriorityQueueRecord<K, V>> queue = null;//creo un ArrayList di Record con chiave K ed elemnti V
  protected Comparator<? super K> comparator = null;//inizializzo il comparatore con elementi generici
  protected HashMap<V, Integer> ht = null;//creo la mia HashMap con chiave V ed element di tipo Integer

  /**
   * Creo una coda prioritaria vuota 
   * @param comparator: il comparatore che confronterà la priorità degli elementi nella coda
   */
  public PriorityQueue(Comparator<? super K> comparator) 
  {//costruttore
    this.queue = new ArrayList<>();
    this.comparator = comparator;
    ht = new HashMap<>();
  }  

  /**
   * @param index:l'indice dell'elemento di cui vogliamo ricavare il padre
   * @return l'indice del padre dell'elemento passato come parametro
   */
  protected int getParent(int index) throws PriorityQueueException
  {
    if (index != 0)
      return (index - 1) / 2;
    
    throw new PriorityQueueException("Error getting parent of element "+ index);
  }

  /**
   * @param index:l'indice dell'elemento di cui vogliamo ricavare il figlio sinistro
   * @return indice dell'elemento sinistro
   */
  protected int getLeft(int index) 
  {
    if (index * 2 + 1 < this.size()) 
      return index * 2 + 1;
    else 
      return index;
  }

  /**
   * @param index:l'indice dell'elemento di cui vogliamo ricavare il figlio destro 
   * @return indice dell'elemento destro
   */
  protected int getRight(int index) 
  {
    if ((index * 2) + 2 < this.size())
      return (index * 2) + 2;
    else
      return index;
  }

  /**
   * scambia due elementi nella coda 
   * @param i : indice del primo elemento
   * @param j : indice del secondo elemento
   */
  protected void swap(int i, int j) 
  {
    PriorityQueueRecord<K, V> tmp = queue.get(i);
    queue.set(i, queue.get(j));
    queue.set(j, tmp);

    // aggiorna il valore nella tabella Hash
    ht.remove(queue.get(i).getValue());
    ht.remove(queue.get(j).getValue());
    ht.put(queue.get(i).getValue(), i);
    ht.put(queue.get(j).getValue(), j);
  }

  /**
   * Questa funzione scambia gli elementi, mantenendo le regole dello heap
   * @return l'elemento alla posizione i
   */
  protected void heapify(int i) throws PriorityQueueException 
  {
    if(i < 0 || i >= (this.queue).size()) //i indice valido 
      throw new PriorityQueueException("Index " + i + "is out of bounds");

    int idxMax = i;

    // Prende l'indice del massimo tra{queue[getLeft[i]], queue[getRight[i]], queue[i]},quindi tra padre,figlio destro e figlio sinistro.
    if (getLeft(i) < size() && comparator.compare(queue.get(getLeft(i)).getKey(), queue.get(idxMax).getKey()) > 0)
      idxMax = getLeft(i);
    if (getRight(i) < size() && comparator.compare(queue.get(getRight(i)).getKey(), queue.get(idxMax).getKey()) > 0)
      idxMax = getRight(i);
    
    if (idxMax != i) 
    {
      swap(i, idxMax);
      heapify(idxMax);
    }
  }

  /**
   * Inserisce l'elemento che attualmente si trova alla posizione i, alla giusta posizione risalendolo nello heap
   * @param i : indice dove inserire l'elemento
   */
  protected void putToRightPosition(int i) throws PriorityQueueException 
  {
    if (i < 0 || i >= (this.queue).size())//i indice valido 
      throw new PriorityQueueException("Index " + i + "is out of bounds");

    while (i > 0 && this.comparator.compare(queue.get(getParent(i)).getKey(), queue.get(i).getKey()) < 0) 
    {
      swap(i, getParent(i));
      i = getParent(i);
    }
  }
  
  /**
   * @return ritorna true se la coda è vuota
   */
  public boolean isEmpty()
  { return (this.queue).isEmpty(); }
 
  /**
   * @return: numero di elementi nella coda 
   */
  public int size()
  { return (this.queue).size(); }
  	
  /**
   * aggiunge un nuovo elemento nella coda 
   * @param priority: la priorità dell'elemento da inserire
   * @param value: il valore dell'elemento da inserire
   */
  public void insert(K priority, V value) throws PriorityQueueException 
  {
	  if (priority == null) 
		  throw new PriorityQueueException("insert: priority cannot be null");
	  
	  if (value == null) 
		  throw new PriorityQueueException("insert: value cannot be null");
  
	  // allocazione di un nuovo elemento
    PriorityQueueRecord<K, V> element = new PriorityQueueRecord<K, V>(priority, value);
	  
	  //si controlla se l'elemento esiste già
	  if (ht.containsKey(element.getValue()))
      throw new PriorityQueueException("insert: the element to be added is already present in the queue");
  
	  // aggiungi l'elemento nella coda 
    queue.add(element);
    ht.put(element.getValue(), size() - 1);

    // Inserisce l'elemento in fondo alla coda alla giusta posizione risalendo lo heap 
      putToRightPosition(size() - 1);
  }

  /**
   * @return l'elemento con la piu alta priorita nella coda 
   */
  public PriorityQueueRecord<K, V> extract() throws PriorityQueueException 
  {
    if (this.isEmpty())
      throw new PriorityQueueException("Priority queue is empty.");

    //l'elemento con la piu alta priorità si trova all inizio della coda
    PriorityQueueRecord<K, V> maximum = queue.get(0);
    PriorityQueueRecord<K, V> last = queue.get(size() - 1); 

    // Rimpiazzo la prima posizione con l'ultima e successivamente aggiorno i valori della HashTable
    ht.remove(maximum.getValue());
    ht.replace(last.getValue(), 0);

    // aggiorno la coda di priorità
    queue.set(0, last);
    queue.remove(size() - 1);

    // riordino la coda secondo le regole dello heap con heapify
    if (size() > 0)
      heapify(0);

    return maximum;
  }

 /**
  * aggiorna la priorità dell'elemento nella coda con il valore passato come parametro
  * @param value: il valore dell'elemento di cui si vuole modificare la priorità
  * @param newPriority: la nuova priorità
  */
  public void updatePriority(V value, K newPriority) throws PriorityQueueException 
  {
    if (ht.get(value) == null)
      throw new PriorityQueueException("The element with the specified value is not present in the queue");

    // ricavo l'indice dell'elemento che nella HashTable ha come chiave un valore uguale a value(preso dall'ArrayList)
    int idx = ht.get(value).intValue();

    K oldPriority = queue.get(idx).getKey();

    // ricavo la chiave col nuovo valore
    queue.get(idx).setKey(newPriority);

    // risalgo lungo la coda di priorità fino alla giusta posizione
    if (comparator.compare(oldPriority, newPriority) < 0) 
    {
      putToRightPosition(idx);
    }
    else 
    {
      // scendo lungo la coda fino alla giusta posizione 
      heapify(idx);
    }
  }

  /**
   * @param value :l'elemento da trovare nella coda
   * @return : true se la coda contiene gia l'elemento
   */
  public boolean containsValue(V value) 
  { return ht.get(value) != null; }
}