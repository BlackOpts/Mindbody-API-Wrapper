package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

import mbapi.Models.Client;

/**
 * Created on 8/23/16.
 */
public class ClientsResult extends ResultParser
{
    public ArrayList<Client> Clients;

    public ClientsResult(String response)
    {
        if (response != null)
        {
            CreateResultDOM(response);
            Parse();
        }
    }

    @Override
    protected void Parse()
    {
        for (int index1 = 0; index1 < ResultDOM.getLength(); index1++)
        {
            Node n = ResultDOM.item(index1);

            ParseResultInfo(n);

            if (n.getNodeName().equals("Clients"))
            {
                Clients = new ArrayList<>();
                NodeList clients = n.getChildNodes();
                for (int index2 = 0; index2 < clients.getLength(); index2++)
                {
                    Clients.add(Client.Parse(clients.item(index2)));
                }
            }

            else if (n.getNodeName().equals("Client"))
            {
                Clients = new ArrayList<>();
                Clients.add(Client.Parse(n));
            }
        }
    }
}
