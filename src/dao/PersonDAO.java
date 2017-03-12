/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Person;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Komputer 03
 */
//class interface yang berisi method yang sudah diimplementasikan di class atasnya 

public interface PersonDAO {
    public int addPerson(Person person) throws SQLException;

	public Person getPerson(int id) throws SQLException;

	public List<Person> getPeople() throws SQLException;

	public int updatePerson(Person person) throws SQLException;

	public int deletePerson(int id) throws SQLException;

	public int deleteAll() throws SQLException;
}
