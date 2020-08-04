/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.Object;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
        
/**
 *
 * @author Ugur
 */ 
public class SYSTRAN_IO extends RapidAPI{
    public SYSTRAN_IO(String API){
        super(API,"systran-systran-platform-for-language-processing-v1.p.rapidapi.com","");
        //TEXT&lang=LANG     Buradaki LANG'I Language Identification metodundan bul
    }
    private Document getDocument(String URL) {
        Document doc = null; 
        try{
            doc = Jsoup.connect(URL).userAgent(  "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36").get();
        } 
        catch(Exception e){
            e.printStackTrace();
        }    
        return doc;
    }
    //Get language of given text
    public String getLanguage(String Text){
        String Response = getResponse("https://systran-systran-platform-for-language-processing-v1.p.rapidapi.com/nlp/lid/detectLanguage/document?input="+Text);
        JSONObject obj = new JSONObject(Response);
        JSONArray languages = obj.getJSONArray("detectedLanguages");
        obj = languages.getJSONObject(0);
        String language = obj.getString("lang");
        return language;
    } 
    
     // input encode edilmiyor galiba.
     public String getResponse(String Text, String lang ,boolean OnlyLetters)   {
         String TextEncoded = null;
         try{ 
           //Remove all non-letter characters to get persons correctly
           if(OnlyLetters)
                TextEncoded =  Text.replaceAll("[^a-zA-Z]",  " ");  
           else
               TextEncoded = Text;
           TextEncoded = URLEncoder.encode(TextEncoded, StandardCharsets.UTF_8.toString());
           TextEncoded = TextEncoded.replace("+","%2520"); 
         }
         catch(Exception e){
             
         }
        String URL =  "https://systran-systran-platform-for-language-processing-v1.p.rapidapi.com/nlp/ner/extract/entities?input="+TextEncoded+"&lang="+lang;
        OkHttpClient client = new OkHttpClient();  
        Request request = new Request.Builder()
	.url(URL)
	.get()  
	.addHeader("x-rapidapi-host", this.Host) 
	.addHeader("x-rapidapi-key", this.API_KEY)
	.build()  ;

        Response response = null;    
        try{    
            response = client.newCall(request).execute();
        }
        catch(Exception e){
            e.printStackTrace();
        } 
        if(response.code() == 200){
            System.out.println("Sorun Yok");
        } 
         
        try{
            return response.body().string();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return null;
        }
    //Get persons from given test by using named entity recognixer
    public ArrayList<String> getPersons(String URL){
        //initialize persons array 
        ArrayList<String> persons = new ArrayList<String>();
        // get and convert to string a page content of given URL 
        
        Document doc = getDocument(URL); 
        if(doc == null)
            return null;
        String Text = doc.text();
        System.out.println(Text);
        //Get language of given text
        try{
            /*String lang = getLanguage(Text);
            if (!lang.equals("en")){
                URL = "https://translate.google.com/translate?hl=en&sl="+lang+"&u=" +URL+"&prev=search&pto=aue";
                Elements translated = getDocument(URL).select("html");
                for(Element e: translated){
                    Text =    e.text();
                }
            } */
                
             
            //Use api to get entities
            String Response = getResponse(Text,"en",true);

            //initialize JSONObject for parsing response acquired from API
            JSONObject obj = new JSONObject(Response);
            //Get Entity array
            JSONArray entities = obj.getJSONArray("entities"); 
            //Get persons
            for( int i=0;i<entities.length();i++){
                JSONObject entity = entities.getJSONObject(0); 
                if(entity.getString("type").equals("ENAMEX_person"))
                    persons.add(entity.getString("value"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return persons;
    } 
    
    
}
