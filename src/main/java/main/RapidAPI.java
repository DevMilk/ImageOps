/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
 
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//My RapidAPI class to get response from all kindss of api from rapidAPI website
public class RapidAPI {
    
    protected String API_KEY; 
    protected String Host; 
    protected String prefix; 
    
    //Constructor
    public RapidAPI(String API_KEY, String Host ,String prefix ){
        this.API_KEY = API_KEY; 
        this.Host = Host;
        this.prefix = prefix;
    }
      
    //Get response of API of given request 
    public String getResponse(String Request){
        
        //Initialize client 
        OkHttpClient client = new OkHttpClient(); 
        
        //Build request by adding headers and url 
        Request request = new Request.Builder()
	.url(prefix + Request)
	.get()
	.addHeader("x-rapidapi-host", this.Host) 
	.addHeader("x-rapidapi-key", this.API_KEY)
	.build();

        //Initialize response    
        Response response = null;    
        
        //Execute Request and get response
        try{    
            response = client.newCall(request).execute();
        }
        catch(Exception e){
            e.printStackTrace();
        } 
        
        //If response state is 200 (Successfull request, return response body as a string)
        if(response!= null && response.code() == 200){
            try{
                return response.body().string();
            }
            catch(Exception e){
                e.printStackTrace();
            } 
        } 
          
        return null;
        }
}
