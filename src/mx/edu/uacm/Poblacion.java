package mx.edu.uacm;

import java.util.Arrays;

public class Poblacion {
	private Individuo poblacion[];
	private double fitnessPoblacion;
	public Poblacion(int tamPoblacion) {
		this.poblacion=new Individuo[tamPoblacion];
	}
	public Poblacion(int tamPoblacion,int tamCromosoma) {
		this.poblacion=new Individuo[tamPoblacion];
		for(int i= 0; i<tamCromosoma; i++) {
			poblacion[i]=new Individuo(tamCromosoma);
		}
	}
	public double getFitness(int i) {
		//Falta poner un criterio de comparacion en la clase individuo
		Arrays.sort(this.poblacion);
		return this.poblacion[i].getFitness();
	}
	public Individuo[] getIndividuos() {
		return this.poblacion;
	}
	public double getFitnessPoblacion() {
		return fitnessPoblacion;
	}
	public void setFitnessPoblacion(double fitnessPoblacion) {
		this.fitnessPoblacion = fitnessPoblacion;
	}
	public int size(){
		return this.poblacion.length;
	}
	
}
