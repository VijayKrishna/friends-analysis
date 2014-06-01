package self.vpalepu.friends;

import static com.google.common.base.Preconditions.*;

import java.util.*;
import java.net.*;
import java.io.*;

import org.jsoup.Jsoup;

/**
 * simple script for extracting data from the raw html sources. The data
 * is transformed into text files using the JSoup parsing library.<br/> <br/>
 * 
 * This class is meant to be a standalone script and not part of any library.
 * Thus, it is not meant to be extended and hence is declared as final.
 * @author vijay
 *
 */
public final class RawData {
  
  final File htmlDataStore;
  final File textDataStore;
  
  /**
   * initializes the Raw Data as a composite of a data store for the source
   * html and the text files that contain the parsed text.
   * @param htmlDataStore - source of the raw data
   * @param textDataStore - destination of the prased data after main method 
   * is executed.
   * @return - a single instance of the class RawData. this instance can be
   * used to run the scrpit by calling the main or extractFromHtmlStore method.
   */
  public static RawData init(File htmlDataStore, File textDataStore) {
    checkState(htmlDataStore.isDirectory());
    checkState(textDataStore.isDirectory());
    RawData data = new RawData(htmlDataStore, textDataStore);
    return data;
  }
  
    private RawData(File htmlDataStore, File textDataStore) {
      this.htmlDataStore = htmlDataStore;
      this.textDataStore = textDataStore;
    }
  
  public void extractFileContents(Scanner source, PrintStream destination) 
      throws IOException {
    String line;
    int count = 0;
    while (source.hasNextLine()) {
      line = source.nextLine();
      count += 1;
      String text = Jsoup.parse(line).text();
      if(text.equals("")) continue;
      destination.append(text + "\n");
    }
    System.out.println(count);
  }
  
  private void extractFromHtmlStore()
      throws FileNotFoundException, IOException {
    final String textDataRoot = this.textDataStore.getAbsolutePath();
    File[] htmlFiles = this.htmlDataStore.listFiles();
    for(File htmlFile : htmlFiles) {
      Scanner source = new Scanner(htmlFile);
      
      String name = htmlFile.getName() + ".txt";
      PrintStream destination = new PrintStream(new File(textDataRoot + name));
      extractFileContents(source, destination);
    }
  }
  
	public static void main(String[] args) 
	    throws MalformedURLException, IOException {
	  if(args == null || args.length == 0) {
	    args = new String[2];
	    args[0] = "/home/vijay/Misc_Programming/lang/freinds_analysis/data/text/";
	    args[1] = "/home/vijay/Misc_Programming/lang/freinds_analysis/data/html/";
	  }
	  
	  File htmlStore = new File(args[0]);
	  File textStore = new File(args[1]);
	  
		RawData.init(htmlStore, textStore).extractFromHtmlStore();	
	}

  

	
}