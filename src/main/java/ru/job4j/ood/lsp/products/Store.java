package ru.job4j.ood.lsp.products;

import java.util.List;

public interface Store {
   List<Food> execute(List<Food> foods);

   List<Food> getFood();
}
