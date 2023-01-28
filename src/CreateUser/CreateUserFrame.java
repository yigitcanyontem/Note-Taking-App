package CreateUser;

import LoginPage.LoginBTNListener;
import LoginPage.MainFrame;
import LoginPage.NewUserBTNListener;
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

public class CreateUserFrame extends JFrame {
    static JTextField create_email;
    static JPasswordField create_password;
    static JTextField reset_code;
    static JButton createBTN;
    static JButton go_back;
    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/createuser.png")));

    public CreateUserFrame() throws HeadlessException {
        super("Register");
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

        create_email = new JTextField(50);
        create_email.setBackground(txt_color);
        create_password = new JPasswordField(50);
        create_password.setBackground(txt_color);

        createBTN = new JButton("CREATE");
        createBTN.addActionListener(new CreateBTNListener());
        createBTN.setBackground(label_color);

        reset_code = new JTextField(50);

        go_back = new JButton("LOGIN");
        go_back.setBackground(label_color);
        go_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Frame f:Frame.getFrames()){
                    if (f.getClass().getName().equals("CreateUser.CreateUserFrame")){
                        f.dispose();
                    }
                }
                new MainFrame();
            }
        });

        JLabel resetlabel = new JLabel("Reset Code");
        resetlabel.setForeground(label_color);
        JLabel userlabel = new JLabel("Username");
        userlabel.setForeground(label_color);
        JLabel passwordlabel = new JLabel("Password");
        passwordlabel.setForeground(label_color);

        JLabel register = new JLabel("Register!");
        register.setFont(new Font("TimesNewRoman",Font.BOLD,30));
        register.setForeground(label_color);

        resetlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        userlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));
        passwordlabel.setFont(new Font("TimesNewRoman",Font.PLAIN,15));

        panel.add(go_back,"align right, wrap 30");
        panel.add(register,"align center, wrap");
        panel.add(userlabel,"wrap");
        panel.add(create_email,"span, grow, wrap");
        panel.add(passwordlabel,"wrap");
        panel.add(create_password, "span, grow,wrap");
        panel.add(resetlabel,"wrap");
        panel.add(reset_code, "span, grow,wrap");
        panel.add(createBTN,"align center");



        panel.setBackground(bg_color);
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
