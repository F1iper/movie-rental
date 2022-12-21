package org.movierental.userinterface;

import org.movierental.controller.CompanyController;

import java.util.Scanner;

public class UserInterfaceTerminal {

    private final CompanyController companyController;

    public UserInterfaceTerminal() {
        this.companyController = new CompanyController();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMainMenu();
            String command = scanner.nextLine();

            if("exit".equals(command)) {
                break;
            }
            if ("3".equals(command)) {
                System.out.println("Insert data to: ");
                printOptions();
                checkWhereToInsertData(scanner.nextLine());
            }
            if ("4".equals(command)) {
                System.out.println("Select data from: ");
                printOptions();
                retrieveData(scanner.nextLine());
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("-------------------");
        System.out.println("MAIN MENU ");
        System.out.println("-------------------");
        System.out.println("What would you like to do? ");
        //// TODO: 12/21/2022 register + login
        System.out.println("3 - Insert data");
        System.out.println("4 - Select data");
        System.out.println("exit - exit program");
    }

    private void retrieveData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Provide company name to select: ");
            String companyName = scanner.nextLine();
            companyController.searchCompany(companyName);
        }
    }

    private void printOptions() {
        System.out.println("1 - Company");
        System.out.println("2 - Branch");
        System.out.println("3 - Staff");
        System.out.println("4 - Movie");
        System.out.println("5 - Actor");
        System.out.println("6 - Address");
        System.out.println("7 - Customer");
        System.out.println("0 - back to Main Menu");
    }

    private void checkWhereToInsertData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Provide company name to insert: ");
            String companyName = scanner.nextLine();
            companyController.addCompany(companyName);
        }
    }
}
