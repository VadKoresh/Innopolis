public class SortedInsertMethod implements Sorted {

  @Override
  public void sortedPerson(Person[] people) {

    for (int i = 1; i < people.length; i++) {
      Person current = people[i];
      int j = i - 1;

      while (j >= 0 && current.getSex().equals(Sex.MEN) && !current.getSex()
          .equals(people[j].getSex())) {
        people[j + 1] = people[j];
        j--;
      }
      if (j >= 0 && current.getSex().equals(people[j].getSex())) {
        while (j >= 0 && current.getSex().equals(people[j].getSex()) && current.getAge() > people[j]
            .getAge()) {
          people[j + 1] = people[j];
          j--;
        }
        if (j >= 0 && current.getAge() == people[j].getAge()) {
          while (j >= 0 && current.getAge() == people[j].getAge()
              && current.getName().compareTo(people[j].getName()) < 0) {
            people[j + 1] = people[j];
            j--;
          }
          if (j >= 0 && current.getName().compareTo(people[j].getName()) == 0) {
            throw new RuntimeException();
          }
        }
      }
      people[j + 1] = current;
    }

  }

}
