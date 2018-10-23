package mx.edu.uacm;

public class Individuo implements Comparable<Individuo>{
	private int[] cromosoma;
	private double fitness=-1;
	public Individuo(int[] cromosoma) {
		this.cromosoma=cromosoma;
	}
	public Individuo(int tamCromsoma) {
		this.cromosoma= new int[tamCromsoma];
		cromosoma[0]=(int)(Math.random()*tamCromsoma);
		for (int i = 1; i < cromosoma.length; i++) {
			cromosoma[i]=(int)(Math.random()*tamCromsoma);
				for (int j = 0; j < i; j++) {
					if (cromosoma[i]==cromosoma[j]) {
						i--;
					}
				}
		}
		//codigo para crear un nuevo cromosoma
	}
	public void setGen(int i,int gen) {
		cromosoma[i]=gen;
	}
	public int getGen(int i) {
		return cromosoma[i];
	}
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	public int getTamanio(){
		return cromosoma.length; 
	}

	public int[] getCromosoma() {
		return this.cromosoma;
	}
	@Override
	public String toString() {
		String cadena="";
		for(int i=0; i<cromosoma.length; i++) {
			cadena=cadena+cromosoma[i];
		}
		return cadena+" fitnes: "+fitness;
	}
	
	@Override
	public int compareTo(Individuo o) {
		//que criterio de comparacion se va a poner????????
		if(this.getFitness()<o.getFitness())
			return -1;
		else if (this.getFitness()>o.getFitness())
			return 1;
		return 0;
	}
}
