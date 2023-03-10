#include "dateEater.h"

/* Programa el cual engancha un String que se corresponde con una fecha y hace
 * cosas nazis con ello*/

string yearFormater(int year) {
  if (year > 30)
    return "19" + to_string(year);
  return "20" + to_string(year);
}

bool monthValid(int month, int day) {
  if (month > 12)
    return false;
  else {
    if (month == 2 && day > 27) // Febrero
      return false;
    if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31)
      return false;
  }
  return true;
}

bool dayValid(int day) {
  if (day > 31)
    return false;
  return true;
}

bool validDate(int day, int month) {
  return dayValid(day) && monthValid(month, day);
}

void dateProcesor(string text, char sep, int &day, int &month, int &year) {
  stringstream ss(text);
  string word;
  int i = 0;
  int partes[3]; // La fecha solo tendra 3 partes kekw

  while (!ss.eof()) {
    getline(ss, word, sep);
    partes[i] = stoi(word);
    i++;
  }
  day = partes[0];
  month = partes[1];
  year = partes[2];
}

void dateOutput(int day, int month, int year) {
  if (!validDate(day, month))
    cout << "Fecha no valida" << endl;
  else {
    vector<string> meses = {"Enero",      "Febrero", "Marzo",     "Abril",
                            "Mayo",       "Junio",   "Julio",     "Agosto",
                            "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    cout << day << " de " << meses[month - 1] << " de " << yearFormater(year)
         << endl;
  }
}

void dateFormater(string fecha) {
  int day, month, year;
  dateProcesor(fecha, '/', day, month, year);
  dateOutput(day, month, year);
}
