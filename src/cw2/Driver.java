package cw2;

import java.io.*;


public class Driver {
	
    public String DriveID;
	private String DriveKey;
    String GoTime=null;
    String BackTime=null;
    public char StateDrive='0';
    public int TofD=0;
    public int DofR=0;//司机在哪条线上工作
    int NoTrip;
    String myTimeTable;
    
	public Driver(){}
	 public Driver(String DriveID){
		    
	    	this.DriveID=DriveID;
	    }
	public Driver(String DriveID,String DriveKey){
		this.DriveID=DriveID;
		this.DriveKey=DriveKey;
		this.Search();
	}
	
	public void GetWork(int TofD,int DofR){
		
		this.StateDrive='1';
		this.TofD=TofD;
		this.DofR=DofR;
		//根据Route号，找Route对象的起止时间
		
	}
	
	public void getNoTrip(int NoTrip){
		this.NoTrip=NoTrip;
	}
	public void Search(){
		String thisLine;
		try{
			FileReader fileR=new FileReader("Driver.txt");
			BufferedReader bfileR = new BufferedReader(fileR);
			
			while((thisLine=bfileR.readLine()) != null){
				
				if(thisLine.substring(0, 4).equals(this.DriveID)){
					if(thisLine.substring(5).equals(this.DriveKey)){
						System.out.println("Welcome! Driver");
					}
					else{
						System.out.println("Key Error!");
					}
					break;
				}
			}
			bfileR.close();
			
		}
		catch(IOException ioe){
			System.out.println("ERROR! "+ioe);
		}
	}
	//Search方法结束
	
	
	public void LookWork(){
		System.out.println("Your train");
	}
	
	public void SearchTable(){
		this.myTimeTable=TimeTable.SearchTime(DofR, NoTrip);
		System.out.println(this.myTimeTable);
	}
	
	public String toString(){
		return "Driver"+this.DriveID+" drives Train"+this.TofD+" State is "+this.StateDrive+" works at Route"+DofR+" I work at Trip "+this.NoTrip;
	}
   
}
