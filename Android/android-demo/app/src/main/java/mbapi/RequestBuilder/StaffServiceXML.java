package mbapi.RequestBuilder;

import mbapi.Helper.SoapHelper;
import java.lang.Integer;

/**
 * Created on 4/13/16.
 */
public class StaffServiceXML
{
    public static String GetStaffXML(String staffID, String locationID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetStaff><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());
        sb.append(SoapHelper.GetUserCredentialsNode());

        if (staffID != null) sb.append(String.format("<ns:StaffIDs><ns:long>%s</ns:long></ns:StaffIDs>", staffID));
        if (locationID != null) sb.append(String.format("<ns:LocationID>%s</ns:LocationID>", locationID));
        sb.append("</ns:Request></ns:GetStaff></x:Body></x:Envelope>");

        return sb.toString();
    }
}
