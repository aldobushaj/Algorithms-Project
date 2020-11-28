package graph;

import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

// Classe test su grafi
public class GraphTests {
     
  private Graph<String, Double> graph;
  
  @Before
  //Creo un grafo non orientato ma pesato
  public void createdistanceArray(){
    graph = new Graph<>(false, true);
  }
  
  @Test
  //Verifico che il grafo sia vuoto, ritornando TRUE in caso affermativo FALSE in caso contrario
  public void testIsEmpty_zeroEl(){
    assertTrue(graph.isEmpty());
  }
  
  @Test
  //Verifico che il grafo non sia vuoto, ritornando TRUE in caso affermativo FALSE in caso contrario
  public void testIsEmpty_oneEl() throws Exception{
    graph.addEdge("a", "b", new Double(2));
    assertFalse(graph.isEmpty());
  }
  
  @Test
  //Verifico che il grafo abbia zero vertici
  public void testSize_zeroEl() throws Exception{
    assertEquals(0, graph.getNumVertices());
  }
  
  @Test
  //Verifico che il peso dell'arco creato(peso 2) tra un vertice A e B sia uguale a 2
  public void test_getDistance() throws Exception{
    graph.addEdge("a", "b", new Double(2));
    assertEquals(new Double(2), graph.getDistance("a", "b"));
  }
  
  @Test
  //Creo un grafo con due vertici e verifico che il numero dei vertici sia effettivamente 2
  public void testSize_twoEl() throws Exception{
    graph.addEdge("a", "b", new Double(2));
    assertEquals(2, graph.getNumVertices());
  }
  
  @Test
  //Creo un grafo con tre vertici e verifico che ci siano effettivamente 3 vertici
  public void testAddGet_oneEl() throws Exception{
    graph.addEdge("a", "b", new Double(2));
    graph.addEdge("b", "c", new Double(6));
    assertTrue(3 == graph.getNumVertices());
  } 

  @Test
  //Creo un grafo con tre vertici e verifico che il peso degli archi corrisponda al valore atteso
  public void test_getWeight() throws Exception{
    graph.addEdge("a", "b", new Double(2));
    graph.addEdge("b", "c", new Double(6));
    assertTrue(new Double(8) == graph.getWeight());
  } 

}
