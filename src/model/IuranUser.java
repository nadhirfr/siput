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
public class IuranUser {
    private int IuranUserId, UserId, IuranId, IuranUserStatus;

    public IuranUser() {
    }

    public IuranUser(int UserId, int IuranId, int IuranUserStatus) {
        this.UserId = UserId;
        this.IuranId = IuranId;
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
    
    public int isIuranUserStatus() {
        return IuranUserStatus;
    }

    public void setIuranUserStatus(int IuranUserStatus) {
        this.IuranUserStatus = IuranUserStatus;
    }
    
    
}
