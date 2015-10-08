/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jswingtest3;

import static java.lang.Double.parseDouble;

/**
 *
 * @author Sheldon
 */
public class TempData {
    
    String myInU;
    String myOutU;
    double myInNum;
    double myOutNum;
    int myDec;
    
    public TempData()
    {
        
    }
    
    public TempData(String inU, String outU, double inNum, double outNum, int dec)
    {
        myInU = inU;
        myOutU = outU;
        myInNum = inNum;
        myOutNum = outNum;
        myDec = dec;
    }
    
    public TempData(String input)
    {
        myInNum = parseDouble(input);
    }
    
    public double getInNum()
    {
        return myInNum;
    }
    
    @Override
    public String toString()
    {
        String temp = "Input: **" + myInNum + myInU + "   Output: ##" + myOutNum 
                + myOutU + "   Decimal: ||" + myDec + " digits";
        return temp;
    }
}
