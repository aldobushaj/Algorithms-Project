

import java.util.Scanner;
//import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		 
		ComparatorDataString compDataStr = new ComparatorDataString();
	
		Queue<Data<String>> queue = new MinHeap<Data<String>>(compDataStr);

		Scanner scan = new Scanner(System.in);
		//scan.useLocale(Locale.US);
		 
		boolean exit = false; // condizione di uscita dal programma
		int answer; // variabile contenente la risposta dell'utente
		String inserToken = ""; // Stringa in cui memorizzare il dato dell'utente
		float priority = 0.0f; // variabile contenente la priorità assegnata all'oggetto 
		
		//finchè exit è uguale a false il while viene reiterato
		while(!exit){		
	        try{
				// Nel blocco try memorizzo nella variabile answer il numero digitato dall'utente
	        	System.out.print("\n1. Inserire un elemento;\n2. Estarre il minimo;\n3. Uscire;\n");
	        	answer = scan.nextInt();
	        	switch(answer){
					case 1:
						// Nel caso in cui abbia inserito il numero 1 si chiede di inserire il token che 
						//sarà memorizzato nella variabile inserToken
		        		System.out.print("\nInserire  inserToken: ");
				        inserToken = scan.next();
						System.out.print("Inserire Priorità: ");
						// chiediamo di inserire anche la priorità, che dovrà essere di tipo float.
				        try{
				        	priority = scan.nextFloat();
					        Data<String> dataInsert = new Data<String>(inserToken, priority);
							queue.insert(dataInsert);
						// se la priorità inserita non è di tipo float gestisco l'eccezione con il blocco catch , avvertendo l'utente 
						//con una stampa a video dell'errore
				        } catch(java.util.InputMismatchException e) {
				        	System.out.print("\nAttenzione: solo tipo Float per la priorità!\n");
				        	scan.next();
				        }
					break;
					case 2:
					// Nel caso in cui abbia inserito il numero 2 estraiamo e rimuoviamo il minimo. 					
		        		Data<String> removeData = queue.extractMin();
		        		if(removeData != null)
		        			System.out.print("\n" + removeData + " rimosso dalla coda\n");
					break;
					// Nel caso in cui abbia inserito il numero 3 dobbiamo uscire dal while e quindi settiamo a true la variabile exit.
		        	case 3:
		        		exit = true;
		        	break;
					default:
					// Altrimenti avvisiamo l'utente di scegliere esclusivamente i numeri 1 ,2 e 3 e usciamo dal while.
		        		System.out.println("Scegliere 1, 2 o 3");
		        		exit = true;
	        	}
	        } catch(java.util.InputMismatchException e){
				// Con il blocco catch se viene rilevato ogni carattere che non sia di tipo alfanumerico (es £$%&  ecc...)
				// avviso l'utente e esco dal while.
	        	
	        	System.out.print("\nSolo lettere e numeri \n");
	        	exit = true;
	        }
	        
		}

		scan.close();

		System.out.println("\n Coda creata : " + queue);
	}
}