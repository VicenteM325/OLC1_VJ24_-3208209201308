package instrucciones;

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
public class AsignacionVar extends Instruccion {
    private String id;
    private Instruccion exp;

    public AsignacionVar(String id, Instruccion exp, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var variable = tabla.getVariable(id);
        
        if(variable==null){
            return new Errores("SEMANTICO", "Varible no existente para asignacion", this.linea, this.columna);
        }
        if (!variable.isMutabilidad()) {
            return new Errores("SEMANTICO", "No se puede modificar el valor de una constante", this.linea, this.columna);
        }
        
       var newValor = this.exp.interpretar(arbol, tabla);
       if(newValor instanceof Errores){
           return newValor;
       }
       
       if(variable.getTipo().getTipo()!= this.exp.tipo.getTipo()){
           return new Errores("SEMANTICO", "Tipos erroneos en asignacion", this.linea, this.columna);
       }
       this.tipo.setTipo(variable.getTipo().getTipo());
       variable.setValor(newValor);
       return null;
    }
    
    
}
