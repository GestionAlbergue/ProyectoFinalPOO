public class Animal {
    private String name;
    private String breed;
    private int age;
    private String description;
    private boolean adopted;

    public Animal(String name, String breed, int age, String description){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.description = description;
    }

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