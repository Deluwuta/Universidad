public class OtroSudoku {

    public static void mostrarMatriz(int matriz[][]){
        for(int i = 0; i < 9; i++){
            System.out.println();
            for(int j = 0; j < 9; j++)
                System.out.print(matriz[i][j] + " ");
        }
        System.out.println("\n");
    }

    public static boolean aceptable(int matriz[][], int num, int fila, int columna){
        // Buscamos en la fila
        for(int j = 0; j < 9; j++)
            if(matriz[fila][j] == num) return false;

        // Buscamos en la columna
        for(int j = 0; j < 9; j++)
            if(matriz[j][columna] == num) return false;

        // Buscamos en el cuadrado 3*3
        int filPart = fila - fila%3;
        int colPart = columna - columna%3;

        for(int j = 0; j < 3; j++)
            for(int k = 0; k < 3; k++)
                if(matriz[filPart + j][colPart + k] == num) return false;

        return true;
    }
/*
    // La versión de puto pro 
    public static boolean backtrack(int matriz[][], int fila, int columna){
        if(fila == 8 && columna == 9){
            mostrarMatriz(matriz);
            return true;
        }

        if(columna == 9){
            fila++;
            columna = 0;
        }

        if(matriz[fila][columna] != 0) return backtrack(matriz, fila, columna+1);
        else{
            for(int i = 1; i < 10; i++){
                if(aceptable(matriz, i, fila, columna)){
                    matriz[fila][columna] = i;
                    if(backtrack(matriz, fila, columna+1)) return true;
                }
                matriz[fila][columna] = 0;
            }
            return false;
        }
    }
*/

    // Backtracking hecho por mí, soy gilipollas
    public static void backtrack2(int matriz[][], int fila, int columna){
        if(fila == 0 && columna == 9) mostrarMatriz(matriz); // Estado objetivo
        else{
            if(matriz[fila][columna] != 0){ // Si no hay 0 en la casilla, avanzamos
                if(fila == 8) backtrack2(matriz, 0, columna+1);
                else backtrack2(matriz, fila+1, columna);
            }
            else{ // Si hay 0 en la casilla, probamos números
                for(int i = 1; i < 10; i++){
                    if(aceptable(matriz, i, fila, columna)){ // Si es aceptable
                        matriz[fila][columna] = i; // Anotamos
                        if(fila == 8) backtrack2(matriz, 0, columna+1);
                        else backtrack2(matriz, fila+1, columna);
                    }
                    matriz[fila][columna] = 0; // Desanotamos si volvemos y no era bueno 
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("A ver si conseguimos que esto tire :')");
        int coso[][] = { {1, 0, 0, 0, 0, 0, 0, 2, 6}, 
                         {0, 0, 3, 0, 0, 9, 0, 0, 0},
                         {8, 0, 0, 0, 0, 2, 5, 0, 4},
                         {0, 4, 0, 0, 8, 0, 0, 0, 0},
                         {0, 0, 7, 0, 0, 4, 6, 0, 0},
                         {0, 0, 0, 0, 0, 0, 1, 0, 2},
                         {5, 9, 4, 0, 0, 6, 0, 0, 0},
                         {0, 0, 0, 4, 0, 0, 9, 0, 8},
                         {0, 0, 0, 0, 5, 7, 0, 0, 0} };

        backtrack2(coso, 0, 0);
        
    }
}
