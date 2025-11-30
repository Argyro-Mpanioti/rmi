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

            // connect to the RMI registry
            Registry rmiRegistry = LocateRegistry.getRegistry(portNumber);
            // get reference for remote object
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
            output=stub.CreateAccount(argsRest[0]);
        }
        else
        {
            if(stub.CheckAuthToken(Integer.parseInt(argsRest[0])))
            {
                if(FN_ID==2)
                {
                    output=stub.ShowAccounts();
                }
                else if(FN_ID==3)
                {
                    output=stub.SendMessage(Integer.parseInt(argsRest[0]),argsRest[1],argsRest[2]);
                }
                else if(FN_ID==4)
                {
                    output=stub.ShowInbox(Integer.parseInt(argsRest[0]));
                }
                else if(FN_ID==5)
                {
                    output=stub.ReadMessage(Integer.parseInt(argsRest[0]), Integer.parseInt(argsRest[1]));
                }
                else if(FN_ID==6)
                {
                    output=stub.DeleteMessage(Integer.parseInt(argsRest[0]), Integer.parseInt(argsRest[1]));
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