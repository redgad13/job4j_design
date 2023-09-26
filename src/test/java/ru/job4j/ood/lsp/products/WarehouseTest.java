package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {

    @Test
    void executeIsOk() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Food apple = new Fruits("apple", sdf.parse("01/01/2022"), sdf.parse("01/07/2022"));
        AbstractStore warehouse = new Warehouse(apple);
        assertThat(warehouse.execute()).isEqualTo("apple in storage");
    }
}