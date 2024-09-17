/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Main
 * 
 * La clase Main tiene el punto de Entrada y solo ejecuta las clases
 * 
 * @author Daniela Navas
 * Fecha de creación: 16/09/2024 
 * Última modificación: 17/09/2024
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<Adoption> adoptions;
    private List<Resource> resources;
    private List<Task> tasks;
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
        resources = new ArrayList<>();
        tasks = new ArrayList<>();  // Inicializar la lista de tareas
        report = new Report(animals, volunteers, resources, tasks);  // Inicializar el objeto Report
        mainPage = new MainPage(report);
    }

    // Control del flujo principal del programa
    public void run() {
        Scanner sc = new Scanner(System.in);
        String option;

        do {
            mainPage.displayOptions();
            option = sc.next();
            sc.nextLine();  // Consumir el salto de línea pendiente
            mainPage.navigate(option, sc, this);
        } while (!option.equals("10"));
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

            Resource resource = new Resource(resourceName, quantity, description);
            resources.add(resource);
            System.out.println("Recurso agregado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de la cantidad. Debe ser un número entero.");
        } catch (Exception e) {
            System.out.println("Error inesperado al agregar el recurso: " + e.getMessage());
        }
    }

    // Método para registrar una tarea
    public void registerTask(Scanner sc) {
        System.out.print("Ingrese el nombre de la tarea: ");
        String name = sc.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = sc.nextLine();
        Task newTask = new Task(name, description);
        tasks.add(newTask);
        System.out.println("Tarea registrada exitosamente.");
    }

    // Método para marcar una tarea como completada
    public void completeTask(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("No hay tareas disponibles para completar.");
            return;
        }

        // Mostrar la lista de tareas numeradas
        System.out.println("Seleccione el número de la tarea que desea marcar como completada:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s - %s (Completada: %s)%n", i + 1, task.getTaskName(), task.getDescription(), task.isCompleted() ? "Sí" : "No");
        }

        // Leer la opción del usuario
        System.out.print("Ingrese el número de la tarea: ");
        int taskNumber = sc.nextInt();
        sc.nextLine();  // Consumir el salto de línea pendiente

        // Validar y marcar la tarea como completada
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task selectedTask = tasks.get(taskNumber - 1);
            if (!selectedTask.isCompleted()) {
                selectedTask.completeTask();
                System.out.println("Tarea marcada como completada.");
            } else {
                System.out.println("La tarea ya está completada.");
            }
        } else {
            System.out.println("Número de tarea inválido. Por favor, selecciona un número válido.");
        }
    }

    public void registerMedicalRecord(Scanner sc) {
        // Código para ingresar un nuevo registro médico
        // Esta parte quedará vacía hasta que tengas la clase `MedicalRecord` o equivalente
    }

    // Función 8: Visualizar récord médico
    public void viewMedicalRecord(Scanner sc) {
        // Código para visualizar el récord médico
        // Esta parte quedará vacía hasta que tengas la clase `MedicalRecord` o equivalente
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
