package org.movierental.userinterface;

import lombok.RequiredArgsConstructor;
import org.movierental.address.controller.AddressController;
import org.movierental.address.entity.Address;
import org.movierental.company.controller.CompanyController;
import org.movierental.company.entity.Company;
import org.movierental.movie.controller.MovieController;
import org.movierental.movie.entity.Movie;
import org.movierental.staff.controller.StaffController;
import org.movierental.staff.entity.Staff;

import java.util.Scanner;

@RequiredArgsConstructor
public class UserInterfaceTerminal {

    private final static Scanner scanner = new Scanner(System.in);
    private final CompanyController companyController;
    private final AddressController addressController;
    private final StaffController staffController;
    private final MovieController movieController;

    public void run() {
        while (true) {
            printMainMenu();
            String command = scanner.nextLine();

            if ("exit".equals(command)) {
                break;
            }
            if ("2".equals(command)) {
                System.out.println("Update data in: ");
                printOptions();
                checkWhereUpdateData(scanner.nextLine());
            }
            if ("3".equals(command)) {
                System.out.println("Insert data to: ");
                printOptions();
                checkWhereToInsertData(scanner.nextLine());
            }
            if ("4".equals(command)) {
                System.out.println("Select data from: ");
                printOptions();
                chooseTableToSearchOn(scanner.nextLine());
            }
            if ("5".equals(command)) {
                System.out.println("Remove data from: ");
                printOptions();
                removeData(scanner.nextLine());
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("-------------------");
        System.out.println("MAIN MENU ");
        System.out.println("-------------------");
        System.out.println("What would you like to do? ");
        // TODO: 12/21/2022 register + login
        System.out.println("2 - Update data");
        System.out.println("3 - Insert data");
        System.out.println("4 - Select data");
        System.out.println("5 - Remove data");
        System.out.println("exit - exit program");
    }

    private void checkWhereUpdateData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide Company ID: ");
            long id = Long.parseLong(scanner.nextLine());
            System.out.println("Company to update: ");
            companyController.findById(id);
            System.out.println("Provide new name: ");
            String newName = scanner.nextLine();
            companyController.updateName(id, newName);
        }
    }

    private void chooseCompanySearchOption(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide Company ID: ");
            System.out.println(companyController.findById(Long.parseLong(scanner.nextLine())));
        }
        if ("2".equals(command)) {
            System.out.println("Provide company name: ");
            System.out.println(companyController.findByName(scanner.nextLine()));
        }
        if ("3".equals(command)) {
            System.out.println(companyController.findAll());
        }
    }

    private void removeData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide company ID: ");
            companyController.removeById(Long.parseLong(scanner.nextLine()));
        }
        if ("2".equals(command)) {
            System.out.println("Provide address ID: ");
            addressController.removeById(Long.parseLong(scanner.nextLine()));
        }
        if ("3".equals(command)) {
            System.out.println("Provide Staff ID: ");
            staffController.removeById(Long.parseLong(scanner.nextLine()));
        }
        if ("4".equals(command)) {
            System.out.println("Provide Movie ID: ");
            movieController.removeById(Long.parseLong(scanner.nextLine()));
        }
    }

    private void chooseTableToSearchOn(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            printCompanySearchOptions();
            chooseCompanySearchOption(scanner.nextLine());
        }
        if ("2".equals(command)) {
            printAddressSearchOptions();
            chooseAddressSearchOption(scanner.nextLine());
        }
        if ("3".equals(command)) {
            printStaffSearchOptions();
            chooseStaffSearchOption(scanner.nextLine());
        }
        if ("4".equals(command)) {
            printMovieSearchOptions();
            chooseMovieSearchOption(scanner.nextLine());
        }
    }

    private void chooseMovieSearchOption(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide movie ID: ");
            System.out.println(movieController.findById(Long.parseLong(scanner.nextLine())));
        }
        if ("2".equals(command)) {
            System.out.println("Provide title: ");
            System.out.println(movieController.findByTitle(scanner.nextLine()));
        }
        if ("3".equals(command)) {
            System.out.println("Provide release year: ");
            System.out.println(movieController.findByReleaseYear(Integer.parseInt(scanner.nextLine())));
        }
        if ("4".equals(command)) {
            System.out.println("Provide cost range, \nfirst number - minimum, \nsecond number - maximum ");
            System.out.println(movieController.findByCostRange(Integer.parseInt(scanner.nextLine()),
                    Integer.parseInt(scanner.nextLine())));
        }
        if ("5".equals(command)) {
            System.out.println(movieController.findAll());
        }
    }


    private void chooseStaffSearchOption(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide staff ID: ");
            staffController.findStaffById(Long.parseLong(scanner.nextLine()));
        }
        if ("2".equals(command)) {
            System.out.println("Provide firstname: ");
            staffController.findByFirstname(scanner.nextLine());
        }
        if ("3".equals(command)) {
            System.out.println("Provide lastname: ");
            staffController.findByLastname(scanner.nextLine());
        }
        if ("4".equals(command)) {
            System.out.println("Provide salary range, \nfirst number - minimum, \nsecond number - maximum ");
            staffController.findBySalaryRange(Integer.parseInt(scanner.nextLine()),
                    Integer.parseInt(scanner.nextLine()));
        }
        if ("5".equals(command)) {
            System.out.println("Provide position ID: ");
            staffController.findByPositionId(Long.parseLong(scanner.nextLine()));
        }
        if ("6".equals(command)) {
            staffController.findAll();
        }
    }

    private void chooseAddressSearchOption(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide address ID: ");
            System.out.println(addressController.findById(Long.parseLong(scanner.nextLine())));
        }
        if ("2".equals(command)) {
            System.out.println("Provide street: ");
            System.out.println(addressController.findByStreet(scanner.nextLine()));
        }
        if ("3".equals(command)) {
            System.out.println("Provide city: ");
            System.out.println(addressController.findByCity(scanner.nextLine()));
        }
        if ("4".equals(command)) {
            System.out.println("Provide state: ");
            System.out.println(addressController.findByState(scanner.nextLine()));
        }
        if ("5".equals(command)) {
            System.out.println("Provide zip code: ");
            System.out.println(addressController.findByZipCode(scanner.nextLine()));
        }
        if ("6".equals(command)) {
            System.out.println(addressController.findAll());
        }
    }

    private void checkWhereToInsertData(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            companyController.addCompany(provideCompany());
        }
        if ("2".equals(command)) {
            addressController.add(provideAddressData());
        }
        if ("3".equals(command)) {
            staffController.add(provideStaffData());
        }
        if ("4".equals(command)) {
            movieController.add(provideMovieData());
        }
    }

    private Movie provideMovieData() {
        Movie movie = new Movie();
        System.out.println("Provide title: ");
        movie.setTitle(scanner.nextLine());
        System.out.println("description: ");
        movie.setDescription(scanner.nextLine());
        System.out.println("release year: ");
        movie.setReleaseYear(Integer.parseInt(scanner.nextLine()));
        System.out.println("length: ");
        movie.setLength(Integer.parseInt(scanner.nextLine()));
        System.out.println("Possible languages");
        printLanguages();
        System.out.println("Language ID: ");
        movie.setLanguageId(Long.parseLong(scanner.nextLine()));
        System.out.println("cost: ");
        movie.setCost(Double.parseDouble(scanner.nextLine()));
        System.out.println("Possible statuses: ");
        printStatuses();
        System.out.println("Choose status: ");
        movie.setStatusId(Long.parseLong(scanner.nextLine()));
        System.out.println("Possible movie types: ");
        printMovieTypes();
        System.out.println("Choose movie type: ");
        movie.setMovieTypeId(Long.parseLong(scanner.nextLine()));

        return movie;
    }

    private static Company provideCompany() {
        System.out.println("Provide company name to insert: ");
        Company company = new Company();
        company.setName(scanner.nextLine());
        return company;
    }

    private static Address provideAddressData() {
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

    private Staff provideStaffData() {
        Staff staff = new Staff();
        System.out.println("Please provide firstname: ");
        staff.setFirstname(scanner.nextLine());
        System.out.println("Lastname: ");
        staff.setLastname(scanner.nextLine());
        System.out.println("Possible positions: ");
        printPositions();

        System.out.println("Please provide position ID: ");
        staff.setPositionId(Long.parseLong(scanner.nextLine()));
        System.out.println("Salary: ");
        staff.setSalary(Double.parseDouble(scanner.nextLine()));
        return staff;
    }

    private void printLanguages() {
        movieController.getLanguages();
    }

    private void printPositions() {
        staffController.getPositions();
    }

    private void printStatuses() {
        movieController.getStatuses();
    }

    private void printMovieTypes() {
        movieController.getMovieTypes();
    }

    private void printOptions() {
        System.out.println("1 - Company");
        System.out.println("2 - Address");
        System.out.println("3 - Staff");
        System.out.println("4 - Movie");
        System.out.println("5 - Actor");
        System.out.println("6 - Branch");
        System.out.println("7 - Customer");
        System.out.println("0 - Back to Main Menu");
    }

    private void printCompanySearchOptions() {
        System.out.println("Choose an option: ");
        System.out.println("1 - Search by ID");
        System.out.println("2 - Search by company name");
        System.out.println("3 - Search all records");
        System.out.println("0 - Back to Main Menu");
    }

    private void printStaffSearchOptions() {
        System.out.println("Choose an option: ");
        System.out.println("1 - Search by ID");
        System.out.println("2 - Search by firstname");
        System.out.println("3 - Search by lastname");
        System.out.println("4 - Search by salary amount");
        System.out.println("5 - Search by position");
        System.out.println("6 - Search all records");
        System.out.println("0 - Back to Main Menu");
    }

    private void printAddressSearchOptions() {
        System.out.println("Choose an option: ");
        System.out.println("1 - Search by ID");
        System.out.println("2 - Search by street");
        System.out.println("3 - Search by city");
        System.out.println("4 - Search by state");
        System.out.println("5 - Search by zip code");
        System.out.println("6 - Search all records");
        System.out.println("0 - Back to Main Menu");
    }

    private void printMovieSearchOptions() {
        System.out.println("Choose an option: ");
        System.out.println("1 - Search by ID ");
        System.out.println("2 - Search by title");
        System.out.println("3 - Search by release year");
        System.out.println("4 - Search by cost with range (min, max)");
        System.out.println("5 - Search all movies");
        System.out.println("0 - Back to Main Menu");
    }
}
