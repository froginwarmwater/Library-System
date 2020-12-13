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

public class Lend_Page {
	Lend_Page(Reader reader){
		show(reader);
	}
	JTable jt = new JTable();
	int selectedRow = -1;
	int b_id;
	Reader reader;
	public void show(Reader reader) {
		this.reader= reader;
		JFrame frame = new JFrame("借书界面");
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
		
		
		
		/*north*/
		JLabel lbl[] = new JLabel[5];
		lbl[0]=new JLabel("书名");
		lbl[1]=new JLabel("ISBN");
		lbl[2]=new JLabel("作者");
		lbl[3]=new JLabel("类别");
		lbl[4]=new JLabel("出版社");
		
		
		JPanel jp_north=new JPanel();
		jp_north.setLayout(new GridLayout(3,4));

		Font f1=new Font("",Font.BOLD,20);
		JTextField fld[]=new JTextField[5];
		for (int i = 0; i <= 4; i ++) {
			lbl[i].setFont(f1);;
			fld[i]=new JTextField();
			fld[i].setFont(f1);
		}
		
		
		
		for(int i = 0 ; i <= 4 ; i ++) {
			jp_north.add(lbl[i]);
			jp_north.add(fld[i]);
		}
		
		
		frame.add(jp_north,BorderLayout.NORTH);
		jp_north.setPreferredSize(new Dimension(1000,90));
		
		
		
		/*south*/
		JPanel jp_south=new JPanel();
		jp_south.setLayout(new GridLayout(1,4));
		
		
		JButton btn[] = new JButton[4];
		btn[0] = new JButton("查询");
		btn[1] = new JButton("借书");
		btn[2] = new JButton("查询所有");
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
				selectedRow = -1;
				String name = fld[0].getText().trim();
				String ISBN=fld[1].getText().trim();
				String author=fld[2].getText().trim(),
						catalog=fld[3].getText().trim(),
						publisher=fld[4].getText().trim();
				
				
				if(name.equals("")) name = null;
				if(ISBN.equals("")) ISBN = null;
				if(author.equals("")) author = null;
				if(catalog.equals("")) catalog = null;
				if(publisher.equals("")) publisher = null;
				if(name == null && ISBN == null && author == null && catalog == null && publisher == null) {
					btn[2].doClick();
					return ;
				}
				List<Book> books = BookFunc.findBooks(name,ISBN,author,catalog,publisher);
				
				
				Object rowdata[][] = new Object[books.size()][8];
				Object cnames[]= {"编号","书名","ISBN","类别","作者","简介","出版社","状态"};
				
				Iterator<Book> it=books.iterator();
				int k=0;
				
				while(it.hasNext()) {
					Book book=it.next();
					rowdata[k][0]=book.getB_id();
					rowdata[k][1]=book.getName();
					rowdata[k][2]=book.getISBN();
					rowdata[k][3]=book.getCatalog();
					rowdata[k][4]=book.getAuthor();
					rowdata[k][5]=book.getContent();
					rowdata[k][6]=book.getPublisher();
					if(book.getState() == 1) {
						rowdata[k][7]="可借";
					}else {
						rowdata[k][7]="不可借";
					}
					k++;
				}

				jt=new JTable(rowdata,cnames);
				jt.setFont(f2);
				jsp.setViewportView(jt);
				jt.setBounds(0,0,1150,540);
				jt.addMouseListener(new MouseAdapter(){
		             public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
		                //得到选中的行列的索引值
		            	 selectedRow= jt.getSelectedRow();
		            	 b_id = (int)rowdata[selectedRow][0];
		            	 //System.out.println(b_id);
		             }
		         }); 
				
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
				renderer.setOpaque(false);//render单元格的属性  

				jt.setOpaque(false);

				jt.setRowHeight(40);

				jsp.setOpaque(false);  
				jsp.getViewport().setOpaque(false); 
				frame.add(jsp);
				
			
			}
				
		});
		btn[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedRow = -1;
				List<Book> books = BookFunc.findAllBooks();
				
				Object rowdata[][] = new Object[books.size()][8];
				Object cnames[]= {"编号","书名","ISBN","类别","作者","简介","出版社","状态"};
				
				Iterator<Book> it=books.iterator();
				int k=0;
				
				while(it.hasNext()) {
					Book book=it.next();
					rowdata[k][0]=book.getB_id();
					rowdata[k][1]=book.getName();
					rowdata[k][2]=book.getISBN();
					rowdata[k][3]=book.getCatalog();
					rowdata[k][4]=book.getAuthor();
					rowdata[k][5]=book.getContent();
					rowdata[k][6]=book.getPublisher();
					if(book.getState() == 1) {
						rowdata[k][7]="可借";
					}else {
						rowdata[k][7]="不可借";
					}
					k++;
				}

				jt=new JTable(rowdata,cnames);
				
				jt.addMouseListener(new MouseAdapter(){
		             public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
		                //得到选中的行列的索引值
		            	 selectedRow= jt.getSelectedRow();
		            	 b_id =(int) rowdata[selectedRow][0];
		            	 //System.out.println(b_id);
		             }
		         }); 
				jt.setFont(f2);
				jsp.setViewportView(jt);
				jt.setBounds(0,0,1150,540);

				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
				renderer.setOpaque(false);//render单元格的属性  
				//遍历表格中所有列，将其渲染器设置为renderer  

				jt.setOpaque(false);

				jt.setRowHeight(40);

				jsp.setOpaque(false);  
				jsp.getViewport().setOpaque(false); 
				frame.add(jsp);
			}
		});
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null,"未选中项目！");
					return;
				}
				String available = lendAvailable(reader.getR_id(),b_id);
				if(available.equals("success")) {
					BookDao.lendBook(reader.getR_id(),b_id);
					JOptionPane.showMessageDialog(null,"借书成功！");
					btn[2].doClick();
				}else {
					JOptionPane.showMessageDialog(null,available);
				}

			}	
		});
		
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ReaderIndex(reader);
			}
		});
		/***************************************************/
		/*setVisible*/
		btn[2].doClick();
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	
	String lendAvailable(String reader_id,int book_id) {
		if(!ReaderFunc.canLend(reader_id)) {
			return "借书已达上限!";
		}else if(BookFunc.canLend(book_id)) {
			return "该书已被借走!";
		}
		return "success";
	}
	public static void main(String[] args) {
		new Lend_Page(ReaderDao.getDemoReader());
	}
}