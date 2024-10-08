package org.container;

import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {

    @Test
    public void testAddAndSize() {
        Container<String> container = new Container<>();
        assertEquals(0, container.size());

        container.add("Hello");
        assertEquals(1, container.size());

        container.add("World");
        assertEquals(2, container.size());
    }

    @Test
    public void testGet() {
        Container<Integer> container = new Container<>();
        container.add(10);
        container.add(20);
        container.add(30);

        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
    }

    @Test
    public void testRemove() {
        Container<String> container = new Container<>();
        container.add("A");
        container.add("B");
        container.add("C");

        container.remove(1);
        assertEquals(2, container.size());
        assertEquals("A", container.get(0));
        assertEquals("C", container.get(1));

        container.remove(0);
        assertEquals(1, container.size());
        assertEquals("C", container.get(0));
    }

    @Test
    public void testIndexOutOfBoundsException() {
        Container<String> container = new Container<>();
        container.add("Test");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            container.get(1);
        });

        assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(1);
        });
    }
}