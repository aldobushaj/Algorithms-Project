package esercizio1;
import java.util.Comparator;

public class ComparatorLong implements Comparator<Long>{
	//Comparo due long a e b e ritorno 1 se a > b, -1 se a < b e 0 altrimenti
	public int compare(Long a, Long b){
		if(a > b) return 1;
		if(a < b) return -1;
		return 0;
	}
}