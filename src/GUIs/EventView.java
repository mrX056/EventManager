package GUIs;

import DAOs.CommentDAO;
import Models.Comment;
import Models.Event;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;

public class EventView extends Form{
    
    Event event;
    Font font1, font2, font3;
    private Label nameL, dateL, locationL, userName, content, label;
    private Button commentBtn;
    private Container mainContainer, cnt;
    public ArrayList<Comment> comments;
    private Comment comment;
    
    public EventView(int id, String name, String date, String location) {
        
        setTitle("Event details");
        setLayout(BoxLayout.y());
        // FONTS
        font1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        font2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        font3 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        // LABELS
        nameL = new Label(name);
        dateL = new Label(date);
        locationL = new Label(location);
        label = new Label("Comments");
        nameL.getUnselectedStyle().setFgColor(-16777216);
        nameL.getUnselectedStyle().setFont(font1);
        dateL.getUnselectedStyle().setFont(font2);
        locationL.getUnselectedStyle().setFont(font2);
        cnt = new Container();
        cnt.setLayout(BoxLayout.x());
        cnt.addAll(dateL, locationL);
        addAll(nameL, cnt);
        comments = CommentDAO.getInstance().findAllComments(id);
        for(int i=0; i<comments.size(); i++){
            comment = comments.get(i);
            mainContainer = new Container();
            mainContainer.setLayout(new GridLayout(1,3));
            userName = new Label(comment.getUserName());
            content = new Label(comment.getContent());
            userName.getUnselectedStyle().setFont(font2);
            content.getUnselectedStyle().setFont(font3);
            content.getUnselectedStyle().setFgColor(-16777216);
            mainContainer.add(userName);
            mainContainer.add(content);
            add(mainContainer);
        }
        commentBtn= new Button("COMMENT");
        commentBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            AddComment aComment = new AddComment(id, name, date, location);
            aComment.getToolbar().setBackCommand(back);
            aComment.setBackCommand(back);
            aComment.show();
        });
        addAll(commentBtn);
        
    }
    
    Command back = new Command("Back") {
        @Override
        public void actionPerformed(ActionEvent evt) {
            showBack();
        }
    };
    
}
