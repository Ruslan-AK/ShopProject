package com.Kutugin;

import com.Kutugin.view.AdminMenu;
import com.Kutugin.view.MainMenu;

import java.io.IOException;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 */
public class App {
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.show();
    }
}
