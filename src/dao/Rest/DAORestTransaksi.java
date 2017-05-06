package dao.Rest;

import dao.implementTransaksi;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Iuran;
import object.Pengeluaran;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import dao.implementDeposit;
import object.Transaksi;
import object.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
            URL url = new URL(alamat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String urlParameters = "transaksi_nama=" + b.getTransaksiNama()
                    + "&transaksi_date=" + b.getTransaksiDate()
                    + "&transaksi_nominal=" + b.getTransaksiNominal()
                    + "&user_id=" + b.getUserId()
                    + "&transaksi_tipe=" + b.getTransaksiTipe()
                    + "&iuran_id=" + b.getIuranId()
                    + "&pengeluaran_id=" + b.getPengeluaranId();
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
            int id = Integer.valueOf(e.text());
            System.out.println("Added id :"+id);
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
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                //System.out.println(jo.get("iuran_nama").toString());
                listTransaksi.add(new Transaksi(Integer.valueOf(jo.get("transaksi_id").toString()),
                        Integer.valueOf(jo.get("transaksi_nominal").toString()),
                        Integer.valueOf(jo.get("user_id").toString()),
                        jo.get("iuran_id") == null ? null : jo.get("iuran_id").toString(),
                        jo.get("pengeluaran_id") == null ? null : jo.get("pengeluaran_id").toString(),
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
            String input
                    = "{"
                    + "\"transaksi_date\":\"" + b.getTransaksiDate()
                    + "\",\"transaksi_nama\":\"" + b.getTransaksiNama()
                    + "\",\"transaksi_nominal\":\"" + b.getTransaksiNominal()
                    + "\",\"user_id\":\"" + b.getUserId()
                    + "\",\"transaksi_tipe\":\"" + b.getTransaksiTipe()
                    + "\",\"pengeluaran_id\":\"" + b.getPengeluaranId()
                    + "\",\"iuran_id\":\"" + b.getIuranId()
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
        DAORestUser restUser = new DAORestUser();
        Transaksi transaksi = get(String.valueOf(b.getTransaksiId()));
        User user = restUser.getUser(String.valueOf(transaksi.getUserId()));

        return user;
    }

    @Override
    public Iuran getIuran(Transaksi b) {
        DAORestIuran restIuran = new DAORestIuran();
        Transaksi transaksi = get(String.valueOf(b.getTransaksiId()));
        Iuran iuran = restIuran.get(String.valueOf(transaksi.getIuranId()));
        return iuran;
    }

    @Override
    public Pengeluaran getPengeluaran(Transaksi b) {
        DAORestPengeluaran restPengeluaran = new DAORestPengeluaran();
        Transaksi transaksi = get(String.valueOf(b.getTransaksiId()));
        Pengeluaran pengeluaran = restPengeluaran.get(String.valueOf(transaksi.getPengeluaranId()));
        return pengeluaran;
    }

    @Override
    public Transaksi get(String transaksiId) {
        populateTransaksi();
        Transaksi transaksi = null;
        for (Transaksi _transaksi : listTransaksi) {
            if (String.valueOf(_transaksi.getTransaksiId()).equals(transaksiId)) {
                transaksi = _transaksi;
            }
        }
        return transaksi;

    }

    @Override
    public List<Transaksi> getAll() {
        populateTransaksi();
        return listTransaksi;
    }

    @Override
    public int getCount() {
        populateTransaksi();
        return listTransaksi.size();
    }

    @Override
    public int getJumlahKas() {
        return getJumlahIuran() - getJumlahPengeluaran();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getJumlahIuran() {
        int jumlah = 0;
        try {
            URL url = new URL(alamat + "?param=jumlahIuran");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //ini ambil output data lalu dimasukkan ke string response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String response = null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = output;
            }

            JSONParser jp = new JSONParser();
            JSONArray json = (JSONArray) jp.parse(response);
            JSONObject jo = (JSONObject) json.get(0);
            jumlah = Integer.valueOf(jo.get("Jumlah").toString());

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }

    @Override
    public int getJumlahPengeluaran() {
        int jumlah = 0;
        try {
            URL url = new URL(alamat + "?param=jumlahPengeluaran");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //ini ambil output data lalu dimasukkan ke string response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String response = null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = output;
            }

            JSONParser jp = new JSONParser();
            JSONArray json = (JSONArray) jp.parse(response);
            JSONObject jo = (JSONObject) json.get(0);
            jumlah = jo.get("Jumlah") == null ? 0 : Integer.valueOf(jo.get("Jumlah").toString());
            System.out.println("jumlah : " + jumlah);

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }

    @Override
    public int getJumlahTransaksi() {
        int jumlah = 0;
        try {
            URL url = new URL(alamat + "?param=jumlahTransaksi");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
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
            JSONObject jo = (JSONObject) jp.parse(json.get(0).toString());
            jumlah = Integer.valueOf(jo.get("Jumlah").toString());
            System.out.println(jo.get("Jumlah").toString());

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }

    @Override
    public Transaksi getTransaksiPertama(String user_id, String iuran_id) {
        Transaksi transaksi = null;
        try {
            URL url = new URL(alamat + "?param=transaksiPertama&user_id=" + user_id + "&iuran_id=" + iuran_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
//            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
//                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//            }
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
            JSONObject jo = (JSONObject) json.get(0);
            listTransaksi.clear();
            transaksi = new Transaksi(Integer.valueOf(jo.get("transaksi_id").toString()),
                    Integer.valueOf(jo.get("transaksi_nominal").toString()),
                    Integer.valueOf(jo.get("user_id").toString()),
                    jo.get("iuran_id") == null ? null : jo.get("iuran_id").toString(),
                    jo.get("pengeluaran_id") == null ? null : jo.get("pengeluaran_id").toString(),
                    jo.get("transaksi_date").toString(),
                    jo.get("transaksi_nama").toString(),
                    jo.get("transaksi_tipe").toString());

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return transaksi;
    }

    @Override
    public int getUtang(int user_id, int iuran_id) {
         int jumlah = 0;
        try {
            URL url = new URL(alamat + "?param=getUtang&user_id="+user_id+"&iuran_id="+iuran_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //ini ambil output data lalu dimasukkan ke string response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String response = null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = output;
            }

            jumlah = Integer.valueOf(response.replace(" ", ""));
            //System.out.println("Jumlah utang: "+jumlah);

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }

    @Override
    public int getTotalBayar(int user_id, int iuran_id) {
    int jumlah = 0;
        try {
            URL url = new URL(alamat + "?param=getTotalBayar&user_id="+user_id+"&iuran_id="+iuran_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //ini ambil output data lalu dimasukkan ke string response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String response = null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = output;
            }

            jumlah = Integer.valueOf(response.replace(" ", ""));
            //System.out.println("Jumlah utang: "+jumlah);

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }

    @Override
    public int getTotalDibayar(int user_id, int iuran_id) {
        int jumlah = 0;
        try {
            URL url = new URL(alamat + "?param=getTotalDibayar&user_id="+user_id+"&iuran_id="+iuran_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            //ini ambil output data lalu dimasukkan ke string response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            String response = null;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = output;
            }

            jumlah = Integer.valueOf(response.replace(" ", ""));
            //System.out.println("Jumlah utang: "+jumlah);

            conn.disconnect();

        } catch (MalformedURLException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DAORestTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah;
    }

}
