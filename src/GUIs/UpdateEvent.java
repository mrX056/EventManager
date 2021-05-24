package GUIs;

import DAOs.EventDAO;
import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

public class UpdateEvent extends Form{
    
    public Font font1, font2, font3;
    public Label nameL, dateL, locationL;
    public TextField nameTF, dateTF, locationTF;
    public Button confirm;
    
    public UpdateEvent(int id, String name, String date, String location){
        
        setTitle("Event Update");
        setLayout(BoxLayout.y());
        // FONTS
        font1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        font2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        font3 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        // LABELS
        Label nameL = new Label("Name");
        Label dateL = new Label("Date");
        Label locationL = new Label("Location");
        nameL.getUnselectedStyle().setFont(font2);
        dateL.getUnselectedStyle().setFont(font2);
        locationL.getUnselectedStyle().setFont(font2);
        // TEXTFIELDS
        nameTF = new TextField(name);
        dateTF = new TextField(date);
        locationTF = new TextField(location);
        // BUTTONS
        confirm = new Button("CONFIRM");
        confirm.addActionListener(e -> new EventDAO().updateEvent(id, nameTF.getText(), dateTF.getText(), locationTF.getText()));
        confirm.addActionListener(e -> new EventsList().show());
        addAll(nameL, nameTF, dateL, dateTF, locationL, locationTF, confirm);
        
    }
    
}
