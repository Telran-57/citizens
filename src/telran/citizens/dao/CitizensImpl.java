package telran.citizens.dao;

import telran.citizens.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CitizensImpl implements Citizens{
    private static Comparator<Person> lastNameComparator = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName());
    private static Comparator<Person> ageComparator = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
    private List<Person> idList;

    public CitizensImpl() {
        idList = new ArrayList<>();
    }

    public CitizensImpl(List<Person> citizens) {
        this();
        citizens.forEach(p -> add(p));
    }

    @Override
    public boolean add(Person person) {
        if (person == null || find(person.getId()) != null) {
            return false;
        }
        return idList.add(person);
    }

    @Override
    public boolean remove(int id) {
        Person victim = find(id);
        if (victim == null) {
            return false;
        }
        idList.remove(victim);
        return true;
    }

    @Override
    public Person find(int id) {
        for (Person person: idList){
            if (person.getId() == id){
                return person;
            }
        }
        return null;
    }

    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        List<Person> res = new ArrayList<>();
        for (Person person: idList){
            if (person.getAge()>= minAge && person.getAge() <= maxAge){
                res.add(person);
            }
        }
        return res;
    }

    @Override
    public Iterable<Person> find(String lastName) {
        List<Person> res = new ArrayList<>();
        for (Person person: idList){
            if (lastName.equals(person.getLastName())){
                res.add(person);
            }
        }
        return res;
    }

    @Override
    public Iterable<Person> getAllPersonSortedById() {
        List<Person> res = new ArrayList<>(idList);
        Collections.sort(res);
        return res;
    }

    @Override
    public Iterable<Person> getAllPersonSortedByLastName() {
        List<Person> res = new ArrayList<>(idList);
        Collections.sort(res, lastNameComparator);
        return res;
    }

    @Override
    public Iterable<Person> getAllPersonSortedByAge() {
        List<Person> res = new ArrayList<>(idList);
        Collections.sort(res, ageComparator);
        return res;
    }

    @Override
    public int size() {
        return idList.size();
    }
}
