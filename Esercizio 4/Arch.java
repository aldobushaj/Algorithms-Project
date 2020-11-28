public class Arch<V,W extends Double>
{
	//gli archi hanno un vertice di arrivo e un peso
  private V vertex;
  private W weight;

  public Arch(V vert, W weight)
  {  //costruttore
      this.vertex = vert;
      this.weight = weight;
  }

  /**
   * @return   il vertice
   */
  public V getVertex() {
      return vertex;
  }

  /**
   * @return il peso
   */
  public W getWeight()
  {
      return this.weight;
  }

  @Override
  /**
   * metodo toString
   * */
  public String toString() {
      return "( "+ vertex + ", " + weight + " )";
  }
}