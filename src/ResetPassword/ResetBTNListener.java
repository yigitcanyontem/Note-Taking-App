package ResetPassword;

import DB.SQLConnection;
import LoginPage.ErrorFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import static DB.SQLConnection.connection;


public class ResetBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Objects.equals(ResetPassword.reset_email.getText(), "") || String.valueOf(ResetPassword.reset_password.getPassword()).equals("") || Objects.equals(ResetPassword.reset_resetPass.getText(), "")){
            new ErrorFrame("Please Fill All!");
        }else{
            if (check()){
                update();
            }
        }
    }

    static void update(){
        try {
            String query = String.format("UPDATE users SET password = '%s' WHERE email= '%s'", String.valueOf(ResetPassword.reset_password.getPassword()),ResetPassword.reset_email.getText());
            PreparedStatement stmt = SQLConnection.connection.prepareStatement(query);

            int rowAffected = stmt.executeUpdate();
            System.out.println(rowAffected);
            if(rowAffected == 1) {
                new ErrorFrame("Password Updated!");
            }
        } catch (SQLException ex) {
            new ErrorFrame("Error");
        }
    }

    static boolean check(){
        try {
            String query = String.format("SELECT * FROM users WHERE email = '%s' ", ResetPassword.reset_email.getText());
            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()){
                if (resultSet.getString("email").equals(ResetPassword.reset_email.getText()) && resultSet.getString("resetcode").equals(ResetPassword.reset_resetPass.getText())){
                    return true;
                }else if (!resultSet.getString("email").equals(ResetPassword.reset_email.getText()) || !resultSet.getString("resetcode").equals(ResetPassword.reset_resetPass.getText())){
                    new ErrorFrame("Incorrect Info!");
                }
            }
        } catch (SQLException ex) {
            new ErrorFrame("Error");
        }
        return false;
    }
}
