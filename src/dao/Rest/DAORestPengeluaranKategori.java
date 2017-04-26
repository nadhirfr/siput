/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.Rest;
import dao.implementPengeluaranKategori;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PengeluaranKategori;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ROSANIA
 */
public class DAORestPengeluaranKategori implements implementPengeluaranKategori {
    
    private List<PengeluaranKategori> listPengeluaranKategori;
    public static String alamat = "http://localhost/siput-server/index.php/Pengeluaran_kategoris";

    public DAORestPengeluaranKategori() {
        populatePengeluaranKategori();
    }

    @Override 
    public void insert(PengeluaranKategori b) {
        try {
            URL url = new URL(alamat+"?id="+b.getPengeluaran_kategori_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = "{"
                    + "\"pengeluaran_kategori_nama\":\"" + b.getPengeluaran_kategori_nama()
                    + "\",\"pengeluaran_kategori_waktu\":\"" + b.getPengeluaran_kategori_waktu()
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
            populatePengeluaranKategori();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populatePengeluaranKategori() {
        listPengeluaranKategori = new ArrayList<>();
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
            listPengeluaranKategori.clear();
            for (int i = 0; i < json.size(); i++) {
                
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                System.out.println(jo.get("user_pengeluaran_kategori_nama").toString());
                listPengeluaranKategori.add(new PengeluaranKategori(Integer.valueOf(jo.get("pengeluaran_kategori_id").toString()), 

                        jo.get("pengeluaran_kategori_nama").toString(), (int) jo.get("pengeluaran_kategori_waktu")));
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

        public PengeluaranKategori get(String pengeluaran_kategori_id) {
        populatePengeluaranKategori();
        PengeluaranKategori pengeluarankategori = null;
        for (PengeluaranKategori _pengeluarankategori : listPengeluaranKategori) {
            if (String.valueOf(_pengeluarankategori.getPengeluaran_kategori_id()).equals(pengeluaran_kategori_id)) {
                pengeluarankategori = _pengeluarankategori;
            }
        }
        return pengeluarankategori;
    }

    @Override
    public void update(PengeluaranKategori b) {
        try {
            URL url = new URL(alamat+"?id="+b.getPengeluaran_kategori_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = "{"
                    + "\"pengeluaran_kategori_nama\":\"" + b.getPengeluaran_kategori_nama()
                    + "\",\"pengeluaran_kategori_waktu\":\"" + b.getPengeluaran_kategori_waktu()
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
            populatePengeluaranKategori();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String pengeluaran_kategori_id) {
        try {
            URL url = new URL(alamat+"?id="+pengeluaran_kategori_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : "+alamat+"?id="+pengeluaran_kategori_id);
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
            populatePengeluaranKategori();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PengeluaranKategori> getAll() {
        populatePengeluaranKategori();
        return listPengeluaranKategori;
    }

    @Override
    public List<PengeluaranKategori> getCari(String PengeluaranKategoriNama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        populatePengeluaranKategori();
        return listPengeluaranKategori.size();
    }

    

}
