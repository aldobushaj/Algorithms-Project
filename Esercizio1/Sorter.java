package esercizio1;

public interface Sorter<T> {

	public int size();
	public T get(int i);
	public void add(T i);
	public void insertionSort(Sort<T> array);
	public void mergeSort(Sort<T> array);

}
