import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class PnlRating extends JPanel{
	JLabel lRating;
	JTextField tRating;
	DefaultTableModel mMovies;
	JTable tbMovies;
	
	PnlRating(final FrmSearch a){
		setLayout(null);
		lRating=new JLabel("Rating");
		lRating.setBounds(490,40,100,60);
		lRating.setFont(new Font("Papyrus",Font.BOLD,18));
		add(lRating);
		
		tRating=new JTextField();
		tRating.setBounds(610,55,170,30);
		add(tRating);
		
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
			PreparedStatement ps=con.prepareStatement("Select * From Movies where rating like'%"+name+"%'");
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
		tRating.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent ke){
			}
			public void keyReleased(KeyEvent ke){
				
				RemoveMovies();
				fillMovies(tRating.getText());
			}
			public void keyTyped(KeyEvent ke){
			}
		});
	}
}