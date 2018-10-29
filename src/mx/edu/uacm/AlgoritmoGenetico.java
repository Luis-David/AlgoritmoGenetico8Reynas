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
	public Poblacion cruzar(Poblacion poblacion) {
		Poblacion nuevaPoblacion= new Poblacion(this.tamPoblacion);
		Individuo padre;
		Individuo padre2;
		Individuo hijo;
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
				nuevaPoblacion.setIndividuo(i, hijo);
			}
			else {
				nuevaPoblacion.setIndividuo(i, padre);
			}
		}
		return nuevaPoblacion;
	}
}
