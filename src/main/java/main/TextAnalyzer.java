/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.regex.*;  
/**
 *
 * @author Ugur
 */
public class TextAnalyzer extends RapidAPI{ 
    public TextAnalyzer(String API){
        super(API,"text-analyzer.p.rapidapi.com","https://text-analyzer.p.rapidapi.com/analyze-text/ner?url=");
        //TEXT&lang=LANG     Buradaki LANG'I Language Identification metodundan bul
    }
    
    public ArrayList<String> getPersons(String URL){
        String TextEncoded = URL;
       /* try{
          TextEncoded = URLEncoder.encode(URL, StandardCharsets.UTF_8.toString());
        }
        catch(Exception e){
            return null;
        }
        TextEncoded = TextEncoded.replace("+","%2520"); 
        TextEncoded = TextEncoded.replace("%","%25"); */
        String Response = getResponse(TextEncoded);
        ArrayList<String> persons = new ArrayList<String>(); 
        JSONObject obj = null;
        try{
            obj = new JSONObject(Response);  
        }
        catch(Exception e){
            System.out.println("Encoding'de hata var");
            return null;
        }
        JSONObject entities = obj.getJSONObject("Entities"); 
        System.out.println(entities.toString());
        Pattern p = Pattern.compile("\\'([^\\']*)\\'\\,\\ \\'PERSON'");
        Matcher m = p.matcher(entities.toString());
        while (m.find()) {
          persons.add(m.group(1));
        }
        return persons;
        }
}
