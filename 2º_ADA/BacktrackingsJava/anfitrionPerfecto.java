public class anfitrionPerfecto {
    // Sentar a N personas. Matriz de afinidad entre 0 y 10. Vector con la persona por asiento (Posición del vector el asiento)
    // La matriz es simétrica y se ve afectado por el anterior

    int matrix[][] = {{0, 1, 4, 10, 7, 8},
                      {1, 0, 0, 5, 6, 3},
                      {4, 0, 0, 1, 10, 2},
                      {10, 5, 1, 0, 8, 7},
                      {7, 6, 10, 8, 0, 3},
                      {8, 3, 2, 7, 3, 0}};

    int[] arrayAsientos;
    int afinidadTotal;
    int N;

    boolean[] sentados;
    int[] asientosParcial;
    int afinidadParcial;

    public anfitrionPerfecto(){
        N = 6;
        afinidadTotal = 0;
        afinidadParcial = 0;

        arrayAsientos = new int[N];
        asientosParcial = new int [N];
        sentados = new boolean[N];

    }

    public void inicializarCosas(int arrayAsientos[], int asientosParcial[], boolean sentados[]){
        for(int i = 0; i < N; i++){
            arrayAsientos[i] = -1;
            asientosParcial[i] = -1;
            sentados[i] = false;
        }
    }

    public void mostrarVector(int vector[], int bestEficiencia){
        for(int i = 0; i < vector.length; i++)
            System.out.print(vector[i] + " ");
        System.out.println(bestEficiencia*2);
    }

    void mejor(){
        if(afinidadParcial > afinidadTotal){
            afinidadTotal = afinidadParcial;
            arrayAsientos = asientosParcial.clone();
        }
    }

    boolean aceptable(int i){
        for(int j = 0; j < N; j++)
            if(asientosParcial[j] == i) return false;
        return true;
    }

    public void backtrack(int etapa){
        if(etapa == N){
            afinidadParcial += matrix[asientosParcial[0]][asientosParcial[etapa-1]];
            mejor();
            afinidadParcial -= matrix[asientosParcial[0]][asientosParcial[etapa-1]];
        }
        else{
            for(int i = 0; i < N; i++){
                if(aceptable(i)){
                    //if(!sentados[etapa]){
                        //sentados[etapa] = true;
                        asientosParcial[etapa] = i;
                        afinidadParcial += matrix[i][asientosParcial[etapa-1]];

                        backtrack(etapa+1);

                        afinidadParcial -= matrix[i][asientosParcial[etapa-1]];
                        asientosParcial[etapa] = -1;
                       // sentados[etapa] = false;
                    //}
                }
            }
        }
    }

    public static void main(String[] args) {
        anfitrionPerfecto a = new anfitrionPerfecto();
        a.inicializarCosas(a.arrayAsientos, a.asientosParcial, a.sentados);
        a.asientosParcial[0] = 0;
        a.backtrack(1);

        a.mostrarVector(a.arrayAsientos, a.afinidadTotal);
    }

}
