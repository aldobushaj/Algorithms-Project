package graph;

//Classe per la stampa a video delle eccezioni lanciate dalla classe Graph
public class GraphException extends Exception
{
  private static final long serialVersionUID = 1L;
  public GraphException(String message){
    super(message);
  }
}