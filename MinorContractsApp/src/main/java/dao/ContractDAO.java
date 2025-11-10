package dao;

import db.DatabaseConnection;
import model.Contract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ContractDAO {
    public void insertContracts(List<Contract> contracts) {
        String sql = "INSERT INTO contracts (organization, description, amount, date, awardedTo) VALUES (?, ?, ?, ?, ?)";

        try(Connection connection = DatabaseConnection.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Contract contract : contracts) {
                preparedStatement.setString(1, contract.getDescription());
                preparedStatement.setString(2, contract.getDescription());
                preparedStatement.setDouble(3, contract.getAmount());
                preparedStatement.setString(4, contract.getDate());
                preparedStatement.setString(5, contract.getAwardedTo());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
