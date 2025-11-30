import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class MessagingClient
{
    public static void main(String args[])
    {
        try
        {
            String ip = args[0];
            int portNumber = Integer.parseInt(args[1]);
            int FN_ID = Integer.parseInt(args[2]);
            String[] argsRest= Arrays.copyOfRange(args,3,args.length);

            Registry rmiRegistry = LocateRegistry.getRegistry(portNumber);
            MessagingAppInt stub = (MessagingAppInt) rmiRegistry.lookup("messaging");

            System.out.println(DoFN_ID(stub,FN_ID,argsRest));
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


    }

    public static String DoFN_ID(MessagingAppInt stub, int FN_ID, String[] argsRest) throws RemoteException
    {
        String output="";
        if (FN_ID==1)
        {
            String username=argsRest[0];
            output=stub.CreateAccount(username);
        }
        else
        {
            int authToken=Integer.parseInt(argsRest[0]);
            if(stub.CheckAuthToken(authToken))
            {
                if(FN_ID==2)
                {
                    output=stub.ShowAccounts();
                }
                else if(FN_ID==3)
                {
                    String recipientUsername=argsRest[1];
                    String messageBody=argsRest[2];
                    output=stub.SendMessage(authToken,recipientUsername,messageBody);
                }
                else if(FN_ID==4)
                {
                    output=stub.ShowInbox(authToken);
                }
                else if(FN_ID==5)
                {
                    int messageId=Integer.parseInt(argsRest[1]);
                    output=stub.ReadMessage(authToken,messageId);
                }
                else if(FN_ID==6)
                {
                    int messageId=Integer.parseInt(argsRest[1]);
                    output=stub.DeleteMessage(authToken,messageId);
                }
            }
            else
            {
                output="Invalid Auth Token";
            }
        }

        return output;
    }

}