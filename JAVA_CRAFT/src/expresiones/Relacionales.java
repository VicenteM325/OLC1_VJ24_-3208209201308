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
public class Relacionales extends Instruccion {
    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresRelacionales comparacion;


    //Constructor de cualquier operación
    public Relacionales(Instruccion operando1, Instruccion operando2, OperadoresRelacionales comparacion, int linea, int col) {
        super(new Tipo(TipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.comparacion = comparacion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }

        return switch (comparacion) {
            case IGUALACION ->
                this.igualacion(opIzq, opDer);
            case DIFEREN -> 
                this.diferen(opIzq, opDer);
            case MENOR_QUE -> 
                this.menor_que(opIzq, opDer);
            case MENOR_IGUAL_QUE ->
                this.menor_igual_que(opIzq, opDer);
            case MAYOR_QUE ->
                this.mayor_que(opIzq, opDer);
            case MAYOR_IGUAL_QUE ->
                this.mayor_igual_que(opIzq, opDer);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.columna);
        };
    }

    public Object igualacion(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 == (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 == (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 == (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación  erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 == (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 == (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 == (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 == (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 == (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 == (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 == (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return op1.toString() ==  op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparación errónea", this.linea, this.columna);

            }
        }
    }
    
    public Object diferen(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 != (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 != (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación  erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 != (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 != (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 != (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 != (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 != (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 != (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return op1.toString() !=  op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparación errónea", this.linea, this.columna);

            }
        }
    }
    
    public Object menor_que(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 < (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double)(int) op1 < (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 < (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación  erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 < (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 < (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 < (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 < (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 < (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 < (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 < (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return op1.toString().equals(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparación errónea", this.linea, this.columna);

            }
        }
    }
    public Object menor_igual_que(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 <= (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 <= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 <= (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación  erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 <= (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 <= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 <= (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 <= (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 <= (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 <= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 <= (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return op1.toString().compareTo(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparación errónea", this.linea, this.columna);

            }
        }
    }
    public Object mayor_que(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 > (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 > (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 > (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación  erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 > (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 > (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 > (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 > (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 > (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 > (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 > (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return op1.toString().compareTo(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparación errónea", this.linea, this.columna);

            }
        }
    }
    public Object mayor_igual_que(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 >= (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (int) op1 >= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 >= (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación  erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 >= (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 >= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 >= (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.BOOLEANO -> {
                switch (tipo2) {
                    case TipoDato.BOOLEANO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (double) op1 >= (int) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 >= (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 >= (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return (char) op1 >= (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                switch (tipo2){
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.BOOLEANO);
                        return op1.toString().compareTo(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Comparación erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Comparación errónea", this.linea, this.columna);

            }
        }
    }
}
