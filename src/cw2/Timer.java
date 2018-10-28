package cw2;


import java.text.*;
import java.util.*;

public class Timer {
	
	static String nowTime;
	static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
	
	public static void showTime(){
		Date now; 
		now	= new Date();
		nowTime =dateFormat.format(now);
	}
	
	public static String delay(String BeforeTime,int delayTime){
		String RtTime;
		Date rtn = null;
		GregorianCalendar cal=new GregorianCalendar();
		try {
			rtn=dateFormat.parse(BeforeTime);
		} catch (ParseException e) {}
		cal.setTime(rtn);
		cal.add(12, delayTime);
        rtn=cal.getTime();
        RtTime =dateFormat.format(rtn);
		return RtTime;
	}
	
	public static String AllDelay(String TimeTable, String StartAccident, int delayTime){
		int i;
		double start,temp;
		String tempstring;
		String BackTable=TimeTable.substring(0, 11);
		start=Integer.parseInt(StartAccident.substring(0, 2))+Integer.parseInt(StartAccident.substring(3))/60.0;
		try{
			for(i=0;i<2000;i++){  //�˴�i<2000��Ϊ�˱�֤TimeTable��ÿһ��ʱ�䶼�ܱ�����ʱ������Խ������쳣����i��Ϊ9999������ѭ��
				tempstring=TimeTable.substring(11+i*6, 16+i*6);
				temp=Integer.parseInt(tempstring.substring(0, 2))+Integer.parseInt(tempstring.substring(3))/60.0;
				if(start<=temp){
				  tempstring=Timer.delay(tempstring, delayTime);
				}
				BackTable=BackTable+tempstring+" ";
			}
		}
			catch(Exception e){
				i=9999;
		}
		return BackTable;
	}
	
}
