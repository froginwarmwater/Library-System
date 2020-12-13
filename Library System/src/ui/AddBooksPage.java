package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

import dao.BookDao;
import entity.Reader;
import service.ReaderFunc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddBooksPage{
	AddBooksPage() {
		new Addbooks();
	}
	public static void main(String[] args) {
		new AddBooksPage();
	}
}

class Addbooks extends JFrame implements ActionListener{
	/*****************************声明部分*************************************/

    String sdept = "计算机类";
    JButton btn_sure = new JButton("确定");
    JButton btn_cancel= new JButton("取消");
    String book_number = "1"; 
	Font f1=new Font("",Font.BOLD,20);

	
	String[] listData_sdept = new String[]{"计算机类", "人文类","社科类","文学类","金融类"};
	final JComboBox<String> comboBox_sdept = new JComboBox<String>(listData_sdept);

	String[] listData_number = new String[]{"1", "2","3","4","5","6","7","8","9","10"};
	final JComboBox<String> comboBox_number = new JComboBox<String>(listData_number);
	
	
	JLabel []lbl = new JLabel[9];

	
	
	JTextField txt_name = new JTextField(15);
	JTextField txt_ISBN = new JTextField(15);
	JTextField txt_price = new JTextField(15);
	JTextField txt_publisher = new JTextField(15);
	JTextField txt_author = new JTextField(15);
	JTextField txt_number = new JTextField(15);
	JTextField txt_introduction = new JTextField(15);
	
	DateChooserJButton datebutton = new DateChooserJButton();

	
	
	/*************************界面部分****************************************/
	public Addbooks() {
		/***/
		setSize(700,720);
		JPanel j=(JPanel)this.getContentPane();
		j.setOpaque(false);
		setLayout(new FlowLayout());
		/***/
		lbl[0] = new JLabel("书名：");
		lbl[1] = new JLabel("ISBN：");
		lbl[2] = new JLabel("类别：");
		lbl[3] = new JLabel("出版日期：");
		lbl[4] = new JLabel("价格：");
		lbl[5] = new JLabel("出版社：");
		lbl[6] = new JLabel("作者：");
		lbl[7] = new JLabel("插入数量：");
		
		for(int i = 0;i<=7;i++) {
			lbl[i].setFont(f1);
		}
		
		txt_name.setFont(f1);
		txt_ISBN.setFont(f1);
		txt_ISBN.setText("ISBN");
		txt_price.setFont(f1);
		txt_publisher.setFont(f1);
		txt_number.setFont(f1);
		txt_author.setFont(f1);
		comboBox_sdept.setFont(f1);
		datebutton.setFont(f1);
		txt_introduction.setFont(f1);
		
		setVisible(true);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		
		
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(lbl[0]);
		add(new JLabel("                  "));
		add(txt_name);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(lbl[1]);
		add(new JLabel("                  "));
		add(txt_ISBN);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		
		add(lbl[2]);
		add(new JLabel("                  "));
		add(comboBox_sdept);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("       "));
		add(lbl[3]);
		add(new JLabel("                  "));
		add(datebutton);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(lbl[4]);
		add(new JLabel("                  "));
		add(txt_price);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("             "));
		add(lbl[5]);
		add(new JLabel("                  "));
		add(txt_publisher);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(lbl[6]);
		add(new JLabel("                  "));
		add(txt_author);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                                        "));
		
		JLabel l = new JLabel("简介：");
		l.setFont(f1);
		add(l);
		add(new JLabel("                  "));
		add(txt_introduction);
		
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(lbl[7]);
		add(new JLabel("             "));
		//add(txt_number);
		comboBox_number.setFont(f1);
		add(comboBox_number);
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("        "));
		add(new JLabel("                                                                                                              "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		add(new JLabel("                  "));
		btn_sure.setFont(f1);
		btn_cancel.setFont(f1);
		add(btn_sure);
		add(btn_cancel);
		
		
		/***********************************/

		

		
		
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
		
		
		
		comboBox_number.addItemListener(new ItemListener() {
		      @Override
		      public void itemStateChanged(ItemEvent e) {
		        // 只处理选中的状态
		        if (e.getStateChange() == ItemEvent.SELECTED) {
		        	book_number = (String) comboBox_number.getSelectedItem();
		          System.out.println(book_number);
		        }
		      }
		    });
		
		
		
		btn_sure.addActionListener(this);
		btn_cancel.addActionListener(this);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setVisible(true);
		
		
		

		validate();
	}
	
	public void actionPerformed(ActionEvent e) {/*******************actionPerformed**********************************/
		if(e.getSource() == btn_sure) {
			String name = txt_name.getText().trim();
			String ISBN = txt_ISBN.getText().trim();
			String price = txt_price.getText().trim();
			String publisher = txt_publisher.getText().trim();
			String author = txt_author.getText().trim();
			String introduction =txt_introduction.getText().trim();
			int number = Integer.parseInt(book_number);
			Date p_date = datebutton.getWhatINeed();
			if(introduction.equals("") ||name.equals("") || ISBN.equals("") || price.equals("") || publisher.equals("") || author.equals("")) {
				JOptionPane.showMessageDialog(null,"信息不能位空！");
			}else if(!CheckISBN.checkISBN(ISBN).equals("success")){
				JOptionPane.showMessageDialog(null,CheckISBN.checkISBN(ISBN));
			}else {
				for(int i = 1;i <= number ; i ++) {
				BookDao.addBook(name,ISBN,price,publisher,author,p_date,sdept,introduction);
				}
				JOptionPane.showMessageDialog(null,"添加成功！");
			}
			

		}
		if(e.getSource() == btn_cancel) {
		
			dispose();
			new AdminBooksPage();
			

		}
		
		}
}