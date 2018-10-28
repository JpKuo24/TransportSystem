package cw2;


public class DriveTest {
	public static void main(String args[]){
		
		Driver olddriver=new Driver("0004","5846");
		olddriver.LookWork();
		
		String TrainTable="01 01 2 04 08:30 09:00 09:30 10:00 10:10 10:40 11:10 11:40 11:50 12:20 12:50 13:20 13:30 14:00 14:30 15:00 08:15 08:45 09:15 09:45 09:55 10:25 10:55 11:25 ";
		System.out.println(TrainTable.substring(11,11+6*8));
		System.out.println(TrainTable.substring(11+48*1,11+6*8+48*1));
		System.out.println(TrainTable.substring(11+48*2,11+6*8+48*2));

	}
}
