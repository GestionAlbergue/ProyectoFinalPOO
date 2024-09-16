import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    //private List<Adoption> adoptions;
    //private List<Resource> resources;

    public Main() {
        animals = new ArrayList<>();
        volunteers = new ArrayList<>();
        //adoptions = new ArrayList<>();
        //resources = new ArrayList<>();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        MainPage mainPage = new MainPage();

        while (true) {
            mainPage.displayOptions();
            String option = sc.nextLine();
            
            try {
                mainPage.navigate(option, sc, this);
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Intenta nuevamente.");
                sc.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
    }

    public void addAnimal(Scanner sc) {
        try {
            System.out.print("Nombre del animal: ");
            String name = sc.nextLine();

            System.out.print("Raza: ");
            String breed = sc.nextLine();

            System.out.print("Edad: ");
            int age = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            System.out.print("Descripción: ");
            String description = sc.nextLine();

            Animal animal = new Animal(name, breed, age, description);
            animals.add(animal);
            System.out.println("Animal registrado exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida para la edad. Intenta nuevamente.");
            sc.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Error inesperado al añadir el animal: " + e.getMessage());
        }
    }

    public void addVolunteer(Scanner sc) {
        try {
            System.out.print("Nombre del voluntario: ");
            String name = sc.nextLine();

            System.out.print("Información de contacto: ");
            String contactInfo = sc.nextLine();

            Volunteer volunteer = new Volunteer(name, contactInfo);
            volunteers.add(volunteer);
            System.out.println("Voluntario registrado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error inesperado al añadir el voluntario: " + e.getMessage());
        }
    }

    public void registerAdoption(Scanner sc) {
        try {
            System.out.print("Nombre del animal a adoptar: ");
            String animalName = sc.nextLine();
            Animal animal = findAnimalByName(animalName);
            if (animal == null) {
                System.out.println("Animal no encontrado.");
                return;
            }

            System.out.print("Nombre del voluntario que gestiona la adopción: ");
            String volunteerName = sc.nextLine();
            Volunteer volunteer = findVolunteerByName(volunteerName);
            if (volunteer == null) {
                System.out.println("Voluntario no encontrado.");
                return;
            }

            System.out.print("Fecha de adopción (DD/MM/AAAA): ");
            String adoptionDate = sc.nextLine();

            Adoption adoption = new Adoption(animal, volunteer, adoptionDate);
            adoptions.add(adoption);
            animal.setAdopted(true);
            System.out.println("Adopción registrada exitosamente.");
        } catch (Exception e) {
            System.out.println("Error inesperado al registrar la adopción: " + e.getMessage());
        }
    }

    public void addResource(Scanner sc) {
        try {
            System.out.print("Nombre del recurso: ");
            String resourceName = sc.nextLine();

            System.out.print("Cantidad disponible: ");
            int quantity = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            System.out.print("Descripción: ");
            String description = sc.nextLine();

            Resource resource = new Resource(resourceName, quantity, description);
            resources.add(resource);
            System.out.println("Recurso registrado exitosamente.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida para la cantidad. Intenta nuevamente.");
            sc.nextLine(); // Limpiar el buffer
        } catch (Exception e) {
            System.out.println("Error inesperado al añadir el recurso: " + e.getMessage());
        }
    }

    public Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    public Volunteer findVolunteerByName(String name) {
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getName().equalsIgnoreCase(name)) {
                return volunteer;
            }
        }
        return null;
    }
}
