/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
 
import java.util.ArrayList;  
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject; 
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
       // System.out.println(entities.toString());
        Pattern p = Pattern.compile("\\'([^\\']*)\\'\\,\\ \\'PERSON'");
        Matcher m = p.matcher(entities.toString());
        while (m.find()) {
          persons.add(m.group(1));
        }
        return persons;
        }
    public ArrayList<String> getPersons(ArrayList<String> URLs){
        
        ArrayList<String> persons = new ArrayList<>();
        TreeMap<String,Integer> PersonsWFreq = new TreeMap<>();
        for(String URL: URLs) {
            ArrayList<String> personsAtURL = getPersons(URL);
            if(personsAtURL!=null)
                personsAtURL.forEach(Person -> {
                    PersonsWFreq.merge(Person, 1, Integer::sum);
                });  
        }
        
        // Yüzdeliklerle göndermek için fonksiyonun dönüş tipi Map olmalı, döndürmesek mi?
        PersonsWFreq.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .forEach(entry -> { 
            persons.add(entry.getKey());
        });
         
        return persons;
    }
}
