public class ministerioAgain {

    int N;
    int vecSol[];
    int vecPar[];
    int bestEficiencia;
    int efiPar;
    boolean visitados[];

    int matrix[][] = {{1, 5, 1, 5},
                      {1, 1, 2, 2},
                      {1, 3, 1, 2},
                      {1, 1, 4, 1}};
    
    public ministerioAgain(){
        N = 4;
        vecSol = new int[N];
        vecPar = new int[N];
        bestEficiencia = 0;
        efiPar = 0;
        visitados = new boolean[N];
    }

    public void fillIntVector(int vector[]){
        for(int i = 0; i < vector.length; i++)
            vector[i] = -1;
    }

    public void mostrarVector(int vector[], int bestEficiencia){
        for(int i = 0; i < vector.length; i++)
            System.out.print(vector[i] + " ");
            System.out.println(bestEficiencia);
    }

    void mejor(){
        if(efiPar > bestEficiencia){
            bestEficiencia = efiPar;
            vecSol = vecPar.clone();
        }
    }

    boolean aceptable(int vector[], int tarea){
        for(int i = 0; i < vector.length; i++)
            if(vector[i] == tarea) return false; // si trabajador "i" ya tiene esa tarea
        return true;
    }

    // etapa = trabajador; i = tarea
    public void backtrack(int etapa){
        if(etapa == N){
            mejor();
        }
        else{
            for(int i = 0; i < N; i++){
                if(aceptable(vecPar, i)){
                    if(!visitados[etapa]){
                        visitados[etapa] = true; // Trabajador usado
                        vecPar[etapa] = i; // Anotamos
                        efiPar += matrix[etapa][i]; // Sumamos

                        backtrack(etapa+1);

                        efiPar -= matrix[etapa][i]; // Restamos
                        visitados[etapa] = false; // Trabajador libre
                        vecPar[etapa] = -1; // Desanotamos
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        ministerioAgain m = new ministerioAgain();
        m.fillIntVector(m.vecPar);
        m.backtrack(0);
        m.mostrarVector(m.vecSol, m.bestEficiencia);

    }
}