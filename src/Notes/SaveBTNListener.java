package Notes;

import DB.SQLConnection;
import LoginPage.ErrorFrame;
import LoginPage.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import static Notes.NoteFrame.*;

public class SaveBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String query = String.format("UPDATE notes SET note = '%s' WHERE email = '%s' AND title = '%s'", NoteFrame.note.getText(),MainFrame.email.getText(),jList.getSelectedValue());
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);
            int temp = jList.getSelectedIndex();
            int rowAffected = stmt.executeUpdate();
            if(rowAffected == 1) {
                note_List.clear();
                NoteFrame.listAdder();
                comment.setText("Saved");
                try{
                    jList.setSelectedIndex(temp);
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
            }
        } catch (SQLException ex) {
            new ErrorFrame("Error");
        }
    }
}

