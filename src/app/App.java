package app;

import model.*;

public class App {
    public static void main(String[] args) {
        BoxOnList box = new BoxOnList("Коробка", 1.5f, 8.5f);

        Item book = new Item("Книга", 1.8f);
        Item phone = new Item("Телефон", 0.7f);

        Item pen = new Item("Ручка", 0.15f);
        Item eraser = new Item("Стирашка", 0.20f);
        BoxOnList pencilCase = new BoxOnList("Пенал", 0.9f, 3.5f);

        box.put(book);
        box.put(phone);

        pencilCase.put(eraser);
        pencilCase.put(pen);

        box.put(pencilCase);




    }
}
