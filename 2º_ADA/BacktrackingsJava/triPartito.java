import java.util.Arrays;

public class triPartito {
    
    int[] conjunto = {10, 25, 3, 7, 9, 11, 15, 4, 5, 1};
    int[] vectorSol = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    boolean[] visitados;
    int tam;
    boolean enc = false;
    int contadorParcial = 0;
    int sumador = 0;
    int sumadorParcial = 0;

    public triPartito(){
        tam = 10;
        visitados = new boolean[tam];
        Arrays.fill(visitados, false);
    }

    public int particion(int parte){
        int cont = 0;
        for(int i = 0; i < tam; i++)
            cont += conjunto[i];
        if(cont % parte != 0) return 1;
        else return cont/parte;
    }

    void mostrarSol(int parte){
        for(int i = 1; i <= parte; i++){
            System.out.print("Conjunto " + i + ": [");
            for(int j = 0; j < tam; j++)
                if(vectorSol[j] == i) System.out.print(conjunto[j] + ",");
            System.out.print("]");
            System.out.println();
        }
    }

    boolean aceptable(int num, int etapa, int sumatercio){
        int cont = 0;
        for(int i = 0; i < etapa; i++){
            if(vectorSol[i] == num) cont += conjunto[i];
        }
        if(cont + conjunto[etapa] <= sumatercio) return true;
        else return false;
    }

    public boolean backtrack(int etapa, int sumatercio, int parte){
        if(etapa == tam){
            mostrarSol(parte);
            return true;
        }
        else{
            for(int i = 1; i <= parte; i++){
                if(aceptable(i, etapa, sumatercio)){
                    vectorSol[etapa] = i;
                    if(backtrack(etapa+1, sumatercio, parte)) return true;
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {
        triPartito t = new triPartito();
        int sumatercio = t.particion(3);

        if(sumatercio == 1) System.out.println("Mal");
        else t.backtrack(0, sumatercio, 3);

    }
}
