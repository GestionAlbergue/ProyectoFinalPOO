import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    // private List<Adoption> adoptions;
    // private List<Resource> resources;

    public Main() {
        animals = new ArrayList<>();
        volunteers = new ArrayList<>();
        // adoptions = new ArrayList<>();
        // resources = new ArrayList<>();
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        MainPage mainPage = new MainPage();
        String option;

        do {
            mainPage.displayOptions();
            option = scanner.nextLine();
            mainPage.navigate(option, this, scanner);
        } while (!option.equals("exit"));
        
        scanner.close();
    }

    public void addAnimal(Scanner scanner) {
        System.out.print("Ingrese el nombre del animal: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la raza del animal: ");
        String breed = scanner.nextLine();
        System.out.print("Ingrese la edad del animal: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume la nueva línea
        System.out.print("Ingrese la descripción del animal: ");
        String description = scanner.nextLine();

        Animal animal = new Animal(name, breed, age, description);
        animals.add(animal);
        System.out.println("Animal registrado exitosamente.");
    }

    public void addVolunteer(Scanner scanner) {
        System.out.print("Ingrese el nombre del voluntario: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese la información de contacto del voluntario: ");
        String contactInfo = scanner.nextLine();

        Volunteer volunteer = new Volunteer(name, contactInfo);
        volunteers.add(volunteer);
        System.out.println("Voluntario registrado exitosamente.");
    }

    public void registerAdoption(Scanner scanner) {
        System.out.println("Lista de animales:");
        for (int i = 0; i < animals.size(); i++) {
            if (!animals.get(i).isAdopted()) {
                System.out.println(i + ". " + animals.get(i).getName());
            }
        }
        System.out.print("Seleccione el número del animal a adoptar: ");
        int animalIndex = scanner.nextInt();
        scanner.nextLine();  // Consume la nueva línea

        System.out.println("Lista de voluntarios:");
        for (int i = 0; i < volunteers.size(); i++) {
            System.out.println(i + ". " + volunteers.get(i).getName());
        }
        System.out.print("Seleccione el número del voluntario que gestionará la adopción: ");
        int volunteerIndex = scanner.nextInt();
        scanner.nextLine();  // Consume la nueva línea

        System.out.print("Ingrese la fecha de adopción (dd/mm/yyyy): ");
        String adoptionDate = scanner.nextLine();

        Animal adoptedAnimal = animals.get(animalIndex);
        adoptedAnimal.setAdopted(true);
        Volunteer adopter = volunteers.get(volunteerIndex);

        Adoption adoption = new Adoption(adoptedAnimal, adopter, adoptionDate);
        adoptions.add(adoption);
        System.out.println("Adopción registrada exitosamente.");
    }
    
}
