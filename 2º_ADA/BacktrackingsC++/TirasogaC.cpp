/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
/* A ver el tirasoga. Tenemos N participantes y hay que ver todas las 
* combinaciones posibles.
qué significa esto jsjs
Tenemos 2 equipos y hemos de ver que el peso sea la ostia con solo 1 persona
de diferencia a lo mucho
backtracking : Etapa: Mover a una persona de un equipo a otro I guess
               Also iniciarlizar todos los randoms en un equipo supongo
Se necesita un vector que lleve los pesos
Otro vector para marcar en qué equipo andamos
Y como necesitamos la mejor combinación supongo que un vector mejor o algo
*/

#include <iostream>
#include <vector>
#include <stdlib.h>
#include <time.h>

using namespace std;

#define N 9

bool equilibrado(vector <bool> teamsP)
{
    int cont1 = 0; 
    int cont2 = 0;
    
    for(int i = 0; i < N; i++)
    {
        if(teamsP[i])
            cont2++;
        else
            cont1++;
    }
    return abs(cont1-cont2) <= 1;
}

int diferencia(vector <int> personas, vector <bool> team)
{
    int cont1 = 0;
    int cont2 = 0;
    
    for(int i = 0; i < N; i++)
    {
        if(team[i])
            cont2 += personas[i];
        else
            cont1 += personas[i];
    }
    return abs(cont2-cont1);
}

void mostrarSol(vector <int> personas, vector <bool> team)
{
    for(int i = 0; i < N; i++)
    {
        if(team[i])
            cout << "La persona " << i << " va al equipo 2\n";
        else
            cout << "La persona " << i << " va al equipo 1\n";
    }
    cout << "La diferencia de peso entre ambos equipos es: " << diferencia(personas, team) << endl;
}

void validarSol(vector<int> personas, vector <bool> teamsP, vector <bool> &teamsF, bool &sol)
{
    if(equilibrado(teamsP))
    {
        if(diferencia(personas, teamsP) < diferencia(personas, teamsF))
        {
            for(int i = 0; i < N; i++)
                teamsF[i] = teamsP[i];
            if(diferencia(personas, teamsF) == 0)
                sol = true;
        }
    }
}

void backtrack(int etapa, vector<int> personas, vector <bool> teamsP, vector <bool> &teamsF, bool &sol)
{
    for(int i = 0; i < 2 && !sol; i++)
    {
        if(i == 0)
            teamsP[etapa] = 0;
        else
            teamsP[etapa] = 1;
        if(etapa == N-1)
            validarSol(personas, teamsP, teamsF, sol);
        else
            backtrack(etapa+1, personas, teamsP, teamsF, sol);
    }
}

void randomizar(vector <int> &personas)
{
    srand(time(NULL));
    cout << "Los pesos de los humanos apuntados son: \n";
    for(int i = 0; i < N; i++)
    {
        personas[i] = rand() % 90 + 40; // Saca un número entre 40 y 129
        cout << personas[i] << " ";
    }
    cout << endl;
    
}

int main()
{
    vector <int> personas(N);
    randomizar(personas);
    vector <bool> equiposParcial(N, false);
    vector <bool> equiposFinal(N, false);
    bool solucion = false;
    
    backtrack(0, personas, equiposParcial, equiposFinal, solucion);
    mostrarSol(personas, equiposFinal);
    
}
