package mx.edu.uacm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Poblacion {
	private ArrayList<Individuo> poblacion;
	private double fitnessPoblacion;
	public Poblacion() {
		this.poblacion=new ArrayList<Individuo>();
	}
	public Poblacion(int tamPoblacion,int tamCromosoma) {
		this.poblacion=new ArrayList<Individuo>();
		for(int i= 0; i<tamPoblacion; i++) {
			poblacion.add(new Individuo(tamCromosoma));
		}
	}
	//Para obtener un individuo mas apto debe de ordenar 
	//ascendentemente el arreglo para ubicar en la primera casilla el que tiene menor costo.
	public Individuo obtenerIndividuoMasApto(int i) {
		//Falta poner un criterio de comparacion en la clase individuo
		//Arrays.sort(this.poblacion);
		Collections.sort(poblacion);
		return poblacion.get(0);
	}
	public ArrayList<Individuo> getIndividuos() {
		return this.poblacion;
	}
	public double getFitnessPoblacion() {
		return fitnessPoblacion;
	}
	public void setFitnessPoblacion(double fitnessPoblacion) {
		this.fitnessPoblacion = fitnessPoblacion;
	}
	public int size(){
		return this.poblacion.size();
	} 
	public Individuo getIndividuo(int i) {
		return poblacion.get(i);
	}
	public void setIndividuo(Individuo individuo) {
		poblacion.add(individuo);
	}
}
