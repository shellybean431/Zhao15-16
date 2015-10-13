/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns;

import static java.lang.Math.*;
import java.util.*;

/**
 *
 * @author Sheldon
 */
public class NS {

    /**
     * @param args the command line arguments
     */
    
    public static List<String> names = new ArrayList<>();
    
    public void addName(String s)
    {
        names.add(s);
    }
    
    public static void fName(List<String> list)
    {
        sort(list);
    }
    
    public static void lName(List<String> list)
    {
        swapByLast(list);
        sort(list);
    }
    

    public static void sort(List<String> list)
    {
        int i, j;
        String key;
        for(i=1; i<list.size(); i++)
        {
            key = list.get(i);
            j = i-1;
            while(j>=0 && key.compareTo(list.get(j))<0)
            {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1, key);
        }
    }
    
    public static void swapByLast(List<String> list)
    {
        for (int i=0; i<list.size(); i++)
        {
            String temp = list.get(i);
            String res = "";
            while(temp.indexOf(" ")!=-1)
            {
                temp = temp.substring(temp.indexOf(" ")+1);
            }
            int pos = list.get(i).indexOf(temp);
            res = temp + " " + list.get(i).substring(0, pos);
            list.set(i, res);
        }
    }

    public static void printNames(List<String> list)
    {
        for(int i=0; i<list.size(); i++)
        {
            System.out.println(list.get(i));
        }
    }
    
    public static String displayNames(List<String> list)
    {
        String temp = "";
        for(int i=0; i<list.size(); i++)
        {
            temp+=list.get(i);
            temp+="\n";
        }
        
        return temp;
    }
    
    public static String[] toArray(List<String> list)
    {
        String[] temp = new String[list.size()];
        for (int i=0; i<list.size(); i++)
        {
            temp[i] = list.get(i);
        }
        return temp;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        UI.main(args);
//        names.add("Stevie Wonder");
//        names.add("Jack Becker");
//        names.add("Louisa Aubakckafhue");
//        fName(names);
//        for(int i = 0; i<names.size(); i++)
//        {
//            System.out.println(names.get(i));
//        }
    }
    
}
