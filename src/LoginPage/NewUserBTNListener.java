package LoginPage;

import CreateUser.CreateUserFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserBTNListener implements ActionListener {
    static CreateUserFrame createUserFrame;
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Frame f:Frame.getFrames()){
            if (f.getClass().getName().equals("LoginPage.MainFrame")){
                f.dispose();
            }
        }
        createUserFrame = new CreateUserFrame();
    }
}
