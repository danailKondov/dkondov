package ru.job4j.taskfortest.patternDAO.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.taskfortest.entities.Address;
import ru.job4j.taskfortest.patternDAO.EntityDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for address database access (DAO).
 *
 * @since 19/01/2018
 * @version 1
 */
class AddressDao implements EntityDao<Address> {

    private static final Logger LOG = LoggerFactory.getLogger(AddressDao.class);

    private static class AddressDaoHelper {
        private static final AddressDao INSTANCE = new AddressDao();
    }

    private AddressDao() {}

    public static AddressDao getInstance() {
        return AddressDaoHelper.INSTANCE;
    }

    @Override
    public void create(Address entity) {
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("INSERT INTO addresses (address_name) " +
                     "VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getAddress());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    entity.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Address> getAll() {
        List<Address> result = new ArrayList<>();
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT id, address_name " +
                     "FROM addresses")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String address = rs.getString(2);
                    result.add(new Address(id, address));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Address getEntityById(int id) {
        Address result = null;
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT address_name " +
                     "FROM addresses " +
                     "WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String address = rs.getString(2);
                    result = new Address(id, address);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Address update(Address entity) {
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("")) {
            //
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("")) {
            //
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
