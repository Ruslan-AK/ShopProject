package com.Kutugin.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dp-ptcstd-49 on 11.02.2019.
 * @author Reneuby
 * this class provide output/input information
 */
public class MainMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
    private final AdminMenu adminMenu = new AdminMenu();
    private final ClientMenu clientMenu = new ClientMenu();

    public void showMenu(){
        boolean isRunning = true;
        while (isRunning){
            System.out.println("1 - Admin\n2 - Client\n0 - Exit");
            String input = null;
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("You enter: " + input);
            switch (input){
                case "1": {
                    System.out.println("Enter as admin");
                    break;
                }
                case "2": {
                    System.out.println("Enter as client");
                    break;
                }
                case "0": {
                    System.out.println("Exit");
                    isRunning = false;
                    break;
                }
                default:
                    isRunning = false;
                    System.out.println("Wrong input");
                    break;
            }
        }
    }
}
