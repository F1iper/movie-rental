package org.movierental.userinterface;

import org.movierental.controller.AddressController;
import org.movierental.controller.CompanyController;
import org.movierental.entity.Address;

import java.util.Scanner;

public class UserInterfaceTerminal {

    private final CompanyController companyController;
    private final AddressController addressController;

    public UserInterfaceTerminal() {
        this.companyController = new CompanyController();
        this.addressController = new AddressController();
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
        // TODO: 12/21/2022 register + login
        System.out.println("3 - Insert data");
        System.out.println("4 - Select data");
        System.out.println("exit - exit program");
    }

    private void retrieveData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            String companyName = provideCompanyName();
            companyController.searchCompany(companyName);
        }
    }

    private void printOptions() {
        System.out.println("1 - Company");
        System.out.println("2 - Address");
        System.out.println("3 - Staff");
        System.out.println("4 - Movie");
        System.out.println("5 - Actor");
        System.out.println("6 - Branch");
        System.out.println("7 - Customer");
        System.out.println("0 - back to Main Menu");
    }

    private void checkWhereToInsertData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            String companyName = provideCompanyName();
            companyController.addCompany(companyName);
        }
        if ("2".equals(command)) {
            addressController.addAddress(provideAddressData());
        }
    }

    private static String provideCompanyName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide company name to insert: ");
        return scanner.nextLine();
    }

    private static Address provideAddressData() {
        Scanner scanner = new Scanner(System.in);
        Address address = new Address();
        System.out.println("Please provide Address");
        System.out.println("Street: ");
        address.setStreet(scanner.nextLine());
        System.out.println("City: ");
        address.setCity(scanner.nextLine());
        System.out.println("State: ");
        address.setState(scanner.nextLine());
        System.out.println("Zip code");
        address.setZip_code(scanner.nextLine());
        System.out.println("Phone: ");
        address.setPhone(scanner.nextLine());
        return address;
    }
}
