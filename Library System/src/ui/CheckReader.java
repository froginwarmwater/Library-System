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

public class CheckReader {
	CheckReader(){
		show();
	}
	JTable jt = new JTable();
	int selectedRow = -1;
	String R_id;
	Reader reader;
	public void show() {
		this.reader= reader;
		JFrame frame = new JFrame("读者情况");
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
		JLabel lbl[] = new JLabel[4];
		lbl[0]=new JLabel("账号");
		lbl[1]=new JLabel("姓名");
		lbl[2]=new JLabel("身份证号");
		lbl[3]=new JLabel("部门");
		
		
		JPanel jp_north=new JPanel();
		jp_north.setLayout(new GridLayout(2,4));

		Font f1=new Font("",Font.BOLD,20);
		JTextField fld[]=new JTextField[5];
		for (int i = 0; i < 4; i ++) {
			lbl[i].setFont(f1);;
			fld[i]=new JTextField();
			fld[i].setFont(f1);
		}
		
		
		
		for(int i = 0 ; i < 4 ; i ++) {
			jp_north.add(lbl[i]);
			jp_north.add(fld[i]);
		}
		
		
		frame.add(jp_north,BorderLayout.NORTH);
		jp_north.setPreferredSize(new Dimension(1000,90));
		
		
		
		/*south*/
		JPanel jp_south=new JPanel();
		jp_south.setLayout(new GridLayout(1,5));
		
		
		JButton btn[] = new JButton[5];
		btn[0] = new JButton("查询");
		btn[1] = new JButton("修改信息");
		btn[2] = new JButton("删除");
		btn[3] = new JButton("借书详情");
		btn[4] = new JButton("返回");


		for(int i = 0 ;i <= 4; i ++) {
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
				String r_id = fld[0].getText().trim();
				String name=fld[1].getText().trim();
				String p_id=fld[2].getText().trim(),
					dept=fld[3].getText().trim();
				
				
				if(r_id.equals("")) r_id = null;
				if(p_id.equals("")) p_id = null;
				if(dept.equals("")) dept = null;
				if(name.equals("")) name = null;
				List<Reader> readers;
				if(r_id == null && p_id == null && dept == null && name == null)
					readers = ReaderDao.getAllReaders();
				else 
					readers = ReaderDao.getReadersBy(r_id,p_id,dept,name);
				Object rowdata[][] = new Object[readers.size()][9];
				Object cnames[]= {"账号","姓名","性别","身份证号","生日","部门","职务","已借数量","逾期次数"};
				
				Iterator<Reader> it=readers.iterator();
				int k=0;
				
				while(it.hasNext()) {
					Reader reader=it.next();
		
					
					rowdata[k][0]=reader.getR_id();
					rowdata[k][1]=reader.getName();
					rowdata[k][2]=reader.getSex();
					rowdata[k][3]=reader.getP_id();
					rowdata[k][4]=reader.getBirth();
					rowdata[k][5]=reader.getDep();
					rowdata[k][6]=reader.getPosition();
					rowdata[k][7]=reader.getHas_lent();
					rowdata[k][8]=reader.getWrong();
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
		            	 R_id = (String)rowdata[selectedRow][0];
		            	 System.out.println(R_id);
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
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null,"未选中项目！");
					return;
				}else {
					ReaderDao.deleteReader(R_id);
					JOptionPane.showMessageDialog(null,"删除成功！");
					btn[0].doClick();
				}
			}
				
		});
		btn[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null,"未选中项目！");
					return;
				}else {
					Reader r = ReaderDao.getReaderByR_id(R_id);
					
					frame.dispose();
					new AdminRefactorReader(r);
				}


				
				

			}	
		});
		btn[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//////////////
				if(selectedRow == -1) {
					JOptionPane.showMessageDialog(null,"未选中项目！");
					return;
				}else {
					Reader r = ReaderDao.getReaderByR_id(R_id);
					frame.dispose();
					new CheckSingleReaderLend(r);
				}
				
			}
		});
		
		
		
		btn[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AdminPage();
			}
		});
		/***************************************************/
		/*setVisible*/
		btn[0].doClick();
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	

	public static void main(String[] args) {
		new CheckReader();
	}
}