package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Simbolo;
import simbolo.TablaSimbolos;
import simbolo.Tipo;

/**
 *
 * @author vicente
 */
public class Declaracion extends Instruccion {
    public String identificador;
    public Instruccion valor;

    public Declaracion(String identificador, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.valor = valor;
    }
    
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        var valorInterpretado = this.valor.interpretar(arbol, tabla);
        
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        
        if(this.valor.tipo.getTipo() != this.tipo.getTipo()){
            return new Errores("SEMANTICO", "Tipos erroneos", this.linea, this.columna);
        }
        
        Simbolo s = new Simbolo(this.tipo, this.identificador, valorInterpretado);
        
        boolean creacion = tabla.setVariable(s);
        if(!creacion){
            return new Errores("SEMANTICO", "Variable existente", this.linea, this.columna);
        }
        return null;
    }
}
