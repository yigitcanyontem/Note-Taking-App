package Notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenameBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new RenameFrame();
    }
}


