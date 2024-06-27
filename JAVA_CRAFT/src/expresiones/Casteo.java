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
public class Casteo extends Instruccion {
    private TipoDato tipoDestino;
    private Instruccion expresion;
    
    public Casteo(TipoDato tipoDestino, Instruccion expresion, Tipo tipo, int linea, int columna){
        super(tipo, linea, columna);
        this.tipoDestino = tipoDestino;
        this.expresion = expresion;      
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object valor = expresion.interpretar(arbol, tabla);
        if (valor instanceof Errores) {
            return valor;
        }

        switch (tipoDestino) {
            case ENTERO:
                if (valor instanceof Double) {
                    return ((Double) valor).intValue();
                } else if (valor instanceof Character) {
                    return (int) ((Character) valor).charValue();
                }
                break;
            case DECIMAL:
                if (valor instanceof Integer) {
                    return ((Integer) valor).doubleValue();
                } else if (valor instanceof Character) {
                    return (double) ((Character) valor).charValue();
                }
                break;
            case CARACTER:
                if (valor instanceof Integer) {
                    return (char) ((Integer) valor).intValue();
                } else if (valor instanceof Double) {
                    return (char) ((Double) valor).intValue();
                }
                break;
            default:
                return new Errores("SEMANTICO", "Casteo inválido: tipo de cambio no establecido", this.linea, this.columna);
        }

        return new Errores("SEMANTICO", "Casteo inválido: tipo de origen no establecido", this.linea, this.columna);
        
        
    }
}
