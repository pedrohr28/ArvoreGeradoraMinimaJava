/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadegrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author aluno
 */
public class Lista8 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{
        AlgoritmosEmGrafos grafo = null;
        int arestas=0, vertices=0;
        int vertice1,vertice2;
        int primeira_linha=0;
        int peso=0;
        int choose;
        int [] antecessor;
        int min;
        
        System.out.println("Escolha o grafo:");
        System.out.println("1 - GrafoAGM1");
        System.out.println("2 - GrafoAGM2");
        Scanner escolha = new Scanner(System.in);
        System.out.print("Digite sua escolha: ");
        choose = escolha.nextInt();
        BufferedReader ler;
        if(choose==1){
            ler = new BufferedReader(new FileReader("src/listadegrafos/grafoagm1.txt")); 
        }else{
            ler = new BufferedReader(new FileReader("src/listadegrafos/grafoagm2.txt")); 
        }
        while(ler.ready())
        { 
            String linha= ler.readLine();
            String []textosplit= linha.split(" ");
            if(primeira_linha==0)
            {
                vertices=Integer.valueOf(textosplit[0]);
                arestas=Integer.valueOf(textosplit[1]);
                grafo=new AlgoritmosEmGrafos(vertices);
                primeira_linha++;
                //System.out.println(vertices+" "+arestas);
            }
            else
            {
                vertice1=Integer.valueOf(textosplit[0]);
                vertice2=Integer.valueOf(textosplit[1]);
                peso=Integer.valueOf(textosplit[2]);
                grafo.insereAresta(vertice1,vertice2 ,peso);
                grafo.insereArestaNaoOrientada(vertice1, vertice2, peso);
                //System.out.println(vertice1+" "+vertice2+" "+peso);
            }
        }
        System.out.println("Escolha seu experimento: ");
        System.out.println("1 - Experimento 1 (vértice 0)");
        System.out.println("2 - Experimento 2 (vértice 5)");
        System.out.println("Escolha: ");
        choose = escolha.nextInt();
        int pesototal;
        if(choose==1){    //experimento 1 
            min=grafo.iniciaArvoreGeradoraMinima(0);
            pesototal=grafo.iniciaArvoreGeradoraMinima(0);
        }
        else{ //experimento 2
            min=grafo.iniciaArvoreGeradoraMinima(5);
            pesototal=grafo.iniciaArvoreGeradoraMinima(5);
        }
        for(int i=0;i<vertices;i++)
        {
            System.out.print("Vertice "+i+" |");
            for(int j=0;j<vertices;j++)
            {
                System.out.print(grafo.getPeso(i, j)+" ");
            }
            System.out.println("");
        }
        //int min=grafo.iniciaArvoreGeradoraMinima(0);
        //System.out.println("Caminho total = "+min);
        ArrayList <Pair<Integer,Integer>> aresta_arvore = grafo.getArestasArvore();
        System.out.println("Arestas do grafo que pertencem à arvore:");
        
        for(int i=0;i<vertices-1;i++)
        {
            System.out.println("Vertice de Origem : "+aresta_arvore.get(i).getKey()+" Vertice de Destino : "+aresta_arvore.get(i).getValue()+" Peso = "+grafo.getPeso(aresta_arvore.get(i).getKey(), aresta_arvore.get(i).getValue()));
            
        }
        System.out.println("Peso total = "+pesototal);
        System.out.println("Vetor de nós antecessores: ");
        antecessor = new int[grafo.getVerticeAntecessorAGM().length];
        antecessor = grafo.getVerticeAntecessorAGM();
        for(int i=0;i<antecessor.length;i++){
            System.out.print(antecessor[i]+" ");
        }
        System.out.println();
    }

  
    
}
