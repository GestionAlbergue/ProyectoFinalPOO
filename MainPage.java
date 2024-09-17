import java.util.Scanner;

public class MainPage {

    // Mostrar las opciones principales del menú
    public void displayOptions() {
        System.out.println("****************************************************");
        System.out.println("***               PÁGINA PRINCIPAL               ***");
        System.out.println("** 1. Registrar Animal                            **");
        System.out.println("** 2. Registrar Voluntario                        **");
        System.out.println("** 3. Registrar Adopción                          **");
        System.out.println("** 4. Registrar Recurso                           **");
        System.out.println("** 5. Informes                                    **");
        System.out.println("** 6. Salir                                       **");
        System.out.print("** Seleccione una opción: ");
    }

    // Mostrar las opciones del menú de reportes
    public void displayOptionsReport() {
        System.out.println("****************************************************");
        System.out.println("***                   REPORTES                   ***");
        System.out.println("** 1. Reporte de Adopciones                       **");
        System.out.println("** 2. Reporte de Animales sin adoptar             **");
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
                this.displayOptionsReport();  // Mostrar el menú de reportes
                navigateReport(sc, main);  // Manejar las opciones de reportes
                break;
            case "6":
                System.out.println("== SALIENDO DEL SISTEMA... ==");
                System.exit(0);  // Salir del programa
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción correcta.");
        }
    }

    // Manejar las opciones del menú de reportes
    public void navigateReport(Scanner sc, Main main) {
        String reportOption = sc.nextLine();
        switch (reportOption) {
            case "1":
                main.displayAdoptionReport();  // Mostrar el reporte de adopciones
                break;
            case "2":
                main.displayUnadoptedAnimalsReport();  // Mostrar el reporte de animales sin adoptar
                break;
            case "3":
                main.displayVolunteerReport();  // Mostrar el reporte de voluntarios
                break;
            case "4":
                main.displayResourceReport();  // Mostrar el reporte de recursos
                break;
            case "5":
                main.displayTaskReport();  // Mostrar el reporte de tareas
                break;
            case "6":
                System.out.println("Regresando al menú principal...");
                break;
            default:
                System.out.println("Opción de reporte no válida. Intenta nuevamente.");
                displayOptionsReport();
                handleReportOptions(sc, main);
        }
    }
}
