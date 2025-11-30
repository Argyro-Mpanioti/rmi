import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class MessagingServer
{
    public static List<Account> accounts;
    public static int authToken;
    public static int messageToken;
    public static void main(String args[])
    {
        try
        {
            accounts=new ArrayList<>();
            authToken=0;
            messageToken=0;

            int portNumber=Integer.parseInt(args[0]);

            RemoteMessagingApp stub = new RemoteMessagingApp();
            Registry rmiRegistry = LocateRegistry.createRegistry(portNumber);
            rmiRegistry.rebind("messaging", stub);

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

}