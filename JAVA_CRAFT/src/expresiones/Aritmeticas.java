package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.*;


public class Aritmeticas extends Instruccion {

    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    //Constructor exclusivo de negacion
    public Aritmeticas(Instruccion operandoUnico, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(TipoDato.ENTERO), linea, col);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    //Constructor de cualquier operación
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(TipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, TablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }

        return switch (operacion) {
            case SUMA ->
                this.suma(opIzq, opDer);
            case RESTA -> 
                this.resta(opIzq, opDer);
            case MULTIPLICACION -> 
                this.multiplicacion(opIzq, opDer);
            case DIVISION ->
                this.division(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            case MODULO ->
                this.modulo(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.columna);
        };
    }

    public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 + (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case TipoDato.CADENA -> {
                        this.tipo.setTipo(TipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CADENA -> {
                this.tipo.setTipo(TipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);

            }
        }
    }
    
     public Object resta(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 - (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 - (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (char) op1 - (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (char) op1 - (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Resta erronea", this.linea, this.columna);

            }
        }
    }
     
     public Object multiplicacion(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 * (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return (int) op1 * (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 * (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (char) op1 * (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (char) op1 * (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Multiplicacion erronea", this.linea, this.columna);

            }
        }
    }
    
      public Object division(Object op1, Object op2) {
                    
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        
        if((int) op2 == 0 || (double) op2 == 0.0){
            System.out.println("Syntax error");
            return new Errores("SEMANTICO", "Syntax Error", this.linea, this.columna);
        } 

        switch (tipo1) {
            case TipoDato.ENTERO -> {
    
                switch (tipo2) {
                    
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 / (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 / (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 / (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 / (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 / (double) op2;
                    }
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 / (char) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.CARACTER -> {
                switch (tipo2) {
                    case TipoDato.CARACTER -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (char) op1 / (double) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (char) op1 / (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Division erronea", this.linea, this.columna);

            }
        }
    }
      
    public Object potencia (Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.ENTERO);
                        return Math.pow((int)op1,(int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((int)op1,(double)op2);
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Potencia erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)op1,(int)op2);
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return Math.pow((double)op1,(double)op2);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Potencia erronea", this.linea, this.columna);
                    }
                }
            }
            
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);

            }
        }
    }
    
    public Object modulo(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();

        switch (tipo1) {
            case TipoDato.ENTERO -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 % (int) op2;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (int) op1 % (double) op2;
                    }
                    
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.columna);
                    }
                }
            }
            case TipoDato.DECIMAL -> {
                switch (tipo2) {
                    case TipoDato.ENTERO -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 % (int) op1;
                    }
                    case TipoDato.DECIMAL -> {
                        this.tipo.setTipo(TipoDato.DECIMAL);
                        return (double) op1 % (double) op2;
                    }
                   
                    default -> {
                        return new Errores("SEMANTICO", "Modulo erronea", this.linea, this.columna);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Modulo erronea", this.linea, this.columna);

            }
        }
    }

    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case TipoDato.ENTERO -> {
                this.tipo.setTipo(TipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case TipoDato.DECIMAL -> {
                this.tipo.setTipo(TipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.columna);
            }
        }
    }
}