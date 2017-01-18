package hey;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Flameaux extends Thread
{
	StringBuffer s = new StringBuffer("FLAMES");
public void run()
{
 while(s.length()>1)
 {
	
	int n=Flame.num;
	while(n>s.length())
	n-=s.length();
	s.deleteCharAt(n-1);
	Flame.l.setText(s.toString());
	try
	{
		Thread.sleep(2000);
	}
	catch(InterruptedException ie)
	{
		System.out.println(""+ie);
	}	
	
 }
 char c = Flame.l.getText().charAt(0);
 switch(c)
 {
 case 'F':Flame.l.setText("Friend");
 			break;
 case 'L':Flame.l.setText("Love!");
	break;
 case 'A':Flame.l.setText("Affection");
	break;
 case 'M':Flame.l.setText("Marriage");
	break;
 case 'E':Flame.l.setText("Enemy");
	break;
 case 'S':Flame.l.setText("Sibling");
	break;
 }
 }
 
}

class Flame extends JFrame implements ActionListener
{
	JTextField n1,n2;
	JButton ok;
	static JLabel l;
	static int num;
	Flame()
	{
		n1=new JTextField();
		n2=new JTextField();
		ok=new JButton("ok");
		l=new JLabel("FLAMES");
		n1.setBounds(20,20,100,40);
		n2.setBounds(140,20,100,40);
		ok.setBounds(100,70,60,40);
		l.setBounds(100,120,100,50);
		ok.addActionListener(this);
		add(l);
		add(n1);
		add(n2);
		add(ok);
		setLayout(null);
		setSize(300,300);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		StringBuffer s1= new StringBuffer(n1.getText()); 
		StringBuffer s2= new StringBuffer(n2.getText());		
	
		int l1,l2;
		l1=s1.length();
		l2=s2.length();
		int i,j,flag=0;
		
		for(i=0;i<l1;i++)
		{
			char c=s1.charAt(i); 
			for(j=0,flag=0;j<l2;j++)
			{
				if(c==s2.charAt(j))
					flag++;
			}
			if(flag>0)
			{
				for(j=0;j<l1;j++)
					if(s1.charAt(j)==c)
					{
						s1.deleteCharAt(j);
						l1--;
						j--;
						//break;
					}
				for(j=0;j<l2;j++)
					if(s2.charAt(j)==c)
					{
						s2.deleteCharAt(j);
						l2--;
						j--;
						//break;
					}
						i--;
						//n1.setText(s1.toString());
						//n2.setText(s2.toString());
			}
		}
		num=s1.length()+s2.length();
		Flameaux obj = new Flameaux();
		obj.start();
		
	}
		
}

public class Flames {

	public static void main(String[] args) {
	new Flame();

	}

}
