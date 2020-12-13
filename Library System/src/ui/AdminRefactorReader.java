package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import dao.ReaderDao;
import entity.Reader;
import service.ReaderFunc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminRefactorReader{
	AdminRefactorReader(Reader reader) {
		new AdminchangeInfo(reader);
	}
	public static void main(String[] args) {
		new AdminRefactorReader(ReaderDao.getDemoReader());
	}
}

class AdminchangeInfo extends JFrame implements ActionListener{
	/*****************************声明部分*************************************/
	ImageIcon image=new ImageIcon("img/tag.jpg");
    JLabel background=new JLabel(image);
	JTabbedPane p;
	Icon icon[];
	String tagName[] = {
			"账号信息",
			"基本信息",
			"身份信息"
	};
	Date birth = Calendar.getInstance().getTime();
	DateChooserJButton btn_birth = new DateChooserJButton();
	Font f1=new Font("",Font.BOLD,20);
	Reader reader = new Reader();
	String[] listData_sex = new String[]{"男", "女"};
	final JComboBox<String> comboBox_sex = new JComboBox<String>(listData_sex);
	
	String[] listData_sdept = new String[]{"计算机学院", "能机学院","环化学院","电气工程学院","电信学院","经管学院","外国语学院"};
	final JComboBox<String> comboBox_sdept = new JComboBox<String>(listData_sdept);
	String sex = "男";
	String sdept = "计算机学院";
	
	
	JLabel lbl_1 = new JLabel("账    号:");
	JLabel lbl_2 = new JLabel("密    码:");
	JLabel lbl_3 = new JLabel("重复密码:");
	
	JLabel lbl_4 = new JLabel("部    门:");
	JLabel lbl_5 = new JLabel("职    位:");
	
	JLabel lbl_7 = new JLabel("姓    名:");
	JLabel lbl_8 = new JLabel("性    别:");
	JLabel lbl_9 = new JLabel("身份证号:");
	JLabel lbl_10 = new JLabel("出生日期:");
	JLabel lbl_11 = new JLabel("电话号码:");
	JLabel lbl_12 = new JLabel("家庭住址:");
	
	JTextField txt_username = new JTextField(15);
	JPasswordField txt_pwd = new JPasswordField(15);
	JPasswordField txt_repeat = new JPasswordField(15);
	
	JTextField txt_pname = new JTextField(15);
	JTextField txt_tele = new JTextField(15);
	JTextField txt_addr = new JTextField(15);
	
	JTextField txt_position = new JTextField(15);
	
	
	
	JButton btn_sure01 = new JButton("下一步");
	JButton btn_cancel01 = new JButton("取 消");

	JButton btn_sure02 = new JButton("下一步");
	JButton btn_cancel02 = new JButton("上一步");
	
	JButton btn_sure03 = new JButton("完成更改");
	JButton btn_cancel03 = new JButton("上一步");
	
	
	/*************************界面部分****************************************/
	public AdminchangeInfo(Reader reader) {
		
		this.reader = reader;
		/***/
		background.setSize(image.getIconWidth(),image.getIconHeight());
	    this.getLayeredPane().add(background,new Integer(Integer.MIN_VALUE));
		JPanel j=(JPanel)this.getContentPane();
		j.setOpaque(false);
		
		/***/
		txt_username.setEditable(false);
		
		txt_pname.setFont(f1);

		txt_tele.setFont(f1);
		txt_addr.setFont(f1);
		txt_position.setFont(f1);
		lbl_4.setFont(f1);
		lbl_5.setFont(f1);
		comboBox_sex.setFont(f1);
		comboBox_sdept.setFont(f1);
		lbl_7.setFont(f1);
		lbl_8.setFont(f1);
		lbl_9.setFont(f1);
		lbl_10.setFont(f1);
		lbl_11.setFont(f1);
		lbl_12.setFont(f1);
		
		
		
		
		txt_username.setText(reader.getR_id());
		txt_pwd.setText(reader.getPwd());
		txt_repeat.setText(reader.getPwd());
		
		txt_pname.setText(reader.getName());

		txt_tele.setText(reader.getTele());
		txt_addr.setText(reader.getAddr());
		
		txt_position.setText(reader.getPosition());
		

		
		
		setVisible(true);
		icon = new Icon[tagName.length];
		
		p=new JTabbedPane(JTabbedPane.RIGHT);
		p.setOpaque(false);
		for (int i = 0; i < icon.length; i++) {
			icon[i]=new ImageIcon(tagName[i]);
			
		}
		
		JPanel tagPanel[] = new JPanel[3];
		tagPanel[0] = new JPanel();
		tagPanel[1] = new JPanel();
		tagPanel[2] = new JPanel();
		tagPanel[0].setLayout(new BorderLayout());
		tagPanel[1].setLayout(new BorderLayout());
		tagPanel[2].setLayout(new BorderLayout());
		
		tagPanel[0].setOpaque(false);
		tagPanel[1].setOpaque(false);
		tagPanel[2].setOpaque(false);
		
		
		tagPanel[0].add(firstPage());
		tagPanel[1].add(secondPage());
		tagPanel[2].add(thirdPage());
		
		for (int i = 0; i < icon.length; i++) {
			icon[i]=new ImageIcon(tagName[i]);
			p.add(tagName[i],tagPanel[i]);
		}
		
		
		
		
		
		
		/***********************************/
		
		btn_sure01.addActionListener(this);
		btn_cancel01.addActionListener(this);
		btn_sure02.addActionListener(this);
		btn_cancel02.addActionListener(this);
		btn_sure03.addActionListener(this);
		btn_cancel03.addActionListener(this);
		
		comboBox_sex.addItemListener(new ItemListener() {
		      @Override
		      public void itemStateChanged(ItemEvent e) {
		        // 只处理选中的状态
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	sex = (String) comboBox_sex.getSelectedItem();
		          //System.out.println(sex);
		        }
		      }
		    });
		
		
		comboBox_sdept.addItemListener(new ItemListener() {
		      @Override
		      public void itemStateChanged(ItemEvent e) {
		        // 只处理选中的状态
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	sdept = (String) comboBox_sdept.getSelectedItem();
		          //System.out.println(sdept);
		        }
		      }
		    });
		
		
		
		
		
		
		
		
		
		/******************************************/
		setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
		
		
		
		p.validate();
		add(p,BorderLayout.CENTER);
		validate();
	}
	
	public void actionPerformed(ActionEvent e) {/*******************actionPerformed**********************************/
		if(e.getSource() == btn_sure01) {
			String pwd = txt_pwd.getText();
			String pwd_repeat = txt_repeat.getText();
			
			if(pwd.equals("") || pwd_repeat.equals("")) {
				JOptionPane.showMessageDialog(null,"信息不能位空！");
			}
			else if(!pwd.equals(pwd_repeat)) {
				JOptionPane.showMessageDialog(null,"两次输入的密码不相同，请重新输入！");
			}else {
				reader.setPwd(pwd_repeat);
				p.setSelectedIndex(1);
			}
		}
		if(e.getSource() == btn_sure02) {
			String name = txt_pname.getText().trim();
			String tele = txt_tele.getText().trim();
			String addr = txt_addr.getText().trim();
			if(name.equals("") || tele.equals("") || addr.equals("")) {
				JOptionPane.showMessageDialog(null,"信息不能位空！");
			}
			else if(!isMobileNO(tele)) {
				JOptionPane.showMessageDialog(null,"电话号码格式不正确，请重新输入！");
			}else {
				reader.setName(name);
				reader.setSex(sex);
				reader.setTele(tele);
				reader.setAddr(addr);
				p.setSelectedIndex(2);
			}
		}
		
		if(e.getSource() == btn_sure03) {
			btn_sure01.doClick();
			btn_sure02.doClick();
			String posi = txt_position.getText().trim();
			if(posi.equals("")) {
				JOptionPane.showMessageDialog(null,"信息不能位空！");
			}else {
				reader.setPosition(posi);
				reader.setDep(sdept);
				
				//ReaderFunc.readerRegister(reader);
				ReaderDao.updateRegister(reader);
				JOptionPane.showMessageDialog(null,"更改成功！");
				dispose();
				
				new CheckReader();
			}
			

		}
		
		
		
		
		if(e.getSource() == btn_cancel01) {
			dispose();
			new CheckReader();
		}
		if(e.getSource() == btn_cancel02) {
			p.setSelectedIndex(0);
		}
		if(e.getSource() == btn_cancel03) {
			p.setSelectedIndex(1);
		}
		
		
	}
	/*************actionPerformed******************/
	
	
	/********************正则*******************/

	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
 
			// 13********* ,15********,18*********
			Pattern p = Pattern.compile("^[1](([3][0-9])|([4][5,7,9])|([5][0-9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$");
 
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
 
		} catch (Exception e) {
			flag = false;
		}
 
		return flag;
	}
	
	
	/*********************正则********************/
	
	

	JPanel firstPage() {//***********************第一页**************************
		JPanel firstpage = new JPanel(new BorderLayout());
		JPanel jp_up = new JPanel();
		


		
		
		lbl_1.setFont(f1);
		txt_username.setFont(f1);
		lbl_2.setFont(f1);
		txt_pwd.setFont(f1);
		lbl_3.setFont(f1);
		txt_repeat.setFont(f1);

		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("         "));
		jp_up.add(new JLabel("                  "));
		jp_up.add(lbl_1);
		jp_up.add(txt_username);
		jp_up.add(new JLabel("               "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("            "));
		jp_up.add(new JLabel("            "));
		jp_up.add(lbl_2);
		jp_up.add(txt_pwd);
		jp_up.add(new JLabel("       "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(lbl_3);
		jp_up.add(txt_repeat);
		
		
		
		JPanel jp_down = new JPanel(new GridLayout(2,5));
		jp_down.add(new JLabel("    "));
		jp_down.add(btn_sure01);
		jp_down.add(new JLabel("    "));
		jp_down.add(btn_cancel01);
		
		
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		
		jp_down.setOpaque(false);
		jp_up.setOpaque(false);
		firstpage.add(jp_up,BorderLayout.CENTER);
		firstpage.add(jp_down,BorderLayout.SOUTH);
		
		firstpage.setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		

		return firstpage;
	}
	
	JPanel secondPage() {//*************************************第二页*******************************
		JPanel secondPage = new JPanel(new BorderLayout());
		JPanel jp_up = new JPanel();
		
		lbl_1.setFont(f1);
		txt_username.setFont(f1);
		lbl_2.setFont(f1);
		txt_pwd.setFont(f1);
		lbl_3.setFont(f1);
		txt_repeat.setFont(f1);

		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("        "));
		jp_up.add(new JLabel("            "));
		jp_up.add(lbl_7);
		jp_up.add(txt_pname);
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                    "));
		jp_up.add(lbl_8);
		if(reader.getSex().equals("男")) {
			comboBox_sex.setSelectedItem("男");	
		}else {
			comboBox_sex.setSelectedItem("女");		
		}
		jp_up.add(comboBox_sex);
		jp_up.add(new JLabel("                                "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                "));
		jp_up.add(new JLabel("                                "));
		jp_up.add(new JLabel("           "));
		jp_up.add(new JLabel("           "));
		jp_up.add(lbl_11);
		jp_up.add(txt_tele);
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("       "));
		jp_up.add(new JLabel("       "));
		jp_up.add(new JLabel("       "));
		jp_up.add(new JLabel("       "));
		jp_up.add(new JLabel("             "));
		jp_up.add(lbl_12);
		jp_up.add(txt_addr);
		
		
		JPanel jp_down = new JPanel(new GridLayout(2,5));
		jp_down.add(new JLabel("    "));
		jp_down.add(btn_sure02);
		jp_down.add(new JLabel("    "));
		jp_down.add(btn_cancel02);
		
		
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		
		jp_down.setOpaque(false);
		jp_up.setOpaque(false);
		secondPage.add(jp_up,BorderLayout.CENTER);
		secondPage.add(jp_down,BorderLayout.SOUTH);
		
		secondPage.setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		


		return secondPage;
	}
	
	JPanel thirdPage() {//****************************************第三页**************************************
		JPanel thirdpage = new JPanel(new BorderLayout());
		JPanel jp_up = new JPanel();
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("        "));
		jp_up.add(new JLabel("            "));
		jp_up.add(lbl_4);
		
		comboBox_sdept.setSelectedItem(reader.getDep());	
		jp_up.add(comboBox_sdept);
		
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("                                 "));
		jp_up.add(new JLabel("           "));
		jp_up.add(new JLabel("      "));
		jp_up.add(lbl_5);
		jp_up.add(txt_position);
		

		
		
		JPanel jp_down = new JPanel(new GridLayout(2,5));
		jp_down.add(new JLabel("    "));
		jp_down.add(btn_sure03);
		jp_down.add(new JLabel("    "));
		jp_down.add(btn_cancel03);
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		jp_down.add(new JLabel("    "));
		
		jp_down.setOpaque(false);
		jp_up.setOpaque(false);
		thirdpage.add(jp_up,BorderLayout.CENTER);
		thirdpage.add(jp_down,BorderLayout.SOUTH);
		
		thirdpage.setBounds(400,170,image.getIconWidth(),image.getIconHeight());
		
		return thirdpage;
	}

}