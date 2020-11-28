public class EditDistance {

	public int[][] matrix;
	String s1;
	String s2;
	

	/**
	 * Costruttore
	 * 
	 * @param s1
	 * @param s2
	 */
	public EditDistance(String s1, String s2) {
		this.matrix = new int[s1.length() + 1][s2.length() + 1];
		this.s1 = s1.toLowerCase(); // rimuoviamo lettere maiuscole
		this.s2 = s2.toLowerCase();// rimuoviamo lettere maiuscole
		// riempiamo la matrice di -1
		for (int i = 0; i <= s1.length(); i++)
			for (int j = 0; j <= s2.length(); j++)
				matrix[i][j] = -1;
	}

	/**
	 * Ritorna la stringa str senza 
	 * il primo carattere
	 * 
	 * @param str
	 * @return
	 */
	private String remFirst(String str) {
		String remFirst = "";// remFirst è sempre vuota all'inizio
		for (int i = 1; i < str.length(); i++) // partiamo da 1 per ignorare la prima lettera 
			remFirst += str.charAt(i);//poi concateniamo a remFirst str senza la prima lettera 
		return remFirst;
	}

	/**
	 * Ritorna la distanza di edit tra due stringhe s1 ed s2.
	 * Metodo ricorsivo ma non dinamico.
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public int edit_distance_rec(String s1, String s2) {

		if (s1.length() == 0) {
			/*se s1 è vuota allora il numero di operazione necessarie per
			 * trasformare s1 in s2 è pari 
			 * all numero di caratteri di s2
			 */
			return s2.length();
		} else if (s2.length() == 0) {
			/*se s2 è vuota allora il numero di operazione necessarie per
			 * trasformare s1 in s2 è pari 
			 * all numero di caratteri di s1
			 */
			return s1.length();
		
	     	/* Notare che se entrambe le stringhe sono vuote allora l'algoritmo ritorna la lunghezza 
			* della seconda ad aver analizzato, quindi 0
			*/
		} else if (s1.charAt(0) == s2.charAt(0)) {
			/*  Se il primo carattere delle sue stringhe
			 * coincide, allora non dobbiamo far altro che
			 * la chiamata ricorsiva sulla restante parte. 
			 * Cio è assicurato dal metodo remFirst che rimuove
			 * la prima lettera della stringa
			 */
			return edit_distance_rec(remFirst(s1), remFirst(s2));
		} else
			/* Se invece il primo carattere è diverso allora dobbiamo incrementare la distanza
			 * di edit, quindi ritorniamo 1 + la chiamata ricorsiva 
			 * che si avvale di remFirst per gli stessi motivi del caso precedente.
			 * Le  chiamate ricorsive sono per l'iserimento o la rimozione 
			 *  */
			//return 1 + min_Three(edit_distance_rec(s1, remFirst(s2)), edit_distance_rec(remFirst(s1), s2), zeroOp);
			return 1+ Math.min(edit_distance_rec(s1, remFirst(s2)), edit_distance_rec(remFirst(s1), s2));
			//return 1+ Math.min(edit_distance_rec(s1, s2.substring(1)), edit_distance_rec(s1.substring(1), s2));	
	}

	/**
	 * Ritorna la distanza di edit tra due stringhe S1 ed S1.
	 * Metodo Dinamico- ricorsivo che si avvale della tecnica della memoizzazione (top-down)
	 * tramite una matrice i*j dove i e j sono le lunghezze 
	 * rispettivamente di s1 ed s2
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int edit_distance_recdyn(int i, int j) {

		if ((s1.length() - i) == 0) // se s1 'vuota'
			return s2.length() - j;
		if ((s2.length() - j) == 0)// se s2 'vuota'
			return s1.length() - i;
		if (s1.charAt(i) == s2.charAt(j))
			/*Se sono uguali non faccio niente e   richiamo la funzione 
			 * */
			return edit_distance_recdyn(i + 1, j + 1); 
		if (matrix[i][j] == -1) //Rimane solo il caso in cui siano diversi.
			/*Se  sono diversi e non ho  ancora  risolto 
			 * quello specifico sottoproblema vuol dire che matrix[i][j] == -1.
			 * Quindi sfruttando la tecnica della memoizzazione, 
			 * salviamo il valore della chiamata ricorsiva nella matrice, in modo da non dover computare
			 * ancora questo sottoproblema.
			 * */
			
			matrix[i][j] =  Math.min(1 + edit_distance_recdyn(i, j + 1),  1 + edit_distance_recdyn(i + 1, j));
		 /* Se sono diversi e non c'è -1 questo significa che il sottoproblema è 
		 *  già stato risolto, quindi basta ritornare il valore calcolato in precedenza
		 */	
		return matrix[i][j]; 
	}

	
	
}








