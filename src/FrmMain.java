import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.plaf.basic.*;
public class FrmMain extends JFrame{
	Container c=getContentPane();
	JToolBar bBar;
	JInternalFrame iFrame;
	JButton bCustomer,bSales,bMovies,bSearch,bReport,bOptions,bExit;
	FrmMain(){
		setTitle("Movies");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setLayout(new BorderLayout());
		filltop();
		Listeners();
		setVisible(true);
		setDefaultCloseOperation(3);
	}
	void filltop(){
		bBar=new JToolBar();
		bBar.setLayout(new GridLayout(1,7));
		bCustomer=new JButton("Customer");
		bCustomer.setToolTipText("Customers");
		bCustomer.setToolTipText("Customer");
		bSales=new JButton("Sales");
		bCustomer.setToolTipText("Sales");
		bMovies=new JButton("Movies");
		bCustomer.setToolTipText("Movies");
		bSearch=new JButton("Search");
		bCustomer.setToolTipText("Search");
		bReport=new JButton("Report");
		bReport.setToolTipText("Report");
		bOptions=new JButton("Options");
		
		bExit=new JButton("Exit");
		
		bBar.add(bCustomer);
		bBar.add(bSales);
		bBar.add(bMovies);
		bBar.add(bSearch);
		bBar.add(bReport);
		bBar.add(bExit);
		c.add(bBar,BorderLayout.NORTH);
	}
	void Listeners(){
		bCustomer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					iFrame.setClosed(true);
				}
				catch(Exception ex){
				}
				iFrame=new FrmCustomer();
				c.add(iFrame);
			}
		});
		bSales.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					iFrame.setClosed(true);
				}
				catch(Exception ex){
					//JOptionPane.showMessageDialog(c,ex.toString());
				}
				iFrame=new FrmSales();
				c.add(iFrame);
			}
		});
		bMovies.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					iFrame.setClosed(true);
				}
				catch(Exception ex){
				}
				iFrame=new FrmMovies();
				//BasicInternalFrameUI bi = (BasicInternalFrameUI)iFrame.getUI();
				//bi.setNorthPane(null);
				c.add(iFrame);
			}
		});
		bSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
					try{
					iFrame.setClosed(true);
				}
				catch(Exception ex){
				}
				iFrame=new FrmSearch();
				c.add(iFrame);				
			}
		});
		bReport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try{
					iFrame.setClosed(true);
				}
				catch(Exception ex){
				}
				iFrame=new FrmReport();
				c.add(iFrame);
			}
		});
		bExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
			}
		});
	}
}