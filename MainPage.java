/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase MainPage
 * 
 * La clase MainPage representa la página principal del sistema de gestión de un albergue de animales.
 * Ofrece las opciones principales del menú para gestionar animales, voluntarios, adopciones, 
 * recursos, tareas y registros médicos, así como para acceder a los informes generados por el sistema.
 * 
 * @author Daniela Navas
 * Fecha de creación: 16/09/2024 
 * Última modificación: 03/11/2024
 */

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPage {

    private Scanner sc;/** Objeto de la clase Report utilizado para generar informes. */
    private Report report;

    /**
    * Constructor de la clase MainPage.
    * Inicializa la clase con un objeto de tipo Report.
    *
    * @param report el objeto Report que será asignado a esta clase
    */
    public MainPage(Report report) {
        this.report = report;
    }

    /**
     * Muestra las opciones principales del menú en la página principal.
     */
    public void displayOptions() {
        System.out.println(" ");
        System.out.println("****************************************************");
        System.out.println("***               PÁGINA PRINCIPAL               ***");
        System.out.println("** 1. Registrar Animal                            **");
        System.out.println("** 2. Registrar Voluntario                        **");
        System.out.println("** 3. Registrar Adoptante                         **");
        System.out.println("** 4. Registrar Adopción                          **");
        System.out.println("** 5. Registrar Recurso                           **");
        System.out.println("** 6. Actualizar Cantidad de Recurso              **");
        System.out.println("** 7. Registrar Tarea                             **");
        System.out.println("** 8. Marcar como completa una Tarea              **");
        System.out.println("** 9. Ingresar un Nuevo Registro Médico           **");
        System.out.println("** 10. Visualizar Record Médico                   **");
        System.out.println("** 11. Reportes del Albergue                      **");
        System.out.println("** 12. Reporte de Adopciones por Período          **");
        System.out.println("** 13. Estadísticas Mensuales de Adopciones       **");
        System.out.println("** 14. Salir                                      **");
        System.out.println("****************************************************");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra las opciones del menú de reportes.
     */
    public void displayOptionsReport() {
        System.out.println(" ");
        System.out.println("****************************************************");
        System.out.println("***            REPORTES DEL ALBERGUE             ***");
        System.out.println("** 1. Reporte de Adopciones                       **");
        System.out.println("** 2. Reporte General de Animales                 **");
        System.out.println("** 3. Reporte de Animales por Nivel de            **");
        System.out.println("**    Peligrosidad                                **");
        System.out.println("** 4. Reporte General de Adoptantes               **");
        System.out.println("** 5. Reporte de Adoptantes por Cantidad de       **");
        System.out.println("**    Animales Adoptados                          **");
        System.out.println("** 6. Reporte General de Voluntarios              **");
        System.out.println("** 7. Top 10 de Voluntarios con mayor cantidad    **");
        System.out.println("**    de horas de Voluntariado                    **");
        System.out.println("** 8. Reporte General de Recursos                 **");
        System.out.println("** 9. Reporte de Recursos bajos en cantidad       **");
        System.out.println("** 10. Reporte General de Tareas                  **");
        System.out.println("** 11. Reporte de Tareas sin Completar            **");
        System.out.println("** 12. Regresar                                   **");
        System.out.println("****************************************************");
        System.out.print("Seleccione una opción: ");
    }

    

    /**
     * Navega entre las opciones del menú principal y ejecuta las acciones correspondientes.
     *
     * @param option la opción seleccionada por el usuario
     * @param sc el objeto Scanner para capturar la entrada del usuario
     * @param main el objeto Main que contiene las acciones a ejecutar
     */
    public void navigate(int option, Scanner sc, Main main) {
        switch (option) {
            case 1:
                main.addAnimal(sc);              // Registrar un nuevo animal
                break;
            case 2: 
                main.addVolunteer(sc);           // Registrar un nuevo voluntario
                break;
            case 3:
                main.registerAdopter(sc);        // Registrar un nuevo adoptante
                break;
            case 4:
                main.registerAdoption(sc);       // Registrar una adopción
                break;
            case 5: 
                main.addResource(sc);       // Registrar un recurso
                break;
            case 6:
                main.updateResourceQuantity(sc); // Cambiar Cantidad de un Recurso
                break;
            case 7:
                main.registerTask(sc);           // Registrar una nueva tarea
                break;
            case 8:
                main.completeTask(sc);           // Marcar una tarea como completada
                break;
            case 9:
                main.registerMedicalRecord(sc);  // Ingresar un nuevo registro médico
                break;
            case 10:
                main.viewMedicalRecord(sc);      // Visualizar récord médico
                break;
            case 11:
                int reportOption = 0;           

                do {                             // Bucle principal para mostrar el menú hasta que el usuario seleccione la opción de regresar
                    this.displayOptionsReport(); // Muestra el menú de opciones
                    
                    try {
                        reportOption = sc.nextInt();  // Lee la opción seleccionada por el usuario
                        sc.nextLine();                // Limpia el buffer del scanner
                        
                        // Navega en el sistema según la opción seleccionada
                        this.navigateReport(reportOption); 
                    } catch (InputMismatchException e) {
                        System.out.println(" ");
                        System.out.println("==========================");
                        System.out.println("===        ERROR       ===");
                        System.out.println("= Ingrese un número      =");
                        System.out.println("= Selecciona otra opción =");
                        System.out.println("==========================");  // Mensaje de error en caso de entrada no válida
                        System.out.println(" ");
                        sc.nextLine();  // Limpia el buffer en caso de excepción
                    }
                    
                } while (reportOption != 12);  // Repite mientras no se elija la opción de regresar (7)

                break;
            case 14:
                main.saveAllData();
                System.out.println(" ");
                System.out.println("=============================");
                System.out.println("== SALIENDO DEL SISTEMA... ==");
                System.out.println("=============================");
                System.out.println(" ");
                System.exit(0);  // Salir del programa
                break;
            default:
                System.out.println(" ");
                System.out.println("==========================");
                System.out.println("===        ERROR       ===");
                System.out.println("= Opción no válida.      =");
                System.out.println("= Selecciona otra opción =");
                System.out.println("==========================");
                System.out.println(" ");
        }
    }

    /**
     * Navega entre las opciones del menú de reportes y ejecuta las acciones correspondientes.
     *
     * @param option la opción seleccionada por el usuario
     */
    public void navigateReport(int option) {
        switch (option) {
            case 1: // Reporte de Adopciones
                System.out.println(report.generateAdoptionReport());   
                break;
            case 2: // Reporte General de Animales
                System.out.println(report.generateAnimalStats());      
                break;
            case 3: // Reporte Animales por Peligrosidad
                System.out.println(report.generateDangerLevelReport());
                break;
            case 4: // Reporte General de Adoptantes
                System.out.println(report.generateAdoptersReport());   
                break;
            case 5: // Reporte de Adoptantes por Cantidad de Animales Adoptados
                //System.out.println(report.generateTopAdoptersReport());
                try {
                    System.out.println("\n=== Filtrar por período de tiempo ===");
                    System.out.println("¿Desea filtrar por período? (S/N): ");
                    String response = sc.nextLine();
                    
                    if (response.equalsIgnoreCase("S")) {
                        System.out.print("Ingrese fecha inicial (DD/MM/YYYY): ");
                        LocalDate startDate = LocalDate.parse(sc.nextLine(), 
                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        System.out.print("Ingrese fecha final (DD/MM/YYYY): ");
                        LocalDate endDate = LocalDate.parse(sc.nextLine(), 
                            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        System.out.println(report.generateTopAdoptersReport(startDate, endDate));
                    } else {
                        System.out.println(report.generateTopAdoptersReport(null, null));
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Formato de fecha inválido");
                }
                break;
            case 6: // Reporte General de Voluntarios
                System.out.println(report.generateVolunteerReport());  
                break;
            case 7: // Top 10 Voluntarios
                System.out.println(report.generateTop10VolunteerReport());
                break;
            case 8: // Reporte General de Recursos
                System.out.println(report.generateResourceReport());  
                break;
            case 9: // Reporte de Recursos Bajos en Cantidad
                System.out.println(report.generateLowResourceReport());
                break;
            case 10: // Reporte General de Tareas
                System.out.println(report.generateTaskReport());
                break;
            case 11: // Reporte de Tareas sin Completar
                System.out.println(report.generateNoCompleteTaskReport());
                break;
            case 14:
                // Regresar al menú principal
                break;
            case 12:
    try {
        System.out.print("Ingrese fecha inicial (DD/MM/YYYY): ");
        LocalDate startDate = LocalDate.parse(sc.nextLine(), 
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Ingrese fecha final (DD/MM/YYYY): ");
        LocalDate endDate = LocalDate.parse(sc.nextLine(), 
            DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println(report.generateAdoptionReportByDate(startDate, endDate));
    } catch (DateTimeParseException e) {
        System.out.println("Error: Formato de fecha inválido");
    }
    break;
case 13:
    try {
        System.out.print("Ingrese año: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.print("Ingrese mes (1-12): ");
        int month = Integer.parseInt(sc.nextLine());
        System.out.println(report.generateMonthlyAdoptionStats(year, month));
    } catch (NumberFormatException e) {
        System.out.println("Error: Ingrese números válidos");
    } catch (DateTimeException e) {
        System.out.println("Error: Fecha inválida");
    }
    break;
            default:
                System.out.println(" ");
                System.out.println("==========================");
                System.out.println("===        ERROR       ===");
                System.out.println("= Opción no válida.      =");
                System.out.println("= Selecciona otra opción =");
                System.out.println("==========================");
                System.out.println(" ");
        }
    }
}
