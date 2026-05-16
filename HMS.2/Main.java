import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Run GUI in the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new AuthScreen());
    }
}