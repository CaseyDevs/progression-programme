package helpers;

import java.util.List;

public class MenuPrinter {
    private final List<String> menu;

    public MenuPrinter(List<String> menu) {
        this.menu = menu;
    }

    public void print() {
        for(String item: menu) {
            System.out.println(item);
        }
    }

}
