package ru.job4j.ood.lsp.products;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.*;

class TrashTest {

    @Test
    void execute() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Food coco = new Milk("coconut milk", sdf.parse("01/01/2022"), sdf.parse("01/07/2022"));
        AbstractStore trash = new Trash(coco);
        assertThat(trash.execute()).isEqualTo("coconut milk eliminating");
    }
}