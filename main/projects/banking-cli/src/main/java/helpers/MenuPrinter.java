package helpers;

public class MenuPrinter {
    private final String[] menu;

    public MenuPrinter(String[] menu) {
        this.menu = menu;
    }

    public void print() {
        for(int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ": " + menu[i]);
        }
    }

}
