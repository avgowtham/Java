package hey;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Test2 extends Thread
{
	public void run()
	{
		do
		{
    	   if(Tic.turn%2==0)
    	   {
    		   try
    			{Thread.sleep(1000);}
    			catch(InterruptedException ie)
    			{	System.out.println(ie);}
    		   
    		   int k=Tic.comp();
    		   Tic.b[k].setText("X");
    	    	Tic.arr[k]=2;
    	    	Tic.turn++;
    	    	Tic.wincheck();}
    	    
    	    if(Tic.turn%2!=0)
    	    {	
    	    	try
    			{Thread.sleep(1000);}
    			catch(InterruptedException ie)
    			{	System.out.println(ie);}
    	    	
    	    int k=Tic.comp();
    	    Tic.b[k].setText("O");
    	    Tic.arr[k]=5;
    	    Tic.turn++;
    	    Tic.wincheck();
    	    }
		}while(Tic.turn!=0); 
	}
}

 class Tic extends JFrame implements ActionListener
{
	
	static JButton [] b = new JButton[10];
	static JButton ok;
	 static int turn=0, arr[]=new int[10];
	static JRadioButton p1,p2,p3;
	Tic()
	{
	ok = new JButton ("okay");
	p1=new JRadioButton("player 1");
	p2=new JRadioButton("player 2");
	p3=new JRadioButton("cpu vs cpu");
	ButtonGroup bg = new ButtonGroup();
	bg.add(p1);bg.add(p2);bg.add(p3);
	p1.setBounds(10,10,70,40);
	p2.setBounds(80,10,70,40);
	p3.setBounds(10,40,120,50);
	ok.setBounds(160,20,70,25);
	ok.setActionCommand(""+-1);
	ok.addActionListener(this);
	add(p1);
	add(p2);
	add(p3);
	add(ok);
	JPanel p = new JPanel();
	p.setBounds(0,100,300,300);
		int i;
		p.setLayout(new GridLayout(3,0)); 
		for(i=0;i<9;i++)
		{
			arr[i]=0;
			b[i]=new JButton("");
			b[i].setActionCommand(""+i);
			p.add(b[i]);
			b[i].addActionListener(this);
		}
		add(p);
		setLayout(null);
		setSize(320,450);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
     int but=Integer.parseInt(e.getActionCommand());
     
     if(p1.isSelected()&&turn==0)
			turn=0;
		else
			if(p2.isSelected()&&turn==0)
			turn=1;
     if(p3.isSelected())
     {
    
    	        Test2 obj = new Test2();
    	        obj.start();
    
     }
     
     
      if((but>=0 && b[but].getText()=="")||turn==1)
    {
   if(turn%2==0)
   {
	   b[but].setText("X");
    	arr[but]=2;
    	turn++;
    	 wincheck();}
    
    if(turn%2!=0)
    {	
    int k=comp();
    b[k].setText("O");
	arr[k]=5;
    turn++;
    wincheck();
    }
    }
   
    }

	static int comp()
	{
		
		
		if(arr[4]==0)
		return 4;
		
			int i;
		if((i=poswin())>=0)
		return i;
	
		for(i=0;i<=4;i++)
			if(arr[i*2]==0&&i!=2)
			return i*2;
				
		for(i=0;i<4;i++)
			if(arr[i*2+1]==0)
				return i*2+1;
		
		return 0;
	}
	
	static int poswin()
	{
		int i,j,sum,sum1,sum2=0,sum3=0,flag=0;
	    for(i=0;i<3&&flag==0;i++)
	    {
	    	sum2+=arr[i*4];
	    	sum3+=arr[i*2+2];
	    	for(j=0,sum=0,sum1=0;j<3;j++)
	    	{
	    		sum+=arr[i*3+j];
	    		sum1+=arr[i+j*3];
	    	}
	    	if(sum==10||sum==4)
			{
			for(j=0;j<3;j++)
				if(arr[i*3+j]==0)
	    		return i*3+j;
			}
	    	if(sum1==10||sum1==4)
			{
	    	for(j=0;j<3;j++)
			 if(arr[i+j*3]==0)
		     return i+j*3;	
			}
	    	if(sum2==10||sum2==4)
			{
	    		for(j=0;j<3;j++)
	   			 if(arr[j*4]==0)
	   		     return j*4;	
			}
	    	if(sum3==10||sum3==4)
			{
	    		for(j=0;j<3;j++)
		   			 if(arr[j*2+2]==0)
		   		     return j*2+2;	
			}
	    }
	    return -1;
	  
	}
		
	static void wincheck()
	{
		int i,j,sum,sum1,sum2=0,sum3=0,flag=0;
	    for(i=0;i<3&&flag==0;i++)
	    {
	    	sum2+=arr[i*4];
	    	sum3+=arr[i*2+2];
	    	for(j=0,sum=0,sum1=0;j<3;j++)
	    	{
	    		sum+=arr[i*3+j];
	    		sum1+=arr[i+j*3];
	    	}
	    	if(sum==6||sum1==6||sum2==6||sum3==6)
	    	{flag=1;
	    	//System.out.println(sum+" "+sum1+" "+" "+sum2+" "+sum3+" "+i+" "+j);
	    		break;}
	    	if(sum==15||sum1==15||sum2==15||sum3==15)
	    	{flag=2;
	    		break;}
	    }
	    if(flag!=0)
	    {
	    if(flag==1)
	    	JOptionPane.showMessageDialog(null, "Player 1 Won!!");
	    if(flag==2)
	    	JOptionPane.showMessageDialog(null, "Player 2 Won!!");
	    for(i=0;i<9;i++)
	    {    b[i].setText("");
	    	arr[i]=0;}
	    turn=0;
	    }
	    for(i=0;i<9&&b[i].getText()!="";i++);
	    if(i==9)
	    {
	    	JOptionPane.showMessageDialog(null, "Match Tied!!");
	    for(i=0;i<9;i++)
	    {    b[i].setText("");
	    	arr[i]=0;}
	    turn=0;
	    }
	
	}
	
	
	
}
public class Tttfinale {
	public static void main(String[] args) {
		new Tic();
	}
}
