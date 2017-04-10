import java.awt.*;
import javax.swing.*;

public class FrmSearch extends JInternalFrame{
	Container c=getContentPane();
	FrmSearch(){
		setTitle("Search");
		setLayout(new BorderLayout());
		JTabbedPane jtp=new JTabbedPane();
		jtp.setTabPlacement(JTabbedPane.LEFT);
		
		jtp.add(new PnlName(this),"Name");
		jtp.add(new PnlDirector(this),"Director");
		jtp.add(new PnlCountry(this),"Country");
		jtp.add(new PnlRating(this),"Rating");
		jtp.add(new PnlDescription(this),"Description");
		c.add(jtp);
				
		setVisible(true);
		setClosable(true);
	}
}