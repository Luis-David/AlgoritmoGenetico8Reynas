package mx.edu.uacm;

public class Principal {
	public static void main(String[] args) {
		AlgoritmoGenetico ag= new AlgoritmoGenetico(50, 0.01,0.95,0);
		Poblacion p= ag.getNuevaPobacion(8);
		ag.evaluarPoblacion(p);
		int generacion=1;
		for(Individuo i: p.getIndividuos()) {
			System.out.println(i);
		}
		while(!ag.encontreSolucion(p)) {
			System.out.println("Fitness de la poblacion:"+p.getFitnessPoblacion());
			System.out.println("Mejor solucion: "+p.obtenerIndividuoMasApto(0));
			//Aplicar cruce
			//p=ag.cruceUnpunto(p);
			p=ag.cruceUniforme(p);
			//Aplicar mutacion
			ag.mutacion(p);
			//Evaluacion de la poblacion
			//p= ag.getNuevaPobacion(8);
			ag.evaluarPoblacion(p);
			
			generacion++;
			System.out.println("generacion: "+generacion);

		}
		System.out.println("Fitness de la poblacion:"+p.getFitnessPoblacion());
		System.out.println("Mejor solucion: "+p.obtenerIndividuoMasApto(0));
	}
}
