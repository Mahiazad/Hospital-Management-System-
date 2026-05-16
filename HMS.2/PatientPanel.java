import javax.swing.*;
import java.awt.*;
import java.io.*;

public class PatientPanel extends JPanel {
    private JTextField nameField, ageField, contactField, diseaseField;
    private JComboBox<String> genderBox, bgBox, docBox, bedBox;

    public PatientPanel() {
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] bloodGroups = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
        String[] doctors = {"Dr. Smith (Cardiology)", "Dr. Adams (Neurology)", "Dr. Lee (General)"};
        String[] beds = {"Ward A - 101", "Ward A - 102", "ICU - 01", "ICU - 02"};

        nameField = new JTextField();
        ageField = new JTextField();
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        contactField = new JTextField();
        bgBox = new JComboBox<>(bloodGroups);
        diseaseField = new JTextField();
        docBox = new JComboBox<>(doctors);
        bedBox = new JComboBox<>(beds);

        formPanel.add(new JLabel("Patient Name:")); formPanel.add(nameField);
        formPanel.add(new JLabel("Age:")); formPanel.add(ageField);
        formPanel.add(new JLabel("Gender:")); formPanel.add(genderBox);
        formPanel.add(new JLabel("Contact No:")); formPanel.add(contactField);
        formPanel.add(new JLabel("Blood Group:")); formPanel.add(bgBox);
        formPanel.add(new JLabel("Disease/Symptoms:")); formPanel.add(diseaseField);
        formPanel.add(new JLabel("Allocate Doctor:")); formPanel.add(docBox);
        formPanel.add(new JLabel("Allocate Bed:")); formPanel.add(bedBox);

        JButton saveBtn = new JButton("Admit Patient");
        add(formPanel, BorderLayout.CENTER);
        add(saveBtn, BorderLayout.SOUTH);

        saveBtn.addActionListener(e -> savePatientInfo());
    }

    private void savePatientInfo() {
        if (nameField.getText().isEmpty() || ageField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name and Age are required!");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("patients.txt", true))) {
            String record = String.format("Name: %s | Age: %s | Doctor: %s | Bed: %s",
                    nameField.getText(), ageField.getText(), docBox.getSelectedItem(), bedBox.getSelectedItem());
            bw.write(record);
            bw.newLine();
            
            JOptionPane.showMessageDialog(this, "Patient Admitted Successfully!");
            
            // Clear fields after saving
            nameField.setText(""); ageField.setText(""); 
            contactField.setText(""); diseaseField.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving patient data.");
        }
    }
}