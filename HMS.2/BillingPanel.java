import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BillingPanel extends JPanel {
    private JTextField nameField, daysField, bedCostField, medCostField, extraCostField;

    public BillingPanel() {
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        nameField = new JTextField();
        daysField = new JTextField();
        bedCostField = new JTextField("500"); // Default per day cost
        medCostField = new JTextField("0");
        extraCostField = new JTextField("0");

        formPanel.add(new JLabel("Patient Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Days in Hospital:")); formPanel.add(daysField);
        formPanel.add(new JLabel("Bed Cost (per day):")); formPanel.add(bedCostField);
        formPanel.add(new JLabel("Medicines Cost:")); formPanel.add(medCostField);
        formPanel.add(new JLabel("Other Charges:")); formPanel.add(extraCostField);

        JButton calcBtn = new JButton("Calculate & Save Bill");
        add(formPanel, BorderLayout.CENTER);
        add(calcBtn, BorderLayout.SOUTH);

        calcBtn.addActionListener(e -> calculateAndSaveBill());
    }

    private void calculateAndSaveBill() {
        if (nameField.getText().isEmpty() || daysField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient Name and Days are required.");
            return;
        }

        try {
            int days = Integer.parseInt(daysField.getText());
            double bedCost = Double.parseDouble(bedCostField.getText());
            double meds = Double.parseDouble(medCostField.getText());
            double extra = Double.parseDouble(extraCostField.getText());

            double total = (days * bedCost) + meds + extra;

            try (BufferedWriter bw = new BufferedWriter(new FileWriter("bills.txt", true))) {
                String record = String.format("Patient: %s | Total Bill: $%.2f", nameField.getText(), total);
                bw.write(record);
                bw.newLine();
                
                JOptionPane.showMessageDialog(this, "Bill Generated! Total: $" + total);
                
                // Clear fields
                nameField.setText(""); daysField.setText(""); 
                medCostField.setText("0"); extraCostField.setText("0");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for costs and days.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving bill.");
        }
    }
}