package cw2;


import java.io.*;

public class TimeTable {
	
	static String thisLine;
	static FileReader fr ;
	static String ItsTime;
	static int NumberRoute;
	static int NumberTrip;
	public static String SearchTime(int NoRoute,int NoTrip){
		try{
			fr =new FileReader("TimeTable.txt");
			BufferedReader bfr = new BufferedReader(fr);
			while((thisLine=bfr.readLine())!=null){
				
				NumberRoute=Integer.parseInt(thisLine.substring(0,2));
				NumberTrip=Integer.parseInt(thisLine.substring(3, 5));
				if((NoRoute==NumberRoute)&&(NoTrip==NumberTrip)){
					ItsTime=thisLine;
				}
			}
			fr.close();
		}
		catch(IOException ioe){}
		return ItsTime;
	}
}