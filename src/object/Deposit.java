/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

/**
 *
 * @author fachrul
 */
public class Deposit {
    private int depositId,userId,depositJumlah;

    public Deposit() {
    }

    public Deposit(int depositId, int userId, int depositJumlah) {
        this.depositId = depositId;
        this.userId = userId;
        this.depositJumlah = depositJumlah;
    }

    public Deposit(int userId, int depositJumlah) {
        this.userId = userId;
        this.depositJumlah = depositJumlah;
    }

    public int getDepositId() {
        return depositId;
    }

    public void setDepositId(int depositId) {
        this.depositId = depositId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDepositJumlah() {
        return depositJumlah;
    }

    public void setDepositJumlah(int depositJumlah) {
        this.depositJumlah = depositJumlah;
    }
    
}
