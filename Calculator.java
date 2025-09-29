import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        setTitle("Colorful Calculator");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 44, 52));

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 26));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(new Color(60, 63, 65));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(40, 44, 52));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            btn.setFocusPainted(false);

            if ("/*-+".contains(text)) {
                btn.setBackground(new Color(255, 140, 0));
                btn.setForeground(Color.WHITE);
            } else if (text.equals("=")) {
                btn.setBackground(new Color(0, 200, 83));
                btn.setForeground(Color.WHITE);
            } else if (text.equals("C")) {
                btn.setBackground(new Color(220, 20, 60));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(new Color(100, 149, 237));
                btn.setForeground(Color.WHITE);
            }

            btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 != 0) result = num1 / num2;
                    else JOptionPane.showMessageDialog(this, "Cannot divide by zero!");
                    break;
            }
            display.setText("" + result);
        } else {
            num1 = Double.parseDouble(display.getText());
            operator = command.charAt(0);
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}
