package simbolo;

/**
 *
 * @author vicente
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    private boolean mutabilidad;

    public Simbolo(boolean mutabilidad,Tipo tipo, String id) {
        this.mutabilidad = mutabilidad;
        this.tipo = tipo;
        this.id = id;
        valor = null;
    }

    public Simbolo(boolean mutabilidad,Tipo tipo, String id, Object valor) {
        this.mutabilidad = mutabilidad;
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public boolean isMutabilidad() {
        return mutabilidad;
    }

    public void setMutabilidad(boolean mutabilidad) {
        this.mutabilidad = mutabilidad;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
    
}
