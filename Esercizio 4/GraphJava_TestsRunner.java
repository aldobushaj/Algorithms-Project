package graph;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

//Classe per la verifica dei test della classe graph
public class GraphJava_TestsRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(GraphTests.class);

    //Inzio test
    System.out.print("\nUnit Tests result: ");

    //Stampa il fallimento nel caso in cui ci siano
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  
    //Stampa a video TRUE se ogni test va a buon fine 
    System.out.println(result.wasSuccessful());
  }
  
}
