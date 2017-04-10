import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;
import java.util.Calendar;
import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

public class FrmSales extends JInternalFrame{
	Container c=getContentPane();
	JButton bBillNo,bSNo,bAdd,bUpdate,bDelete,bFind,bPrint,bExit;
	JPanel pControl,pCustomer,pMovies,pSales;
	JLabel lTotal,lcCid1,lcName1,lBill,lDate,lSno,lCid,lId,lPrice,lQuantity;
	JTextField tTotal,tcCID1,tcName1,tBill,tSno,tCid,tId,tPrice,tQuantity,tmChoice;
	JFormattedTextField tDate;
	
	DefaultComboBoxModel mChoice;
	JComboBox cbChoice;
	
	DefaultTableModel mCustomer,mMovies,mSales;
	JTable tbCustomer,tbMovies,tbSales;
	
	FrmSales(){
		setTitle("Sales");
		setLayout(null);
		fillTop();
		fillCustomer();
		fillMovies();
		fillSales();
		
		lBill=new JLabel("Bill No.");
		lBill.setBounds(675,65,100,30);
		//lBill.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lBill);
		tBill=new JTextField();
		tBill.setBounds(780,65,100,30);
		c.add(tBill);
		
		lSno=new JLabel("SNo.");
		lSno.setBounds(675,120,100,30);
		//lSno.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lSno);
		tSno=new JTextField();
		tSno.setBounds(780,120,100,30);
		c.add(tSno);
		
		lDate=new JLabel("Sale Date");
		lDate.setBounds(675,175,100,30);
		//lDate.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lDate);
		try{
			tDate=new JFormattedTextField(new javax.swing.text.MaskFormatter("##-##-####"));
		}
		catch(Exception ex){
		}
		tDate.setBounds(780,175,100,30);
		c.add(tDate);
		
		lCid=new JLabel("Customer Id");
		lCid.setBounds(1004,50,100,30);
		//lCid.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lCid);
		tCid=new JTextField();
		tCid.setBounds(1110,50,100,30);
		c.add(tCid);
		
		lId=new JLabel("Movie Id");
		lId.setBounds(1005,95,100,30);
		//lId.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lId);
		tId=new JTextField();
		tId.setBounds(1110,95,100,30);
		c.add(tId);
		
		lPrice=new JLabel("Price");
		lPrice.setBounds(1005,140,100,30);
		//lPrice.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lPrice);
		tPrice=new JTextField();
		tPrice.setBounds(1110,140,100,30);
		c.add(tPrice);
		
		lQuantity=new JLabel("Quantity");
		lQuantity.setBounds(1005,185,100,30);
		//lQuantity.setFont(new Font("Papyrus",Font.BOLD,14));
		c.add(lQuantity);
		tQuantity=new JTextField();
		tQuantity.setBounds(1110,185,100,30);
		c.add(tQuantity);
		
		//---------------Buttons
		bSNo=new JButton("New SNo");
		bSNo.setBounds(710,250,100,30);
		c.add(bSNo);
		bAdd=new JButton("Add");
		bAdd.setBounds(840,250,100,30);
		c.add(bAdd);
		bUpdate=new JButton("Update");
		bUpdate.setBounds(960,250,100,30);
		c.add(bUpdate);
		bDelete=new JButton("Delete");
		bDelete.setBounds(1080,250,100,30);
		c.add(bDelete);
		
		bExit=new JButton("Exit");
		bExit.setBounds(1255,3,80,30);
		c.add(bExit);
		
		filltableCustomer();
		fillTableMovies();
		fillTableSales();
		
		Listeners();
		
		setVisible(true);
		setClosable(true);
	}
	void fillTop(){
		pControl=new JPanel();
		pControl.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.RAISED),"Controls"));
		pControl.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(1,8,30,1));
		bBillNo=new JButton("New BillNo");
		p.add(bBillNo);
		bFind=new JButton("Find");
		p.add(bFind);
				
		pControl.setBounds(5,1,300,70);
		pControl.add(p);
		c.add(pControl);
		//c.add(pControl,BorderLayout.NORTH);
	}
	void fillCustomer(){
		pCustomer=new JPanel();
		pCustomer.setLayout(null);
		pCustomer.setBounds(5,76,580,230);
		pCustomer.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Customer"));
			
		lcCid1=new JLabel("Id");
		lcCid1.setBounds(70,30,50,30);
		pCustomer.add(lcCid1);
		tcCID1=new JTextField();
		tcCID1.setBounds(110,30,100,30);
		pCustomer.add(tcCID1);
		
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
		jspcustomer.setBounds(10,80,560,140);
		pCustomer.add(jspcustomer);
			
			
		
		c.add(pCustomer);
	}
	void fillMovies(){
		pMovies=new JPanel();
		pMovies.setLayout(null);
		pMovies.setBounds(5,316,580,300);
		pMovies.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Movies"));
			
		JLabel lSearchBy=new JLabel("Search By");
		lSearchBy.setBounds(70,30,100,30);
		pMovies.add(lSearchBy);
		
		mChoice=new DefaultComboBoxModel();
		cbChoice=new JComboBox(mChoice);
		mChoice.addElement("Name");
		mChoice.addElement("Director");
		mChoice.addElement("Star Cast");
		mChoice.addElement("Rating");
		mChoice.addElement("Id");
		mChoice.addElement("Description");
		cbChoice.setBounds(150,30,100,30);
		pMovies.add(cbChoice);
		
		tmChoice=new JTextField();
		tmChoice.setBounds(280,30,130,30);
		pMovies.add(tmChoice);
		
		String heads[]={"Id","Name","Director","StarCast","Rating","Description"};
		mMovies=new DefaultTableModel(heads,0);
		tbMovies=new JTable(mMovies);
		JScrollPane jspMovies=new JScrollPane(tbMovies);
		jspMovies.setBounds(10,80,560,210);
		pMovies.add(jspMovies);
		
		c.add(pMovies);
	}
	void fillSales(){
		pSales=new JPanel();
		pSales.setLayout(null);
		pSales.setBounds(620,316,710,300);
			pSales.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Sales"));
			
		String heads[]={"BillNo.","Cust_Id","Sale Date","Sno.","Movie_Id","Price","Quantity","Total"};
		mSales=new DefaultTableModel(heads,0);
		tbSales=new JTable(mSales);
		JScrollPane jspSales=new JScrollPane(tbSales);
		jspSales.setBounds(10,20,690,220);
		pSales.add(jspSales);
		
		lTotal=new JLabel("Grand Total");
		lTotal.setBounds(420,250,100,30);
		lTotal.setFont(new Font("Serif",Font.BOLD,18));
		pSales.add(lTotal);
		tTotal=new JTextField();
		tTotal.setBounds(530,250,120,30);
		pSales.add(tTotal);
		
		bPrint=new JButton("Print");
		bPrint.setBounds(160,250,100,30);
		pSales.add(bPrint);
		
		c.add(pSales);
		
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
//----------------------Movies Table
	void fillTableMovies(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillNameMovies(String val){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies where Name like '%"+ val +"%'");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillDirectorMovies(String val){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies where DirectedBy like '%"+ val +"%'");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillStarCastMovies(String val){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies where StarCast like '%"+ val +"%'");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillDescriptionMovies(String val){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies where Description like '%"+ val +"%'");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillRatingMovies(int val){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies where rating like '%"+ val +"%'");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void fillIdMovies(int val){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select Id,Name,DirectedBy,StarCast,Rating,Description From Movies where id like '%"+ val +"%'");   				
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void removeChoiceMovies(){
		while(tbMovies.getRowCount()>0)
			mMovies.removeRow(0);
	}
//----------------------Sales Table
	void fillTableSales(){
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
	}
	void filladdSales(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select BillNo,cid,salesdate,sid,id,price,quantity From Sales where billNo=?");
			ps.setString(1,tBill.getText());
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
				String p=rs.getString(6);
				String q=rs.getString(7);
				v.add(p);
				v.add(q);
				v.add(Integer.parseInt(p)*Integer.parseInt(q)+"");
				mSales.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void removeTableSales(){
		while(tbSales.getRowCount()>0)
			mSales.removeRow(0);
	}
	void update(){
		try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
						PreparedStatement ps=con.prepareStatement("update sales set cid=?,id=?,salesdate=?,price=?,quantity=? where billNo=? AND sid=?");
						ps.setString(1,tCid.getText());
						ps.setString(2,tId.getText());
						ps.setString(3,tDate.getText());
						ps.setString(4,tPrice.getText());
						ps.setString(5,tQuantity.getText());
						ps.setString(6,tBill.getText());
						ps.setString(7,tSno.getText());
						
						int count=ps.executeUpdate();
						//if(count>0)
						//	JOptionPane.showMessageDialog(c,"Saved");	
					    con.close();
				}
				catch(Exception ex){
					JOptionPane.showConfirmDialog(c,ex.toString());
				}
	}
	void insert(){
		try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
						PreparedStatement ps=con.prepareStatement("Insert into Sales values(?,?,?,?,?,?,?)");
						ps.setString(1,tSno.getText());
						ps.setString(2,tCid.getText());
						ps.setString(3,tId.getText());
						ps.setString(4,tDate.getText());
						ps.setString(5,tBill.getText());
						ps.setString(6,tPrice.getText());
						ps.setString(7,tQuantity.getText());
						
						int count=ps.executeUpdate();
						//if(count>0)
						//	JOptionPane.showMessageDialog(c,"Saved");	
					    con.close();
				}
				catch(Exception ex){
					JOptionPane.showConfirmDialog(c,ex.toString());
				}
	}
	void delete(){
		try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Delete from sales where sid=?");
				ps.setString(1,tSno.getText());
					
				int count=ps.executeUpdate();
				if(count>0)
					JOptionPane.showMessageDialog(c,"Deleted");	
			    con.close();
		}
		catch(Exception ex){
			JOptionPane.showConfirmDialog(c,ex.toString());
		}
	}
	void find(){
		try{
				String billno=JOptionPane.showInputDialog(c,"Enter Bill No.");
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Select billNo,cid,salesdate,sid,id,price,quantity from sales where billNo=?");
				ps.setString(1,billno);	
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
					String p=rs.getString(6);
					String q=rs.getString(7);
					v.add(p);
					v.add(q);
					v.add(Integer.parseInt(p)*Integer.parseInt(q)+"");
							//v.add(tQuantity.getText());
					//v.add(tQuantity.getText());
					
					//v.add(Integer.parseInt(tPrice.getText())*Integer.parseInt(tQuantity.getText())+"");
					mSales.addRow(v);
				}
				//if(count>0)
				//	JOptionPane.showMessageDialog(c,"Saved");	
			    con.close();
		}
		catch(Exception ex){
			JOptionPane.showConfirmDialog(c,ex.toString());
		}
	}
	void custFind(){
		try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Select Distinct(billNo),cid,salesdate from sales where cid=?");
				ps.setString(1,tCid.getText());	
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
					mSales.addRow(v);
				}
				//if(count>0)
				//	JOptionPane.showMessageDialog(c,"Saved");	
			    con.close();
		}
		catch(Exception ex){
			JOptionPane.showConfirmDialog(c,ex.toString());
		}
	}
	void fillagain(){
		try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Select billNo,cid,salesdate,sid,id,price,quantity from sales where billNo=?");
				ps.setString(1,tBill.getText());	
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
					String p=rs.getString(6);
					String q=rs.getString(7);
					v.add(p);
					v.add(q);
					v.add(Integer.parseInt(p)*Integer.parseInt(q)+"");
							//v.add(tQuantity.getText());
					//v.add(tQuantity.getText());
					
					//v.add(Integer.parseInt(tPrice.getText())*Integer.parseInt(tQuantity.getText())+"");
					mSales.addRow(v);
				}
				//if(count>0)
				//	JOptionPane.showMessageDialog(c,"Saved");	
			    con.close();
		}
		catch(Exception ex){
			JOptionPane.showConfirmDialog(c,ex.toString());
		}
	}
	void PrintDetails(String s,int i,String na){
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Select Name,Price,Quantity,price*quantity from sales,movies where sales.billNo=? AND sales.id=Movies.id");
				ps.setString(1,tBill.getText());	
				ResultSet rs=ps.executeQuery();
				
				File result=new File("Result1.html");
				result.createNewFile();
				PrintWriter out=new PrintWriter(result);
				out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<link href='example.css' type='text/css' rel='stylesheet' /> ");
				out.println("</head>");	
				out.println("<body>");
				out.println("<img src='logo.png' alt='Logo' width='220' height='75' align='left' />");
				out.println("<br><h3>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Bill No : "+i+"</h3>");
				out.println("<p><b>&emsp;M/S</b>&ensp;<u>"+na+"</u> &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<b>Date</b> - "+s+"</p>");
				out.println("<table>");
				
				out.println("<tr>");
					out.println("<th>SNo.</th>");
					out.println("<th>Particulars</th>");
					out.println("<th class='money'>Price</th>");
					out.println("<th class='quan'>Quantity</th>");
					out.println("<th class='money'>Amount</th>");
				out.println("</tr>");
				int j=1,temp=0,grandtotal=0;
				while(rs.next())
				{
					if(j%2==0)
						out.println("<tr class='even'>");
					else out.println("<tr class='odd'>");
						out.println("<td>"+j+"</td>");
						out.println("<td>"+rs.getString(1)+"</td>");
						out.println("<td class='money'>Rs. "+rs.getString(2)+"</td>");
						out.println("<td class='quan'>"+rs.getString(3)+"</td>");
						temp=Integer.parseInt(rs.getString(4));
						grandtotal+=temp;
						out.println("<td class='money'>Rs. "+temp+"</td>");
					out.println("</tr>");
					++j;
				}
				out.println("<tr class='result'>");
					out.println("<td class='result'></td>");
					out.println("<td class='result'></td>");
					out.println("<td class='result'></td>");
					out.println("<td  class='result' class='quan' align='center'>TOTAL</td>");
					out.println("<td  class='result' class='money'>Rs. "+grandtotal+"</td>");
				out.println("</tr>");
				out.println("</table></body></html>");
													
				out.close();
				con.close();
				Desktop.getDesktop().open(result);
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	
	void Listeners(){
		tcCID1.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				removeCustomer();
				fillIdCustomer(Integer.parseInt(tcCID1.getText()));
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
		
		tmChoice.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				removeChoiceMovies();
				switch(cbChoice.getSelectedIndex())
				{
					case 0: fillNameMovies(tmChoice.getText());
							break;  //name
					case 1: fillDirectorMovies(tmChoice.getText()); 
							break;  //Director
					case 2: fillStarCastMovies(tmChoice.getText());
							break;  //starcast
					case 3: fillRatingMovies(Integer.parseInt(tmChoice.getText()));
							break;  //Rating
					case 4: fillIdMovies(Integer.parseInt(tmChoice.getText()));
							break;  //Movie Id
					case 5: fillDescriptionMovies(tmChoice.getText());
							break;  //Description
				}
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
				int r=tbCustomer.getSelectedRow();
				tCid.setText((String)mCustomer.getValueAt(r,0));
				removeTableSales();
				custFind();
			}
			public void mouseClicked(MouseEvent me){
			}	
		});
		
		tbMovies.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent me){
				//tbCustomer.setBackground(new Color(0,0,0,64));
			}
			public void mouseExited(MouseEvent me){
				//tbCustomer.setBackground(Color.white);
			}
			public void mousePressed(MouseEvent me){
			}
			public void mouseReleased(MouseEvent me){
				int r=tbMovies.getSelectedRow();
				tId.setText((String)mMovies.getValueAt(r,0));
			}
			public void mouseClicked(MouseEvent me){
			}	
		});
		
		tbSales.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent me){
				//tbCustomer.setBackground(new Color(0,0,0,64));
			}
			public void mouseExited(MouseEvent me){
				//tbCustomer.setBackground(Color.white);
			}
			public void mousePressed(MouseEvent me){
			}
			public void mouseReleased(MouseEvent me){
				int r=tbSales.getSelectedRow();
				tBill.setText((String)mSales.getValueAt(r,0));
				String s=(String)(mSales.getValueAt(r,3));
				tCid.setText((String)mSales.getValueAt(r,1));
				tDate.setText((String)mSales.getValueAt(r,2));
				if(s==" "){
					removeTableSales();
					fillagain();
					return;
				}
				tCid.setText((String)mSales.getValueAt(r,1));
				tDate.setText((String)mSales.getValueAt(r,2));
				tSno.setText(s);
				tId.setText((String)mSales.getValueAt(r,4));
				tPrice.setText((String)mSales.getValueAt(r,5));
				tQuantity.setText((String)mSales.getValueAt(r,6));
			}
			public void mouseClicked(MouseEvent me){
			}	
		});
		
		bBillNo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {	
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
						PreparedStatement ps=con.prepareStatement("select max(BillNo),max(sid) from Sales");
						ResultSet rs=ps.executeQuery();
						int bn=0,bs=0;
						if(rs.next())
						{
							bn=rs.getInt(1);
							bs=rs.getInt(2);
						}
						++bn;
						++bs;
						tBill.setText(bn+"");
						tSno.setText(bs+"");
						tCid.setText("");
						tId.setText("");
						tPrice.setText("");
						tQuantity.setText("");
						Calendar cal=Calendar.getInstance();
						tDate.setText(String.format("%td-%tm-%tY",cal,cal,cal));
						removeTableSales();
						//if(count>0)
						//	JOptionPane.showMessageDialog(c,"Saved");	
					    con.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(c,ex.toString());
			}
			}
		});
		bAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				insert();
				removeTableSales();
				filladdSales();
			}
		});
		
		
		bUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				update();
				removeTableSales();
				filladdSales();
			}
		});
		
		bDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				delete();
				removeTableSales();
				filladdSales();
			}
		});
		
		bSNo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {	
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
						PreparedStatement ps=con.prepareStatement("select max(sid) from Sales");
						ResultSet rs=ps.executeQuery();
						int bs=0;
						if(rs.next())
							bs=rs.getInt(1);
						++bs;
						tSno.setText(bs+"");
						tId.setText("");
						tPrice.setText("");
						tQuantity.setText("");
						//if(count>0)
						//	JOptionPane.showMessageDialog(c,"Saved");	
					    con.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(c,ex.toString());
			}
			}
		});
			
		bFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				removeTableSales();
				find();
			}
		});
		
		bPrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int i=0;
				String s="date";
				String cname="";
				try{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
						PreparedStatement ps=con.prepareStatement("Select billNo,salesdate,customers.name from sales,customers where billNo=? AND Customers.cid=Sales.cid");
						ps.setString(1,tBill.getText());	
						ResultSet rs=ps.executeQuery();
						i=0;
						Date d=new Date();
						if(rs.next())
						{
							i=rs.getInt(1);
							d=rs.getDate(2);
							cname=rs.getString(3);
						}
						s=String.format("%td-%tm-%tY",d,d,d);
						con.close();
					}
				catch(Exception ex){
				}
				PrintDetails(s,i,cname);
			}
		});
		bExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
	}
}