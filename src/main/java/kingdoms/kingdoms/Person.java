package kingdoms.kingdoms;

public abstract class Person extends BaseObject {

    private short age;

    public Person(String name, short age) {
        super(name);
        this.age = age;
    }

    public short getAge()
    {
        return age;
    }

    public void setAge(short age) { this.age = age; }

    public abstract void Age();

    public abstract String ToString();
}
