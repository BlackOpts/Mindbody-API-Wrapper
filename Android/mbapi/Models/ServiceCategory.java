package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import mbapi.Constants.ScheduleType;

/**
 * Created on 8/25/16.
 */
public class ServiceCategory
{
    public String ID;
    /// The service category's title
    public String Name;
    /// Service category's type
    public ScheduleType Type;
    /// The offset in seconds that allows cancelling
    public int CancelOffset;

    public static ServiceCategory Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        ServiceCategory obj = new ServiceCategory();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();


            if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("Name") && !node.hasAttributes()) obj.Name = node.getTextContent();
            else if (field.equals("CancelOffset") && !node.hasAttributes()) obj.CancelOffset = Integer.parseInt(node.getTextContent());

            else if (field.equals("ScheduleType") && !node.hasAttributes())
            {
                String type = node.getTextContent();
                if (type.equals(ScheduleType.Appointment.toString())) obj.Type = ScheduleType.Appointment;
                else if (type.equals(ScheduleType.Arrival.toString())) obj.Type = ScheduleType.Arrival;
                else if (type.equals(ScheduleType.DropIn.toString())) obj.Type = ScheduleType.DropIn;
                else if (type.equals(ScheduleType.Enrollment.toString())) obj.Type = ScheduleType.Enrollment;
                else if (type.equals(ScheduleType.Media.toString())) obj.Type = ScheduleType.Media;
                else if (type.equals(ScheduleType.Resource.toString())) obj.Type = ScheduleType.Resource;
                else obj.Type = ScheduleType.All;
            }
        }
        return obj;
    }
}
