/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rheza
 */
public class Session {

    private int session_id, user_id;
    private String session_time;

    public Session() {

    }

    public Session(int session_id, int user_id, String session_time, boolean session_status) {
        this.session_id = session_id;
        this.user_id = user_id;
        this.session_time = session_time;
    }

    public Session(int user_id, String session_time, boolean session_status) {
        this.user_id = user_id;
        this.session_time = session_time;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSession_time() {
        return session_time;
    }

    public void setSession_time(String session_time) {
        this.session_time = session_time;
    }
}
