import java.util.ArrayList;
import java.util.List;

public class Account {
    String username;
    int authToken;
    List<Message> messageBox;
    Account(String username,int authToken)
    {
        this.username=username;
        this.authToken=authToken;
        this.messageBox=new ArrayList<>();
    }
}
