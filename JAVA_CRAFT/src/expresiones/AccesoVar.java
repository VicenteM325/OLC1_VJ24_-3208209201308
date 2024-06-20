package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.TablaSimbolos;
import simbolo.Tipo;
import simbolo.TipoDato;

/**
 *
 * @author vicente
 */
public class AccesoVar extends Instruccion{
    
    private String id;

    public AccesoVar(String id, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
         var valor = tabla.getVariable(this.id);
         if(valor == null){
             return new Errores("SEMANTICA", "Variable no existente", this.linea, this.columna);
         }
         this.tipo.setTipo(valor.getTipo().getTipo());
         return valor.getValor();
    }
    
    
}
