public class Volunteer {
    private String name, 
                   contactInfo;
    private int hoursWorked;

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
