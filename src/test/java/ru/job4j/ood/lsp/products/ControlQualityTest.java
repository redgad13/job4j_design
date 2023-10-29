package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
        Date expDateOrange = sdf.parse("01/12/2023");
        Date expDatePear = sdf.parse("01/07/2024");
        Food apple = new Fruits("Apple", createDateApple, expDateApple);
        Food orange = new Fruits("Orange", createDateOrange, expDateOrange);
        Food pear = new Fruits("Pear", createDatePear, expDatePear);
        List<Food> fruits = List.of(apple, orange, pear);
        List<Store> stores = List.of(new Shop(), new Warehouse(), new Trash());
        ControlQuality cq = new ControlQuality(stores);
        List<Store> rsl = cq.actionOnExpiryPercent(fruits, sdf.parse("29/10/2023"));
        assertThat(rsl.get(0).getFood()).isEqualTo(List.of(orange));
    }

    @Test
    void resort() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date createDateApple = sdf.parse("01/01/2023");
        Date createDateOrange = sdf.parse("01/06/2023");
        Date createDatePear = sdf.parse("01/09/2023");
        Date expDateApple = sdf.parse("01/07/2023");
        Date expDateOrange = sdf.parse("01/12/2023");
        Date expDatePear = sdf.parse("01/07/2024");
        Food apple = new Fruits("Apple", createDateApple, expDateApple);
        Food orange = new Fruits("Orange", createDateOrange, expDateOrange);
        Food pear = new Fruits("Pear", createDatePear, expDatePear);
        List<Food> fruits = List.of(apple, orange, pear);
        List<Store> stores = List.of(new Shop(), new Warehouse(), new Trash());
        ControlQuality cq = new ControlQuality(stores);
        List<Store> newStores = cq.actionOnExpiryPercent(fruits, sdf.parse("29/10/2023"));
        List<Store> rsl = cq.resort(newStores, sdf.parse("30/10/2023"));
        assertThat(rsl.get(0).getFood()).isEqualTo(List.of(orange));
    }
}