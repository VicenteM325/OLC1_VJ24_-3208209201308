package analisis;

import java_cup.runtime.Symbol;

%%

// codigo usuario
%{
%}
// Definiciones Iniciales
%init{
    yyline = 1;
    yycolumn = 1;
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
FINCADENA = ";"
/*MAS = "+"
MENOS = "-"
MULTIPLICACION = "*"
DIVISION = "/" */
BLANCOS = [\ \r\t\n\f]+
ENTERO = [0-9]+
DECIMAL = [0-9]+"."[0-9]+
CADENA = [\"][^\"]*[\"]

// palabras reservadas
IMPRIMIR = "imprimir"

%%

<YYINITIAL>  {IMPRIMIR} {return new Symbol(sym.IMPRIMIR, yyline, yycolumn, yytext());}
<YYINITIAL>  {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn, yytext());}
<YYINITIAL>  {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn, yytext());}
<YYINITIAL>  {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1,cadena.length()-1);
    return new Symbol(sym.CADENA, yyline, yycolumn, cadena);
    }

<YYINITIAL>  {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn, yytext());}
/*<YYINITIAL>  {MAS} {return new Symbol(sym.MAS, yyline, yycolumn, yytext());}
<YYINITIAL>  {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn, yytext());}
<YYINITIAL>  {MULTIPLICACION} {return new Symbol(sym.MULTIPLICACION, yyline, yycolumn, yytext());}
<YYINITIAL>  {DIVISION} {return new Symbol(sym.DIVISION, yyline, yycolumn, yytext());}*/
<YYINITIAL>  {PAR_A} {return new Symbol(sym.PAR_A, yyline, yycolumn, yytext());}
<YYINITIAL>  {PAR_C} {return new Symbol(sym.PAR_C, yyline, yycolumn, yytext());}
<YYINITIAL>  {BLANCOS} {}



/* Estado cadena

<CADENA> {
    [\"]    {String tmp = cadena;
            cadena = "";
            yybegin(YYINITIAL);
            return new Symbol(sym.CADENA, yyline, yycolumn, tmp);}

    [^\"]  {cadena += yytext();}
}
*/
