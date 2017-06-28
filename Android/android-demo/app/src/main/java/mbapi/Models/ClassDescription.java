package mbapi.Models;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ClassDescription
{
    public String ID;
    public String Name;
    public String Description;
    public String Prereq;
    public String Notes;
    public ServiceCategory ServiceCategory;
    public SessionType SessionType;

    public static ClassDescription Parse(Node n)
    {
        NodeList properties = n.getChildNodes();

        ClassDescription obj = new ClassDescription();
        for (int i = 0; i < properties.getLength(); i++)
        {
            Node node = properties.item(i);
            String field = node.getNodeName();

            if (field.equals("Name") && !node.hasAttributes()) obj.Name = node.getTextContent();
            else if (field.equals("ID") && !node.hasAttributes()) obj.ID = node.getTextContent();
            else if (field.equals("Description") && !node.hasAttributes()) obj.Description = node.getTextContent();
            else if (field.equals("Prereq") && !node.hasAttributes()) obj.Prereq = node.getTextContent();
            else if (field.equals("Notes") && !node.hasAttributes()) obj.Notes = node.getTextContent();

            else if (field.equals("ServiceCategory") && !node.hasAttributes()) obj.ServiceCategory = mbapi.Models.ServiceCategory.Parse(node);
            else if (field.equals("SessionType") && !node.hasAttributes()) obj.SessionType = mbapi.Models.SessionType.Parse(node);
        }
        return obj;
    }
}
