package org.movierental.company.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.movierental.company.entity.Company;
import org.movierental.repository.QueryExecutor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final QueryExecutor queryExecutor;

    @Override
    public Company insert(Company company) {
        try (var connection = queryExecutor.getConnection();
             var statement = connection.prepareStatement("INSERT INTO company (name) VALUES (?)")) {
            statement.setString(1, company.getName());
            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(("New company [" + company.getName() + "] has been inserted successfully."));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return company;
    }

    @Override
    public void update(long id, String newName) {
        execute("UPDATE company SET name = '" + newName + "' WHERE company_id = " + id + ";");
        System.out.println("Company with id [" + id + "] name was updated to: [" + newName + "]");
    }

    @Override
    public void findById(Long id) {
        execute("SELECT * FROM company WHERE company_id = " + id + ";");
    }

    @Override
    public void findAll() {
        execute("SELECT * FROM company ;");
    }

    @Override
    public void findByName(String companyName) {
        execute("SELECT * FROM company WHERE NAME LIKE '" + companyName + "';");
    }

    @Override
    public void removeById(Long id) {
        queryExecutor.executeQuery("DELETE FROM company WHERE company_id = " + id + ";");
    }

    private void execute(String sql) {
        try (QueryExecutor queryExecution = new QueryExecutor();
             var connection = queryExecution.getConnection();
             var statement = connection.createStatement();
             var rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                print(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void print(ResultSet rs) {
        try {
            System.out.print(rs.getString("company_id"));
            System.out.print(" - ");
            System.out.println(rs.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
