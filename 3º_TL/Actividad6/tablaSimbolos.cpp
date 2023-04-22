#include "tablaSimbolos.h"

string mostrarBool(bool val) {
    if(val) return "true";
    return "false";
}

void obtenerTipoSalida(struct valor v){
    string salida = "";
    switch (v.tipoValor) {
        case 0:
            cout << "real" << "\t" << v.valorReal << endl;
            break;

        case 1: 
            cout << "entero" << "\t" << v.valorEntero << endl;
            break;

        case 2: 
            cout << "lógico" << "\t" << mostrarBool(v.valorBool) << endl;
            break;

        default: 
            cout << "'-'" << endl;
            break;
    }
}

void mostrarTabla(map<string, valor> tabla) {
    for (auto itr = tabla.begin(); itr != tabla.end(); itr++){
        cout << itr->first << "\t";
        obtenerTipoSalida(itr->second);
    }
}
