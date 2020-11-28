
/**
 * classe che serve a gestire le eccezioni, stampando l'errore riscontrato
 */
public class PriorityQueueException extends Exception{
  private static final long serialVersionUID = 1L;

  public PriorityQueueException(String message){
    super(message);
  }
}