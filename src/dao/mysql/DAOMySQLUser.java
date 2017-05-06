package dao.mysql;

import dao.implementUser;
import koneksi.Koneksi;
import object.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMySQLUser implements implementUser {

    Connection connection;
    final String insert = "INSERT INTO user (user_id,user_username,user_displayname,user_password,user_tipe) VALUES (NULL,?,?,?,?);";
    final String update = "UPDATE user SET user_username=?, user_displayname=?, user_password=?, user_tipe=? WHERE user_id=?;";
    final String delete = "DELETE FROM user WHERE user_id=?;";
    final String select = "SELECT * FROM user;";
    final String get = "SELECT * FROM user WHERE user_id=?;";
    final String cari = "SELECT * FROM user WHERE user_displayname LIKE ?;";

    public DAOMySQLUser() {
        connection = Koneksi.connection();
    }

    @Override
    public int insert(User b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert);
            statement.setString(1, b.getUser_username());
            statement.setString(2, b.getUser_displayname());
            statement.setString(3, b.getUser_password());
            statement.setString(4, b.getUser_tipe());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 1;
    }
    
    @Override
    public void update(User b) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setString(1, b.getUser_username());
            statement.setString(2, b.getUser_displayname());
            statement.setString(3, b.getUser_password());
            statement.setString(4, b.getUser_tipe());
            statement.setString(5, Integer.toString(b.getUser_id()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String user_id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, user_id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAll() {
        List<User> lb = null;
        try {
            lb = new ArrayList<User>();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                User b = new User();
                b.setUser_id(Integer.parseInt(rs.getString("user_id")));
                b.setUser_username(rs.getString("user_username"));
                b.setUser_displayname(rs.getString("user_displayname"));
                b.setUser_password(rs.getString("user_password"));
                b.setUser_tipe(rs.getString("user_tipe"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public List<User> getCari(String displayname) {
        List<User> lb = null;
        try {
            lb = new ArrayList<User>();
            PreparedStatement st = connection.prepareStatement(cari);
            st.setString(1, "%" + displayname + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User b = new User();
                b.setUser_id(Integer.parseInt(rs.getString("user_id")));
                b.setUser_username(rs.getString("user_username"));
                b.setUser_displayname(rs.getString("user_displayname"));
                b.setUser_password(rs.getString("user_password"));
                b.setUser_tipe(rs.getString("user_tipe"));
                lb.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }

    @Override
    public User getUser(String user_id) {
    PreparedStatement statement = null;
    User user = new User();
        try {
            statement = connection.prepareStatement(get);
            statement.setString(1, user_id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               user.setUser_id(Integer.parseInt(rs.getString("user_id")));
               user.setUser_username(rs.getString("user_username"));
               user.setUser_displayname(rs.getString("user_displayname"));
               user.setUser_password(rs.getString("user_password"));
               user.setUser_tipe(rs.getString("user_tipe"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }    
        return user;
    }

    @Override
    public int getCount() {
        int lb = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            rs.last();
            lb = rs.getRow();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMySQLUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lb;
    }
}
