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
 * Fecha de creación: 16/09/2024
 * Última modificación: 13/11/2024
 */

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Animal> animals;                       // Lista de animales del albergue
    private List<Volunteer> volunteers;                 // Lista de voluntarios del albergue
    private List<Adoption> adoptions;                   // Lista de adopciones realizadas
    private List<Resource> resources;                   // Lista de recursos del albergue
    private List<Task> tasks;                           // Lista de tareas para los voluntarios
    private List<MedicalHistory> histories;             // Lista de historiales médicos en el sistema
    private List<AdoptionCandidate> adoptionCandidates; // Lista de Adoptantes en el sistema
    private Report report;                              // Referencia al objeto Report para generar informes
    private MainPage mainPage;                          // Referencia a la clase MainPage para navegar en el sistema

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
        this.loadAllData();     // Cargar toda la data desde los CSV
        this.report = new Report(animals, volunteers, resources, tasks, adoptionCandidates, adoptions);  // Crea el objeto Report
        this.mainPage = new MainPage(report);  // Crea el objeto MainPage para la navegación
    }

    /**
     * Método que controla el flujo principal del programa.
     * Muestra el menú de opciones y navega según la selección del usuario.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);                        // Scanner para la entrada del usuario
        int option = 0;                                             // Cambiado a int para manejar las opciones numéricas

        do {  // Bucle principal para mostrar el menú hasta que el usuario seleccione la opción de salir
            mainPage.displayOptions();  // Muestra el menú de opciones
            
            try {
                option = sc.nextInt();  // Lee la opción seleccionada por el usuario
                sc.nextLine();  // Limpia el buffer del scanner
                
                // Navega en el sistema según la opción seleccionada
                mainPage.navigate(option, sc, this);  
            } catch (InputMismatchException e) {
                System.out.println(" ");
                System.out.println("==========================");
                System.out.println("===        ERROR       ===");
                System.out.println("= Ingrese un número      =");
                System.out.println("= Selecciona otra opción =");
                System.out.println("==========================");  // Mensaje de error en caso de entrada no válida
                System.out.println(" ");
                sc.nextLine();  // Limpia el buffer en caso de excepción
            }
            
        } while (option != 14);  // Repite mientras no se elija la opción de salir (12)
    }


    /**
     * Método para agregar un nuevo animal al sistema.
     * @param sc Scanner utilizado para la entrada del usuario.
     */
    public void addAnimal(Scanner sc) {
        try {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===       AGREGAR ANIMAL       ===");
            System.out.println("==================================");
            System.out.println(" "); // Solicitar datos del nuevo animal
            System.out.print("Nombre del animal: ");
            String name = sc.nextLine();
            System.out.print("Raza del animal: ");
            String breed = sc.nextLine();
            System.out.print("Edad del animal (Años): ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Descripción del animal: ");
            String description = sc.nextLine();

            String dangerLevelResponse = "";
            boolean valida = false;
            boolean dangerLevel = true;
            while (!valida) {
                System.out.print("¿Se considera animal peligroso? (Si/No): ");
                dangerLevelResponse = sc.nextLine();
        
                if (dangerLevelResponse.equalsIgnoreCase("si") || dangerLevelResponse.equalsIgnoreCase("no")) {
                    valida = true;  // Si la entrada es válida, salimos del bucle
                } else {
                    System.out.println(" ");
                    System.out.println("==================================");
                    System.out.println("===             ERROR          ===");
                    System.out.println("= Ingrese Si o No                =");
                    System.out.println("==================================");
                    System.out.println(" ");
                }
            }

            if (dangerLevelResponse.equalsIgnoreCase("si")) {
                dangerLevel = true;
            } else {
                dangerLevel = false;
            }

            // Crear y agregar el nuevo animal a la lista
            Animal animal = new Animal(name, breed, description, age, false, dangerLevel);
            animals.add(animal);
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("== Animal agregado exitosamente ==");
            System.out.println("== Número de ID: " + animal.getId());
            System.out.println("==================================");

        } catch (NumberFormatException e) {
            // Manejo de errores si la edad o el nivel de peligro ingresados no son números válidos
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= El formato de la edad deben    =");
            System.out.println("= ser números enteros.           =");
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
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===     AGREGAR VOLUNTARIO     ===");
            System.out.println("==================================");
            System.out.println(" "); // Solicitar datos del nuevo voluntario
            System.out.print("Nombre del voluntario: ");
            String name = sc.nextLine();
            System.out.print("Información de contacto del voluntario: ");
            String contactInfo = sc.nextLine();

            // Crear y agregar el nuevo voluntario a la lista
            Volunteer volunteer = new Volunteer(name, contactInfo, 0);
            volunteers.add(volunteer);
            System.out.println(" ");
            System.out.println("======================================");
            System.out.println("== Voluntario agregado exitosamente ==");
            System.out.println("== Número de ID: " + volunteer.getId());
            System.out.println("======================================");
            System.out.println(" ");
        } catch (Exception e) {
            // Manejo de cualquier error inesperado
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
            System.out.println(" ");
        }
    }

    /**
     * Método para registrar un posible adoptante.
     * @param sc Scanner utilizado para la entrada del usuario.
     */
    public void registerAdopter(Scanner sc) {
        try {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===    REGISTRAR ADOPTANTE     ===");
            System.out.println("==================================");
            System.out.println(" "); 

            System.out.println("========== ADOPTANTE ==========");
            System.out.print("Nombre de Adoptante: ");
            String adopterName = sc.nextLine();

            System.out.print("Información de Contacto de Adoptante: ");
            String adopterContactInfo = sc.nextLine();

            String experienceResponse = "";
            boolean entradaValida = false;
            boolean hasPetExperience = true;
            while (!entradaValida) {
                System.out.print("¿Tienes experiencia con mascotas? (Si/No): ");
                experienceResponse = sc.nextLine();
        
                if (experienceResponse.equalsIgnoreCase("si") || experienceResponse.equalsIgnoreCase("no")) {
                    entradaValida = true;  // Si la entrada es válida, salimos del bucle
                } else {
                    System.out.println(" ");
                    System.out.println("==================================");
                    System.out.println("===             ERROR          ===");
                    System.out.println("= Ingrese Si o No                =");
                    System.out.println("==================================");
                    System.out.println(" ");
                }
            }

            if (experienceResponse.equalsIgnoreCase("si")) {
                hasPetExperience = true;
            } else {
                hasPetExperience = false;
            }

            String additionalExperienceResponse = "";
            entradaValida = false;
            boolean additionalExperience = true;
            while (!entradaValida) {
                System.out.print("¿Tienes experiencia con Animales Peligrosos? (Si/No): ");
                additionalExperienceResponse = sc.nextLine();
        
                if (additionalExperienceResponse.equalsIgnoreCase("si") || additionalExperienceResponse.equalsIgnoreCase("no")) {
                    entradaValida = true;  // Si la entrada es válida, salimos del bucle
                } else {
                    System.out.println(" ");
                    System.out.println("==================================");
                    System.out.println("===             ERROR          ===");
                    System.out.println("= Ingrese Si o No                =");
                    System.out.println("==================================");
                    System.out.println(" ");
                }
            }

            if (additionalExperienceResponse.equalsIgnoreCase("si")) {
                additionalExperience = true;
            } else {
                additionalExperience = false;
            }

            System.out.print("¿Cuál es tu razón para adoptar un animal? ");
            String reasonForAdoption = sc.nextLine();

            System.out.print("ID del voluntario que gestiona la comunicacion con el adoptante: ");
            int volunteerId = Integer.parseInt(sc.nextLine());
            Volunteer volunteer = findVolunteerById(volunteerId);  // Buscar el voluntario por su ID
            if (volunteer == null) {
                System.out.println(" ");
                System.out.println("============================");
                System.out.println("===         ERROR        ===");
                System.out.println("= Voluntario no encontrado =");
                System.out.println("============================");
                System.out.println(" ");
                return;
            }
    
            AdoptionCandidate adopter = new AdoptionCandidate(adopterName, adopterContactInfo, reasonForAdoption, hasPetExperience, additionalExperience, volunteer);
            
            adoptionCandidates.add(adopter);
            System.out.println(" ");
            System.out.println("======================================");
            System.out.println("== Adoptante agregado exitosamente ==");
            System.out.println("== Número de ID: " + adopter.getId());
            System.out.println("======================================");
            System.out.println(" ");
        } catch (Exception e) {
            // Manejo de cualquier error inesperado
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
            System.out.println(" ");
        }
    }

    /**
     * Método para registrar una adopción.
     * @param sc Scanner utilizado para la entrada del usuario.
     */
    public void registerAdoption(Scanner sc) {
        try {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===     REGISTRAR ADOPCIÓN     ===");
            System.out.println("==================================");
            System.out.println(" "); // Solicitar el ID del animal a adoptar
            System.out.print("ID del animal a adoptar: ");
            int animalId = Integer.parseInt(sc.nextLine());
            Animal animal = findAnimalById(animalId);  // Buscar el animal por su ID
            if (animal == null) {
                System.out.println(" ");
                System.out.println("========================");
                System.out.println("===        ERROR     ===");
                System.out.println("= Animal no encontrado =");
                System.out.println("========================");
                System.out.println(" ");
                return;
            }

            // Verificar si el animal ya está adoptado
            if (animal.isAdopted()) {
                System.out.println(" ");
                System.out.println("=================================");
                System.out.println("===   ADOPCIÓN NO PERMITIDA   ===");
                System.out.println("= El animal ya ha sido adoptado =");
                System.out.println("=================================");
                System.out.println(" ");
                return;
            }

            // Solicitar el ID del adoptante
            System.out.print("ID del adoptante: ");
            int adopterId = Integer.parseInt(sc.nextLine());
            AdoptionCandidate adopter = findAdopterById(adopterId);  // Buscar el adoptante por su ID
            if (adopter == null) {
                System.out.println(" ");
                System.out.println("===========================");
                System.out.println("===        ERROR        ===");
                System.out.println("= Adoptante no encontrado =");
                System.out.println("===========================");
                System.out.println(" ");
                return;
            }

            // Extraer información del voluntario encargado de la gestión con el adoptante.
            Volunteer volunteer = adopter.getVolunteer();

            // Verificar si el animal es peligroso y si el adoptante tiene experiencia
            if (animal.getDangerLevel() && !adopter.getAdditionalExperience()) {
                System.out.println(" ");
                System.out.println("======================================");
                System.out.println("===       ADOPCIÓN NO PERMITIDA    ===");
                System.out.println("= El animal es peligroso y el        =");
                System.out.println("= adoptante no tiene experiencia.    =");
                System.out.println("======================================");
                System.out.println(" ");
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
                System.out.println(" ");
                System.out.println("==================================");
                System.out.println("===             ERROR          ===");
                System.out.println("= Fecha en formato incorrecto    =");
                System.out.println("= Debe ser DD/MM/AAAA            =");
                System.out.println("==================================");
                System.out.println(" ");
                return;
            }

            // Registrar la adopción
            Adoption adoption = new Adoption(animal, volunteer, adoptionDate, adopter);
            adoptions.add(adoption);
            
            // Agregar el animal a la lista de animales adoptados por el adoptante
            adopter.addAnimal(animal);

            // Agregar el volunteer relacionado con el adoptante.
            adopter.setVolunteer(volunteer);

            // Marcar al animal como adoptado
            animal.setAdopted(true);

            System.out.println(" ");
            System.out.println("======================================");
            System.out.println("== Adopción registrada exitosamente ==");
            System.out.println("======================================");
            System.out.println(" ");
            System.out.println("============ Detalles ============");
            System.out.print(adoption.displayAdoptionDetails());
            System.out.println("==================================");
        } catch (NumberFormatException e) {
            // Manejo de errores si el ID ingresado no es válido
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= El formato de ID debe ser un   =");
            System.out.println("= número entero.                 =");
            System.out.println("==================================");
            System.out.println(" ");
        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
            System.out.println(" ");
        }
    }

    /**
     * Método para agregar un recurso al sistema.
     * @param sc Scanner utilizado para la entrada del usuario.
     */
    public void addResource(Scanner sc) {
        try {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===      AGREGAR RECURSO       ===");
            System.out.println("==================================");
            System.out.println(" "); // Solicitar datos del nuevo recurso
            System.out.print("Nombre del recurso: ");
            String resourceName = sc.nextLine();
            System.out.print("Cantidad: ");
            int quantity = Integer.parseInt(sc.nextLine());

            // Verificar si el recurso ya existe
            boolean resourceExists = false;
            
            for (Resource existingResource : resources) {
                if (existingResource.getResourceName().equalsIgnoreCase(resourceName)) {
                    // Si el recurso ya existe, actualizar la cantidad
                    existingResource.updateQuantity(existingResource.getQuantity() + quantity);
                    resourceExists = true;
                    if(existingResource.checkAlert() != null){
                        System.out.println(" ");
                        System.out.println(existingResource.checkAlert());
                    }
                    break;  // Salir del bucle ya que se encontró el recurso
                }
            }

            // Si el recurso no existe, crear y agregar el nuevo recurso a la lista
            if (!resourceExists) {
                System.out.print("Descripción del recurso: ");
                String description = sc.nextLine();
                Resource newResource = new Resource(resourceName, quantity, description);
                resources.add(newResource);
                if(newResource.checkAlert() != null){
                    System.out.println(" ");
                    System.out.println(newResource.checkAlert());
                }
                System.out.println(" ");
                System.out.println("===================================");
                System.out.println("== Recurso agregado exitosamente ==");
                System.out.println("===================================");
                System.out.println(" ");
            } else {
                System.out.println(" ");
                System.out.println("===================================");
                System.out.println("==    Recurso ya existestente    ==");
                System.out.println("==     Cantidad Actualizada      ==");
                System.out.println("===================================");
                System.out.println(" ");
            }
        } catch (NumberFormatException e) {
            // Manejo de errores si la cantidad ingresada no es un número válido
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= La cantidad debe ser un número =");
            System.out.println("= entero.                        =");
            System.out.println("==================================");
            System.out.println(" ");
        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
            System.out.println(" ");
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
        System.out.println(" ");
        System.out.println("==================================");
        System.out.println("===       AGREGAR TAREA        ===");
        System.out.println("==================================");
        System.out.println(" "); 
        System.out.print("Ingrese el nombre de la tarea: ");
        String name = sc.nextLine();
        System.out.print("Ingrese la descripción de la tarea: ");
        String description = sc.nextLine();

        // Solicitar el ID del voluntario
        System.out.print("Ingrese el ID del voluntario: ");
        int volunteerId = sc.nextInt();
        sc.nextLine();  // Consumir el salto de línea pendiente

        // Buscar al voluntario por su ID
        Volunteer volunteer = findVolunteerById(volunteerId);
        if (volunteer != null) {
            Task newTask = new Task(name, description, false, volunteer);
            tasks.add(newTask);
            System.out.println(" ");
            System.out.println("=================================");
            System.out.println("== Tarea agregada exitosamente ==");
            System.out.println("=================================");
            System.out.println(" ");
           
        } else {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= Voluntario no encontrado       =");
            System.out.println("==================================");
            System.out.println(" ");
        }
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
            System.out.println(" ");
            System.out.println("============================================");
            System.out.println("= No hay tareas disponibles para completar =");
            System.out.println("============================================");
            System.out.println(" ");
            return;
        }
    
        // Mostrar la lista de tareas numeradas
        System.out.println(" ");
        System.out.println("=================================");
        System.out.println("===     Tareas Pendientes     ===");
        System.out.println("=================================");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.print(i + 1 + ". ");
            System.out.println(task);
            System.out.println(" ");
        }
    
        // Leer la opción del usuario
        System.out.print("Ingrese el número de la tarea a marcar como Completa: ");
        int taskNumber = sc.nextInt();
        sc.nextLine();  // Consumir el salto de línea pendiente
    
        // Validar y marcar la tarea como completada
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task selectedTask = tasks.get(taskNumber - 1);
            if (!selectedTask.isCompleted()) {
    
                // Solicitar el ID del voluntario
                System.out.print("Ingrese el ID del voluntario: ");
                int volunteerId = sc.nextInt();
                sc.nextLine();  // Consumir el salto de línea pendiente
    
                // Buscar al voluntario por su ID
                Volunteer volunteer = findVolunteerById(volunteerId);
                if (volunteer != null) {
                    // Solicitar las horas trabajadas en la tarea
                    System.out.print("Ingrese las horas trabajadas en la tarea: ");
                    int hoursWorked = Integer.parseInt(sc.nextLine());
    
                    // Sumar las horas trabajadas al voluntario
                    volunteer.addHours(hoursWorked);
                    selectedTask.setVolunteer(volunteer);
                    selectedTask.completeTask();

                    System.out.println(" ");
                    System.out.println("=================================");
                    System.out.println("= Tarea marcada como completada =");
                    System.out.println("= Horas trabajadas sumadas: " + hoursWorked);
                    System.out.println("=================================");
                    System.out.println(" ");
                } else {
                    System.out.println(" ");
                    System.out.println("==================================");
                    System.out.println("===             ERROR          ===");
                    System.out.println("= Voluntario no encontrado       =");
                    System.out.println("==================================");
                    System.out.println(" ");
                }
            } else {
                System.out.println(" ");
                System.out.println("=================================");
                System.out.println("= Esta tarea ya está completada =");
                System.out.println("=================================");
                System.out.println(" ");
            }
        } else {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= Número de tarea inválido.      =");
            System.out.println("= Selecciona un número válido.   =");
            System.out.println("==================================");
            System.out.println(" ");
        }
    }

    /**
     * Registra un nuevo registro médico para un animal.
     * Solicita al usuario que ingrese los detalles del registro médico
     * y lo añade al historial médico del animal.
     * 
     * @param sc El objeto Scanner para recibir la entrada del usuario.
     */
    public void registerMedicalRecord(Scanner sc) {
        try {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===  AGREGAR REGISTRO MÉDICO   ===");
            System.out.println("==================================");
            System.out.println(" "); // Solicitar el ID del animal
            System.out.print("Ingrese el ID del animal: ");
            int animalId = Integer.parseInt(sc.nextLine());
            Animal animal = findAnimalById(animalId);

            // Verificar si el animal existe
            if (animal == null) {
                System.out.println(" ");
                System.out.println("==================================");
                System.out.println("===             ERROR          ===");
                System.out.println("== Animal no encontrado         ==");
                System.out.println("==================================");
                System.out.println(" ");
                return;
            }

            System.out.print("Ingrese la fecha del registro(DD/MM/AAAA): ");
            String dateInput = sc.nextLine();
            LocalDate recordDate;
            try {
                recordDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                // Manejo de errores si la fecha no tiene el formato correcto
                System.out.println(" ");
                System.out.println("==================================");
                System.out.println("===             ERROR          ===");
                System.out.println("= Fecha en formato incorrecto    =");
                System.out.println("= Debe ser DD/MM/AAAA            =");
                System.out.println("==================================");
                System.out.println(" ");
                return;
            }

            System.out.print("Ingrese una descripción del Record: ");
            String description = sc.nextLine();

            System.out.print("Ingrese el tratamiento: ");
            String treatment = sc.nextLine();

            System.out.print("Ingrese el nombre del veterinario: ");
            String veterinarian = sc.nextLine();

            // Crear un nuevo registro médico
            MedicalRecord newRecord = new MedicalRecord(recordDate, description, treatment, veterinarian);

            // Obtener el historial médico del animal
            MedicalHistory medicalHistory = findMedicalHistoryByAnimal(animal);

            // Si no existe historial médico, crear uno nuevo
            if (medicalHistory == null) {
                medicalHistory = new MedicalHistory(animal);
                histories.add(medicalHistory); // Añadirlo a la lista de historiales
            }

            // Agregar el nuevo registro al historial médico del animal
            medicalHistory.addRecord(newRecord);
            System.out.println(" ");
            System.out.println("========================================");
            System.out.println("= Registro médico añadido exitosamente =");
            System.out.println("========================================");
            System.out.println(" ");

        } catch (NumberFormatException e) {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= El ID debe ser un número       =");
            System.out.println("= entero.                        =");
            System.out.println("==================================");
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===             ERROR          ===");
            System.out.println("= " + e.getMessage());
            System.out.println("==================================");
            System.out.println(" ");
        }
    }

    /**
     * Muestra el historial médico de un animal dado su ID.
     * 
     * Solicita al usuario que ingrese el ID del animal y busca el animal en la base de datos. 
     * Si el animal se encuentra, busca su historial médico y muestra los detalles. 
     * Si el animal o el historial médico no se encuentran, se muestra un mensaje de error apropiado.
     * 
     * Si el ID ingresado no es un número entero, se muestra un mensaje de error indicando el problema.
     * 
     * @param sc El objeto utilizado para la entrada del usuario.
     */
    public void viewMedicalRecord(Scanner sc) {
        try {
            System.out.println(" ");
            System.out.println("==================================");
            System.out.println("===     VER REGISTRO MÉDICO    ===");
            System.out.println("==================================");
            System.out.println(" ");
            
            // Solicita al usuario que ingrese el ID del animal
            System.out.print("Ingrese el ID del animal: ");
            int animalId = Integer.parseInt(sc.nextLine());
            
            Animal animal = findAnimalById(animalId);
            if (animal == null) {
                System.out.println("==================================");
                System.out.println("===             ERROR          ===");
                System.out.println("= Animal no encontrado           =");
                System.out.println("==================================");
                System.out.println(" ");
                return;
            }
    
            MedicalHistory medicalHistory = findMedicalHistoryByAnimal(animal);
            if (medicalHistory == null) {
                System.out.println(" ");
                System.out.println("==================================");
                System.out.println("===             ERROR          ===");
                System.out.println("= Historial médico no encontrado =");
                System.out.println("==================================");
                System.out.println(" ");
                return;
            }

            System.out.println(" ");
            System.out.println("** Seleccione el período para ver los registros: **");
            System.out.println("* 1. Semanal                                      *");
            System.out.println("* 2. Mensual                                      *");
            System.out.println("* 3. Anual                                        *");
            System.out.print("Opción: ");
            
            int option = Integer.parseInt(sc.nextLine());
            LocalDate now = LocalDate.now();
            LocalDate filterDate = null;
    
            switch (option) {
                case 1:
                    filterDate = now.minusDays(7); // Filtro de la última semana
                    break;
                case 2:
                    filterDate = now.minusMonths(1); // Filtro del último mes
                    break;
                case 3:
                    filterDate = now.minusYears(1); // Filtro del último año
                    break;
                default:
                    System.out.println("==================================");
                    System.out.println("===             ERROR          ===");
                    System.out.println("= Opción no Válida               =");
                    System.out.println("==================================");
                    System.out.println(" ");
                    return;
            }

            System.out.println(" ");
            System.out.println("=== Registros médicos Filtrados ===");
            System.out.println(" ");
            boolean recordsFound = false;
            
            for (MedicalRecord record : medicalHistory.getRecords()) {
                if (record.getDate().isAfter(filterDate) || record.getDate().isEqual(filterDate)) {
                    System.out.println(record);
                    recordsFound = true;
                }
            }
            
            if (!recordsFound) {
                System.out.println(" ");
                System.out.println("=======================================");
                System.out.println("= No se encontraron registros médicos =");
                System.out.println("= para el periodo seleccionado.       =");
                System.out.println("=======================================");
                System.out.println(" ");
            }
    
        } catch (NumberFormatException e) {
            System.out.println(" ");
            System.out.println("===================================");
            System.out.println("===             ERROR           ===");
            System.out.println("= El ID debe ser un número entero =");
            System.out.println("===================================");
            System.out.println(" ");
        } catch (Exception e) {
            System.out.println(" ");
            System.out.println("===================================");
            System.out.println("===             ERROR           ===");
            System.out.println("=== " + e.getMessage() + " ===");
            System.out.println("===================================");
            System.out.println(" ");
        }
    }    

    /**
     * Muestra una lista numerada de recursos y permite al usuario seleccionar uno para actualizar su cantidad.
     * @param sc Scanner para leer la entrada del usuario.
     */
    public void updateResourceQuantity(Scanner sc) {
        if (resources.isEmpty()) {
            System.out.println(" ");
            System.out.println("===============================================");
            System.out.println("= No hay recursos disponibles para actualizar =");
            System.out.println("===============================================");
            System.out.println(" ");
            return;
        }

        // Mostrar la lista de recursos
        System.out.println(" ");
        System.out.println("=========================");
        System.out.println("= Recursos del Albergue =");
        System.out.println("=========================");
        for (int i = 0; i < resources.size(); i++) {
            Resource resource = resources.get(i);
            System.out.println((i + 1) + ". " + resource.getResourceName() + " - Cantidad: " + resource.getQuantity());
        }

        try {
            System.out.print("Ingrese el número del recurso: ");
            int resourceIndex = Integer.parseInt(sc.nextLine()) - 1;

            if (resourceIndex < 0 || resourceIndex >= resources.size()) {
                System.out.println(" ");
                System.out.println("==============================");
                System.out.println("===          ERROR         ===");
                System.out.println("= Número de recurso inválido =");
                System.out.println("==============================");
                System.out.println(" ");
                return;
            }

            Resource selectedResource = resources.get(resourceIndex);

            System.out.print("Nueva Cantidad Disponible: ");
            int newQuantity = Integer.parseInt(sc.nextLine());

            // Se actualiza la cantidad (No se suma, solo se actualiza)
            selectedResource.updateQuantity(newQuantity); 

            // Mostrar mensaje de alerta si la cantidad es baja
            String alertMessage = selectedResource.getAlertMessage();
            if (alertMessage != null) {
                System.out.println(alertMessage);
            } else {
                if(selectedResource.checkAlert() != null){
                    System.out.println(" ");
                    System.out.println(selectedResource.checkAlert());
                }
                System.out.println(" ");
                System.out.println("=====================================");
                System.out.println("= Cantidad actualizada exitosamente =");
                System.out.println("=====================================");
                System.out.println(" ");
            }
        } catch (NumberFormatException e) {
            System.out.println(" ");
            System.out.println("=============================================");
            System.out.println("= Entrada inválida. Debe ingresar un número =");
            System.out.println("==============================================");
            System.out.println(" ");
        }
    }

    /**
     * Busca y devuelve un objeto Animal de la lista de animales basado en su ID.
     *
     * @param id El ID del animal que se desea buscar.
     * @return El objeto Animal con el ID correspondiente, o null si no se encuentra ningún animal con ese ID.
     */
    private Animal findAnimalById(int id) {
        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    /**
     * Busca el historial médico asociado a un animal específico en la lista de historiales.
     *
     * @param animal El animal del que se busca historial médico
     * @return El historial médico asociado al animal, o null si no se encuentra ningún historial médico para el animal.
     */
    private MedicalHistory findMedicalHistoryByAnimal(Animal animal) {
        for (MedicalHistory history : histories) {
            if (history.getAnimal().equals(animal)) {
                return history;
            }
        }
        return null; // Retorna null si no se encuentra el historial médico
    }

    /**
     * Busca y devuelve un objeto Volunteer de la lista de voluntarios basado en su ID.
     *
     * @param id El ID del voluntario que se desea buscar.
     * @return El objeto Volunteer con el ID correspondiente, o null si no se encuentra ningún voluntario con ese ID.
     */
    private Volunteer findVolunteerById(int id) {
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getId() == id) {
                return volunteer; // Retorna el voluntario encontrado
            }
        }
        return null; // Retorna null si no se encuentra el voluntario
    }

    /**
     * Busca y retorna un candidato de adopción basado en su ID único.
     * Recorre la lista de candidatos de adopción y compara el ID de cada
     * candidato con el ID proporcionado.
     *
     * @param id El ID único del candidato de adopción que se desea buscar.
     * @return El objeto AdoptionCandidate que coincide con el ID proporcionado,
     *         o null si no se encuentra ningún candidato con ese ID.
     */
    private AdoptionCandidate findAdopterById(int id) {
        for (AdoptionCandidate adopter : adoptionCandidates) {  // asumiendo que tienes una lista de candidatos
            if (adopter.getId() == id) {
                return adopter;
            }
        }
        return null;  // Retorna null si no se encuentra el adoptante
    }

    /**
     * Guarda toda la información de los animales en archivos CSV.
     * 
     * Este método se encarga de llamar a los métodos de cada clase relacionada 
     * para guardar sus datos en archivos CSV correspondientes. Si se produce 
     * un error durante el proceso de guardado, se captura la excepción y se 
     * muestra un mensaje de error.
     *
     * @throws IOException Si ocurre un error al intentar escribir en los archivos CSV.
     */
    public void saveAllData() {
        try {
            Animal.saveToCSV(animals, "animals.csv");
            Volunteer.saveToCSV(volunteers, "volunteers.csv");
            AdoptionCandidate.saveToCSV(adoptionCandidates, "adoption_candidates.csv");
            Adoption.saveToCSV(adoptions, "adoptions.csv");
            Resource.saveToCSV(resources, "resources.csv");
            Task.saveToCSV(tasks, "tasks.csv");
            MedicalHistory.saveToCSV(histories, "medical_histories.csv");
            System.out.println(" ");
            System.out.println("=====================================================");
            System.out.println("===                DATOS GUARDADOS                ===");
            System.out.println("=====================================================");
        } catch (IOException e) {
            System.out.println(" ");
            System.out.println("==============================");
            System.out.println("===          ERROR         ===");
            System.out.println("= Error al guardar los datos: " + e.getMessage());
            System.out.println("==============================");
            System.out.println("");
        }
    }

    /**
     * Solicita al usuario una fecha válida a través de un objeto {@code Scanner}.
     * El método sigue solicitando la fecha hasta que el usuario ingrese una en el formato correcto.
     *
     * @param scanner el objeto {@code Scanner} utilizado para leer la entrada del usuario
     * @return un objeto {@code LocalDate} que representa la fecha ingresada por el usuario
     */
    public LocalDate getValidDateFromUser(Scanner scanner) {
        LocalDate date = null;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Ingrese la fecha para filtrar los recursos (formato: YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                date = LocalDate.parse(dateInput);
                valid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, intente de nuevo.");
            }
        }
        return date;
    }

    /**
     * Solicita al usuario una fecha válida, filtra los recursos de acuerdo con esa fecha,
     * y muestra los recursos filtrados en la consola.
     *
     * @param sc el objeto {@code Scanner} utilizado para leer la entrada del usuario
     * @param resourceManager el objeto {@code ResourceManager} que gestiona los recursos
     */
    public void displayFilteredResourcesByDate(Scanner sc, ResourceManager resourceManager) {
        // Solicita una fecha válida al usuario
        LocalDate date = getValidDateFromUser(sc);

        // Filtra los recursos por la fecha ingresada
        List<Resource> filteredResources = resourceManager.filterResourcesByDate(date);

        // Muestra los recursos filtrados
        System.out.println("Recursos filtrados por la fecha " + date + ":");
        for (Resource resource : filteredResources) {
            System.out.println(resource);
        }
    }


    /**
     * Carga toda la información desde archivos CSV al iniciar el programa.
     * 
     * Este método se encarga de leer los archivos CSV y cargar la información
     * correspondiente en las listas de objetos. Si no se pueden cargar los datos,
     * se inicializan las listas con valores vacíos para evitar problemas en la 
     * ejecución del programa.
     *
     * @throws IOException Si ocurre un error al intentar leer los archivos CSV.
     * @throws Exception Si ocurre un error al intentar leer los archivos CSV del Historial Medico.
     */
    public void loadAllData() {
        try {
            animals = Animal.loadFromCSV("animals.csv");
        } catch (IOException e) {
            System.out.println("===============================================================");
            System.out.println("==      No se encuentra archivo animals.csv para cargar      ==");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            animals = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }
    
        try {
            volunteers = Volunteer.loadFromCSV("volunteers.csv");
        } catch (IOException e) {
            System.out.println("===============================================================");
            System.out.println("==    No se encuentra archivo volunteers.csv para cargar     ==");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            volunteers = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }
    
        try {
            adoptionCandidates = AdoptionCandidate.loadFromCSV("adoption_candidates.csv", animals, volunteers);
        } catch (IOException e) {
            System.out.println("===============================================================");
            System.out.println("= No se encuentra archivo adoption_candidates.csv para cargar =");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            adoptionCandidates = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }
    
        try {
            adoptions = Adoption.loadFromCSV("adoptions.csv", animals, volunteers, adoptionCandidates);
        } catch (IOException e) {
            System.out.println("===============================================================");
            System.out.println("==     No se encuentra archivo adoptions.csv para cargar     ==");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            adoptions = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }
    
        try {
            resources = Resource.loadFromCSV("resources.csv");
        } catch (IOException e) {
            System.out.println("===============================================================");
            System.out.println("==     No se encuentra archivo resources.csv para cargar     ==");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            resources = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }
    
        try {
            tasks = Task.loadFromCSV("tasks.csv", volunteers);
        } catch (IOException e) {
            System.out.println("===============================================================");
            System.out.println("==       No se encuentra archivo tasks.csv para cargar       ==");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            tasks = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }
        
        try {
            histories = MedicalHistory.loadFromCSV("medical_histories.csv", animals);
        } catch (Exception e) {
            System.out.println("===============================================================");
            System.out.println("== No se encuentra archivo medical_histories.csv para cargar ==");
            System.out.println("==                 Se inicia sin información                 ==");
            System.out.println("===============================================================");
            histories = new ArrayList<>(); // Inicializa la lista vacía si hay un error
        }

        System.out.println(" ");
        System.out.println("===============================================================");
        System.out.println("===                 FINALIZA CARGA DE DATOS                 ===");
        System.out.println("===============================================================");
        System.out.println("");
    }
    
    /**
 * Muestra el historial de tareas de un voluntario específico.
 * @param sc Scanner para entrada de usuario
 */
public void viewVolunteerTaskHistory(Scanner sc) {
    try {
        if (volunteers.isEmpty()) {
            System.out.println(" ");
            System.out.println("=======================================");
            System.out.println("= No hay voluntarios registrados     =");
            System.out.println("=======================================");
            System.out.println(" ");
            return;
        }

        // Mostrar lista de voluntarios
        System.out.println(" ");
        System.out.println("====================================");
        System.out.println("===     HISTORIAL DE TAREAS      ===");
        System.out.println("====================================");
        System.out.println("Voluntarios disponibles:");
        for (Volunteer volunteer : volunteers) {
            System.out.println("ID: " + volunteer.getId() + " - Nombre: " + volunteer.getName());
        }

        // Solicitar ID del voluntario
        System.out.print("\nIngrese el ID del voluntario: ");
        int volunteerId = Integer.parseInt(sc.nextLine());

        // Buscar el voluntario
        Volunteer selectedVolunteer = findVolunteerById(volunteerId);

        if (selectedVolunteer == null) {
            System.out.println(" ");
            System.out.println("====================================");
            System.out.println("===           ERROR             ===");
            System.out.println("= Voluntario no encontrado         =");
            System.out.println("====================================");
            System.out.println(" ");
            return;
        }

        // Filtrar tareas del voluntario
        List<Task> volunteerTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getVolunteer() != null && 
                task.getVolunteer().getId() == selectedVolunteer.getId()) {
                volunteerTasks.add(task);
            }
        }

        // Mostrar resultados
        System.out.println("\n=== Historial de Tareas ===");
        System.out.println("Voluntario: " + selectedVolunteer.getName());
        System.out.println("ID: " + selectedVolunteer.getId());
        System.out.println("Horas Trabajadas: " + selectedVolunteer.getHoursWorked());
        System.out.println("------------------------");

        if (volunteerTasks.isEmpty()) {
            System.out.println("Este voluntario no tiene tareas asignadas.");
        } else {
            // Mostrar tareas completadas
            System.out.println("\nTareas Completadas:");
            boolean hasCompletedTasks = false;
            for (Task task : volunteerTasks) {
                if (task.isCompleted()) {
                    System.out.println("- " + task.getTaskName());
                    System.out.println("  Descripción: " + task.getDescription());
                    System.out.println("------------------------");
                    hasCompletedTasks = true;
                }
            }
            if (!hasCompletedTasks) {
                System.out.println("No hay tareas completadas.");
            }

            // Mostrar tareas pendientes
            System.out.println("\nTareas Pendientes:");
            boolean hasPendingTasks = false;
            for (Task task : volunteerTasks) {
                if (!task.isCompleted()) {
                    System.out.println("- " + task.getTaskName());
                    System.out.println("  Descripción: " + task.getDescription());
                    System.out.println("------------------------");
                    hasPendingTasks = true;
                }
            }
            if (!hasPendingTasks) {
                System.out.println("No hay tareas pendientes.");
            }

            // Mostrar estadísticas
            int totalTasks = volunteerTasks.size();
            long completedTasks = volunteerTasks.stream()
                .filter(Task::isCompleted)
                .count();
            
            System.out.println("\nEstadísticas:");
            System.out.println("Total de tareas: " + totalTasks);
            System.out.println("Tareas completadas: " + completedTasks + 
                " (" + String.format("%.1f", (double)completedTasks/totalTasks * 100) + "%)");
            System.out.println("Tareas pendientes: " + (totalTasks - completedTasks) + 
                " (" + String.format("%.1f", (double)(totalTasks - completedTasks)/totalTasks * 100) + "%)");
            System.out.println("Promedio de horas por tarea: " + 
                String.format("%.2f", (double)selectedVolunteer.getHoursWorked()/totalTasks));
        }

    } catch (NumberFormatException e) {
        System.out.println(" ");
        System.out.println("====================================");
        System.out.println("===           ERROR             ===");
        System.out.println("= El ID debe ser un número entero  =");
        System.out.println("====================================");
        System.out.println(" ");
    } catch (Exception e) {
        System.out.println(" ");
        System.out.println("====================================");
        System.out.println("===           ERROR             ===");
        System.out.println("= " + e.getMessage() + " =");
        System.out.println("====================================");
        System.out.println(" ");
    }
}

public void viewFilteredResourcesByDate(Scanner sc) {
    System.out.println("Seleccione el período para filtrar recursos:");
    System.out.println("1. Último año");
    System.out.println("2. Último mes");
    System.out.println("3. Última semana");
    System.out.print("Seleccione una opción: ");
    
    int option = sc.nextInt();
    sc.nextLine(); // Limpiar el buffer

    List<Resource> filteredResources = new ArrayList<>();
    
    switch (option) {
        case 1:
            filteredResources = Resource.filterUpdatedInLastYear(resources);
            break;
        case 2:
            filteredResources = Resource.filterUpdatedInLastMonth(resources);
            break;
        case 3:
            filteredResources = Resource.filterUpdatedInLastWeek(resources);
            break;
        default:
            System.out.println("Opción no válida.");
            return;
    }

    // Mostrar los recursos filtrados
    if (filteredResources.isEmpty()) {
        System.out.println("No se encontraron recursos actualizados en el período seleccionado.");
    } else {
        System.out.println("Recursos actualizados:");
        for (Resource resource : filteredResources) {
            System.out.println("Nombre: " + resource.getResourceName() + ", Cantidad: " + resource.getQuantity());
        }
    }
}
}
 