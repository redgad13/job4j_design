package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    void executeTrash() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date createDateApple = sdf.parse("01/01/2023");
        Date expDateApple = sdf.parse("01/07/2023");
        Date createDateOrange = sdf.parse("01/06/2023");
        Date expDateOrange = sdf.parse("01/12/2023");
        Date createDateMilk = sdf.parse("01/09/2023");
        Date expDateMilk = sdf.parse("01/07/2024");
        Food apple = new Fruits("Apple", createDateApple, expDateApple);
        Food orange = new Fruits("Orange", createDateOrange, expDateOrange);
        Food milk = new Milk("Milk", createDateMilk, expDateMilk);
        Store trash = new Trash();
        List<Food> foods = List.of(apple, orange, milk);
        BestBeforeDate bbd = new BestBeforeDate();
        List<Food> rsl = bbd.findPercentTillExpiryDate(foods, new Date().getTime());
        rsl = trash.execute(rsl);
        assertThat(rsl).isEqualTo(List.of(apple));
    }
}