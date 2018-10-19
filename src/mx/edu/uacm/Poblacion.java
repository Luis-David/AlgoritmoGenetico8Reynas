package mx.edu.uacm;

import java.util.Arrays;
import java.util.Random;

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
	//Para obtener un individuo mas apto debe de ordenar 
	//ascendentemente el arreglo para ubicar en la primera casilla el que tiene menor costo.
	public Individuo obtenerIndividuoMasApto(int i) {
		//Falta poner un criterio de comparacion en la clase individuo
		Arrays.sort(this.poblacion);
		return this.poblacion[i];
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
	public void revolver() {
		Random rdm=new Random();
		Individuo individuo;
		int indiceAleatorio;
	
		for(int i=size()-1; i>0;i--) {
			indiceAleatorio=rdm.nextInt(i+1);
			individuo=poblacion[indiceAleatorio];
			poblacion[indiceAleatorio]=poblacion[i];
			poblacion[i]=individuo;
		}
	}
	public Individuo getIndividuo(int i) {
		return poblacion[i];
	}
	public Individuo setIndividuo(int i,Individuo individuo) {
		return poblacion[i]=individuo;
	}
}
