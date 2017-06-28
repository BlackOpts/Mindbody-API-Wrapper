package mbapi.RequestBuilder;

import java.util.Date;

import mbapi.Helper.SoapHelper;
import mbapi.Helper.Utility;

public class ClassServiceXML
{
    public static String getXmlGetClasses(String classID, String serviceCategoryID, String classDescriptionID, boolean includeResource, Date start, Date end, String locationID)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:GetClasses><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        if (classID != null) sb.append(String.format("<ns:ClassIDs><ns:int>%s</ns:int></ns:ClassIDs>", classID));
        if (serviceCategoryID != null) sb.append(String.format("<ns:ProgramIDs><ns:int>%s</ns:int></ns:ProgramIDs>", serviceCategoryID));
        if (classDescriptionID != null) sb.append(String.format("<ns:ClassDescriptionIDs><ns:int>%s</ns:int></ns:ClassDescriptionIDs>", classDescriptionID));

        if (start != null) sb.append(String.format("<ns:StartDateTime>%s</ns:StartDateTime>", Utility.getStringFromDate(start)));
        if (end != null) sb.append(String.format("<ns:EndDateTime>%s</ns:EndDateTime>", Utility.getStringFromDate(end)));

        if (locationID != null) sb.append(String.format("<ns:LocationIDs><ns:int>%s</ns:int></ns:LocationIDs>", locationID));

        if (includeResource) sb.append("<ns:Fields><ns:string>Class.Resource</ns:string></ns:Fields>");

        sb.append("<ns:HideCanceledClasses>true</ns:HideCanceledClasses>");
        sb.append("<ns:SchedulingWindow>true</ns:SchedulingWindow>");

        sb.append("</ns:Request></ns:GetClasses></x:Body></x:Envelope>");

        return sb.toString();
    }

    public static String getXmlAddClientsToClasses(String classID, String clientID, String serviceID, Boolean sendEmail)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<x:Envelope xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://clients.mindbodyonline.com/api/0_5\">");
        sb.append("<x:Header/><x:Body><ns:AddClientsToClasses><ns:Request>");

        sb.append(SoapHelper.GetSourceCredentialsNode());

        if (classID != null) sb.append(String.format("<ns:ClassIDs><ns:int>%s</ns:int></ns:ClassIDs>", classID));
        if (clientID != null) sb.append(String.format("<ns:ClientIDs><ns:string>%s</ns:string></ns:ClientIDs>", clientID));
        if (serviceID != null) sb.append(String.format("<ns:ClientServiceID>%s</ns:ClientServiceID>", serviceID));
        if (sendEmail != null) sb.append(String.format("<ns:SendEmail>%s</ns:SendEmail>", sendEmail.toString()));


        sb.append("</ns:Request></ns:AddClientsToClasses></x:Body></x:Envelope>");

        return sb.toString();
    }
}
