package LoginPage;

import ResetPassword.ResetPassword;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainFrame extends JFrame {
    public static JTextField email;
    static JPasswordField password;
    static JButton loginBTN;
    static JButton newUserBTN;
    static JButton resetPass;
    public MainFrame() throws HeadlessException {
        super("Login");
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0,0,0,0);
        add(panel(), gc);

        setSize(750,750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(153, 183, 172));
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    JPanel panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx"));

        email = new JTextField(50);
        email.setFont(new Font("TimesNewRoman",Font.PLAIN,20));
        password = new JPasswordField(50);
        password.setFont(new Font("TimesNewRoman",Font.PLAIN,20));

        loginBTN = new JButton("LOGIN");
        loginBTN.addActionListener(new LoginBTNListener());

        newUserBTN = new JButton("Create Account");
        newUserBTN.addActionListener(new NewUserBTNListener());

        resetPass = new JButton("Reset Password");
        resetPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Frame f:Frame.getFrames()){
                    if (f.getClass().getName().equals("LoginPage.MainFrame")){
                        f.dispose();
                    }
                }
                new ResetPassword();
            }
        });

        JLabel login = new JLabel("Please Login!");
        login.setFont(new Font("TimesNewRoman",Font.BOLD,30));
        panel.add(resetPass,"align left");
        panel.add(newUserBTN,"align right, wrap 50");
        panel.add(login,"span ,align center, wrap");
        panel.add(new JLabel("E-Mail"),"wrap");
        panel.add(email,"span, grow, wrap");
        panel.add(new JLabel("Password"),"wrap");
        panel.add(password, "span, grow,wrap");
        panel.add(loginBTN,"span, align center");


        panel.setBackground(new Color(153, 183, 172));
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
