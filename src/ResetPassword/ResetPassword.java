package ResetPassword;

import LoginPage.MainFrame;
import Notes.NoteFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static LoginPage.MainFrame.bg_color;
import static LoginPage.MainFrame.label_color;
import static LoginPage.MainFrame.txt_color;

public class ResetPassword extends JFrame {
    static JTextField reset_email;
    static JPasswordField reset_password;
    static JButton resetBTN;
    static JTextField reset_resetPass;
    static JButton go_back;
    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/reset.png")));
    public ResetPassword() throws HeadlessException {
        super("Reset Password!");
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

        reset_email = new JTextField(50);
        reset_password = new JPasswordField(50);
        reset_resetPass = new JTextField(50);

        reset_email.setBackground(txt_color);
        reset_password.setBackground(txt_color);
        reset_resetPass.setBackground(txt_color);

        resetBTN = new JButton("RESET");
        resetBTN.addActionListener(new ResetBTNListener());
        resetBTN.setBackground(label_color);

        go_back = new JButton("LOGIN");
        go_back.setBackground(label_color);
        go_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainFrame();
            }
        });

        JLabel pass = new JLabel("Reset Password!");
        pass.setFont(new Font("TimesNewRoman",Font.BOLD,20));
        pass.setForeground(label_color);


        JLabel userlabel = new JLabel("Username");
        userlabel.setForeground(label_color);
        JLabel passwordlabel = new JLabel("New Password");
        passwordlabel.setForeground(label_color);
        JLabel resetlabel = new JLabel("Reset Code");
        resetlabel.setForeground(label_color);

        userlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        passwordlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        resetlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));

        panel.add(go_back,"align right, wrap 30");
        panel.add(pass,"align center, wrap");
        panel.add(userlabel,"wrap");
        panel.add(reset_email,"span, grow, wrap");
        panel.add(passwordlabel,"wrap");
        panel.add(reset_password, "span, grow,wrap");
        panel.add(resetlabel,"wrap");
        panel.add(reset_resetPass, "span, grow,wrap 15");
        panel.add(resetBTN,"align center");


        panel.setBackground(bg_color);
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
