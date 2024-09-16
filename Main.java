import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    // private List<Adoption> adoptions;
    // private List<Resource> resources;

    public Main() {
        animals = new ArrayList<>();
        volunteers = new ArrayList<>();
        // adoptions = new ArrayList<>();
        // resources = new ArrayList<>();
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        MainPage mainPage = new MainPage();
        String option;

        do {
            mainPage.displayOptions();
            option = scanner.nextLine();
            mainPage.navigate(option, this, scanner);
        } while (!option.equals("exit"));
        
        scanner.close();
    }
    
}
