package mbapi.RequestBuilder;

import mbapi.Constants.ScheduleType;
import mbapi.Helper.SoapHelper;

public class SiteServiceXML
{
    public static String getXmlGetLocations()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetLocationsRequest><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        sb.append("</ns:Request></ns:GetLocationsRequest></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlGetPrograms(ScheduleType type, boolean onlineOnly)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetPrograms><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        if (type != null) sb.append(String.format("<ns:ScheduleType>%s</ns:ScheduleType>", type.toString()));
        sb.append(String.format("<ns:OnlineOnly>%s</ns:OnlineOnly>", onlineOnly));

        sb.append("</ns:Request></ns:GetPrograms></x:Body></x:Envelope>");
        return sb.toString();
    }

    public static String getXmlGetSessionTypes(boolean onlineOnly)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetSessionTypes><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        sb.append(String.format("<ns:OnlineOnly>%s</ns:OnlineOnly>", onlineOnly));

        sb.append("</ns:Request></ns:GetSessionTypes></x:Body></x:Envelope>");
        return sb.toString();
    }
}
