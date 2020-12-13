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
		
		JFrame frame = new JFrame("�������");
		frame.setLayout(new BorderLayout());
		frame.setSize(1000,720);
		Font f2=new Font("",Font.BOLD,15);
		
		/*background*/
		ImageIcon background=new ImageIcon("img/background.png");
		
		JLabel back=new JLabel(background);
		back.setBounds(0, 0,frame.getWidth(),frame.getHeight());
		JPanel imagePane=(JPanel)frame.getContentPane();
		imagePane.setOpaque(false);//��͸����5
		frame.getLayeredPane().add(back,new Integer(Integer.MIN_VALUE));
		frame.setLocationRelativeTo(null);
		
		
		


		Font f1=new Font("",Font.BOLD,20);
	
		
		
		/*south*/
		JPanel jp_south=new JPanel();
		jp_south.setLayout(new GridLayout(1,4));
		
		
		JButton btn[] = new JButton[4];
		btn[0] = new JButton("������ʷ");
		btn[1] = new JButton("����");
		btn[2] = new JButton("�����鼮");
		btn[3] = new JButton("����");


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
					JOptionPane.showMessageDialog(null,"�޼�¼����ȥ����ɣ�");
					return;
				}

				
				
				
				Object rowdata[][] = new Object[books.size()][7];
				Object cnames[]= {"���","����","ISBN","���","����ʱ��","�黹ʱ��","�Ƿ�����"};
				
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
						rowdata[k][5]="δ�黹";
					}
					
					
					
					if(book.getIs_due()==1) {
						rowdata[k][6]="����";
					}else {
						rowdata[k][6]="δ����";
					}
					k++;
				}

				jt=new JTable(rowdata,cnames);
				jt.setFont(f2);
				jsp.setViewportView(jt);
				jt.setBounds(0,0,1150,540);
				jt.addMouseListener(new MouseAdapter(){
		             public void mouseClicked(MouseEvent e) {//������굥��ʱ��Ӧ
		                //�õ�ѡ�е����е�����ֵ
		            	 selectedRow= jt.getSelectedRow();
		            	 System.out.println(selectedRow);
		            	 b_id = (int)rowdata[selectedRow][0];
		            	 //System.out.println(b_id);
		             }
		         }); 
				
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();  
				renderer.setOpaque(false);//render��Ԫ�������  

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
					JOptionPane.showMessageDialog(null,"�޼�¼����ȥ����ɣ�");
					return;
				}
				
				Object rowdata[][] = new Object[books.size()][8];
				Object cnames[]= {"���","����","ISBN","���","����ʱ��","�黹ʱ��","�Ƿ�����","ʣ������"};
				
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
						rowdata[k][5]="δ�黹";
					}
					
					
					
					if(book.getIs_due()==1) {
						rowdata[k][6]="����";
					}else {
						rowdata[k][6]="δ����";
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
		             public void mouseClicked(MouseEvent e) {//������굥��ʱ��Ӧ
		                //�õ�ѡ�е����е�����ֵ
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
				renderer.setOpaque(false);//render��Ԫ�������  
				//��������������У�������Ⱦ������Ϊrenderer  

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
					JOptionPane.showMessageDialog(null,"δѡ����Ŀ��");
					return;
				}
				String available = returnAvailable(reader.getR_id(),b_id);
				if(available.equals("success")) {
					BookDao.returnBook(reader.getR_id(),b_id);
					JOptionPane.showMessageDialog(null,"�黹�ɹ���");
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
		if(ReaderFunc.canReturn(reader_id,book_id)) {//�����Ǹ��û���Ĳ������ڻ�û�黹
			return "���鲻�������!";
		}
		if(!BookFunc.canLend(book_id)) {
			return "����δ������!";
		}
		return "success";
	}
	public static void main(String[] args) {
		new ReturnBook_Page(ReaderDao.getDemoReader());
	}
}