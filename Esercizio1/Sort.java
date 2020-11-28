package esercizio1;

import java.util.Comparator;
import java.util.ArrayList;

public class Sort<T> implements Sorter<T>{

	private ArrayList<T> elements;
	private Comparator<T> comp;
	private ArrayList<T> left;
	private ArrayList<T> right;
	/**
     * costruttore
     * @param comparator
     */
	public Sort(Comparator<T> comp){
		this.elements = new ArrayList<T>();
		this.comp = comp;
	}

	
	/**
     * imposta il valore value
     * @param position
     * @param value
     */
	private void set(int position, T value){
		elements.set(position, value);
	}
	/**
     * Implementa insertion sort
     * @param array
     */
	public void insertionSort(Sort<T> array){
		if(array != null){
			for(int i = 1; i < array.size(); i++){
				int j = i;
				while(j > 0 && (comp.compare(array.get(j - 1), array.get(j))) == 1){
					array.swap(j - 1, j);
					j--;
				}
			}
		}
	}
	/**
     * Implementa merge sort
     * @param array
     * @param low
     * @param high
     */
	private void mergeSort(Sort<T> array, int low, int high) {
		if (low < high && array != null) {
			int middle = low + (high - low) / 2;
		    mergeSort(array, low, middle);
		    mergeSort(array, middle + 1, high);
		    merge(array, low, middle, high);
	    }
	}
	/**
     * merge sort
     * @param array
     */
	public void mergeSort(Sort<T> array){
		if(array != null){
			mergeSort(array, 0, array.size() - 1);
		}
	}
	
	/**
     * Merge destra e sinistra
     * @param array
     * @param low
     * @param middle
     * @param high
     */
	private void merge(Sort<T> array, int low, int middle, int high){
		int lsize = middle - low + 1;
        int rsize = high - middle;

        this.left = new ArrayList<T>();
        this.right = new ArrayList<T>();

        for (int i = 0; i < lsize; ++i){
        	left.add(i, array.get(low + i));
        }

        for (int j = 0; j < rsize; ++j){
        	right.add(j, array.get(middle + j + 1));
        }

        int i = 0, j = 0;

        int k = low;
        while (i < lsize && j < rsize){
        	if(comp.compare(left.get(i), right.get(j)) <= 0){
        		array.set(k, left.get(i));
                i++;
            }
            else{
            	array.set(k, right.get(j));
                j++;
            }
            k++;
        }

        while (i < lsize){
        	array.set(k, left.get(i));
            i++;
            k++;
        }

		while (j < rsize){
			array.set(k, right.get(j));
		    j++;
		    k++;
		}
	}
	/**
     * aggiunge all'array
     * @param i
     */
	public void add(T i){
		elements.add(elements.size(), i);
	}
	/**
     * ritorna il size dell'array
     * @return
     */
	public int size(){
		if(elements == null)
			return 0;
		return elements.size();
	}
	/**
     *  swap i e j
     * @param i
     * @param j
     */
	private void swap(int i, int j){
		T tmp = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, tmp);
	}
	/**
     * ritorna il valore
     * @param position
     * @return
     */
	public T get(int position) {
        return elements.get(position);
	}

	@Override
	public boolean equals(Object o){
	    if (o == null || this == null) return false;

	    Sort<?> obj = (Sort<?>) o;

	    return obj.elements.equals(elements);
	}

	@Override
	public String toString(){
		return elements.toString();
	}
}
