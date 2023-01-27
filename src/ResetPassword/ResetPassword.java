package ResetPassword;

import LoginPage.LoginBTNListener;
import LoginPage.MainFrame;
import LoginPage.NewUserBTNListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPassword extends JFrame {
    static JTextField reset_email;
    static JPasswordField reset_password;
    static JButton resetBTN;
    static JTextField reset_resetPass;
    static JButton go_back;
    public ResetPassword() throws HeadlessException {
        super("Reset Password!");
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

        reset_email = new JTextField(50);
        reset_password = new JPasswordField(50);
        reset_resetPass = new JTextField(50);

        resetBTN = new JButton("RESET");
        resetBTN.addActionListener(new ResetBTNListener());

        go_back = new JButton("LOGIN");
        go_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame();
            }
        });

        JLabel pass = new JLabel("Reset Password!");
        pass.setFont(new Font("TimesNewRoman",Font.BOLD,20));

        panel.add(go_back,"align right, wrap 30");
        panel.add(pass,"align center, wrap");
        panel.add(new JLabel("E-Mail"),"wrap");
        panel.add(reset_email,"span, grow, wrap");
        panel.add(new JLabel("New Password"),"wrap");
        panel.add(reset_password, "span, grow,wrap");
        panel.add(new JLabel("Reset Code"),"wrap");
        panel.add(reset_resetPass, "span, grow,wrap");
        panel.add(resetBTN,"align center");


        panel.setBackground(new Color(153, 183, 172));
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
