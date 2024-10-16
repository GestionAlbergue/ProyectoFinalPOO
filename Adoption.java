/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Adoption
 * 
 * La clase Adoption representa el proceso de adopción de un
 * animal en el Albegue
 * 
 * @author Antony Barrios 
 * Fecha de creación: 17/09/2024 
 * Última modificación: 13/10/2024
 */

 import java.io.*;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.ArrayList;
 import java.util.List;

public class Adoption {
    private Animal animal;               // El animal que ha sido adoptado
    private Volunteer volunteer;         // El voluntario que gestionó la adopción
    private LocalDate adoptionDate;      // La fecha de adopción
    private AdoptionCandidate adopter;   // Persona que lo adopto 

    /**
     * Constructor que inicializa una adopción con un animal, un voluntario, 
     * la fecha de adopción y el candidato adoptante.
     *
     * @param animal        El animal que ha sido adoptado.
     * @param volunteer     El voluntario encargado de gestionar la adopción.
     * @param adoptionDate  La fecha en la que se realizó la adopción.
     * @param adopter       El candidato adoptante que adopta el animal.
     */
    public Adoption(Animal animal, Volunteer volunteer, LocalDate adoptionDate, AdoptionCandidate adopter) {
        this.animal = animal;                 // Inicializa el animal adoptado
        this.volunteer = volunteer;           // Inicializa el voluntario que gestionó la adopción
        this.adoptionDate = adoptionDate;     // Inicializa la fecha de adopción
        this.adopter = adopter;               // Inicializa al adoptante    
    }

    /**
     * Obtiene el animal adoptado.
     * 
     * @return El animal que ha sido adoptado.
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Obtiene el voluntario que gestionó la adopción.
     * 
     * @return El voluntario que gestionó la adopción.
     */
    public Volunteer getVolunteer() {
        return volunteer;
    }

    /**
     * Obtiene la fecha de adopción.
     * 
     * @return La fecha en la que se realizó la adopción.
     */
    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    /**
     * Obtiene la fecha de adopción.
     * 
     * @return La fecha en la que se realizó la adopción.
     */
    public AdoptionCandidate getAdopter() {
        return adopter;
    }

    /**
     * Retorna los detalles de la adopción en formato de String.
     * Incluye información sobre el animal, el voluntario y la fecha de adopción.
     * 
     * @return Los detalles de la adopción en un formato legible.
     */
    public String displayAdoptionDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "-------------- ANIMAL -------------- \n" +
               "Animal adoptado: " + animal.getName() + " (ID: " + animal.getId() + ")\n" +
               "Raza: " + animal.getBreed() + "\n" +
               "Edad: " + animal.getAge() + " años\n" +
               "Descripción: " + animal.getDescription() + "\n" +
               "Gestionado por: " + volunteer.getName() + " (ID: " + volunteer.getId() + ")\n" +
               "Fecha de Adopción: " + adoptionDate.format(formatter) + "\n" +
               "------------- ADOPTANTE ------------- \n" +
               "Nombre: " + adopter.getName() + "\n" +
               "Informacion de Contacto: " + adopter.getContactInfo() + "\n";
    }

    /**
     * Formato de fecha utilizado para la serialización y deserialización de fechas en formato "yyyy-MM-dd".
     */
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Método para convertir un objeto Adoption en una línea CSV.
     * @return La representación en formato CSV del objeto Adoption.
     */
    public String toCSV() {
        return animal.getId() + "," + volunteer.getId() + "," + adoptionDate.format(dateFormatter) + "," + adopter.getId();
    }

    /**
     * Método para cargar un objeto Adoption desde una línea CSV.
     * @param csvLine La línea CSV que contiene la información del Adoption.
     * @param allAnimals Lista de todos los animales disponibles para buscar por ID.
     * @param allVolunteers Lista de todos los voluntarios disponibles para buscar por ID.
     * @param allCandidates Lista de todos los candidatos a adopción disponibles para buscar por ID.
     * @return El objeto Adoption creado a partir de la línea CSV.
     */
    public static Adoption fromCSV(String csvLine, List<Animal> allAnimals, List<Volunteer> allVolunteers, List<AdoptionCandidate> allCandidates) {
        String[] fields = csvLine.split(",");
        int animalId = Integer.parseInt(fields[0]);
        int volunteerId = Integer.parseInt(fields[1]);
        LocalDate adoptionDate = LocalDate.parse(fields[2], dateFormatter);
        int adopterId = Integer.parseInt(fields[3]);

        // Encontrar los objetos relacionados por ID
        Animal animal = findAnimalById(allAnimals, animalId);
        Volunteer volunteer = findVolunteerById(allVolunteers, volunteerId);
        AdoptionCandidate adopter = findAdoptionCandidateById(allCandidates, adopterId);

        // Crear y devolver la adopción
        return new Adoption(animal, volunteer, adoptionDate, adopter);
    }

    /**
     * Método para guardar la lista de adopciones a un archivo CSV.
     * @param adoptions Lista de adopciones a guardar en formato CSV.
     * @param filePath Ruta del archivo donde se guardará la información.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public static void saveToCSV(List<Adoption> adoptions, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Adoption adoption : adoptions) {
                writer.write(adoption.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Método para cargar la lista de adopciones desde un archivo CSV.
     * @param filePath Ruta del archivo CSV desde donde se cargarán las adopciones.
     * @param allAnimals Lista de todos los animales disponibles para asociar con las adopciones.
     * @param allVolunteers Lista de todos los voluntarios disponibles para asociar con las adopciones.
     * @param allCandidates Lista de todos los candidatos a adopción disponibles para asociar con las adopciones.
     * @return Lista de adopciones cargadas desde el archivo CSV.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public static List<Adoption> loadFromCSV(String filePath, List<Animal> allAnimals, List<Volunteer> allVolunteers, List<AdoptionCandidate> allCandidates) throws IOException {
        List<Adoption> adoptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                adoptions.add(Adoption.fromCSV(line, allAnimals, allVolunteers, allCandidates));
            }
        }
        return adoptions;
    }

    /**
     * Método auxiliar para encontrar un animal por su ID.
     * @param allAnimals Lista de todos los animales donde se realizará la búsqueda.
     * @param id ID del animal a buscar.
     * @return El animal encontrado o {@code null} si no se encuentra ningún animal con ese ID.
     */
    private static Animal findAnimalById(List<Animal> allAnimals, int id) {
        for (Animal animal : allAnimals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        return null;
    }

    /**
     * Método auxiliar para encontrar un voluntario por su ID.
     * @param allVolunteers Lista de todos los voluntarios donde se realizará la búsqueda.
     * @param id ID del voluntario a buscar.
     * @return El voluntario encontrado o {@code null} si no se encuentra ningún voluntario con ese ID.
     */
    private static Volunteer findVolunteerById(List<Volunteer> allVolunteers, int id) {
        for (Volunteer volunteer : allVolunteers) {
            if (volunteer.getId() == id) {
                return volunteer;
            }
        }
        return null;
    }

    /**
     * Método auxiliar para encontrar un candidato a adopción por su ID.
     * @param allCandidates Lista de todos los candidatos a adopción donde se realizará la búsqueda.
     * @param id ID del candidato a adopción a buscar.
     * @return El candidato a adopción encontrado o {@code null} si no se encuentra ningún candidato con ese ID.
     */
    private static AdoptionCandidate findAdoptionCandidateById(List<AdoptionCandidate> allCandidates, int id) {
        for (AdoptionCandidate candidate : allCandidates) {
            if (candidate.getId() == id) {
                return candidate;
            }
        }
        return null;
    }
}
