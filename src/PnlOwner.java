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

public class PnlOwner extends JPanel{
	JLabel lcCid1,lcName1,lTo,lMovieTo;
	JTextField tcCid1,tcName1,tmChoice,tProfit,tQuantity,tProfitName,tProfitCustomer;
	DefaultTableModel mCustomer,mMovies,mQueryMovies,mQueryCustomer;
	JTable tbCustomer,tbMovies,tbQueryMovies,tbQueryCustomer;
	DefaultComboBoxModel mChoice;
	JComboBox cbChoice;
	JPanel pCustomer;
	JButton bQueryMovie,bQueryCustomer;
	JCheckBox cDate,cMovieDate;
	JFormattedTextField tCustomerFrom,tCustomerTo,tMoviesTo,tMoviesFrom,tProfitFrom,tProfitTo;
	JLabel lProfitDate,lProfitTo,lProfitName,lProfitCustomer;
	
	Container c;
	PnlOwner(FrmReport obj){
		setLayout(null);
		c=obj.getContentPane();
		
		fillCustomer();
		fillMovies();
		//tcCid1.setEnabled(false);
		//tcName1.setEnabled(false);
		//tmChoice.setEnabled(false);
		fillQueryMovie();
		fillQueryCustomer();
		//fillQueryProfit();
		filltableCustomer();
		fillTableMovies();
		
		Listeners();
	}
	void fillQueryMovie(){
		JPanel pFilters=new JPanel();
		pFilters.setLayout(null);
		pFilters.setBounds(870,5,350,300);
		pFilters.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Movie stats"));
		
		bQueryMovie=new JButton("Search");
		bQueryMovie.setBounds(230,260,100,30);
		pFilters.add(bQueryMovie);
		cMovieDate=new JCheckBox("Date");
		cMovieDate.setBounds(15,20,60,30);
		lMovieTo=new JLabel("to");
		lMovieTo.setBounds(195,20,30,30);
		lMovieTo.setVisible(false);
		try{
			tMoviesFrom=new JFormattedTextField(new javax.swing.text.MaskFormatter("##-##-####"));
		}
		catch(Exception ex){
		}
		tMoviesFrom.setBounds(80,20,100,30);
		try{
			tMoviesTo=new JFormattedTextField(new javax.swing.text.MaskFormatter("##-##-####"));
		}
		catch(Exception ex){
		}
		tMoviesTo.setBounds(220,20,100,30);
		tMoviesFrom.setVisible(false);
		tMoviesTo.setVisible(false);
				
		String heads[]={"Name","Copies","Earnings"};
		mQueryMovies=new DefaultTableModel(heads,0);
		tbQueryMovies=new JTable(mQueryMovies);
		JScrollPane jsp=new JScrollPane(tbQueryMovies);
		jsp.setBounds(10,60,330,190);
		
		pFilters.add(cMovieDate);
		pFilters.add(lMovieTo);
		pFilters.add(tMoviesFrom);
		pFilters.add(tMoviesTo);
		pFilters.add(jsp);
		fillTableQueryMovies();
		add(pFilters);
	}
	void fillQueryCustomer(){
		JPanel pFilters=new JPanel();
		pFilters.setLayout(null);
		pFilters.setBounds(870,330,350,280);
		pFilters.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createBevelBorder(BevelBorder.LOWERED),"Cutomer stats"));
		
		bQueryCustomer=new JButton("Search");
		bQueryCustomer.setBounds(230,240,100,30);
		pFilters.add(bQueryCustomer);
		cDate=new JCheckBox("Date");
		cDate.setBounds(15,20,60,30);
		lTo=new JLabel("to");
		lTo.setBounds(195,20,30,30);
		lTo.setVisible(false);
		try{
			tCustomerFrom=new JFormattedTextField(new javax.swing.text.MaskFormatter("##-##-####"));
		}
		catch(Exception ex){
		}
		tCustomerFrom.setBounds(80,20,100,30);
		tCustomerFrom.setVisible(false);
		try{
			tCustomerTo=new JFormattedTextField(new javax.swing.text.MaskFormatter("##-##-####"));
		}
		catch(Exception ex){
		}
		tCustomerTo.setBounds(220,20,100,30);
		tCustomerTo.setVisible(false);
		
		String heads[]={"Name","DVD's Purchased","Amount"};
		mQueryCustomer=new DefaultTableModel(heads,0);
		tbQueryCustomer=new JTable(mQueryCustomer);
		JScrollPane jsp=new JScrollPane(tbQueryCustomer);
		jsp.setBounds(10,60,330,170);
		
		pFilters.add(cDate);
		pFilters.add(lTo);
		pFilters.add(tCustomerFrom);
		pFilters.add(tCustomerTo);
		pFilters.add(jsp);
		add(pFilters);
		fillTableQueryCustomer();
	}
	void fillCustomer(){
		pCustomer=new JPanel();
		pCustomer.setLayout(null);
		pCustomer.setBounds(5,330,830,280);
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
		tbCustomer.getColumnModel().getColumn(0).setPreferredWidth(27);
		JScrollPane jspcustomer=new JScrollPane(tbCustomer);
		jspcustomer.setBounds(10,80,810,180);
		pCustomer.add(jspcustomer);
			
			
		
		add(pCustomer);
	}
	void fillMovies(){
		JPanel pMovies=new JPanel();
		pMovies.setLayout(null);
		pMovies.setBounds(5,5,830,300);
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
		tbMovies.getColumnModel().getColumn(0).setPreferredWidth(10);
		tbMovies.getColumnModel().getColumn(4).setPreferredWidth(10);
		JScrollPane jspMovies=new JScrollPane(tbMovies);
		jspMovies.setBounds(10,80,810,190);
		pMovies.add(jspMovies);
		
		add(pMovies);
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
//----------------------Customer Stats
    void fillTableQueryCustomer(){
    	try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
					String str,f,t;
					if(cDate.isSelected())
					{
						f=tCustomerFrom.getText();
						t=tCustomerTo.getText();
						str="SELECT Customers.Name, Sum(Sales.quantity), Sum(Sales.price) FROM Customers,Sales where Customers.cid=sales.cid GROUP BY Customers.Name, Sales.salesdate HAVING Sales.salesDate between #"+f+"# and #"+t+"# order by sum(sales.price) desc";
					}
					else
						str="select Customers.Name,sum(sales.quantity),sum(sales.price) from Customers,Sales where Customers.cid=sales.cid group By Customers.Name,sales.cid order by sum(sales.price) desc";
					PreparedStatement ps=con.prepareStatement(str);
					ResultSet rs=ps.executeQuery();
					Vector<Object> v;
					while(rs.next()){
						v=new Vector<Object>();
						v.add(rs.getString(1));
						v.add(rs.getString(2));
						v.add(rs.getString(3));
						mQueryCustomer.addRow(v);
					}
					con.close();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(c,ex.toString());
				}
    }
    void removeTableQueryCustomer(){
    	while(tbQueryCustomer.getRowCount()>0)
    		mQueryCustomer.removeRow(0);
    }
//----------------------Movie Stats
 	void fillTableQueryMovies(){
    	try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
					String str,f,t;
					if(cMovieDate.isSelected())
					{
						f=tMoviesFrom.getText();
						t=tMoviesTo.getText();
						str="SELECT Movies.Name, Sum(Sales.quantity), Sum(Sales.price) FROM Movies,Sales where Movies.id=sales.id GROUP BY Movies.Name,Sales.id,Sales.salesdate HAVING Sales.salesDate between #"+f+"# and #"+t+"# order by sum(sales.price) desc";
					}
					else
						str="select Movies.Name,sum(sales.quantity),sum(sales.price) from Movies,Sales where Movies.id=sales.id group By Movies.Name,sales.id order by sum(sales.price) desc";
					PreparedStatement ps=con.prepareStatement(str);
					ResultSet rs=ps.executeQuery();
					Vector<Object> v;
					while(rs.next()){
						v=new Vector<Object>();
						v.add(rs.getString(1));
						v.add(rs.getString(2));
						v.add(rs.getString(3));
						mQueryMovies.addRow(v);
					}
					con.close();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(c,ex.toString());
				}
    }
	void removeTableQueryMovies(){
    	while(tbQueryMovies.getRowCount()>0)
    		mQueryMovies.removeRow(0);
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
		bQueryCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				removeTableQueryCustomer();
				fillTableQueryCustomer();
			}	
		});
		bQueryMovie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				removeTableQueryMovies();
				fillTableQueryMovies();
			}	
		});
		
		cMovieDate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(cMovieDate.isSelected())
				{
					tMoviesFrom.setVisible(true);
					tMoviesTo.setVisible(true);
					lMovieTo.setVisible(true);
				}
				else
				{
					tMoviesFrom.setVisible(false);
					tMoviesTo.setVisible(false);
					lMovieTo.setVisible(false);
				}
			}
		});
		cDate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(cDate.isSelected())
				{
					tCustomerFrom.setVisible(true);
					tCustomerTo.setVisible(true);
					lTo.setVisible(true);
				}
				else
				{
					tCustomerFrom.setVisible(false);
					tCustomerTo.setVisible(false);
					lTo.setVisible(false);
				}
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
				removeTableQueryCustomer();
				try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
					String str,f,t;
					if(cDate.isSelected())
					{
						f=tCustomerFrom.getText();
						t=tCustomerTo.getText();
						str="SELECT Customers.Name, Sum(Sales.quantity), Sum(Sales.price) FROM Customers,Sales where Customers.cid=sales.cid AND customers.cid="+ tbCustomer.getValueAt(tbCustomer.getSelectedRow(),0) +" GROUP BY Customers.Name,sales.cid, Sales.salesdate HAVING Sales.salesDate between #"+f+"# and #"+t+"# order by sum(sales.price) desc";
					}
					else
						str="select Customers.Name,sum(sales.quantity),sum(sales.price) from Customers,Sales where Customers.cid=sales.cid AND customers.cid="+ tbCustomer.getValueAt(tbCustomer.getSelectedRow(),0) +" group By Customers.Name,sales.cid order by sum(sales.price) desc";
					PreparedStatement ps=con.prepareStatement(str);
					ResultSet rs=ps.executeQuery();
					Vector<Object> v;
					while(rs.next()){
						v=new Vector<Object>();
						v.add(rs.getString(1));
						v.add(rs.getString(2));
						v.add(rs.getString(3));
						mQueryCustomer.addRow(v);
					}
					con.close();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(c,ex.toString());
				}
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
				removeTableQueryMovies();
				try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
					String str,f,t;
					if(cMovieDate.isSelected())
					{
						f=tMoviesFrom.getText();
						t=tMoviesTo.getText();
						str="SELECT Movies.Name, Sum(Sales.quantity), Sum(Sales.price) FROM Movies,Sales where Movies.id=sales.id AND Movies.id="+ tbMovies.getValueAt(tbMovies.getSelectedRow(),0) +" GROUP BY Movies.Name,Sales.id,Sales.salesdate HAVING Sales.salesDate between #"+f+"# and #"+t+"# order by sum(sales.price) desc";
					}
					else
						str="select Movies.Name,sum(sales.quantity),sum(sales.price) from Movies,Sales where Movies.id=sales.id AND Movies.id="+ tbMovies.getValueAt(tbMovies.getSelectedRow(),0) +" group By Movies.Name,sales.id order by sum(sales.price) desc";
					PreparedStatement ps=con.prepareStatement(str);
					ResultSet rs=ps.executeQuery();
					Vector<Object> v;
					while(rs.next()){
						v=new Vector<Object>();
						v.add(rs.getString(1));
						v.add(rs.getString(2));
						v.add(rs.getString(3));
						mQueryMovies.addRow(v);
					}
					con.close();
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(c,ex.toString());
				}
    		}
			public void mouseClicked(MouseEvent me){
			}	
		});
	}
	
}