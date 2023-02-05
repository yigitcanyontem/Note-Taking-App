package LoginPage;

import Notes.NoteFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ErrorFrame extends JFrame {
    static JButton close = new JButton("CLOSE");
    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/message.png")));

    public ErrorFrame(String text) throws HeadlessException {
        super(text);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        setIconImage(icon.getImage());

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0,0,0,0);
        add(panel(text), gc);

        getRootPane().setDefaultButton(close);
        setSize(350,200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }

    JPanel panel(String text){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx"));

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Frame f:Frame.getFrames()){
                    if (f.getClass().getName().equals("CreateUser.CreateUserFrame") && text.equals("Registered!")){
                        f.dispose();
                        new MainFrame();
                    }
                }
                dispose();
            }
        });

        JLabel login = new JLabel(text);
        login.setFont(new Font("TimesNewRoman",Font.BOLD,20));

        panel.add(login,"align center, wrap 15");
        panel.add(close,"align center");

        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
