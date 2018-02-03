import crawler.*;
import generator.*;


public class Main
{
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		Markdown markdown = new Markdown();
		crawler.run();
		if (crawler.hasContent()) {
			markdown.generate();
		}
	}
}