#include <iostream>
#include <vector>

using namespace std;

void mostrarSol(vector<int> pesos, vector<bool> sol, int mejorValor){
  for(int i = 0; i < sol.size(); i++){
    cout << "La persona " << i << " va al equipo ";

    if(sol[i]) cout << "1. ";
    else cout << "2. ";

    cout << "Peso: " << pesos[i] << endl;
  }
  cout << "La diferencia de peso entre ambos equipos es: " << mejorValor << endl;
}

void mejorSol(int N, vector<int>pesos, vector<bool> reparto, vector<bool> &solFinal, int &mejorPeso){
  int contPesos1, contPesos2, contT1, contT2;
  contPesos1 = contPesos2 = contT1 = contT2 = 0;

  for(int i = 0; i < N; i++){
    if(reparto[i]){
      contPesos1 += pesos[i];
      contT1++;
    }
    else{
      contPesos2 += pesos[i];
      contT2++;
    }
  }
  if(abs(contT1-contT2) <= 1)
    if(abs(contPesos1-contPesos2) < mejorPeso){
      mejorPeso = abs(contPesos1-contPesos2);
      solFinal = reparto;
    }
}

void backtrack(int N, vector<int> pesos, vector<bool>reparto, int etapa, vector<bool> &solFinal, int &mejorPeso){
  if(etapa == N){
    mejorSol(N, pesos, reparto, solFinal, mejorPeso);
  }
  else{
    for(int i = 0; i < 2; i++){
      reparto[etapa] = i;
      backtrack(N, pesos, reparto, etapa+1, solFinal, mejorPeso);
    }
  }
}

int main(){
  int N = 9;
  vector<int> pesos = {90, 68, 109, 85, 115, 101, 68, 101, 40};
  vector<bool> reparto(N, false);
  vector<bool> solFinal(N, false);
  int mejorValor = 99999; // Algo mu grande

  backtrack(N, pesos, reparto, 0, solFinal, mejorValor);
  mostrarSol(pesos, solFinal, mejorValor);

  return 0;
}
