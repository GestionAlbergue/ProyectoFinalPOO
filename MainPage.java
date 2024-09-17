/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase MainPage
 * 
 * La clase MainPage representa la página principal y gestiona la navegación.
 * 
 * @author Daniela Navas
 * Fecha de creación: 16/09/2024 
 * Última modificación: 16/09/2024
 */

import java.util.Scanner;

public class MainPage {

    private Report report;  // Asignar el objeto Report

    // Constructor para inicializar la clase con el Report
    public MainPage(Report report) {
        this.report = report;
    }

    // Mostrar las opciones principales del menú
    public void displayOptions() {
        System.out.println("****************************************************");
        System.out.println("***               PÁGINA PRINCIPAL               ***");
        System.out.println("** 1. Registrar Animal                            **");
        System.out.println("** 2. Registrar Voluntario                        **");
        System.out.println("** 3. Registrar Adopción                          **");
        System.out.println("** 4. Registrar Recurso                           **");
        System.out.println("** 5. Registrar Tarea                             **");
        System.out.println("** 6. Completar una Tarea                         **");
        System.out.println("** 7. Informes                                    **");
        System.out.println("** 8. Salir                                       **");
        System.out.print("** Seleccione una opción: ");
    }

    // Mostrar las opciones del menú de reportes
    public void displayOptionsReport() {
        System.out.println("****************************************************");
        System.out.println("***                   REPORTES                   ***");
        System.out.println("** 1. Reporte de Adopciones                       **");
        System.out.println("** 2. Reporte de Animales                         **");
        System.out.println("** 3. Reporte de Voluntarios                      **");
        System.out.println("** 4. Reporte de Recursos                         **");
        System.out.println("** 5. Reporte de Tareas                           **");
        System.out.println("** 6. Regresar                                    **");
        System.out.print("** Seleccione una opción: ");
    }

    // Navegar entre las opciones principales
    public void navigate(String option, Scanner sc, Main main) {
        switch (option) {
            case "1":
                main.addAnimal(sc);  // Registrar un nuevo animal
                break;
            case "2":
                main.addVolunteer(sc);  // Registrar un nuevo voluntario
                break;
            case "3":
                main.registerAdoption(sc);  // Registrar una adopción
                break;
            case "4":
                main.addResource(sc);  // Registrar un nuevo recurso
                break;
            case "5":
                // 
                break;
            case "6":
                this.displayOptionsReport();
                String reportOption = sc.next();
                this.navigateReport(reportOption);
                break;
            case "7":
                System.out.println("== SALIENDO DEL SISTEMA... ==");
                System.exit(0);  // Salir del programa
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción correcta.");
        }
    }

    // Manejar las opciones del menú de reportes
    public void navigateReport(String option) {
        switch (option) {
            case "1":
                System.out.println(report.generateAdoptionReport());  // Mostrar el reporte de adopciones
                break;
            case "2":
                System.out.println(report.generateAnimalStats());
                break;
            case "3":
                System.out.println(report.generateVolunteerReport());
                break;
            case "4":
                // Mostrar el reporte de recursos
                break;
            case "5":
                // Mostrar el reporte de tareas
                break;
            case "6":
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción correcta.");
        }
    }
}
