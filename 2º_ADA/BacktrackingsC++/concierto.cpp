#include <iostream>
#include <sstream>
#include <vector>
#include <string>
#include <stdio_ext.h>
#include <bits/stdc++.h>

using namespace std;

void comprobarSol(vector<int> v){ // Comprobamos si hemos usado todas las obras
    bool sol = true;
    for(int i : v){ // Bucle for-each
        if(i != 1) {
            cout << "NO" << endl;
            sol = false;
        }
    }
    if(sol) cout << "SI" << endl;
}

void mostrarVector(vector<int> v){
    for(int i = 0; i < v.size(); i++){
        cout << v[i] << " ";
    }
}

void voraz(int n, int dm, int nob, vector<int> v){
    int acum = 0;
    vector<int> usados(nob); // Uso int pero funciona como un vector de bool's
    fill(usados.begin(), usados.end(), 0); // Función que llena el vector desde el límite inferior al superior con el valor pasado

    for(int i = 0; i < n; i++){
        acum = 0;
        for(int j = 0; j < nob; j++){
            acum += v[j];
            if(acum <= dm && usados[j] == 0) usados[j] = 1;
            else acum-=v[j];
        }
    }
    comprobarSol(usados);
}

void rellenarVector(int nob, vector<int> &vector, string cad){
    if(nob > 0){
        getline(cin, cad);
        istringstream os(cad);
        int h = 0;
        while(os >> h)
            vector.push_back(h);
    }
}

int main()
{
    int n, dm, nob;
    int j = 0;
    vector<int> vector;
    cin >> n;
    cin >> dm;
    cin >> nob;
    
    string cad;
     __fpurge(stdin); // Usado para limpiar el buffer de teclado
    rellenarVector(nob, vector, cad);
    
    sort(vector.begin(), vector.end(), greater<int>()); // Función que ordena el vector desde el límite inferior al superior de la forma dicha (greater, mayor a menor)

    voraz(n, dm, nob, vector);
    
    return 0;
}