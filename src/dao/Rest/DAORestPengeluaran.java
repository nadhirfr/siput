/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.Rest;

import dao.implementPengeluaran;
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
import model.Iuran;
import model.Pengeluaran;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import model.PengeluaranKategori;
import model.PengeluaranJenis;
import model.PengeluaranUser;


public class DAORestPengeluaran implements implementPengeluaran {

    private List<Pengeluaran> listPengeluaran;

    public static String alamat = "http://localhost/siput-server/index.php/pengeluarans";

    public DAORestPengeluaran() {
        populatePengeluaran();
    }

    @Override
    public void insert(Pengeluaran b) {
        try {
            URL url = new URL(alamat + "?id=" + b.getPengeluaran_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = 
                    "{"
                    + "\"pengeluaran_nama\":\"" + b.getPengeluaran_nama()
                    + "\",\"pengeluaran_jenis_id\":\"" + b.getPengeluaran_jenis_id()
                    + "\",\"pengeluaran_kategori_id\":\"" + b.getPengeluaran_kategori_id()
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
            populatePengeluaran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populatePengeluaran() {
        listPengeluaran = new ArrayList<>();
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
            listPengeluaran.clear();
            for (int i = 0; i < json.size(); i++) {
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                //System.out.println(jo.get("iuran_nama").toString());
                listPengeluaran.add(new Pengeluaran (Integer.valueOf(jo.get("pengeluaran_id").toString()),
                        Integer.valueOf(jo.get("pengeluaran_nama").toString()),
                        Integer.valueOf(jo.get("pengeluaran_jenis_id").toString()),
                        jo.get("pengeluaran_nama").toString(),
                        jo.get("pengeluaran_kategori_id").toString()));
                       
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

    public Pengeluaran get(String pengeluaran_id) {
        populatePengeluaran();
        Pengeluaran pengeluaran = null;
        for (Pengeluaran _pengeluaran : listPengeluaran) {
            if (String.valueOf(_pengeluaran.getPengeluaran_id()).equals(pengeluaran_id)) {
                pengeluaran = _pengeluaran;
            }
        }
        return pengeluaran;
    }

    @Override
    public void update(Pengeluaran b) {
        try {
            URL url = new URL(alamat + "?id=" + b.getPengeluaran_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input=
                   "{"
                    + "\"pengeluaran_nama\":\"" + b.getPengeluaran_nama()
                    + "\",\"pengeluaran_jenis_id\":\"" + b.getPengeluaran_jenis_id()
                    + "\",\"pengeluaran_kategori_id\":\"" + b.getPengeluaran_kategori_id()
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
            populatePengeluaran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String pengeluaran_id) {
        try {
            URL url = new URL(alamat + "?id=" + pengeluaran_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : " + alamat + "?id=" + pengeluaran_id);
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
            populatePengeluaran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Pengeluaran> getAll() {
        populatePengeluaran();
        return listPengeluaran;
    }

    @Override
    public List<Pengeluaran> getCari(String PengeluaranNama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        populatePengeluaran();
        return listPengeluaran.size();
    }
    
 
   
}
