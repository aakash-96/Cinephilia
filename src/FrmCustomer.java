import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class FrmCustomer extends JInternalFrame{
	Container c=getContentPane();
	JLabel lId,lName,lGender,lPhNo,lAddress,lEmail,lFind;
	JTextField tId,tName,tEmail,tFind;
	
	JRadioButton rMale,rFemale;
	ButtonGroup bg;
	
	JTextField tPhNo;
	JTextArea tAddress;
	
	
	JButton bAdd,bNew,bDelete,bUpdate,bFind,bExit,bPrint;
	
	JTable tbCustomer;
	DefaultTableModel mCustomer;
	
	//----------------------
	FrmCustomer(){
		setTitle("Customer");
		setLayout(null);
		
		lId=new JLabel("Customer Id");
		lId.setBounds(100,50,100,30);
		c.add(lId);
		tId=new JTextField();
		tId.setBounds(200,50,100,30);
		c.add(tId);	
		
		lName=new JLabel("Name");
		lName.setBounds(100,100,100,30);
		c.add(lName);	
		tName=new JTextField();
		tName.setBounds(200,100,100,30);
		c.add(tName);
		
		lGender=new JLabel("Gender");
		lGender.setBounds(100,150,50,30);
		c.add(lGender);
		rMale=new JRadioButton("Male");
		rMale.setOpaque(false);
		rMale.setBounds(180,150,80,30);
		c.add(rMale);
		rFemale=new JRadioButton("Female");
		rFemale.setOpaque(false);
		rFemale.setBounds(260,150,80,30);
		c.add(rFemale);
		bg=new ButtonGroup();
		bg.add(rMale);
		bg.add(rFemale);
		
		lPhNo=new JLabel("Contact No.");
		lPhNo.setBounds(100,200,100,30);
		c.add(lPhNo);
		tPhNo=new JTextField();
		tPhNo.setBounds(200,200,100,30);
		c.add(tPhNo);
		
		
		lAddress=new JLabel("Address");
		lAddress.setBounds(100,250,100,30);
		c.add(lAddress);
		tAddress=new JTextArea();
		tAddress.setLineWrap(true);		
		//tAddress.setBounds(200,250,170,70);
		//c.add(tAddress);
		JScrollPane jspAddress=new JScrollPane(tAddress);
		jspAddress.setBounds(200,255,170,70);
		jspAddress.setAutoscrolls(true);
		jspAddress.setWheelScrollingEnabled(true);
		c.add(jspAddress);
		
		lEmail=new JLabel("Email Id");
		lEmail.setBounds(100,360,100,30);
		c.add(lEmail);
		tEmail=new JTextField();
		tEmail.setBounds(200,360,150,30);
		c.add(tEmail);
		
		lFind=new JLabel("Search");
		lFind.setBounds(660,430,100,30);
		lFind.setFont(new Font("Serif",Font.BOLD,18));
		c.add(lFind);
		tFind=new JTextField();
		tFind.setBounds(750,430,130,30);
		c.add(tFind);
		
		//Panel
	    JPanel pControl=new JPanel();
		pControl.setBorder(BorderFactory.createLineBorder(Color.black));
		pControl.setLayout(null);
		pControl.setBounds(80,520,620,70);
		//c.setBackground(new Color(113,198,113));
		//pControl.setBackground(new Color(0,0,0,64));
		c.add(pControl);
		
		//Table
		String heads[]={"Id","Name","Gender","Ph No.","Address","Email Id"};
		mCustomer=new DefaultTableModel(heads,0);
		tbCustomer=new JTable(mCustomer);
		//tbCustomer.setBackground(new Color(0,0,0,64));
		
		JScrollPane jspCustomer=new JScrollPane(tbCustomer);
		jspCustomer.setBounds(450,50,800,350);
		//jspCustomer.setOpaque(false);
		//jspCustomer.setBackground(new Color(0,0,0,64));
		//jspCustomer.getViewport().setBackground(Color.white);
		c.add(jspCustomer);
		//fillStudents();
		
		
		
		//---------------------Buttons
		bAdd=new JButton("Add");
		bAdd.setBounds(20,20,100,30);
		pControl.add(bAdd);
		
		bNew=new JButton("New");
		bNew.setBounds(140,20,100,30);
		pControl.add(bNew);
		
		bDelete=new JButton("Delete");
		bDelete.setBounds(260,20,100,30);
		pControl.add(bDelete);
		
		bUpdate=new JButton("Update");
		bUpdate.setBounds(380,20,100,30);
		pControl.add(bUpdate);
		
		//bFind=new JButton("Find");
		//bFind.setBounds(500,20,100,30);
		//pControl.add(bFind);
		
		//bPrint=new JButton("Print");
		//bPrint.setBounds(620,20,100,30);
		//pControl.add(bPrint);
		
		bExit=new JButton("Exit");
		bExit.setBounds(500,20,100,30);
		pControl.add(bExit);
		
				
		//Event Handling
		Listeners();
		fillTable();
		
				
		//--------------------------
		setVisible(true);
		setDefaultCloseOperation(3);
		
	}
	void Listeners(){
		bAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				insert();
				removeTable();
				fillTable();
			}
		});
		
		bExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					setClosed(true);
				}
				catch(Exception ex){
				}
			}
		});
		
		bNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				newRecord();
				tName.setText("");
				bg.clearSelection();
				tPhNo.setText("");
				tAddress.setText("");
				tEmail.setText("");
			}
		});
		
		bDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String r=tId.getText();
				if(r.isEmpty())
				{
					JOptionPane.showMessageDialog(c,"Please Enter the Customer ID to Delete Record");
					return;
				}
				DeleteRecord();
				removeTable();
				fillTable();
			}
		});
		
		bUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				//JOptionPane.showMessageDialog(c,"dcdasc");
				updateRecord();
				removeTable();
				fillTable();
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
				tId.setText((String)mCustomer.getValueAt(r,0));
				tName.setText(mCustomer.getValueAt(r,1).toString());
				String gender=(String)mCustomer.getValueAt(r,2);
				if(gender.equals("Male"))
					rMale.setSelected(true);
				else
					rFemale.setSelected(true);
				tPhNo.setText(mCustomer.getValueAt(r,3).toString());
				tAddress.setText(mCustomer.getValueAt(r,4).toString());
				tEmail.setText(mCustomer.getValueAt(r,5).toString());
			}
			public void mouseClicked(MouseEvent me){
			}	
		});
		tFind.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				
				removeTable();
				fillSearch();
			}
			public void keyTyped(KeyEvent ke){
			}
		});
	}
	
	void fillSearch(){
		try{
				String name=tFind.getText();
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    	Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
		    	PreparedStatement ps=con.prepareStatement("Select * from customers where name like '%"+name+"%'");
		    	//ps.setInt(1,Integer.parseInt(tId.getText()));
		    	ResultSet rs=ps.executeQuery();
		    	
		    	Vector<Object>v;
		    	while(rs.next())
		    	{
		    		v=new Vector<Object>();
		    		v.add(rs.getString(1));
		    		v.add(rs.getString(2));
		    		v.add(rs.getString(3));
		    		v.add(rs.getString(4));
		    		v.add(rs.getString(5));
		    		v.add(rs.getString(6));
		    		mCustomer.addRow(v);
		    	}
		    	
		    	con.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(c,ex.toString());
			}
	}
	void insert(){
		try{
				//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    	//Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
		    	Connection con=Data.getConnection();
		    	PreparedStatement ps=con.prepareStatement("Insert into Customers values(?,?,?,?,?,?)");
		    	//ps.setInt(1,Integer.parseInt(tId.getText()));
		    	ps.setString(1,tId.getText());
		    	ps.setString(2,tName.getText());
		    	if(rMale.isSelected())
		    		ps.setString(3,"Male");
		    	else	
		    		ps.setString(3,"Female");
		    	ps.setString(4,tPhNo.getText());
		    	ps.setString(5,tAddress.getText());
		    	ps.setString(6,tEmail.getText());
		    	int count=ps.executeUpdate();
		    	if(count>0)
		    		JOptionPane.showMessageDialog(c,"Saved");	
		    	con.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(c,ex.toString());
			}
	}
	void DeleteRecord(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("delete from Customers where cid=?");
				//ps.setInt(1,Integer.parseInt(tId.getText()));
				ps.setString(1,tId.getText());
				int count=ps.executeUpdate();
				if(count<1)
					JOptionPane.showMessageDialog(c,"No such Record FOUND");
				else
					JOptionPane.showMessageDialog(c,"Record Deleted");
				removeTable();
				fillTable();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void newRecord(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select max(cid) from Customers");
			ResultSet rs=ps.executeQuery();
			int id=0;
			if(rs.next())
				id=rs.getInt(1);
			++id;
			tId.setText(id + "");
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void updateRecord(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Update Customers set Name=?,Gender=?,ContactNo=?,Address=?,EmailId=? where cid=?");
			ps.setString(1,tName.getText());
			String g;
			if(rMale.isSelected())
				g="Male";
			else g="Female";
			ps.setString(2,g);
			ps.setString(3,tPhNo.getText());
			ps.setString(4,tAddress.getText());
			ps.setString(5,tEmail.getText());
			ps.setString(6,tId.getText());
			int count=ps.executeUpdate();
			if(count==0)
				JOptionPane.showMessageDialog(c,"Please check the fields");
			else
				JOptionPane.showMessageDialog(c,"Updated");
		}
		catch(Exception ex){
		}
	}
	void fillTable(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("select * from Customers");
			ResultSet rs=ps.executeQuery();
			Vector<Object> v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				v.add(rs.getString(6));
				mCustomer.addRow(v);
			}
			con.close();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void removeTable(){
		while(mCustomer.getRowCount()>0)
			mCustomer.removeRow(0);
	}
}