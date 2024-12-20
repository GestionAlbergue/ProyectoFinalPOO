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
 * Última modificación: 13/11/2024
 */

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPage {
    // private Scanner sc; /** Objeto de la clase Report utilizado para generar informes. */
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
        // Códigos ANSI para colores
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        // final String BLUE = "\u001B[34m";
        final String CYAN = "\u001B[36m";
        final String WHITE_BOLD = "\u001B[1;37m";
    
        // Impresión del menú
        System.out.println(" ");
        System.out.println(CYAN + "====================================================" + RESET);
        System.out.println(CYAN + "||                                                ||" + RESET);
        System.out.println(CYAN + "||" + WHITE_BOLD + "             ** PÁGINA PRINCIPAL **             " + CYAN + "||" + RESET);
        System.out.println(CYAN + "||                                                ||" + RESET);
        System.out.println(CYAN + "====================================================" + RESET);
        System.out.println(GREEN + "||  " + RESET + "1. Registrar Animal                           " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "2. Registrar Voluntario                       " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "3. Registrar Adoptante                        " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "4. Registrar Adopción                         " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "5. Registrar Recurso                          " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "6. Actualizar Cantidad de Recurso             " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "7. Registrar Tarea                            " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "8. Marcar como completa una Tarea             " + GREEN + "||" + RESET);
        System.out.println(GREEN + "||  " + RESET + "9. Ingresar un Nuevo Registro Médico          " + GREEN + "||" + RESET);
        System.out.println(GREEN + "|| " + RESET + "10. Visualizar Record Médico                   " + GREEN + "||" + RESET);
        System.out.println(GREEN + "|| " + RESET + "11. Reportes del Albergue                      " + GREEN + "||" + RESET);
        System.out.println(GREEN + "|| " + RESET + "12. Reporte de Adopciones por Período          " + GREEN + "||" + RESET);
        System.out.println(GREEN + "|| " + RESET + "13. Estadísticas Mensuales de Adopciones       " + GREEN + "||" + RESET);
        System.out.println(GREEN + "|| " + RESET + "14. " + RED + "Salir                                      " + GREEN + "||" + RESET);
        System.out.println(CYAN + "====================================================" + RESET);
        System.out.print(YELLOW + "Seleccione una opción: " + RESET);
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
                main.addResource(sc);            // Registrar un recurso
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
                    
                } while (reportOption != 12);  // Repite mientras no se elija la opción de regresar (12)
                break;
            case 12: // Reporte de Adopciones por Período
                try {
                    System.out.print("Ingrese fecha inicial (DD/MM/YYYY): ");
                    String inputStartDate = sc.nextLine().trim(); // Trim to remove extra spaces
                    LocalDate startDate = LocalDate.parse(inputStartDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
                    System.out.print("Ingrese fecha final (DD/MM/YYYY): ");
                    String inputEndDate = sc.nextLine().trim();
                    LocalDate endDate = LocalDate.parse(inputEndDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            
                    // Ensure startDate is not after endDate
                    if (startDate.isAfter(endDate)) {
                        System.out.println("Error: La fecha inicial no puede ser posterior a la fecha final.");
                    } else {
                        System.out.println(report.generateAdoptionReportByDate(startDate, endDate));
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Formato de fecha inválido");
                } catch (Exception e) {
                    System.out.println("Error inesperado: " + e.getMessage());
                    e.printStackTrace(); // For debugging purposes
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
                System.out.println(report.generateTopAdoptersReport());
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
            case 9: // Reporte de Recursos Bajos en Cantidad (Menos 10 como se definió en Threshold)
                System.out.println(report.generateLowResourceReport());
                break;
            case 10: // Reporte General de Tareas
                System.out.println(report.generateTaskReport());
                break;
            case 11: // Reporte de Tareas sin Completar
                System.out.println(report.generateNoCompleteTaskReport());
                break;
            case 12:
                // Regresar al menú principal
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
