import java.util.ArrayList;
import java.util.Arrays;

public class conciertos {
    /*
     * Entrada:
     * n = número de conciertos. 1 <= n <= 15
     * dm = duración del concierto
     * nob = número de obras. 1 <= nob <= 100
     * vector con la duración de cada obra 
     * Salida:
     * String, SI o NO dependiendo de si se pueden repartir las obras de tal forma en la que se hagan todas sin pasarse de la duración máxima
    */

    int N;
    int dm;
    int nob;
    ArrayList<Integer> vector;

    public conciertos(int N, int dm, int nob, ArrayList<Integer>vector){
        this.N = N;
        this.dm = dm;
        this.nob = nob;
        this.vector = vector;
    }

    public void comprobarSol(int vector[]){
        for(int i = 0; i < vector.length; i++)
            System.out.print(vector[i] + " ");
        System.out.println();
    }

    public void backtrack(int conciertosAc, int durAc){
        int[] usados = {0, 0, 0, 0, 0, 0, 0};
        for(int i = 0; i < N; i++){
            comprobarSol(usados);
            durAc = 0;
            for(int j = 0; j < nob; j++){
                durAc+=vector.get(j);
                if(usados[j] == 0 && durAc <= dm){
                    usados[j] = 1;
                }
                else{
                    durAc-=vector.get(j);
                }
            }
        }
        comprobarSol(usados);
    }

    public static void main(String[] args) {
        ArrayList<Integer> vector = new ArrayList<>(Arrays.asList(80, 80, 30, 10, 20, 50, 50));
        conciertos c = new conciertos(3, 100, 7, vector);
        c.backtrack(0, 0);
    }
}
