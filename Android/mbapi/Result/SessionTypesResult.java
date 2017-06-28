package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Models.ServiceCategory;
import mbapi.Models.SessionType;

/**
 * Created on 8/26/16.
 */
public class SessionTypesResult extends ResultParser
{
    public ArrayList<SessionType> SessionTypes;

    public SessionTypesResult(String response)
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

            if (n.getNodeName().equals("SessionTypes"))
            {
                SessionTypes = new ArrayList<>();
                NodeList subNodes = n.getChildNodes();
                for (int c = 0; c < subNodes.getLength(); c++)
                {
                    SessionTypes.add(SessionType.Parse(subNodes.item(c)));
                }
            }
        }
    }
}
