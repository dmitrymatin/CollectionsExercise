package tests;

import model.Box;
import model.BoxOnList;
import model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class BoxOnListTest {
    private static BoxOnList box = new BoxOnList("Коробка",
                                            1.5f,
                                    8.5f);

    @BeforeAll
    static void setUp() {
        Item book = new Item("Книга", 1.8f);
        Item phone = new Item("Телефон", 0.7f);

        BoxOnList pencilCase = new BoxOnList("Пенал", 0.9f, 3.5f);

        Item pen = new Item("Ручка", 0.15f);
        Item eraser = new Item("Стирашка", 0.20f);

        box.put(book);
        box.put(phone);

        pencilCase.put(eraser);
        pencilCase.put(pen);

        box.put(pencilCase);
    }

    @Test
    void puttingWorks() {
        Item pen = box.find(5);
        Item pencilCase = box.find(4);

        assertEquals(pen.getName(), "Ручка");
        assertEquals(pencilCase.getName(), "Пенал");
    }

    @Test
    void removingByRefWorks() {
        Item test1 = new Item("test1", 0.01f);
        Item test2 = new Item("test2", 0.01f);
        box.put(test1);
        box.put(test2);

        box.remove(test1);
        box.remove(test2);

        assertFalse(box.find(test1));
        assertFalse(box.find(test2));
    }

    @Test
    void removingByIdWorks() {
        Item test1 = new Item("test", 0.01f);
        box.put(test1);
        int id = test1.getId();

        box.remove(id);

        assertNull(box.find(id));
    }

    @Test
    void removingBoxWorks() {
        Box boxToRemove = new BoxOnList("box to remove", 3f, 4f);

        box.put(boxToRemove);
        box.remove(boxToRemove);

        assertFalse(box.find(boxToRemove));
    }

    @Test
    void exceedingStorageCapacityProtectionWorks() {
        Item test = new Item("largeItem", Float.MAX_VALUE);

        box.put(test);

        assertFalse(box.find(test));
    }

    @Test
    void makeSureBoxCannotBePutInItself() {
        BoxOnList box = new BoxOnList("illegalNestingBox", 5f, 5f);
        box.put(box);

        assertFalse(box.find(box));
    }

    @Test
    void testRemove() {
    }

    @Test
    void find() {
    }

    @Test
    void testFind() {
    }

    @Test
    void getMaxStorageMass() {
    }

    @Test
    void getStorageMass() {
    }
}