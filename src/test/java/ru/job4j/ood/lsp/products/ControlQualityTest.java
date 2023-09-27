package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class ControlQualityTest {

    @Test
    void actionOnExpiryPercent() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date createDateApple = sdf.parse("01/01/2023");
        Date createDateOrange = sdf.parse("01/06/2023");
        Date createDatePear = sdf.parse("01/09/2023");
        Date expDateApple = sdf.parse("01/07/2023");
        Date expDateOrange= sdf.parse("01/12/2023");
        Date expDatePear = sdf.parse("01/07/2024");
        List<Food> fruits = List.of(
                new Fruits("Apple", createDateApple, expDateApple),
                new Fruits("Orange", createDateOrange, expDateOrange),
                new Fruits("Pear", createDatePear, expDatePear));
        List<AbstractStore> stores = List.of(new Shop(), new Warehouse(), new Trash());
        ControlQuality cq = new ControlQuality(stores);
        cq.actionOnExpiryPercent(fruits, new Date());

    }
}