import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static GestionAcademica gestion = new GestionAcademica();
    private static GestorArchivos archivos = new GestorArchivos();
    private static boolean cargaCorrecta;

    public static void main(String[] args) {
        cargaCorrecta = archivos.cargar(gestion);
        String opcion;
        do {
            mostrarMenu();
            opcion = leerLinea();
            switch (opcion) {
                case "1":
                    registrar();
                    break;
                case "2":
                    gestion.listar();
                    break;
                case "3":
                    buscar();
                    break;
                case "4":
                    gestion.generarReporte();
                    break;
                case "5":
                    guardar();
                    break;
                case "6":
                    guardar();
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Opcion incorrecta.");
            }
        } while (!opcion.equals("6"));
    }

    private static void mostrarMenu() {
        System.out.println("=====================================");
        System.out.println(" SISTEMA AULALOGIC");
        System.out.println("=====================================");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Generar reporte academico");
        System.out.println("5. Guardar registros");
        System.out.println("6. Salir");
        System.out.println("=====================================");
        System.out.print("Seleccione una opcion: ");
    }

    // Si se cierra la entrada, se guarda y se termina
    private static String leerLinea() {
        if (!sc.hasNextLine()) {
            guardar();
            System.exit(0);
        }
        return sc.nextLine().trim();
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = leerLinea();
            if (!texto.isEmpty() && !texto.contains(";")) {
                return texto;
            }
            System.out.println("No puede estar vacio ni contener punto y coma.");
        }
    }

    private static int leerNota(int numero) {
        while (true) {
            System.out.print("Calificacion " + numero + " (0-20): ");
            try {
                int nota = Integer.parseInt(leerLinea());
                if (nota >= 0 && nota <= 20) {
                    return nota;
                }
            } catch (NumberFormatException ex) {
                // se muestra el mismo mensaje de abajo
            }
            System.out.println("Calificacion invalida, ingrese un entero entre 0 y 20.");
        }
    }

    private static void registrar() {
        String codigo = leerTexto("Codigo: ");
        if (gestion.buscar(codigo) != null) {
            System.out.println("Ese codigo ya esta registrado.");
            return;
        }
        String nombre = leerTexto("Nombre completo: ");
        int[] notas = new int[3];
        for (int i = 0; i < notas.length; i++) {
            notas[i] = leerNota(i + 1);
        }
        gestion.registrar(new Estudiante(codigo, nombre, notas));
        System.out.println("Estudiante registrado.");
    }

    private static void buscar() {
        Estudiante e = gestion.buscar(leerTexto("Codigo a buscar: "));
        if (e == null) {
            System.out.println("No existe un estudiante con ese codigo.");
        } else {
            System.out.println(e.resumen());
        }
    }

    private static void guardar() {
        // Evita sobrescribir un archivo que no se leyo completo
        if (!cargaCorrecta) {
            System.out.println("No se guarda: revise datos/estudiantes.txt y reinicie.");
            return;
        }
        if (archivos.guardar(gestion.getEstudiantes())) {
            System.out.println("Registros guardados.");
        }
    }
}
