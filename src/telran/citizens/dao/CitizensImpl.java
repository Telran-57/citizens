package telran.citizens.dao;

import telran.citizens.model.Person;

import java.time.LocalDate;
import java.util.*;

public class CitizensImpl implements Citizens {
    private static Comparator<Person> lastNameComparator;
    private static Comparator<Person> ageComparator;
    private TreeSet<Person> idList;
    private TreeSet<Person> lastNameList;
    private TreeSet<Person> ageList;

    static {
        lastNameComparator = (p1, p2) -> {
            int res = p1.getLastName().compareTo(p2.getLastName());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
        ageComparator = (p1, p2) -> {
            int res = Integer.compare(p1.getAge(), p2.getAge());
            return res != 0 ? res : Integer.compare(p1.getId(), p2.getId());
        };
    }

    public CitizensImpl() {
        idList = new TreeSet<>();
        ageList = new TreeSet<>(ageComparator);
        lastNameList = new TreeSet<>(lastNameComparator);
    }

    public CitizensImpl(List<Person> citizens) {
        this();
        citizens.forEach(p -> add(p));
    }

    // O(log(n))
    @Override
    public boolean add(Person person) {
        return person != null && idList.add(person) && lastNameList.add(person) && ageList.add(person);
    }

    // O(log(n))
    @Override
    public boolean remove(int id) {
        Person person = find(id);
        return person != null && idList.remove(person) && lastNameList.remove(person) && ageList.remove(person);
    }

    // O(log(n))
    @Override
    public Person find(int id) {
        Person pattern = new Person(id, null, null, null);
        Person person = idList.ceiling(pattern);
        return pattern.equals(person) ? person : null;
    }

    // O(log(n))
    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        LocalDate now = LocalDate.now();
        Person from = new Person(idList.first().getId() - 1, null, null, now.minusYears(minAge));
        Person to = new Person(idList.last().getId() + 1, null, null, now.minusYears(maxAge));
        return ageList.subSet(from, to);
    }

    // O(log(n))
    @Override
    public Iterable<Person> find(String lastName) {
        Person from = new Person(Integer.MIN_VALUE, null, lastName, null);
        Person to = new Person(Integer.MAX_VALUE, null, lastName, null);
        return lastNameList.subSet(from, to);
    }

    // O(1)
    @Override
    public Iterable<Person> getAllPersonSortedById() {
        return idList;
    }

    // O(1)
    @Override
    public Iterable<Person> getAllPersonSortedByLastName() {
        return lastNameList;
    }

    // O(1)
    @Override
    public Iterable<Person> getAllPersonSortedByAge() {
        return ageList;
    }

    // O(1)
    @Override
    public int size() {
        return idList.size();
    }
}
