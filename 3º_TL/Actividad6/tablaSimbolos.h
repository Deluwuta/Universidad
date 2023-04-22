#include <iostream>
#include <map>
#include <string.h>

using namespace std;

struct valor {
  int tipoValor; // 0 = Float; 1 = Int; 2 = Bool
  float valorReal;
  int valorEntero;
  bool valorBool;
};

void mostrarTabla(map<string, valor> tabla);
