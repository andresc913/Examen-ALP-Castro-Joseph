import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorArchivos {
    private static final String RUTA = "datos/estudiantes.txt";

    public boolean guardar(ArrayList<Estudiante> estudiantes) {
        new File("datos").mkdirs();
        // FileWriter sin modo append: reescribe todo y no duplica
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {
            for (Estudiante e : estudiantes) {
                int[] n = e.getNotas();
                bw.write(e.getCodigo() + ";" + e.getNombre() + ";" + n[0] + ";" + n[1] + ";" + n[2]);
                bw.newLine();
            }
            return true;
        } catch (IOException ex) {
            System.out.println("Error al guardar: " + ex.getMessage());
            return false;
        }
    }

    // Devuelve false si el archivo existe pero no se pudo leer completo
    public boolean cargar(GestionAcademica gestion) {
        boolean correcto = true;
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) {
                    continue;
                }
                if (!procesarLinea(linea, gestion)) {
                    System.out.println("Linea invalida: " + linea);
                    correcto = false;
                }
            }
        } catch (FileNotFoundException ex) {
            if (new File(RUTA).exists()) {
                System.out.println("No se pudo abrir el archivo: " + ex.getMessage());
                return false;
            }
            System.out.println("No existe el archivo de datos. Se inicia sin registros.");
        } catch (IOException ex) {
            System.out.println("Error al leer: " + ex.getMessage());
            return false;
        }
        return correcto;
    }

    private boolean procesarLinea(String linea, GestionAcademica gestion) {
        String[] partes = linea.split(";");
        if (partes.length != 5 || partes[0].trim().isEmpty() || partes[1].trim().isEmpty()) {
            return false;
        }
        int[] notas = new int[3];
        try {
            for (int i = 0; i < 3; i++) {
                notas[i] = Integer.parseInt(partes[i + 2].trim());
                if (notas[i] < 0 || notas[i] > 20) {
                    return false;
                }
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return gestion.registrar(new Estudiante(partes[0].trim(), partes[1].trim(), notas));
    }
}
