/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Ugur
 */
public class Tests {
    public Tests(){
        String path =  "/images/Image.png" ;
        FileUpload testUpload = new FileUpload("dyokzmlbd","193473836574824","r29-Wx6WgYdL__iEqY2zW5E_vP8");
        testUpload.uploadFile(path);
    }
}
