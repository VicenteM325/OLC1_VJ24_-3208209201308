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
    private LinkedList<Instruccion> instruccionesIfVerdadero;
    private LinkedList<Instruccion> instruccionesElse;

    // Constructor para la producción IF PAR_A EXPRESION:a PAR_C LLAVE1 INSTRUCCIONES:b LLAVE2 ELSE LLAVE1 INSTRUCCIONES:c LLAVE2
    public If(Instruccion condicion, LinkedList<Instruccion> instruccionesIfVerdadero, LinkedList<Instruccion> instruccionesElse, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIfVerdadero = instruccionesIfVerdadero;
        this.instruccionesElse = instruccionesElse;
    }

        // Constructor para la producción IF PAR1 EXPRESION:a PAR2 LLAVE1 INSTRUCCIONES:b LLAVE2
    public If(Instruccion condicion, LinkedList<Instruccion> instruccionesIfVerdadero, int linea, int columna) {
        super(new Tipo(TipoDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIfVerdadero = instruccionesIfVerdadero;
        this.instruccionesElse = null; 
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        var cond = this.condicion.interpretar(arbol, tabla);
        if (cond instanceof Errores) {
            return cond;
        }

        // La condicion es Booleano
        if (this.condicion.tipo.getTipo() != TipoDato.BOOLEANO) {
            return new Errores("SEMANTICO", "INVALIDA EXPRESION", this.linea, this.columna);
        }

        var newTabla = new TablaSimbolos(tabla);
        if ((boolean) cond) {
            // Cuando la condicion es verdadera se ejecuta
            for (var i : this.instruccionesIfVerdadero) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }
            }
        } else if (this.instruccionesElse != null) {
            // Se ejecuta cuando exite el else
            for (var i : this.instruccionesElse) {
                var resultado = i.interpretar(arbol, newTabla);
                if (resultado instanceof Errores) {
                    arbol.errores.add((Errores) resultado);
                }
            }

        }

        return null;
    }

}
    
    
