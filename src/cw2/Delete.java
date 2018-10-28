package cw2;


import java.io.*;
import java.util.*;

public class Delete {
	String InputofRecycle ="";
	int Rwhich;
	int Dwhich;
	public Delete(int Rwhich,int Dwhich){
		this.Rwhich=Rwhich;
		this.Dwhich=Dwhich;
		String Rwhichstr=""+Rwhich;
		if(Rwhich<10){
			Rwhichstr="0"+Rwhich;
		}
		InputofRecycle=Rwhichstr+" "+Dwhich;
		//this.DeleteStation(Dwhich);
		//this.Recycle(Dwhich);
	}
	
	public void DeleteTimeTable(){
		String thisLine;
		ArrayList<String> Input=new ArrayList<String>();
		FileReader fr;
		FileWriter fw;
		String BackNofStation = null;
		
		try {
			fr = new FileReader("TimeTable.txt");
			BufferedReader bfr=new BufferedReader(fr);
			while((thisLine=bfr.readLine())!=null){
				int NofR=Integer.parseInt(thisLine.substring(0, 2));
				if(NofR==this.Rwhich){    //此处1表示几号线，2的话就是2号线
					int NofStation=Integer.parseInt(thisLine.substring(8, 10));
					for(int j=0;j<(thisLine.charAt(6)-48);j++){
						thisLine=thisLine.replace(thisLine.substring(11+6*Dwhich+j*2*NofStation*6-j*2*6,16+1+6*Dwhich+j*2*NofStation*6-j*2*6),"");
						thisLine=thisLine.replace(thisLine.substring(11+6*Dwhich+((NofStation-Dwhich-1)*2+1)*6+j*2*NofStation*6-(j*2*6+6),16+1+6*Dwhich+((NofStation-Dwhich-1)*2+1)*6+j*2*NofStation*6-(j*2*6+6)),"");
					}
					NofStation=NofStation-1;
					BackNofStation=""+NofStation;
					if(NofStation<10){
					BackNofStation="0"+NofStation;
					}
				thisLine=thisLine.replace(thisLine.substring(7, 11), " "+BackNofStation+" ");
				}
				Input.add(thisLine);
			}
			bfr.close();
		} catch (IOException e) {}
		
		try {
			fw = new FileWriter("TimeTable.txt");
			for(int i=0;i<Input.size();i++){
				fw.write(Input.get(i));
				fw.write("\r\n");
				fw.flush();
			}
			
		} catch (IOException e) {}	
		
		//this.DeleteStation(Dwhich);
	}
	
	public void DeleteStation(){
		ArrayList<String> Input=new ArrayList<String>();
		String thisLine;
		FileReader fr;
		FileWriter fw;
		
		try{
			fr = new FileReader("RouteStation.txt");
			BufferedReader bfr=new BufferedReader(fr);
			int count=0;
			while((thisLine=bfr.readLine())!=null){
				if(Integer.parseInt((thisLine.substring(0, 2)))==this.Rwhich){//判断是否是该线路上的第Dwhich站
					if(count==Dwhich){
						thisLine=thisLine+"(Won't Stop)";
					}
					count++;//如果是count加一，count加到Dwhich说明扫到了想删除的站
				}
				Input.add(thisLine);
			}
			bfr.close();
			//Input.set(Dwhich, Input.get(Dwhich)+"(Won't Stop)");
		}
		catch(IOException e){}
		try {
			fw = new FileWriter("RouteStation.txt");
			for(int i=0;i<Input.size();i++){
				fw.write(Input.get(i));
				fw.write("\r\n");
				fw.flush();
			}
			
		} catch (IOException e) {}
		this.Recycle();
	}
	
	public void Recycle(){
		FileWriter fw;
		System.out.println(this.InputofRecycle);
		try {
			fw = new FileWriter("Recycle.txt",true);
			File f = new File("Recycle.txt");
			if(f.length()!=0){
				fw.write("\r\n");
			}
			fw.write(this.InputofRecycle);
			fw.flush();
		} catch (IOException e) {}
	}
}
