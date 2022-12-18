public class ministerio {
    static final int N = 4;
    int vectorSol[];
    int vectorParcial[];
    int[] vectorSolEfi;
    int [] vectorParcialEfi;
    /*
    Tenemos una matriz de eficiencias con filas=trabajadores y columnas=numtarea
    La idea es sacar la máxima eficiencia posible. 
    Resultado = vector. Posición = tarea, número = trabajador
    Es tipo en mejor hasta ahora.
    */
    int[][] matrix = {{10, 8, 10, 8},
    {1, 3, 4, 6},
    {5, 6, 5, 9},
    {2, 1, 3, 3} };

    public ministerio(){
        this.vectorSol = new int[N];
        vectorParcial = new int[N];
        vectorSolEfi = new int[N];
        vectorParcialEfi = new int[N];
    }

    public void mostrarVector(){
        int suma = 0;
        for(int i = 0; i < N; i++){
            System.out.print(vectorSol[i] + " ");
            suma += vectorSolEfi[i];
        }
        System.out.print(" Con una eficiencia de: " + suma);
        System.out.println();
    }

    public boolean mejorSolucion(){
        int sum1, sum2;
        sum1 = sum2 = 0;
        for(int i = 0; i < vectorSolEfi.length; i++){
            sum1 += vectorSolEfi[i];
            sum2 += vectorParcialEfi[i];
        }
        return sum1 < sum2;
    }

    public boolean puedeTrabajar(int valor){
        for(int i = 0; i < vectorParcial.length; i++)
            if(vectorParcial[i] == valor) return false;
        return true;
    }

    public void backtrack(int etapa, int matrix[][]){
        if(etapa == 4){
            if(mejorSolucion()){
                vectorSol = vectorParcial.clone();
                vectorSolEfi = vectorParcialEfi.clone();
            }
        }
        else{
            for(int i = 1; i <= N; i++){
                if(vectorParcial[etapa] == 0 && puedeTrabajar(i)){ // Tarea libre
                    vectorParcial[etapa] = i; // Anoto. Trabajador i con tarea etapa
                    vectorParcialEfi[etapa] = matrix[i-1][etapa];
                    backtrack(etapa+1, matrix);
                    vectorParcial[etapa] = 0; // Desanoto.
                }
            }
        }
    }

    public static void main(String[] args) {
        ministerio perra = new ministerio();
        perra.backtrack(0, perra.matrix);
        perra.mostrarVector();
    }
}