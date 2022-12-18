import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class filmoteca {

    public class pelicula {
        int comienzo, finalizacion, duracion;
        public pelicula(int c, int f, int d){
            comienzo = c;
            finalizacion =f;
            duracion = d;
        }
    }

    int tam;
    List<pelicula> peliculas;
    
    public filmoteca(int tam){
        this.tam = tam;
        peliculas = new ArrayList<>();
    }

    public class Sortbyroll implements Comparator<pelicula> {
        // Used for sorting in ascending order of
        // roll number
        public int compare(pelicula a, pelicula b)
        {
            return a.duracion - b.duracion;
        }
    }

    public void voraz(){
        Sortbyroll s = new Sortbyroll();
        int cont = 0; int num = 0;
        Collections.sort(peliculas, s);
        for(int i = 1; i < tam; i++)
            if(peliculas.get(i).comienzo >= peliculas.get(i-1).finalizacion)
                cont += peliculas.get(i).duracion;
        
        System.out.println("Se pueden ver " + num + " películas que consumen " + cont + " minutos");
    }

    public static void main(String[] args) {
        Random r = new Random();
        filmoteca f = new filmoteca(20);

        for(int i = 0; i < f.tam; i++){

        }
        f.voraz();
    }
}

// Sin acabar, me da palo
