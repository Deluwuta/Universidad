import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class hileraCuadros {
    public class cuadro{
        int alto, ancho, valor;
        public cuadro(int alto, int ancho, int valor){
            this.alto = alto;
            this.ancho = ancho;
            this.valor = valor;
        }
    }

    int numCuadros;
    List<cuadro> listaCuadros;
    List<cuadro> listaParcial;
    List<cuadro> listaFinal;
    boolean[] posicion; // 0 = vertical(ancho); 1 = horizontal(alto)
    boolean[] usado;
    int prestigioTotal; 

    public hileraCuadros(int n){
        numCuadros = n;
        listaCuadros = new ArrayList<>();
        listaParcial = new ArrayList<>();
        listaFinal = new ArrayList<>();
        posicion = new boolean[n];
        usado = new boolean[n];
        Arrays.fill(posicion, false);
        Arrays.fill(usado, false);
        prestigioTotal = 0;
    }

    void distancias(List<cuadro> l){
        int sum = 0;
        for(int i = 0; i < l.size(); i++){
            if(!usado[i]) sum += l.get(i).ancho;
            else sum += l.get(i).alto;
        }
        System.out.println("La suma: " + sum);
    }

    void mejorSol(int a, List<cuadro> listaParcial, int lon){
        //System.out.print("Longitud: " + lon + " Prestigio: " + a);
        //distancias(listaParcial);
        if(prestigioTotal < a){
            prestigioTotal = a;
            posicion = usado.clone();
            listaFinal.clear();
            listaFinal.addAll(listaParcial);
        }
    }

    public void mostrarCuadros(){
        for(int i = 0; i < listaFinal.size(); i++){
            if(!posicion[i]) System.out.println("Cuadro " + i + ". Ocupa " + listaFinal.get(i).ancho + " metros (ancho). Prestigio de: " + listaFinal.get(i).valor);
            else System.out.println("Cuadro " + i + ". Ocupa " + listaFinal.get(i).alto + " metros (alto). Prestigio de: " + listaFinal.get(i).valor);
        }
    }

    public void backtrackExtra(int longitud, int longitudParcial, int prestigioParcial){
        if(longitud == longitudParcial){ // Solución
            mejorSol(prestigioParcial, listaParcial, longitudParcial);
        }
        else{ // Tenemos n cuadros. 2 posiciones. 
            for(int i = 0; i < numCuadros; i++){
                if(!usado[i]){
                    if(longitudParcial + listaCuadros.get(i).alto <= longitud){
                        usado[i] = true;
                        longitudParcial += listaCuadros.get(i).alto;
                        prestigioParcial += listaCuadros.get(i).valor;
                        listaParcial.add(listaCuadros.get(i));
                        backtrackExtra(longitud, longitudParcial, prestigioParcial);
                        usado[i] = false;
                        longitudParcial -= listaCuadros.get(i).alto;
                        prestigioParcial -= listaCuadros.get(i).valor;
                        listaParcial.remove(listaCuadros.get(i));
                    }
                    if(longitudParcial + listaCuadros.get(i).ancho <= longitud){
                        usado[i] = true;
                        longitudParcial += listaCuadros.get(i).ancho;
                        prestigioParcial += listaCuadros.get(i).valor;
                        listaParcial.add(listaCuadros.get(i));
                        backtrackExtra(longitud, longitudParcial, prestigioParcial);
                        usado[i] = false;
                        longitudParcial -= listaCuadros.get(i).ancho;
                        prestigioParcial -= listaCuadros.get(i).valor;
                        listaParcial.remove(listaCuadros.get(i));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        hileraCuadros h = new hileraCuadros(15);
        h.listaCuadros.add(h.new cuadro(14, 9, 51));
        h.listaCuadros.add(h.new cuadro(18, 10, 73));
        h.listaCuadros.add(h.new cuadro(14, 8, 49));
        h.listaCuadros.add(h.new cuadro(12, 9, 39));
        h.listaCuadros.add(h.new cuadro(14, 7, 87));
        h.listaCuadros.add(h.new cuadro(11, 10, 61));
        h.listaCuadros.add(h.new cuadro(15, 6, 97));
        h.listaCuadros.add(h.new cuadro(12, 5, 51));
        h.listaCuadros.add(h.new cuadro(17, 5, 97));
        h.listaCuadros.add(h.new cuadro(17, 5, 82));
        h.listaCuadros.add(h.new cuadro(19, 10, 92));
        h.listaCuadros.add(h.new cuadro(10, 7, 81));
        h.listaCuadros.add(h.new cuadro(14, 9, 66));
        h.listaCuadros.add(h.new cuadro(19, 6, 76));
        h.listaCuadros.add(h.new cuadro(14, 10, 69));

        h.backtrackExtra(50, 0, 0);
        System.out.println();
        h.mostrarCuadros();
        System.out.println(h.prestigioTotal);
    }
}
