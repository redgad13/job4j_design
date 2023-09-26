package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void actionOnExpiryPercent() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Food apple = new Fruits("red apple", sdf.parse("01/01/2022"), sdf.parse("09/12/2024"));
        ControlQuality cq = new ControlQuality();
        assertThat(cq.actionOnExpiryPercent(apple)).isInstanceOf(Shop.class);
    }

    @Test
    void newPriceOnExpiryPercent() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Food apple = new Fruits("red apple", sdf.parse("01/01/2022"), sdf.parse("09/12/2024"), 30, 5);
        ControlQuality cq = new ControlQuality();
        cq.actionOnExpiryPercent(apple);
        assertThat(apple.getPrice()).isEqualTo(28.5);
    }
}