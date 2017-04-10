import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
public class PnlName extends JPanel{
	JLabel lName;
	JTextField tName;
	DefaultTableModel mMovies;
	JTable tbMovies;
	
	PnlName(final FrmSearch a){
		setLayout(null);
		lName=new JLabel("Enter the Name of Movie");
		lName.setBounds(390,40,200,60);
		lName.setFont(new Font("Papyrus",Font.BOLD,16));
		add(lName);
		
		tName=new JTextField();
		tName.setBounds(640,55,170,30);
		add(tName);
		
		String heads[]={"Id","Name","Director","Producer","StarCast","<html>Release Date</html>","Duration","Language","Country","Rating","Description"};
		mMovies=new DefaultTableModel(heads,0);
		tbMovies=new JTable(mMovies);
		//tbMovies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF));
		tbMovies.getColumnModel().getColumn(0).setPreferredWidth(37);
		//tbMovies.getColumnModel().getColumn(5).setPreferredWidth(97);
		//tbMovies.setPreferredSize(new Dimension(500,90));
		tbMovies.getTableHeader().setFont(new Font("Serif",Font.BOLD,12));
		JScrollPane jspMovies=new JScrollPane(tbMovies);
		jspMovies.setBounds(90,120,1100,410);
		add(jspMovies);
		
		/* StarRater starRater = new StarRater(5, 2, 1);
        starRater.addStarListener(
            new StarRater.StarListener()   {

                public void handleSelection(int selection) {
                    System.out.println(selection);
                }
            });
         starRater.setBounds(1000,50,150,60);
         add(starRater);*/
         
         //-----------------Button
		ImageIcon cross=new ImageIcon("2.png");
		JButton bClose=new JButton(cross);
		bClose.setBounds(1200,1,48,48);
		bClose.setContentAreaFilled(false);
		bClose.setFocusPainted(false);
		bClose.setBorderPainted(false);
		add(bClose);
		bClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					a.setClosed(true);
				}catch(Exception ex){
				}
			}
		});
		Listeners();
	}
	void fillMovies(String name){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con=DriverManager.getConnection("jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb);DBQ=Data.mdb");
			PreparedStatement ps=con.prepareStatement("Select * From Movies where name like'%"+name+"%'");
			//ps.setString(1,tCountry.getText());
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
			JOptionPane.showMessageDialog(this,ex.toString());
		}
	}
	void RemoveMovies(){
		while(mMovies.getRowCount()>0)
			mMovies.removeRow(0);
	}
	void Listeners(){
		tName.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				
				RemoveMovies();
				fillMovies(tName.getText());
			}
			public void keyTyped(KeyEvent ke){
			}
		});
	}
}