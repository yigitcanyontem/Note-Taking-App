package Notes;

import DB.SQLConnection;
import LoginPage.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Position;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import static Notes.NoteFrame.*;
import static Notes.NoteFrame.jList;

public class ImportBTNListener implements ActionListener {
    static JFileChooser fileChooser;
    static StringBuilder area;
    static String title;
    @Override
    public void actionPerformed(ActionEvent e) {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "Text Files (*.txt)";
            }
        });

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {

                String text;
                area = new StringBuilder();
                title = fileChooser.getSelectedFile().getName();
                while ((text = br.readLine()) != null) {
                    area.append(text).append("\n");
                }
                import_note_query();
                comment.setText("Imported");
                try{
                    jList.setSelectedIndex(jList.getNextMatch(title,0, Position.Bias.Forward));
                    jList.setSelectedIndex(jList.getNextMatch(title,0, Position.Bias.Backward));
                }catch (NullPointerException s){
                    //
                }
                java.util.Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        comment.setText("");
                    }
                },1000);
            } catch (IOException exp) {
                exp.printStackTrace();
            }
        }


    }
    public void import_note_query(){
        try {
            String query = String.format("INSERT INTO notes VALUES('%s','%s','%s')", MainFrame.email.getText(),fileChooser.getSelectedFile().getName(),area);
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);

            int rowAffected = stmt.executeUpdate();
            if(rowAffected == 1) {
                note_List.clear();
                NoteFrame.listAdder();
            }
        } catch (SQLException ex) {
            title += " (new)";
            import_note_query();
        }
    }
}



