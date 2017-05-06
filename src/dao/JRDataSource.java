/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author fachrul
 */
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseField;

/**
 * 
 * This class implements a DataSource for the data adapter MyImplementation. A data source it 
 * is like an envelope for the data adapter. It can create and destroy the data adapter itself, 
 * but it can also provide informations about the data adapters, like which fields it provide and 
 * a description\type for everyone of them.
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class JRDataSource implements JRDataSourceProvider {

    @Override
    public boolean supportsGetFieldsOperation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JRField[] getFields(JasperReport jr) throws JRException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public net.sf.jasperreports.engine.JRDataSource create(JasperReport jr) throws JRException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose(net.sf.jasperreports.engine.JRDataSource jrds) throws JRException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}