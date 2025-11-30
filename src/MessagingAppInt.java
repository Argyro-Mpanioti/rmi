import java.rmi.*;

public interface MessagingAppInt extends Remote {

    public String CreateAccount(String username) throws RemoteException;

    public String ShowAccounts() throws RemoteException;

    public String SendMessage(int senderAuthToken, String recipientUsername, String messageBody) throws RemoteException;

    public String ShowInbox(int authToken) throws RemoteException;

    public boolean CheckAuthToken(int authToken) throws RemoteException;

    public String ReadMessage(int receiverAuthToken, int messageId) throws RemoteException;

    public String DeleteMessage(int receiverAuthToken, int messageId) throws RemoteException;

}