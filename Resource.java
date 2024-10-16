/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final 2
 * 
 * Clase Resource
 * 
 * La clase Resource representa un recurso con un nombre, cantidad disponible y descripción.
 * También permite la actualización de la cantidad y la generación de alertas de reabastecimiento.
 * 
 * @author Adriana Martinez 
 * Fecha de creación: 18/09/2024 
 * Última modificación: 15/10/2024
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Resource {
    private String resourceName;  // Nombre del recurso
    private int quantity;         // Cantidad del recurso disponible
    private String description;   // Descripción del recurso

    /**
     * Constructor para inicializar un recurso con su nombre, cantidad y descripción.
     *
     * @param resourceName El nombre del recurso.
     * @param quantity La cantidad disponible del recurso.
     * @param description Una breve descripción del recurso.
     */
    public Resource(String resourceName, int quantity, String description) {
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.description = description;
    }

    /**
     * Devuelve el nombre del recurso.
     *
     * @return El nombre del recurso.
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * Devuelve la cantidad disponible del recurso.
     *
     * @return La cantidad disponible del recurso.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Devuelve la descripción del recurso.
     *
     * @return La descripción del recurso.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Actualiza la cantidad disponible del recurso.
     * Después de actualizar la cantidad, verifica si es necesario generar una alerta de reabastecimiento.
     *
     * @param quantity La nueva cantidad del recurso.
     */
    public void updateQuantity(int quantity) {
        
    }

    /**
     * Método para agregar o actualizar un recurso mediante la entrada del usuario.
     * Si el recurso ya existe, se actualiza la cantidad; de lo contrario, se agrega un nuevo recurso.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @param resources La lista de recursos existentes.
     * @param filePath La ruta del archivo CSV.
     */
    public static void addOrUpdateResource(Scanner scanner, List<Resource> resources, String filePath) {
        
    }

    /**
     * Verifica si es necesario generar una alerta de reabastecimiento basada en un umbral de cantidad.
     *
     * @return Un mensaje de alerta si la cantidad es menor o igual al umbral, o null si no se necesita alerta.
     */
    private String checkAlert() {
        
    }

    /**
     * Devuelve el mensaje de alerta si la cantidad del recurso es baja.
     *
     * @return Un mensaje de alerta o null si no es necesario.
     */
    public String getAlertMessage() {
        return checkAlert();
    }

    /**
     * Guarda la lista de recursos en un archivo CSV.
     *
     * @param resources La lista de recursos.
     * @param filePath La ruta del archivo CSV.
     */
    public static void saveToCSV(List<Resource> resources, String filePath) {
        
    }

    /**
     * Carga la lista de recursos desde un archivo CSV.
     *
     * @param filePath La ruta del archivo CSV.
     * @return La lista de recursos.
     */
    public static List<Resource> loadFromCSV(String filePath) {
        
    }

    /**
     * Convierte el recurso a una línea de formato CSV.
     *
     * @return La representación del recurso en formato CSV.
     */
    public String toCSV() {
        return resourceName + "," + quantity + "," + description;
    }
}
