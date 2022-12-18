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

void printGrid(int N, vector<vector<int>> matrix){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++)
                cout << matrix[i][j] << " ";
            cout << endl;
        }
    }
    
bool aceptable(int N, vector<vector<int>> matrix, int x, int y, int num){
        for(int i = 0; i < N; i++){
            if(matrix[x][i] == num) return false;
            if(matrix[i][y] == num) return false;
        }

        int parX = x - x%3; // Si x es 5, 5 - 2 = 3 --> 3, 4, 5
        int parY = y - y%3;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++)
                if(matrix[parX+i][parY+j] == num) return false;
        }
        return true;
    }

bool backtrack(int N, vector<vector<int>> &matrix, int x, int y){
        if(y == N && x == N-1) return true;
        
        if(y == N){
            x++;
            y = 0;
        }

        if(matrix[x][y] != 0) return backtrack(N, matrix, x, y+1);
            for(int i = 1; i <= N; i++){
                if(aceptable(N, matrix, x, y, i)){
                    matrix[x][y] = i;
                    if(backtrack(N, matrix, x, y+1)) return true;
                    matrix[x][y] = 0;
                }
            }
            return false;
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
    int N = 9;
    
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
    
    bool kek = false;
    kek = backtrack(N, matrix, 0, 0);
    
    if(kek) printGrid(N, matrix);
    else cout << "NO SOLUCION";

    return 0;
}