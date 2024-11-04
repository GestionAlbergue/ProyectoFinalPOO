/**
 * Universidad del Valle de Guatemala - Segundo Semestre 2024
 * Programación Orientada a Objetos (POO)
 * Proyecto Final 2
 * 
 * Clase ResourceManager
 * 
 * La clase ResourceManager administra una lista de recursos y proporciona
 * métodos para filtrar y acceder a esos recursos según ciertos criterios.
 * 
 * @author Angel Higueros
 * Fecha de creación: 03/1q/2024 
 * Última modificación: 03/11/2024
 */

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class ResourceManager {
    private List<Resource> resources; // Lista de Recursos 

    /**
     * Constructor que inicializa el {@code ResourceManager} con una lista de recursos.
     *
     * @param resources la lista de recursos a gestionar
     */
    public ResourceManager(List<Resource> resources) {
        this.resources = resources;
    }

    /**
     * Filtra los recursos por una fecha específica.
     *
     * @param date la fecha para filtrar los recursos
     * @return una lista de recursos cuya fecha coincide con la especificada
     */
    public List<Resource> filterResourcesByDate(LocalDate date) {
        return resources.stream()
                .filter(resource -> resource.getDate().isEqual(date))
                .collect(Collectors.toList());
    }
}    