package analisis;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

// codigo usuario
%{
     public LinkedList<Errores> listaErrores = new LinkedList<>();
%}
// Definiciones Iniciales
%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

// Declaraciones de caracteristicas de jflex
%cup
%class scanner // nombre de la clase
%public // acceso de la clase
%line // conteo de lineas
%column // conteo de columnas
%char // conteo de caracteres
%full // reconocimiento de caracteres
%ignorecase // quitar la distincion entre mayusculas y minusculas(case insensitive)


// definir los simbolos del sistema
PAR_A = "("
PAR_C = ")"

//Relacionales especiales
IGUALACION = "=="
DIFEREN = "!="
MENOR_IGUAL_QUE = "<="
MAYOR_IGUAL_QUE = ">="
IGUAL = "="
//Aritmeticos
POTENCIA = "**"
MAS = "+"
MENOS = "-"
MULTIPLICACION = "*"
DIVISION = "/"
MODULO = "%"

//Relacionales
MENOR_QUE = "<"
MAYOR_QUE = ">"

//Logicos
AND = "&&"
OR = "\|\|"
XOR = "\^"
NOT = "!"

FINCADENA = ";"
DOS_PUNTOS = ":"
LLAVE1 = "{"
LLAVE2 = "}"

//Expresiones regulares
BLANCOS = [\ \r\t\n\f]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
ID=[a-zA-z][a-zA-Z0-9_]*
CADENA = [\"][^\"]*[\"]
CARACTER = [\']([^\'])*[\']

//Comentarios
COMENTARIO_LINEA = "//".*
COMENTARIO_MULTIPLE = "/*"[^*]*"*"([^/]|[^*]*"*"[^/])*"*/"


// palabras reservadas
PRINTLN="println"
INT = "int"
DOUBLE = "double"
STRING = "string"
IF = "if"
TRUE = "true"
FALSE = "false"
BOOL = "bool"
VAR = "var"
CONST = "const"
CHAR = "char"




%%
//PALABRAS RESERVADAS
<YYINITIAL>  {PRINTLN} {return new Symbol(sym.PRINTLN, yyline, yycolumn, yytext());}
<YYINITIAL>  {INT} {return new Symbol(sym.INT, yyline, yycolumn, yytext());}
<YYINITIAL>  {DOUBLE} {return new Symbol(sym.DOUBLE, yyline, yycolumn, yytext());}
<YYINITIAL>  {STRING} {return new Symbol(sym.STRING, yyline, yycolumn, yytext());}
<YYINITIAL>  {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn, yytext());}
<YYINITIAL>  {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn, yytext());}
<YYINITIAL>  {IF} {return new Symbol(sym.IF, yyline, yycolumn, yytext());}
<YYINITIAL>  {BOOL} {return new Symbol(sym.BOOL, yyline, yycolumn, yytext());}
<YYINITIAL>  {VAR} {return new Symbol(sym.VAR, yyline, yycolumn, yytext());}
<YYINITIAL>  {CONST} {return new Symbol(sym.CONST, yyline, yycolumn, yytext());}
<YYINITIAL>  {CHAR} {return new Symbol(sym.CHAR, yyline, yycolumn, yytext());}


//LOGICOS
<YYINITIAL>  {AND} {return new Symbol(sym.AND, yyline, yycolumn, yytext());}
<YYINITIAL>  {OR} {return new Symbol(sym.OR, yyline, yycolumn, yytext());}
<YYINITIAL>  {XOR} {return new Symbol(sym.XOR, yyline, yycolumn, yytext());}
<YYINITIAL>  {NOT} {return new Symbol(sym.NOT, yyline, yycolumn, yytext());}

<YYINITIAL> {ID} {return new Symbol(sym.ID, yyline, yycolumn, yytext());}

<YYINITIAL>  {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());}
<YYINITIAL>  {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());}
<YYINITIAL>  {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1,cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn, cadena);
    }

<YYINITIAL>  {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1,caracter.length()-1);
    return new Symbol(sym.CARACTER, yyline, yycolumn, caracter);
    }


<YYINITIAL>  {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext());}
<YYINITIAL>  {DOS_PUNTOS} {return new Symbol(sym.DOS_PUNTOS, yyline, yycolumn, yytext());}
<YYINITIAL>  {PAR_A} {return new Symbol(sym.PAR_A, yyline, yycolumn, yytext());}
<YYINITIAL>  {PAR_C} {return new Symbol(sym.PAR_C, yyline, yycolumn, yytext());}
<YYINITIAL>  {LLAVE1} {return new Symbol(sym.LLAVE1, yyline, yycolumn, yytext());}
<YYINITIAL>  {LLAVE2} {return new Symbol(sym.LLAVE2, yyline, yycolumn, yytext());}

//ARITMETICOS
<YYINITIAL>  {MAS} {return new Symbol(sym.MAS, yyline, yycolumn, yytext());}
<YYINITIAL>  {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn, yytext());}
<YYINITIAL>  {POTENCIA} {return new Symbol(sym.POTENCIA, yyline, yycolumn, yytext());}
<YYINITIAL>  {MULTIPLICACION} {return new Symbol(sym.MULTIPLICACION, yyline, yycolumn, yytext());}
<YYINITIAL>  {DIVISION} {return new Symbol(sym.DIVISION, yyline, yycolumn, yytext());}
<YYINITIAL>  {MODULO} {return new Symbol(sym.MODULO, yyline, yycolumn, yytext());}

//RELACIONALES
<YYINITIAL>  {IGUALACION} {return new Symbol(sym.IGUALACION, yyline, yycolumn, yytext());}
<YYINITIAL>  {DIFEREN} {return new Symbol(sym.DIFEREN, yyline, yycolumn, yytext());}
<YYINITIAL>  {MENOR_IGUAL_QUE} {return new Symbol(sym.MENOR_IGUAL_QUE, yyline, yycolumn, yytext());}
<YYINITIAL>  {MAYOR_IGUAL_QUE} {return new Symbol(sym.MAYOR_IGUAL_QUE, yyline, yycolumn, yytext());}
<YYINITIAL>  {MENOR_QUE} {return new Symbol(sym.MENOR_QUE, yyline, yycolumn, yytext());}
<YYINITIAL>  {MAYOR_QUE} {return new Symbol(sym.MAYOR_QUE, yyline, yycolumn, yytext());}
<YYINITIAL>  {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn, yytext());}


<YYINITIAL> {COMENTARIO_LINEA} {}
<YYINITIAL> {COMENTARIO_MULTIPLE} {}

<YYINITIAL>  {BLANCOS} {}




<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO","El caracter "+
                yytext() + " no pertenece al lenguaje ", yyline, yycolumn));
}

