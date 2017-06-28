package mbapi.RequestBuilder;

import mbapi.Constants.ClientField;
import mbapi.Helper.SoapHelper;
import mbapi.Helper.Utility;
import mbapi.Models.Client;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created on 8/23/16.
 */
public class ClientServiceXML
{
    public static String GetClientsXML(String clientID, ArrayList<ClientField> fields)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetClientAccountBalances><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());
        sb.append(SoapHelper.GetUserCredentialsNode());

        if (clientID != null) sb.append(String.format("<ns:ClientIDs><ns:string>%s</ns:string></ns:ClientIDs>", clientID));

        if (fields != null)
        {
            sb.append("<ns:Fields>");
            for (ClientField item: fields)
            {
                sb.append(String.format("<ns:string>Clients.%s</ns:string>", item.toString()));
            }
            sb.append("</ns:Fields>");
        }

        sb.append("<ns:PageSize>20</ns:PageSize>");

        sb.append("</ns:Request></ns:GetClientAccountBalances></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlValidateLogin(String username, String password, ArrayList<ClientField> fields)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:ValidateLogin><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        if (username != null) sb.append(String.format("<ns:Username>%s</ns:Username>", username));
        if (password != null) sb.append(String.format("<ns:Password>%s</ns:Password>", password));

        if (fields != null)
        {
            sb.append("<ns:Fields>");
            for (ClientField item: fields)
            {
                sb.append(String.format("<ns:string>Client.%s</ns:string>", item.toString()));
            }
            sb.append("</ns:Fields>");
        }

        sb.append("</ns:Request></ns:ValidateLogin></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlAddOrUpdateClients(Client client, String clientID)
    {
        Client c = client;
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:AddOrUpdateClients><ns:Request>");

        if (clientID != null) sb.append("<ns:Action>Update</ns:Action>");
        else sb.append("<ns:Action>AddNew</ns:Action>");

        sb.append(SoapHelper.GetSourceCredentialsNode());
        sb.append(SoapHelper.GetUserCredentialsNode());

        sb.append("<ns:Clients><ns:Client>");

        if (clientID != null) sb.append(String.format("<ns:ID>%s</ns:ID>", clientID));

        if (c.FirstName != null) sb.append(String.format("<ns:FirstName>%s</ns:FirstName>", c.FirstName));
        if (c.LastName != null) sb.append(String.format("<ns:LastName>%s</ns:LastName>", c.LastName));
        if (c.BirthDate != null) sb.append(String.format("<ns:BirthDate>%s</ns:BirthDate>", c.BirthDate));
        if (c.EmailOptIn != null) sb.append(String.format("<ns:EmailOptIn>%s</ns:EmailOptIn>", c.EmailOptIn));
        if (c.Email != null) sb.append(String.format("<ns:Email>%s</ns:Email>", c.Email));
        if (c.Username != null) sb.append(String.format("<ns:Username>%s</ns:Username>", c.Username));
        if (c.Password != null) sb.append(String.format("<ns:Password>%s</ns:Password>", c.Password));
        if (c.IsCompany != null) sb.append(String.format("<ns:IsCompany>%s</ns:IsCompany>", c.IsCompany));
        if (c.IsProspect != null) sb.append(String.format("<ns:IsProspect>%s</ns:IsProspect>", c.IsProspect));
        if (c.AppointmentGenderPreference != null) sb.append(String.format("<ns:AppointmentGenderPreference>%s</ns:AppointmentGenderPreference>", c.AppointmentGenderPreference));
        if (c.City != null) sb.append(String.format("<ns:City>%s</ns:City>", c.City));
        if (c.State != null) sb.append(String.format("<ns:State>%s</ns:State>", c.State));
        if (c.Country != null) sb.append(String.format("<ns:Country>%s</ns:Country>", c.Country));
        if (c.AddressLine1 != null) sb.append(String.format("<ns:AddressLine1>%s</ns:AddressLine1>", c.AddressLine1));
        if (c.EmergencyContactInfoEmail != null) sb.append(String.format("<ns:EmergencyContactInfoEmail>%s</ns:EmergencyContactInfoEmail>", c.EmergencyContactInfoEmail));
        if (c.EmergencyContactInfoRelationship != null) sb.append(String.format("<ns:EmergencyContactInfoRelationship>%s</ns:EmergencyContactInfoRelationship>", c.EmergencyContactInfoRelationship));
        if (c.EmergencyContactInfoPhone != null) sb.append(String.format("<ns:EmergencyContactInfoPhone>%s</ns:EmergencyContactInfoPhone>", c.EmergencyContactInfoPhone));
        if (c.EmergencyContactInfoName != null) sb.append(String.format("<ns:EmergencyContactInfoName>%s</ns:EmergencyContactInfoName>", c.EmergencyContactInfoName));
        if (c.MiddleName != null) sb.append(String.format("<ns:MiddleName>%s</ns:MiddleName>", c.MiddleName));
        if (c.MobilePhone != null) sb.append(String.format("<ns:MobilePhone>%s</ns:MobilePhone>", c.MobilePhone));
        if (c.Gender != null) sb.append(String.format("<ns:Gender>%s</ns:Gender>", c.Gender));
        if (c.PostalCode != null) sb.append(String.format("<ns:PostalCode>%s</ns:PostalCode>", c.PostalCode));
        if (c.HomePhone != null) sb.append(String.format("<ns:HomePhone>%s</ns:HomePhone>", c.HomePhone));
        if (c.WorkPhone != null) sb.append(String.format("<ns:WorkPhone>%s</ns:WorkPhone>", c.WorkPhone));
        if (c.WorkExtension != null) sb.append(String.format("<ns:WorkExtension>%s</ns:WorkExtension>", c.WorkExtension));
        if (c.Notes != null) sb.append(String.format("<ns:Notes>%s</ns:Notes>", c.Notes));
        if (c.RedAlert != null) sb.append(String.format("<ns:RedAlert>%s</ns:RedAlert>", c.RedAlert));
        if (c.YellowAlert != null) sb.append(String.format("<ns:YellowAlert>%s</ns:YellowAlert>", c.YellowAlert));
        if (c.ReferredBy != null) sb.append(String.format("<ns:ReferredBy>%s</ns:ReferredBy>", c.ReferredBy));
        sb.append("</ns:Client></ns:Clients>");

        sb.append("</ns:Request></ns:AddOrUpdateClients></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlGetClientServices(String clientID, String sessionTypeID, String classID, ArrayList<String> serviceCategoryIDs)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetClientServices><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());
        sb.append(SoapHelper.GetUserCredentialsNode());

        if (sessionTypeID != null) sb.append(String.format("<ns:SessionTypeIDs><ns:int>%s</ns:int></ns:SessionTypeIDs>", sessionTypeID));
        if (clientID != null) sb.append(String.format("<ns:ClientID>%s</ns:ClientID>", clientID));
        if (classID != null) sb.append(String.format("<ns:ClassID>%s</ns:ClassID>", classID));

        if (serviceCategoryIDs != null)
        {
            sb.append("<ns:ProgramIDs>");
            for (String item: serviceCategoryIDs)
            {
                sb.append(String.format("<ns:int>%s</ns:int>", item));
            }
            sb.append("</ns:ProgramIDs>");
        }

        sb.append("</ns:Request></ns:GetClientServices></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlGetClientSchedule(String clientID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetClientSchedule><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());
        sb.append(SoapHelper.GetSourceCredentialsNode());

        sb.append(String.format("<ns:StartDate>%s</ns:StartDate>", Utility.getStringFromDate(new Date())));
        sb.append("<ns:EndDate>2030-01-01T00:00:00</ns:EndDate>");

        if (clientID != null) sb.append(String.format("<ns:ClientID>%s</ns:ClientID>", clientID));
        sb.append("</ns:Request></ns:GetClientSchedule></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlGetClientPastSchedule(String clientID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetClientSchedule><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());
        sb.append(SoapHelper.GetSourceCredentialsNode());

        sb.append(String.format("<ns:EndDate>%s</ns:EndDate>", Utility.getStringFromDate(new Date())));
        sb.append("<ns:StartDate>2010-01-01T00:00:00</ns:StartDate>");

        if (clientID != null) sb.append(String.format("<ns:ClientID>%s</ns:ClientID>", clientID));
        sb.append("</ns:Request></ns:GetClientSchedule></x:Body></x:Envelope>");

        return sb.toString();
    }
}