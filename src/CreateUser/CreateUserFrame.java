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

        create_email = new JTextField(50);
        create_password = new JPasswordField(50);

        createBTN = new JButton("CREATE");
        createBTN.addActionListener(new CreateBTNListener());

        reset_code = new JTextField(50);

        go_back = new JButton("LOGIN");
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

        JLabel register = new JLabel("Register!");
        register.setFont(new Font("TimesNewRoman",Font.BOLD,20));
        panel.add(go_back,"align right, wrap 30");
        panel.add(register,"align center, wrap");
        panel.add(new JLabel("Username"),"wrap");
        panel.add(create_email,"span, grow, wrap");
        panel.add(new JLabel("Password"),"wrap");
        panel.add(create_password, "span, grow,wrap");
        panel.add(new JLabel("Reset Code"),"wrap");
        panel.add(reset_code, "span, grow,wrap");
        panel.add(createBTN,"align center");



        panel.setBackground(new Color(153, 183, 172));
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
