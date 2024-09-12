/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final
 * 
 * Clase Volunteer
 * 
 * La clase Volunteer representa a un voluntario en el sistema de gestión de un albergue.
 * Cada voluntario tiene un nombre, información de contacto y un registro de horas trabajadas.
 * 
 * @author Daniela Navas
 * Fecha de creación: 12/09/2024 
 * Última modificación: 12/09/2024
 */

public class Volunteer {
    // Atributos de la clase Volunteer
    private String name,         // Nombre del voluntario
                   contactInfo;  // Información de contacto del voluntario
    private int hoursWorked;     // Cantidad de horas trabajadas por el voluntario

    public Volunteer(String name, String contactInfo){
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    public String getName(){
        return name;
    }

    public String getContactInfo(){
        return contactInfo;
    }
    
    public int getHoursWorked(){
        return hoursWorked;
    }

    public void addHours(int hours){
        this.hoursWorked = hoursWorked + hours;
    }



}
