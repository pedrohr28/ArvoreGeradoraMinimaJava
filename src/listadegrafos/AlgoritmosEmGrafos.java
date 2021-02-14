package listadegrafos;

import java.util.ArrayList;
import javafx.util.Pair;

public class AlgoritmosEmGrafos extends Grafo{
    private final int infinito = Integer.MAX_VALUE;
    private final ArrayList <Pair<Integer,Integer>> arestasArvoreGeradoraMinima; //armazena as arestas que estão na AGM
    private final int[] verticeAntecessorAGM; //guarda o vértice antecessor de cada nó pertencente  à AGM
    
    public AlgoritmosEmGrafos(int vertices){ //construtor
        super(vertices); //inicializa construtor de Grafo
        this.verticeAntecessorAGM = new int[vertices]; //cria vetor de vértices antecessores
        this.arestasArvoreGeradoraMinima = new ArrayList();
    }
    
    public int iniciaArvoreGeradoraMinima(int vertice){ //inicia algoritmo AGM
        for(int i=0;i<this.numeroVertices;i++){
            this.verticeAntecessorAGM[i]=-1; //inicia todos vértices antecessores como -1
        }
        return this.arvoreGeradoraMinima(vertice);
    }
    private int arvoreGeradoraMinima(int vertice)
    { //implementar prim
        ArrayList<Integer> conhecidos = new ArrayList();
        int pesototal=0;
        boolean[] visitados= new boolean [this.numeroVertices]; //marca vertice como visitado
        int[] distancias = new int[this.numeroVertices];
        for(int i=0;i<this.numeroVertices;i++)
        {
            visitados[i]=false;
            distancias[i]=infinito;
        }
        ArrayList<Integer> naoconhecidos = new ArrayList(); //vetor de vertices nao visitados
        for(int i=0;i<this.numeroVertices;i++)
        {
            naoconhecidos.add(i);   
        }
        distancias[vertice]=0;
        visitados[vertice]=true;
   
       
        while(!naoconhecidos.isEmpty()) //enquanto naoconhecidos nao estiver vazio
        {
            
            int vertice_predecessor=-1;
            for(int i=0;i<naoconhecidos.size();i++)
            {
                if(naoconhecidos.get(i)==vertice) //se vertice estiver em naoconhecidos
                {
                    conhecidos.add(vertice); //adiciona em conhecidos
                    naoconhecidos.remove(i); //e retira de naoconhecidos
                }
            }
            ArrayList<Integer> arestas = this.listaDeAdjacencia(vertice);
            for(Integer vertice2 : arestas) //percorre lista de arestas
            {
                if(this.getPeso(vertice, vertice2)<distancias[vertice2]&&!visitados[vertice2]) //se peso da aresta for menor que a anterior e vertice2 nao foi visitado
                {
                   
                    distancias[vertice2]=this.getPeso(vertice, vertice2); //soma peso do vertice com vertice2 e guarda no vertice2
                    this.verticeAntecessorAGM[vertice2]=vertice; //guarda vertice antecessor ao vertice2
                    vertice_predecessor=vertice2; //coloca vertice2 como antecessor
                }   
            }
    
            int distancia_min=infinito;
            //his.arestasArvoreGeradoraMinima.add(new Pair <Integer,Integer> (vertice,vertice_predecessor)); //joga vertice e vertice2 como aresta da AGM
            for(int i=0;i<naoconhecidos.size();i++)
            {
                if(distancia_min>distancias[naoconhecidos.get(i)]) //se distancia_min for maior que a distancia de um naoconhecido
                {
                    distancia_min=distancias[naoconhecidos.get(i)]; //distancia_min recebe a distancia do naoconhecido
                    vertice_predecessor=naoconhecidos.get(i); //vertice antecessor será o vertice naoconhecido
                }
            }
            visitados[vertice]=true; //marca como visitado o vertice
            vertice=vertice_predecessor; //e o vertice1 será o vertice antecessor
        }
        for(int i=0;i<this.numeroVertices;i++)
        {
            if(this.verticeAntecessorAGM[i]!=-1)
            {
                this.arestasArvoreGeradoraMinima.add(new Pair <Integer,Integer> (this.verticeAntecessorAGM[i],i));
                pesototal+=this.getPeso(this.verticeAntecessorAGM[i],i);
            }
        }
       
        return pesototal; //retorna a distancia do vertice1
    }
    public ArrayList <Pair<Integer,Integer>> getArestasArvore(){
        return this.arestasArvoreGeradoraMinima;
    }
    public int[] getVerticeAntecessorAGM(){
        return this.verticeAntecessorAGM;
    }
}

