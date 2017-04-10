import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.plaf.basic.*;
import java.util.Vector;
import java.sql.*;
import java.lang.Thread;
import java.util.*;

public class FrmMovies extends JInternalFrame{
	Container c=getContentPane();
	JLabel lLanguage,lId,lName,lDirector,lProducer,lStarCast,lReleaseDate,lRunningTime,lCountry,lRating,lDescription;
	
	JTextField tLanguage,tId,tName,tDirector,tProducer,tRunningTime,tDescription;
	
	JTextArea tStarCast;
	
	JFormattedTextField tReleaseDate;
	
	DefaultComboBoxModel mCountry,mRating;
	JComboBox cbCountry,cbRating;
	
	JButton bAdd,bNew,bUpdate,bExit,bDelete;	
	DefaultTableModel mMovies;
	JTable tbMovies;
	int x=60,x2=130;
	
	FrmMovies(){
		setLayout(null);
		setTitle("Movies");
		//BasicInternalFrameUI bi = (BasicInternalFrameUI)this.getUI();
		//bi.setNorthPane(null);
		
		lId=new JLabel("ID");
		lId.setBounds(x,30,100,30);
		c.add(lId);
		tId=new JTextField();
		tId.setBounds(x2,30,120,30);
		c.add(tId);
		
		lName=new JLabel("Name");
		lName.setBounds(x,80,100,30);
		c.add(lName);
		tName=new JTextField();
		tName.setBounds(x2,80,120,30);
		c.add(tName);
		
		lDirector=new JLabel("Director");
		lDirector.setBounds(x,130,100,30);
		c.add(lDirector);
		tDirector=new JTextField();
		tDirector.setBounds(x2,130,120,30);
		c.add(tDirector);
		
		lProducer=new JLabel("Producer");
		lProducer.setBounds(x,180,100,30);
		c.add(lProducer);
		tProducer=new JTextField();
		tProducer.setBounds(x2,180,120,30);
		c.add(tProducer);
		
		lStarCast=new JLabel("Star Cast");
		lStarCast.setBounds(x,230,100,30);
		c.add(lStarCast);
		tStarCast=new JTextArea();
		tStarCast.setLineWrap(true);
		JScrollPane jspStarCast=new JScrollPane(tStarCast);
		jspStarCast.setBounds(x2,230,170,70);
		jspStarCast.setAutoscrolls(true);
		jspStarCast.setWheelScrollingEnabled(true);
		c.add(jspStarCast);
		
		lReleaseDate=new JLabel("<html>Release<br>   Date</html>");
		lReleaseDate.setBounds(x,330,100,30);
		c.add(lReleaseDate);
		try{
			tReleaseDate=new JFormattedTextField(new javax.swing.text.MaskFormatter("##-##-####"));
			tReleaseDate.setBounds(x2,330,170,30);
			c.add(tReleaseDate);
		}
		catch(Exception ex){
		}
		
		lRunningTime=new JLabel("<html>Running<br>Time</html>");
		lRunningTime.setBounds(x,380,100,30);
		c.add(lRunningTime);
		tRunningTime=new JTextField();
		tRunningTime.setBounds(x2,380,120,30);
		c.add(tRunningTime);
		JLabel lmin=new JLabel("min");
		lmin.setBounds(x2+125,380,30,30);
		c.add(lmin);
		
		lCountry=new JLabel("Country");
		lCountry.setBounds(x,430,100,30);
		c.add(lCountry);
		mCountry=new DefaultComboBoxModel();
		cbCountry=new JComboBox(mCountry);
		fillCountry();
		cbCountry.setBounds(x2,430,170,30);
		c.add(cbCountry);
		
		lRating=new JLabel("Rating");
		lRating.setBounds(x,480,100,30);
		c.add(lRating);
		mRating=new DefaultComboBoxModel();
		cbRating=new JComboBox(mRating);
		fillRating();
		cbRating.setBounds(x2,480,170,30);
		c.add(cbRating);
		
		lDescription=new JLabel("Description");
		lDescription.setBounds(x,530,100,30);
		c.add(lDescription);
		tDescription=new JTextField();
		tDescription.setBounds(x2,530,170,30);
		c.add(tDescription);
		
		lLanguage=new JLabel("Language");
		lLanguage.setBounds(x,580,100,30);
		c.add(lLanguage);
		tLanguage=new JTextField();
		tLanguage.setBounds(x2,580,100,30);
		c.add(tLanguage);
		
		String heads[]={"Id","Name","Director","Producer","StarCast","<html>Release Date</html>","Duration","Language","Country","Rating","Description"};
		mMovies=new DefaultTableModel(heads,0);
		tbMovies=new JTable(mMovies);
		//tbMovies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF));
		tbMovies.getColumnModel().getColumn(0).setPreferredWidth(37);
		//tbMovies.getColumnModel().getColumn(5).setPreferredWidth(97);
		//tbMovies.setPreferredSize(new Dimension(500,90));
		tbMovies.getTableHeader().setFont(new Font("Serif",Font.BOLD,12));
		JScrollPane jspMovies=new JScrollPane(tbMovies);
		jspMovies.setBounds(330,30,830,510);
		c.add(jspMovies);
		
		//-------------Buttons
		bAdd=new JButton("Add");
		bAdd.setBounds(1200,170,100,30);
		c.add(bAdd);
		bNew=new JButton("New");
		bNew.setBounds(1200,230,100,30);
		c.add(bNew);
		bDelete=new JButton("Delete");
		bDelete.setBounds(1200,290,100,30);
		c.add(bDelete);
		bUpdate=new JButton("Update");
		bUpdate.setBounds(1200,350,100,30);
		c.add(bUpdate);
		
		ImageIcon cross=new ImageIcon("2.png");
		bExit=new JButton(cross);
		//bExit.setBackground(Color.red);
		bExit.setBounds(1290,1,48,48);
		bExit.setContentAreaFilled(false);
		bExit.setFocusPainted(false);
		bExit.setBorderPainted(false);
		c.add(bExit);
		
		fillMovies();
		
		Listeners();
		setVisible(true);
		setClosable(true);
	}
	void Listeners(){
		bAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				insert();
				RemoveMovies();
				fillMovies();
			}
		});
		bNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				newId();
			}
		});
		bUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				UpdateId();
				RemoveMovies();
				fillMovies();
			}
		});
		bDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				DeleteId();
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
		tbMovies.addMouseListener(new MouseListener(){
			public void mouseEntered(MouseEvent me){
			}
			public void mouseExited(MouseEvent me){
			}
			public void mousePressed(MouseEvent me){
			}
			public void mouseReleased(MouseEvent me){
				int r=tbMovies.getSelectedRow();
				tId.setText((String)tbMovies.getValueAt(r,0));
				tName.setText((String)tbMovies.getValueAt(r,1));
				tDirector.setText((String)tbMovies.getValueAt(r,2));
				tProducer.setText((String)tbMovies.getValueAt(r,3));
				tStarCast.setText((String)tbMovies.getValueAt(r,4));
				tReleaseDate.setText((String)tbMovies.getValueAt(r,5));
				tRunningTime.setText((String)tbMovies.getValueAt(r,6));
				tLanguage.setText((String)tbMovies.getValueAt(r,7));
				tDescription.setText((String)tbMovies.getValueAt(r,10));
				cbRating.setSelectedItem((String)tbMovies.getValueAt(r,9));
				cbCountry.setSelectedItem((String)tbMovies.getValueAt(r,8));
			}
			public void mouseClicked(MouseEvent me){
			}
		});
	}
	void DeleteId(){
		try {	
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Delete from Movies where id=?");
				ps.setString(1,tId.getText());
				int count=ps.executeUpdate();
				if(count==0)
					JOptionPane.showMessageDialog(c,"Please check the Id");
				else
					JOptionPane.showMessageDialog(c,"Record Deleted");
				RemoveMovies();
				fillMovies();
		}
		catch(Exception ex){
		}
	}
	void newId(){
		try {	
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Select max(Id) from Movies");
				ResultSet rs=ps.executeQuery();
				int id=0;
				if(rs.next())
					id=rs.getInt(1);
				++id;
				tId.setText(String.valueOf(id));
				tName.setText("");
				tDirector.setText("");
				tProducer.setText("");
				tStarCast.setText("");
				tReleaseDate.setText("");
				tRunningTime.setText("");
				tLanguage.setText("");
				tDescription.setText("");
				con.close();
		}
		catch(Exception ex){
		}
	}
	void insert(){
		try {	
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Insert into Movies values(?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,tId.getText());
				ps.setString(2,tName.getText().toString());
				ps.setString(3,tDirector.getText().toString());
			 	ps.setString(4,tProducer.getText());
			    ps.setString(5,tStarCast.getText());
		   	    ps.setString(6,tReleaseDate.getText());
			    ps.setString(7,tRunningTime.getText());
		   	    ps.setString(8,tLanguage.getText());
				ps.setString(9,cbCountry.getSelectedItem().toString());
			    ps.setString(10,cbRating.getSelectedItem().toString());
		   	    ps.setString(11,tDescription.getText());
				int count=ps.executeUpdate();
				//if(count>0)
				//	JOptionPane.showMessageDialog(c,"Saved");	
			    con.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(c,ex.toString());
			}
	}
	void UpdateId(){
		try {	
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
				PreparedStatement ps=con.prepareStatement("Update Movies set Name=?,DirectedBy=?,ProducedBy=?,StarCast=?,ReleaseDate=?,RunningTime=?,Language=?,Country=?,Rating=?,Description=? where Id=?");
				ps.setString(1,tName.getText().toString());
				ps.setString(2,tDirector.getText().toString());
			 	ps.setString(3,tProducer.getText());
			    ps.setString(4,tStarCast.getText());
		   	    ps.setString(5,tReleaseDate.getText());
			    ps.setString(6,tRunningTime.getText());
		   	    ps.setString(7,tLanguage.getText());
				ps.setString(8,cbCountry.getSelectedItem().toString());
			    ps.setString(9,cbRating.getSelectedItem().toString());
		   	    ps.setString(10,tDescription.getText());
				ps.setString(11,tId.getText());
				int count=ps.executeUpdate();
				if(count>0)
					JOptionPane.showMessageDialog(c,"Updated");	
			    con.close();
			}
			catch (Exception ex) {
				JOptionPane.showMessageDialog(c,ex.toString());
			}
	}
	void fillMovies(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select * From Movies");
			ResultSet rs=ps.executeQuery();
			Vector<Object>v;
			while(rs.next()){
				v=new Vector<Object>();
				v.add(rs.getString(1));
				v.add(rs.getString(2));
				v.add(rs.getString(3));
				v.add(rs.getString(4));
				v.add(rs.getString(5));
				java.util.Date d=rs.getDate(6);
				v.add(String.format("%td-%tm-%tY",d,d,d));
				v.add(rs.getString(7));
				v.add(rs.getString(8));
				v.add(rs.getString(9));
				v.add(rs.getString(10));
				v.add(rs.getString(11));
				mMovies.addRow(v);
			}
				
			con.close();
		}catch(Exception ex){
			JOptionPane.showMessageDialog(c,ex.toString());
		}
	}
	void RemoveMovies(){
		while(mMovies.getRowCount()>0)
			mMovies.removeRow(0);
	}
	void fillCountry(){
		mCountry.addElement("India");
		mCountry.addElement("United Kingdom");
		mCountry.addElement("USA");
		mCountry.addElement("Canada");
		mCountry.addElement("Pakistan");
		mCountry.addElement("Malaysia");
	}
	void fillRating(){
		mRating.addElement("5");
		mRating.addElement("4");
		mRating.addElement("3");
		mRating.addElement("2");
		mRating.addElement("1");
	}
}