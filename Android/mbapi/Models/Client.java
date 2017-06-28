package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Date;

import mbapi.Helper.Utility;

/**
 * Created on 4/15/16.
 */
public class Client
{
    /**
     * First name [get, add, update]
     */
    public String FirstName;
    /// Last name [get, add, update]
    public String LastName;
    /// Middle name [get, add, update]
    public String MiddleName;
    /// Email address [get, add, update]
    public String Email;
    /// Mobile phone [get, add, update]
    public String MobilePhone;
    /// Home phone [get, add, update]
    public String HomePhone;
    /// Work phone [get, add, update]
    public String WorkPhone;
    /// Address [get, add, update]
    public String AddressLine1;
    /// City [get, add, update]
    public String City;
    /// State [get, add, update]
    public String State;
    /// Country [get, add, update]
    public String Country;
    /// Postal code / zip code [get, add, update]
    public String PostalCode;
    /// Zip code [get, add, update]
    public String ForeignZip;
    /// Gender [get, add, update]
    public String Gender;
    /// Name of the person in the emergency contact info [get, add, update]
    public String EmergencyContactInfoName;
    /// Relationship to the client in the emergency contact info [get, add, update]
    public String EmergencyContactInfoRelationship;
    /// Phone number of the person in the emergency contact info [get, add, update]
    public String EmergencyContactInfoPhone;
    /// Email of the person in the emergency contact info [get, add, update]
    public String EmergencyContactInfoEmail;
    /// Work extension [get, add, update]
    public String WorkExtension;
    /// Birth date [get, add, update]
    public Date BirthDate;
    /// The date the client is created [get]
    public Date CreationDate;
    /// The date of the first appointment [get]
    public Date FirstAppointmentDate;
    /// Name of the referrer [get, add, update]
    public String ReferredBy;
    /// Warning message [get, add, update]
    public String YellowAlert;
    /// Red alert message [get, add, update]
    public String RedAlert;
    /// Password [add, update]
    public String Username;
    /// Password [add, update]
    public String Password;
    /// Client notes [get, add, update]
    public String Notes;
    /// ID [get, add]
    public String ID;
    /// Member status [get]
    public String Status;
    /// Prefered gender for the appointment trainer [get, add, update]
    public String AppointmentGenderPreference;
    /// Current balance
    public String AccountBalance;
    /// URL for the client's image in the profile [get]
    public String PhotoURL;
    /// Determine if the client is company [get, add, update]
    public Boolean IsCompany;
    /// The bool value indicating if this client has accepted their liability release [get]
    public Boolean LiabilityRelease;
    /// Email opt-in status to the notification [get, add, update]
    public Boolean EmailOptIn;
    // Prospect status [get, add, update]
    public Boolean IsProspect;
    // Company active status. [get]
    public Boolean Inactive;
    /// Home location
    public Location HomeLocation;
/// The client's credit card information.
    //@property (strong, nonatomic) ClientCreditCard *CreditCard;

    public static Client Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        Client obj = new Client();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("FirstName") && !node.hasAttributes()) obj.FirstName = node.getTextContent();
            else if (field.equals("LastName") && !node.hasAttributes()) obj.LastName = node.getTextContent();
            else if (field.equals("MiddleName") && !node.hasAttributes()) obj.MiddleName = node.getTextContent();
            else if (field.equals("Email") && !node.hasAttributes()) obj.Email = node.getTextContent();
            else if (field.equals("MobilePhone") && !node.hasAttributes()) obj.MobilePhone = node.getTextContent();
            else if (field.equals("HomePhone") && !node.hasAttributes()) obj.HomePhone = node.getTextContent();
            else if (field.equals("WorkPhone") && !node.hasAttributes()) obj.WorkPhone = node.getTextContent();
            else if (field.equals("AddressLine1") && !node.hasAttributes()) obj.AddressLine1 = node.getTextContent();
            else if (field.equals("City") && !node.hasAttributes()) obj.City = node.getTextContent();
            else if (field.equals("State") && !node.hasAttributes()) obj.State = node.getTextContent();
            else if (field.equals("Country") && !node.hasAttributes()) obj.Country = node.getTextContent();
            else if (field.equals("PostalCode") && !node.hasAttributes()) obj.PostalCode = node.getTextContent();
            else if (field.equals("ForeignZip") && !node.hasAttributes()) obj.ForeignZip = node.getTextContent();
            else if (field.equals("Notes") && !node.hasAttributes()) obj.Notes = node.getTextContent();
            else if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("Gender") && !node.hasAttributes()) obj.Gender = node.getTextContent();
            else if (field.equals("EmergencyContactInfoEmail") && !node.hasAttributes()) obj.EmergencyContactInfoEmail = node.getTextContent();
            else if (field.equals("EmergencyContactInfoName") && !node.hasAttributes()) obj.EmergencyContactInfoName = node.getTextContent();
            else if (field.equals("EmergencyContactInfoPhone") && !node.hasAttributes()) obj.EmergencyContactInfoPhone = node.getTextContent();
            else if (field.equals("EmergencyContactInfoRelationship") && !node.hasAttributes()) obj.EmergencyContactInfoRelationship = node.getTextContent();
            else if (field.equals("WorkExtension") && !node.hasAttributes()) obj.WorkExtension = node.getTextContent();
            else if (field.equals("WorkPhone") && !node.hasAttributes()) obj.WorkPhone = node.getTextContent();
            else if (field.equals("ReferredBy") && !node.hasAttributes()) obj.ReferredBy = node.getTextContent();
            else if (field.equals("AppointmentGenderPreference") && !node.hasAttributes()) obj.AppointmentGenderPreference = node.getTextContent();
            else if (field.equals("PhotoURL") && !node.hasAttributes()) obj.PhotoURL = node.getTextContent();
            else if (field.equals("RedAlert") && !node.hasAttributes()) obj.RedAlert = node.getTextContent();
            else if (field.equals("YellowAlert") && !node.hasAttributes()) obj.YellowAlert = node.getTextContent();
            else if (field.equals("Status") && !node.hasAttributes()) obj.Status = node.getTextContent();
            else if (field.equals("AccountBalance") && !node.hasAttributes()) obj.AccountBalance = node.getTextContent();
            else if (field.equals("Username") && !node.hasAttributes()) obj.Username = node.getTextContent();

            else if (field.equals("IsCompany") && !node.hasAttributes()) obj.IsCompany = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("LiabilityRelease") && !node.hasAttributes()) obj.LiabilityRelease = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("IsProspect") && !node.hasAttributes()) obj.IsProspect = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("EmailOptIn") && !node.hasAttributes()) obj.EmailOptIn = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("Inactive") && !node.hasAttributes()) obj.Inactive = Boolean.parseBoolean(node.getTextContent());
            else if (field.equals("EmailOptIn") && !node.hasAttributes()) obj.EmailOptIn = Boolean.parseBoolean(node.getTextContent());

            else if (field.equals("CreationDate") && !node.hasAttributes()) obj.CreationDate = Utility.getDateFromISO(node.getTextContent());
            else if (field.equals("BirthDate") && !node.hasAttributes()) obj.BirthDate = Utility.getDateFromISO(node.getTextContent());
            else if (field.equals("FirstAppointmentDate") && !node.hasAttributes()) obj.FirstAppointmentDate = Utility.getDateFromISO(node.getTextContent());

            else if (field.equals("HomeLocation") && !node.hasAttributes()) obj.HomeLocation = Location.Parse(node);
        }
        return obj;
    }
}
