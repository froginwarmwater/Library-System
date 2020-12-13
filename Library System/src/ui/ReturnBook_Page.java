package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

public class ReturnBook_Page {
	ReturnBook_Page(Reader reader){
		show(reader);
		
	}
	JTable jt = new JTable();
	int selectedRow = -1;
	int b_id;
	Reader reader;
	public void show(Reader reader) {
		this.reader= reader;
		
		JFrame frame = new JFrame("还书界面");
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
		btn[0] = new JButton("借书历史");
		btn[1] = new JButton("还书");
		btn[2] = new JButton("待还书籍");
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
				List<Book> books = BookFunc.lendHistory(reader);
				if(books.size() == 0) {
					JOptionPane.showMessageDialog(null,"无记录，快去借书吧！");
					return;
				}

				
				
				
				Object rowdata[][] = new Object[books.size()][7];
				Object cnames[]= {"编号","书名","ISBN","类别","借阅时间","归还时间","是否逾期"};
				
				Iterator<Book> it=books.iterator();
				int k=0;
				
				while(it.hasNext()) {
					Book book=it.next();
					rowdata[k][0]=book.getB_id();
					rowdata[k][1]=book.getName();
					rowdata[k][2]=book.getISBN();
					rowdata[k][3]=book.getCatalog();
					rowdata[k][4]=book.getLend_date();
					if(book.getReturn_date() != null) {
						rowdata[k][5]=book.getLend_date();
					}else {
						rowdata[k][5]="未归还";
					}
					
					
					
					if(book.getIs_due()==1) {
						rowdata[k][6]="逾期";
					}else {
						rowdata[k][6]="未逾期";
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
		            	 System.out.println(selectedRow);
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
				List<Book> books = BookFunc.hasntReturn(reader);
				if(books.size() == 0) {
					JOptionPane.showMessageDialog(null,"无记录，快去借书吧！");
					return;
				}
				
				Object rowdata[][] = new Object[books.size()][8];
				Object cnames[]= {"编号","书名","ISBN","类别","借阅时间","归还时间","是否逾期","剩余天数"};
				
				Iterator<Book> it=books.iterator();
				int k=0;
				
				while(it.hasNext()) {
					Book book=it.next();
					rowdata[k][0]=book.getB_id();
					rowdata[k][1]=book.getName();
					rowdata[k][2]=book.getISBN();
					rowdata[k][3]=book.getCatalog();
					rowdata[k][4]=book.getLend_date();
					if(book.getReturn_date() != null) {
						rowdata[k][5]=book.getLend_date();
					}else {
						rowdata[k][5]="未归还";
					}
					
					
					
					if(book.getIs_due()==1) {
						rowdata[k][6]="逾期";
					}else {
						rowdata[k][6]="未逾期";
					}
					
					if(book.getIs_due()==1 || book.getReturn_date() != null) {
						rowdata[k][7]=0;
					}else {
						
						int a = (int) ((new Date().getTime() - book.getLend_date().getTime()) / (1000*3600*24));
						a = 90 - a;
						rowdata[k][7] = a;
					}
					
					k++;
					
					
					
				}

				jt=new JTable(rowdata,cnames);
				
				jt.addMouseListener(new MouseAdapter(){
		             public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
		                //得到选中的行列的索引值
		            	 selectedRow= jt.getSelectedRow();
		            	 System.out.println(selectedRow);
		            	 b_id =(int) rowdata[selectedRow][0];
		            	 System.out.println(b_id);
		            	 
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
				String available = returnAvailable(reader.getR_id(),b_id);
				if(available.equals("success")) {
					BookDao.returnBook(reader.getR_id(),b_id);
					JOptionPane.showMessageDialog(null,"归还成功！");
					btn[0].doClick();
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
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	    btn[2].doClick();
	}
	
	
	String returnAvailable(String reader_id,int book_id) {
		if(ReaderFunc.canReturn(reader_id,book_id)) {//该书是该用户借的并且现在还没归还
			return "该书不是您借的!";
		}
		if(!BookFunc.canLend(book_id)) {
			return "该书未被借走!";
		}
		return "success";
	}
	public static void main(String[] args) {
		new ReturnBook_Page(ReaderDao.getDemoReader());
	}
}