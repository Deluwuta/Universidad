public class sudokuA {

    int[][] matrix = {{5, 3, 0, 0, 7, 0, 0, 0, 0},
                      {6, 0, 0, 1, 9, 5, 0, 0, 0},
                      {0, 9, 8, 0, 0, 0, 0, 6, 0},
                      {8, 0, 0, 0, 6, 0, 0, 0, 3},
                      {4, 0, 0, 8, 0, 3, 0, 0, 1},
                      {7, 0, 0, 0, 2, 0, 0, 0, 6},
                      {0, 6, 0, 0, 0, 0, 2, 8, 0},
                      {0, 0, 0, 4, 1, 9, 0, 0, 5},
                      {0, 0, 0, 0, 8, 0, 0, 7, 9}};

    int tam = 9;

    public sudokuA(){

    }

    public void printGrid(int matrix[][]){
        for(int i = 0; i < tam; i++){
            for(int j = 0; j < tam; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    public boolean aceptable(int matrix[][], int x, int y, int num){
        for(int i = 0; i < tam; i++){
            if(matrix[x][i] == num) return false;
            if(matrix[i][y] == num) return false;
        }

        int parX = x - x%3; // Si x es 5, 5 - 2 = 3 --> 3, 4, 5
        int parY = y - y%3;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++)
                if(matrix[parX+i][parY+j] == num) return false;
        }
        return true;
    }

    public boolean backtrack(int matrix[][], int x, int y){
        if(y == 9 && x == 8) return true;
        
        if(y == 9){
            x++;
            y = 0;
        }

        if(matrix[x][y] != 0) return backtrack(matrix, x, y+1);
            for(int i = 1; i <= tam; i++){
                if(aceptable(matrix, x, y, i)){
                    matrix[x][y] = i;
                    if(backtrack(matrix, x, y+1)) return true;
                    matrix[x][y] = 0;
                }
            }
            return false;
    }    

    public static void main(String[] args) {
        sudokuA s = new sudokuA();
        boolean kek = false;
        kek = s.backtrack(s.matrix, 0, 0);

        if(kek) s.printGrid(s.matrix);
        else System.out.println("NO SOLUCION");
    }
}
