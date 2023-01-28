package LoginPage;

import Notes.NoteFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static LoginPage.MainFrame.bg_color;
import static LoginPage.MainFrame.label_color;
import static Notes.NoteFrame.jList;

public class ErrorFrame extends JFrame {
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

        setSize(350,200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(bg_color);
        setResizable(false);
        setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

    JPanel panel(String text){
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("fillx"));


        JButton close = new JButton("CLOSE");
        close.setBackground(label_color);
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
        login.setForeground(label_color);

        panel.add(login,"align center, wrap 15");
        panel.add(close,"align center");


        panel.setBackground(bg_color);
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
