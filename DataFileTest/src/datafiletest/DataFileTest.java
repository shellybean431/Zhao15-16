/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafiletest;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sheldon
 */
public class DataFileTest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DataOutputStream dat;
        String path = "/Users/Sheldon/Documents/2013wesp.pdf";
        
        try {
            dat = new DataOutputStream(new FileOutputStream("dhua.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String name = "Celia";
        int age = 34;
        double height = 167.5;
        
        try {
            
            dat.writeBytes(name);
            dat.write(age);
            dat.writeDouble(height);        
            dat.flush();
             dat.close();
        } catch (IOException ex) 
        {
            System.out.println("something went wrong");
        } catch (NullPointerException x)
        {
            System.out.println("Somethins is wrong");
        }
        
        
    }
    
}
