package mbapi.Result;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mbapi.Constants.ErrorCode;

/**
 * Created on 4/29/16.
 */
public abstract class ResultParser
{
    public int ErrorCode;

    public String Status;

    public int ResultCount;

    public boolean Success;

    public String Message;

    protected final String CStatus = "Status";

    protected final String CErrorCode = "ErrorCode";

    protected final String CResultCount = "ResultCount";

    protected NodeList ResultDOM;

    protected abstract void Parse();

    protected void CreateResultDOM(String xml)
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
            doc.getDocumentElement().normalize();

            Node response = doc.getDocumentElement().getChildNodes().item(0).getFirstChild().getFirstChild();
            ResultDOM = response.getChildNodes();
        }
        catch (Exception ex)
        {
            // Invalid xml, or invalid xml response format cought here
        }
    }

    protected void ParseResultInfo(Node n)
    {
        String nodeName = n.getNodeName();
        if (nodeName.equals(CErrorCode)) ErrorCode = Integer.parseInt(n.getTextContent());
        if (nodeName.equals(CStatus)) Status = n.getTextContent();
        if (nodeName.equals(CResultCount)) ResultCount = Integer.parseInt(n.getTextContent());
        if (nodeName.equals("Message")) Message = n.getTextContent();

        Success = (ErrorCode == mbapi.Constants.ErrorCode.Success);
    }
}
