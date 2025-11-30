import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteMessagingApp extends UnicastRemoteObject implements MessagingAppInt
{
    public RemoteMessagingApp() throws RemoteException {
        super();
    }



    public String CreateAccount(String username) throws RemoteException
    {
        String output;
        if(!IsUsernameValid(username))
        {
            output="Invalid Username";
        }
        else
        {
            boolean accountExists = false;
            for (Account account : MessagingServer.accounts) {
                if (account.username.equals(username)) {
                    accountExists = true;
                }
            }

            if (accountExists) {
                output = "Sorry, the user already exists";
            } else {
                MessagingServer.authTokensCounter++;
                Account account = new Account(username, MessagingServer.authTokensCounter);
                MessagingServer.accounts.add(account);
                output = String.valueOf(account.authToken);
            }
        }
        return output;
    }

    private boolean IsUsernameValid(String username)
    {
        if(username.replaceAll("[a-zA-Z0-9_]", "").isBlank())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String ShowAccounts() throws RemoteException
    {
        String output="";
        int i;
        for(i=0;i<MessagingServer.accounts.size()-1;i++)
        {
            output=output+(i+1)+". "+MessagingServer.accounts.get(i).username+"\n";
        }
        output=output+(i+1)+". "+MessagingServer.accounts.get(i).username;
        return output;
    }

    public String SendMessage(int senderAuthToken, String recipientUsername, String messageBody) throws RemoteException
    {
        boolean recipientFound=false;
        Account recipient=null;
        for(Account acc:MessagingServer.accounts)
        {
            if(acc.username.equals(recipientUsername))
            {
                recipient=acc;
                recipientFound=true;
            }
        }

        String output;
        if(recipientFound==true)
        {
            String senderUsername = null;
            for(Account acc:MessagingServer.accounts)
            {
                if(acc.authToken==senderAuthToken)
                {
                    senderUsername=acc.username;
                }
            }


            MessagingServer.messageIdsCounter++;
            Message message=new Message(senderUsername, recipientUsername, messageBody, MessagingServer.messageIdsCounter);
            recipient.messageBox.add(message);
            output="OK";
        }
        else
        {
            output="User does not exist";
        }

        return output;

    }

    public String ShowInbox(int authToken) throws RemoteException
    {
        String output="";

        Account account=null;
        for(Account acc:MessagingServer.accounts)
        {
            if(acc.authToken==authToken)
            {
                account=acc;
            }
        }
        for (Message message:account.messageBox)
        {
            output=output+message.info()+"\n";
        }

        return output;
    }

    public boolean CheckAuthToken(int authToken) throws RemoteException
    {
        boolean tokenExists=false;
        for(Account account:MessagingServer.accounts)
        {
            if(account.authToken==authToken)
            {
                tokenExists=true;
            }
        }

        String output;
        if(tokenExists)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String ReadMessage(int receiverAuthToken, int messageId) throws RemoteException
    {
        Account receiver = null;
        for (Account acc:MessagingServer.accounts)
        {
            if(acc.authToken==receiverAuthToken)
            {
                receiver=acc;
            }
        }

        boolean messageIdExists=false;
        Message message = null;
        for (Message mess: receiver.messageBox)
        {
            if(mess.id==messageId)
            {
                messageIdExists=true;
                message=mess;
            }
        }

        String output;
        if(messageIdExists)
        {
            message.isRead=true;
            output="("+message.sender+") "+message.body;
        }
        else
        {
            output="Message ID does not exist";
        }

        return output;
    }

    public String DeleteMessage(int receiverAuthToken, int messageId) throws RemoteException
    {

        Account receiver = null;
        for (Account acc:MessagingServer.accounts)
        {
            if(acc.authToken==receiverAuthToken)
            {
                receiver=acc;
            }
        }

        boolean messageIdExists=false;
        int index=0;
        for(int i=0;i<receiver.messageBox.size();i++)
        {
            if(receiver.messageBox.get(i).id==messageId)
            {
                messageIdExists=true;
                index=i;
            }
        }

        String output;
        if(messageIdExists)
        {
            receiver.messageBox.remove(index);
            output="OK";
        }
        else
        {
            output="Message does not exist";
        }

        return output;
    }

}