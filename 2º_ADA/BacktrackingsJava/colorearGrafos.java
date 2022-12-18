import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class colorearGrafos{

    int[][] grafo = {{0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                     {1, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                     {1, 1, 0, 1, 0, 0, 0, 1, 0, 0},
                     {1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
                     {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                     {1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                     {0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                     {0, 0, 1, 0, 0, 1, 0, 0, 0, 1},
                     {0, 0, 0, 1, 0, 1, 1, 0, 0, 0},
                     {0, 0, 0, 0, 1, 1, 1, 1, 0, 0}};

    int[] colores = {1, 2, 3, 4};
    int[] colorNodo;
    int tam = 10;
    List<Integer> visitados;

    public colorearGrafos(){
        visitados = new ArrayList<>();
        colorNodo = new int[10];
        Arrays.fill(colorNodo, 0);
        colorNodo[0] = 1;
    }

    public void mostrarVector(List<Integer> lista){
        for(int i = 0; i < lista.size(); i++){
            System.out.print(lista.get(i) + " con color ");
            if(colorNodo[lista.get(i)] == 1) System.out.println("rojo.");
            else if(colorNodo[lista.get(i)] == 2) System.out.println("azul.");
            else if(colorNodo[lista.get(i)] == 3) System.out.println("verde.");
            else if(colorNodo[lista.get(i)] == 4) System.out.println("amarillo.");
        }
        System.out.println();
    }

    public void generarAdyacentes(int grafo[][], List<Integer> ady, int tipoVertice){
        for(int i = 0; i < grafo.length; i++){
            if(grafo[tipoVertice][i] != 0) ady.add(i);
        }
    }

    void colorearNodo(int nodo, int w){
        List<Integer> pruebas = new ArrayList<>();
        boolean[] coloreado = {false, false, false, false};
        generarAdyacentes(grafo, pruebas, w);
        for(int i = 0; i < pruebas.size(); i++){
            if(colorNodo[pruebas.get(i)] == 1) coloreado[0] = true;
            else if(colorNodo[pruebas.get(i)] == 2) coloreado[1] = true;
            else if(colorNodo[pruebas.get(i)] == 3) coloreado[2] = true;
            else if(colorNodo[pruebas.get(i)] == 4) coloreado[3] = true;
        }
        for(int i = 0; i < coloreado.length; i++){
            if(!coloreado[i]) colorNodo[w] = i+1;
        }
    }

    public void backtrack(int nodoInicial, List<Integer> visitados){
        List<Integer> adyacentes = new ArrayList<>();
        visitados.add(nodoInicial);
        generarAdyacentes(grafo, adyacentes, nodoInicial);
        while(!adyacentes.isEmpty()){
            int w = adyacentes.get(0);
            adyacentes.remove(0);
            if(!visitados.contains(w)){
                colorearNodo(nodoInicial, w);
                backtrack(w, visitados);
            }
        }
    }

    public static void main(String[] args) {
        colorearGrafos c = new colorearGrafos();
        c.backtrack(0, c.visitados);
        c.mostrarVector(c.visitados);
    }
}