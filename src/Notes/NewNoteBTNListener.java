package Notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNoteBTNListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new NewNoteFrame();
    }

}
