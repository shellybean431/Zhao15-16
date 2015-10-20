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

    public static List<String> test = new ArrayList<>();
        
        
    public void addName(String s)
    {
        s = clearSpace(s);
        names.add(s);
        
    }
    
    public static List<String> fName(List<String> list)
    {
        List<String> temp = migrate(sort(list));
                            System.out.println("******THE CURRENT LIST GOES:******");
        printNames(names);
                            System.out.println("******THE END OF THE LIST:******");
        return temp;
    }
    
    public static List<String> lName(List<String> list)
    {
        List<String> temp = sort(swapByLast(migrate(list)));
        
                             System.out.println("******THE CURRENT LIST GOES:******");
        printNames(names);
                            System.out.println("******THE END OF THE LIST:******");       
        return temp;
    }
    
    public static List<String> mName(List<String> list)
    {
        List<String> temp = sort(swapByMiddle(migrate(list)));
                             System.out.println("******THE CURRENT LIST GOES:******");
        printNames(names);
                            System.out.println("******THE END OF THE LIST:******");   
        return temp;
        
    }
    
    public static List<String> migrate(List<String> list)
    {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<list.size(); i++)
        {
            temp.add(list.get(i));
        }
        return temp;
    }
    

    public static List<String> sort(List<String> list)
    {
        List<String> temp = migrate(list);
        
        int i, j;
        String key;
        for(i=1; i<temp.size(); i++)
        {
            key = temp.get(i);
            j = i-1;
            while(j>=0 && key.compareTo(temp.get(j))<0)
            {
                temp.set(j+1, temp.get(j));
                j--;
            }
            temp.set(j+1, key);
        }
        
        return temp;
    }
    
    public static List<String> swapByLast(List<String> list)
    {
        List<String> temp = new ArrayList<String>(list.size());
        for (int i=0; i<list.size(); i++)
        {
            String name = list.get(i);
            String res = "";
            int pos;
            if(!suffix(name))
            {
                while(name.indexOf(" ")!=-1)
                {
                    name = name.substring(name.indexOf(" ")+1);
                }
                pos = list.get(i).indexOf(name);
                res = name + ", " + list.get(i).substring(0, pos);
                temp.add(res);
            }
            
            else if(suffix(name))
            {
                while(name.indexOf(" ")!=-1)
                {
                    name = name.substring(name.indexOf(" ")+1);
                }
                String remain = list.get(i).substring(0, list.get(i).indexOf(name)-1);
                                
                while(remain.indexOf(" ")!=-1)
                {
                    remain = remain.substring(remain.indexOf("")+1);
                }
                System.out.println("***" + remain);
                res = remain + " " + name + ", " + list.get(i).substring(0, list.get(i).indexOf(remain)-1);
                temp.add(res);
            }
        }
        return temp;
    }
    
    public static List<String> swapByMiddle(List<String> list)
    {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<list.size(); i++)
        {
            String name = list.get(i);
            String res = "";
            String noF = name.substring(name.indexOf(" ")+1);
            
            if(noF.indexOf(" ")!=-1&&!suffix(name))
            {
                int pos = name.indexOf(" ");
                res = noF + ", " + name.substring(0, pos);
                temp.add(res);
            }
            
            else
            {
                res = " - " + noF + ", " + name.substring(0, name.indexOf(noF));
                temp.add(res);
            }       
        }
        return temp;
    }
    
    public static String clearSpace(String s)
    {
        String tmp = s;
        try{
            String temp = tmp.substring(0, 1);
            while (temp.equals(" "))
            {
                tmp=tmp.substring(1);
                temp = tmp.substring(0, 1);
            }
            return tmp;
        }
        catch(NullPointerException e)
        {
            return tmp;
        }
    }
    
    public static boolean suffix(String name)
    {
        boolean found = false;
        String[] sx = {" Jr.", " Sr.", " II", " III", " IV", " V", " Ph.D", " MD"};
        for(int i=0; i<sx.length; i++)
        {
            if (name.indexOf(sx[i])!=-1)
            {
                found = true;
            }
        }
        return found;
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
//        test = fName(names);
//        for(int i = 0; i<names.size(); i++)
//        {
//            System.out.println(test.get(i));
//        }
//        test = lName(names);
//        for(int i = 0; i<names.size(); i++)
//        {
//            System.out.println(test.get(i));
//        }
//        
    }
    
}
