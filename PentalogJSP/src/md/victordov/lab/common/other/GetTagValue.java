package md.victordov.lab.common.other;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class GetTagValue {
	
	public GetTagValue(){
		
	}
	
	public static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}

}
