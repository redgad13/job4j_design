package ru.job4j.ood.lsp.products;

import java.util.List;

public interface Store {
   void execute(List<Food> foods);

   List<Food> getFood();
}
