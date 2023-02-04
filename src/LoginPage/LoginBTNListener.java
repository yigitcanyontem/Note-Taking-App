package LoginPage;

import Notes.NoteFrame;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DB.SQLConnection.connection;
import static LoginPage.MainFrame.*;

public class LoginBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean b = true;
        boolean c = true;
        try {

            String query = String.format("SELECT * FROM users WHERE email = '%s' ", email.getText());
            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                if (resultSet.getString("email").equals(email.getText()) && resultSet.getString("password").equals(String.valueOf(password.getPassword()))){
                    for (Frame f:Frame.getFrames()){
                        if (f.getClass().getName().equals("LoginPage.MainFrame")){
                            f.dispose();
                        }
                    }

                    new NoteFrame();
                    b = false;
                }else if (resultSet.getString("email").equals(email.getText()) && !resultSet.getString("password").equals(String.valueOf(password.getPassword()))){
                    new ErrorFrame("Wrong Password!");
                    c = false;
                }else if (resultSet.getString("email").equals(email.getText()) && String.valueOf(password.getPassword()).equals("")){
                    new ErrorFrame("Fill All!");
                }
            }
            if (b){
                if (email.getText().equals("")){
                    new ErrorFrame("Fill All!");
                }else{
                    if (c){
                        new ErrorFrame("Wrong Info!");
                    }

                }
            }
        } catch (SQLException ex) {
            //
        }
    }
}
