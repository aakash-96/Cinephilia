import java.awt.*;
import javax.swing.*;

public class FrmReport extends JInternalFrame{
	Container c=getContentPane();
	FrmReport(){
		setTitle("Report");
		setLayout(new BorderLayout());
		JTabbedPane jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		
		jtp.add(new PnlOwner(this),"Owner");
		jtp.add(new PnlCustomer(this),"Customer");
		c.add(jtp);
				
		setVisible(true);
		setClosable(true);
	}
}