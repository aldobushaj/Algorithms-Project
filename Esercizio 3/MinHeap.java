

import java.util.Comparator;
import java.util.ArrayList;

public class MinHeap<T> implements Queue<T>{
	//Variabili di istanza
	private ArrayList<T> heap; // heap implementato con arraylist
	private int size; // grandezza dell'heap
	private Comparator<T> comparator; // il comparator che scegliamo di usare
	/**
     * Costruttore
     * @param comparator
     */
	public MinHeap(Comparator<T> comparator){ // Costruttore 
		this.heap = new ArrayList<T>();
		this.size = 0; // il size è ovviamente 0
		this.comparator = comparator;
		this.heap.add(size, null); // il primo elemento è null e size rimane zero.
	}
	/**
     * Ritorna l'elemento alla posizione voluta tramite la funzione get di default degli arrayList
     * @param position 
     * @return element
     */
	public T getData(int position){
		return heap.get(position);
	}
	/**
     * Ritorna la posizione del padre,avendo solo due possibili figli basta dividere per due la posizione del figlio
     * @param position 
     * @return position of parent 
     */
	private int getParent(int position){
		return position/2;
	}
	/**
     * Ritorna la poszione del figlio sinistro
     * @param position
     * @return position of left child
     */
	private int getLeft(int position){
		if((2 * position) <= this.size)
			return 2 * position;
		return position;
	}
	/**
     * Ritorna la poszione del figlio destro
     * @param position
     * @return position of right child
     */
	private int getRight(int position){
		if((2 * position) + 1 <= this.size)
			return (2 * position) + 1;
		return position;
	}
	/**
	* Il seguente metodo ritorna la grandezza dell'heap con cui lo richiamiamo.
	* @return size of heap
    */
	public int getSize(){
		return size;
	}
	/**
	* Con il metodo swap scambiamo(swappiamo) l'elemento alla posizione 
	* indexI con l'elemento alla posizione indexJ(passati come parametri) tramite l ausilio di temp 
	* @param indexI,indexJ
    */
	private void swap(int indexI, int indexJ){
		T temp = heap.get(indexI);
		heap.set(indexI, heap.get(indexJ));
		heap.set(indexJ, temp);
	}
	/**
	* Ritorniamo un booleano che indica se si tratta un heap vuoto con il booleano true e false altrimenti.
	* @return boolean
    */
	public boolean empty(){
		if(size == 0)
			return true;
		return false;
	}
	/**
	* Inseriamo l'elemento passato come parametro nell'opportuna posizione e aumentiamo di 1 il valore di size.
	* @param data
    */
	public void insert(T data){
		size++;
		int lastPos = size;
		heap.add(data);
		while(lastPos > 1 && (comparator.compare(heap.get(lastPos), heap.get(getParent(lastPos))) < 0)){
			swap(lastPos, getParent(lastPos));
			lastPos = getParent(lastPos);
		}
	}
	/**
	* Inserisco alla posizione passata come parametro l'elemento più piccolo ricavato 
	* con varie chiamate ricorsive.
	* @param position
    */
	private void heapify(int position){
		int minPos = min(position, getLeft(position), getRight(position));
		if(minPos != position){
			swap(position, minPos);
			heapify(minPos);
		}
	}
	/**
	* Ritorno il il minore tra gli elementi x, y e z passati come parametri.
	* @param x,y,z
	* @return minimum value between x, y, z
    */
	private int min(int x, int y, int z){
		int min = x;
		if(comparator.compare(heap.get(min), heap.get(y)) > 0) min = y;
		if(comparator.compare(heap.get(min), heap.get(z)) > 0) min = z;
		return min;
	}
	/**
	* Elimino l'elemento più piccolo e diminuisco di uno il valore di size se abbiamo almeno un elemento, altrimenti ritorniamo null.
	* @return the minimum element if exist, else null
    */
	public T deleteMin(){
		if(!empty()){
			T data = heap.get(1);
			swap(1, this.size);
			heap.remove(this.size);
			this.size--;
			if(this.size > 0) heapify(1);
			return data;
		}
		return null;
	}
	/**
	* Sovrascrivo alla posizione "position" l'elemento "newData" (passati entrambi come parametri) 
	* e riordina l'heap opportunamente.
	* @param position,newData
	*/
	public void updatePosition(int position, T newData){
		if(position <= size && position > 0){ //  se i parametri rispettano i limiti dell'heap.
			heap.set(position, newData); // imposto il nuovo dato alla posizione indicata
			while(position > 1 && (comparator.compare(heap.get(position), heap.get(getParent(position))) < 0)){
				swap(position, getParent(position));// se la nuova priorità è maggiore di quella del parent effettuo lo swap
				position = getParent(position);
			}
			heapify(position);  // riordino l'heap
		}
	}
	/**
	* Confronto il tipo dell'oggetto data (passato come parametro) con il tipo dell'oggetto alla posizione i 
	* sovrascrivendone la prima occorenza 
	* con l'ausilio del metodo updatePosition nel caso siano uguali.
	* @param data
	*/
	public void updateFirstSameType(T data){
		int i = 1;
		while(i <= this.size){
			if(data.equals(heap.get(i))){
				updatePosition(i, data);
				break;
			}
			i++;
		}
	}

	/**
	* Facciamo l'override del metodo toString di java in modo da stampare a video gli elementi dell'heap con cui lo richiamiamo.
	* @return  String
	*/
	@Override
	public String toString(){
		String string = "{";
		for(int i = 1; i <= this.size; i++){
			string += heap.get(i);
			if(i < size) string += ", ";
		}
		return string + "}";
	}
}