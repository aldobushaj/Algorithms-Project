
public interface Queue<T> {

	public void insert(T elem); // inserisce l'elemento elem passato come parametro
	public T deleteMin();// estrae e rimuove il minimo elemento
	public void updatePosition(int position, T newData); //aggiorna la posizione dell'elemento newData.
	public void updateFirstSameType(T data); //inserisce data sostituendolo al primo elemento nell'heap con lo stesso tipo.
	public T getData(int position);  //ritorna l'elemento alla posizione specificata
	public int getSize(); //metodo get per il size

}