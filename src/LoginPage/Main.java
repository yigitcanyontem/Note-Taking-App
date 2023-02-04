package LoginPage;


import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkSoftIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatGruvboxDarkSoftIJTheme() );
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                new MainFrame();
            }
        });
    }
}