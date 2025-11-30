import java.rmi.*;

public interface MessagingAppInt extends Remote {

    public String CreateAccount(String username) throws RemoteException;

    public String ShowAccounts() throws RemoteException;

    public String SendMessage(String[] args) throws RemoteException;

    public String ShowInbox(int authToken) throws RemoteException;

    public boolean CheckAuthToken(int authToken) throws RemoteException;

    public String ReadMessage(String args[]) throws RemoteException;

    public String DeleteMessage(String args[]) throws RemoteException;

}