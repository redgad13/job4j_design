package ru.job4j.ood.isp.menu;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuPrinterTest {

    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void print() {
        Menu menu = new SimpleMenu();
        ActionDelegate action = System.out::println;
        menu.add(Menu.ROOT, "Сходить в магазин", action);
        menu.add(Menu.ROOT, "Покормить собаку", action);
        menu.add("Сходить в магазин", "Купить продукты", action);
        menu.add("Купить продукты", "Купить хлеб", action);
        menu.add("Купить продукты", "Купить молоко", action);
        SimpleMenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        simpleMenuPrinter.print(menu);
        String expected = """
                1.Сходить в магазин
                  1.1.Купить продукты
                    1.1.1.Купить хлеб
                    1.1.2.Купить молоко
                2.Покормить собаку""";
        assertThat(expected).isEqualTo(output.toString());
    }
}