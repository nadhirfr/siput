package dao;

import model.*;
import java.util.List;

public interface implementUser {

    public int insert(User b);
    
    public User getUser(String user_id);

    public void update(User b);

    public void delete(String user_id);

    public List<User> getAll();

    public List<User> getCari(String displayname);
    
    public int getCount();
}
