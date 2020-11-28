package esercizio3;
import java.util.Comparator;
/**
* La seguente classe con l'ausilio del metodo compare compara due stringe a e b passate come parametri e 
* ritorna 1 se a è maggiore di b, -1 se a è minore di b e 0 altrimenti.
 */
public class ComparatorDataString implements Comparator<Data<String>>{

	public int compare(Data<String> a, Data<String> b){
		if(a.getPriority() > b.getPriority()) return 1;
		if(a.getPriority() < b.getPriority()) return -1;
		return 0;
	}
}