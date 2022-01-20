package kingdoms.kingdoms;

/**
<p>Class implementing a theoretical person. Because aa ordinary person has no use in this game, a person cannot be created. It serves only as template for a ruler</p>
<p>Person has only an age</p>
@author Jakub Niewiarowski
 */

public abstract class Person extends BaseObject {

    private short age;

    /**
    Constructs a Person with given name and age
    @param name Name of the person
    @param age Age of a person
    */
    public Person(String name, short age) {
        super(name);
        this.age = age;
    }

    /**
    @return Age of the person
    */
    public short getAge()
    {
        return age;
    }

    /**
    Sets a new age for the person
    @param age New age of th person 
    */
    public void setAge(short age) { this.age = age; }

    /**
    Incrementing an age of the person (method for aging of the person)
    */
    public abstract void Age();
}
