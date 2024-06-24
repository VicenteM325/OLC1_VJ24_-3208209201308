package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author vicente
 */
public class If extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public If(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }
    
    
    
    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        var cond = this.condicion.interpretar(arbol, tabla);
        if(cond instanceof Errores){
            return cond;
        }
        //Validar que la condicion sea de tipo bool
        if(this.condicion.tipo.getTipo() != TipoDato.BOOLEANO){
            return new Errores("SEMANTICO", "Expresion invalida", this.linea, this.columna);
        }
        
        var newTabla = new TablaSimbolos(tabla);
        if((boolean) cond){
            for(var i : this.instrucciones){
                var resultado = i.interpretar(arbol, newTabla);
            }
        }
        return null;
    }
    
    
}
