/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include <stdio.h>
#include <stdio_ext.h>
#include <bits/stdc++.h>

using namespace std;

void mostrarVector(vector<int> vector, int bestEficiencia){
    for(int i = 0; i < vector.size(); i++)
        cout << vector[i] << " ";
    cout << endl << bestEficiencia*2 << endl;
}

void mejor(vector<int> &arrayAsientos, vector<int> asientosParcial, int &afinidadTotal, int afinidadParcial){
    if(afinidadParcial > afinidadTotal){
        afinidadTotal = afinidadParcial;
        arrayAsientos = asientosParcial;
    }
}

bool aceptable(int N, int i, vector<int> asientosParcial){
    for(int j = 0; j < N; j++)
        if(asientosParcial[j] == i) return false;
    return true;
}

void backtrack(int N, int etapa, vector<int> &arrayAsientos, vector<int> &asientosParcial, vector<vector<int>> matrix, int &afinidadTotal, int &afinidadParcial){
    if(etapa == N){
        afinidadParcial += matrix[asientosParcial[0]][asientosParcial[etapa-1]];
        mejor(arrayAsientos, asientosParcial, afinidadTotal, afinidadParcial);
        afinidadParcial -= matrix[asientosParcial[0]][asientosParcial[etapa-1]];
    }
    else{
        for(int i = 0; i < N; i++){
            if(aceptable(N, i, asientosParcial)){
                asientosParcial[etapa] = i;
                afinidadParcial += matrix[i][asientosParcial[etapa-1]];

                backtrack(N, etapa+1, arrayAsientos, asientosParcial, matrix, afinidadTotal, afinidadParcial);

                afinidadParcial -= matrix[i][asientosParcial[etapa-1]];
                asientosParcial[etapa] = -1;
            }
       }
    }
}

void rellenarMatriz(string cad, vector<int> &vaux){
    getline(cin, cad);
    istringstream os(cad);
    int h = 0;
    while(os >> h)
        vaux.push_back(h);
}

int main()
{
    int N;
    cin >> N;
    
    vector<int> arrayAsientos(N, -1);
    vector<int> asientosParcial(N, -1);
    arrayAsientos[0] = 0;
    asientosParcial[0] = 0;
    
    int afinidadTotal = 0;
    int afinidadParcial = 0;
    
    vector<vector<int>> matrix;
    int cont = 0;
    
    if(1 <= N && N <= 12){
        do{
            string cad;
            vector<int> vaux;
            __fpurge(stdin);
            rellenarMatriz(cad, vaux);
            matrix.push_back(vaux);
            cont++;
        } while(cont < N);
    
    backtrack(N, 1, arrayAsientos, asientosParcial, matrix, afinidadTotal, afinidadParcial);
    mostrarVector(arrayAsientos, afinidadTotal);
    }
    
    return 0;
}