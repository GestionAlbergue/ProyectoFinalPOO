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
    
}
