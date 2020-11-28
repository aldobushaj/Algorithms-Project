package esercizio1;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	
	public static boolean findSum(Sort<Long> array, long sum){
		//Dichiariamo e inizializziamo le variabili left(primo elemento dell'array) e right(ultimo elemento dell'array)
		int left = 0;
		int right = array.size() - 1;
		//Se la somma del primo e l'ultimo elemento sono uguali ritorno TRUE, altrimenti decremento di uno la dimensione dell'array
		//nella parte sinistra o destra e ripeto il ciclo.
        while(left < right){
           if((array.get(left) + array.get(right)) == sum) return true;
           if((array.get(left) + array.get(right)) >= sum) right--;
           if((array.get(left) + array.get(right)) < sum) left++;
		}
		//Se la somma del primo e dell'ultimo elemento non sono uguali a sum dopo aver scansionato l'intero array ritorno FALSE.
        return false;
	}

	public static void load(String path, Sort<Long> array){
	    BufferedReader br = null;
	    try {
			//Carico il file
	    	br = new BufferedReader(new FileReader(path));
	    	String line = null;
	    	long x;
			System.out.println("\nCaricamento dei dati da " + path);
	  		while((line = br.readLine()) != null){
	  		
	  		    x = Long.parseLong(line);
				//Riempo l'array con i dati caricati dal path indicato
	  		    array.add(x);
	  		}
	  		System.out.println("Fine");
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
	          if (br != null) {
	                try {
						//Chiudo il buffer
	                	br.close();
	                } catch (IOException e) {
	                      e.printStackTrace();
	                }
	          }
	    }
	}

	

	public static void main(String[] args){
		if(args.length < 2){
	        System.out.println("Inserire in arg[0] il path del file di interi e in args[1] il path del  file delle somme");
	        System.exit(0);
	    }
		ComparatorLong comp = new ComparatorLong();
        Sorter<Long> sorter = new Sort<Long>(comp);
		Sort<Long> elements = new Sort<Long>(comp);
		Sort<Long> sums = new Sort<Long>(comp);
		System.out.print("Scegliere \n\n1: Insertion, 2: Merge\n  \n");
		
		Scanner scan = new Scanner(System.in);
		//Inizializzo choise con l'intero scelto dall'utente
		int choise = scan.nextInt();
        scan.close();
		//Inizializzo startSim con il tempo di inizio simulazione.
        long startSim = System.currentTimeMillis();

		load(args[0], elements);
		load(args[1], sums);

		if(choise == 1){
			//Se l'utente ha scelto 1, useremo l'algoritmo insertionSort.
			sorter.insertionSort(elements);
		} else if(choise == 2){
			//Se l'utente ha scelto 2, useremo l'algoritmo mergeSort.
			sorter.mergeSort(elements);
		} else {
			System.out.println("Inserire solo 1 o 2");
			System.exit(0);
		}

        for(int i = 0; i < sums.size(); i++){
			//Cerco i due estremi uguali a sum alla posizione i e stampo a video se l'ho trovato o meno.
        	if(findSum(elements, sums.get(i)))
               	System.out.println("\nSomma " + sums.get(i) + " trovato");
        	else System.out.println("\nNessuna somma " + sums.get(i) + " trovata");
        } 
		//Inizializzo endSim con il tempo di fine della simulazione.
        long endSim = System.currentTimeMillis();
        long timeEndSim = ((endSim - startSim) / 1000);
        System.out.println("\nTempo totale " + timeEndSim + " secondi");
	}

}
