package mx.edu.uacm;

import java.util.Comparator;

public class Individuo implements Comparator{
	private int[] cromosoma;
	private double fitness=-1;
	public Individuo(int[] cromosoma) {
		this.cromosoma=cromosoma;
	}
	public Individuo(int tamCromsoma) {
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

	@Override
	public String toString() {
		String cadena="";
		for(int gen: cromosoma) {
			cadena=cadena+cromosoma[gen];
		}
		return cadena+" fitnes: "+fitness;
	}
	@Override
	public int compare(Object o1, Object o2) {
		Individuo ind1=(Individuo)o1;
		Individuo ind2=(Individuo)o2;
		
		//que criterio de comparacion se va a poner????????
		
		return 0;
	}
}
