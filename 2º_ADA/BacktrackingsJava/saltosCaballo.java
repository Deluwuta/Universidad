import java.util.Arrays;

public class saltosCaballo {
    
    int tam;
    int[][] matrix;
    int posicionesATocar;

    public saltosCaballo(int tam){
        this.tam = tam;
        posicionesATocar = tam*tam;
        matrix = new int[tam][tam];

        for (int[] row : matrix)
            Arrays.fill(row, 0); // 0 será casilla no visitada

        //Arrays.fill(matrix, 0); // 0 será casilla no visitada
        matrix[0][0] = 1; // Casilla inicial del caballo
    }

    void mostrarSolucion(){
        System.out.println("TU MADRE ME AGARRA DE LA PORONGA");
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    boolean aceptable(int x, int y){
        if(x < 0 || y < 0 || x >= tam || y >= tam) return false;
        if(matrix[x][y] != 0) return false;

        return true;
    }

    public boolean backtrack(int posicionesParcial, int x, int y){
        if(posicionesParcial == posicionesATocar){
            mostrarSolucion();
            return true;
        }
        else{ // Posiciones: 1. x+2,y+1; 2. x+1,y+2; 3. x-1,y+2; 4. x-2,y+1; 5. x-2,y-1; 6. x-1,y-2; 7. x+1,y-2; 8. x+2,y-1
            if(aceptable(x+2, y+1)){ // 1.
                matrix[x+2][y+1] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x+2, y+1)) return true;
                matrix[x+2][y+1] = 0;
            }
            if(aceptable(x+1, y+2)){ // 2.
                matrix[x+1][y+2] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x+1, y+2)) return true;
                matrix[x+1][y+2] = 0;
            }
            if(aceptable(x-1, y+2)){ // 3.
                matrix[x-1][y+2] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x-1, y+2)) return true;
                matrix[x-1][y+2] = 0;
            }
            if(aceptable(x-2, y+1)){ // 4.
                matrix[x-2][y+1] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x-2, y+1)) return true;
                matrix[x-2][y+1] = 0;
            }
            if(aceptable(x-2, y-1)){ // 5.
                matrix[x-2][y-1] = posicionesParcial+1; // Anoto
                if(backtrack(posicionesParcial+1, x-2, y-1)) return true;
                matrix[x-2][y-1] = 0; // Desanoto
            }
            if(aceptable(x-1, y-2)){ // 6.
                matrix[x-1][y-2] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x-1, y-2)) return true;
                matrix[x-1][y-2] = 0;
            }
            if(aceptable(x+1, y-2)){ // 7.
                matrix[x+1][y-2] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x+1, y-2)) return true;
                matrix[x+1][y-2] = 0;
            }
            if(aceptable(x+2, y-1)){ // 8.
                matrix[x+2][y-1] = posicionesParcial+1;
                if(backtrack(posicionesParcial+1, x+2, y-1)) return true;
                matrix[x+2][y-1] = 0;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        saltosCaballo s = new saltosCaballo(6);
        s.backtrack(1, 0, 0);
    }
}
