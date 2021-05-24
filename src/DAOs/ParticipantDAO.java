
package DAOs;

import Models.Event;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;

public class ParticipantDAO {
    
    private ConnectionRequest connectionRequest;

    public void addEvent(Event event){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Add to my book shelf");
            TextArea popupBody = new TextArea("Book successfully added");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.showDialog();
            }
        };
        connectionRequest.setUrl("http://localhost/shelfie/insert.php?title=" + event.getName() + "&author=" + event.getDate()+"&category="+event.getLocation());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void removeEvent(){
        
    }
   
    public void updateEvent(){
        
    }

    public void findAllEvents(){
        
    }
        
}
