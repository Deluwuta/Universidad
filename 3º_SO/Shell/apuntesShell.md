### Cositas shell
TODAS LAS VARIABLES EN SHELL SON GLOBALES, 
ES DECIR, si llamas a funciones dentro de un bucle con i
y dicha funcion tiene un bucle con i también, el programita
te irá mal

TAMBIÉN TODO EN BASH ES UN STRING

#### Cositas importantes
$# -> Número de parametros
$? -> Resultado de la ultima funcion
$@ -> Todos los parametros pasados
>/dev/null -> Redirección al sumidero
1>&2 -> Redirección al error estandar
$(basename $0) -> Nombre del fichero

#### Redirecciones
2> lo que hace es redirigir el ERROR (standard error)
1> redirige el OUTPUT (standard output). De normal ">" también lo hace

Ejemplo: find carpeta -type f 2> errores 1> todoGucci

#### Sort command
-n pa numeros
sort filename
Solo funciona con saltos de líneas '-'

#### Cut command (nunca me acuerdo de cómo va)
cut -c file, corta chars
cut -c-9 file, corta 9 chars, del 0 al 8
cut -c9- path/to/a/fucking/file, corta al partir del noveno

#### Tail command (cositas)
Si se quiere sacar todas las lineas excepto 3, por ejemplo, quieres mostrar un fichero de 10 líneas sin las 3 primeras:
tail -n +4 fichero

#### Ejecuciones de comandos
En general abuso de $(()) para las operaciones
number=$(( 2 + 4 ))
number=`expr 2 + 4`
Las comillas de mierda se usan para ejecutar comandos y asignar el resultado a una variable
El dollar el mejor para operaciones aritmeticas pochas y tirando, para no hacer el lio

#### If's statements 
Los if se pueden usar de mil formas
if [ cositas ]; then
if [[ cositas ]]; then
if (( cositas )); then

Si se usa el if entre [ ] los valores de dentro son STRINGS
Si se usa el if entre ( ) los valores de dentro son NUMÉRICOS

(Recomendable usar single [] por portabilidad)
([[ ]] es más seguro)

if [ ! -f file ]
Esa mierda te dice si file no es un fichero (sirviendo también para comprobar su existencia)

if [[ -e file ]] hace lo mismo, pero más literal (en sentido de que literalmente te dice si existe)
if [ -e 'file' ] hace lo mismo que con 2 corchetes

Con [ ] hay que usar cosas tipo
Equals -> -eq
Less than -> -lt
Greater equal -> -ge
Not equals -> ! n1 -eq n2
Para comparar STRINGS y NÚMEROS

Con [[ ]] puedes usar
Equals -> =
Not equals -> !=
Para comparar STRINGS

Si quieres comparar números sin usar flags pochas es (( ))
Esto es importante con números de varias cifras

#### Bucles
Pa leer de ficheros lo más cómo es

while read field1 field2 restOfLine; do
  some shit
done < inputFile

Pa leer de texto o líneas pochas de algún comando (ls) idk
for variable in inputText; do
  do shit
done

##### Rangos en el for
for i in {1..5}
for i in $(seq 0 $number)
