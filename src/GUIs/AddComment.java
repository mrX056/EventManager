package GUIs;

import DAOs.CommentDAO;
import DAOs.EventDAO;
import DAOs.UserDAO;
import Models.Event;
import Models.User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.TextField;

public class AddComment extends Form {
    
    public Font font;
    public Label label;
    public Button confirm;
    public TextField comment, commentTF;
    public User user;       
    
    public AddComment(int id, String name, String date, String location) {
        
        user = new UserDAO().findConnectedUser();
        font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        setTitle("Add Comment");
        setLayout(BoxLayout.yCenter());
        label = new Label("Type your comment below");
        label.getUnselectedStyle().setFont(font);
        commentTF = new TextField();
        confirm = new Button("CONFIRM");
        confirm.addActionListener(e -> new CommentDAO().addComment(commentTF.getText(), user.getId(), user.getName(), id));
        confirm.addActionListener(e -> new EventView(id, name, date, location).show());
        addAll(commentTF, confirm);
    
    }

}
