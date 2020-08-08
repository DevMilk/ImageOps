/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
 
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ugur
 */
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
public class FileUpload { 
    public FileUpload(String cloud_name, String api_key, String api_secret){
          
    } 
     public String uploadFile(String filePath){ 

         try{
            // Use the application default credentials
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();
            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build();
            FirebaseApp.initializeApp(options);

            Firestore db = FirestoreClient.getFirestore();
         }
         catch(Exception E){
             
         }
        return null;
     }
      }  
