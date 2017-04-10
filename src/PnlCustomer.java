import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PnlCustomer extends JPanel{
	JPanel pCustomer,pSales,pBill;
	JLabel lcCid1,lcName1;
	JTextField tcCid1,tcName1;
	DefaultTableModel mCustomer,mSales,mBill;
	JTable tbCustomer,tbSales,tbBill;
	Container c;
	PnlCustomer(FrmReport obj){
		setLayout(null);
		c=obj.getContentPane();
		fillCustomer();
		filltableCustomer();
		fillSales();
		fillBill();
		fillTableBill();
		
		Listeners();
	}
	
	void fillCustomer(){
		pCustomer=new JPanel();
		pCustomer.setLayout(null);
		pCustomer.setBounds(45,10,730,300);
		pCustomer.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Customer"));
			
		lcCid1=new JLabel("Id");
		lcCid1.setBounds(70,30,50,30);
		pCustomer.add(lcCid1);
		tcCid1=new JTextField();
		tcCid1.setBounds(110,30,100,30);
		pCustomer.add(tcCid1);
		
		lcName1=new JLabel("Name");
		lcName1.setBounds(270,30,50,30);
		pCustomer.add(lcName1);
		tcName1=new JTextField();
		tcName1.setBounds(330,30,100,30);
		pCustomer.add(tcName1);
		
		String heads[]={"Id","Name","Contact No.","Address"};
		mCustomer=new DefaultTableModel(heads,0);
		tbCustomer=new JTable(mCustomer);
		JScrollPane jspcustomer=new JScrollPane(tbCustomer);
		jspcustomer.setBounds(10,80,710,210);
		pCustomer.add(jspcustomer);
			
			
		
		add(pCustomer);
	}
	void fillSales(){
		pSales=new JPanel();
		pSales.setLayout(null);
		pSales.setBounds(35,316,1190,300);
			pSales.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Sales"));
			
		String heads[]={"BillNo.","Sno.","Sale Date","Movie_Name","Price","Quantity","Total"};
		mSales=new DefaultTableModel(heads,0);
		tbSales=new JTable(mSales);
		JScrollPane jspSales=new JScrollPane(tbSales);
		jspSales.setBounds(10,20,1170,270);
		pSales.add(jspSales);
			
		add(pSales);
		
	}
	void fillBill(){
		pBill=new JPanel();
		pBill.setLayout(null);
		pBill.setBounds(790,10,410,300);
			pBill.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Bills"));
			
		String heads[]={"BillNo.","Sale Date","Amount"};
		mBill=new DefaultTableModel(heads,0);
		tbBill=new JTable(mBill);
		JScrollPane jspBill=new JScrollPane(tbBill);
		jspBill.setBounds(10,20,390,270);
		pBill.add(jspBill);
			
		add(pBill);
		
	}
	
	void fillTableBill(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("select DISTINCT(BillNo),SalesDate,sum(Price*Quantity) from Sales group By BillNo,SalesDate");
			ResultSet rs=ps.executeQuery();
			Vector<Object> v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				java.util.Date d=rs.getDate(2);
				v.add(String.format("%td-%tm-%tY",d,d,d));
				v.add(rs.getString(3));
				//v.add(rs.getString(4));
				mBill.addRow(v);
			}
			con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillIdTableBill(int r){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("select DISTINCT(BillNo),SalesDate,sum(Price*Quantity) from Sales where cid="+r+" group By BillNo,SalesDate");
			ResultSet rs=ps.executeQuery();
			Vector<Object> v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				java.util.Date d=rs.getDate(2);
				v.add(String.format("%td-%tm-%tY",d,d,d));
				v.add(rs.getString(3));
				//v.add(rs.getString(4));
				mBill.addRow(v);
			}
			con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void removeTableBill(){
		while(tbBill.getRowCount()>0)
			mBill.removeRow(0);
	}
	
	void removeTableSales(){
		while(tbSales.getRowCount()>0)
			mSales.removeRow(0);
	}
//----------------------Customer Table	
	void filltableCustomer(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("select cid,name,ContactNo,Address from Customers");
			ResultSet rs=ps.executeQuery();
			Vector<Object> v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				mCustomer.addRow(v);
			}
			con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillIdCustomer(int id){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("select cid,name,ContactNo,Address from Customers where cid like '%"+ id +"%'");
			ResultSet rs=ps.executeQuery();
			Vector<Object> v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				mCustomer.addRow(v);
			}
			con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillNameCustomer(String name){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("select cid,name,ContactNo,Address from Customers where name like '%"+ name +"%'");
			ResultSet rs=ps.executeQuery();
			Vector<Object> v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				mCustomer.addRow(v);
			}
			con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void removeCustomer(){
		while(tbCustomer.getRowCount()>0)
			mCustomer.removeRow(0);
	}
//----------------------Sales Table
	/*void fillTableSales(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Distinct BillNo,cid,salesdate From Sales");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				Date d=rs.getDate(3);
					String s=String.format("%td-%tm-%tY",d,d,d);
					v.add(s);
				v.add(" ");
				//v.add(tPrice.getText());
				//v.add(tQuantity.getText());
				//v.add(tQuantity.getText());
				
				//v.add(Integer.parseInt(tPrice.getText())*Integer.parseInt(tQuantity.getText())+"");
				mSales.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}	*/
	
	void fillBillTableSales(int bn){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Sales.BillNo,Sales.sid,Sales.salesdate,Movies.Name,Sales.Price,Sales.Quantity,Sales.Price*Sales.quantity From Sales,Movies where Sales.id=Movies.id and Sales.BillNo="+bn);   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				Date d=rs.getDate(3);
					String s=String.format("%td-%tm-%tY",d,d,d);
					v.add(s);
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				v.add(rs.getString(7));
				//v.add(tPrice.getText());
				//v.add(tQuantity.getText());
				//v.add(tQuantity.getText());
				
				//v.add(Integer.parseInt(tPrice.getText())*Integer.parseInt(tQuantity.getText())+"");
				mSales.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	
	void Listeners(){
		tcCid1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				removeCustomer();
				fillIdCustomer(Integer.parseInt(tcCid1.getText()));
			}
			public void keyTyped(KeyEvent ke){
			}
		});
		tcName1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				removeCustomer();
				fillNameCustomer(tcName1.getText());
			}
			public void keyTyped(KeyEvent ke){
			}
		});
		
		tbCustomer.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent me){
				//tbCustomer.setBackground(new Color(0,0,0,64));
			}
			public void mouseExited(MouseEvent me){
				//tbCustomer.setBackground(Color.white);
			}
			public void mousePressed(MouseEvent me){
			}
			public void mouseReleased(MouseEvent me){
				removeTableBill();
				int r=Integer.parseInt(tbCustomer.getValueAt(tbCustomer.getSelectedRow(),0).toString());
				fillIdTableBill(r);
    		}
			public void mouseClicked(MouseEvent me){
			}	
		});
		
		tbBill.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent me){
				//tbCustomer.setBackground(new Color(0,0,0,64));
			}
			public void mouseExited(MouseEvent me){
				//tbCustomer.setBackground(Color.white);
			}
			public void mousePressed(MouseEvent me){
			}
			public void mouseReleased(MouseEvent me){
				removeTableSales();
				int r=Integer.parseInt(tbBill.getValueAt(tbBill.getSelectedRow(),0).toString());
				fillBillTableSales(r);
    		}
			public void mouseClicked(MouseEvent me){
			}	
		});
		
		
	}
}