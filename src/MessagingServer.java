import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class MessagingServer
{
    public static List<Account> accounts;
    public static int authTokensCounter;
    public static int messageIdsCounter;
    public static void main(String[] args)
    {
        try
        {
            accounts = new ArrayList<>();
            authTokensCounter = 0;
            messageIdsCounter = 0;

            int portNumber = Integer.parseInt(args[0]);

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