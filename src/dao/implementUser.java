package dao;

import model.*;
import java.util.List;

public interface implementUser {

    public void insert(model_User b);

    public void update(model_User b);

    public void delete(String user_id);

    public List<model_User> getAll();

    public List<model_User> getCari(String displayname);
}
