package hey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Postfix extends JFrame implements ActionListener 
{
	JButton [] b = new JButton[20];
	JTextField t ;
	JButton c;
	String s;
	int top=0,stop=0,rtop=0;
	String[]exp = new String[20];
	String[]res = new String[20];
	Postfix()
	{
		t = new JTextField();
		c = new JButton("c");
		t.setBounds(0,0,200,70);
		c.setBounds(200,0,100,70);
		c.addActionListener(this);
		add(t);
		add(c);
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,3));
		int i;
		for(i=0;i<10;i++)
		{
			b[i] = new JButton(""+i);
			b[i].addActionListener(this);
			p.add(b[i]);
		}
		b[10] = new JButton("(");
		b[11] = new JButton(")");
		b[12] = new JButton("+");
		b[13] = new JButton("-");
		b[14] = new JButton("*");
		b[15] = new JButton("/");
		b[16] = new JButton("^");
		b[17] = new JButton("=");
		for(i=10;i<18;i++)
		{
			p.add(b[i]);
			b[i].addActionListener(this);
		}
		p.setBounds(0, 70, 300, 300);
		add(p);
		setSize(320,420);
		setLayout(null);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String c = ae.getActionCommand();
		if(c=="c")
		{
			t.setText("");
			top=0; stop=0;rtop=0;
		}
		if(c!="="&&c!="c")
		{
			t.setText(t.getText()+c);
		}
		if(c=="=")
		{
			s=t.getText();
			conversion(s);
		}
	}
	
	void conversion(String s)
	{	
	 char[]st = new char[20];
	 String aux="";
	 int i,flag;
	 
	for(i=0;i<s.length();i++)
	{   
		flag=0;
		aux="";
	while(i<s.length()&&s.charAt(i)>='0'&&s.charAt(i)<='9')	
	{
		aux=aux+s.charAt(i);
		i++;
		flag++;
	}
	if(flag>0)
	{
		i--;
	exp[++top]=aux;
	}
		if(s.charAt(i)=='(')
			st[++stop]=s.charAt(i);
		
		if(s.charAt(i)==')')
		{
			while(st[stop]!='(')
			{
				exp[++top]=Character.toString(st[stop--]);
			}
			stop--;
		}
		
		if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'||s.charAt(i)=='^')
		{
			if(st[stop]=='('||st[stop]=='\0')
				{
				st[++stop]=s.charAt(i);
				//System.out.println("test ok"+i);
				}
			else if(! priority(st[stop],s.charAt(i)))
			{
				while(! priority(st[stop],s.charAt(i)))
				{
					exp[++top]=Character.toString(st[stop--]);
				}
				st[++stop]=s.charAt(i);
			}
			else
				st[++stop]=s.charAt(i);
		}
		
	}
	
	while(stop>0)
		exp[++top]=Character.toString(st[stop--]);
	for(i=1;i<=top;i++)
	System.out.println(exp[i]);
	resolve();
	}
	
	void resolve()
	{
		int i,fl;
		for(i=1;i<=top;i++)
		{
			fl=0;
			switch(exp[i].charAt(0))
			{
			case '+':res[rtop-1]=""+(Integer.parseInt(res[rtop-1])+Integer.parseInt(res[rtop]));
				rtop--;fl++;
				break;
			case '-':res[rtop-1]=""+(Integer.parseInt(res[rtop-1])-Integer.parseInt(res[rtop]));
			rtop--;fl++;
			break;
			case '*':res[rtop-1]=""+(Integer.parseInt(res[rtop-1])*Integer.parseInt(res[rtop]));
			rtop--;fl++;
			break;
			case '/':res[rtop-1]=""+(Integer.parseInt(res[rtop-1])/Integer.parseInt(res[rtop]));
			rtop--;fl++;
			break;
			case '^':res[rtop-1]=""+(Math.pow(Integer.parseInt(res[rtop-1]),Integer.parseInt(res[rtop])));
			rtop--;fl++;
			break;
			}
			if(fl==0)
			{
				res[++rtop]=exp[i];
			}
		}
		t.setText(res[1]);
	}
	
boolean priority(char a, char b)
{
char[] c = new char[3];
int[] p = new int[3];
c[0]=a;	
c[1]=b;
int i;
for(i=0;i<2;i++)	
{
	switch(c[i])
	{
	case '(':p[i]=-1;
	break;
	case ')':p[i]=-1;
	break;
	case '\0':p[i]=-1;
	break;
	case '+':p[i]=0;
		break;
	case '-':p[i]=0;
		break;
	case '*':p[i]=1;
		break;	
	case '/':p[i]=1;
		break;
	case '^':p[i]=2;
		break;	
	}
}

if(p[1]>p[0])
return true;
else
	return false;
}

}


public class Cal_post {

	public static void main(String[] args) {
    new Postfix();

	}

}
