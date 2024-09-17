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
 * Última modificación: 17/09/2024
 */

import java.util.Date; 
import java.util.Scanner;

public class Adoption {
    // Atributos de la clase que almacenan la información relevante de la adopción
    private String animalName;    // Nombre del animal que será adoptado
    private String adopterName;   // Nombre de la persona que adoptará al animal
    private Date adoptionDate;    // Fecha en la que se realizó la adopción

    // Se encarga de inicializar los valores de los atributos
    public Adoption(String animalName, String adopterName, Date adoptionDate) {
        this.animalName = animalName;         // Asignamos el nombre del animal
        this.adopterName = adopterName;       // Asignamos el nombre del adoptante
        this.adoptionDate = adoptionDate;     // Asignamos la fecha de adopción
    }

    /**
     * Método estático que confirma y registra una nueva adopción.
     * @param scanner
     * @return
     */
    public static Adoption confirmAdoption(Scanner scanner) {
        // Pedimos el nombre del animal a adoptar
        System.out.println("Ingrese el nombre del animal a adoptar:");
        String animalName = scanner.nextLine();  // Leemos el nombre del animal

        // Pedimos el nombre del adoptante
        System.out.println("Ingrese el nombre del adoptante:");
        String adopterName = scanner.nextLine(); // Leemos el nombre del adoptante

        // Creamos un objeto Date que almacena la fecha actual como la fecha de adopción
        Date adoptionDate = new Date();  // La fecha de adopción será la actual

        // Devolvemos una nueva instancia de la clase Adoption con los datos proporcionados
        return new Adoption(animalName, adopterName, adoptionDate);
    }

    /**
     * Método que muestra en consola los detalles de la adopción.
     */
    public void displayAdoptionDetails() {
        System.out.println("Detalles de la adopción:");
        System.out.println("Animal: " + animalName);        // Muestra el nombre del animal
        System.out.println("Adoptante: " + adopterName);    // Muestra el nombre del adoptante
        System.out.println("Fecha de adopción: " + adoptionDate); // Muestra la fecha de adopción
    }
}
