/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase AdoptionCandidate
 * 
 * La clase representa a un candidato para la adopción de un animal en un refugio.
 * Esta clase maneja la información sobre el adoptante, la experiencia con mascotas, el motivo de la adopción
 * y cualquier experiencia adicional requerida para animales peligrosos.
 * 
 * @author Angel Higueros
 * Fecha de creación: 18/09/2024 
 * Última modificación: 03/11/2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptionCandidate {
    private List<Animal> animals;            // Lista por si un Adoptante, adopta más de un animal
    private Volunteer volunteer;             // Voluntario que esta en contacto con el adoptante
    private String reasonForAdoption,        // Motivo de la adopción  
                   name,                     // Nombre 
                   contactInfo;              // Información de Contacto
    private boolean hasPetExperience;        // Indicador de experiencia  
    private boolean additionalExperience;    // Indicador de experiencia  
    private int id;                          // ID del voluntario
    private static int idCounter = 1;        // ID único ascendiente  

    /**
     * Constructor que inicializa un candidato de adopción con la información del 
     * candidato y una lista de animales considerados para la adopción.
     *
     * @param name                 El nombre del candidato.
     * @param contactInfo          La información de contacto del candidato.
     * @param reasonForAdoption    La razón por la cual el candidato desea adoptar un animal.
     * @param hasPetExperience     Indica si el candidato tiene experiencia previa con mascotas.
     * @param additionalExperience Indica si el candidato tiene experiencia adicional en el cuidado de animales.
     * @param volunteer            Indica el voluntario asociado
     */   
    public AdoptionCandidate(String name, String contactInfo, String reasonForAdoption, boolean hasPetExperience, boolean additionalExperience, Volunteer volunteer) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.reasonForAdoption = reasonForAdoption;
        this.hasPetExperience = hasPetExperience;
        this.additionalExperience = additionalExperience;
        this.volunteer = volunteer;
        this.animals = new ArrayList<>();
        this.id = idCounter++;
    }

    /**
     * Obtiene ID el voluntario.
     * 
     * @return ID del Voluntario.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la información de contacto del voluntario.
     *
     * @return Lista de Animales que contiene los animales adoptados por el adoptante
     */
    public List<Animal> getAnimals() {
        return animals;
    }

    /**
     * Proporciona un resumen de los detalles de la adopción, incluyendo el nombre del animal,
     * el adoptante, la experiencia con mascotas y cualquier experiencia adicional si es necesario.
     *
     * @return Un String con los detalles resumidos del adoptante
    */ 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adoptante ID: ").append(id).append("\n")
        .append("Nombre: ").append(name).append("\n")
        .append("Información de Contacto: ").append(contactInfo).append("\n")
        .append("Motivo para la adopción: ").append(reasonForAdoption).append("\n")
        .append("Experiencia con mascotas: ").append(hasPetExperience ? "Si" : "No").append("\n")
        .append("Experiencia con Animales Peligrosos: ").append(additionalExperience ? "Si" : "No").append("\n");

        // Mostrar los animales adoptados por este adoptante, si los hay
        if (animals.isEmpty()) {
            sb.append("No ha adoptado ningún animal aún.\n");
        } else {
            sb.append("Animales adoptados:\n");
            for (Animal animal : animals) {
                sb.append("- ").append(animal.getName()).append(", Raza: ").append(animal.getBreed()).append(", Edad: ").append(animal.getAge()).append("\n");
            }
        }

        sb.append("Voluntario encargado: ").append(volunteer != null ? volunteer.getName() : "Ninguno").append("\n");
        return sb.toString();
    }

    /**
     * Proporciona el número de los animales adoptados de forma detallada.
     *
     * @return Un String con los detalles de la cantidad de animales adoptados.
    */ 
    public String getCantidadAdoptados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adoptante ID: ").append(id).append("\n")
        .append("Nombre: ").append(name).append("\n")
        .append("Información de Contacto: ").append(contactInfo).append("\n")
        .append("Número de animales adoptados: ").append(animals.size()).append("\n");

        return sb.toString();
    }

    /**
     * Obtiene el nombre del voluntario.
     *
     * @return Un String que representa el nombre del voluntario.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene la información de contacto del adoptante.
     *
     * @return String que contiene la información de contacto del adoptante.
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * Obtiene la información de si tiene experiencia con animales peligrosos.
     *
     * @return boolean que contiene la información si tiene experiencia con animales peligrosos.
     */
    public boolean getAdditionalExperience() {
        return additionalExperience;
    }    

    /**
     * Obtiene la información del voluntario encargado de la gestión del adoptante.
     *
     * @return volunteer El voluntario encargado del proceso de adopción.
     */
    public Volunteer getVolunteer() {
        return volunteer;
    }

    /**
     * Añade el voluntario que gestiona el contacto con el adoptante.
     * @param volunteer El voluntario encargado del proceso de adopción.
     */
    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    /**
     * Añade un animal a la lista del adoptante.
     */
    public void addAnimal(Animal animal) {
        if (animals == null) {
            animals = new ArrayList<>();  // Inicializar la lista si está vacía
        }
        animals.add(animal);  // Agregar el animal a la lista
    }


    /**
     * Convierte un objeto AdoptionCandidate en una línea de texto en formato CSV.
     *
     * @return Una cadena CSV que representa los datos del candidato a adopción.
     */
    public String toCSV() {
        StringBuilder animalIds = new StringBuilder();
        for (Animal animal : animals) {
            animalIds.append(animal.getId()).append(";");  // Usamos ";" para separar múltiples IDs de animales
        }
        // Quitar el último ";"
        if (animalIds.length() > 0) {
            animalIds.setLength(animalIds.length() - 1);
        }
        
        // Formato CSV
        return id + "," + name + "," + contactInfo + "," + reasonForAdoption + "," + hasPetExperience + "," + additionalExperience + "," + volunteer.getId() + "," + animalIds.toString();
    }

    /**
     * Carga un objeto AdoptionCandidate desde una línea de texto en formato CSV.
     *
     * @param csvLine La línea de texto en formato CSV.
     * @param allAnimals La lista de todos los animales disponibles.
     * @param allVolunteers La lista de todos los voluntarios disponibles.
     * @return Un objeto AdoptionCandidate creado a partir de la línea CSV.
     */
    public static AdoptionCandidate fromCSV(String csvLine, List<Animal> allAnimals, List<Volunteer> allVolunteers) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String contactInfo = fields[2];
        String reasonForAdoption = fields[3];
        boolean hasPetExperience = Boolean.parseBoolean(fields[4]);
        boolean additionalExperience = Boolean.parseBoolean(fields[5]);
        int volunteerId = Integer.parseInt(fields[6]);

        // Encontrar el voluntario por ID
        Volunteer volunteer = findVolunteerById(allVolunteers, volunteerId);

        // Crear el candidato
        AdoptionCandidate candidate = new AdoptionCandidate(name, contactInfo, reasonForAdoption, hasPetExperience, additionalExperience, volunteer);
        candidate.id = id;  // Asignar el ID manualmente
        idCounter = Math.max(idCounter, id + 1);  // Actualizar el contador de ID si es necesario

        // Cargar la lista de animales adoptados
        if (fields.length > 7 && !fields[7].isEmpty()) {
            String[] animalIds = fields[7].split(";");
            for (String animalIdStr : animalIds) {
                int animalId = Integer.parseInt(animalIdStr);
                Animal animal = findAnimalById(allAnimals, animalId);
                if (animal != null) {
                    candidate.animals.add(animal);
                }
            }
        }

        return candidate;
    }

    /**
     * Guarda una lista de candidatos a adopción en un archivo CSV.
     *
     * @param candidates La lista de candidatos a adopción.
     * @param filePath La ruta del archivo CSV donde se guardarán los datos.
     * @throws IOException Si ocurre un error al escribir en el archivo CSV.
     */
    public static void saveToCSV(List<AdoptionCandidate> candidates, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (AdoptionCandidate candidate : candidates) {
                writer.write(candidate.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Carga una lista de candidatos a adopción desde un archivo CSV.
     *
     * @param filePath La ruta del archivo CSV desde donde se cargarán los datos.
     * @param allAnimals La lista de todos los animales disponibles.
     * @param allVolunteers La lista de todos los voluntarios disponibles.
     * @return Una lista de objetos AdoptionCandidate cargados desde el archivo CSV.
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    public static List<AdoptionCandidate> loadFromCSV(String filePath, List<Animal> allAnimals, List<Volunteer> allVolunteers) throws IOException {
        List<AdoptionCandidate> candidates = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                candidates.add(AdoptionCandidate.fromCSV(line, allAnimals, allVolunteers));
            }
        }
        return candidates;
    }

    /**
     * Método auxiliar para encontrar un animal por ID que encuentra un animal por su ID en una lista de animales.
     *
     * @param allAnimals La lista de todos los animales.
     * @param id El ID del animal a buscar.
     * @return El objeto Animal si se encuentra, o null si no existe.
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
     * Método auxiliar para encontrar un voluntario en una lista de voluntarios.
     *
     * @param allVolunteers La lista de todos los voluntarios.
     * @param id El ID del voluntario a buscar.
     * @return El objeto Volunteer si se encuentra, o null si no existe.
     */
    private static Volunteer findVolunteerById(List<Volunteer> allVolunteers, int id) {
        for (Volunteer volunteer : allVolunteers) {
            if (volunteer.getId() == id) {
                return volunteer;
            }
        }
        return null;
    }
}
