import java.util.Arrays;
import java.util.Collections;

public class cambioVoraz {
    
    Integer[] tipoMonedas;
    int cantidad;

    public cambioVoraz(int cantidad, int nMonedas){
        this.cantidad = cantidad;
        tipoMonedas = new Integer[nMonedas];
    }

    public void voraz(Integer[] tipoMonedas){
        Integer[] numMonedas = new Integer[tipoMonedas.length];
        Arrays.fill(numMonedas, 0);
        int cambioParcial = 0;
        Arrays.sort(tipoMonedas, Collections.reverseOrder());
        for(int i = 0; i < tipoMonedas.length; i++){
            while(cambioParcial + tipoMonedas[i] <= cantidad){
                cambioParcial += tipoMonedas[i];
                numMonedas[i]++;
            }
        }
        System.out.println("La cantidad devuelta es de: " + cambioParcial);
        System.out.println("Han sido usadas: ");
        for(int i = 0; i < tipoMonedas.length; i++)
            System.out.print(numMonedas[i] + " de " + tipoMonedas[i] + " cents.\n");
    }

    public static void main(String[] args) {
        cambioVoraz c = new cambioVoraz(23095702, 6);
        c.tipoMonedas[0] = 5;
        c.tipoMonedas[1] = 50;
        c.tipoMonedas[2] = 20;
        c.tipoMonedas[3] = 1;
        c.tipoMonedas[4] = 10;
        c.tipoMonedas[5] = 2;
        c.voraz(c.tipoMonedas);
    }
}
