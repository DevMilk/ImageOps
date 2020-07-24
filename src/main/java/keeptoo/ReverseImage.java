/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

/**
 *
 * @author Ugur
 */  
package keeptoo; 
import okhttp3.OkHttpClient; 
import okhttp3.Request; 
import okhttp3.Response;  

public class ReverseImage {
    private String API_KEY;
    private String engine;
    
    public ReverseImage(String API_KEY  ){
        this.API_KEY = API_KEY;
        this.engine = "google_reverse_image";
    }
    
    private String getResultUrl(String ImageURL){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
	.url(ImageURL)
	.get()
	.addHeader("x-rapidapi-host", "google-reverse-image-search.p.rapidapi.com")
	.addHeader("x-rapidapi-key", API_KEY)
	.build();

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
        
        String resultUrl = "None";
        try{
            return response.body().string();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        return resultUrl;
        }
}


