import java.util.Arrays;

public class warshallMod { // Hacer que dado un grafo dirigido acíclico el algoritmo de Warshall te muestre el número de caminos distintos que hay de un nodo a otro
    
    int grafo[][] = {{0, 1, 1, 0},
                     {0, 0, 1, 1},
                     {0, 0, 0, 1},
                     {0, 0, 0, 0}};

    int[] pruebas;

    public warshallMod(){
        pruebas = new int[4];
        Arrays.fill(pruebas, 0);
    }

    void mostrarMatriz(int[][] matrix){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    void mostrarVector(int[] vector){
        for(int i : vector)
            System.out.print(i + " ");
        System.out.println();
    }

    public void warshall(){
        int i, j, k;
        int matrix[][] = grafo.clone();
        for(k = 0; k < 4; k++){
            for(i = 0; i < 4; i++){
                for(j = 0; j < 4; j++){
                    if(matrix[i][j] == 0){ // Esto comprueba el camino directo. Como ahora el objetivo es ver CUÁNTOS caminos hay, no si existe.
                        if(matrix[i][k] == 1 && matrix[k][j] == 1){ // Esto solo comprueba los caminos extra, aquellos longitud de 2 o más.
                            matrix[i][j] = 1;
                        }
                    }
                }
            }
        }
        mostrarMatriz(matrix);
    }

    public void warshallModded(){
        int i, j, k;
        int matrix[][] = grafo.clone();
        for(k = 3; k >= 0; k--){
            for(i = 0; i < 4; i++){
                for(j = 0; j < 4; j++){
                    if(matrix[k][i] != 0 && matrix[i][j] != 0){ // Esto solo comprueba los caminos extra, aquellos longitud de 2 o más.
                        matrix[k][j] += 1;
                        pruebas[j] = pruebas[j]+1; // k = 1 
                    }
                }
            }
            // He terminado con el mayor nodo del grafo
            if(k > 0){
                for(int a = 0; a < 4; a++)
                    matrix[k-1][a] += pruebas[a];
            }
            Arrays.fill(pruebas, 0);
        }
        mostrarMatriz(matrix);
    }

    public static void main(String[] args) {
        warshallMod w = new warshallMod();
        w.warshallModded();
    }
}
