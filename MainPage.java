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

    public void navigate(String option, Main main, Scanner scanner) {
        switch (option) {
            case "1":
                main.addAnimal(scanner);
                break;
            case "2":
                main.addVolunteer(scanner);
                break;
            case "3":
                main.registerAdoption(scanner);
                break;
            case "4":
                main.addResource(scanner);
                break;
            case "5":
                System.out.println("Saliendo del programa...");
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }
}
