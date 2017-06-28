package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created on 8/24/16.
 */
public class Location 
{
    /// The business ID of this location. This field is the unique ID for locations that do NOT have an associated site
    public String BusinessID;
    /// The site ID of this location
    public String SiteID;
    /// The description of this business
    public String BusinessDescription;
    /// Size in square feet of the location
    public int FacilitySquareFeet;
    /// Number of treatment rooms at location
    public int TreatmentRooms;
    /// If the location has classes
    public Boolean HasClasses;
    /// Location phone extension
    public String PhoneExtension;
    /// The unique ID for the location
    public String ID;
    /// The name of the location
    public String Name;
    /// Address of the loaction
    public String Address;
    /// Additional address info
    public String Address2;
    /// Tax rate
    public double Tax1;
    /// Additional tax rate
    public double Tax2;
    /// Additional tax rate
    public double Tax3;
    /// Additional tax rate
    public double Tax4;
    /// Additional tax rate
    public double Tax5;
    /// The phone number of the location
    public String Phone;
    /// The city of the location
    public String City;
    /// The state / province of the location
    public String StateProvCode;
    /// The postal code of the location
    public String PostalCode;
    /// The Latitude of the location
    public double Latitude;
    /// The longitude of the location
    public double Longitude;
    /// Location's distance in miles
    public double DistanceInMiles;
    /// The image URL of the location
    public String ImageURL;
    /// The description of the location
    public String Description;
    /// Whether the location has an MB site that is available to consumers
    public Boolean HasSite;
    /// Whether the location can have classes reserved / appointments booked
    public Boolean CanBook;

    public static Location Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        Location obj = new Location();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("Address") && !node.hasAttributes()) obj.Address = node.getTextContent();
            else if (field.equals("Address2") && !node.hasAttributes()) obj.Address2 = node.getTextContent();
            else if (field.equals("BusinessDescription") && !node.hasAttributes()) obj.BusinessDescription = node.getTextContent();
            else if (field.equals("BusinessID") && !node.hasAttributes()) obj.BusinessID = node.getTextContent();
            else if (field.equals("CanBook") && !node.hasAttributes()) obj.CanBook = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("City") && !node.hasAttributes()) obj.City = node.getTextContent();
            else if (field.equals("Description") && !node.hasAttributes()) obj.Description = node.getTextContent();
            else if (field.equals("DistanceInMiles") && !node.hasAttributes()) obj.DistanceInMiles = Double.parseDouble(node.getTextContent());
            else if (field.equals("FacilitySquareFeet") && !node.hasAttributes()) obj.FacilitySquareFeet = Integer.parseInt(node.getTextContent());
            else if (field.equals("HasClasses") && !node.hasAttributes()) obj.HasClasses = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("HasSite") && !node.hasAttributes()) obj.HasSite = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("SiteID") && !node.hasAttributes()) obj.SiteID = node.getTextContent();
            else if (field.equals("ImageURL") && !node.hasAttributes()) obj.ImageURL = node.getTextContent();
            else if (field.equals("Latitude") && !node.hasAttributes()) obj.Latitude = Double.parseDouble(node.getTextContent());
            else if (field.equals("Longitude") && !node.hasAttributes()) obj.Longitude = Double.parseDouble(node.getTextContent());
            else if (field.equals("Name") && !node.hasAttributes()) obj.Name = node.getTextContent();
            else if (field.equals("Phone") && !node.hasAttributes()) obj.Phone = node.getTextContent();
            else if (field.equals("PhoneExtension") && !node.hasAttributes()) obj.PhoneExtension = node.getTextContent();
            else if (field.equals("PostalCode") && !node.hasAttributes()) obj.PostalCode = node.getTextContent();
            else if (field.equals("WorkExtension") && !node.hasAttributes()) obj.StateProvCode = node.getTextContent();
            else if (field.equals("Tax1") && !node.hasAttributes()) obj.Tax1 = Double.parseDouble(node.getTextContent());
            else if (field.equals("Tax2") && !node.hasAttributes()) obj.Tax2 = Double.parseDouble(node.getTextContent());
            else if (field.equals("Tax3") && !node.hasAttributes()) obj.Tax3 = Double.parseDouble(node.getTextContent());
            else if (field.equals("Tax4") && !node.hasAttributes()) obj.Tax4 = Double.parseDouble(node.getTextContent());
            else if (field.equals("Tax5") && !node.hasAttributes()) obj.Tax5 = Double.parseDouble(node.getTextContent());
            else if (field.equals("TreatmentRooms") && !node.hasAttributes()) obj.TreatmentRooms = Integer.parseInt(node.getTextContent());
        }
        return obj;
    }
}
