package hello;

/*
    This is a class that stores information like a row in our 
    pet database table. All of these functions are getters and setters
    and give us a way to access the private variables associated with an
    instance of the Pet class.
*/
public class Pet{

    private int id;
    private String name;
    private String type;
    private String color;
    private String date;
    private Boolean checked_in;
    private int owner_id;

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getDate(){
        return this.date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public Boolean getCheckedIn(){
        return this.checked_in;
    }

    public void setCheckedIn(Boolean checked_in){
        this.checked_in = checked_in;
    }

    public int getOwnerId(){
        return this.owner_id;
    }

    public void setOwnerId(int id){
        this.owner_id = id;
    }

    public Pet(){}

    //This is a constructor function. When you declare a new instance of this class you can 
    //pass in a set of values that will initialize this class instance with all the data accessible.
    //this is essentially just a container for our pet.
    public Pet(int id, String name, String type, String color, String date, Boolean checked_in, int owner_id){
        this.id = id;
        this.name = name;
        this.type = type;
        this.color = color;
        this.date = date;
        this.checked_in = checked_in;
        this.owner_id = owner_id;
    }
}