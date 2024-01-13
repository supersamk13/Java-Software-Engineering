import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
 * Scans a web page looking for links and images.  
 * Each link that is found is explored by launching another PageScanner
 * thread. Images that are found are displayed in a common
 * PicViewer shared by all threads in the application.
 */
public class PageScanner implements Runnable {

	// The URL of the web page to be scanned
	private String URLString = null;

	// HashSet to keep track of processed pages to avoid duplication
	private static HashSet<String> pagesProcessed = new HashSet<>();
	
	// HashSet to keep track of processed images to avoid duplication
	private static HashSet<String> imagesProcessed = new HashSet<>();

	// Constructor to initialize the URL
	public PageScanner(String urlString) {
		this.URLString = urlString;
	}

	@Override
	public void run() {
		/* If there are already 100 threads, kill this one so that the CPU isn't
		 * overwhelmed.  Leave this here.
		 */
		if(Thread.activeCount() > 100) {
			return;
		}
		
		try {
			// Create a URL object from the specified URLString
			URL url = new URL(URLString);
			
			// Open an input stream from the URL
			InputStream in = url.openStream();
			
			// Create a Scanner to read from the input stream
			Scanner scanner = new Scanner(in);
			
			// Iterate through each line of the web page
			while(scanner.hasNext()) {
				String myLine = scanner.nextLine();
				
				// Extract links from the current line
				ArrayList<String> links = extractLinks(myLine);
				for(String link : links) {
					boolean mySetContains;
					
					// Synchronize access to the HashSet to ensure thread safety
					synchronized(pagesProcessed) {
						mySetContains = pagesProcessed.contains(link);
						
						// If the link is not already processed, add it to the HashSet
						if(!mySetContains) {
							pagesProcessed.add(link);
						}
					}
					
					// If the link is not already processed, create a new thread to scan it
					if(!mySetContains) {
						Thread myThread = new Thread(new PageScanner(link));
						myThread.start();
					}
				}
				
				// Extract image names from the current line
				ArrayList<String> images = extractImageNames(myLine);
				for(String image : images) {
					boolean setContains;
					
					// Synchronize access to the HashSet to ensure thread safety
					synchronized(imagesProcessed) {
						setContains = imagesProcessed.contains(image);
						
						// If the image is not already processed, add it to the HashSet
						if(!setContains) {
							imagesProcessed.add(image);
						}
					}
					
					// If the image is not already processed, display it using PictureViewer
					if(!setContains) {
						PictureViewer.showImage(image);
					}
				}
			}
			
			// Close the scanner
			scanner.close();
			
		} catch(Exception e) {   
			// If any exception occurs, print a message and terminate the thread
			System.out.println("Error occurred: " + e.getMessage());
			return;
		}
	}

	/* A "regular expression" that defines legitimate HTML links */
	private static final Pattern LINK_PATTERN = Pattern.compile("href=(\"|\')([^\"\']+)\\1");

	/* 
	 * Given a line of HTML text, returns a list of links that appear
	 * on that line.  It doesn't always work -- sometimes links are missed,
	 * and sometimes Strings are returned that aren't legitimate links.
	 */
	private ArrayList<String> extractLinks(String line) {
		ArrayList<String> list = new ArrayList<>();
		Matcher matcher = LINK_PATTERN.matcher(line);
		while(matcher.find()) {
			String link = matcher.group(2);
			if (link != null) {
				list.add(link);
			}
		}
		return list;
	}

	/* A "regular expression" that defines legitimate HTML image tags */
	private static final Pattern IMAGE_PATTERN = Pattern.compile("<img\\s+[^>]*?src=(\"|\')([^\"\']+)\\1");

	/* 
	 * Given a line of HTML text, returns names of images that appear
	 * on that line.  It doesn't always work -- sometimes images are missed,
	 * and sometimes Strings are returned that aren't legitimate image names.
	 */
	private ArrayList<String> extractImageNames(String line) {
		ArrayList<String> list = new ArrayList<>();
		Matcher matcher = IMAGE_PATTERN.matcher(line);
		while(matcher.find()) {
			String found = matcher.group(2);
			if (!found.contains("http")) {   // relative URL
				found = URLString + "/" + found;
			}
			list.add(found);
		}
		return list;
	}
}