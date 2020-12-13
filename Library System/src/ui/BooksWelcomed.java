package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import dao.BookDao;
import dao.ReaderDao;
import entity.Book;
import entity.Reader;
import service.BookFunc;
import service.ReaderFunc;
import ui.ReaderIndex;

public class BooksWelcomed {
	BooksWelcomed(Reader reader,boolean isAdmin){
		show(reader,isAdmin);
		
	}
	JTable jt = new JTable();
	int selectedRow = -1;
	int b_id;
	Reader reader;
	public void show(Reader reader,boolean isAdmin) {
		this.reader= reader;
		
		JFrame frame = new JFrame("受欢迎的书籍界面");
		frame.setLayout(new BorderLayout());
		frame.setSize(1000,720);
		Font f2=new Font("",Font.BOLD,15);
		
		/*background*/
		ImageIcon background=new ImageIcon("img/background.png");
		
		JLabel back=new JLabel(background);
		back.setBounds(0, 0,frame.getWidth(),frame.getHeight());
		JPanel imagePane=(JPanel)frame.getContentPane();
		imagePane.setOpaque(false);//不透明的5
		frame.getLayeredPane().add(back,new Integer(Integer.MIN_VALUE));
		frame.setLocationRelativeTo(null);
		
		
		


		Font f1=new Font("",Font.BOLD,20);
	
		
		
		/*south*/
		JPanel jp_south=new JPanel();
		jp_south.setLayout(new GridLayout(1,4));
		
		
		JButton btn[] = new JButton[4];
		btn[0] = new JButton("最受欢迎的十本书");
		btn[1] = new JButton("借阅排名");
		btn[2] = new JButton("最不受欢迎的十本书");
		btn[3] = new JButton("返回");


		for(int i = 0 ;i < 4; i ++) {
			btn[i].setFont(f1);
			jp_south.add(btn[i]);
		}
		
		
		frame.add(jp_south, BorderLayout.SOUTH);
		jp_south.setPreferredSize(new Dimension(1000,40));
		
		
		/*center*/
		
		JTable table=new JTable();
		final JScrollPane jsp =new JScrollPane(table);
		jsp.setFont(f1);
		frame.add(jsp, BorderLayout.CENTER);	
		
		/*addListener*/
		/***************************************************/
		
		 
		
		
		
		btn[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Book> books = BookDao.rank("topten");
				setTable(books,jsp,frame);
			}
				
		});
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Book> books =BookDao.rank("rank");
				setTable(books,jsp,frame);
			}
		});
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Book> books =BookDao.rank("lastten");
				setTable(books,jsp,frame);
			}	
		});
		
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if(!isAdmin)
				new InfoChoose(reader);
				else new AdminPage();
			}
		});
		/***************************************************/
		/*setVisible*/
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    btn[0].doClick();
	}
	
	void setTable(List<Book> books,JScrollPane jsp,JFrame frame) {
		Object rowdata[][] = new Object[books.size()][5];
		Object cnames[]= {"书名","ISBN","类别","简介","借阅次数"};
		
		Iterator<Book> it=books.iterator();
		int k=0;
		
		while(it.hasNext()) {
			Book book=it.next();
			rowdata[k][0]=book.getName();
			rowdata[k][1]=book.getISBN();
			rowdata[k][2]=book.getCatalog();
			rowdata[k][3]=book.getContent();
			rowdata[k][4]=book.getLendTimes();
			
			k++;
		}

		jt=new JTable(rowdata,cnames);
		jt.setFont(new Font("",Font.BOLD,15));
		jsp.setViewportView(jt);
		jt.setBounds(0,0,1150,540);

		
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
		renderer.setOpaque(false);//render单元格的属性  

		jt.setOpaque(false);

		jt.setRowHeight(40);

		jsp.setOpaque(false);  
		jsp.getViewport().setOpaque(false); 
		frame.add(jsp);
	}

	public static void main(String[] args) {
		new BooksWelcomed(ReaderDao.getDemoReader(),false);
	}
}