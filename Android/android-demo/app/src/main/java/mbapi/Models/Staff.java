package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created on 4/15/16.
 */
public class Staff
{
    /// First name
    public String FirstName;
    /// Last name
    public String LastName;
    /// Full name
    public String Name;
    /// Email address
    public String Email;
    /// Mobile phone
    public String MobilePhone;
    /// Home phone
    public String HomePhone;
    /// Work phone
    public String WorkPhone;
    /// Address
    public String Address;
    /// Additional address information
    public String Address2;
    /// City
    public String City;
    /// State
    public String State;
    /// Country
    public String Country;
    /// Postal code / zip code
    public String PostalCode;
    /// Zip code
    public String ForeignZip;
    /// Biographic information about the staff
    public String Bio;
    /// ID (Readonly). This value can be replaced with NewID via the UpdateStaff function
    public String ID;
    /// URL for the staff's image in the profile (Readonly)
    public String ImageURL;
    /// Appointment staff status (Readonly)
    public Boolean AppointmentTrn;
    /// Independent contractor status (Readonly)
    public Boolean IndependentContractor;
    /// Overlap booking status (Readonly)
    public Boolean AlwaysAllowDoubleBooking;
    /// Class teacher status (Readonly)
    public Boolean ReservationTrn;
    /// Male gender status
    public Boolean IsMale;

    public static Staff Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        Staff obj = new Staff();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("FirstName") && !node.hasAttributes()) obj.FirstName = node.getTextContent();
            if (field.equals("LastName") && !node.hasAttributes()) obj.LastName = node.getTextContent();
            if (field.equals("Name") && !node.hasAttributes()) obj.Name = node.getTextContent();
            if (field.equals("Email") && !node.hasAttributes()) obj.Email = node.getTextContent();
            if (field.equals("MobilePhone") && !node.hasAttributes()) obj.MobilePhone = node.getTextContent();
            if (field.equals("HomePhone") && !node.hasAttributes()) obj.HomePhone = node.getTextContent();
            if (field.equals("WorkPhone") && !node.hasAttributes()) obj.WorkPhone = node.getTextContent();
            if (field.equals("Address") && !node.hasAttributes()) obj.Address = node.getTextContent();
            if (field.equals("Address2") && !node.hasAttributes()) obj.Address2 = node.getTextContent();
            if (field.equals("City") && !node.hasAttributes()) obj.City = node.getTextContent();
            if (field.equals("State") && !node.hasAttributes()) obj.State = node.getTextContent();
            if (field.equals("Country") && !node.hasAttributes()) obj.Country = node.getTextContent();
            if (field.equals("PostalCode") && !node.hasAttributes()) obj.PostalCode = node.getTextContent();
            if (field.equals("ForeignZip") && !node.hasAttributes()) obj.ForeignZip = node.getTextContent();
            if (field.equals("Bio") && !node.hasAttributes()) obj.Bio = node.getTextContent();
            if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            if (field.equals("ImageURL") && !node.hasAttributes()) obj.ImageURL = node.getTextContent();
            if (field.equals("AppointmentTrn") && !node.hasAttributes()) obj.AppointmentTrn = Boolean.parseBoolean(node.getTextContent());
            if (field.equals("IndependentContractor") && !node.hasAttributes()) obj.IndependentContractor = Boolean.parseBoolean(node.getTextContent());
            if (field.equals("AlwaysAllowDoubleBooking") && !node.hasAttributes()) obj.AlwaysAllowDoubleBooking = Boolean.parseBoolean(node.getTextContent());
            if (field.equals("ReservationTrn") && !node.hasAttributes()) obj.ReservationTrn = Boolean.parseBoolean(node.getTextContent());
            if (field.equals("IsMale") && !node.hasAttributes()) obj.IsMale = Boolean.parseBoolean(node.getTextContent());
        }
        return obj;
    }
}
