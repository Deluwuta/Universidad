// De esta forma tira en C, no en java 🤡

import java.util.Arrays;

public class alforjas {
    
    int[] pesos;
    int[] usados;
    int[] verga;
    int numObjetos;
    int reparto;

    public alforjas(int num){
        numObjetos = num;
        pesos = new int [numObjetos];
        usados = new int[numObjetos];
        reparto = 99999;

        Arrays.fill(usados, 0);
        pesos[0] = 52;
        pesos[1] = 42;
        pesos[2] = 30;
        pesos[3] = 28;
        pesos[4] = 58;
        pesos[5] = 25;
        pesos[6] = 39;
        pesos[7] = 55;
        pesos[8] = 60;
        pesos[9] = 17;
        pesos[10] = 44;
        pesos[11] = 50;
        pesos[12] = 68;
        pesos[13] = 27;
        pesos[14] = 53;
    }

    void mostrarSolucion(){
        for(int i = 0; i < numObjetos; i++){
            System.out.print("El objeto " + i + " va a la alforja ");
            if(verga[i] == 0) System.out.println("1.");
            else System.out.println("2.");
        }
        System.out.println("Diferencia de pesos entre ambas: " + reparto);
    }

    void mejorSol(){
        int cont1, cont2;
        cont1 = cont2 = 0;
        for(int i = 0; i < numObjetos; i++){
            if(usados[i] == 0) cont1 += pesos[i];
            else cont2 += pesos[i];
        }
        if(Math.abs(cont1 - cont2) < reparto){
            reparto = Math.abs(cont1 - cont2);
            verga = usados;
        }
        
        //if(reparto == 0) mostrarSolucion();
    }

    public void backtrack(int etapa){
        if(etapa == numObjetos){
            mejorSol();
        }
        else{
            for(int i = 0; i < 2; i++){
                usados[etapa] = i;
                backtrack(etapa+1);
            }
        }
    }

    public static void main(String[] args) {
        alforjas a = new alforjas(15);
        a.backtrack(0);
        a.mostrarSolucion();
    }

}