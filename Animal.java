/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Animal
 * 
 * La clase Animal representa a un animal en el sistema de gestión de albergue.
 * Cada animal tiene un nombre, raza, edad, descripción y un estado de adopción.
 * 
 * @author Daniela Navas
 * Fecha de creación: 12/09/2024 
 * Última modificación: 12/10/2024
 */
import java.io.*;
import java.util.*;

public class Animal {
    private String name,                  // Nombre del animal
                   breed,                 // Raza del animal
                   description;           // Descripción del animal
    private int age,                      // Edad del animal
                id;                       // ID del animal
    private static int idCounter = 1;     // ID único ascendiente 
    private boolean adopted,              // Estado de adopción (true si está adoptado, false si no)
                    dangerLevel;          // Nivel de peligro (true si es peligroso, false si no)

    /**
     * Constructor de la clase Animal.
     * 
     * @param name        El nombre del animal.
     * @param breed       La raza del animal.
     * @param age         La edad del animal.
     * @param description Una breve descripción del animal.
     */
    public Animal(String name, String breed, String description, int age, boolean adopted, boolean dangerLevel) {
        this.id = idCounter++;        // Asignar ID único y aumentar el contador
        this.name = name;                 // Inicializa el nombre
        this.breed = breed;               // Inicializa la raza
        this.age = age;                   // Inicializa la edad
        this.description = description;   // Inicializa la descripción
        this.dangerLevel = dangerLevel;   // Inicializa el danger level
        this.adopted = adopted;             // Inicializa el estado de adopción como no adoptado
    }

    /**
     * Obtiene el Nivel de Peligro del Animal en número
     * 
     * @return el número de Peligro de, animal.
     */
    public boolean getDangerLevel() {
        return this.dangerLevel;
    }

    /**
     * Obtiene la descripación del Nivel de Peligro del Animal 
     * 
     * @return la descripción del Nivel de Peligro de, animal.
     */
    public String getDangerLevelDescription() {
        if(dangerLevel){
            return "Peligroso";
        } else if(!dangerLevel){
            return "No Peligroso";
        } else{
            return "Nivel de peligro desconocido";
        }
    }

    /**
     * Obtiene el nombre del animal.
     * 
     * @return El nombre del animal.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtiene la raza del animal.
     * 
     * @return La raza del animal.
     */
    public String getBreed() {
        return this.breed;
    }

    /**
     * Obtiene la edad del animal.
     * 
     * @return La edad del animal.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Obtiene la descripción del animal.
     * 
     * @return La descripción del animal.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Verifica si el animal ha sido adoptado.
     * 
     * @return true si el animal está adoptado, false en caso contrario.
     */
    public boolean isAdopted() {
        return this.adopted;
    }

    /**
     * Establece el estado de adopción del animal.
     * 
     * @param adopted true si el animal ha sido adoptado, false si no.
     */
    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    /**
     * Obtiene ID del animal.
     * 
     * @return ID del animal.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sobreescribe el método toString para dar información detallada del animal 
     * 
     * @return String de detalles del animal.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------\n" +
            "ID: " + this.id + "\n" +
            "Nombre: " + this.name + "\n" +
            "Raza: " + this.breed + "\n" +
            "Edad: " + this.age + "\n" +
            "Descripción: " + this.description + "\n" +
            "Peligrosidad: " + this.getDangerLevelDescription() + "\n" +
            "-------------------------------------------------------\n";
    }

    /**
     * Convierte un objeto Animal en una línea CSV.
     *
     * @return Una cadena que representa el objeto Animal en formato CSV, donde los campos están separados por comas.
     */
    public String toCSV() {
        return id + "," + name + "," + breed + "," + description + "," + age + "," + adopted + "," + dangerLevel;
    }

    /**
     * Carga un objeto Animal desde una línea CSV.
     *
     * @param csvLine La línea CSV que contiene los datos del Animal, donde los campos están separados por comas.
     * @return Un objeto Animal con los atributos cargados desde la línea CSV.
     * @throws NumberFormatException Si los valores numéricos en la línea CSV no pueden ser convertidos correctamente.
     */
    public static Animal fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        String breed = fields[2];
        String description = fields[3];
        int age = Integer.parseInt(fields[4]);
        boolean adopted = Boolean.parseBoolean(fields[5]);
        boolean dangerLevel = Boolean.parseBoolean(fields[6]);

        // Crear el animal con el ID ya cargado
        Animal animal = new Animal(name, breed, description, age, adopted, dangerLevel);
        animal.id = id;  // Asignar el ID manualmente
        return animal;
    }

    /**
     * Guarda la lista de animales en un archivo CSV.
     *
     * @param animals La lista de objetos Animal que se desea guardar en el archivo.
     * @param filePath La ruta del archivo donde se guardarán los datos.
     * @throws IOException Si ocurre un error al intentar escribir en el archivo.
     */
    public static void saveToCSV(List<Animal> animals, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Animal animal : animals) {
                writer.write(animal.toCSV());
                writer.newLine();
            }
        }
    }

    /**
     * Carga la lista de animales desde un archivo CSV.
     *
     * @param filePath La ruta del archivo desde donde se cargarán los datos.
     * @return Una lista de objetos Animal cargados desde el archivo CSV.
     * @throws IOException Si ocurre un error al intentar leer el archivo.
     */
    public static List<Animal> loadFromCSV(String filePath) throws IOException {
        List<Animal> animals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                animals.add(Animal.fromCSV(line));
            }
        }
        return animals;
    }
}
