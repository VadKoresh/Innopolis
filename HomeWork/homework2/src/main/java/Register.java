public class Register {

    private Person[] people;

    public Register(int countPersons) {
        people = new Person[countPersons];
        add();
    }

    private void add() {
        for (int i = 0; i < people.length; i++) {
            people[i] = CreatPerson.creatPerson();
        }
    }

    public Person[] getPeople() {
        return people;
    }

    public void sortedBubble() {
        new SortedMetodBubble().sortedPerson(people);
    }

    public void sortPast() {
        new SotredInsertMetod().sortedPerson(people);
    }
}
