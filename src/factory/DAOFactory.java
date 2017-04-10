/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;
//pattern MVC selanjutnya adalah packages model
//dimana nantinya dalam packages ini kita mengetahui cara memisahkan data

import dao.*;
import dao.implementUser;

public abstract class DAOFactory {
    //class ini adalah abstract interface dari DAO dimana disini nantinya
    //setiap kode dipisah berdasarkan fungsinya masing2
    //sehingga kode lain diatasnya tidak perlu tahu cara pengaksesan
    //ke sumber data yang diimplementasikan.
    public static final int MySQL = 0;
    public abstract implementUser getUserMySQL();
    public abstract implementIuran getIuranMySQL();
    public abstract implementIuranUser getIuranUserMySQL();
    public abstract implementDeposit getDepositMySQL();
    public static DAOFactory getFactory ( int type){
        switch (type){
            case MySQL: 
                return new MySQLDAOFactory();
//            case MySQL_iuran: 
//                return new MySQLDAOFactory();
            default:
                return null;
        }
    }
}
