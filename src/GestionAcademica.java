import java.util.ArrayList;

public class GestionAcademica {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public boolean registrar(Estudiante e) {
        if (buscar(e.getCodigo()) != null) {
            return false;
        }
        estudiantes.add(e);
        return true;
    }

    public Estudiante buscar(String codigo) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return e;
            }
        }
        return null;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public void listar() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : estudiantes) {
            System.out.println(e.resumen());
        }
    }

    // Ordenamiento burbuja sobre una copia, de mayor a menor promedio
    public ArrayList<Estudiante> ordenarPorPromedio() {
        ArrayList<Estudiante> copia = getEstudiantes();
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {
                if (copia.get(j).calcularPromedio() < copia.get(j + 1).calcularPromedio()) {
                    Estudiante aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }

    public void generarReporte() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes para generar el reporte.");
            return;
        }
        int aprobados = 0;
        double suma = 0;
        Estudiante mayor = estudiantes.get(0);
        Estudiante menor = estudiantes.get(0);

        for (Estudiante e : estudiantes) {
            double promedio = e.calcularPromedio();
            suma += promedio;
            if (e.obtenerEstado().equals("APROBADO")) {
                aprobados++;
            }
            // Con > y < en caso de empate queda el primero registrado
            if (promedio > mayor.calcularPromedio()) {
                mayor = e;
            }
            if (promedio < menor.calcularPromedio()) {
                menor = e;
            }
        }

        int total = estudiantes.size();
        double general = suma / total;

        System.out.println("--- REPORTE ACADEMICO ---");
        System.out.println("Total de estudiantes: " + total);
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Reprobados: " + (total - aprobados));
        System.out.printf("Promedio general: %.2f%n", general);
        System.out.printf("Porcentaje de aprobados: %.2f%%%n", aprobados * 100.0 / total);
        System.out.println("Promedio mas alto: " + mayor.resumen());
        System.out.println("Promedio mas bajo: " + menor.resumen());

        System.out.println("Con promedio inferior al general:");
        int inferiores = 0;
        for (Estudiante e : estudiantes) {
            if (e.calcularPromedio() < general) {
                System.out.println("  " + e.resumen());
                inferiores++;
            }
        }
        if (inferiores == 0) {
            System.out.println("  Ninguno");
        }

        System.out.println("Listado de mayor a menor promedio:");
        for (Estudiante e : ordenarPorPromedio()) {
            System.out.println("  " + e.resumen());
        }
    }
}
