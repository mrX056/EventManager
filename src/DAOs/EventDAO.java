
package DAOs;

import Database.Statics;
import Models.Event;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventDAO {
    public static EventDAO instance;
    public ArrayList<Event> events;
    
    public static EventDAO getInstance() {
        if(instance == null){
            instance = new EventDAO();
        }
        return instance;
    }
    
    public void addEvent(String name, String date, String location){
        ConnectionRequest connectionRequest = new ConnectionRequest();
        connectionRequest.setUrl(Statics.BASE_URL+"/AddEvent.php?name=" + name + 
                "&date=" + date + "&location=" + location);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    public void removeEvent(int id){
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {}           
        };
        connectionRequest.setUrl(Statics.BASE_URL + "/deleteEvent.php?id=" + id);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
   
   public void updateEvent(int id, String name, String date, String location){
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {}
        };
        connectionRequest.setUrl(Statics.BASE_URL + "/updateEvent.php?name="+name+
                                "&date="+date+"&location="+location+"&id="+id);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public ArrayList<Event> parseEvents(String jsonText){
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>)eventsListJSON.get("root");
            for(Map<String, Object> obj : list){
                Event e = new Event();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setName(obj.get("name").toString());
                e.setDate(obj.get("date").toString());
                e.setLocation(obj.get("location").toString());
                events.add(e);
            }
        } catch (IOException ex) {}
        return events;
    }
        
    public ArrayList<Event> findAllEvents(){
        String url = Statics.BASE_URL + "/getEvents.php";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               events = parseEvents(new String(req.getResponseData()));
               req.removeResponseListener(this);
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
        
}
