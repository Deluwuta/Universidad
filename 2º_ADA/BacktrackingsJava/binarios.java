public class binarios {
    int N;
    int vectorBi[];

    public binarios(int N){
        this.N = N;
        vectorBi = new int[N];
    }

    void mostrarSol(){
        for(int i : vectorBi)
            System.out.print(i + " ");
        System.out.println();
    }
    
    public void backtrack(int etapa, int cont){
        if(etapa == N){
            if(cont%2 == 0) mostrarSol();
        }
        else{
            for(int i = 0; i < 2; i++){
                vectorBi[etapa] = i;
                if(i == 0){
                    cont++;
                    backtrack(etapa+1, cont);
                    cont--;
                }
                else backtrack(etapa+1, cont);
            }
        }
    }

    public static void main(String[] args) {
        binarios b = new binarios(4);
        b.backtrack(0, 0);
    }
}