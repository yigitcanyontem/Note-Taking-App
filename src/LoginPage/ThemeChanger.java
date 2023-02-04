package LoginPage;

import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkSoftIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static LoginPage.MainFrame.*;

public class ThemeChanger implements ActionListener {

    public static LookAndFeel lightmode = new FlatMacLightLaf();
    public static LookAndFeel darkmode = new FlatGruvboxDarkSoftIJTheme();
    @Override
    public void actionPerformed(ActionEvent e) {
        if(dark_light_mode.getIcon().equals(dark)){
            try {
                UIManager.setLookAndFeel(darkmode);
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
            dark_light_mode.setIcon(light);
        }else if(dark_light_mode.getIcon().equals(light)){
            dark_light_mode.setIcon(dark);
            try {
                UIManager.setLookAndFeel(lightmode);
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
        }

        for (Frame frame:Frame.getFrames()){
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }
}
