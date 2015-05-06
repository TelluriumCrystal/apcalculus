# AP Calculus CSV Reader
A Java program that reads .csv files with time and position data and then calculates velocity and acceleration. It graphs the calculated data and lists important data points.

The program will consist of four main parts: the .csv reader, the derivative calculator, the data display, and the GUI front-end. All four parts will be housed in their own class files, and can be worked on independantly. The following are the public methods and returns that each class should have. Because they all need to store information to work, they will not be static classes and will need to be initalized when the program loads.

1. CSV processor
- public static readcsv(String filename, String filepath); #should process the .csv file at the specified location and store the information in a float arrray in the object
- public float[] getarray(); #should return an array derived from the .csv file that has time in the first value and position in the second

2. Derivative calculator
- public static processdata(long[] data); #should process the data into a velocity array and an acceleration array and store it in the object
- public float[] positiondata(); #should return the float array that is currently stored in the class
- public float[] velocitydata(); #should return a float array of values with the first being time and the second being velocity; these should come from the last processed array
- public float[] accelerationdata(); #should return a float array of values with the first being time and the second being acceleration; these should come from the last processed array

3. Data display
- public static importdata(float[] position, float[] velocity, float[] acceleration); #imports all data and stores it in the object
- public JPanel getgraph(); #returns a graph with position, velocity, and acceleration, contained in a JPanel
NOTE: Any additional calculations, such as finding a local maximum, will be done in this class.

4. GUI front-end
Is unique as it is what is calling all the other classes. It will not have any public classes.
