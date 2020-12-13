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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.AdministratorDao;
import entity.Reader;
import service.AdminFunc;
import service.ReaderFunc;


public class User_Login extends JFrame implements ActionListener{
	ImageIcon background;
	JPanel myPanel;
	JLabel label;
	JPanel p1,p;
	Font f1,f2,f3,f4;
	JButton btn_sure;
	JButton btn_cancel;
	JTextField txt_username;
	JPasswordField txt_pwd;
	
    JLabel head1=new JLabel("--------用户登录--------");
	
    JLabel label1=new JLabel("            ");
    JLabel label2=new JLabel("            ");
    JLabel label3=new JLabel("            ");
    JLabel label4=new JLabel("            ");
    JLabel label5=new JLabel("            ");
    JLabel label6=new JLabel("            ");

	
	public User_Login(){
		super("上海电力图书管理系统");
		
		
		
	    
		setLayout(new FlowLayout());
		p=new JPanel();
		p1=new JPanel();
		f1=new Font("",Font.BOLD,20);
	    f2=new Font("",Font.ITALIC,30);
	    f3=new Font("",Font.BOLD,18);
	    f4=new Font("",Font.PLAIN,40);


	    head1.setFont(f4);
	    head1.setForeground(Color.WHITE);
	    
		// 设置背景
		ImageIcon image=new ImageIcon("img/background_login.png");
	    JLabel background=new JLabel(image);
	    background.setSize(image.getIconWidth(),image.getIconHeight());
	    this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		JPanel j=(JPanel)this.getContentPane();
		j.setOpaque(false);
		p1.setOpaque(false);
		p.setOpaque(false);

	    //p1.setBackground(new Color(255,240,240));
	    p.setLayout(new GridLayout(15,2));
	    p1.setLayout(new GridLayout(2,1));
	    
	    
	    btn_sure = new JButton("确定");
	    btn_cancel= new JButton("取消");
	    JLabel lbl_username = new JLabel("账号"); 
	    JLabel lbl_pwd = new JLabel("密码"); 
	    txt_username = new JTextField(17);
	    txt_pwd = new JPasswordField(17);
	    
	    
	    
	    btn_sure.setFont(f1);
	    btn_cancel.setFont(f1);
	    lbl_username.setFont(f1);
	    lbl_pwd.setFont(f1);
	    
	    
	    p1.add(label4);
	    p1.add(head1);
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(lbl_username);
	    p.add(txt_username);
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(lbl_pwd);
	    p.add(txt_pwd);
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(new JLabel("         "));
	    p.add(btn_sure);
	    p.add(btn_cancel);


	    
	    


	    this.add(p1);
	    this.add(p);
	    
	    btn_sure.addActionListener(this);
	    btn_cancel.addActionListener(this);

	    
		setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
	    
	    
	}
	
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		if(source==btn_sure) { 
			String uname = txt_username.getText();
			String pwd = txt_pwd.getText();
			if(ReaderFunc.login(uname, pwd)) {
				dispose();
				JOptionPane.showMessageDialog(null,"登陆成功！");
				Reader reader = ReaderFunc.loadReader(uname);
				new ReaderIndex(reader);
			}else {
				JOptionPane.showMessageDialog(null,"账户名或密码错误！");
			}
			
		}else if(source==btn_cancel) {
			dispose();
			new Welcome();
		}

	}
	
	
	public static void main(String[] args){
		new User_Login();
	}
}
