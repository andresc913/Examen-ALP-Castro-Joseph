# Casos de prueba (variante A, límite 11)

| Caso | Entrada | Resultado esperado | Resultado obtenido | Estado |
|---|---|---|---|---|
| CP01 | E001, Ana Torres, 15, 18, 12 | Se registra | "Estudiante registrado." | Correcto |
| CP02 | Código E001 otra vez | Se rechaza | "Ese codigo ya esta registrado." | Correcto |
| CP03 | E002, primera nota 25 y luego 8 | Pide la nota otra vez | "Calificacion invalida..." y acepta 8 | Correcto |
| CP04 | E004, Carlos Vega, 11, 11, 11 | Promedio 11.00, APROBADO | Promedio 11.00, APROBADO | Correcto |
| CP05 | Buscar E004 | Muestra sus datos | Muestra notas, promedio, max, min y estado | Correcto |
| CP06 | Buscar E999 | Mensaje informativo | "No existe un estudiante con ese codigo." | Correcto |
| CP07 | Opción 4 sin registros | Mensaje, sin error | "No hay estudiantes para generar el reporte." | Correcto |
| CP08 | Salir, volver a abrir y listar | Recupera los registros sin duplicar | Lista 4 estudiantes, el archivo mantiene 4 líneas | Correcto |
