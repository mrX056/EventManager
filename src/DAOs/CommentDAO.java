package DAOs;

import Database.Statics;
import Models.Comment;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDAO {

    private ConnectionRequest connectionRequest;
    public int userId, eventId;
    public String content, userName;
    public static CommentDAO instance;
    public ArrayList<Comment> comments;
    public static Form listOfComments;
    
    public CommentDAO() {}
    
    public static CommentDAO getInstance() {
        if(instance == null){
            instance = new CommentDAO();
        }
        return instance;
    }
    
    public void addComment(String content, int userId, String userName, int eventId){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            
            }
        };
        connectionRequest.setUrl(Statics.BASE_URL+"/addComment.php?content=" + content
                + "&userID=" + userId + "&userName=" + userName + "&eventID=" + eventId);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public ArrayList<Comment> parseComments(String jsonText, int eventId){
        try {
            comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventsListJSON = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>)eventsListJSON.get("root");
            for(Map<String, Object> obj : list){
                Comment c = new Comment();
                c.setContent(obj.get("content").toString());
                c.setUserId(Integer.parseInt(obj.get("userID").toString()));
                c.setUserName(obj.get("userName").toString());
                c.setEventId(Integer.parseInt(obj.get("eventID").toString()));
                if(c.getEventId() == eventId) comments.add(c);
            }
        } catch (IOException ex) {}
        return comments;
    }
        
    public ArrayList<Comment> findAllComments(int eventId){
        String url = Statics.BASE_URL + "/getComments.php";
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
               comments = parseComments(new String(req.getResponseData()), eventId);
               req.removeResponseListener(this);
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
    
}
