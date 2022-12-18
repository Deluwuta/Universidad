#include <iostream>
#include <vector>

using namespace std;

void mostrarSolucion(vector<bool> verga, int reparto){
    for(int i = 0; i < verga.size(); i++){
        cout << "El objeto " << i << " va a la alforja ";
        if(verga[i]) cout << "1." << endl;
        else cout << "2." << endl;
    }
    cout << "Diferencia de pesos entre ambas: " << reparto << endl;
}

    void mejorSol(vector<bool> &verga, vector<bool> usados, vector<int> pesos, int &reparto){
        int cont1, cont2;
        cont1 = cont2 = 0;
        for(int i = 0; i < usados.size(); i++){
            if(usados[i]) cont1 += pesos[i];
            else cont2 += pesos[i];
        }
        if(abs(cont1 - cont2) < reparto){
            reparto = abs(cont1 - cont2);
            verga = usados;
        }
        
        //if(reparto == 0) mostrarSolucion();
    }

void backtrack(int etapa, int &reparto, int numObjetos, vector<bool> usados, vector<bool> &solFin, vector<int> pesos){
    if(etapa == numObjetos){
        mejorSol(solFin, usados, pesos, reparto);
    }
    else{
        for(int i = 0; i < 2; i++){
            usados[etapa] = i;
            backtrack(etapa+1, reparto, numObjetos, usados, solFin, pesos);
        }
    }
}

int main(){
    
    int numObjetos = 15;
    int reparto = 99999;
    vector<int> pesos(numObjetos);
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
    
    vector<bool> usados (numObjetos, false);
    vector<bool> solFin (numObjetos, false);
    
    backtrack(0, reparto, numObjetos, usados, solFin, pesos);
    mostrarSolucion(solFin, reparto);
    
    return 0;
}