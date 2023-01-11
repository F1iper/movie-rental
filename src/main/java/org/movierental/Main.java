package org.movierental;

import org.movierental.address.controller.AddressController;
import org.movierental.address.repository.AddressRepositoryImpl;
import org.movierental.address.service.mockito.AddressServiceImpl;
import org.movierental.company.controller.CompanyController;
import org.movierental.company.repository.CompanyRepositoryImpl;
import org.movierental.company.service.CompanyServiceImpl;
import org.movierental.movie.controller.MovieController;
import org.movierental.movie.repository.MovieRepositoryImpl;
import org.movierental.movie.service.MovieServiceImpl;
import org.movierental.staff.controller.StaffController;
import org.movierental.staff.repository.StaffRepositoryImpl;
import org.movierental.staff.service.StaffServiceImpl;
import org.movierental.userinterface.UserInterfaceTerminal;

class Main {

    public static void main(String[] args) {
        var uITerminal = new UserInterfaceTerminal(
                new CompanyController(new CompanyServiceImpl(new CompanyRepositoryImpl())),
                new AddressController(new AddressServiceImpl(new AddressRepositoryImpl())),
                new StaffController(new StaffServiceImpl(new StaffRepositoryImpl())),
                new MovieController(new MovieServiceImpl(new MovieRepositoryImpl()))
        );
        uITerminal.run();
    }
}