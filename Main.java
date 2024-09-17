import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    // private List<Adoption> adoptions;
    // private List<Resource> resources;
    private Report report;  // Referencia al objeto Report
    private MainPage mainPage;  // Referencia a la clase MainPage para navegar en el sistema

    // Método principal para iniciar el programa
    public static void main(String[] args) {
        Main program = new Main();
        program.run();  // Llama al método que controla el flujo del programa
    }
    
    // Constructor de la clase Main
    public Main() {
        animals = new ArrayList<>();
        volunteers = new ArrayList<>();
        // adoptions = new ArrayList<>();
        // resources = new ArrayList<>();
        report = new Report(animals, volunteers, resources);  // Inicializar el objeto Report
        mainPage = new MainPage(report);  // Inicializar la navegación con el objeto Report
    }

    // Control del flujo principal del programa
    public void run() {
        Scanner sc = new Scanner(System.in);
        String option;

        do {
            mainPage.displayOptions();
            option = sc.next();
            mainPage.navigate(option, sc, this);  // Navegar según la opción seleccionada
        } while (!option.equals("6"));  // Opción "6" es salir del sistema
    }

    // Método para agregar un nuevo animal
    public void addAnimal(Scanner sc) {
        try {
            System.out.print("Nombre del animal: ");
            String name = sc.nextLine();
            System.out.print("Raza del animal: ");
            String breed = sc.nextLine();
            System.out.print("Edad del animal: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Descripción del animal: ");
            String description = sc.nextLine();

            Animal animal = new Animal(name, breed, age, description);
            animals.add(animal);
            System.out.println("Animal agregado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de la edad. Debe ser un número entero.");
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar el animal: " + e.getMessage());
        }
    }

    // Método para agregar un nuevo voluntario
    public void addVolunteer(Scanner sc) {
        try {
            System.out.print("Nombre del voluntario: ");
            String name = sc.nextLine();
            System.out.print("Información de contacto del voluntario: ");
            String contactInfo = sc.nextLine();

            Volunteer volunteer = new Volunteer(name, contactInfo);
            volunteers.add(volunteer);
            System.out.println("Voluntario agregado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar el voluntario: " + e.getMessage());
        }
    }

    // Método para registrar una nueva adopción
    public void registerAdoption(Scanner sc) {
        try {
            System.out.print("ID del animal a adoptar: ");
            int animalId = Integer.parseInt(sc.nextLine());
            Animal animal = findAnimalById(animalId);
            if (animal == null) {
                System.out.println("Animal no encontrado.");
                return;
            }

            System.out.print("ID del voluntario que gestiona la adopción: ");
            int volunteerId = Integer.parseInt(sc.nextLine());
            Volunteer volunteer = findVolunteerById(volunteerId);
            if (volunteer == null) {
                System.out.println("Voluntario no encontrado.");
                return;
            }

            System.out.print("Fecha de adopción (DD/MM/AAAA): ");
            String dateInput = sc.nextLine();
            LocalDate adoptionDate;
            try {
                adoptionDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Fecha en formato incorrecto. Debe ser DD/MM/AAAA.");
                return;
            }

            // Adoption adoption = new Adoption(animal, volunteer, adoptionDate);
            // adoptions.add(adoption);
            animal.setAdopted(true);
            System.out.println("Adopción registrada exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato del ID. Debe ser un número entero.");
        } catch (Exception e) {
            System.out.println("Error inesperado al registrar la adopción: " + e.getMessage());
        }
    }

    // Método para agregar un recurso
    public void addResource(Scanner sc) {
        try {
            System.out.print("Nombre del recurso: ");
            String resourceName = sc.nextLine();
            System.out.print("Cantidad disponible: ");
            int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Descripción del recurso: ");
            String description = sc.nextLine();

            // Resource resource = new Resource(resourceName, quantity, description);
            // resources.add(resource);
            System.out.println("Recurso agregado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de la cantidad. Debe ser un número entero.");
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar el recurso: " + e.getMessage());
        }
    }

    private Animal findAnimalById(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    private Volunteer findVolunteerById(int id) {
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getId() == id) {
                return volunteer;
            }
        }
        return null;
    }
}
