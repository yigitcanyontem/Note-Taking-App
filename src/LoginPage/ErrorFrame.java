package LoginPage;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorFrame extends JFrame {
    public ErrorFrame(String text) throws HeadlessException {
        super(text);
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(0,0,0,0);
        add(panel(text), gc);


        setSize(350,200);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(153, 183, 172));
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
        login.setForeground(Color.darkGray);
        panel.add(login,"align center, wrap 15");
        panel.add(close,"align center");


        panel.setBackground(new Color(153, 183, 172));
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }
}
