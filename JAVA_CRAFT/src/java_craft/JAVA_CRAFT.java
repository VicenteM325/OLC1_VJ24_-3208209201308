package java_craft;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.TablaSimbolos;

/**
 *
 * @author vicente
 */
public class JAVA_CRAFT {


    public static void main(String[] args) {
            try{
                String texto = "imprimir(\"Mi cadena\"+1);"
                        + "imprimir(5/5.5);";
                scanner s = new scanner(new BufferedReader(new StringReader(texto)));
                parser p = new parser(s);
                var resultado = p.parse();
                var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
                var tabla = new TablaSimbolos();
                tabla.setNombre("Global");
                ast.setConsola("");
                for(var a : ast.getInstrucciones()){
                    var res = a.interpretar(ast, tabla);
                }
                System.out.println(ast.getConsola());
                
            } catch(Exception ex){
                System.out.println("Algo salio mal");
                
            }
    }
    
}
