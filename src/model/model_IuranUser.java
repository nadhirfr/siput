/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author fachrul
 */
public class model_IuranUser {
    private int IuranUserId, UserId, IuranId, TransaksiId;
    private boolean IuranUserStatus;

    public model_IuranUser() {
    }

    public model_IuranUser(int UserId, int IuranId, int TransaksiId, boolean IuranUserStatus) {
        this.UserId = UserId;
        this.IuranId = IuranId;
        this.TransaksiId = TransaksiId;
        this.IuranUserStatus = IuranUserStatus;
    }

    public int getIuranUserId() {
        return IuranUserId;
    }

    public void setIuranUserId(int IuranUserId) {
        this.IuranUserId = IuranUserId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getIuranId() {
        return IuranId;
    }

    public void setIuranId(int IuranId) {
        this.IuranId = IuranId;
    }

    public int getTransaksiId() {
        return TransaksiId;
    }

    public void setTransaksiId(int TransaksiId) {
        this.TransaksiId = TransaksiId;
    }

    public boolean isIuranUserStatus() {
        return IuranUserStatus;
    }

    public void setIuranUserStatus(boolean IuranUserStatus) {
        this.IuranUserStatus = IuranUserStatus;
    }
    
    
}
