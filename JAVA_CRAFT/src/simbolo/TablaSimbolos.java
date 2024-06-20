package simbolo;

import java.util.HashMap;

/**
 *
 * @author vicente
 */
public class TablaSimbolos {
    
    private TablaSimbolos tablaAnterior;
    private HashMap<String,Object> tablaActual;
    private String nombre;
    
    
    public TablaSimbolos(){
        this.tablaActual = new HashMap<>();
        this.nombre = "";
        
    }

    public TablaSimbolos(TablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual = new HashMap<>();
        this.nombre = "";
    }

    public TablaSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public void setTablaAnterior(TablaSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean setVariable(Simbolo simbolo){
        Simbolo busqueda = (Simbolo) this.tablaActual.get(simbolo.getId().toLowerCase());
        
        if(busqueda == null){
            this.tablaActual.put(simbolo.getId().toLowerCase(), simbolo);
            return true;
        }
        return false;
    }
    
    public Simbolo getVariable(String id){
        Simbolo busqueda = (Simbolo) this.tablaActual.get(id.toLowerCase());
        
        if(busqueda != null){
            return busqueda;
        }
        return null;
                        
    }
    
}
