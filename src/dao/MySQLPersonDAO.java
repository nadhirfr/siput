/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//class ini bertanggung jawab untuk isi dari source data.
// pada class database kita sudah membuat koneksi DAO ke database
//elanjutnya diclass ini kita membuat isi dari sql agar mampu terkoneksi dengan DAO

/**
 *
 * @author Komputer 03
 */
public class MySQLPersonDAO implements PersonDAO{
    public int addPerson(Person person) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn 
                .prepareStatement("insert into people ( name, password)"
                + "values (?,?)");// databse yang diinputkan bernama people dengan jenis integer
        //dan berisi nama serta passwordnya.
        
        p.setString(1, person.getName());
        p.setString(2, person.getPassword());
        int updated = p.executeUpdate();//update ini nantinya ditampung pada controller
        p.close();
        return updated;
                
    }//method getter untuk mengambil   data nama dan password dari source
    public Person getPerson(int id)throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        String sql = "select id, name, password from people" 
                + "where id=? order by id";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1,id);
        ResultSet results = selectStatement.executeQuery();
        Person person = null;
        if(results.next()){
            String name = results.getString("name");
            String password = results.getString("password");
            person = new Person(id,name,password);
        }
        results.close();
        selectStatement.close();
        return person;
    }
    //membuat list nama dan password
    public List<Person> getPeople() throws SQLException{
        List<Person> people = new ArrayList<Person>();
        Connection conn = Database.getInstance().getConnection();
        String sql = "select id,name,password"
                + "from people order by id";
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery(sql);
        while (results.next()){
            int id = results.getInt("id");
            String name = results.getString("name");
            String password = results.getString("password");
            Person person = new Person(id,name, password);
            people.add(person);
        }
        results.close();
        selectStatement.close();
        return people;
    }
    public int updatePerson(Person person) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn
                .prepareStatement ("update people set name = ?," 
                + "password=? where id=?");
        p.setString(1,person.getName());
        p.setString(2,person.getPassword());
        p.setInt(3, person.getId());
        int updated = p.executeUpdate();
        p.close();
        return updated;
    }
     public int deletePerson(int id) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn
                .prepareStatement ("delete from"
                + "people where id=?");
        p.setInt(1, id);
        int deleted = p.executeUpdate();
        p.close();
        return deleted;
    }


    @Override
    public int deleteAll() throws SQLException {
        Connection conn = Database.getInstance().getConnection();
        PreparedStatement p = conn
                .prepareStatement ("delete from people");

        int deleted = p.executeUpdate();
        p.close();
        return deleted;
    }
}
