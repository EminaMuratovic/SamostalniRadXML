import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * 
 * @author eminamuratovic
 *
 */
public class Article {
	private String title;
	private String content;
	
	/**
	 * creates the article
	 * @param title String title of the article
	 * @param content String content of the article
	 */
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	/**
	 * gets the title of the article
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * gets the content of the article
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * makes the string representation of the article
	 */
	public String toString() {
		String str = "";
		str += "Title: " + title + "\nContent: " + content;
		return str;
	}

}
