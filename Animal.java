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
 * Última modificación: 12/09/2024
 */

public class Animal {
    // Atributos de la clase Animal
    private String name;         // Nombre del animal
    private String breed;        // Raza del animal
    private int age;             // Edad del animal
    private String description;  // Descripción del animal
    private boolean adopted;     // Estado de adopción (true si está adoptado, false si no)

    public String getName(){
        return name;
    }

    public String getBreed(){
        return breed;
    }
    
    public int getAge(){
        return age;
    }

    public String getDescription(){
        return description;
    }
    
    public boolean isAdopted(){
        return adopted;
    }
    
    public void setAdopted(boolean adopted){
        this.adopted = adopted;
    }

}