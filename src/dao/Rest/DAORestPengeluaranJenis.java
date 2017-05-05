/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.Rest;
import static dao.Rest.DAORestJenisIuran.alamat;
import dao.implementPengeluaranJenis;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import object.PengeluaranJenis;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author ROSANIA
 */
public class DAORestPengeluaranJenis implements implementPengeluaranJenis {
    private List<PengeluaranJenis> listPengeluaranJenis;
    public static String alamat = "http://localhost/siput-server/index.php/Pengeluaran_jeniss";

    public DAORestPengeluaranJenis() {
        populatePengeluaranJenis();
    }

    @Override
    public void insert(PengeluaranJenis b) {
        int id = 0;
        try {
            URL url = new URL(alamat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String urlParameters = "pengeluaran_nama=" + b.getPengeluaran_nama()
                    + "&pengeluaran_keterangan=" + b.getPengeluaran_keterangan();
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
            populatePengeluaranJenis();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populatePengeluaranJenis() {
        listPengeluaranJenis = new ArrayList<>();
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
            listPengeluaranJenis.clear();
            for (int i = 0; i < json.size(); i++) {

                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                //System.out.println(jo.get("pengeluaran_nama").toString());
                listPengeluaranJenis.add(new PengeluaranJenis(Integer.valueOf(jo.get("pengeluaran_jenis_id").toString()), 
                        jo.get("pengeluaran_nama").toString(), 
                        jo.get("pengeluaran_keterangan").toString()));
                //new PengeluaranJenis(1, "kuda", "kuda");

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

    public PengeluaranJenis get(String PengeluaranJenisId) {
        populatePengeluaranJenis();
        PengeluaranJenis pengeluaranjenis = null;
        for (PengeluaranJenis _pengeluaranjenis : listPengeluaranJenis) {
            if (String.valueOf(_pengeluaranjenis.getPengeluaran_jenis_id()).equals(PengeluaranJenisId)) {
                pengeluaranjenis = _pengeluaranjenis;
            }
        }
        return pengeluaranjenis;
    }

    @Override
    public void update(PengeluaranJenis b) {
        try {
            URL url = new URL(alamat + "?id=" + b.getPengeluaran_jenis_id());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input
                    = 
                   "{"
                    + "\"pengeluaran_nama\":\"" + b.getPengeluaran_nama()
                    + "\"pengeluaran_keterangan\"\":\"" + b.getPengeluaran_keterangan()
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
            populatePengeluaranJenis();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String PengeluaranJenisId) {
        try {
            URL url = new URL(alamat + "?id=" + PengeluaranJenisId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : " + alamat + "?id=" + PengeluaranJenisId);
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
            populatePengeluaranJenis();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PengeluaranJenis> getAll() {
        populatePengeluaranJenis();
        return listPengeluaranJenis;
    }

    @Override
    public List<PengeluaranJenis> getCari(String PengeluaranNama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        populatePengeluaranJenis();
        return listPengeluaranJenis.size();
    }


    
    
   
}
