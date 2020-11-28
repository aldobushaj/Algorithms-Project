

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Implementa grafi pesati orientati e non orientati usando liste di adiacenza.
 * @param <V>: vertici del grafo
 * @param <W>: pesi del grafo. Se pesato, W deve essere un Double
 */
public class Graph<V, W extends Double> 
{ 
  private HashMap<V, ArrayList<Arch<V, W>>> adjacencyList = null; //hasmap che usa V come chiave e come valore ha la lista dei vertici adicenti a V
  private ArrayList<V> vertexList = null; // lista di tutti i vertici del grafo
  private boolean oriented = true; //se orientato
  private boolean weighed = true; //se pesato

  /**
   * Costruttore, crea un grafo vuoto
   * @param oriented true se grafo orientato, false altrimenti
   * @param isWeighed true se pesato, false altrimenti
   * @return l'istanza del grafo
   */
  public Graph(boolean oriented, boolean isWeighed) 
  {
    this.adjacencyList = new HashMap<>();
    vertexList = new ArrayList<>();
    this.oriented = oriented;
    this.weighed = isWeighed;
  }

  /**
   * @return lista contenente tutti i vertici del grafo
   */
  public ArrayList<V> getVertexList() 
  { return this.vertexList; }

  /**
   * @return : numero di vertici del grafo
   */
  public int getNumVertices() 
  { return this.vertexList.size(); }

  /**
   * @return : true se il grafo non ha vertici
   */
  public boolean isEmpty()
  { return this.vertexList.isEmpty(); }
  	
  /**
   * Aggiunge il nuovo vertice alla adjacencyList(hash map) con la lista dei suoi vertici adiacenti
   * @param vertex: vertice da aggiungere
   * @param connectedVertices: lista dei vertici adiacenti al vertice dato
   * @throws GraphExceptionse uno dei parametri è null
   */
  private void add(V vertex, ArrayList<Arch<V, W>> connectedVertices) throws GraphException 
  {
    if (vertex == null) {
      throw new GraphException("add: vertex cannot be null.");
    }
    if (connectedVertices == null) {
      throw new GraphException("add: connectedVertices cannot be null.");
    }
  
    //Aggiunge il nuovo vertice alla adjacencyList(hash map) con la sua lista dei suoi vertici adiacenti
    adjacencyList.put(vertex, connectedVertices);
    vertexList.add(vertex);

   
    /* Se è un grafo non orientato, ogni arco deve essere rappresentato
     * due volte: una per l'arco (a,b), l'altra per l'arco (b,a)
     */
    for (int i = 0; i < connectedVertices.size(); i++) {
      Arch<V, W> vert = connectedVertices.get(i); // variabile temporanea
      ArrayList<Arch<V, W>> correspondingConnectedList = adjacencyList.get(vert.getVertex());

      
      /* Le adiacenze del vertice appena aggiunto potrebbero ancora non essere rappresentate,
       * quindi le aggiungiamo
       */
      if (correspondingConnectedList == null) {
        adjacencyList.put(vert.getVertex(), new ArrayList<Arch<V, W>>());
        vertexList.add(vert.getVertex());
        correspondingConnectedList = adjacencyList.get(vert.getVertex());
      }
      
      if (!this.oriented) {
        // Il peso di un arco in un grafo non orientato è uguale in entrabe le direzioni
        W weight = vert.getWeight();
        correspondingConnectedList.add(new Arch<V, W>(vertex, weight));
      }
    }
  }

  /**
   * @return un array list contenente i vertici adiacenti al vertice dato
   */
  public ArrayList<V> getAdjacentVertices(V vertex) 
  {
    ArrayList<V> ret = new ArrayList<V>();
    ArrayList<Arch<V, W>> adjOfVertex = adjacencyList.get(vertex);

    for (int i = 0; i < adjOfVertex.size(); i++) {
      ret.add(adjOfVertex.get(i).getVertex());
    }

    return ret;
  }
  
  /**
   * @return il peso dell'arco (start, end)
   * @throws GraphException se l'arco specificato non esiste
   */
  public W getDistance(V start, V end) throws GraphException 
  {
    ArrayList<Arch<V, W>> adjOfStart = adjacencyList.get(start);

    for (Arch<V, W> edge : adjOfStart) {
      if (edge.getVertex().equals(end)) {
        return edge.getWeight();
      }
    }
    throw new GraphException("getDistance: there is no edge which connects start to end");
  }

  /**
   * calcola il peso totale del grafo usando una Depth First Search
   * @return il peso del grafo
   * @throws GraphException se il grafo non è pesato
   */
  public double getWeight() throws GraphException 
  {
    if (!weighed) 
      throw new GraphException("getWeight: the graph must be weighed to calculate its weight. Every edges must have a weight of type Double");

    if (vertexList.size() == 0)
      return 0;

    double weight = 0;

    //Somma di tutti i pesi del grafo
    for (V vertex : vertexList) {
      ArrayList<Arch<V, W>> adj = adjacencyList.get(vertex);
      for (Arch<V, W> edge : adj) {
        weight += ((Double)edge.getWeight()).doubleValue();
      }
    }
    return oriented ? weight : weight / 2;
  }

  /**
   * Aggiunge un arco orientato al grafo
   * @param source : vertice di partenza
   * @param end : vertice di arrivo
   * @param weight : peso dell'arco
   */
  public void addArc(V source, V end, W weight) throws GraphException 
  {
    if (!this.oriented) // se il grafo non è orientato
      throw new GraphException("addArc: cannot add an arc for an undirected graph.");

    
    if (!adjacencyList.containsKey(source)) // se il nodo source non è contenuto nel grafo
    {
      ArrayList<Arch<V, W>> tempList = new ArrayList<Arch<V, W>>(); //ne creo una nuova
      tempList.add(new Arch<V, W>(end, weight)); // e aggiungo alla lista appena creata l'arco fino ad end
      add(source, tempList); // aggiungo source al grafo  con la sua lista di adiacenza appena creata
      return;
    }
    
    if (!adjacencyList.containsKey(end))  // se il nodo end non è contenuto nel grafo
    {
      ArrayList<Arch<V, W>> tempList = new ArrayList<Arch<V, W>>();
      add(end, tempList); // creo una lista di adiacenza vuota per poter aggiungere il nodo al grafo
    }

    adjacencyList.get(source).add(new Arch<V, W>(end, weight)); // aggiungo l'arco source->end al grafo
  }

  /**
   * Aggiunge un arco non orientato
   * @param vertexOne : vertice di origine
   * @param VertexTwo : vertice di fine
   * @param weight : peso
   */
  public void addEdge(V vertexOne, V vertexTwo, W weight) throws GraphException 
  {
    if (this.oriented) // se il grafo è orientato non posso aggiungere un arco non orientato!
        throw new GraphException("addEdge: cannot add an edge to an oriented graph.");

    //crea la lista di adiacenza del vertice se questo non esiste
    if (!adjacencyList.containsKey(vertexOne)) 
    {
      ArrayList<Arch<V, W>> tempList = new ArrayList<Arch<V, W>>();
      tempList.add(new Arch<V, W>(vertexTwo, weight));
      add(vertexOne, tempList); // aggiungo il vertice al grafo con la lista appena creata
      return;
    }

    if (!adjacencyList.containsKey(vertexTwo)) //se non contiene il secondo vertice lo aggiungo
    {
      ArrayList<Arch<V, W>> tempList = new ArrayList<Arch<V, W>>(); // creo una lista temporanea
      tempList.add(new Arch<V, W>(vertexOne, weight));// aggiungo l'arco alla lista appena creata
      add(vertexTwo, tempList); // aggiungo il secondo vertice al grafo
      return;
    }
    // Se arrivo qui è perchè entrambi i vertici sono presenti nel grafo, quindi devo solo aggiungere l'arco
    //Aggiunge (a,b) e (b,a) perchè è un grafo non orientato
    adjacencyList.get(vertexOne).add(new Arch<V, W>(vertexTwo, weight));
    adjacencyList.get(vertexTwo).add(new Arch<V, W>(vertexOne, weight));
    return;
  }
}