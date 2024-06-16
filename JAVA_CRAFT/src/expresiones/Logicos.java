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
public class Logicos extends Instruccion {
   private Instruccion operador1;
    private Instruccion operador2;
    private OperadoresLogicos operacion;
    private Instruccion operadorUnico;

    public Logicos(Instruccion operadorUnico, OperadoresLogicos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operacion = operacion;
        this.operadorUnico = operadorUnico;
    }

    public Logicos(Instruccion operador1, Instruccion operador2, OperadoresLogicos operacion, int linea, int columna) {
        super(new Tipo(TipoDato.BOOLEANO), linea, columna);
        this.operador1 = operador1;
        this.operador2 = operador2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operadorUnico != null) {
            Unico = this.operadorUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            opIzq = this.operador1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operador2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }

        return switch (operacion) {
            case AND ->
                this.and(opIzq, opDer);
            case OR ->
                this.or(opIzq, opDer);
            case NOT ->
                this.not(Unico);
            case XOR ->
                this.xor(opIzq, opDer);
            default ->
                new Errores("SEMANTICO", "Operador lógico inválido", this.linea, this.columna);
        };
    }

    public Object and(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return (boolean) op1 && (boolean) op2;
        }
        return new Errores("SEMANTICO", "Operación AND inválida", this.linea, this.columna);
    }

    public Object or(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return (boolean) op1 || (boolean) op2;
        }
        return new Errores("SEMANTICO", "Operación OR inválida", this.linea, this.columna);
    }

    public Object not(Object op1) {
        if (op1 instanceof Boolean) {
            return !(boolean) op1;
        }
        return new Errores("SEMANTICO", "Operación NOT inválida", this.linea, this.columna);
    }

    public Object xor(Object op1, Object op2) {
        if (op1 instanceof Boolean && op2 instanceof Boolean) {
            return (boolean) op1 ^ (boolean) op2;
        }
        return new Errores("SEMANTICO", "Operación XOR inválida", this.linea, this.columna);
    }


}
