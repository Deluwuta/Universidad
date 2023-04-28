#include "tablaSimbolos.h"

string mostrarBool(bool val) {
    if(val) return "true";
    return "false";
}

bool existeIdentificador(map<string, valor> tabla, string id){
    auto it = tabla.find(id);
    if (it != tabla.end())
        return true;
    return false;
}

int obtenerTipoValor(map<string, valor> tabla, string id){
    auto it = tabla.find(id);
    if (it != tabla.end()){
        return it->second.tipoValor;
    }
    return -1;
}

string obtenerNombreTipo(int valor){
    string salida = "";
    switch (valor) {
        case 0:
            return "real";
            break;

        case 1: 
            return "entero";
            break;

        case 2: 
            return "lógico";
            break;

        default: 
            return "'-'";
            break;
    }
}

bool insertarEnTabla(map<string, valor> &tabla, string id, struct valor v, int n_linea){
    int aux = obtenerTipoValor(tabla, id);

    if(v.tipoValor != aux && aux != -1){
        cout << "Error semántico en la instrucción " << n_linea << ": la variable " << id << " de tipo " << obtenerNombreTipo(aux) << " no se le puede asignar un valor " << obtenerNombreTipo(v.tipoValor) << endl;
        return false;
    }
    if(v.tipoValor == aux)
        tabla.erase(id);
    tabla.insert({id, v});
    return true;
}

void insertFloat(map<string, valor> &tabla, string id, float num, int n_linea){
    struct valor v;
    v.valorReal = num;
    v.tipoValor = 0;

    insertarEnTabla(tabla, id, v, n_linea);
}

void insertInt(map<string, valor> &tabla, string id, int num, int n_linea){
    struct valor v;
    v.valorEntero = num;
    v.tipoValor = 1;

    insertarEnTabla(tabla, id, v, n_linea);
}

void insertBool(map<string, valor> &tabla, string id, bool num, int n_linea){
    struct valor v;
    v.valorBool = num;
    v.tipoValor = 2;

    insertarEnTabla(tabla, id, v, n_linea);
}

void obtenerTipoSalida(struct valor v, ostream& theStream){
    string salida = "";
    switch (v.tipoValor) {
        case 0:
            theStream << "real" << "\t" << v.valorReal << endl;
            break;

        case 1: 
            theStream << "entero" << "\t" << v.valorEntero << endl;
            break;

        case 2: 
            theStream << "lógico" << "\t" << mostrarBool(v.valorBool) << endl;
            break;

        default: 
            theStream << "'-'" << endl;
            break;
    }
}

void mostrarTabla(map<string, valor> tabla, ostream& theStream) {
    for (auto itr = tabla.begin(); itr != tabla.end(); itr++){
        theStream << itr->first << "\t";
        obtenerTipoSalida(itr->second, theStream);
    }
}
