package mx.edu.uacm;

public class AlgoritmoGenetico {
	private int tamPoblacion;
	private double tazaMutacion;
	private double tazaCruce;
	private int contadorElitismo;
	public AlgoritmoGenetico(int numPoblacion,double tazaMutacion,double tazaCruce,int contadorElitismo) {
		this.tamPoblacion=numPoblacion;
		this.tazaMutacion=tazaMutacion;
		this.tazaCruce=tazaCruce;
		this.contadorElitismo=contadorElitismo;
	}
	public Poblacion getNuevaPobacion(int tamCromosoma) {
		Poblacion poblacion= new Poblacion(this.tamPoblacion,tamCromosoma);
		return poblacion;
	}
	public double calcularFitness(Individuo individuo) {
		return 2;
	}
	public void evaluarPoblacion(Poblacion p) {
		double fitnessPoblacion=0;
		for(Individuo individuo: p.getIndividuos()) {
			fitnessPoblacion+=calcularFitness(individuo);
		}
		p.setFitnessPoblacion(fitnessPoblacion);
	}
	public boolean encontreSolucion(Poblacion p) {
		for(Individuo individuo: p.getIndividuos()) {
			if(individuo.getFitness()==0) {
				return true;
			}
		}
		return false;
	}
	public Individuo seleccionaIndividuo(Poblacion poblacion) {
		Individuo[] individuos=poblacion.getIndividuos();
		//Metodo de la ruleta
		//Ejemplo:
		// poblacion con fitness = 2 5 6 1
		//poblacion global=14
		// 0.5*14 = 7
		double posicionDeLaRuleta=Math.random()*poblacion.getFitnessPoblacion();
		
		double ruedaGiro=0;
		for(Individuo individuo: individuos) {
			ruedaGiro+=individuo.getFitness();
			if(ruedaGiro>=posicionDeLaRuleta) {
				return individuo;
			}
		}
		return individuos[individuos.length-1];
	}
}
