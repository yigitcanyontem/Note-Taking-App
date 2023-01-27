package CreateUser;

import DB.SQLConnection;
import LoginPage.ErrorFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

import static CreateUser.CreateUserFrame.*;

public class CreateBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(create_email.getText(), "") || String.valueOf(create_password.getPassword()).equals("") || Objects.equals(reset_code.getText(), "")){
            new ErrorFrame("Please Fill All!");
        }else{
            queryAdd();
        }

    }

    static void queryAdd(){
        try {
            String query = String.format("INSERT INTO users VALUES('%s','%s','%s')", create_email.getText(),String.valueOf(create_password.getPassword()),reset_code.getText());
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);

            int rowAffected = stmt.executeUpdate();
            if(rowAffected == 1) {
                new ErrorFrame("Registered!");
            }
        } catch (SQLException ex) {
            new ErrorFrame("E-Mail Already Registered");
        }
    }
}
