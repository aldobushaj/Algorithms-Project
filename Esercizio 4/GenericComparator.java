

import java.util.Comparator;

/**
 * Comparatore per Double, usato per comparare valori generici
 * 
 */
public class GenericComparator implements Comparator<Double>
{
  @Override
  public int compare(Double d1, Double d2) {
    return d2.compareTo(d1);
  }
}