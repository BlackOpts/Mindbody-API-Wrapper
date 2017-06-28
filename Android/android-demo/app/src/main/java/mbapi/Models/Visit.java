package mbapi.Models;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

import mbapi.Constants.ScheduleType;
import mbapi.Helper.Utility;

public class Visit
{
    public String Name;
    public String ClassID;
    public String AppointmentID;
    public Date StartDateTime;
    public Date EndDateTime;
    public String ID;
    public Staff Staff;
    public Location Location;

    public ScheduleType getType()
    {
        if (ClassID != null) return ScheduleType.DropIn;
        if (AppointmentID != null) return ScheduleType.Appointment;
        return ScheduleType.All;
    }

    public static Visit Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        Visit obj = new Visit();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("ClassID") && !node.hasAttributes()) obj.ClassID = node.getTextContent();
            else if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("AppointmentID") && !node.hasAttributes()) obj.AppointmentID = node.getTextContent();
            else if (field.equals("Name") && !node.hasAttributes()) obj.Name = node.getTextContent();

            else if (field.equals("StartDateTime") && !node.hasAttributes()) obj.StartDateTime = Utility.getDateFromISO(node.getTextContent());
            else if (field.equals("EndDateTime") && !node.hasAttributes()) obj.EndDateTime = Utility.getDateFromISO(node.getTextContent());

            else if (field.equals("Staff") && !node.hasAttributes()) obj.Staff = mbapi.Models.Staff.Parse(node);
            else if (field.equals("Location") && !node.hasAttributes()) obj.Location = mbapi.Models.Location.Parse(node);

        }
        return obj;
    }
}
