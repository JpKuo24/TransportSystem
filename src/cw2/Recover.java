package cw2;


import java.io.*;
import java.util.*;

public class Recover {
	int Rwhich, Dwhich;
	public Recover(int Rwhich,int Dwhich){
		this.Rwhich=Rwhich;
		this.Dwhich=Dwhich;
	}
	public void RecoverStation(){
		ArrayList<String> Input=new ArrayList<String>();
		FileReader fr;
		FileWriter fw;
		String thisLine;
		//�����ļ�
		try{
			fr = new FileReader("RouteStation.txt");
			BufferedReader bfr=new BufferedReader(fr);
			int count=0;
			while((thisLine=bfr.readLine())!=null){
				if(Integer.parseInt(thisLine.substring(0, 2))==this.Rwhich){
					if(count==this.Dwhich){
						thisLine=thisLine.replace("(Won't Stop)", "");
					}
					count++;//�����count��һ��count�ӵ�Dwhich˵��ɨ������ָ���վ
				}
				Input.add(thisLine);
			}
			
			
			bfr.close();
		}
		catch(IOException e){}
		
		//д���ļ�
		try {
			fw = new FileWriter("RouteStation.txt");
			System.out.println(Input);
			for(int i=0;i<Input.size();i++){
				fw.write(Input.get(i));
				fw.write("\r\n");
				fw.flush();
			}
			
		} catch (IOException e) {}
	}
}
