public class Person {

    // старайтесь делать переменный финальными когда это возможно
    private Sex sex;
    private String name;
    private int age;

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
