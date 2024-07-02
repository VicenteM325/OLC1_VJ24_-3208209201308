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
    public boolean mutabilidad;
    
    
    public Declaracion(String identificador, Tipo tipo, boolean mutabilidad, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.valor = null;
        this.mutabilidad = mutabilidad;
    }
    
    public Declaracion(String identificador, Instruccion valor, Tipo tipo, boolean mutabilidad, int linea, int columna) {
        super(tipo, linea, columna);
        this.identificador = identificador;
        this.valor = valor;
        this.mutabilidad = mutabilidad;
    }
    
    

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        
        Object valorInterpretado = null;
        
        if (this.valor != null) {
            valorInterpretado = this.valor.interpretar(arbol, tabla);
            
            if (valorInterpretado instanceof Errores) {
                return valorInterpretado;
            }

            if (this.valor.tipo.getTipo() != this.tipo.getTipo()) {
                return new Errores("SEMANTICO", "Tipos erroneos", this.linea, this.columna);
            }
        }
        
        Simbolo simbolo;
        if (valorInterpretado != null) {
            simbolo = new Simbolo(this.mutabilidad, this.tipo, this.identificador, valorInterpretado);
        } else {
            simbolo = new Simbolo(this.mutabilidad, this.tipo, this.identificador);
        }
        
        boolean creacion = tabla.setVariable(simbolo);
        if (!creacion) {
            return new Errores("SEMANTICO", "Variable existente", this.linea, this.columna);
        }
        
        return null;
    }
}
