package Display;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MLogin  implements ActionListener{
	 Menu m1;
	 JButton loginbutton;
	 JButton quitbutton;
	 JFrame mframe;
	 JTextField txt;
	 JLabel mlabel; 
	
	public MLogin(Menu m){
		this.m1 =m;
	}
	
	public void Mgo(){
		 mframe = new JFrame("Manager Log In");
	     mframe.setBounds(500, 300, 300, 180);
	     mframe.setLayout(null);
	     mframe.setResizable(false);	   
	       
	     mlabel = new JLabel("Please input your name:");
	     mframe.add(mlabel);
	     mlabel.setBounds(60,0,200,70);
	     
	     txt = new JTextField(10);  
 	     mframe.add(txt);
         txt.setBounds(70,50, 150, 30);
         
        loginbutton = new JButton("log in");
     	loginbutton.setBounds(65, 100, 70, 30);
     	mframe.add(loginbutton);
     	loginbutton.addActionListener(this);
     	
     	quitbutton = new JButton("quit");
      	quitbutton.setBounds(155, 100, 70, 30);
      	mframe.add(quitbutton);
    	quitbutton.addActionListener(this);  	
        mframe.setVisible(true);
	}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==loginbutton){
				if(txt.getText().equals("admin")){
						m1.b =true;
						mframe.dispose();	
				} else {
					txt.setText("");
					mlabel.setText("Wrong name, please input again:");
				    mlabel.setForeground(Color.BLUE);
				}							
			} else if(e.getSource()== quitbutton){
				mframe.dispose();		
			}
		}
}
