/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.Rest;

import dao.implementKategoriIuran;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import model.KategoriIuran;
import model.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ROSANIA
 */
public class DAORestKategoriIuran implements implementKategoriIuran{
 private List<KategoriIuran> listKategoriIuran;
    public static String alamat = "http://localhost/siput-server/index.php/Iuran_kategoris";

    public DAORestKategoriIuran() {
        populateKategoriIuran();
    }

    @Override 
    public void insert(KategoriIuran b) {
        try {
            URL url = new URL(alamat+"?id="+b.getIuranKategoriId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = "{"
                    + "\"iuran_kategori_nama\":\"" + b.getIuranKategoriNama()
                    + "\",\"iuran_kategori_interval\":\"" + b.getIuranKategoriInterval()
                    + "\"}";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
            populateKategoriIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateKategoriIuran() {
        listKategoriIuran = new ArrayList<>();
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
            listKategoriIuran.clear();
            for (int i = 0; i < json.size(); i++) {
                
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                //System.out.println(jo.get("iuran_nama").toString());
                listKategoriIuran.add(new KategoriIuran(Integer.valueOf(jo.get("iuran_kategori_id").toString()), 
                        jo.get("iuran_kategori_nama").toString(), 
                        Integer.valueOf(jo.get("iuran_kategori_interval").toString())));
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

        public KategoriIuran getIuranKategoriId(String iuran_kategori_id) {
        populateKategoriIuran();
        KategoriIuran kategoriiuran = null;
        for (KategoriIuran _kategoriiuran : listKategoriIuran) {
            if (String.valueOf(_kategoriiuran.getIuranKategoriId()).equals(iuran_kategori_id)) {
                kategoriiuran = _kategoriiuran;
            }
        }
        return kategoriiuran;
    }

    @Override
    public void update(KategoriIuran b) {
        try {
            URL url = new URL(alamat+"?id="+b.getIuranKategoriId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input =  "{"
                    + "\"iuran_kategori_nama\":\"" + b.getIuranKategoriNama()
                    + "\",\"iuran_kategori_interval\":\"" + b.getIuranKategoriInterval()
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
            populateKategoriIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String KategoriIuranId) {
        try {
            URL url = new URL(alamat+"?id="+KategoriIuranId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : "+alamat+"?id="+KategoriIuranId);
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
            populateKategoriIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public KategoriIuran get(String KategoriIuranId) {
        populateKategoriIuran();
        KategoriIuran kategoriIuran = null;
        for (KategoriIuran _kategoriIuran : listKategoriIuran) {
            if (String.valueOf(_kategoriIuran.getIuranKategoriId()).equals(KategoriIuranId)) {
                kategoriIuran = _kategoriIuran;
            }
        }
        return kategoriIuran;
    }

    @Override
    public List<KategoriIuran> getAll() {
        populateKategoriIuran();
        return listKategoriIuran;
    }

    @Override
    public List<KategoriIuran> getCari(String KategoriIuranNama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        populateKategoriIuran();
        return listKategoriIuran.size();
    }


}
