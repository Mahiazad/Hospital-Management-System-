import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AuthScreen extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private final String FILE_NAME = "users.txt";

    public AuthScreen() {
        setTitle("Hospital System - Login / Signup");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Username:"));
        userField = new JTextField();
        add(userField);

        add(new JLabel(" Password:"));
        passField = new JPasswordField();
        add(passField);

        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Sign Up");

        add(loginBtn);
        add(signupBtn);

        // Action Listeners
        signupBtn.addActionListener(e -> registerUser());
        loginBtn.addActionListener(e -> loginUser());

        setVisible(true);
    }

    private void registerUser() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty!");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(user + "," + pass);
            bw.newLine();
            JOptionPane.showMessageDialog(this, "Registration Successful! You can now log in.");
            userField.setText("");
            passField.setText("");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving user data.");
        }
    }

    private void loginUser() {
        String user = userField.getText();
        String pass = new String(passField.getPassword());

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean loggedIn = false;
            while ((line = br.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(user) && credentials[1].equals(pass)) {
                    loggedIn = true;
                    break;
                }
            }

            if (loggedIn) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                this.dispose(); // Close login window
                new DashboardScreen(); // Open Main Dashboard
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password.");
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "No users found. Please sign up first.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading user data.");
        }
    }
}