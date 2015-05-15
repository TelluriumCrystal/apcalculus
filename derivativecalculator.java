// Billy Hansen

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class derivativecalculator
{
 
 private ArrayList<Float> numarr;
 private float[] positionarr;
 private float[] velocityarr;
 private float[] accelerationarr;
  
 public ArrayList<Float> processData() throws FileNotFoundException
 {
  numarr = new ArrayList<Float>();
  BufferedReader in = new BufferedReader(new FileReader("Launch 3.csv"));
  String line = "";
  boolean check = true;
  while (check == true)
  {
    try
    {
     line = in.readLine();
     // System.out.println(line);
    }
    catch (IOException exception)
    {
     System.out.println(exception); 
     break;
    }
    if (line == null)
    {
     check = false; 
    }
    else
    {
     if (line.length() > 0 && Character.isDigit(line.charAt(0)))
     {
      String temp = "";
      for (int i = 0; i < line.length(); i++)
      {
       if (Character.isDigit(line.charAt(i))) 
         temp += line.charAt(i);
       else if (line.charAt(i) == '.')
       {
        temp += line.charAt(i); 
       }
       else if (line.charAt(i) == '-')
       {
        temp += line.charAt(i); 
       }
       else if (line.charAt(i) == ',')
       {
        numarr.add(Float.parseFloat(temp));
        System.out.println(temp);
        temp = "";
       }
      }
     }
    }
  }
  return numarr;
 }
  
 public float[] getPositionData()
 {
  positionarr = new float[100];
  return positionarr;
 }
 
 public float[] getVelocityData()
 {
  velocityarr = new float[100];
  return velocityarr;
 }
 
 public float[] getAccelerationData()
 {
  accelerationarr = new float[100];
  return accelerationarr;
 }
 
 public double calculateDerivative()
 {
  return 0.0; 
 }
 
 
 public static void main(String[] args) throws FileNotFoundException
 {
  derivativecalculator owen = new derivativecalculator();
  owen.processData();
 }
}