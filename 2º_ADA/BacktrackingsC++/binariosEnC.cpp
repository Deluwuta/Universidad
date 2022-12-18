#include <iostream>
#include <vector>

using namespace std;

void mostrarSol(int n, vector<int> v){
    for(int i = 0; i < n; i++)
        cout << v[i];
    cout << endl;
}

void backtrack(int n, int etapa, vector<int> v, int cont){
    if(etapa == n){
        if(cont%2 == 0) mostrarSol(n, v);
    }
    else{
        for(int i = 0; i < 2; i++){
            v[etapa] = i;
            if(i == 0) cont++;
            backtrack(n, etapa+1, v, cont);
            if(i == 0) cont--;
        }
    }
}

int main(){
    int n = 0;
    cin >> n;
    
    vector<int> v(n, 0);
    backtrack(n, 0, v, 0);
    
    return 0;
}