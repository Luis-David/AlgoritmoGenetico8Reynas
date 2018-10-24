package mx.edu.uacm;

public class AlgoritmoGenetico {
	private int tamPoblacion;
	private double tazaMutacion;
	private double tazaCruce;
	private int elitismo;
	public AlgoritmoGenetico(int numPoblacion,double tazaMutacion,double tazaCruce,int elitismo) {
		this.tamPoblacion=numPoblacion;
		this.tazaMutacion=tazaMutacion;
		this.tazaCruce=tazaCruce;
		this.elitismo=elitismo;
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
	//Mutacion de un gen con probabilidad 0.8
	public void Mutacion(int[] cromosoma) {
		int aux,posicion,aux2;
		for (int i = 0; i < cromosoma.length; i++) {
			if(0.8>Math.random()) {
				aux =cromosoma[i];
				posicion =(int) (Math.random()*8);
				aux2=cromosoma[posicion];
				cromosoma[posicion]=aux;
				cromosoma[i]=aux2;
			}
		}
		for (int i = 0; i < cromosoma.length; i++) {
			System.out.print(cromosoma[i]);
		}
	}
	//Cruce a un punto
	public void cruceUnpunto(int[] padre, int[] madre) {
		int hijo1[] = new int[8];
		int hijo2[] = new int[8];
		for (int i = 0; i < padre.length/2; i++) {
			hijo1[i]=padre[i];
			hijo2[i]=madre[i];
		}
		for (int i = padre.length/2; i < padre.length; i++) {
			hijo1[i]=madre[i];
			hijo2[i]=padre[i];
		}
		
	}
	
}
