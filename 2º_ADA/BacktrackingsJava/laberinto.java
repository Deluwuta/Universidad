public class laberinto {
    /*
     * Tenemos una matriz con el laberinto, se empieza en el 1,1 y se sale en el n,n
     * Hay que marcar en la matriz por dónde vamos pasando y por dónde hemos pasado
     * Posición libre: 0, muro = 3, camino solución = 1, camino recorrido = 2.  
     * Se necesita la matriz, las posiciones por la que nos vamos a mover x e y, algo para indicar el final?
     */
    int N = 8;
    int matriz[][] = {{0, 3, 3, 3, 3, 0, 0, 0},
                      {0, 0, 3, 0, 3, 0, 3, 0},
                      {3, 0, 3, 0, 3, 0, 3, 0},
                      {3, 0, 0, 0, 3, 0, 3, 3},
                      {3, 0, 3, 3 ,3, 0, 0 ,0},
                      {0, 0, 0, 0, 0, 0, 3, 0},
                      {0, 3, 3, 3, 0, 3, 3, 0},
                      {0, 0, 3, 0, 0, 0, 3, 0}};

    int matrizSolucion[][];
    int x, y;
    boolean fin;

    public laberinto(){
        x = 0;
        y = 0;
        matrizSolucion = new int[N][N];
        fin = false;
    }

    public void inicializarMatrizSol(){
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                matrizSolucion[i][j] = 9;
    }

    public void mostrarMatriz(int matriz[][]){
        for(int i = 0; i < N; i++){
            System.out.println();
            for(int j = 0; j < N; j++)
                System.out.print(matriz[i][j] + " ");
        }
        System.out.println("\n");
    }

    public boolean aceptable(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= N) return false;

        if(matriz[x][y] == 3) return false;

        if(matrizSolucion[x][y] == 0) return false;

        if(matrizSolucion[x][y] == 1) return false;

        return true;
    }

    public void backtrack(int x, int y){
        if(x == N-1 && y == N-1){
            matrizSolucion[x][y] = 1;
            fin = true;
            System.out.println("Solución encontrada");
        }
        else{
            matrizSolucion[x][y] = 1;
            if(aceptable(x+1, y) && !fin){
                backtrack(x+1, y);
            }
            if(aceptable(x, y+1) && !fin){
                backtrack(x, y+1);
            }
            if(aceptable(x-1, y) && !fin){
                backtrack(x-1, y);
            }
            if(aceptable(x, y-1) && !fin){
                backtrack(x, y-1);
            }
            if(!fin) matrizSolucion[x][y] = 0;
        }
    }

    public static void main(String[] args) {
        laberinto l = new laberinto();
        l.inicializarMatrizSol();
        l.mostrarMatriz(l.matrizSolucion);
        l.backtrack(0, 0);
        System.out.println("Lloro");
        l.mostrarMatriz(l.matrizSolucion);
    }
}
