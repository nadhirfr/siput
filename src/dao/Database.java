/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//disini seperti biasa kita mengimport JDBC terlebih dahulu
/**
 *
 * @author Komputer 03
 */
public class Database {
    private static Database instance = new Database();//pertama-tama kita membuat instance dari database
	
	private Connection con;// code ini agar dapat terkoneksi dengan database SQL
	
	private Database() {//memanggil instance yang sudah dibuat
		
	}
	
	public static Database getInstance() {//method getter untuk
		return instance;//mengambil instnce
	}
	

	public Connection getConnection() {
		return con;//method agar terkoneksi dengan database
	}
	
	public void connect() throws Exception {
		if (con != null)
			return;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");//apabila tdak ditemukan koneksi
		}

		String url = String.format("jdbc:mysql://localhost:%d/siput", 3306);//url SQL yang kita gunakan unntuk pengambilan source data

		con = DriverManager.getConnection(url, "root", "");//disini kita mengetahui bahwa 
                //koneksinya menggunakan url dan akun bernama root dan tanpa password untuk masuk ke localhost diatas
	}
	
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
		con = null;
	}
        
        public boolean isLogin(String user, String pass) throws SQLException{
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            String query = "select * from user where username = ?"
                    + " and password = ?";
            try {
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                
                resultSet = preparedStatement.executeQuery();
                
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            } finally {
                preparedStatement.close();
                resultSet.close();
            }
        }
}

