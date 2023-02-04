package Notes;

import DB.SQLConnection;
import LoginPage.ErrorFrame;
import LoginPage.MainFrame;
import com.formdev.flatlaf.FlatIntelliJLaf;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static LoginPage.MainFrame.*;

public class NoteFrame extends JFrame {
    static DefaultListModel<String> note_List;
    static JLabel note_title;
    public static JList<String> jList;
    static JTextArea note;
    static JLabel comment;
    //static Color color = bg_color;
    static ImageIcon new_note_icon;
    static ImageIcon save_icon;
    static ImageIcon delete_icon;
    static ImageIcon rename_icon;
    static ImageIcon import_icon;
    static JButton new_note;
    static JButton save;
    static JButton delete;
    static JButton rename;
    static JScrollPane scrollPane;
    static JButton[] buttons;
    static JButton importer;
    static Map<String,String> title_note_map;
    static ImageIcon icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/notebook.png")));

    public NoteFrame() throws HeadlessException {
        super("NotePad");
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

        setSize(1600,900);
        setMinimumSize(new Dimension(1600,900));
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    JPanel panel(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        comment = new JLabel("");
        comment.setFont(new Font("TimesNewRoman",Font.BOLD,25));

        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0,0,10,500);
        panel.add(comment, gc);

        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(0,800,10,0);
        panel.add(dark_light_mode, gc);

        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(0,0,10,100);
        panel.add(button_panel(), gc);

        note_List = new DefaultListModel<>();

        listAdder();

        jList = new JList<>(note_List);
        jList.setPreferredSize(new Dimension(400,700));
        jList.setFont(new Font("TimesNewRoman",Font.BOLD,20));
        note_title = new JLabel();
        note_title.setPreferredSize(new Dimension(1000,50));
        note_title.setBorder(BorderFactory.createEtchedBorder());
        note_title.setFont(new Font("TimesNewRoman",Font.BOLD,24));

        note = new JTextArea();
        note.setPreferredSize(new Dimension(1000,640));
        note.setFont(new Font("TimesNewRoman",Font.PLAIN,20));
        note.setMargin( new Insets(15,15,15,15) );
        note.setBorder(BorderFactory.createEtchedBorder());

        scrollPane = new JScrollPane(jList);
        scrollPane.setPreferredSize(new Dimension(400,700));

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                note_title.setText("  "+jList.getSelectedValue());
                note.setText(title_note_map.get(jList.getSelectedValue()));
            }
        });

        try{
            jList.setSelectedIndex(0);
        }catch (NullPointerException e){
            //
        }


        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0,0,0,100);
        panel.add(scrollPane, gc);

        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.NORTH;
        gc.insets = new Insets(0,0,0,0);
        panel.add(note_title, gc);

        gc.weightx = 0;
        gc.weighty = 1;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(55,0,0,0);
        panel.add(note, gc);

        panel.setMinimumSize(new Dimension(1600,900));
        return panel;
    }

    JPanel button_panel(){
        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new MigLayout("fillx"));

        new_note_icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/newnote.png")));
        rename_icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/rename.png")));
        save_icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/save.png")));
        delete_icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/delete.png")));
        import_icon = new ImageIcon(Objects.requireNonNull(NoteFrame.class.getResource("/resources/import.png")));

        rename = new JButton(rename_icon);
        rename.setToolTipText("Rename");

        new_note = new JButton(new_note_icon);
        new_note.setToolTipText("New Note");

        save = new JButton(save_icon);
        save.setToolTipText("Save");

        delete = new JButton(delete_icon);
        delete.setToolTipText("Delete");

        importer = new JButton(import_icon);
        importer.setToolTipText("Import");

        buttons = new JButton[]{new_note, save, delete,rename,importer};
        for (JButton button:buttons){
            button.setPreferredSize(new Dimension(32,32));
            button.setBorder(BorderFactory.createEmptyBorder());
        }

        save.addActionListener(new SaveBTNListener());
        new_note.addActionListener(new NewNoteBTNListener());
        delete.addActionListener(new DeleteBTNListener());
        rename.addActionListener(new RenameBTNListener());
        importer.addActionListener(new ImportBTNListener());

        save.setEnabled(false);
        rename.setEnabled(false);
        delete.setEnabled(false);

        btnpanel.add(new_note);
        btnpanel.add(save, "gapleft 15");
        btnpanel.add(delete,"gapleft 15");
        btnpanel.add(rename,"gapleft 15");
        btnpanel.add(importer,"gapleft 15");


        btnpanel.setMinimumSize(new Dimension(300,70));
        return btnpanel;
    }

    static void listAdder(){
        String query = String.format("SELECT * FROM notes WHERE email = '%s'", MainFrame.email.getText());

        try {
            title_note_map = new HashMap<>();
            Statement stmt = SQLConnection.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (!save.isEnabled()){
                    save.setEnabled(true);
                    rename.setEnabled(true);
                    delete.setEnabled(true);
                }
                title_note_map.put(rs.getString("title"),rs.getString("note"));
                note_List.addElement(rs.getString("title"));
            }
        } catch (SQLException ex) {
            new ErrorFrame("Error!");
        }


    }
}
