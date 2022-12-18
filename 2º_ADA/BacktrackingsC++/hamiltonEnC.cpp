#include <iostream>
#include <vector>

using namespace std;

void mostrarSol(vector<int> vectorSol){
    for(int i = 0; i < vectorSol.size(); i++)
        cout << vectorSol[i] <<  " ";
    cout << endl;
}

bool validarSol(int N, vector<int> vectorSol, vector<vector<int>> grafo){
    if(vectorSol[0] == vectorSol[N]){
        for(int i = 1; i <= N; i++)
            if(grafo[vectorSol[i-1]][vectorSol[i]] == 0) return false;
    }
    else 
        return false;
        
    return true;
}

bool aceptable(int N, int etapa, int num, vector<int> vectorSol, vector<vector<int>> grafo){
    if(etapa == N && num == vectorSol[0]){
        if(grafo[vectorSol[etapa-1]][vectorSol[0]]) return true;
        else return false;
    }

    if(grafo[vectorSol[etapa-1]][num] == 1){ // Si existe arco
        for(int i = 0; i < etapa; i++){
            if(vectorSol[i] == num) return false;
        }
    }
    else return false;

    return true;
}

bool backtrack(int N, int etapa, vector<int> &vectorSol, vector<vector<int>> grafo){
    if (etapa == N + 1)
            //return validarSol(N, vectorSol, grafo);
            return true;
        else {
            for (int i = 0; i < N; i++)
                if (aceptable(N, etapa, i, vectorSol, grafo)) {
                    vectorSol[etapa] = i;
                    if (backtrack(N, etapa+1, vectorSol, grafo))
                        return true;
                    else {
                        vectorSol[etapa] = -1;
                    }
                }
            return false;
        }
}

int main()
{
    /*
    vector<vector<int>> grafo = {{0, 1, 0, 0, 0, 1},
                                 {1, 0, 1, 0, 0, 0},
                                 {0, 1, 0, 1, 1, 1},
                                 {0, 0, 1, 0, 1, 0},
                                 {0, 0, 1, 1, 0, 0},
                                 {1, 0, 1, 0, 0, 0}};
                                 
    vector<vector<int>> grafo = {{0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 0
                                 {1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 1
                                 {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, // 2
                                 {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, // 3
                                 {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, // 4
                                 {0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 5
                                 {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 6
                                 {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 7
                                 {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, // 8
                                 {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // 9
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0}, // 10
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0}, // 11
                                 {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0}, // 12
                                 {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0}, // 13
                                 {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0}, // 14
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0}, // 15
                                 {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0}, // 16
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0}, // 17
                                 {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, // 18
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
    
    vector<vector<int>> grafo = {{0, 1, 1, 0, 0, 0, 7, 0},
                                 {1, 0, 1, 0, 0, 0, 0, 1},
                                 {1, 1, 0, 1, 0, 1, 0, 0},
                                 {0, 0, 1, 0, 1, 0, 0, 0},
                                 {0, 0, 0, 1, 0, 1, 0, 0},
                                 {0, 0, 1, 0, 1, 0, 1, 0},
                                 {1, 0, 0, 0, 0, 1, 0, 1},
                                 {0, 1, 0, 0, 0, 0, 1, 0}};
                                 
    */
    
    vector<vector<int>> grafo = {{0, 1, 0, 0, 1},
                                 {1, 0, 1, 1, 1},
                                 {0, 1, 0, 1, 0},
                                 {0, 1, 1, 0, 0},
                                 {1, 1, 0, 0, 0}};
                                 
    int N = grafo.size();
    //cout << grafo.size();
    
    vector<int> vectorSol (N+1, -1);
    vectorSol[0] = 0;
    
    bool kek = false;
    kek = backtrack(N, 1, vectorSol, grafo);
    
    if(kek)
        mostrarSol(vectorSol);
    else
        cout << "Perra mala" << endl;
        
    return 0;
}



