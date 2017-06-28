package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Models.ServiceCategory;
import mbapi.Models.Staff;

/**
 * Created on 8/25/16.
 */
public class ServiceCategoriesResult extends ResultParser
{
    public ArrayList<ServiceCategory> ServiceCategories;

    public ServiceCategoriesResult(String response)
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

            if (n.getNodeName().equals("Programs"))
            {
                ServiceCategories = new ArrayList<>();
                NodeList subNodes = n.getChildNodes();
                for (int c = 0; c < subNodes.getLength(); c++)
                {
                    ServiceCategories.add(ServiceCategory.Parse(subNodes.item(c)));
                }
            }
        }
    }
}
