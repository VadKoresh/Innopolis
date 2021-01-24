public class Person {

    private final Sex sex;
    private final String name;
    private final int age;

    public Person(Sex sex, String name, int age) {
        this.sex = sex;
        this.name = name;
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


}
