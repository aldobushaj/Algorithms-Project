
/**
 * Classe timer per calcolare il tempo impiegato da una funzione 
 */
public class Timer
{ 
  private static long startTime = 0;
  private static long endTime = 0;
  
  public static void startTimer()
  { //stampa il tempo di inizio 
    startTime = System.currentTimeMillis();
  }

  public static void endTimer()
  {//stampa il tempo di fine 
    endTime = System.currentTimeMillis();
  }

  public static long getExecTime()
  { //calcola il tempo impiegato 
    return endTime - startTime;
  }

  public static void showTimer()
  {//stampa il tempo impiegato
    float time = (endTime - startTime)/1000;
    System.out.println("\nTotal execution time: " + time + " seconds" );
  }     
}