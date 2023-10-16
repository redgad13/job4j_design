package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        StringBuilder stringBuilder;
        String s;
        int length;
        for (Menu.MenuItemInfo itemInfo : menu) {
            length = itemInfo.getNumber().length() - 2;
            stringBuilder = new StringBuilder();
            s = getSpaces(length);
            stringBuilder.append(s, 0, length);
            System.out.println(stringBuilder + itemInfo.getNumber() + itemInfo.getName());
        }

    }

    private String getSpaces(int length) {
        StringBuilder s = new StringBuilder();
        while (length > 0) {
            s.append(" ");
            length--;
        }
        return s.toString();
    }
}
