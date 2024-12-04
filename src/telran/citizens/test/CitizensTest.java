package telran.citizens.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.citizens.dao.Citizens;
import telran.citizens.model.Person;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CitizensTest {
    private Citizens citizens;
    private static final LocalDate now = LocalDate.now();

    @BeforeEach
    void setUp() {
        // TODO citizens = new CitizensImpl();
        citizens.add(new Person(1, "Peter", "Jackson", now.minusYears(23)));
        citizens.add(new Person(2, "John", "Smith", now.minusYears(20)));
        citizens.add(new Person(3, "Mary", "Jackson", now.minusYears(20)));
        citizens.add(new Person(4, "Rabindranate", "Anand", now.minusYears(25)));
    }

    @Test
    void testAdd() {
        assertFalse(citizens.add(null));
        assertFalse(citizens.add(new Person(2, "John", "Smith", now.minusYears(20))));
        assertEquals(4, citizens.size());
        assertTrue(citizens.add(new Person(5, "John", "Smith", now.minusYears(20))));
        assertEquals(5, citizens.size());
    }

    @Test
    void testRemove() {
        assertFalse(citizens.remove(5));
        assertEquals(4, citizens.size());
        assertTrue(citizens.remove(2));
        assertEquals(3, citizens.size());
    }

    @Test
    void testFindById() {
        Person person = citizens.find(1);
        assertEquals(1, person.getId());
        assertEquals("Peter", person.getFirstName());
        assertEquals("Jackson", person.getLastName());
        assertEquals(23, person.getAge());
        assertNull(citizens.find(5));
    }

    @Test
    void testFindByAges() {
        Iterable<Person> res = citizens.find(20, 23);
    }

    @Test
    void testFindByLastName() {
    }

    @Test
    void testGetAllPersonSortedById() {
    }

    @Test
    void testGetAllPersonSortedByLastName() {
    }

    @Test
    void testGetAllPersonSortedByAge() {
    }

    @Test
    void testSize() {
    }
}