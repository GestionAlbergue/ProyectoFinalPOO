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
 * Última modificación: 12/10/2024
 */

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
        this.quantity = quantity;
        this.checkAlert();  
    }

    /**
     * Método para agregar un nuevo recurso mediante la entrada del usuario.
     *
     * @param scanner El objeto Scanner para leer la entrada del usuario.
     * @return Un nuevo objeto Resource con los datos proporcionados por el usuario.
     */
    public Resource addResource(Scanner scanner) {
        System.out.println("Ingrese el nombre del recurso: ");
        String resourceName = scanner.nextLine();
        
        System.out.println("Ingrese la cantidad inicial: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Ingrese la descripción del recurso: ");
        String description = scanner.nextLine();

        return new Resource(resourceName, quantity, description);
    }

    /**
     * Verifica si es necesario generar una alerta de reabastecimiento basada en un umbral de cantidad.
     *
     * @return Un mensaje de alerta si la cantidad es menor o igual al umbral, o null si no se necesita alerta.
     */
    private String checkAlert() {
        int threshold = 10;  
        if (quantity <= threshold) {
            return "==========================================\n" +
                   "===               ALERTA               ===\n" + 
                   "= Es necesario reabastecer " + resourceName + "\n" +
                   "= Cantidad actual: " + quantity + "\n" +
                   "==========================================\n";
        }
        return null; // No hay alerta
    }

    /**
     * Devuelve el mensaje de alerta si la cantidad del recurso es baja.
     *
     * @return Un mensaje de alerta o null si no es necesario.
     */
    public String getAlertMessage() {
        return checkAlert();
    }
}
