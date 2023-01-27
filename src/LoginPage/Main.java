package LoginPage;

import Notes.NoteFrame;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    static MainFrame mainFrame;
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                new MainFrame();
            }
        });
    }
}