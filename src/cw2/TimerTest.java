package cw2;


//此Test类为Timer的测试类且不需要改动
public class TimerTest {
	public static void main(String args[]){
		Timer.showTime();
		System.out.println(Timer.nowTime);
		String TimeTable="01 03 2 04 09:30 10:00 10:30 11:00 11:10 11:40 12:10 12:40 12:50 13:20 13:50 14:20 14:30 15:00 15:30 16:00 ";
		String StartAccident="11:40";
		int delayTime=63;
		TimeTable=Timer.AllDelay(TimeTable, StartAccident, delayTime);
		System.out.println(TimeTable);
	}
}
