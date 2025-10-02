import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FReader extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton btnOpen;

    public FReader() {
        setTitle("File Reader");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 44, 52));

        textArea = new JTextArea();
        textArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setBackground(new Color(60, 63, 65));
        textArea.setForeground(Color.WHITE);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(40, 44, 52));

        btnOpen = new JButton("Open File");
        btnOpen.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnOpen.setBackground(new Color(100, 149, 237));
        btnOpen.setForeground(Color.WHITE);
        btnOpen.setFocusPainted(false);
        btnOpen.addActionListener(this);

        panel.add(btnOpen);
        add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOpen) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                readFile(file);
            }
        }
    }

    private void readFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            textArea.setText(""); 
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error reading file: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FReader reader = new FReader();
            reader.setVisible(true);
        });
    }
}
