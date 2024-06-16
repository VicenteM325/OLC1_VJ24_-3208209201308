package carga_archivo;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CargaArchivo {

    public ArrayList<String> leerFichero(File archivo, JTextPane textPane) throws FileNotFoundException, IOException, BadLocationException {
        ArrayList<String> lineas = new ArrayList<>();
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;

        StyledDocument doc = textPane.getStyledDocument();
        doc.remove(0, doc.getLength()); // Limpia el contenido existente

        SimpleAttributeSet regularStyle = new SimpleAttributeSet(); // Estilo por defecto
        SimpleAttributeSet redStyle = new SimpleAttributeSet();
        StyleConstants.setForeground(redStyle, Color.BLACK);
        // Agrega otros estilos si es necesario

        while ((linea = br.readLine()) != null) {
            try {
                AttributeSet style = regularStyle; // Por defecto, no se aplican estilos
                // Aplica estilos según tus criterios aquí
                if (linea.contains("algo")) {
                    style = redStyle;
                }

                doc.insertString(doc.getLength(), "\n" + linea, style);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }

        fr.close();
        return lineas;
    }
}
