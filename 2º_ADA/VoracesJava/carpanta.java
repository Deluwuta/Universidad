import java.util.ArrayList;
import java.util.List;

public class carpanta {
    /* N alimentos. Maximizar el valor del plato. Tipo el problema del knocksap
     * Alimentos divisibles
     * n = alimentos disponibles. 1 <= n <= 1000
     * a = área del plato. 0 <= a <=  100000 mm
     * Alimentos formados por:
     * valor por mm -> vi, 0 <= vi <= 100
     * area -> ai, 0 <= ai <= 100000000
     * 
     * salida: valor máximo
     */

    public class comida{
        int espacio;
        int valor;
        public comida(int espacio, int valor){
            this.espacio = espacio;
            this.valor = valor;
        }
    }

    List<comida> arrayCom;
    int numAlimentos;
    int areaPlato;

    
    public carpanta(){
        numAlimentos = 0;
        areaPlato = 0;
        arrayCom = new ArrayList<>();
    }
    
    public int llenarPlato(int numAlimentos, int areaPlato){
        int areaParcial = 0;
        int valorTotal = 0;

        for(int i = 0; i < numAlimentos; i++){
            if(areaParcial + arrayCom.get(i).espacio <= areaPlato){
                valorTotal += arrayCom.get(i).valor * arrayCom.get(i).espacio;
                areaParcial += arrayCom.get(i).espacio;
            }
            else if(areaParcial < areaPlato) { 
                System.out.println("parcial: " + areaParcial + " plato: " + areaPlato);
                System.out.println("");
                valorTotal += arrayCom.get(i).valor * (areaPlato - areaParcial);
                areaParcial += areaPlato - areaParcial;
            }
        }
        System.out.println(areaParcial);
        return valorTotal;
    }

    public static void main(String[] args) {
        carpanta c = new carpanta();

        c.arrayCom.add(c.new comida(12, 80)); // 960
        c.arrayCom.add(c.new comida(230, 50)); // 11500
        c.arrayCom.add(c.new comida(450, 25)); // 11250
        c.arrayCom.add(c.new comida(1000000, 10)); // 10000000
        c.arrayCom.add(c.new comida(50, 2)); // 100

        c.numAlimentos = 5;
        c.areaPlato = 1000;

        int j = 0;
        j = c.llenarPlato(c.numAlimentos, c.areaPlato);
        System.out.println("HOla: " + j);
    }
}
