package mbapi.RequestBuilder;

import java.util.ArrayList;
import java.util.Date;

import mbapi.Helper.SoapHelper;
import mbapi.Helper.Utility;

public class AppointmentServiceXML
{
    public static String getXmlGetBookableItems(Date start, Date end, ArrayList<String> sessionTypeIDs, String locationID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetBookableItems><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        sb.append("<ns:XMLDetail>Basic</ns:XMLDetail>"); // basic is enough for booking and display booking info

        if (start != null) sb.append(String.format("<ns:StartDate>%s</ns:StartDate>", Utility.getStringFromDate(start)));
        if (end != null) sb.append(String.format("<ns:EndDate>%s</ns:EndDate>", Utility.getStringFromDate(end)));

        if (locationID != null) sb.append(String.format("<ns:LocationIDs><ns:int>%s</ns:int></ns:LocationIDs>", locationID));

        if (sessionTypeIDs != null)
        {
            sb.append("<ns:SessionTypeIDs>");
            for (String item: sessionTypeIDs)
            {
                sb.append(String.format("<ns:int>%s</ns:int>", item));
            }
            sb.append("</ns:SessionTypeIDs>");
        }

        sb.append("</ns:Request></ns:GetBookableItems></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlAddOrUpdateAppointments(String locationID, String sessionTypeID, String clientID, String staffID, Date startDateTime, String clientService)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:AddOrUpdateAppointments><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        sb.append("<ns:UpdateAction>AddNew</ns:UpdateAction>");
        sb.append("<ns:Appointments><ns:Appointment>");
        sb.append(String.format("<ns:Location><ns:ID>%s</ns:ID></ns:Location>", locationID));
        sb.append(String.format("<ns:SessionType><ns:ID>%s</ns:ID></ns:SessionType>", sessionTypeID));
        sb.append(String.format("<ns:Staff><ns:ID>%s</ns:ID></ns:Staff>", staffID));
        sb.append(String.format("<ns:Client><ns:ID>%s</ns:ID></ns:Client>", clientID));
        sb.append(String.format("<ns:ClientService><ns:ID>%s</ns:ID></ns:ClientService>", clientService));
        sb.append(String.format("<ns:StartDateTime>%s</ns:StartDateTime>", Utility.getStringFromDate(startDateTime)));
        sb.append("</ns:Appointment></ns:Appointments>");

        sb.append("</ns:Request></ns:AddOrUpdateAppointments></x:Body></x:Envelope>");

        return sb.toString();
    }
}
