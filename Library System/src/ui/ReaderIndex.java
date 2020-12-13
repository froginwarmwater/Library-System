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


public class ReaderIndex extends JFrame implements ActionListener{
	Reader reader;
	ImageIcon background;
	JPanel myPanel;
	JLabel label;
	JPanel p1,p;
	Font f1,f2,f3,f4;

    JLabel head1;
	
    JLabel label1=new JLabel("            ");
    JLabel label2=new JLabel("            ");
    JLabel label3=new JLabel("            ");
    JLabel label4=new JLabel("            ");
    JLabel label5=new JLabel("            ");
    JLabel label6=new JLabel("            ");
	JButton a=new JButton("我 要 借 书");
	JButton b=new JButton("我 要 还 书");
	JButton c=new JButton("相 关 信 息");
	JButton d=new JButton("退 出 登 录");

	public ReaderIndex(Reader reader){
		super("上海电力图书管理系统");
		this.reader = reader;
		head1 = new JLabel("----------"+reader.getName()+",您好-----------");
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
	    d.setFont(f1);

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
	    p.add(a);
	    p.add(label2);
	    p.add(b);
	    p.add(label3);
	    p.add(c);
	    p.add(label6);
	    p.add(d);

	    this.add(p1);
	    this.add(p);
	    
	    a.addActionListener(this);
	    b.addActionListener(this);
	    c.addActionListener(this);
	    d.addActionListener(this);
	    
		setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	    
	}
	
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		if(source==a) { 
			dispose();
			new Lend_Page(reader);
		}
		if(source==b) {   
			dispose();
			new ReturnBook_Page(reader);
		}
		if(source==c) {   
			dispose();
			new InfoChoose(reader);
		}
		if(source==d) {   
			dispose();
			new Welcome();
		}
	}
	
	
	public static void main(String[] args){
		new ReaderIndex(ReaderDao.getDemoReader());
	}
}
