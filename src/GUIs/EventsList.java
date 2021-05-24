package GUIs;

import DAOs.EventDAO;
import Models.Event;
import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

public class EventsList extends Form{
    
    public Label nameL, label1, label2;
    public Container eventCnt, btnCnt;
    public Font font1, font2, font3;
    public Button[] editBtns = new Button[10], removeBtns = new Button[10], moreBtns = new Button[10];
    public Event event;
    public ArrayList<Event> events;
    
    public EventsList() {
        
        events = EventDAO.getInstance().findAllEvents();
        font1 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_LARGE);
        font2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
        font3 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        setTitle("List of events");
        setLayout(BoxLayout.y());
        for(int i = 0; i < events.size(); i++){
            int index = i;
            Event event = events.get(index);
            // CONTAINERS
            eventCnt = new Container();
            btnCnt = new Container();
            eventCnt.setLayout(BoxLayout.y());
            btnCnt.setLayout(BoxLayout.x());
            // LABELS
            nameL = new Label(event.getName());
            nameL.getUnselectedStyle().setFgColor(-16777216);
            nameL.getUnselectedStyle().setFont(font1);
            // BUTTONS
            moreBtns[i] = new Button("More details");
            editBtns[i] = new Button("Edit");
            removeBtns[i] = new Button("Remove");
            moreBtns[i].addActionListener((ActionListener) (ActionEvent evt) -> {
                EventView eView = new EventView(event.getId(), event.getName(), event.getDate(), event.getLocation());
                eView.getToolbar().setBackCommand(back);
                eView.setBackCommand(back);
                eView.show();
            });
            label1 = new Label("    ");
            editBtns[i].addActionListener((ActionListener) (ActionEvent evt) -> {
                UpdateEvent uEvent = new UpdateEvent(event.getId(), event.getName(), event.getDate(), event.getLocation());
                uEvent.getToolbar().setBackCommand(back);
                uEvent.setBackCommand(back);
                uEvent.show();
            });
            removeBtns[i].addActionListener((ActionListener) (ActionEvent evt) -> {
                DeleteEvent dEvent = new DeleteEvent(event.getId());
                dEvent.getToolbar().setBackCommand(back);
                dEvent.setBackCommand(back);
                dEvent.show();
            });
            btnCnt.setLayout(BoxLayout.x());
            btnCnt.addAll(moreBtns[i], label1, editBtns[i], removeBtns[i]);
            label2 = new Label(" ");
            eventCnt.addAll(nameL, btnCnt, label2);
            add(eventCnt);
        }
        FloatingActionButton add = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
        add.addActionListener((ActionListener) (ActionEvent evt) -> {
            AddEvent aEvent = new AddEvent();
            aEvent.getToolbar().setBackCommand(back);
            aEvent.setBackCommand(back);
            aEvent.show();
        });
        add.bindFabToContainer(getContentPane());
    
    }

    Command back = new Command("Back") {
        @Override
        public void actionPerformed(ActionEvent evt) {
            showBack();
        }
    };
    
}
