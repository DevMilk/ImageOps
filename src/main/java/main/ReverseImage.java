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
import java.util.ArrayList;
import org.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//Google ImageApi reverse image api dğeil. Reverse'ü kullanıp çıkan sayfadaki url leri listelemeliyiz.
public class ReverseImage extends RapidAPI{  
    private RapidAPI gSearch;
    public ReverseImage(String API_KEY   ){
         super(API_KEY,"google-reverse-image-search.p.rapidapi.com",
                "https://google-reverse-image-search.p.rapidapi.com/imgSearch?url=");  
        gSearch = new RapidAPI("b4176007bbmsh019a5302dfbf37ep128a44jsne61077ecbeee","google-search3.p.rapidapi.com",""); //super(API_KEY,"google-search3.p.rapidapi.com","");
    }
    
    private String Upload(String filePath){
        return "";
    }
    
    private String getNextPageUrl(Document doc){
        Elements nextPage = doc.select("#pnnext");
        String nextPageUrl = null; 
        for (Element e: nextPage){
            nextPageUrl = "https://www.google.com" + e.attr("href"); 
        } 
        return nextPageUrl;
    }
     
    private Document getDocument(String URL){
        Document doc = null; 
        try{
            doc = Jsoup.connect(URL).userAgent(  "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36").timeout(20*1000).get();
             
        } 
        catch(Exception e){
            e.printStackTrace();
        }    
        return doc;
    }
    
    //Timeout olabiliyor onu 
    private ArrayList<String> GetURLList(String Response, int depth){
        ArrayList<String> urlList = new ArrayList<String>();
        String pageURL = Response;       
        int counter = 0;
        do{
            Document doc = getDocument(pageURL); 
           
            Elements elements = doc.select(".g .rc .r a");
            for(Element e: elements) 
                urlList.add(e.attr("href"));
            pageURL = getNextPageUrl(doc);
            counter++;
        }while(pageURL!=null && (counter <=depth || depth==-1));
         
        return urlList;
    }
     
    public ArrayList<String> findByLocalFile(String ImagePath,int depth){
        String urlFile  = Upload(ImagePath);
        String Response = getResponse(urlFile); 
        String rootURL = parseResponse(Response);
        ArrayList<String> urlList = GetURLList(rootURL,depth ); 
        return urlList; 
    }
    private String parseResponse(String Response){
        final JSONObject obj = new JSONObject(Response);
        return obj.getString("googleSearchResult");
    }
    public ArrayList<String> findByURL(String ImagePath, int depth ){
        String Response = getResponse(ImagePath);
        String rootURL = parseResponse(Response);
        ArrayList<String> urlList = GetURLList(rootURL, depth); 
        return urlList;
    }
  
}


