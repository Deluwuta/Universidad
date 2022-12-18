import java.util.Arrays;

public class parejasEstables2 { 
    
    int numHombres = 6;
    int numMujeres = 4;

    int[][] matOpinionHombres = {{0, 1, 2, 3}, // Opinion de las mujeres del hombre 0 -> El hombre 0 prefiere a la mujer 0, luego a la 1...
                                 {3, 1, 2, 0}, // Opinion de las mujeres del hombre 1
                                 {3, 2, 1, 0}, // Opinion de las mujeres del hombre 2
                                 {0, 3, 1, 2}, // Opinion de las mujeres del hombre 3
                                 {2, 0, 3, 1}, // Opinion de las mujeres del hombre 4
                                 {1, 0, 3, 2}};// Opinion de las mujeres del hombre 5

    int[][] matOpinionMujeres = {{1, 2, 3, 4, 5, 0}, // Opinion de los hombres de la mujer 0 
                                 {4, 2, 3, 1, 0, 5}, // Opinion de los hombres de la mujer 1 
                                 {3, 4, 1, 5, 2, 0}, // Opinion de los hombres de la mujer 2 
                                 {2, 0, 4, 1, 3, 5}};// Opinion de los hombres de la mujer 3 

    int[] eleccionMujer = {-1, -1, -1, -1};
    int[] parcial;
    boolean[] emparejado;

    int mejorEstabilidad;

    
    public parejasEstables2(){
        mejorEstabilidad = 999999999;
        parcial = new int[4];
        emparejado = new boolean[6];
        Arrays.fill(parcial, -1);
        Arrays.fill(emparejado, false);
    }

    void comprobarSol(int inestabilidad, int[] vector){
        if(inestabilidad < mejorEstabilidad){ 
            mejorEstabilidad = inestabilidad;
            eleccionMujer = vector.clone();
        }
    }

    int calcularDistancia(int posM, int posH){
        int contH = 0; int contM = 0;
        boolean enc = false;

        for(int i = 0; i < 6 && !enc; i++){
            if(matOpinionMujeres[posM][i] != posH) contM++; // Posicion del hombre posH en el ranking de la mujer posM. A mayor peor
            else enc = true;
        }

        enc = false;
        for(int i = 0; i < 4 && !enc; i++){
            if(matOpinionHombres[posH][i] != posM) contH++; // Posición de la mujer posM en el ranking del hombre posH. A mayor peor
            else enc = true;
        }
        return contH+contM;
    }

    public void backtrack(int etapa, int inestabilidad, int aux, int[] vectorParcial){
        if(etapa == 4){ // Junto a la mujer con el hombre (por ser menos)
            comprobarSol(inestabilidad, vectorParcial);
        }
        else{
            for(int i = 0; i < 6; i++){ // La i marca a los hombres, etapa marca a las mujeres
                if(!emparejado[i]){
                    aux = calcularDistancia(etapa, i);
                    emparejado[i] = true;
                    inestabilidad += aux; // Anoto
                    vectorParcial[etapa] = i;
                    backtrack(etapa+1, inestabilidad, aux, vectorParcial);
                    inestabilidad -= aux;
                    emparejado[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        parejasEstables2 p = new parejasEstables2();
        p.backtrack(0, 0, 0, p.parcial);

        for(int i = 0; i < 4; i++)
            System.out.print(p.eleccionMujer[i] + " ");
        System.out.println();

        System.out.println("Inestabilidad conseguida (menor mejor): " + p.mejorEstabilidad);
    }

}
