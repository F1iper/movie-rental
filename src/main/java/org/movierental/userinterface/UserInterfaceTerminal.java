package org.movierental.userinterface;

import lombok.RequiredArgsConstructor;
import org.movierental.address.controller.AddressController;
import org.movierental.address.entity.Address;
import org.movierental.company.controller.CompanyController;
import org.movierental.company.entity.Company;
import org.movierental.entity.Language;
import org.movierental.entity.MovieType;
import org.movierental.entity.Status;
import org.movierental.movie.controller.MovieController;
import org.movierental.movie.entity.Movie;
import org.movierental.staff.controller.StaffController;
import org.movierental.staff.entity.Position;
import org.movierental.staff.entity.Staff;

import java.util.List;
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
            updateCompany();
        }
        if ("2".equals(command)) {

        }
        if ("4".equals(command)) {
            updateMovie();
        }
    }

    private void updateCompany() {
        System.out.println("Provide company ID: ");
        long id = Long.parseLong(scanner.nextLine());
        Company company = companyController.findById(id);
        if (company == null) {
            System.out.println("Company not found.");
            return;
        }
        System.out.println("Company to update: " + company.getName());
        System.out.println("Provide new name: ");
        String newName = scanner.nextLine();
        boolean success = companyController.updateName(id, newName);
        if (success) {
            System.out.println("Company name updated.");
        } else {
            System.out.println("Failed to update company name.");
        }
    }

    private void updateMovie() {
        System.out.println("Movies available: ");
        List<Movie> allMovies = movieController.findAll();
        for (Movie movie : allMovies) {
            System.out.println(movie.getMovieId() + " - " + movie.getTitle());
        }
        System.out.println("Choose a movie id to update: ");
        Long movieId = Long.parseLong(scanner.nextLine());
        Movie selectedMovie = allMovies.stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findFirst()
                .orElse(null);
        if (selectedMovie == null) {
            System.out.println("Invalid movie id.");
            return;
        }
        System.out.println("Choose option: ");
        System.out.println("1 - Update title");
        System.out.println("2 - Update description");
        System.out.println("0 - Back");
        int command = Integer.parseInt(scanner.nextLine());
        switch (command) {
            case 1:
                System.out.println("Enter new title: ");
                boolean titleUpdated = movieController.updateTitle(selectedMovie.getMovieId(), scanner.nextLine());
                if (titleUpdated) {
                    System.out.println("Movie title updated.");
                } else {
                    System.out.println("Failed to update movie title.");
                }
                break;
            case 2:
                System.out.println("Enter new description: ");
                boolean descriptionUpdated = movieController.updateDescription(selectedMovie.getMovieId(), scanner.nextLine());
                if (descriptionUpdated) {
                    System.out.println("Movie description updated.");
                } else {
                    System.out.println("Failed to update movie description.");
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option.");
                break;
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
            System.out.println(movieController.findByCostRange(Double.parseDouble(scanner.nextLine()),
                    Double.parseDouble(scanner.nextLine())));
        }
        if ("5".equals(command)) {
            findMovieByMovieType();
        }
        if ("6".equals(command)) {
            printAllMovies();
        }
    }

    private void findMovieByMovieType() {
        System.out.println("Available movie types: ");
        List<MovieType> movieTypes = movieController.getMovieTypes();
        movieTypes.forEach(mt -> System.out.println(mt.getMovieTypeId() + " - " + mt.getName()));
        System.out.println("Provide movie type ID: ");
        Long typeId = Long.parseLong(scanner.nextLine());
        MovieType selectedType = movieTypes.stream()
                .filter(mt -> mt.getMovieTypeId().equals(typeId))
                .findFirst()
                .orElse(null);
        if (selectedType == null) {
            System.out.println("Invalid movie type ID.");
            return;
        }
        List<Movie> movies = movieController.findByMovieTypeId(selectedType.getMovieTypeId());
        if (movies.isEmpty()) {
            System.out.println("No movies found for the given type.");
        } else {
            System.out.println("Movies found for the given type: ");
            for (Movie movie : movies) {
                System.out.println(movie.getTitle());
            }
        }
    }

    private void chooseStaffSearchOption(String command) {
        if ("0".equals(command)) {
            return;
        }
        if ("1".equals(command)) {
            System.out.println("Provide staff ID: ");
            System.out.println(staffController.findStaffById(Long.parseLong(scanner.nextLine())));
        }
        if ("2".equals(command)) {
            System.out.println("Provide firstname: ");
            System.out.println(staffController.findByFirstname(scanner.nextLine()));
        }
        if ("3".equals(command)) {
            System.out.println("Provide lastname: ");
            System.out.println(staffController.findByLastname(scanner.nextLine()));
        }
        if ("4".equals(command)) {
            System.out.println("Provide salary range, \nfirst number - minimum, \nsecond number - maximum ");
            System.out.println(staffController.findBySalaryRange(Integer.parseInt(scanner.nextLine()),
                    Integer.parseInt(scanner.nextLine())));
        }
        if ("5".equals(command)) {
            System.out.println("Provide position ID: ");
            System.out.println(staffController.findByPositionId(Long.parseLong(scanner.nextLine())));
        }
        if ("6".equals(command)) {
            System.out.println(staffController.findAll());
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
        System.out.println(getLanguages());
        System.out.println("Language ID: ");
        movie.setLanguageId(Long.parseLong(scanner.nextLine()));
        System.out.println("cost: ");
        movie.setCost(Double.parseDouble(scanner.nextLine()));
        System.out.println("Possible statuses: ");
        getStatuses();
        System.out.println("Choose status: ");
        movie.setStatusId(Long.parseLong(scanner.nextLine()));
        System.out.println("Possible movie types: ");
        getMovieTypes();
        System.out.println("Choose movie type: ");
        movie.setMovieTypeId(Long.parseLong(scanner.nextLine()));

        return movie;
    }

    private Company provideCompany() {
        Company company = new Company();
        while (true) {
            try {
                System.out.println("Provide company name to insert: ");
                String name = scanner.nextLine();
                validateNotEmpty(name, "Company name");
                company.setName(name);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return company;
    }

    private Staff provideStaffData() {
        Staff staff = new Staff();
        while (true) {
            try {
                System.out.println("Please provide firstname: ");
                String firstname = scanner.nextLine();
                validateNotEmpty(firstname, "Firstname");

                System.out.println("Lastname: ");
                String lastname = scanner.nextLine();
                validateNotEmpty(lastname, "Lastname");

                System.out.println("Possible positions: ");
                System.out.println(getPositions());
                System.out.println("Please provide position ID: ");
                long positionId = Long.parseLong(scanner.nextLine());

                if (!validatePositionId(positionId)) {
                    break;
                }

                System.out.println("Salary: ");
                double salary = Double.parseDouble(scanner.nextLine());
                validateSalary(salary);

                staff.setFirstname(firstname);
                staff.setLastname(lastname);
                staff.setPositionId(positionId);
                staff.setSalary(salary);
                System.out.println("Staff member added: " + staff);

                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary or position ID.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return staff;
    }

    private boolean validatePositionId(long positionId) {
        List<Position> positions = staffController.getPositions();
        for (Position position : positions) {
            if (position.getPositionId() == positionId) {
                return true;
            }
        }
        return false;
    }

    private void validateSalary(double salary) {
        if (salary <= 0) {
            throw new IllegalArgumentException("Salary must be a positive value.");
        }
    }

    private Address provideAddressData() {
        Address address = new Address();
        while (true) {
            try {
                System.out.println("Please provide Address");
                System.out.println("Street: ");
                String street = scanner.nextLine();
                validateNotEmpty(street, "Street");

                System.out.println("City: ");
                String city = scanner.nextLine();
                validateNotEmpty(city, "City");

                System.out.println("State: ");
                String state = scanner.nextLine();
                validateNotEmpty(state, "State");

                System.out.println("Zip code");
                String zipCode = scanner.nextLine();
                validateNotEmpty(zipCode, "Zip code");

                System.out.println("Phone: ");
                String phone = scanner.nextLine();
                validateNotEmpty(phone, "Phone");

                address.setStreet(street);
                address.setCity(city);
                address.setState(state);
                address.setZip_code(zipCode);
                address.setPhone(phone);
                System.out.println("Address added: " + address);

                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return address;
    }

    private void validateNotEmpty(String value, String fieldName) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    private List<Language> getLanguages() {
        return movieController.getLanguages();
    }

    private List<Position> getPositions() {
        return staffController.getPositions();
    }

    private List<Status> getStatuses() {
        return movieController.getStatuses();
    }

    private List<MovieType> getMovieTypes() {
        return movieController.getMovieTypes();
    }

    private void printAllMovies() {
        List<Movie> movies = movieController.findAll();
        if (movies.isEmpty()) {
            System.out.println("No movies found.");
        } else {
            System.out.println("Movies: ");
            for (Movie movie : movies) {
                System.out.println("ID: " + movie.getMovieId());
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Description: " + movie.getDescription());
//                System.out.println("Movie type: " + movieController.getMovieTypes().);
                // TODO: 1/4/2023 PRINT movie type
                System.out.println("Duration: " + movie.getLength() + " minutes");
                System.out.println("Released: " + movie.getReleaseYear());
            }
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
        System.out.println("5 - Search by movie type");
        System.out.println("6 - Search all movies");
        System.out.println("0 - Back to Main Menu");
    }
}
