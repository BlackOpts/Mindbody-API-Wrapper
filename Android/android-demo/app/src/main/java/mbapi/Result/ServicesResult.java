package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Models.Service;
import mbapi.Models.ServiceCategory;

public class ServicesResult extends ResultParser
{
    public ArrayList<Service> Services;

    public ServicesResult(String response)
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

            if (n.getNodeName().equals("Services"))
            {
                Services = new ArrayList<>();
                NodeList subNodes = n.getChildNodes();
                for (int c = 0; c < subNodes.getLength(); c++)
                {
                    Services.add(Service.Parse(subNodes.item(c)));
                }
            }
        }
    }
}
