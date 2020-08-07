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
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
/**
 *
 * @author Ugur
 */
public class TextAnalyzer extends RapidAPI{ 
    public TextAnalyzer(String API){
        super(API,"text-analyzer.p.rapidapi.com","https://text-analyzer.p.rapidapi.com/analyze-text/ner?url=");
        //TEXT&lang=LANG     Buradaki LANG'I Language Identification metodundan bul
    }
    
    public ArrayList<String> getEntities(String URL, String Entity){
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
        Pattern p = Pattern.compile("\\'([^\\']*)\\'\\,\\ \\'"+Entity+"'");
        Matcher m = p.matcher(entities.toString());
        while (m.find()) {
          persons.add(m.group(1));
        }
        return persons;
        }
    public ArrayList<String> getEntities(ArrayList<String> URLs, javax.swing.JProgressBar bar, String Entity , Boolean parallel){
        
        ArrayList<String> persons = new ArrayList<>();
        TreeMap<String,Integer> PersonsWFreq = new TreeMap<>();
        /*for(String URL: URLs) {
            
        }*/ 
        int increment = Math.max((int)((float)100/URLs.size() ),1);  
        Stream<String> stream = StreamSupport.stream(URLs.spliterator(), parallel);
        stream.forEach(URL -> {
            ArrayList<String> personsAtURL = getEntities(URL,Entity);
            if(personsAtURL!=null){
                personsAtURL.forEach(Person -> {
                    PersonsWFreq.merge(Person, 1, Integer::sum);
                });  
                bar.setValue(bar.getValue()+increment);
            }

        }); 
        
        // Yüzdeliklerle göndermek için fonksiyonun dönüş tipi Map olmalı, döndürmesek mi?
        PersonsWFreq.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .forEach(entry -> { 
            persons.add(entry.getKey());
        });
        bar.setValue(100);
        return persons;
    }
}
