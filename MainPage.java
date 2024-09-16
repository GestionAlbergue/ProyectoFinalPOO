import java.util.Scanner;

public class MainPage {

    public void displayOptions() {
        System.out.println("****************************************************");
        System.out.println("***               PÁGINA PRINCIPAL               ***");
        System.out.println("** 1. Registrar Animal                            **");
        System.out.println("** 2. Registrar Voluntario                        **");
        System.out.println("** 3. Registrar Adopción                          **");
        System.out.println("** 4. Registrar Recurso                           **");
        System.out.println("** 5. Salir                                       **");
        System.out.print("** Seleccione una opción: ");
    }

    public void navigate(String option, Scanner sc, Main main) {
        switch (option) {
            case "1":
                main.addAnimal(sc);
                break;
            case "2":
                main.addVolunteer(sc);
                break;
            case "3":
                main.registerAdoption(sc);
                break;
            case "4":
                main.addResource(sc);
                break;
            case "5":
                System.out.println("Saliendo del sistema. ¡Adiós!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción correcta.");
        }
    }
}

