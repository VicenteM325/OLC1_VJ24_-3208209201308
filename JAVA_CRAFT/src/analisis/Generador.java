package analisis;

/**
 *
 * @author vicente
 */
public class Generador {

    public static void main(String[] args) {
        generarCompilador();
    }

    public static void generarCompilador() {
        try {
            String ruta = "src/analisis/";
            String Flex[] = {ruta + "Lexico.jflex", "-d", ruta};
            jflex.Main.generate(Flex);
            String Cup[] = {"-destdir", ruta, "-parser", "parser", ruta + "Sintactico.cup"};
            java_cup.Main.main(Cup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
