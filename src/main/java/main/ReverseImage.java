/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ugur
 */
package main;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject; 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Google ImageApi reverse image api dğeil. Reverse'ü kullanıp çıkan sayfadaki url leri listelemeliyiz.
public class ReverseImage extends RapidAPI {

    //ımgur client id for uploading local files for search
    private String IMGUR_ID;

    public ReverseImage(String API_KEY, String IMGUR_ID) {
        super(API_KEY, "google-reverse-image-search.p.rapidapi.com",
                "https://google-reverse-image-search.p.rapidapi.com/imgSearch?url=");

        this.IMGUR_ID = IMGUR_ID;
    }

    //Upload File to server from local 
    String Upload(String filePath) throws Exception {
        
        //Turn selected image to byte array
        byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath));
        //Turn byte array to base64 data
        String EncodedString = Base64.getEncoder().encodeToString(fileContent);
        //initialize http client
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        //Set request body and add base64 data to it as a form data part    
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("image", EncodedString)
                .build();
        //Post request to imgur api to upload file 
        Request request = new Request.Builder()
                .url("https://api.imgur.com/3/image")
                .method("POST", body)
                .addHeader("Authorization", "Client-ID " + IMGUR_ID)
                .build();
        //Execute request and get JSON response 
        Response response = client.newCall(request).execute();
        
        //Check Response
        if (!(response != null && response.code() == 200)) {
            throw new Exception("Upload Response null or failed, Check API Keys");
        }
        //Parse JSON Response and get link of the uploaded file 
        String responseBody = response.body().string();
        JSONObject responseJSON = new JSONObject(responseBody);
        JSONObject data = responseJSON.getJSONObject("data");
        String link = data.getString("link");
        //return URL of uploaded file  
        return link;
    }

    //Parses google search page and gets next page's url from pagination button attributes   
    private String getNextPageUrl(Document doc) {
        Elements nextPage = doc.select("#pnnext");
        String nextPageUrl = null;
        for (Element e : nextPage) {
            nextPageUrl = "https://www.google.com" + e.attr("href");
        }
        return nextPageUrl;
    }
    //Get Content Of The webpage whose url is given
    private Document getDocument(String URL) {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36").timeout(20 * 1000).get();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    //Get url list of search page, depth: the google search webpage count 
    private ArrayList<String> GetURLList(String Response, int depth) {
        ArrayList<String> urlList = new ArrayList<String>();
        String pageURL = Response;
        int counter = 0;
        try{
            do {
                Document doc = getDocument(pageURL);
                //Get url results of current google search page by executing css query 
                Elements elements = doc.select(".g .rc .r a");
                for (Element e : elements) {
                    //add href of html page elements
                    urlList.add(e.attr("href"));
                }
                pageURL = getNextPageUrl(doc);
                counter++;
            } while (pageURL != null && (counter <= depth || depth == -1));
        }
        catch(Exception e){
            System.out.println("Input is deprecated");
        }
        return urlList;
    }

    //Get Root of Google Search Page by parsing JSON String 
    private String parseResponse(String Response) {
        final JSONObject obj = new JSONObject(Response);
        return obj.getString("googleSearchResult");
    }
    
    //Find given image's reverse image results
    public ArrayList<String> find(String ImagePath, int depth, String lang) {

        //If file is a local file, upload it first and get its URL    
        if ( new File(ImagePath).isDirectory() && Files.exists(Paths.get(ImagePath))) {
            try {
                ImagePath = Upload(ImagePath);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Couldn't upload local file");
                return null;
            }
        }

        //Get root of reverse search page and parse it for receiving rootURL    
        String Response = getResponse(ImagePath);
        String rootURL = parseResponse(Response);

        /*If language is not setted as "current", delete hl parameter from url 
        if exists and change it to language user selected*/    
        if (lang != "current") {
            //I used regex to get hl parameters from url
            Pattern p = Pattern.compile("(&hl=[a-zA-Z]*)");
            Matcher m = p.matcher(rootURL);
            while (m.find()) {
                String delete = m.group(1);
                rootURL = rootURL.replace(delete, "");
            }
            rootURL += "&hl=" + lang;
        }
        //Get all url list with given depth and return list 
        ArrayList<String> urlList = GetURLList(rootURL, depth);
        return urlList;
    }

}
