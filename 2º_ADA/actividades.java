import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class actividades {
    DecimalFormat df = new DecimalFormat("#.###");
    
    public class actividad{
        int nota, tiempo;
        double var;
        public actividad(int nota, int tiempo){
            this.nota = nota;
            this.tiempo = tiempo;
            var = ((double)nota/(double)tiempo);
        } 
    }

    int nActividades;
    int tiempoTotal;
    List<actividad> lista;
    boolean[] usada;
    int notaMejor;
    public actividades(int n, int t){
        nActividades = n;
        tiempoTotal = t;
        notaMejor = 0;
        lista = new ArrayList<>();
        usada = new boolean[n];
        Arrays.fill(usada, false);
    }

    public class Sortbyroll implements Comparator<actividad> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(actividad a, actividad b)
        {
            Double change1 = Double.valueOf(a.var);
            Double change2 = Double.valueOf(b.var);
            return change1.compareTo(change2);
        }
    }

    public class Sortbytime implements Comparator<actividad> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(actividad a, actividad b)
        {
            Double change1 = Double.valueOf(a.tiempo);
            Double change2 = Double.valueOf(b.tiempo);
            return change1.compareTo(change2);
        }
    }

    public void mostrarVector(){
        for(int i = 0; i < lista.size(); i++){
            System.out.print(df.format(lista.get(i).nota) + " ");
        }
    }

    public void voraz(){
        Sortbytime s = new Sortbytime();
        //Collections.sort(lista, Collections.reverseOrder(s));
        Collections.sort(lista, s);
        mostrarVector();
        int tiempoRn = 0;
        int notaTotal = 0;
        //int actividadesUsadas = 0;

        for(int i = 0; i < nActividades && tiempoRn <= tiempoTotal; i++){
            if(tiempoRn + lista.get(i).tiempo <= tiempoTotal){
                tiempoRn += lista.get(i).tiempo;
                notaTotal += lista.get(i).nota;
            }
        }
        System.out.println("Nota total lograda: " + notaTotal);
    }

    void mejor(int n){
        if(notaMejor < n) notaMejor = n;
    }

    public void backtrack(int tiempo, int mejorNota){
            for(int i = 0; i < nActividades; i++){
                if(!usada[i]){
                    if(tiempo + lista.get(i).tiempo <= tiempoTotal){
                        usada[i] = true;
                        tiempo+=lista.get(i).tiempo;
                        mejorNota+=lista.get(i).nota;
                        mejor(mejorNota);
                        backtrack(tiempo, mejorNota);
                        usada[i] = false;
                        tiempo -= lista.get(i).tiempo;
                        mejorNota -= lista.get(i).nota;
                    }
                }
            }
            //System.out.println(mejorNota);
    }

    public static void main(String[] args) {
        actividades a = new actividades(6, 50);
        a.lista.add(a.new actividad(15, 30));
        a.lista.add(a.new actividad(13, 14));
        a.lista.add(a.new actividad(11, 25));
        a.lista.add(a.new actividad(17, 21));
        a.lista.add(a.new actividad(12, 10));
        a.lista.add(a.new actividad(19, 5));

        a.voraz();
        a.mostrarVector();
        a.backtrack(0, 0);
        System.out.println(a.notaMejor);

    }
}
