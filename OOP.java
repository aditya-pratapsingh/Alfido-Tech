import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void sound(JTextArea output) {
        output.append(name + " makes a sound\n");
    }
}

class Dog extends Animal {
    Dog(String name) {
        super(name);
    }

    @Override
    void sound(JTextArea output) {
        output.append(name + " barks: Woof! Woof!\n");
    }
}

class Cat extends Animal {
    Cat(String name) {
        super(name);
    }

    @Override
    void sound(JTextArea output) {
        output.append(name + " meows: Meow! Meow!\n");
    }
}

public class OOP extends JFrame implements ActionListener {
    private JTextArea output;
    private JButton btnAnimal, btnDog, btnCat;

    public OOP() {
        setTitle("OOP Demo with UI");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(40, 44, 52));

        output = new JTextArea();
        output.setFont(new Font("Arial", Font.BOLD, 16));
        output.setEditable(false);
        output.setBackground(new Color(60, 63, 65));
        output.setForeground(Color.WHITE);
        add(new JScrollPane(output), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 10, 10));
        panel.setBackground(new Color(40, 44, 52));

        btnAnimal = new JButton("Animal");
        btnDog = new JButton("Dog");
        btnCat = new JButton("Cat");

        styleButton(btnAnimal, new Color(100, 149, 237)); 
        styleButton(btnDog, new Color(0, 200, 83));       
        styleButton(btnCat, new Color(255, 140, 0));      

        btnAnimal.addActionListener(this);
        btnDog.addActionListener(this);
        btnCat.addActionListener(this);

        panel.add(btnAnimal);
        panel.add(btnDog);
        panel.add(btnCat);

        add(panel, BorderLayout.SOUTH);
    }

    private void styleButton(JButton btn, Color color) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAnimal) {
            Animal a = new Animal("Generic Animal");
            a.sound(output);
        } else if (e.getSource() == btnDog) {
            Dog d = new Dog("Tommy");
            d.sound(output);
        } else if (e.getSource() == btnCat) {
            Cat c = new Cat("Kitty");
            c.sound(output);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OOP frame = new OOP();
            frame.setVisible(true);
        });
    }
}
