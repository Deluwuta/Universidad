import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class recorridosGrafo {

    int grafoV[][] = {{0, 1, 5, 2},
                      {1, 0, 4, 6},
                      {5, 4, 0, 3},
                      {2, 6, 3, 0}};

    int grafoN[][] = {{0, 0, 0, 1, 1, 1},
                      {0, 0, 1, 0, 1, 0},
                      {0, 1, 0, 0, 0, 1},
                      {1, 0, 0, 0, 1, 0},
                      {1, 1, 0, 1, 0, 1},
                      {1, 0, 1, 0, 1, 0}};


    List<Integer> adyacentes;  
    List<Integer> visitados;
    Queue<Integer> colaNodos;

    int u;
    int v;
    int etiqueta;


    public recorridosGrafo(){
        adyacentes = new ArrayList<>();
        visitados = new ArrayList<>();
        colaNodos = new LinkedList<>();
        u = 0;
        v = 0; 
        etiqueta = 0;
    }

    public void mostrarVector(List<Integer> lista){
        for(int i = 0; i < lista.size(); i++)
            System.out.print(lista.get(i) + " ");
        System.out.println("\n");
    }

    public void mostrarGrafo(int grafo[][]){
        for(int i = 0; i < grafo.length; i++){
            for(int j = 0; j < grafo.length; j++)
                System.out.print(grafo[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    public void generarAdyacentes(int grafo[][], List<Integer> ady, int tipoVertice){
        for(int i = 0; i < grafo.length; i++){
            if(grafo[tipoVertice][i] != 0) ady.add(i);
        }
    }

// ************ Algoritmos de recorridos de grafos. El objetivo es encontrar todos los nodos que componen un grafo CONEXO ************ //

    // ****** RECORRIDO EN PROFUNDIDAD DE UN GRAFO ****** // T(n) = n^2 (Recursivo)
    public void profundidad(int grafo[][], int tipoVertice, List<Integer> visitados){
        adyacentes = new ArrayList<>(); // Crear la lista de adyacentes (NUEVA PARA CADA VERTICE)
        visitados.add(tipoVertice); // Añadimos a visitados el nodo inicial
        generarAdyacentes(grafo, adyacentes, tipoVertice);

        while(!adyacentes.isEmpty()){
            int w = adyacentes.get(0); // Un vértice cualquiera
            adyacentes.remove(0);
            if(!visitados.contains(w)) 
                profundidad(grafo, w, visitados);
        }
    }

    // ****** RECORRIDO EN ANCHURA DE UN GRAFO ****** // T(n) = n^2 (Iterativo)
    public void anchura(int grafo[][], int tipoVertice, List<Integer> visitados, Queue<Integer> colaNodos){
        colaNodos.add(tipoVertice); // Añadimos a las listas el nodo inicial
        visitados.add(tipoVertice);

        while(!colaNodos.isEmpty()){
            int w = colaNodos.poll(); // Sacamos el primer nodo de la cola
            //System.out.print(w + " ");

            adyacentes = new ArrayList<>(); 
            generarAdyacentes(grafo, adyacentes, w);

            while(!adyacentes.isEmpty()){
                int z = adyacentes.get(0);
                adyacentes.remove(0);
                if(!visitados.contains(z)){
                    visitados.add(z);
                    colaNodos.add(z); // Encolamos los nodos adyacentes
                }
            }
        }
    }

    public void modificarValores(int u, int v, int x){
        u = 2;
        v = 4;
        x = 5;
    }

    int elegirArco(int grafo[][], List<Integer> cjtoVertex, int u, int v, int x){
        v = 0; x = 9999; // literal valores useless
        for(int i = 0; i < grafoV.length; i++){
            if(grafoV[u][i] < x && !cjtoVertex.contains(i)){
                x = grafoV[u][i];
                v = i;
            }
        }
        cjtoVertex.add(v);
        grafo[u][v] = x;
        grafo[v][u] = x;

        return v;
    }
        

// ************ Algoritmos que generan árboles de expansión mínimos. Minimizar el número de edges así como su coste. Grafos no dirigidos, conexos, y valuados ************ //

    public void prim(int u, int v, int etiqueta, List<Integer> visitados){
        //List <Integer> visitados = new ArrayList<>(); // Cjto de vértices

        int nuevoGrafo[][] = new int[4][4];
        visitados.add(u);  

        while(visitados.size() < grafoV.length){
            u = elegirArco(nuevoGrafo, visitados, u, v, etiqueta); // Este método sólo debería buscar el arco y devolver el nodo v con la etiqueta
            // nuevoGrafo[visitados.get(0)][v] = etiqueta; // Grafo no dirigido
            // nuevoGrafo[v][visitados.get(0)] = etiqueta; // Dirigido no grafo
            // visitados.add(v); // Hemos visitado v
        }
        mostrarGrafo(nuevoGrafo);
    }

    public void kruskal(){

    }

// ************ Algoritmos que demuestran las existencia de caminos en un grafo. El objetivo es encontrar un camino desde un nodo a los demás. Grafo no valuado ************ //

    public void matrices(){

    }

    public void warshall(){

    }

// ************ Algoritmos que encuentran el camino MÍNIMO de un grafo. Grafo con caminos de menor coste posible. Grafos valuados ************ //

    public void floyd(){

    }

    public void dijkstra(){

    }

    public static void main(String[] args) {
        recorridosGrafo g = new recorridosGrafo();

        g.profundidad(g.grafoN, 0, g.visitados);
        System.out.print("RECORRIDO EN PROFUNDIDAD: "); g.mostrarVector(g.visitados);
        g.visitados.removeAll(g.visitados);

        g.anchura(g.grafoN, 0, g.visitados, g.colaNodos);
        System.out.print("RECORRIDO EN ANCHURA: "); g.mostrarVector(g.visitados);
        g.visitados.removeAll(g.visitados);
        g.colaNodos.removeAll(g.colaNodos);

        System.out.println("ALGORITMO DE PRIM\nLista adyacencia MST: ");
        g.prim(2, g.v, g.etiqueta, g.visitados);
        //System.out.print("Orden generación: "); g.mostrarVector(g.visitados);

    }
}
