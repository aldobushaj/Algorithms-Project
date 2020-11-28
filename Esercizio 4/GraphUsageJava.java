


import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.ArrayList;


public class GraphUsageJava 
{
  private static final Charset ENCODING = StandardCharsets.UTF_8;
  //Il path del file
  public static final String FILE_PATH = "../italian_dist_graph.csv";
  public static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException, GraphException 
  {  
    //Crea un grafo non orientato e pesato
    Graph<String,Double> graph = new Graph<>(false,true);

    //Legge il file del grafo e aggiunge gli archi
    Path inputFilePath = Paths.get(FILE_PATH);
    System.out.println("Loading data from " + FILE_PATH);
    try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) 
    {
      String line = null;
      while((line = fileInputReader.readLine()) != null) 
      {   
        if (line.trim().length() > 0) //rimuove gli spazi dalla riga appena letta
        {
          //Struttura della linea : vertice; vertice; peso
          String[] lineElements = line.split(",");
          graph.addEdge(lineElements[0], lineElements[1], Double.valueOf(lineElements[2])); 
        }
      }
    } 
    catch(Exception ex)
    {
      new GraphException("Error Reading File! " + ex.getMessage());  
    }
    System.out.println("Data loaded and Graph created.");

    //Menu principale
    int menu_choice = 0;
    do 
    {
      System.out.println("\nMenu : \n");
      System.out.println("1. Get Number of Vertices");
      System.out.println("2. Get Weight");
      System.out.println("3. Get Minimum Forest - Prim's Algorithm");
      System.out.println("0. Exit");

      System.out.print("Choice : ");
      menu_choice = scanner.nextInt();
      switch (menu_choice) {
          case 1:
              System.out.println(graph.getNumVertices());
              break;
          case 2 :
              System.out.println(String.format("%,.2f",graph.getWeight()));
              break;
          case 3 :
              try 
              {
                Timer.startTimer();
                prim(graph);
                Timer.endTimer();
                Timer.showTimer();  
                return;
              } 
              catch (Exception e) 
              {
                new GraphException("Error MST_PRIM function!");
              }
              break;
          case 0 : break;
          default:
              System.out.println("Error choice!");
              break;
      }
    } while (menu_choice != 0);
    scanner.close();
  }

  /**
  * Calcola il minimum spanning tree usando l'algoritmo di Prim
  * @param :  il grafo a cui vogliamo applicare l'algoritmo
  */
  public static void prim(Graph<String, Double> graph) throws PriorityQueueException, GraphException 
  {  
    //Primo vertice
    String radix = graph.getVertexList().get(0);
    final String NIL = null;
    
    //Hashmap di prec e value del vertice
    HashMap<String, Double> keys = new HashMap<>();
    HashMap<String, String> prec = new HashMap<>();
    PriorityQueue<Double, String> queue = new PriorityQueue<>(new GenericComparator());

    //Grafo in cui inserire il minimum spanning tree
    Graph<String, Double> path = new Graph<>(false,true);

    // Impostare i valori di default nella coda e nelle hashmap per ogni vertice
    for (String vertex : graph.getVertexList()) 
    {
      keys.put(vertex, Double.POSITIVE_INFINITY);
      prec.put(vertex, NIL);
      queue.insert((Double)Double.POSITIVE_INFINITY, vertex);
    }

    // Vertice da cui iniziare
    keys.replace(radix, new Double(0));
    //Contatore degli archi
    int numEdge = 0;
    
    while (!queue.isEmpty()) 
    {
      //Estrae il vertice di priorità massima dalla coda di priorità 
      String extractedVertex = queue.extract().getValue();

      //Non contiamo gli alberi non connessi
      if (prec.get(extractedVertex) != NIL) 
      {
        numEdge++;
        path.addEdge(prec.get(extractedVertex), extractedVertex, graph.getDistance(prec.get(extractedVertex), extractedVertex));
      }

      //Prende i vertici adiacenti ad extractedVertex
      ArrayList<String> adjOfU = graph.getAdjacentVertices(extractedVertex);
      for (String v : adjOfU) {
        if (queue.containsValue(v) && keys.get(v) > graph.getDistance(extractedVertex, v)) {
          // Aggiorna key,prec e priority
          keys.replace(v, graph.getDistance(extractedVertex, v));
          prec.replace(v, extractedVertex);
          queue.updatePriority(v, graph.getDistance(extractedVertex, v));
        }
      }
    }
    
    //Stampa i risultati
    System.out.println("\nMST-PRIM Results :\n");
    System.out.println("Vertices: " + path.getNumVertices());
    System.out.println("Edges: " + numEdge);
    System.out.println("Total weight (distance) : " + String.format("%,.2f",path.getWeight()) + " km");
  }


}