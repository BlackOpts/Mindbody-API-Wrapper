package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Models.ClientService;
import mbapi.Models.Service;
import mbapi.Models.ServiceCategory;

public class ClientServicesResult extends ResultParser
{
    public ArrayList<ClientService> ClientServices;

    public ClientServicesResult(String response)
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
        for (int i = 0; i < ResultDOM.getLength(); i++)
        {
            Node n = ResultDOM.item(i);

            ParseResultInfo(n);

            if (n.getNodeName().equals("ClientServices"))
            {
                ClientServices = new ArrayList<>();
                NodeList subNodes = n.getChildNodes();
                for (int c = 0; c < subNodes.getLength(); c++)
                {
                    ClientServices.add(ClientService.Parse(subNodes.item(c)));
                }
            }
        }
    }
}
