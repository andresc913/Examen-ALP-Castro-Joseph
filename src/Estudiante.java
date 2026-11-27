public class Estudiante {
    // Variante A. Cambiar aquí si te asignaron otra (B=12 ... H=18)
    public static final int LIMITE_APROBACION = 11;

    private String codigo;
    private String nombre;
    private int[] notas;

    public Estudiante(String codigo, String nombre, int[] notas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.notas = notas.clone();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getNotas() {
        return notas.clone();
    }

    public double calcularPromedio() {
        int suma = 0;
        for (int nota : notas) {
            suma += nota;
        }
        return (double) suma / notas.length;
    }

    public int obtenerNotaMaxima() {
        int max = notas[0];
        for (int nota : notas) {
            if (nota > max) {
                max = nota;
            }
        }
        return max;
    }

    public int obtenerNotaMinima() {
        int min = notas[0];
        for (int nota : notas) {
            if (nota < min) {
                min = nota;
            }
        }
        return min;
    }

    public String obtenerEstado() {
        if (calcularPromedio() >= LIMITE_APROBACION) {
            return "APROBADO";
        }
        return "REPROBADO";
    }

    public String resumen() {
        return String.format("%s | %s | Notas: %d, %d, %d | Promedio: %.2f | Max: %d | Min: %d | %s",
                codigo, nombre, notas[0], notas[1], notas[2], calcularPromedio(),
                obtenerNotaMaxima(), obtenerNotaMinima(), obtenerEstado());
    }
}
