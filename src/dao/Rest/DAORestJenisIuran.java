/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.Rest;
import dao.implementJenisIuran;
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
import model.JenisIuran;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ROSANIA
 */
public class DAORestJenisIuran implements implementJenisIuran {
    
    
    private List<JenisIuran> listJenisIuran;
    public static String alamat = "http://localhost/siput-server/index.php/iuran_jeniss";

    public DAORestJenisIuran() {
        populateJenisIuran();
    }

    @Override 
    public void insert(JenisIuran b) {
        try {
            URL url = new URL(alamat+"?id="+b.getIuranJenisId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = "{"
                    + "\"iuran_jenis_nama\":\"" + b.getIuranJenisNama()
                    + "\",\"iuran_jenis_keterangan\":\"" + b.getIuranJenisKeterangan()
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
            populateJenisIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateJenisIuran() {
        listJenisIuran = new ArrayList<>();
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
            listJenisIuran.clear();
            for (int i = 0; i < json.size(); i++) {
                
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                //System.out.println(jo.get("user_pengeluaran_kategori_nama").toString());
                listJenisIuran.add(new JenisIuran(Integer.valueOf(jo.get("iuran_jenis_id").toString()), 
                        jo.get("iuran_jenis_nama").toString(),
                        
                        jo.get("iuran_jenis_keterangan").toString()));
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

        public JenisIuran getJenisIuran(String IuranJenisId) {
        populateJenisIuran();
        JenisIuran jenisiuran= null;
        for (JenisIuran _jenisiuran : listJenisIuran) {
            if (String.valueOf(_jenisiuran.getIuranJenisId()).equals(IuranJenisId)) {
                jenisiuran = _jenisiuran;
            }
        }
        return jenisiuran;
    }

    @Override
    public void update(JenisIuran b) {
        try {
            URL url = new URL(alamat+"?id="+b.getIuranJenisId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = 
                    "{"
                    + "\"iuran_jenis_nama\":\"" + b.getIuranJenisNama()
                    + "\",\"iuran_jenis_keterangan\":\"" + b.getIuranJenisKeterangan()
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
            populateJenisIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String JenisIuranId) {
        try {
            URL url = new URL(alamat+"?id="+JenisIuranId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : "+alamat+"?id="+JenisIuranId);
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
            populateJenisIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JenisIuran get(String JenisIuranId) {
        populateJenisIuran();
        JenisIuran jenisIuran = null;
        for (JenisIuran _jenisIuran : listJenisIuran) {
            if (String.valueOf(_jenisIuran.getIuranJenisId()).equals(JenisIuranId)) {
                jenisIuran = _jenisIuran;
            }
        }
        return jenisIuran;
    }

    @Override
    public List<JenisIuran> getAll() {
        populateJenisIuran();
        return listJenisIuran;
    }

    @Override
    public List<JenisIuran> getCari(String JenisIuranNama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        populateJenisIuran();
        return listJenisIuran.size();
    }

    
}
