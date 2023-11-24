public class Student {
    // Define class atributes
    public int id;
    public String name;
    public int age;
    public float distanceToCollege;

    // Define the constructor
    public Student(String name, int age, float distanceToCollege) {
        this.name = name;
        this.age = age;
        this.distanceToCollege = distanceToCollege;
    }

    // Define getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getDistanceToCollege() {
        return distanceToCollege;
    }

    public void setDistanceToCollege(float distanceToCollege) {
        this.distanceToCollege = distanceToCollege;
    }

    // Define toString method
    @Override
    public String toString() {
        return id + ". " + name + " (" + age + ") - " + distanceToCollege;
    }
}
