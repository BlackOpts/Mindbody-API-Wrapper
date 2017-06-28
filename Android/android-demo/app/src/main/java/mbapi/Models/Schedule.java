package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

import mbapi.Helper.Utility;

public class Schedule
{
    public Staff Staff;
    public SessionType SessionType;
    public Date StartDateTime;
    public Date EndDateTime;
    public Date BookableEndDateTime;
    public Location Location;

    public static Schedule Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        Schedule obj = new Schedule();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("Staff") && !node.hasAttributes()) obj.Staff = mbapi.Models.Staff.Parse(node);

            else if (field.equals("SessionType") && !node.hasAttributes()) obj.SessionType = mbapi.Models.SessionType.Parse(node);

            else if (field.equals("Location") && !node.hasAttributes()) obj.Location = mbapi.Models.Location.Parse(node);

            else if (field.equals("StartDateTime") && !node.hasAttributes()) obj.StartDateTime = Utility.getDateFromISO(node.getTextContent());
            else if (field.equals("EndDateTime") && !node.hasAttributes()) obj.EndDateTime = Utility.getDateFromISO(node.getTextContent());
            else if (field.equals("BookableEndDateTime") && !node.hasAttributes()) obj.BookableEndDateTime = Utility.getDateFromISO(node.getTextContent());
        }
        return obj;
    }
}
