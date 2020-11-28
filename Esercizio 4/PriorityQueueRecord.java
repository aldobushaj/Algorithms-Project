
/** 
* Record della classe di PriorityQueues
*/
public class PriorityQueueRecord<K, V> 
{
  private K key;
  private V value;

  public PriorityQueueRecord(K key, V val) 
  {//costruttore del record
    this.key = key;
    this.value = val;
  }
  //metodi vari per ricavare e aggiornare valori
  public K getKey() { return this.key; }
  public V getValue() { return this.value; }
  public void setKey(K key) { this.key = key; }
}