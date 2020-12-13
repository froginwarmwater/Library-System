package ui;


import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.ReaderDao;
import entity.Reader;


public class InfoChoose extends JFrame implements ActionListener{
	ImageIcon background;
	JPanel myPanel;
	JLabel label;
	JPanel p1,p;
	Font f1,f2,f3,f4;
	Reader reader;

    JLabel head1=new JLabel("上海电力图书管理系统");
	
    JLabel label1=new JLabel("            ");
    JLabel label2=new JLabel("            ");
    JLabel label3=new JLabel("            ");
    JLabel label4=new JLabel("            ");
    JLabel label5=new JLabel("            ");
    JLabel label6=new JLabel("            ");
	JButton a=new JButton("书  籍  概  况");
	JButton b=new JButton("信  息  更  改");
	JButton c=new JButton("   返   回   ");


	
	public InfoChoose(Reader reader){
		super("上海电力图书管理系统");
		this.reader=reader;
		setLayout(new FlowLayout());
		p=new JPanel();
		p1=new JPanel();
		f1=new Font("",Font.BOLD,20);
	    f2=new Font("",Font.ITALIC,30);
	    f3=new Font("",Font.BOLD,18);
	    f4=new Font("",Font.PLAIN,40);
	    a.setFont(f1);
	    b.setFont(f1);
	    c.setFont(f1);

	    head1.setFont(f4);
	    head1.setForeground(Color.WHITE);
	    
		// 设置背景
		ImageIcon image=new ImageIcon("img/background_welcome.png");
	    JLabel background=new JLabel(image);
	    background.setSize(image.getIconWidth(),image.getIconHeight());
	    this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		JPanel j=(JPanel)this.getContentPane();
		j.setOpaque(false);
		p1.setOpaque(false);
		p.setOpaque(false);

	    //p1.setBackground(new Color(255,240,240));
	    p.setLayout(new GridLayout(9,1));
	    p1.setLayout(new GridLayout(2,1));
	    
	    p1.add(label4);
	    p1.add(head1);
	    p.add(label5);
	    p.add(label1);
	    p.add(new JLabel("            "));
	    p.add(a);
	    p.add(new JLabel("            "));
	    p.add(b);
	    p.add(new JLabel("            "));
	    p.add(c);


	    this.add(p1);
	    this.add(p);
	    
	    a.addActionListener(this);
	    b.addActionListener(this);
	    c.addActionListener(this);
	    
		setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	    
	}
	
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		if(source==a) { 
			new BooksWelcomed(reader,false);
			dispose();
		}
		if(source==b) {   
			new Refactor(reader);
			dispose();
		}
		if(source==c) {   
			new ReaderIndex(reader);
			dispose();
		}

	}
	
	
	public static void main(String[] args){
		new InfoChoose(ReaderDao.getDemoReader());
	}
}
