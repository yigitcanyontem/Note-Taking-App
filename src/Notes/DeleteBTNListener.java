package Notes;

import DB.SQLConnection;
import LoginPage.ErrorFrame;
import LoginPage.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static Notes.NoteFrame.jList;
import static Notes.NoteFrame.note_List;

public class DeleteBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String query = String.format("DELETE FROM notes WHERE email = '%s' AND title = '%s'", MainFrame.email.getText(),jList.getSelectedValue());
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);

            int rowAffected = stmt.executeUpdate();
            if(rowAffected == 1) {
                new ErrorFrame("Deleted!");
                note_List.clear();
                NoteFrame.listAdder();
            }
        } catch (SQLException ex) {
            new ErrorFrame("Error!");
        }
    }
}
