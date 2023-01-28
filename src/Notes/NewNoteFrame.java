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
import static LoginPage.MainFrame.bg_color;
import static LoginPage.MainFrame.label_color;
import static LoginPage.MainFrame.txt_color;

import static Notes.NoteFrame.note_List;


public class NewNoteFrame extends JFrame {
    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/newnote.png")));
    static JTextField new_title;
    static JButton note_saveBTN;
    public NewNoteFrame() throws HeadlessException {
        super("New Note");
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
        getContentPane().setBackground(bg_color);
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
        new_title.setBackground(txt_color);
        note_saveBTN = new JButton("CREATE");
        note_saveBTN.setBackground(label_color);
        note_saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new_note_query();
                dispose();
            }
        });


        JLabel login = new JLabel("Enter The Title!");
        login.setFont(new Font("TimesNewRoman",Font.BOLD,30));
        login.setForeground(label_color);

        panel.add(login,"span,align center, wrap 5");
        panel.add(new_title,"span, align center, wrap 15");
        panel.add(note_saveBTN, "span, align center");

        panel.setBackground(bg_color);
        panel.setMinimumSize(new Dimension(600,200));
        return panel;
    }

    public void new_note_query(){
        try {
            if (new_title.getText().equals("")){
                new_title.setText("New Note");
            }

            String query = String.format("INSERT INTO notes VALUES('%s','%s','')", MainFrame.email.getText(),new_title.getText());
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);

            int rowAffected = stmt.executeUpdate();
            if(rowAffected == 1) {
                new ErrorFrame("Created!");
                note_List.clear();
                NoteFrame.listAdder();
            }
        } catch (SQLException ex) {
            new_title.setText(new_title.getText() + " (new)");
            new_note_query();
        }
    }
}
