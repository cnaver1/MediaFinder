/*
 * This program is a testing ground for scraping the web with Java.
 */
package webscraper;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author Mitchell Bearry
 */
public class Webscraper 
{
    public void bestBooksEver() throws IOException
    {
        final Document doc = Jsoup.connect("https://www.goodreads.com/list/show/1.Best_Books_Ever").get();
        PrintWriter out = new PrintWriter(new FileWriter("Goodreads' Best Books Ever.txt"));
        
        for (Element row : doc.select("table.tableList.js-dataTooltip tr"))
        {
            out.println(row.select(".bookTitle").text());
            out.println(row.select(".authorName").text());
            out.println(row.select(".minirating").text());
            out.println();
        }
        out.close();
    }
    
    public void iMDBTop250() throws IOException
    {
        final Document doc = Jsoup.connect("http://www.imdb.com/chart/top").get();
        PrintWriter out = new PrintWriter(new FileWriter("IMDB's Top 250.txt"));
        
        for (Element row : doc.select("table.chart.full-width tr"))
        {
            out.println(row.select(".titleColumn").text());
            out.println(row.select(".imdbRating").text());
            out.println();
        }
        out.close();
    }
    
    public void fPSGames() throws IOException
    {
        final Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_first-person_shooters").get();
        int i = 0;
        PrintWriter out = new PrintWriter(new FileWriter("Wikipedia's list of First Person Shooters.txt"));
        
        for(Element row : doc.select("table.wikitable.sortable tbody tr"))
        {
            if(i == 0)
            {
                i++;
                continue;
            }
            
            out.println(row.select("th").text());
            
            for(Element thing : row.select("td"))
            {
                out.println(thing.text());
            }
            out.println();
            i++;
        }
        out.close();
    }
    
    public static void main(String[] args) throws IOException
    {
        Webscraper w = new Webscraper();
        w.bestBooksEver();
        w.iMDBTop250();
        w.fPSGames();
    }
    
}
