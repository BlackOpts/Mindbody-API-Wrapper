package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

import mbapi.Helper.Utility;

public class Class
{
    /// Class schedule ID
    public String ClassScheduleID;
/// ID
    public String ID;
    /// Maximum capacity for this class
    public int MaxCapacity;
    /// Online maximum capacity for this class
    public int WebCapacity;
    /// Total signed up
    public int TotalBooked;
    /// Total waitlist signed up
    public int TotalBookedWaitlist;
    /// Total web signed up
    public int WebBooked;
/// Semester ID
    public String SemesterID;
    /// Cancel status
    public Boolean IsCanceled;
    /// Substitute status
    public Boolean Substitute;
    /// Active status
    public Boolean Active;
    /// Waitlist availability status
    public Boolean IsWaitlistAvailable;
    /// Enrollment status
    public Boolean IsEnrolled;
    /// Visibility status after canceled
    public Boolean HideCancel;
    /// Availability status
    public Boolean IsAvailable;
/// Start time (site time)
    public Date StartDateTime;
/// End time (site time)
    public Date EndDateTime;
/// Location that the class takes place
    public Location Location;
/// Description
    public ClassDescription ClassDescription;
/// Staff/Trainer
    public Staff Staff;

    public static Class Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        Class obj = new Class();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("ClassScheduleID") && !node.hasAttributes()) obj.ClassScheduleID = node.getTextContent();
            else if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("SemesterID") && !node.hasAttributes()) obj.SemesterID = node.getTextContent();

            else if (field.equals("MaxCapacity") && !node.hasAttributes()) obj.MaxCapacity = Integer.parseInt(node.getTextContent());
            else if (field.equals("WebCapacity") && !node.hasAttributes()) obj.WebCapacity = Integer.parseInt(node.getTextContent());
            else if (field.equals("TotalBooked") && !node.hasAttributes()) obj.TotalBooked = Integer.parseInt(node.getTextContent());
            else if (field.equals("TotalBookedWaitlist") && !node.hasAttributes()) obj.TotalBookedWaitlist = Integer.parseInt(node.getTextContent());
            else if (field.equals("WebBooked") && !node.hasAttributes()) obj.WebBooked = Integer.parseInt(node.getTextContent());

            else if (field.equals("IsCanceled") && !node.hasAttributes()) obj.IsCanceled = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("Substitute") && !node.hasAttributes()) obj.Substitute = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("Active") && !node.hasAttributes()) obj.Active = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("IsWaitlistAvailable") && !node.hasAttributes()) obj.IsWaitlistAvailable = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("IsEnrolled") && !node.hasAttributes()) obj.IsEnrolled = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("HideCancel") && !node.hasAttributes()) obj.HideCancel = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("IsAvailable") && !node.hasAttributes()) obj.IsAvailable = Boolean.parseBoolean(node.getTextContent());

            else if (field.equals("StartDateTime") && !node.hasAttributes()) obj.StartDateTime = Utility.getDateFromISO(node.getTextContent());
            else if (field.equals("EndDateTime") && !node.hasAttributes()) obj.EndDateTime = Utility.getDateFromISO(node.getTextContent());

            else if (field.equals("Staff") && !node.hasAttributes()) obj.Staff = mbapi.Models.Staff.Parse(node);
            else if (field.equals("Location") && !node.hasAttributes()) obj.Location = mbapi.Models.Location.Parse(node);
            else if (field.equals("ClassDescription") && !node.hasAttributes()) obj.ClassDescription = mbapi.Models.ClassDescription.Parse(node);
        }
        return obj;
    }
}
