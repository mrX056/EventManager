package GUIs;

import DAOs.EventDAO;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class DeleteEvent extends Form {
    
    public Button confirm;
    public Label label;
    public Font font;
    
    public DeleteEvent(int id) {
        
        font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        setTitle("Delete Event");
        setLayout(BoxLayout.yCenter());
        label = new Label("Are you sure you want to delete this event?");
        label.getUnselectedStyle().setFgColor(-16777216);
        label.getUnselectedStyle().setFont(font);
        label.setAlignment(CENTER);
        confirm = new Button("CONFIRM");
        confirm.addActionListener(e -> new EventDAO().removeEvent(id));
        confirm.addActionListener(e -> new EventsList().show());
        addAll(label, confirm);
    
    }
}
