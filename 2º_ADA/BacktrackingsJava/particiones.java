import java.util.ArrayList;
import java.util.List;

public class particiones{

    int N = 5; // Valor a particionar
    int cont = 0;
    int vecSol[];
    List<Integer> arraySol;

    public particiones(){
        vecSol = new int[N];
        cont = 0;
        arraySol = new ArrayList<>();
    }

    void mostrarSol(){
        for(int i = 0; i < arraySol.size()-1; i++){
            System.out.print(arraySol.get(i) + " + ");
        }
        System.out.print(arraySol.get(arraySol.size()-1));
        System.out.println();
    }

    void backtrack(int etapa) {
        int ini; // Limita el número por el que se empieza ya que una partición es una suma de valores crecientes. n + n+1 --> n <= n+1

        if (etapa == 0) ini = 1;
        else ini = arraySol.get(etapa-1);

        if (cont == N) mostrarSol();
        else {
            for (int i = ini; i < N; i++) {
                if (cont + i <= N) {
                    arraySol.add(i); // Anoto
                    cont += i;
                    backtrack(etapa + 1);
                    arraySol.remove(etapa); // Desanoto
                    cont -= i;
                }
            }
        }
    }

    public static void main(String[] args) {
        particiones p = new particiones();
        p.backtrack(0);
    }
}