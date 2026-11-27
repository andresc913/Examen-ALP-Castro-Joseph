# Análisis del problema

## Entrada
- Opción del menú.
- Código, nombre y tres calificaciones enteras (0 a 20).
- Código a buscar.
- Archivo datos/estudiantes.txt.

## Proceso
- Validar que código y nombre no estén vacíos ni tengan punto y coma, que el código no se repita y que las notas estén en el rango.
- Promedio = (n1 + n2 + n3) / 3, sin división entera.
- Nota máxima y mínima recorriendo el arreglo.
- Estado: APROBADO si el promedio >= 11 (variante A), si no REPROBADO.
- Reporte: contar aprobados, acumular promedios, hallar el mayor y el menor, ordenar con burbuja.
- Guardar y leer el archivo.

## Salida
- Listado, resultado de búsqueda, reporte académico, mensajes de error y archivo actualizado.

## Algoritmo general
1. Cargar el archivo, si existe.
2. Mostrar el menú y leer la opción.
3. Ejecutar la opción con switch.
4. Repetir hasta elegir 6.
5. Guardar y salir.

## Clases
- Estudiante: datos y cálculos de un estudiante.
- GestionAcademica: lista de estudiantes, búsqueda, orden y reporte.
- GestorArchivos: lectura y escritura.
- Main: menú, captura y validación de datos.
