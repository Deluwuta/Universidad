import java.util.Random;

public class ordenacionVectores {
    int[] vector;
    int[] vector2;
    public ordenacionVectores(){
        vector = new int[50];
        vector2 = new int[50];
    }

    // ****** MERGESORT NO SÉ ****** //

    void Mezclar(int iz, int de, int mitad){
        int[] B = new int[50];
        int h, i, j, k;
        k = mitad+1;
        j = iz;
        i = iz;
        while((j <= mitad) && (k <= de)){
            if(vector[j] <= vector[k]){
                B[i] = vector[j]; 
                j++;
            }
            else{
                B[i] = vector[k]; 
                k++;
            }
            i++;
        }
        if(j > mitad){
            for(h = k; h <= de; h++){
                B[i] = vector[h]; 
                i++;
            }
        }
        else{
            for(h = j; h <= mitad; h++){
                B[i] = vector[h]; 
                i++;
            }
        }
        for(k = iz; k <= de; k++)
            vector[k] = B[k];
    }

    void Ordena(int iz, int de){
        int mitad;
        if(iz < de){
            mitad = (iz+de)/2;
            Ordena(iz, mitad);
            Ordena(mitad+1, de);
            Mezclar(iz, de, mitad);
        }
    }

    public void mergeSort(){ // O(nlogn) en todos los casos
        int iz, de;
        iz = 0; de = vector.length-1;
        Ordena(iz, de);
        for(int i = iz; i <= de; i++)
            System.out.print(vector[i] + " ");
        System.out.println();
    }

    // ****************** MERGESORT NO SÉ ****************** //

    // ****** QUICKSORT O ALGO ASÍ ****** //

    void OrdenaButQuick(int Izq, int Der) {
        int i, j;
        int Pivote, Aux;
        if (Izq < Der) {
            i = Izq;
            j = Der;
            Pivote = vector2[(Izq + Der) / 2];
            do {
                while (vector2[i] < Pivote) i++;
                while (vector2[j] > Pivote) j--;
                if (i <= j) {
                    Aux = vector2[i];
                    vector2[i] = vector2[j];
                    vector2[j] = Aux;
                    i++; j--;
                }
            } while (i < j);
            if (Izq < j) OrdenaButQuick(Izq, j);
            if (i < Der) OrdenaButQuick(i, Der);
        }
    }

    public void quicksort(){
        int iz, de;
        iz = 0; de = vector2.length-1;
        OrdenaButQuick(iz, de);
        for(int i = iz; i <= de; i++)
            System.out.print(vector2[i] + " ");
        System.out.println();
    }

    // ****************** ORDENA RÁPIDO O ALGO ASÍ ****************** //

    public static void main(String[] args) {
        ordenacionVectores o = new ordenacionVectores();
        Random r = new Random();
        for(int i = 0; i < 50; i++){
            o.vector[i] = r.nextInt(301);
            o.vector2[i] = r.nextInt(301);
        }
        o.mergeSort();
        o.quicksort();
    }
}
