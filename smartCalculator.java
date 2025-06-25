package kodnestproject1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class smartCalculator extends JFrame {
    private JPanel contentPane;
    private JTextField display;
    private String operator = "";
    private double num1 = 0;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator frame = new Calculator();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        setTitle("Final Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 550);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        display = new JTextField();
        display.setFont(new Font("Tahoma", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setPreferredSize(new Dimension(400, 60));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        topPanel.add(display, BorderLayout.CENTER);
        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // Row 1
        buttonPanel.add(createClearButton());
        buttonPanel.add(createBackspaceButton());
        buttonPanel.add(createOperatorButton("%"));
        buttonPanel.add(createOperatorButton("/"));

        // Row 2
        buttonPanel.add(createNumberButton("7"));
        buttonPanel.add(createNumberButton("8"));
        buttonPanel.add(createNumberButton("9"));
        buttonPanel.add(createOperatorButton("*"));

        // Row 3
        buttonPanel.add(createNumberButton("4"));
        buttonPanel.add(createNumberButton("5"));
        buttonPanel.add(createNumberButton("6"));
        buttonPanel.add(createOperatorButton("-"));

        // Row 4
        buttonPanel.add(createNumberButton("1"));
        buttonPanel.add(createNumberButton("2"));
        buttonPanel.add(createNumberButton("3"));
        buttonPanel.add(createOperatorButton("+"));

        // Row 5
        buttonPanel.add(createNumberButton("0"));
        buttonPanel.add(createDecimalButton());
        buttonPanel.add(createEqualButton());
        buttonPanel.add(new JLabel("")); // empty

        contentPane.add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createNumberButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(e -> display.setText(display.getText() + text));
        return button;
    }

    private JButton createOperatorButton(String op) {
        JButton button = new JButton(op);
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(e -> {
            try {
                String currentText = display.getText();
                if (!currentText.isEmpty() && !currentText.contains(" ")) {
                    num1 = Double.parseDouble(currentText);
                    operator = op;
                    display.setText(currentText + " " + op + " ");
                }
            } catch (Exception ex) {
                display.setText("Error");
            }
        });
        return button;
    }

    private JButton createEqualButton() {
        JButton button = new JButton("=");
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(e -> {
            try {
                String[] parts = display.getText().split(" ");
                if (parts.length < 3) {
                    display.setText("Error");
                    return;
                }
                double num2 = Double.parseDouble(parts[2]);
                double result = 0;

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            display.setText("Can't divide by 0");
                            return;
                        }
                        result = num1 / num2; break;
                    case "%":
                        if (num2 == 0) {
                            display.setText("Can't divide by 0");
                            return;
                        }
                        result = num1 % num2; break;
                    default:
                        display.setText("Error");
                        return;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        });
        return button;
    }

    private JButton createClearButton() {
        JButton button = new JButton("C");
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(e -> {
            display.setText("");
            num1 = 0;
            operator = "";
        });
        return button;
    }

    private JButton createBackspaceButton() {
        JButton button = new JButton("⌫");
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(e -> {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        });
        return button;
    }

    private JButton createDecimalButton() {
        JButton button = new JButton(".");
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.addActionListener(e -> {
            String text = display.getText();
            if (text.contains(" ")) {
                String[] parts = text.split(" ");
                if (parts.length > 2 && !parts[2].contains(".")) {
                    display.setText(text + ".");
                }
            } else {
                if (!text.contains(".")) {
                    display.setText(text + ".");
                }
            }
        });
        return button;
    }
}
