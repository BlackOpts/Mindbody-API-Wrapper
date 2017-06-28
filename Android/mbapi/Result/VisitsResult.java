package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import mbapi.Models.Visit;

public class VisitsResult extends ResultParser
{
    public ArrayList<Visit> Visits;

    public VisitsResult(String response)
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

            if (n.getNodeName().equals("Visits"))
            {
                Visits = new ArrayList<>();
                NodeList items = n.getChildNodes();
                for (int index2 = 0; index2 < items.getLength(); index2++)
                {
                    Visits.add(Visit.Parse(items.item(index2)));
                }
            }

            else if (n.getNodeName().equals("Visit"))
            {
                Visits = new ArrayList<>();
                Visits.add(Visit.Parse(n));
            }
        }
    }
}