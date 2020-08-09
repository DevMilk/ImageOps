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
    
    //Constructor
    public TextAnalyzer(String API){
        super(API,"text-analyzer.p.rapidapi.com","https://text-analyzer.p.rapidapi.com/analyze-text/ner?url="); 
    }
    
    //Get entities from given url  
    public ArrayList<String> getEntities(String URL, String Entity){ 
        
        //Encoding maybe will be necessary later
       /* try{
          TextEncoded = URLEncoder.encode(URL, StandardCharsets.UTF_8.toString());
        }
        catch(Exception e){
            return null;
        }
        TextEncoded = TextEncoded.replace("+","%2520"); 
        TextEncoded = TextEncoded.replace("%","%25"); */
       
        //Get response 
        String Response = getResponse(URL);
        ArrayList<String> entityList = new ArrayList<String>(); 
        JSONObject obj = null;
        try{
            obj = new JSONObject(Response);  
        }
        catch(Exception e){
            System.out.println("URL Encoding Warning");
            return null;
        }
        
        //Get entities that user requested from api using REGEX  
        JSONObject entities = obj.getJSONObject("Entities");
        
        //Regex i wrote for extracting entity names from untidy api response (API is  definitely not an REST API!)
        Pattern p = Pattern.compile("\\'([^\\']*)\\'\\,\\ \\'"+Entity+"'");
        Matcher m = p.matcher(entities.toString());
        while (m.find()) {
          entityList.add(m.group(1));
        }
        
        return entityList;
        
    }
    
    /*Get All entities of given urls, store them 
    on treemap to sort them by frequency on reverse order efficiently and change value of bar */
    public ArrayList<String> getEntities(ArrayList<String> URLs, javax.swing.JProgressBar bar, String Entity , Boolean parallel){
        
        //Initialize entities array , and treemap for storing frequency of entities and names
        ArrayList<String> entities = new ArrayList<>();
        TreeMap<String,Integer> PersonsWFreq = new TreeMap<>(); 
        
        //Set increment sscale of progress bar
        int increment = Math.max((int)((float)100/URLs.size() ),1);  
        
        // Iteration for Parallel or Normal threading, Parallel Threading is not recommended yet 
        Stream<String> stream = StreamSupport.stream(URLs.spliterator(), parallel);
        stream.forEach(URL -> {
            //Get entities from given URL
            ArrayList<String> personsAtURL = getEntities(URL,Entity);
            if(personsAtURL!=null){
                //Merge map of entities to count their frequencies 
                personsAtURL.forEach(Person -> {
                    PersonsWFreq.merge(Person, 1, Integer::sum);
                });  
                
            }
            bar.setValue(bar.getValue()+increment);    
        }); 
        
        // Sort treemap by their frequencies
        PersonsWFreq.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .forEach(entry -> { 
            entities.add(entry.getKey());
        });
        
        //Set bar value 
        bar.setValue(100);
        
        //Return Entities 
        return entities;
    }
}
