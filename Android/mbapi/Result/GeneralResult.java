package mbapi.Result;

import org.w3c.dom.Node;

/**
 * Only get result info from the header
 */
public class GeneralResult extends ResultParser
{
    public GeneralResult(String response)
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

            // Only get result info from the header
            ParseResultInfo(n);
        }
    }
}
