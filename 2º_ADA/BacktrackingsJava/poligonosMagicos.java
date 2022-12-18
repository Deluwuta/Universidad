public class poligonosMagicos {
    // A ver tenemos que la suma de la posición N con N-1 (en un vector) ha de ser
    // un número primo
    // Empezamos con 1 y probamos hasta N
    // Hay que sacar todas las soluciones btw

    static void mostrarVector(int vector[], int tam){
        for(int i = 0; i < tam; i++)
            System.out.print(vector[i] + " ");
        System.out.println();
    }

    static boolean aceptable(int vector[], int etapa) {
        for(int i = 0; i < etapa; i++){
            if(vector[i] == vector[etapa]) return false;
        }

        int sumador = (vector[etapa] + vector[etapa - 1]);
        for (int i = 2; i < sumador/2; i++) {
            if (sumador % i == 0)
                return false; // Nos saltamos el 1, probamos hasta la mitad del número. Con 7, de 2 a 3. Con 4, de 2 a 2
        }
        return true;
    }

    public static void backtrack(int vector[], int etapa, int tam) { // vector[0] = 1; etapa = 1
        if (etapa == tam) {
            mostrarVector(vector, tam);
        } else {
            for (int i = 2; i <= tam; i++) {
                vector[etapa] = i;
                if (aceptable(vector, etapa))
                    backtrack(vector, etapa + 1, tam);
            }
        }
    }

    public static void backtrack2(int vector[], int etapa, int tam){
        for(int i = 2; i <= tam; i++){
            vector[etapa] = i;
            if(aceptable(vector, etapa)){
                if(etapa == tam-1) mostrarVector(vector, tam);
                else backtrack2(vector, etapa+1, tam);
            }
        }
    }

    public static void main(String[] args) {
        int N = 6;
        int vector[] = new int[N];
        vector[0] = 1;
        backtrack2(vector, 1, N); 
    }
}
