import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class takes the xml file from the site www.klix.ba 
 * It also prints all of the titles from the site and the user chooses which article he wants to read.
 * @author eminamuratovic
 *
 */
public class ArticleReader {

	/**
	 * adds the current article casted in Element into the list of articles
	 * @param articles LinkedList<Article> list of articles
	 * @param current Element current article casted in Element
	 */
	public static void add(LinkedList<Article> articles, Element current) {
		NodeList titleNode = ((Element) current).getElementsByTagName("title");
		NodeList contentNode = ((Element) current)
				.getElementsByTagName("clanak");
		for (int i = 0; i < titleNode.getLength(); i++) {
			Node currentT = titleNode.item(i);
			Node currentC = contentNode.item(i);
			if (currentT instanceof Element && currentC instanceof Element) {
				Element titleE = (Element) currentT;
				Element contentE = (Element) currentC;
				String title = titleE.getTextContent();
				String content = contentE.getTextContent();
				articles.add(new Article(title, content));
			}
		}

	}

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilder docReader = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document xmldoc = docReader.parse(new URL(
				"http://www.klix.ba/rss/svevijesti").openStream());
		NodeList xmlArticles = xmldoc.getElementsByTagName("item");
		LinkedList<Article> articles = new LinkedList<Article>();
		LinkedList<Element> items = new LinkedList<Element>();
		for (int i = 0; i < xmlArticles.getLength(); i++) {
			Node current = xmlArticles.item(i);
			if (current instanceof Element) {
				Element currentEl = (Element) current;
				items.add(currentEl);
				if (current.hasChildNodes()) {
					add(articles, currentEl);
				}
				String title = currentEl.getAttribute("title");
				String content = currentEl.getAttribute("clanak");
				Article currentArticle = new Article(title, content);
				articles.add(currentArticle);
			}
		}

		Iterator<Article> iterator = articles.iterator();
		int i = 1;
		while (iterator.hasNext()) {
			System.out.println(i + ". " + iterator.next().getTitle());
			i++;
		}
		System.out.println("Choose the number of the title: ");
		int n = TextIO.getlnInt();
		System.out.println(articles.get(n).toString());
	}

}
