import javax.swing.*;

public class DashboardScreen extends JFrame {
    public DashboardScreen() {
        setTitle("Hospital Management Dashboard");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Pulling in external classes as tabs
        tabbedPane.add("Patient Registration", new PatientPanel());
        tabbedPane.add("Billing System", new BillingPanel());

        add(tabbedPane);
        setVisible(true);
    }
}