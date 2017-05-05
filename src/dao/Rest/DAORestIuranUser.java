/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.Rest;

import dao.implementIuranUser;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.Iuran;
import model.IuranUser;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ROSANIA
 */
public class DAORestIuranUser implements implementIuranUser {

    private List<IuranUser> listIuranUser;
    public static String alamat = "http://localhost/siput-server/index.php/iuran_users";

    public DAORestIuranUser() {
        populateIuranUser();
    }

    @Override
    public void insert(IuranUser b) {
        int id = 0;
        try {
            URL url = new URL(alamat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String urlParameters = "iuran_id=" + b.getIuranId()
                    + "&user_id=" + b.getUserId()
                    + "&iuran_user_status=" + b.isIuranUserStatus();
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                wr.write(postData);
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //ini ambil output data lalu dimasukkan ke string response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String response = null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = response + output;
            }

            //ini parsing data output menggunakan jsoup, diambil id nya
            Document d = Jsoup.parse(response);
            Elements tables = d.select("table > tbody > tr > td");
            Element e = tables.first();
            System.out.println(e.text());
            id = Integer.valueOf(e.text());
            System.out.println("id created:" + id);

            conn.disconnect();
            populateIuranUser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public IuranUser get(String IuranIuranUserId) {
        populateIuranUser();
        IuranUser iuranUser = null;
        for (IuranUser _iuranUser : listIuranUser) {
            if (String.valueOf(_iuranUser.getIuranUserId()).equals(IuranIuranUserId)) {
                iuranUser = _iuranUser;
            }
        }
        return iuranUser;
    }

    @Override
    public IuranUser getByUserAndIuran(User u, Iuran i) {
        populateIuranUser();
        IuranUser iuranUser = null;
        for (IuranUser _iuranUser : listIuranUser) {
            if (_iuranUser.getUserId() == u.getUser_id()
                    && _iuranUser.getIuranId() == i.getIuranId()) {
//                System.out.println("True lik");
                iuranUser = _iuranUser;
            }
        }
        return iuranUser;
    }

    @Override
    public void update(IuranUser b) {
        try {
            URL url = new URL(alamat + "?id=" + b.getIuranUserId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = "{"
                    + "\"user_id\":\"" + b.getUserId()
                    + "\",\"iuran_id\":\"" + b.getIuranId()
                    + "\",\"iuran_user_status\":\"" + b.isIuranUserStatus()
                    + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
            populateIuranUser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String IuranUserId) {
        try {
            URL url = new URL(alamat + "?id=" + IuranUserId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : " + alamat + "?id=" + IuranUserId);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
            populateIuranUser();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IuranUser> getAll() {
        populateIuranUser();
        return listIuranUser;
    }

    @Override
    public List<IuranUser> getCari(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(IuranUser b) {
        DAORestUser restUser = new DAORestUser();
        IuranUser iuranUser = get(String.valueOf(b.getIuranUserId()));
        User user = restUser.getUser(String.valueOf(iuranUser.getUserId()));

        return user;
    }

    @Override
    public Iuran getIuran(IuranUser b) {
        DAORestIuran restIuran = new DAORestIuran();
        IuranUser iuranUser = get(String.valueOf(b.getIuranUserId()));
        Iuran iuran = restIuran.get(String.valueOf(iuranUser.getIuranId()));

        return iuran;
    }

    @Override
    public int getCount() {
        populateIuranUser();
        return listIuranUser.size();
    }

    private void populateIuranUser() {
        listIuranUser = new ArrayList<>();
        try {
            URL url = new URL(alamat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            char[] buffer = new char[1024];
            StringBuilder sb = new StringBuilder();
            Reader in = new InputStreamReader(conn.getInputStream());
            while (true) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0) {
                    break;
                }
                sb.append(buffer, 0, rsz);
            }
            JSONParser jp = new JSONParser();
            JSONArray json = (JSONArray) jp.parse(sb.toString());
//            System.out.println(json.toString());
            listIuranUser.clear();
            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
//                System.out.println(jo.toString());
                listIuranUser.add(new IuranUser(Integer.valueOf(jo.get("iuran_user_id").toString()),
                        Integer.valueOf(jo.get("user_id").toString()),
                        Integer.valueOf(jo.get("iuran_id").toString()),
                        Integer.valueOf(jo.get("iuran_user_status").toString())));
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IuranUser> getBelumLunas(User b) {
    listIuranUser = new ArrayList<>();
        try {
            URL url = new URL(alamat+"?param=getBelumLunas&user_id="+b.getUser_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            char[] buffer = new char[1024];
            StringBuilder sb = new StringBuilder();
            Reader in = new InputStreamReader(conn.getInputStream());
            while (true) {
                int rsz = in.read(buffer, 0, buffer.length);
                if (rsz < 0) {
                    break;
                }
                sb.append(buffer, 0, rsz);
            }
            JSONParser jp = new JSONParser();
            JSONArray json = (JSONArray) jp.parse(sb.toString());
//            System.out.println(json.toString());
            listIuranUser.clear();
            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
//                System.out.println(jo.toString());
                listIuranUser.add(new IuranUser(Integer.valueOf(jo.get("iuran_user_id").toString()),
                        Integer.valueOf(jo.get("user_id").toString()),
                        Integer.valueOf(jo.get("iuran_id").toString()),
                        Integer.valueOf(jo.get("iuran_user_status").toString())));
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listIuranUser;
    }

}
