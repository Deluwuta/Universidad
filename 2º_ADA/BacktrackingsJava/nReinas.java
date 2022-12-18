public class nReinas {

    static void mostrarTablero(int N, int vector[]){
        // Inicializo tablero
        int tablero[][] = new int[N][N];
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                tablero[i][j] = 0;
    
        // Metemos 1 para marcar la posición de una Reina
        for(int i = 0; i < N; i++)
            tablero[i][vector[i]] = 1;
    
        // Mostramos tablero
        for(int i = 0; i < N; i++){
            System.out.println();
            for(int j = 0; j < N; j++)
                System.out.print(tablero[i][j] + " ");
        }
        System.out.println("\n");
    }

    public static boolean aceptable(int vector[], int etapa){
        for(int i = 0; i < etapa; i++){
            if(vector[i] == vector[etapa]) return false;
            if(Math.abs(vector[i]-vector[etapa]) == Math.abs(i-etapa)) return false;
        }
        return true;
    }

    public static void backtrack(int N, int vector[], int etapa) {
        if (etapa == N) {
            mostrarTablero(N, vector);
        } else {
            for (int i = 0; i < N; i++) {
                vector[etapa] = i;
                if (aceptable(vector, etapa)) {
                    backtrack(N, vector, etapa + 1);
                }
            }
        }
    }

    public static void backtrack2(int N, int vector[], int etapa) {
        for (int i = 0; i < N; i++) {
            vector[etapa] = i;
            if (aceptable(vector, etapa)) {
                if (etapa == N - 1)
                    mostrarTablero(N, vector);
                else
                    backtrack2(N, vector, etapa + 1);
            }
        }
    }

    public static void main(String[] args) {
        int N = 4;
        int vector[] = new int[N];
        backtrack2(N, vector, 0);
    }
}
