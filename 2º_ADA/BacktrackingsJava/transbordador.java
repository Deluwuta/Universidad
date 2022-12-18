public class transbordador {
    int capacidad;
    int nGrupos;
    int vector[];
    boolean usados[];

    int cont, mejorCont;
    boolean enc = false;

    public transbordador(int capacidad, int nGrupos){
        this.capacidad = capacidad;
        this.nGrupos = nGrupos;
        cont = 0;
        mejorCont = 0;
        vector = new int[nGrupos];
        usados = new boolean[nGrupos];
    }

    public void mostrarVector(){
        for(int i = 0; i < nGrupos; i++){
            System.out.print(vector[i] + " ");
            System.out.println(usados[i]);
        }
    }

    public void inicializarVector(boolean usados[]){
        for(int i = 0; i < usados.length; i++)
            usados[i] = false;
    }

    boolean mejorSol(int cont){
        if(cont > mejorCont) mejorCont = cont;

        if(mejorCont == capacidad) return true;
        else return false;
    }
/*
    public void backtrack(int cont, int mejorCont) {
        for (int i = 0; i < nGrupos && !enc; i++) {
            if (!usados[i])
                if (cont + vector[i] <= capacidad) {
                    usados[i] = true;
                    cont += vector[i];
                    if (mejorSol(cont))
                        enc = true;
                    else {
                        backtrack(cont, mejorCont);
                        cont -= vector[i];
                        usados[i] = false;
                    }
                }
        }
    }
*/
    public boolean backtrack(int cont, int mejorCont) {
        if (mejorSol(cont))
            return true;
        else {
            for (int i = 0; i < nGrupos && !enc; i++) {
                if (!usados[i])
                    if (cont + vector[i] <= capacidad) {
                        usados[i] = true;
                        cont += vector[i];
                        if (backtrack(cont, mejorCont))
                            return true;
                        cont -= vector[i];
                        usados[i] = false;
                    }
            }
            return false;
        }
    }
    

    public static void main(String[] args) {
        transbordador t = new transbordador(25, 8);
        t.vector[0] = 20;
        t.vector[1] = 20;
        t.vector[2] = 15;
        t.vector[3] = 20;
        t.vector[4] = 4;
        t.vector[5] = 4;
        t.vector[6] = 4;
        t.vector[7] = 2;

        t.inicializarVector(t.usados);
        if(t.backtrack(t.cont, t.mejorCont)) System.out.println("Puta");
        else System.out.println("Tu madre");
        System.out.println(t.capacidad-t.mejorCont);
    }
}
