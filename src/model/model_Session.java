/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author rheza
 */
public class model_Session {

    private int session_id, user_id;
    private Time session_time;
    private boolean session_status;

    public model_Session() {

    }

    public model_Session(int session_id, int user_id, Time session_time, boolean session_status) {
        this.session_id = session_id;
        this.user_id = user_id;
        this.session_time = session_time;
        this.session_status = session_status;
    }

    public model_Session(int user_id, Time session_time, boolean session_status) {
        this.user_id = user_id;
        this.session_time = session_time;
        this.session_status = session_status;
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

    public Time getSession_time() {
        return session_time;
    }

    public void setSession_time(Time session_time) {
        this.session_time = session_time;
    }

    public boolean isSession_status() {
        return session_status;
    }

    public void setSession_status(boolean session_status) {
        this.session_status = session_status;
    }

}
