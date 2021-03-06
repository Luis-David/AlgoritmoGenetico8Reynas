package mx.edu.uacm;

import java.util.ArrayList;
import java.util.List;

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
	public Poblacion getNuevaPoblacion(int tamCromosoma) {
		Poblacion poblacion= new Poblacion(this.tamPoblacion,tamCromosoma);
		return poblacion;
	}
	public double calcularFitness(Individuo individuo) {
		int reyna[]= new int[2];
		int c=0;
		int cromosoma[];
		cromosoma=individuo.getCromosoma();
		for(int i=0; i<individuo.getTamanio(); i++){
			reyna[0]=cromosoma[i];//filas
			reyna[1]=i;//columnas
			c=c+getNumeroReynaComidasDiagonalDerecha(reyna,cromosoma);
			c=c+getNumeroReynaComidasDiagonalIzquierda(reyna,cromosoma);
			c=c+getNumeroReynaComidasHorizantal(reyna,cromosoma);

		}
		individuo.setFitness(c);
		return c;
	}
	private int getNumeroReynaComidasDiagonalDerecha(int reyna[],int cromosoma[]){
		int contador=0;
		int casillaArriba[]=new int[2];
		int casillaAbajo[]=new int[2];
		casillaArriba[0]=reyna[0]; casillaArriba[1]=reyna[1];
		casillaAbajo[0]=reyna[0]; casillaAbajo[1]=reyna[1];
		for(int i=0; i<cromosoma.length; i++){
			casillaArriba[0]-=1;
			casillaArriba[1]+=1;
			if(casillaArriba[0]>=0 && casillaArriba[1]<cromosoma.length){
				if(cromosoma[casillaArriba[1]]==casillaArriba[0]){
					contador++;
				}
			}
			casillaAbajo[0]+=1;
			casillaAbajo[1]-=1;
			if(casillaAbajo[1]>=0 && casillaAbajo[0]<cromosoma.length){
				if(cromosoma[casillaAbajo[1]]==casillaAbajo[0]){
					contador++;
				}
			}
		}
		return contador;
	}
	private int getNumeroReynaComidasHorizantal(int reyna[],int cromosoma[]){
		int c1,c2;
		int numReynasComidas=0;
		c1=reyna[1];
		c2=reyna[1];
		for(int i=0; i<cromosoma.length; i++){
			c1+=1;
			if(c1<cromosoma.length){
				if(cromosoma[c1]==reyna[0]){
					numReynasComidas++;
				}
			}
			c2-=1;
			if(c2>=0){
				if(cromosoma[c2]==reyna[0]){
					numReynasComidas++;
				}
			}
		}
		return numReynasComidas;
	}
	private int getNumeroReynaComidasDiagonalIzquierda(int reyna[],int cromosoma[]){
		int contador=0;
		int casillaArriba[]=new int[2];
		int casillaAbajo[]=new int[2];
		casillaArriba[0]=reyna[0]; casillaArriba[1]=reyna[1];
		casillaAbajo[0]=reyna[0]; casillaAbajo[1]=reyna[1];
		for(int i=0; i<cromosoma.length; i++){
			casillaArriba[0]+=1;
			casillaArriba[1]+=1;
			if(casillaArriba[0]<cromosoma.length && casillaArriba[1]<cromosoma.length){
				if(cromosoma[casillaArriba[1]]==casillaArriba[0]){
					contador++;
				}
			}
			casillaAbajo[0]-=1;
			casillaAbajo[1]-=1;
			if(casillaAbajo[1]>=0 && casillaAbajo[0]>=0){
				if(cromosoma[casillaAbajo[1]]==casillaAbajo[0]){
					contador++;
				}
			}
		}
		return contador;
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
	public Poblacion obtenerMediaMejorPoblacion(Poblacion p) {
		Poblacion mp= new Poblacion();
		Individuo individuo;
		int tamanioPoblacion=p.size()/2;
		while(tamanioPoblacion != p.size()) {
			individuo=seleccionaIndividuo(p);
			mp.setIndividuo(individuo);
			p.getIndividuos().remove(individuo);
		}
		return mp;
	}
	public Individuo seleccionaIndividuo(Poblacion poblacion) {
		ArrayList<Individuo> individuos=poblacion.getIndividuos();
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
		return individuos.get(individuos.size()-1);
	}
	
	public void mutacion(Poblacion poblacion) {
		
		int cromosoma[];
		int aux,posicion;
		
		for(Individuo individuo: poblacion.getIndividuos()) {
			cromosoma=individuo.getCromosoma();
			for (int i = 0; i < cromosoma.length; i++) {
				if(tazaMutacion>Math.random()) {
					aux =cromosoma[i];
					posicion =(int) (Math.random()*8);//columna
					cromosoma[i]=cromosoma[posicion];
					cromosoma[posicion]=aux;
				}
			}
		}
	}
	//Cruce a un punto
	public Poblacion cruceUnpunto(Poblacion poblacion) {
		Poblacion nuevaPoblacion= new Poblacion();
		Individuo padre;
		Individuo madre;
		Individuo hijo1=new Individuo(poblacion.getIndividuo(0).getTamanio());
		Individuo hijo2=new Individuo(poblacion.getIndividuo(0).getTamanio());
		int puntoCruce;
		for(int i=0; i<poblacion.size(); i+=2) {
			padre=poblacion.obtenerIndividuoMasApto(i);
		    madre=this.seleccionaIndividuo(poblacion);
			for (int j = 0; j < padre.getTamanio()/2; j++) {
				hijo1.setGen(j,padre.getGen(j));
				hijo2.setGen(j,madre.getGen(j));
			}
			for (int j = padre.getTamanio()/2; j < padre.getTamanio(); j++) {
				hijo1.setGen(j,madre.getGen(j));
				hijo2.setGen(j,padre.getGen(j));
			}
			nuevaPoblacion.setIndividuo(hijo1);
			nuevaPoblacion.setIndividuo(hijo2);
			System.out.println("padres: "+padre+" madre: "+madre);
			System.out.println("hijo1 "+hijo1);
			System.out.println("hijo2 "+hijo2);
		}
		return nuevaPoblacion;
	}
	public Poblacion cruceUniforme(Poblacion poblacion) {
		Poblacion nuevaPoblacion= this.getNuevaPoblacion(poblacion.getIndividuo(0).getTamanio());
		Individuo padre;
		Individuo padre2;
		Individuo hijo;
		System.out.println("Cruce uniforme");
		for(int i=0; i<poblacion.size(); i++) {
			padre=poblacion.obtenerIndividuoMasApto(i);
			if(this.tazaCruce>Math.random() && i>this.elitismo) {
				hijo=new Individuo(padre.getTamanio());
				padre2=this.seleccionaIndividuo(poblacion);
				for(int j=0; i<padre.getTamanio();i++) {
					if(0.5>Math.random()) {
						hijo.setGen(j,padre.getGen(j));
					}
					else {
						hijo.setGen(j, padre2.getGen(j));
					}
				}
				System.out.println("hijo; "+hijo);
				nuevaPoblacion.setIndividuo(hijo);
			}
			else {
				System.out.println("padre: "+padre);
				nuevaPoblacion.setIndividuo(padre);
			}
		}
		return nuevaPoblacion;
	}
	public Poblacion cruceDeCiclos(Poblacion poblacion) {
		Poblacion nuevaPoblacion=this.getNuevaPoblacion(poblacion.getIndividuo(0).getTamanio());
		Poblacion nuevaGeneracion= new Poblacion();
		this.evaluarPoblacion(nuevaPoblacion);
		Poblacion poblacionP,poblacionM;
		ArrayList<Integer> indices = new ArrayList<Integer>();
		Individuo padre;
		Individuo madre;
		Individuo hijo1;
		Individuo hijo2;
		int i=0;
		int alelo=0;
		int indice;
		int c=0;
		
		poblacionM=obtenerMediaMejorPoblacion(poblacion);
		poblacionP=obtenerMediaMejorPoblacion(nuevaPoblacion);
		for(int l=0; l<poblacionM.size(); l++) {
			padre=poblacionP.getIndividuo(l);
			madre=poblacionM.getIndividuo(l);
			indices.clear();
			i=0;
			c=0;
			do{
				indices.add(i);
				alelo=madre.getGen(i);
				i=getIndiceAlelo(padre.getCromosoma(), alelo);
				c++;
			}while(i >0 && c<padre.getTamanio());
		
			hijo1=new Individuo(padre.getCromosoma().length);
			hijo2=new Individuo(padre.getCromosoma().length);
			for(int j=0; j<indices.size(); j++) {
				indice=indices.get(j);
				hijo1.setGen(indice, padre.getGen(indice));
				hijo2.setGen(indice, madre.getGen(indice));
				for(int k=indice+1; (j+1)<indices.size() && k<indices.get(j+1); k++) {
					hijo1.setGen(k, madre.getGen(k));
					hijo2.setGen(k, padre.getGen(k));
				}
			}
			nuevaGeneracion.setIndividuo(hijo1);
			nuevaGeneracion.setIndividuo(hijo2);
		}
		return nuevaGeneracion;
	}
	private int getIndiceAlelo(int[] cromosoma, int alelo) {
		for(int i=0; i<cromosoma.length; i++) {
			if(cromosoma[i]==alelo) {
				return i;
			}
		}
		return -1;
	}
}
