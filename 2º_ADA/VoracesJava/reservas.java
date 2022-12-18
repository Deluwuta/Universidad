import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class reservas {
    public class reserva{
        int nPersonas, turno, dinero;
        boolean aLaCarta;
        public reserva(int n, int t, boolean a){
            nPersonas = n;
            aLaCarta = a;
            turno = t;
            if(aLaCarta) dinero = nPersonas * 40;
            else dinero = nPersonas * 20;
        }
    }

    public class Sortbyroll implements Comparator<reserva> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(reserva a, reserva b)
        {
            return a.dinero - b.dinero;
        }
    }

    List<reserva> lista;
    List<reserva> definitiva;
    boolean[] usados;

    public reservas(int tam){
        lista = new ArrayList<>();
        definitiva = new ArrayList<>();
        usados = new boolean[tam];
        Arrays.fill(usados, false);
    }

    public void voraz(int nTurnos, int plazas){
        Sortbyroll s = new Sortbyroll();
        Collections.sort(lista, Collections.reverseOrder(s));

        int plazasParcial = 0;
        int recaudacion = 0;
        for(int i = 0; i < nTurnos; i++){ // Considero que el primer turno es el 0 por simplicidad
            recaudacion = 0;
            plazasParcial = 0;
            for(int j = 0; j < lista.size() && plazas > plazasParcial; j++){
                if(lista.get(j).turno == i){ // Reserva j es del turno i
                    recaudacion += lista.get(j).dinero;
                    definitiva.add(lista.get(j));
                    plazasParcial++;
                }
            }
            System.out.println("Recaudación turno " + i + ": " + recaudacion);
        }
    }

    public static void main(String[] args) {
        /*
         * Pruebas. Consideramos 3 turnos
         * Un total de 15 reservas
         * 3 plazas por turno
         */
        reservas r = new reservas(15);   
        
        r.lista.add(r.new reserva(10, 1, true)); // 1 - 400---
        r.lista.add(r.new reserva(16, 0, false)); // 0 - 320 --
        r.lista.add(r.new reserva(18, 2, false)); // 2 - 360--------------
        r.lista.add(r.new reserva(11, 1, false)); // 1 - 220---
        r.lista.add(r.new reserva(20, 1, true)); // 1 - 800---
        r.lista.add(r.new reserva(19, 0, false)); // 0 - 380--
        r.lista.add(r.new reserva(3, 1, false)); // 1 - 60---
        r.lista.add(r.new reserva(16, 0, false)); // 0 - 320--
        r.lista.add(r.new reserva(6, 2, true)); // 2 - 240----------------
        r.lista.add(r.new reserva(20, 1, true)); // 1 - 800---
        r.lista.add(r.new reserva(4, 2, true)); // 2 - 160-----------------
        r.lista.add(r.new reserva(11, 0, false)); // 0 - 220--
        r.lista.add(r.new reserva(3, 0, false)); // 0 - 60--
        r.lista.add(r.new reserva(20, 1, true)); // 1 - 800 ---
        r.lista.add(r.new reserva(4, 2, true)); // 2 - 160----------------

        r.voraz(3, 3);

    }
}
