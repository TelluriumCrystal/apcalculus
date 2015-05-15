// Billy Hansen and Hashem Anabtawi

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class derivativecalculator
{
 
 private ArrayList<Float> numarr;
 private ArrayList<Float> positionarr;
 private ArrayList<Float> velocityarr;
 private ArrayList<Float> accelerationarr;
  
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
        // System.out.println(temp);
        temp = "";
       }
      }
     }
    }
  }
  return numarr;
 }
  
 public ArrayList<Float> getPositionData(ArrayList<Float> fopo)
 {
  positionarr = new ArrayList<Float>(); 
  for (int i = 0; i < fopo.size(); i = i + 4)
  {
   positionarr.add(fopo.get(i));
   positionarr.add(fopo.get(i+1));
  }
  System.out.println(positionarr);
  System.out.println();
  return positionarr;
 }
 
 public ArrayList<Float> calculateDerivative(ArrayList<Float> fopo)
 { 
  velocityarr = new ArrayList<Float>();
  for (int i = 0; i < fopo.size(); i = i + 4)
  {
   float x = 0;
   x = ((fopo.get(i+3) - fopo.get(i+1)) / (fopo.get(i+2) - fopo.get(i)));
   velocityarr.add(x);
  }
  System.out.println(velocityarr);
  System.out.println();
  return velocityarr; 
 }
 
 public ArrayList<Float> getVelocityData(ArrayList<Float> fovo)
 {
  return fovo;
 }
 
 public ArrayList<Float> getAccelerationData(ArrayList<Float> fopo, ArrayList<Float> fovo)
 {
  accelerationarr = new ArrayList<Float>();
  int j = 0;
  for (int i = 0; i < fopo.size(); i = i + 2)
  {
    float y = 0;
    y = ((fovo.get(j+1) - fovo.get(j)) / (fopo.get(i+2) - fopo.get(i)));
    accelerationarr.add(y);
    j++;
    if (j > 1474)
      break;
  }
  System.out.println(accelerationarr);
  System.out.println();
  return accelerationarr;
 }
 
 public static void main(String[] args) throws FileNotFoundException
 {
  derivativecalculator owen = new derivativecalculator();
  owen.processData();
  owen.getPositionData(owen.processData());
  owen.calculateDerivative(owen.getPositionData(owen.processData()));
  owen.getAccelerationData(owen.getPositionData(owen.processData()), owen.calculateDerivative(owen.getPositionData(owen.processData())));
 }
}