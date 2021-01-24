public class SortedMetodBubble implements Sorted {

  @Override
  public void sortedPerson(Person[] people) {
    for (int j = people.length - 1; j >= 0; j--) {
      for (int i = 0; i < j; i++) {
        if (!people[i].getSex().equals(people[i + 1].getSex())) {
          if (people[i].getSex().equals(Sex.WOMEN)) {
            permutationPersons(people, i);
          }
        } else if (people[i].getAge() < people[i + 1].getAge()) {
          permutationPersons(people, i);
        } else if (people[i].getAge() == people[i + 1].getAge()) {
          int cash = people[i].getName().compareTo(people[i + 1].getName());
          if (cash > 0) {
            permutationPersons(people, i);
          } else if (cash == 0) {
            throw new RuntimeException();
          }
        }
      }
    }
  }

  private void permutationPersons(Person[] people, int i) {
    Person temp;
    temp = people[i];
    people[i] = people[i + 1];
    people[i + 1] = temp;
  }
}
