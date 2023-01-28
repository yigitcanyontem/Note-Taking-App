package LoginPage;

import Notes.NoteFrame;
import ResetPassword.ResetPassword;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class MainFrame extends JFrame {
    public static JTextField email;
    static JPasswordField password;
    static JButton loginBTN;
    static JButton newUserBTN;
    static JButton resetPass;
    public static Color bg_color = new Color(31, 31, 31);
    public static Color label_color = new Color(189, 205, 224);
    public static Color txt_color = new Color(221, 229, 239);

    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/notebook.png")));
    public MainFrame() throws HeadlessException {
        super("Login");
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setIconImage(icon.getImage());

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0,0,0,0);
        add(panel(), gc);

        setSize(750,750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(bg_color);
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
        email.setBackground(txt_color);
        password = new JPasswordField(50);
        password.setFont(new Font("TimesNewRoman",Font.PLAIN,20));
        password.setBackground(txt_color);

        loginBTN = new JButton("LOGIN");
        loginBTN.addActionListener(new LoginBTNListener());
        loginBTN.setBackground(label_color);

        newUserBTN = new JButton("Create Account");
        newUserBTN.addActionListener(new NewUserBTNListener());
        newUserBTN.setBackground(label_color);

        resetPass = new JButton("Reset Password");
        resetPass.setBackground(label_color);
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
        login.setForeground(label_color);
        JLabel userlabel = new JLabel("Username");
        userlabel.setForeground(label_color);
        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setForeground(label_color);

        passwordlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        userlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));

        panel.add(resetPass,"align left");
        panel.add(newUserBTN,"align right, wrap 50");
        panel.add(login,"span ,align center, wrap");
        panel.add(userlabel,"wrap");
        panel.add(email,"span, grow, wrap");
        panel.add(passwordlabel,"wrap");
        panel.add(password, "span, grow,wrap 15");
        panel.add(loginBTN,"span, align center");


        panel.setBackground(bg_color);
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
