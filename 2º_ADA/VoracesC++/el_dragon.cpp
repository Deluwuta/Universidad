#include <bits/stdc++.h>
#include <iostream>
#include <stdio.h>
#include <vector>
using namespace std;

bool matarDragon(int N, int P, vector <float> ci, vector <float> ai){
  if(N > P){
    return false;
  }
  else{
    sort(ci.begin(), ci.end());
    sort(ai.begin(), ai.end());
    while(!ci.empty()){
      if(ci[0] <= ai[0]){
        ci.erase(ci.begin());
        ai.erase(ai.begin());
      }
      else{
        ai.erase(ai.begin());
        if(ci.size() > ai.size())
          return false;
      }
    }
    return true;
  }
}

void mostrarVector(vector<float> killers){
  for(int i = 0; i < killers.size(); i++)
    cout << killers[i] << ", ";
  cout << endl;
}

int main(){
  vector<float>ci;
  vector<float>ai;
  float x;

  cout << "A continuación se pide que introduzcas las alturas de las cabezas y las personas, introduce 0 para salir\n";

  do{
    cout << "Introduce la altura de una cabeza: ";
    cin >> x; 
    if(x > 0)
      ci.push_back(x);
  } while(x > 0);

  cout << endl;

  do{
    cout << "Introduce la altura de una persona: ";
    cin >> x; 
    if(x > 0)
      ai.push_back(x);
  } while(x > 0);

  cout << "\nMostramos ambos vectores para comprobar que son correctos: \n";
  mostrarVector(ci);
  mostrarVector(ai);

  if(matarDragon(ci.size(), ai.size(), ci, ai)){
    cout << "El dragón puede ser matado" << endl;
  }
  else{
    cout << "El dragón no será matado" << endl;
  }
  return 0;
}
