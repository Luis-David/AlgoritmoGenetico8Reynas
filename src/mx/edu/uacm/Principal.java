package mx.edu.uacm;

public class Principal {
	public static void main(String[] args) {
		Long inicio=System.currentTimeMillis();
		Long fin;
		Long t;
		AlgoritmoGenetico ag= new AlgoritmoGenetico(50, 0.8,0.95,0);
		Poblacion p= ag.getNuevaPoblacion(12);
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
			p=ag.cruceDeCiclos(p);
			//p=ag.cruceUniforme(p);
			//Aplicar mutacion
			ag.mutacion(p);
			//Evaluacion de la poblacion
			//p= ag.getNuevaPobacion(8);
			ag.evaluarPoblacion(p);
			
			generacion++;
			System.out.println("generacion: "+generacion);

		}
		fin=System.currentTimeMillis();
		t=fin-inicio;
		System.out.println("Fitness de la poblacion:"+p.getFitnessPoblacion());
		System.out.println("Mejor solucion: "+p.obtenerIndividuoMasApto(0));
		System.out.println("Tiempo demorado para encontrar la solucion: "+t+" ms");
	}
}
