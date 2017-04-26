
package dao.Rest;


import dao.implementTransaksi;
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
//import dao.implementDeposit;
import model.TransaksiModel;
import model.Transaksi;
import model.User;
//import model.User;

public class DAORestTransaksi implements implementTransaksi {

    private List<Transaksi> listTransaksi;
    public static String alamat = "http://localhost/siput-server/index.php/transaksis";

    public DAORestTransaksi() {
        populateTransaksi();
    }

    @Override
    public void insert(Transaksi b) {
        try {
            URL url = new URL(alamat + "?id=" + b.getTransaksiId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = 
                    "{"
                    + "\"transaksi_date\":\"" + b.getTransaksiDate()
                    + "\",\"transaksi_nama\":\"" + b.getTransaksiNama()
                    + "\",\"transaksi_nominal\":\"" + b.getTransaksiNominal()
                    + "\",\"user_id\":\"" + b.getUserId()
                    + "\",\"transaksi_tipe\":\"" +b.getTransaksiTipe()
                    +"\",\"pengeluaran_id\":\"" +b.getPengeluaranId()
                    +"\",\"iuran_id\":\"" +b.getIuranId()
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
            populateTransaksi();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateTransaksi() {
        listTransaksi = new ArrayList<>();
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
            listTransaksi.clear();
            for (int i = 0; i < json.size(); i++) {
//EROR KARENA KONSTRUKTOR//
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                //System.out.println(jo.get("iuran_nama").toString());
                listTransaksi.add(new Transaksi(Integer.valueOf(jo.get("transaksi_id").toString()), 
                        Integer.valueOf(jo.get("transaksi_nominal").toString()), 
                        Integer.valueOf(jo.get("user_id").toString()), 
                        jo.get("iuran_id").toString(), 
                        jo.get("pengeluaran_id").toString(), 
                        jo.get("transaksi_date").toString(), 
                        jo.get("transaksi_nama").toString(), 
                        jo.get("transaksi_tipe").toString()));
                       
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

    public Transaksi getTransaksiId(String transaksi_id) {
        populateTransaksi();
        Transaksi transaksi = null;
        for (Transaksi _transaksi : listTransaksi) {
            if (String.valueOf(_transaksi.getTransaksiId()).equals(transaksi_id)) {
                transaksi = _transaksi;
            }
        }
        return transaksi;
    }

    @Override
    public void update(Transaksi b) {
        try {
            URL url = new URL(alamat + "?id=" + b.getTransaksiId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input=
                    
                  "{"
                    + "\"transaksi_date\":\"" + b.getTransaksiDate()
                    + "\",\"transaksi_nama\":\"" + b.getTransaksiNama()
                    + "\",\"transaksi_nominal\":\"" + b.getTransaksiNominal()
                    + "\",\"user_id\":\"" + b.getUserId()
                    + "\",\"transaksi_tipe\":\"" +b.getTransaksiTipe()
                    + "\",\"pengeluaran_id\":\"" +b.getPengeluaranId()
                    + "\",\"iuran_id\":\"" +b.getIuranId()
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
            populateTransaksi();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String transaksi_id) {
        try {
            URL url = new URL(alamat + "?id=" + transaksi_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : " + alamat + "?id=" + transaksi_id);
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
            populateTransaksi();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaksi> getCari(String IuranNama) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser(Transaksi b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iuran getIuran(Transaksi b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pengeluaran getPengeluaran(Transaksi b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaksi get(String transaksiId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaksi> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getJumlahKas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getJumlahIuran() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getJumlahPengeluaran() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getJumlahTransaksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Transaksi getTransaksiPertama(String user_id, String iuran_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
