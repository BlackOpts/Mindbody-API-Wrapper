package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mbapi.Constants.ScheduleType;

/**
 * Created on 8/26/16.
 */
public class SessionType
{
    /// Default time length for this session type in seconds
    public int DefaultTimeLength;
    /// The service category ID
    public String ServiceCategoryID;
    /// Number deducted
    public int NumDeducted;
    /// The sesstion type ID
    public String ID;
    /// The session type's name
    public String Name;

    public static SessionType Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        SessionType obj = new SessionType();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("ProgramID") && !node.hasAttributes()) obj.ServiceCategoryID = node.getTextContent();
            else if (field.equals("Name") && !node.hasAttributes()) obj.Name = node.getTextContent();
            else if (field.equals("NumDeducted") && !node.hasAttributes()) obj.NumDeducted = Integer.parseInt(node.getTextContent());
            else if (field.equals("DefaultTimeLength") && !node.hasAttributes()) obj.DefaultTimeLength = Integer.parseInt(node.getTextContent());

        }
        return obj;
    }
}
