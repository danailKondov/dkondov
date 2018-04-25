package ru.job4j.taskfortest.patternDAO.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.taskfortest.entities.Role;
import ru.job4j.taskfortest.patternDAO.EntityDao;

import java.sql.*;
import java.util.List;

/**
 * Class for role database access (DAO).
 *
 * @since 19/01/2018
 * @version 1
 */
class RoleDao implements EntityDao<Role> {

    private static final Logger LOG = LoggerFactory.getLogger(RoleDao.class);

    private static class RoleDaoHelper {
        private static final RoleDao INSTANCE = new RoleDao();
    }

    private RoleDao() {}

    public static RoleDao getInstance() {
        return RoleDao.RoleDaoHelper.INSTANCE;
    }

    @Override
    public void create(Role entity) {
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("INSERT INTO roles " +
                     "(role_name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
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
    public List<Role> getAll() {
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("" +
                     "SELECT r.id, r.role_name, u.id, u.user_name, a.id, a.address_name, mt.id, mt.music_type_name " +
                     "FROM roles AS r " +
                     "LEFT OUTER JOIN users AS u ON r.id = u.roles_id " +
                     "LEFT OUTER JOIN addresses AS a ON u.address_id = a.id " +
                     "LEFT OUTER JOIN users_music AS um ON u.id = um.user_id " +
                     "LEFT OUTER JOIN music_types As mt ON um.music_id = mt.id")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int roleId = rs.getInt(1);
                    String roleName = rs.getString(2);
                    int userId = rs.getInt(3);
                    String userName = rs.getString(4);
                    int addressId = rs.getInt(5);
                    String addressName = rs.getString(6);
                    int musicTypeId = rs.getInt(7);
                    String musicTypeName = rs.getString(8);
//                    result.add(new User (id, name, login, email, password, role, city, country, createDate));
                    // TODO: есть интересное решение с HashMap - найти
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Role getEntityById(int id) {
        try (final Connection connection = DaoFactory.getConnection();
             final PreparedStatement statement = connection.prepareStatement("")) {
            //
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Role update(Role entity) {
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
