public class Message {
    boolean isRead;
    String sender;
    String receiver;
    String body;
    int id;
    public String info()
    {
        String information= id +". from: "+sender;
        if(!isRead)
        {
            information=information+"*";
        }
        return information;
    }

    public Message(String sender, String receiver, String body, int id)
    {
        isRead=false;
        this.sender=sender;
        this.receiver=receiver;
        this.body=body;
        this.id=id;
    }
}
