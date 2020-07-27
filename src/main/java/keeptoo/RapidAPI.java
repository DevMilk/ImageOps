/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keeptoo;

import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Ugur
 */
public class RapidAPI {
     private String API_KEY; 
    private String Host; 
    private String prefix; 
    public RapidAPI(String API_KEY, String Host ,String prefix ){
        this.API_KEY = API_KEY; 
        this.Host = Host;
        this.prefix = prefix;
    }
      
    
    public String getResponse(String ImageURL){
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
	.url(prefix + ImageURL)
	.get()
	.addHeader("x-rapidapi-host", this.Host) 
	.addHeader("x-rapidapi-key", this.API_KEY)
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
