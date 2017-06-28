package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.Date;

import mbapi.Models.Location;

/**
 * Created on 8/24/16.
 */
public class LocationsResult extends ResultParser
{
    public ArrayList<Location> Locations;

    public LocationsResult(String response)
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

            if (n.getNodeName().equals("Locations"))
            {
                Locations = new ArrayList<>();
                NodeList locs = n.getChildNodes();
                for (int index2 = 0; index2 < locs.getLength(); index2++)
                {
                    Locations.add(Location.Parse(locs.item(index2)));
                }
            }
        }
    }
}
