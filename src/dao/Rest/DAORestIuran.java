
package dao.Rest;

import static dao.Rest.DAORestUser.alamat;
import dao.implementIuran;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import dao.implementIuran;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import model.JenisIuran;
import model.KategoriIuran;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DAORestIuran implements implementIuran{
    
    
    private List<Iuran> listIuran;
    public static String alamat = "http://localhost/siput-server/index.php/iurans";

    public DAORestIuran() {
        populateIuran();
    }

    @Override 
    public int insert(Iuran b) {
        int id = 0;
        try {
            URL url = new URL(alamat);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            String urlParameters  = "iuran_nama="+b.getIuranId()+
                    "&iuran_nominal="+b.getIuranNominal()+
                    "&iuran_nama="+b.getIuranNama()+
                    "&iuran_jenis_id="+b.getIuranJenisId()+
                    "&iuran_kategori_id="+b.getIuranKategoriId();
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;   
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
               wr.write( postData );
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
                response = response+output;
            }
            System.out.println("respone :"+response);
            //ini parsing data output menggunakan jsoup, diambil id nya
            Document d = Jsoup.parse(response);
            Elements tables = d.select("table > tbody > tr > td");
            Element e = tables.first();
            System.out.println(e.text());
            id = Integer.valueOf(e.text());
            
            conn.disconnect();
            populateIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void populateIuran() {
        listIuran = new ArrayList<>();
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
            listIuran.clear();
            for (int i = 0; i < json.size(); i++) {
                
                JSONObject jo = (JSONObject) jp.parse(json.get(i).toString());
                System.out.println(jo.get("iuran_nama").toString());
                listIuran.add(new Iuran(Integer.valueOf(jo.get("iuran_id").toString()), 
                        Integer.valueOf(jo.get("iuran_jenis_id").toString()),
                        Integer.valueOf(jo.get("iuran_kategori_id").toString()), 
                        jo.get("iuran_nama").toString(), 
                        Integer.valueOf(jo.get("iuran_nominal").toString())));
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

        public Iuran getIuranId(String iuran_id) {
        populateIuran();
        Iuran iuran = null;
        for (Iuran _iuran : listIuran) {
            if (String.valueOf(_iuran.getIuranId()).equals(iuran_id)) {
                iuran = _iuran;
            }
        }
        return iuran;
    }

    @Override
    public void update(Iuran b) {
        try {
            URL url = new URL(alamat+"?id="+b.getIuranId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            String input = "{"
                    + "\",\"iuran_nama\":\"" + b.getIuranNama()
                    + "\",\"iuran_nominal\":\"" + b.getIuranNominal()
                    + "\",\"iuran_jenis_id\":\"" + b.getIuranJenisId()
                    + "\",\"iuran_kategori_id\":\"" + b.getIuranKategoriId()
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
            populateIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String user_id) {
        try {
            URL url = new URL(alamat+"?id="+user_id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Content-Type", "application/json");
            //conn.addRequestProperty("Authorization", LoginDAOREST.user);
            System.out.println("alamat url : "+alamat+"?id="+user_id);
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
            populateIuran();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//BELUM DIISIKAN BODY OVERIDDENYA//
    
    @Override
    public List<Iuran> getAll() {
        populateIuran();
        return listIuran;
    }

    @Override
    public List<Iuran> getCari(String displayname) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCount() {
        populateIuran();
        return listIuran.size();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iuran get(String IuranId) {
        populateIuran();
        Iuran iuran = null;
        for (Iuran _iuran : listIuran) {
            if (String.valueOf(_iuran.getIuranId()).equals(IuranId)) {
                iuran = _iuran;
            }
        }
        return iuran;
    }

    @Override
    public JenisIuran getJenisIuran(Iuran b) {
        DAORestJenisIuran restJenisIuran = new DAORestJenisIuran();
        Iuran iuran = get(String.valueOf(b.getIuranJenisId()));
        JenisIuran jenisIuran = restJenisIuran.get(String.valueOf(iuran.getIuranJenisId()));
        
        return jenisIuran;
    }

    @Override
    public KategoriIuran getKategoriIuran(Iuran b) {
        DAORestKategoriIuran restKategoriIuran = new DAORestKategoriIuran();
        Iuran iuran = get(String.valueOf(b.getIuranJenisId()));
        KategoriIuran kategoriIuran = restKategoriIuran.get(String.valueOf(iuran.getIuranKategoriId()));
        
        return kategoriIuran;
    }
    
    

}
