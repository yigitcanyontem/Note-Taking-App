package Notes;

import DB.SQLConnection;
import LoginPage.ErrorFrame;
import LoginPage.MainFrame;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static Notes.NoteFrame.jList;
import static Notes.NoteFrame.note_List;

public class RenameFrame extends JFrame{
    static JTextField new_title;
    static JButton note_saveBTN;
    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/rename.png")));
    public RenameFrame() throws HeadlessException {
        super("Rename Title");
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

        setSize(750,400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(153, 183, 172));
        setResizable(true);
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

        new_title = new JTextField(50);
        new_title.setFont(new Font("TimesNewRoman",Font.PLAIN,20));
        new_title.setText(jList.getSelectedValue());

        note_saveBTN = new JButton("RENAME");
        note_saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rename_query();
                dispose();
            }
        });

        JLabel login = new JLabel("Enter The New Title!");
        login.setFont(new Font("TimesNewRoman",Font.BOLD,30));
        panel.add(login,"span,align center, wrap 5");
        panel.add(new_title,"span, align center, wrap 15");
        panel.add(note_saveBTN, "span, align center");

        panel.setBackground(new Color(153, 183, 172));
        panel.setMinimumSize(new Dimension(600,300));
        return panel;
    }

    public void rename_query(){
        try {
            String query = String.format("UPDATE notes SET title = '%s' WHERE email = '%s' AND title = '%s'", new_title.getText(), MainFrame.email.getText(),jList.getSelectedValue());
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);

            int rowAffected = stmt.executeUpdate();
            if(rowAffected == 1) {
                new ErrorFrame("Renamed!");
                note_List.clear();
                NoteFrame.listAdder();
            }
        } catch (SQLException ex) {
            new ErrorFrame("Error");
        }
    }
}


