import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class becas {
    public class datos{
        int id, mesIni, mesFin, sueldo, total;
        public datos(int id, int mesIni, int mesFin, int sueldo){
            this.id = id;
            this.mesIni = mesIni;
            this.mesFin = mesFin;
            this.sueldo = sueldo;
            total = (mesFin-mesIni+1)*sueldo;
        }
    }

    List<datos> lista;
    int mejorCont;
    boolean visitados[] = {false, false, false, false, false};

    public becas(){
        lista = new ArrayList<>();
        mejorCont = 0;
    }

    boolean aceptable(int beca){
        Boolean[] meses = new Boolean[13]; // Vemos los meses que tenemos ocupados hasta ahora
        Arrays.fill(meses, false);
        for(int i = 0; i < 5; i++){
            if(visitados[i]){
                for(int j = lista.get(i).mesIni; j <= lista.get(i).mesFin; j++){
                    meses[j] = true;
                }
            }
        }
        
        for(int i = lista.get(beca).mesIni; i <= lista.get(beca).mesFin; i++)
            if(meses[i]) return false; // Si los meses de la nueva beca están ocupados retornamos false
        return true;
    }

    void mejorSol(int contPar){
        if(mejorCont < contPar) mejorCont = contPar;
    }

    public void backtrack(int contPar){ // No es mejor aquel que ocupa todos los meses
        for(int i = 0; i < 5; i++){
            if(!visitados[i]) // Compruebo si estoy usando la beca i de la lista
                if(aceptable(i)){ // Compruebo que no se superpone ningún mes
                    visitados[i] = true; // Usada 
                    contPar += lista.get(i).total; // Anotamos
                    mejorSol(contPar);
                    backtrack(contPar);
                    contPar -= lista.get(i).total;
                    visitados[i] = false;
                }
        }
    }

    public static void main(String[] args) {
        becas b = new becas();

        b.lista.add(b.new datos(1, 1, 10, 175)); // 1750
        b.lista.add(b.new datos(2, 8, 12, 100)); // 500
        b.lista.add(b.new datos(3, 5, 6, 150)); // 300
        b.lista.add(b.new datos(4, 1, 4, 300)); // 1200
        b.lista.add(b.new datos(5, 5, 11, 100)); // 700

        b.backtrack(b.mejorCont);
        System.out.println(b.mejorCont);

    }
}
