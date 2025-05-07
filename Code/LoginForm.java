import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class LoginForm extends JFrame implements ActionListener {
    JTextField usernameField, captchaField;
    JPasswordField passwordField;
    JButton submitButton;
    JCheckBox showPasswordCheckbox;
    JLabel captchaLabel, captchaCodeLabel, captchaInputLabel, schoolLogoLabel;
    String captchaCode;

    public LoginForm() {
        setTitle("Login Form");
        setSize(900, 700);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Add school logo at the top center
        ImageIcon schoolLogoIcon = new ImageIcon("logo1.png"); // Provide the path to your logo image
        schoolLogoLabel = new JLabel(schoolLogoIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(schoolLogoLabel, gbc);

        // Username field
        usernameField = new JTextField(30);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 35));
        setPlaceholder(usernameField, "Enter Teacher Id");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(usernameField, gbc);

        // Password field
        passwordField = new JPasswordField(30);
        passwordField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 35));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(passwordField, gbc);

        showPasswordCheckbox = new JCheckBox("Show Password");
        showPasswordCheckbox.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 30));
        showPasswordCheckbox.setBackground(Color.ORANGE); // Set checkbox text color to orange
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(showPasswordCheckbox, gbc);
        showPasswordCheckbox.addActionListener(this);

        captchaCode = generateCaptcha();
        captchaCodeLabel = new JLabel("CAPTCHA : "+captchaCode);
        captchaCodeLabel.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 35));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(captchaCodeLabel, gbc);

        captchaField = new JTextField(30);
        setPlaceholder(captchaField, "Enter CAPTCHA");
        captchaField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 35));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        add(captchaField, gbc);

        submitButton = new JButton("LOGIN");
        submitButton.setBackground(Color.RED); // Set button background color to orange
        submitButton.setForeground(Color.WHITE);
        submitButton.setPreferredSize(new Dimension(100,30));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(submitButton, gbc);

        submitButton.addActionListener(this);

        setVisible(true);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to set placeholder text for a JTextField
    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setForeground(Color.GRAY); // Set text color to gray
        textField.setText(placeholder); // Set placeholder text
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText(""); // Clear placeholder text when focused
                    textField.setForeground(Color.BLACK); // Set text color to black
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY); // Restore gray color if no text entered
                    textField.setText(placeholder); // Restore placeholder text if no text entered
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String[] a = new String[]{"Akash","Riya"};
        String[] p = new String[]{"123","234"};
        if (e.getSource() == submitButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String enteredCaptcha = captchaField.getText();

            // Verify CAPTCHA
            if (enteredCaptcha.equals(captchaCode)) {
                // Here you can perform validation or authentication
                // For demonstration purposes, just printing the inputs
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                for(int i=0;i<a.length;i++){
                    if(username.equals(a[i]) && password.equals(p[i])){
                        openOptionPage();
                    }
                }
            } else {
                System.out.println("CAPTCHA verification failed!");
            }
        } else if (e.getSource() == showPasswordCheckbox) {
            // Toggle password visibility
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('*'); // Hide password
            }
        }
    }

    private String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int type = random.nextInt(3);
            switch (type) {
                case 0:
                    captcha.append(random.nextInt(10));
                    break;
                case 1:
                    captcha.append((char) (random.nextInt(26) + 'a'));
                    break;
                case 2:
                    captcha.append((char) (random.nextInt(26) + 'A'));
                    break;
            }
        }
        return captcha.toString();
    }
    public void openOptionPage(){
        setVisible(false);
        SwingUtilities.invokeLater(() -> new OptionPage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
