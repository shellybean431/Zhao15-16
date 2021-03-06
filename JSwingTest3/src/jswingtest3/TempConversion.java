/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jswingtest3;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;
import java.text.DecimalFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author Sheldon Zhao
 */
public class TempConversion extends JPanel implements ActionListener{

    //declaration of the variables
    JCheckBox input;
    JCheckBox output;
    JLabel label, label1;
    JLabel decimalInfo;
    static JLabel ERR;
    JButton button;
    JTextField val;
    JTextField result;
    JSlider decimal;

    final static String C = "Celcius";
    final static String F = "Farenheit";
    final static String K = "Kelvin";
    final static String EMPTYUNIT = "*SELECT UNIT*";
    static String inU = EMPTYUNIT;
    static String outU = EMPTYUNIT;
    int dec=0;
    List<TempData> opList = new ArrayList<TempData>();
    static List<TempData> ipList = new ArrayList<TempData>();
    String[] units = {EMPTYUNIT, C, F, K};
    static String ERR_UNIT = "SELECT UNIT";
    static String ERR_NUM = "ENTER PROPER INPUT";
    JComboBox inputUnit, outputUnit;
    GridBagConstraints c = new GridBagConstraints();

    static String path = "/Users/twardrop/Desktop/Temperature Data.txt";
    
    //GUI layout
    public TempConversion()
    {
        super(new GridBagLayout());
        inputUnit = new JComboBox(units);
        c.gridx=0;
        c.gridy=0;
        add(inputUnit, c);
        System.out.println("step");

        outputUnit = new JComboBox(units);
        c.gridx=1;
        c.gridy=0;
        System.out.println("step");
        add(outputUnit, c);
        
        label = new JLabel("Insert Numer");
        c.gridx=0;
        c.gridy=2;
        add(label, c);
        
        label1 = new JLabel("RESULT");
        c.gridx=1;
        c.gridy=2;
        add(label1, c);
        
        val = new JTextField("");
        c.gridx=0;
        c.gridy=1;
        c.fill = GridBagConstraints.BOTH;
        add(val, c);
        
        result = new JTextField("");
        c.gridx=1;
        c.gridy=1;
        c.weightx = 0.5; // MODIFICATION
        c.fill = GridBagConstraints.BOTH;
        add(result, c);
        
        ERR = new JLabel("");
        c.gridx = 0;
        c.gridy = 4;
        add(ERR, c);
        
        decimalInfo = new JLabel("Decimal digits:" + dec);
        c.gridx = 1;
        c.gridy = 4;
        add(decimalInfo, c);
        
        button = new JButton("Convert");
        c.gridx=0;
        c.gridy=3;
        add(button, c);
        
        decimal = new JSlider(0, 9, 0);
        c.gridx=1;
        c.gridy=3;
        add(decimal, c);
      
        System.out.println("step");
        
        inputUnit.setSelectedIndex(0);
        outputUnit.setSelectedIndex(0);
        inputUnit.addActionListener(this);
        outputUnit.addActionListener(new outputListener());
        button.addActionListener(new buttonListener());
        decimal.addChangeListener(new silderListener());
        System.out.println("step done");
    }

    /*
        THE FOLLOWING METHODS are used for specific temperature conversion operations
    */
    public static double c2f(double t)
    {
        return (t*9/5)+32;
    }
    
    public static double f2c(double t)
    {
        return (t-32)*5/9;
    }
    
    public static double c2k(double t)
    {
        return t+273.15;
    }
    
    public static double k2c(double t)
    {
        return t-273.15;
    }
    
    public static double k2f(double t)
    {
        return (t*9.0/5.0)-459.67;
    }
    
    public static double f2k(double t)
    {
        return (t+459.67)*5.0/9.0;
    }
    
    //initialize UI
    public static void initUI()
    {
        JFrame ui = new JFrame();
        
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ui.setName("Temperature conversion software");
        ui.setSize(400,400);
        
        JComponent box = new TempConversion();
        box.setOpaque(true);
        ui.setContentPane(box);
        ui.setVisible(true);
        try {
            readFile();
        } catch (IOException ex) {
            System.out.println("***CANNOT OPEN PREVIOUS DATA");
        }
    }
    
    /*
        This method identifies the units selected and choose, accordingly, the
        calculation methods
    */
    public static double calculate(double t)
    {
       if(inU!=null&&outU!=null&&!inU.equals(outU))
       {
       if(inU.equals(C))
       {

           if(outU.equals(F))
           {
               System.out.println(c2f(t));
               return c2f(t);
           }
           else if(outU.equals(K))
           {
               System.out.println(c2k(t));
               return c2k(t);
           }
       }
       else if(inU.equals(F))
       {
           if(outU.equals(C))
           {
               System.out.println(f2c(t));
               return f2c(t);
           }
           else if(outU.equals(K))
           {
               System.out.println(f2k(t));
               return f2k(t);
           }
       }
       else if(inU.equals(K))
       {
           if(outU.equals(C))
           {
               System.out.println(k2c(t));
               return k2c(t);
           }
           else if(outU.equals(F))
           {
               System.out.println(k2f(t));
               return k2f(t);
           }
       }
       }
       
       else if (inU!=null&&outU!=null) return t;
       return 0;
}
    
    public static String decimal(int d, double num)
    {

        double fm = 1.0 + ((double)d/10);
        String format = String.format("%" + fm +"f", num);  
        return format;
        /*
        RIGHT NOW: the method cannot handle more than 9 digits of decimal digits,
        The method attatched below could handle such instance but has its own defects.
        
        String format = "#.";
        String decimal = "#";
        for (int i=0; i<d; i++)
        {
            format = format + decimal;
        }
        System.out.println("decimal points: "+d+ ", initional number: " +num);
        
        DecimalFormat deci = new DecimalFormat(format);
        
        return deci.format(num);
        */
        
    }   
    
    public void writeFile()
    {
        File file = new File(path);
        PrintWriter output = null;
        try
        {
            output = new PrintWriter(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("***ERROR IN FILE OUTPUT***");
        }
        for (int i=0; i<opList.size()-1; i++)
        {
            output.write(opList.get(i).toString() + "\n");
        }
        output.close();
    }
    
    public static void readFile()
            throws IOException
    {
        File file = new File(path);
        BufferedReader input = new BufferedReader(new FileReader(file));
        
        while(input.readLine()!=null)
        {
            String temp = input.readLine();
            System.out.println(temp);
            ipList.add(new TempData(temp));
        }
        
        for(int i=0; i<ipList.size(); i++)
        {
            System.out.println("The previous input is: " + ipList.get(i).getInNum());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        initUI();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JComboBox temp = (JComboBox)e.getSource();
        inU = String.valueOf(temp.getSelectedItem());
        System.out.println("The input unit has been changed to "+ inU);
    }
    
    
    /*
    THE FOLLOWING CLASSES are listener classes that responds to different Swing
    components.
    */
    class outputListener implements ActionListener 
    {
    String outputFormat;
    
    public outputListener() {  
    }
    
    public void actionPerformed(ActionEvent e) {
        JComboBox temp = (JComboBox)e.getSource();
        outU = String.valueOf(temp.getSelectedItem());
        System.out.println("The output unit has been changed to "+ outU);
    }
    }
    
    class buttonListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e) {
            
        try{
            if(!outU.equals(EMPTYUNIT)&&!inU.equals(EMPTYUNIT))
            {
            double inputNum = Double.parseDouble(val.getText());
            double res = calculate(inputNum);
            String resDeci = decimal(dec, res);
            ERR.setText("");
            result.setText(resDeci);
            TempData data = new TempData(inU, outU, inputNum, parseDouble(resDeci), dec);
            opList.add(data);
            }
            else
            {
                ERR.setText(ERR_UNIT);
            }
        } 
        catch (NumberFormatException n)
        {ERR.setText(ERR_NUM);}
        
        writeFile();
        repaint();
        System.out.println("calculating...");
        }
    }
    
    class silderListener implements ChangeListener
    {

        @Override
        public void stateChanged(ChangeEvent e) {
            dec = decimal.getValue();
            decimalInfo.setText(("Decimal digits:" + dec));
            System.out.println(dec);
            repaint();
        }


    }
}


