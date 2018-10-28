package Display;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DLogin implements ActionListener{
	 Menu Dm;
	 JButton loginbutton;
	 JButton quitbutton;
	 JFrame dframe;
	 JTextField txt;
	 JPasswordField pw;
	 JLabel dlabel; 
	 int P=0;

	 public DLogin(Menu m){
			this.Dm =m;
		}
	 public void Dgo(){
		 dframe = new JFrame("Driver Log In");
	     dframe.setBounds(500, 300, 300, 220);
	     dframe.setLayout(null);
	     dframe.setResizable(false);	   
	     
	     dlabel = new JLabel("Please input your ID and Password:");
	     dframe.add(dlabel);
	     dlabel.setBounds(48,0,200,55);
	     
	     JLabel dIDlabel = new JLabel("Driver ID",JLabel.CENTER);
	     dframe.add(dIDlabel);
	     dIDlabel.setBounds(18, 50, 50, 30);
	     JLabel dPAlabel = new JLabel("Password",JLabel.CENTER);
	     dframe.add(dPAlabel);
	     dPAlabel.setBounds(6, 90, 80, 30);
	     
	     txt = new JTextField(10);  
 	     dframe.add(txt);
         txt.setBounds(82, 50, 150, 30);
	     pw =new JPasswordField(10);
	     dframe.add(pw);
	     pw.setBounds(82, 90, 150, 30);
	     
	     loginbutton = new JButton("log in");
	     loginbutton.setBounds(82, 137, 65, 30);
	     dframe.add(loginbutton);
	     loginbutton.addActionListener(this);
	     
	     quitbutton = new JButton("quit");
	     quitbutton.setBounds(165, 137, 65, 30);
	     dframe.add(quitbutton);
	     quitbutton.addActionListener(this);  	
	     
	     dframe.setVisible(true);
	     
	 }
//	 public static void main(String args[]){
//		 DLogin dlogin = new DLogin();
//		 dlogin.Dgo();
//	 }
//	 
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginbutton){	
			String password = new String(pw.getPassword());
			getDriverInformaiton(txt.getText(), password);
			if(P==1){
				Dm.c =true;
			dframe.dispose();
			System.out.println("OK");
			} else {
			txt.setText("");
			pw.setText("");
			dlabel.setText("      Something wrong!    Try again:");
		    dlabel.setForeground(Color.BLUE);
			}
		}	
		if(e.getSource()== quitbutton){
			dframe.dispose();		
		}
	}
	public void getDriverInformaiton(String ID, String PA){
		 try{
	        	File fileRouteStation = new File("Driver.txt");
	        	FileReader fileReader = new FileReader(fileRouteStation);
	        	@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(fileReader);	        	
	        	String line  = null;

	        	while((line = reader.readLine()) != null){
	        		StringTokenizer myTokenizer= new StringTokenizer(line);
	        		if (ID.equals(myTokenizer.nextToken()) && PA.equals(myTokenizer.nextToken()))	{
	        			P=1;
	        			break;
	        		}
	        	}
		 } catch(Exception ex){
	         	ex.printStackTrace();
	     }		
	}	
}
