


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	
	// Carica il contenuto del file da analizzare, in questo caso correctme.txt, in speech
	public static void loadText(String path, ArrayList<String> speech) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			System.out.println("\n loadText carica i dati da " + path);
			System.out.println("FIne del caricamento");
			String word = br.readLine();//mettiamo in word una riga
			String[] temp = word.split("\\, |\\. |\\: |\\.|\\s"); // divide in parole word, caricandole nell'array di stringhe temp
			for (String s : temp) // per ognuno dei pezzi in cui abbiamo diviso word otteniamo una parola che mettiamo in s
				speech.add(s);// che poi  aggiungiamo ad speech
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// Ricerca del minimo in un array
		public static int findMin(ArrayList<Integer> array) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) < min) {
					min = array.get(i);
				}
			}
			return min;
		}
		
		
	//Carica il contenuto del dictionary.txt in un array, per potervi accedere durante i confronti
	public static void loadArray(String path, ArrayList<String> array) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			System.out.println("\n loadArray carica i dati da " + path);
			while ((line = br.readLine()) != null) {
				array.add(line.toLowerCase());
			}
			System.out.println("Dati caricati");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	

	public static void main(String args[]) {
		// controlli per la scelta e inserimento parametri
		if (args.length < 2) {
			System.out.println(
					"Inserire in args[0] il path del file dizionario , e in args[1] il path del file da correggere");
			System.exit(0);
		}
		System.out.print("Scegliere:\n\n1: Ricorsivo, 2: Dinamico\n");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		scan.close();
		if (choice != 1 && choice != 2) {
			System.out.println("Ammesso solo  1 o 2");
			System.exit(1);
		}
		// scelt fatta e path correttamente inseriti
		long startSim = System.currentTimeMillis(); // avvio tempo programma
		ArrayList<String> speech = new ArrayList<String>();
		ArrayList<String> dictionary = new ArrayList<String>();
		

		loadArray(args[0], dictionary); // carica dictionary 
		loadText(args[1], speech); // carica speech
		
		
		
		int  valDist = Integer.MAX_VALUE; // il massimo valore che un int possa avere

		for (String s1 : speech) //Per quante parole ci sono nella frase 
			{

			
			System.out.print("\n" + "'" + s1 + "' trovato:");
			
			int min = Integer.MAX_VALUE; // settato al massimo per  avere true al primo confronto min > x
			ArrayList<Integer> distance = new ArrayList<Integer>();
			long start = System.currentTimeMillis(); //avvio ricerca
			
			for (int tok = 0; tok < dictionary.size(); tok++) {
				String s2 = dictionary.get(tok); 
				//prendiamo il tok-esimo elemento di dictionary
				EditDistance ed = new EditDistance(s1, s2);
				
				//in base alla scelta si esegue l'algoritmo corrispondente
				if (choice == 1) {
					valDist = ed.edit_distance_rec(s1.toLowerCase(), s2.toLowerCase());
				} else {
					valDist = ed.edit_distance_recdyn(0, 0);
				}
				distance.add(valDist);// riempiamo l'array distance con i valori di valDist ad ogni ripetizione del ciclo
				
			}
			   min=findMin(distance); // trovo il min nell'array distance 
			if (min > 0) // se min >0 ==> la parola è sbagliata o non esiste nel dictionary
				{
				int i = findMin(distance);// i è la distanza minima di quella parola da un'altra del dizionario
				
				System.out.println("\n Parola sbagliata!\n Parole simili" + min + ": ");
				
				for (int tok = 0; tok < distance.size(); tok++) {
					
					
					if (distance.get(tok) == i){
					/* sfruttiamo il fatto che distance.size = dictionary.size, quindi abbiamo una
					 'mapppatura' tra la parola e ogni vocabolo del dizionario. Di questi ci interessano quelli più simili 
					  ossia con minima editDistance
					   */	
					
				    // stampa la parola in posizione tok-esima in dictionary
					System.out.print("\"" + dictionary.get(tok) + "\"; "); 
					}
				}
				System.out.print("\n Parola trovata in  " + (System.currentTimeMillis() - start) + " ms\n");
			} else
				System.out.println(" Corretta!");
				
		}
		long endSim = System.currentTimeMillis();
		long timeEndSim = ((endSim - startSim) / 1000);
		System.out.println("\n Durata Totale: " + timeEndSim + " sec");
	}
}

