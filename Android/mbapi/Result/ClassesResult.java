package mbapi.Result;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import mbapi.Models.Class;

public class ClassesResult extends ResultParser
{
    public ArrayList<Class> Classes;

    public ClassesResult(String response)
    {
        if (response != null)
        {
            CreateResultDOM(response);
            Parse();
        }
    }

    @Override
    protected void Parse()
    {
        for (int index1 = 0; index1 < ResultDOM.getLength(); index1++)
        {
            Node n = ResultDOM.item(index1);

            ParseResultInfo(n);

            if (n.getNodeName().equals("Classes"))
            {
                Classes = new ArrayList<>();
                NodeList classes = n.getChildNodes();
                for (int index2 = 0; index2 < classes.getLength(); index2++)
                {
                    Classes.add(Class.Parse(classes.item(index2)));
                }
            }

            else if (n.getNodeName().equals("Class"))
            {
                Classes = new ArrayList<>();
                Classes.add(Class.Parse(n));
            }
        }
    }
}
