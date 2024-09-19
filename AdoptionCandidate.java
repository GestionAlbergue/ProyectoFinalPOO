public class AdoptionCandidate {
    private Animal animal;
    private Volunteer volunteer;
    private String additionalExperience; // For dangerous animals  
    private boolean hasPetExperience; // Experience indicator  
    private String reasonForAdoption; // Reason for adopting  

    public AdoptionCandidate(Animal animal, Volunteer volunteer) {
        this.animal = animal;
        this.volunteer = volunteer;
    }

    public void collectAdoptionInfo(Scanner sc) {
        System.out.print("¿Tienes experiencia con mascotas? (Sí/No): ");
        String experienceResponse = sc.nextLine();
        this.hasPetExperience = experienceResponse.equalsIgnoreCase("Sí");

        System.out.print("¿Cuál es tu razón para adoptar este animal?: ");
        this.reasonForAdoption = sc.nextLine();

        if (animal.getDangerLevel() > 0) {
            System.out.println("Este animal tiene un nivel de peligro: " + animal.getDangerLevelDescription());
            System.out.print("Ingrese su experiencia con animales peligrosos: ");
            additionalExperience = sc.nextLine();
        }

        animal.setAdopted(true); // Mark the animal as adopted  
    }

    public String summarizeAdoptionDetails() {
        return "Detalles de adopción:\n"
                + "- Animal: " + animal.getName() + "\n"
                + "- Adoptante: " + volunteer.getName() + "\n"
                + "- Experiencia con mascotas: " + (hasPetExperience ? "Sí" : "No") + "\n"
                + "- Razón para adoptar: " + reasonForAdoption + "\n"
                + (animal.getDangerLevel() > 0 ? "- Experiencia adicional: " + additionalExperience + "\n" : "");
    }
}