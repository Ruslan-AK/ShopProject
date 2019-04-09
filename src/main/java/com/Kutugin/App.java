package com.Kutugin;

import com.Kutugin.view.MainMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MainMenu menu = context.getBean(MainMenu.class);
        menu.showMenu();
    }
}