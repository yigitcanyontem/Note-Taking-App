package LoginPage;

import Notes.NoteFrame;
import ResetPassword.ResetPassword;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainFrame extends JFrame {
    public static JTextField email;
    static JPasswordField password;
    static JButton loginBTN;
    static JButton newUserBTN;
    static JButton resetPass;
    public static JButton dark_light_mode;
    static ImageIcon dark = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/dark.png")));
    static ImageIcon light = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/light.png")));
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

        setSize(1016,751);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
        getRootPane().setDefaultButton(loginBTN);

        JLabel login = new JLabel("Please Login!");
        login.setFont(new Font("TimesNewRoman",Font.BOLD,30));
        JLabel userlabel = new JLabel("Username");
        JLabel passwordlabel = new JLabel("Password");

        passwordlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        userlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));

        panel.add(toolbar(),"align center, wrap 100");
        panel.add(login,"span ,align center, wrap");
        panel.add(userlabel,"wrap");
        panel.add(email,"span, grow, wrap");
        panel.add(passwordlabel,"wrap");
        panel.add(password, "span, grow,wrap 15");
        panel.add(loginBTN,"align center");

        panel.setMinimumSize(new Dimension(600,500));
        return panel;
    }

    JPanel toolbar(){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx"));
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

        dark_light_mode = new JButton();
        dark_light_mode.setIcon(light);
        dark_light_mode.addActionListener(new ThemeChanger());
        dark_light_mode.setBorder(BorderFactory.createEmptyBorder());

        panel.add(resetPass,"dock west");
        panel.add(dark_light_mode,"align center");

        panel.add(newUserBTN,"dock east");

        panel.setMinimumSize(new Dimension(600,50));
        return panel;
    }
}
