package DAOs;

import Database.Statics;
import Models.User;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserDAO {
    public static UserDAO instance;
    User user;
    
    public UserDAO() {}
    
    public static UserDAO getInstance() {
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }

    public User parseUser(String jsonText){
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>)eventsListJSON.get("root");
            for(Map<String, Object> obj : list){
                User u = new User();
                u.setId(Integer.parseInt(obj.get("id").toString()));
                u.setName(obj.get("nom").toString());
                user = u;
            }
        } catch (IOException ex) {}
        return user;
    }
        
    public User findConnectedUser(){
        String url = Statics.BASE_URL + "/getConnectedUser.php";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
                user = parseUser(new String(req.getResponseData()));
                System.out.println(0);
                req.removeResponseListener(this);
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
        
}
