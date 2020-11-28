package esercizio3;

public class Data<T> {

	private T label;
	private float priority;
	/**
     * costruttore
     * @param valore
     * @param prioritò
     */
	public Data(T label, float priority){ //assegna un etichetta al tipo generico e una priorità
		this.label = label;
		this.priority = priority;
	}
	
	/**
	* restituisce la priorità dell'oggetto che invoca il metodo
     * @return 
     */
	public double getPriority(){
		return this.priority;
	}

	/**
     * Restituisce l'etichetta dell'oggetto che invoca il metodo. 
     * @return 
     */
	public T getLabel(){
		return this.label;
	}
	
	/**
     * assegna un valore all'etichetta
     * @param valore
     */
	public void setLabel(T label){
		this.label = label;
	}
	
	/**
     * assegna un valore alla priorità
     * @param valore
     */
	public void setPriority(float priority){
		this.priority = priority;
	}
	
	/*
	 *stampa il valore dell'etichetta e la sua priorità
	 * 
	 */
	@Override //si esegue un override del metodo di default toString()
	public String toString(){
		return "(" + label + "; " + priority + ")";
	}
	
	@Override //si esegue un override del metodo di default equals()
	public boolean equals(Object obj){
		if(this == null || obj == null) return false;
		if(obj.getClass() != this.getClass()) return false;
		
		/*confronta l'etichetta del tipo generico che invoca il metodo con quello del tipo genrico passato come parametro
		 *e ritorna true se i due sono uguali
		 */
		Data<?> data = (Data<?>) obj;
		if(data.getLabel().equals(this.label)) return true;
		return false;
	}

}
