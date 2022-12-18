import java.util.Arrays;

public class anillo {
    // Si N es 6, tenemos que ir de 1 a 6 incluído

    int[] vector;
    boolean[] usados;
    int N;

    public anillo(int N){
        this.N = N;
        vector = new int[N];
        usados = new boolean[N];
        Arrays.fill(usados, false);
        Arrays.fill(vector, -1);
        vector[0] = 1;
        usados[0] = true;
    }

    boolean esPrimo(int num){
        if(num == 1) return false;
        if(num == 2) return true;

        int div = num/2;
        for(int i = 2; i <= div; i++)
            if(num%i == 0) return false;
        return true;
    }

    boolean aceptable(int etapa, int num){
        int suma = 0;
        if(etapa == N-1){
            suma = num + vector[0];
            if(esPrimo(suma)){
                suma = num + vector[etapa-1];
                return esPrimo(suma);
            }
            else return false;
            
        }
        else{
            suma = num + vector[etapa-1];
            return esPrimo(suma);
        }
    }

    void mostrarSol(){
        for(int v : vector)
            System.out.print(v + " ");
        System.out.println();
    }

    public void backtrack(int etapa){
        if(etapa == N){
            mostrarSol();
        }
        else{
            for(int i = 2; i <= N; i++){
                if(!usados[i-1]){
                    if(aceptable(etapa, i)){ // Ver que sea primo con el anterior. Si etapa es N-1 hay que compararlo con el 0
                        usados[i-1] = true;
                        vector[etapa] = i;
                        backtrack(etapa+1);
                        vector[etapa] = -1;
                        usados[i-1] = false; 
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        anillo a = new anillo(8);
        a.backtrack(1);
    }
}
