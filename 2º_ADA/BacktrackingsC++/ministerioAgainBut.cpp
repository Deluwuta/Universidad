#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include <stdio.h>
#include <stdio_ext.h>
#include <bits/stdc++.h>

using namespace std;

void rellenarMatriz(string cad, vector<int> &vaux){
    getline(cin, cad);
    istringstream os(cad);
    int h = 0;
    while(os >> h)
        vaux.push_back(h);
}

void mostrarVector(vector<int> vector, int bestEficiencia){
    for(int i = 0; i < vector.size(); i++)
        cout << vector[i] << " ";
        cout << bestEficiencia;
}

void mejor(vector<int> &vecSol, vector<int> vecPar, int &bestEficiencia, int efiPar){
    if(efiPar > bestEficiencia){
        bestEficiencia = efiPar;
        vecSol = vecPar;
    }
}

bool aceptable(vector<int> vector, int tarea){
    for(int i = 0; i < vector.size(); i++)
        if(vector[i] == tarea) return false; // si trabajador "i" ya tiene esa tarea
    return true;
}

void backtrack(int N, int etapa, vector<int> &vecSol, vector<int> &vecPar, vector<bool> &visitados, vector<vector<int>> matrix, int &bestEficiencia, int &efiPar){
    if(etapa == N){
        mejor(vecSol, vecPar, bestEficiencia, efiPar);
    }
    else{
        for(int i = 0; i < N; i++){
            if(aceptable(vecPar, i)){
                if(!visitados[etapa]){
                    visitados[etapa] = true; // Trabajador usado
                    vecPar[etapa] = i; // Anotamos
                    efiPar += matrix[etapa][i]; // Sumamos

                    backtrack(N, etapa+1, vecSol, vecPar, visitados, matrix, bestEficiencia, efiPar);

                    efiPar -= matrix[etapa][i]; // Restamos
                    visitados[etapa] = false; // Trabajador libre
                    vecPar[etapa] = -1; // Desanotamos
                }
            }
        }
    }
}

int main()
{
    int N = 0;
    cin >> N;
    
    vector<int> vecSol(N, -1);
    vector<int> vecPar(N, -1);
    int bestEficiencia = 0;
    int efiPar = 0;
    vector<bool> visitados(N, false);
    vector<vector<int>> matrix;
    int cont = 0;
    
    do{
        string cad;
        vector<int> vaux;
         __fpurge(stdin);
        rellenarMatriz(cad, vaux);
        matrix.push_back(vaux);
        cont++;
    } while(cont < N);
    
    backtrack(N, 0, vecSol, vecPar, visitados, matrix, bestEficiencia, efiPar);
    mostrarVector(vecSol, bestEficiencia);

    return 0;
}