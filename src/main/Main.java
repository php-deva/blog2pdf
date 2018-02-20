import main.crawler.*;
import generator.*;


public class Main
{
	public static void main(String[] args) {
		Crawler crawler = new Crawler();
		Markdown markdown = new Markdown();
		crawler.start();
		if (crawler.hasContent()) {
			markdown.generate();
		}
	}
}