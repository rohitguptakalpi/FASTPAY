package com.FastPay;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
    public static void appendStrToFile(String fileName,String str)
    {
    	
    	try {
    		BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
    		out.write(str);
    		out.close();
    		}
    	catch (IOException e) {
    		System.out.println("exception occoured" + e);
    		}
    	}
	  public void writer( String s) 
	  {
	         String fileName = "C:\\Users\\Ashutosh Gupta\\eclipse-workspace\\FastPay\\src\\main\\java\\com\\FastPay\\log.txt";
	         String str = s;
	         appendStrToFile(fileName, str);
	         }
	  
}
	  
