import java.rmi.*;

public interface MessagingAppInt extends Remote {

    String CreateAccount(String username) throws RemoteException;

    String ShowAccounts() throws RemoteException;

    String SendMessage(int senderAuthToken, String recipientUsername, String messageBody) throws RemoteException;

    String ShowInbox(int authToken) throws RemoteException;

    boolean CheckAuthToken(int authToken) throws RemoteException;

    String ReadMessage(int receiverAuthToken, int messageId) throws RemoteException;

    String DeleteMessage(int receiverAuthToken, int messageId) throws RemoteException;

}