import java.util.Scanner;

public class Resource {
    // Atributos de la clase
    private String resourceName;  // Nombre del recurso
    private int quantity;         // Cantidad del recurso disponible
    private String description;   // Descripción del recurso

    // Constructor para inicializar un recurso con sus atributos
    public Resource(String resourceName, int quantity, String description) {
        this.resourceName = resourceName;
        this.quantity = quantity;
        this.description = description;
    }

    // Métodos 'getter' para acceder a cada atributo

    // Devuelve el nombre del recurso
    public String getResourceName() {
        return resourceName;
    }

    // Devuelve la cantidad disponible del recurso
    public int getQuantity() {
        return quantity;
    }

    // Devuelve la descripción del recurso
    public String getDescription() {
        return description;
    }

    // Actualiza la cantidad disponible del recurso
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
        checkAlert();  
    }

    // Método para agregar nuevos recursos sin imprimir
    public static Resource addResource(Scanner scanner) {
        
        System.out.println("Ingrese el nombre del recurso: ");
        String resourceName = scanner.nextLine();
        
        System.out.println("Ingrese la cantidad inicial: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Ingrese la descripción del recurso: ");
        String description = scanner.nextLine();

        return new Resource(resourceName, quantity, description);
    }

    // Verifica si se debe generar una alerta de reabastecimiento devolviendo el mensaje sin imprimirlo
    private String checkAlert() {
        int threshold = 10;  
        if (quantity <= threshold) {
            return "Alerta: Es necesario reabastecer el recurso " + resourceName + ". Cantidad actual: " + quantity;
        }
        return null; // No hay alerta
    }

    // Método que revisa si se debe hacer una alerta y devuelve el mensaje de la alerta o null
    public String getAlertMessage() {
        return checkAlert();
    }
}
