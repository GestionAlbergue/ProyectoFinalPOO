
/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Main
 * 
 * La clase Main es el punto de entrada de la aplicación y maneja la ejecución
 * del sistema de gestión para un albergue de animales. Controla la interacción 
 * con el usuario mediante un menú y coordina las operaciones entre las diferentes clases.
 * 
 * @author Daniela Navas
 * @version 1.0
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

    private List<Animal> animals;         // Lista de animales del albergue
    private List<Volunteer> volunteers;   // Lista de voluntarios del albergue
    private List<Adoption> adoptions;     // Lista de adopciones realizadas
    private List<Resource> resources;     // Lista de recursos del albergue
    private List<Task> tasks;             // Lista de tareas para los voluntarios
    private Report report;                // Referencia al objeto Report para generar informes
    private MainPage mainPage;            // Referencia a la clase MainPage para navegar en el sistema

    /**
     * Método principal que inicia el programa.
    * @param args Argumentos de la línea de comandos.
    */
    public static void main(String[] args) {
        Main program = new Main();               // Crea una instancia de Main
        program.run();                           // Llama al método que controla el flujo del programa
    }

    /**
     * Constructor de la clase Main. Inicializa las listas de animales, voluntarios,
    * adopciones, recursos y tareas. También crea el objeto Report y MainPage.
    */
    public Main() {
        animals = new ArrayList<>();                                 // Inicializa la lista de animales
        volunteers = new ArrayList<>();                              // Inicializa la lista de voluntarios
        resources = new ArrayList<>();                               // Inicializa la lista de recursos
        tasks = new ArrayList<>();                                   // Inicializa la lista de tareas
        report = new Report(animals, volunteers, resources, tasks);  // Crea el objeto Report
        mainPage = new MainPage(report);                             // Crea el objeto MainPage para la navegación
    }

    /**
     * Método que controla el flujo principal del programa.
    * Muestra el menú de opciones y navega según la selección del usuario.
    */
    public void run() {
        Scanner sc = new Scanner(System.in);  // Scanner para la entrada del usuario
        String option;

        // Bucle principal para mostrar el menú hasta que el usuario seleccione la opción de salir
        do {
            mainPage.displayOptions();            // Muestra el menú de opciones
            option = sc.next();                   // Lee la opción seleccionada por el usuario
            sc.nextLine();                        // Limpia el buffer del scanner
            mainPage.navigate(option, sc, this);  // Navega en el sistema según la opción seleccionada
        } while (!option.equals("10"));  // Repite mientras no se elija la opción de salir (10)
    }

    /**
     * Método para agregar un nuevo animal al sistema.
    * @param sc Scanner utilizado para la entrada del usuario.
    */
    public void addAnimal(Scanner sc) {
        try {
            // Solicitar datos del nuevo animal
            System.out.print("Nombre del animal: ");
            String name = sc.nextLine();
            System.out.print("Raza del animal: ");
            String breed = sc.nextLine();
            System.out.print("Edad del animal: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Descripción del animal: ");
            String description = sc.nextLine();

            // Crear y agregar el nuevo animal a la lista
            Animal animal = new Animal(name, breed, age, description);
            animals.add(animal);
            System.out.println("==================================");
            System.out.println("== Animal agregado exitosamente ==");
            System.out.println("==================================");
        } catch (NumberFormatException e) {
            // Manejo de errores si la edad ingresada no es un número válido
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= El formato de la edad debe ser =");
            System.out.println("= un número entero.              =");
            System.out.println("==================================");
        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
        }
    }

    /**
     * Método para agregar un nuevo voluntario al sistema.
    * @param sc Scanner utilizado para la entrada del usuario.
    */
    public void addVolunteer(Scanner sc) {
        try {
            // Solicitar datos del nuevo voluntario
            System.out.print("Nombre del voluntario: ");
            String name = sc.nextLine();
            System.out.print("Información de contacto del voluntario: ");
            String contactInfo = sc.nextLine();

            // Crear y agregar el nuevo voluntario a la lista
            Volunteer volunteer = new Volunteer(name, contactInfo);
            volunteers.add(volunteer);
            System.out.println("======================================");
            System.out.println("== Voluntario agregado exitosamente ==");
            System.out.println("======================================");
        } catch (Exception e) {
            // Manejo de cualquier error inesperado
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
        }
    }

    /**
     * Método para registrar una adopción.
    * @param sc Scanner utilizado para la entrada del usuario.
    */
    public void registerAdoption(Scanner sc) {
        try {
            // Solicitar el ID del animal a adoptar
            System.out.print("ID del animal a adoptar: ");
            int animalId = Integer.parseInt(sc.nextLine());
            Animal animal = findAnimalById(animalId);  // Buscar el animal por su ID
            if (animal == null) {
                System.out.println("========================");
                System.out.println("===        ERROR     ===");
                System.out.println("= Animal no encontrado =");
                System.out.println("========================");
                return;
            }

            // Solicitar el ID del voluntario que gestiona la adopción
            System.out.print("ID del voluntario que gestiona la adopción: ");
            int volunteerId = Integer.parseInt(sc.nextLine());
            Volunteer volunteer = findVolunteerById(volunteerId);  // Buscar el voluntario por su ID
            if (volunteer == null) {
                System.out.println("============================");
                System.out.println("===         ERROR        ===");
                System.out.println("= Voluntario no encontrado =");
                System.out.println("============================");
                return;
            }

            // Solicitar la fecha de adopción
            System.out.print("Fecha de adopción (DD/MM/AAAA): ");
            String dateInput = sc.nextLine();
            LocalDate adoptionDate;
            try {
                adoptionDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                // Manejo de errores si la fecha no tiene el formato correcto
                System.out.println("==================================");
                System.out.println("===             ERROR          ===");
                System.out.println("= Fecha en formato incorrecto    =");
                System.out.println("= Debe ser DD/MM/AAAA            =");
                System.out.println("==================================");
                return;
            }

            // Registrar la adopción y marcar el animal como adoptado
            Adoption adoption = new Adoption(animal, volunteer, adoptionDate);
            adoptions.add(adoption);
            animal.setAdopted(true);  // Marcar al animal como adoptado
            System.out.println("======================================");
            System.out.println("== Adopción registrada exitosamente ==");
            System.out.println("======================================");
            System.out.println(" ");
            System.out.println("======= Detalles ========");
            System.out.println(adoption.displayAdoptionDetails());
            System.out.println("=========================");
        } catch (NumberFormatException e) {
            // Manejo de errores si el ID ingresado no es válido
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= El formato de ID debe ser un   =");
            System.out.println("= número entero.                 =");
            System.out.println("==================================");
        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
        }
    }

    /**
     * Método para agregar un recurso al sistema.
    * @param sc Scanner utilizado para la entrada del usuario.
    */
    public void addResource(Scanner sc) {
        try {
            // Solicitar datos del nuevo recurso
            System.out.print("Nombre del recurso: ");
            String resourceName = sc.nextLine();
            System.out.print("Cantidad disponible: ");
            int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Descripción del recurso: ");
            String description = sc.nextLine();

            // Crear y agregar el nuevo recurso a la lista
            Resource resource = new Resource(resourceName, quantity, description);
            resources.add(resource);
            System.out.println("===================================");
            System.out.println("== Recurso agregado exitosamente ==");
            System.out.println("===================================");
        } catch (NumberFormatException e) {
            // Manejo de errores si la cantidad ingresada no es un número válido
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= La cantidad debe ser un número =");
            System.out.println("= entero.                        =");
            System.out.println("==================================");
        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
        }
    }
 
    /**
     * Método para registrar una nueva tarea en la lista de tareas.
     * Pide al usuario ingresar el nombre y la descripción de la tarea 
     * a través del objeto Scanner.
     * 
     * @param sc el objeto Scanner utilizado para leer la entrada del usuario
     */
    public void registerTask(Scanner sc) {
        System.out.print("Ingrese el nombre de la tarea: ");
        String name = sc.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = sc.nextLine();
        Task newTask = new Task(name, description);
        tasks.add(newTask);
        System.out.println("=================================");
        System.out.println("== Tarea agregada exitosamente ==");
        System.out.println("=================================");
    }

    /**
     * Método para marcar una tarea como completada.
     * Muestra la lista de tareas pendientes al usuario y permite seleccionar 
     * una tarea para marcarla como completa. Si la tarea ya está completada,
     * se muestra un mensaje. 
     * 
     * @param sc el objeto Scanner utilizado para leer la entrada del usuario
     */
    public void completeTask(Scanner sc) {
        if (tasks.isEmpty()) {
            System.out.println("============================================");
            System.out.println("= No hay tareas disponibles para completar =");
            System.out.println("============================================");
            return;
        }

        // Mostrar la lista de tareas numeradas
        System.out.println("=================================");
        System.out.println("===     Tareas Pendientes     ===");
        System.out.println("=================================");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s - %s (Completada: %s)%n", i + 1, task.getTaskName(), task.getDescription(), task.isCompleted() ? "Sí" : "No");
        }

        // Leer la opción del usuario
        System.out.print("Ingrese el número de la tarea a marcar como COMPLETA: ");
        int taskNumber = sc.nextInt();
        sc.nextLine();  // Consumir el salto de línea pendiente

        // Validar y marcar la tarea como completada
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task selectedTask = tasks.get(taskNumber - 1);
            if (!selectedTask.isCompleted()) {
                selectedTask.completeTask();
                System.out.println("=================================");
                System.out.println("= Tarea marcada como completada =");
                System.out.println("=================================");
            } else {
                System.out.println("============================");
                System.out.println("= Tarea ya está completada =");
                System.out.println("============================");
            }
        } else {
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= Número de tarea inválido.      =");
            System.out.println("= Selecciona un número válido.   =");
            System.out.println("==================================");
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
 