#include <iostream>
#include <map>
#include <fstream>
#include <ostream>
#include <string.h>

using namespace std;

struct valor {
  int tipoValor; // 0 = Float; 1 = Int; 2 = Bool
  float valorReal;
  int valorEntero;
  bool valorBool;
};

void mostrarTabla(map<string, valor> tabla, FILE* theStream);
void insertFloat(map<string, valor> &tabla, string id, float num, int n_linea);
void insertInt(map<string, valor> &tabla, string id, int num, int n_linea);
void insertBool(map<string, valor> &tabla, string id, bool num, int n_linea);
int obtenerTipoValor(map<string, valor> tabla, string id);
bool existeIdentificador(map<string, valor> tabla, string id);
