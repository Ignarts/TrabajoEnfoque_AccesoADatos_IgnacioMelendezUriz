package dao;

import db.DatabaseConnection;
import model.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ContractDAO {

    private static final String INSERT_SQL =
            "INSERT INTO contracts (nif, awardedTo, genericObject, objectDescription, awardedDate, amount, consultedProviders) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Inserts a list of Contract objects into the database.
     */
    public void insertContracts(List<Contract> contracts) {
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(INSERT_SQL)) {

            for (Contract c : contracts) {

                stmt.setString(1, c.getNif());
                stmt.setString(2, c.getAwardedTo());
                stmt.setString(3, c.getGenericObject());
                stmt.setString(4, c.getObjectDescription());
                stmt.setString(5, c.getAwardedDate());
                stmt.setDouble(6, c.getAmount());
                stmt.setString(7, c.getConsultedProviders());

                stmt.executeUpdate();
            }

            System.out.println("✔️ All contracts inserted successfully.");

        } catch (SQLException e) {
            System.err.println("❌ Error inserting contracts: " + e.getMessage());
        }
    }
}
