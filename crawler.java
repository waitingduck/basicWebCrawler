package htmlUnit_crawler_test1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class crawler {
	@Test
	public void homePage() throws Exception {
	    //final WebClient webClient = new WebClient();
	    try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
	        final HtmlPage page = webClient.getPage("http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/");
	        Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

	        final String pageAsXml = page.asXml();
	        Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	        final String pageAsText = page.asText();
	        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
	    }
	}
	@Test
	public void homePage_Firefox() throws Exception {
	    try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38)) {
	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	        Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());
	    }
	}
	@Test
	public void getElements() throws Exception {
//	    try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
//	        final HtmlPage page = webClient.getPage("http://www.mfa.org/node/9638");
//	        
//	        //final HtmlDivision div = page.getHtmlElementById("banner");
////	        final List<DomElement> spans = page.getElementsByTagName("a");
////	        for (DomElement element : spans) {
////	            
////	                System.out.println(element.getNodeValue());
////	            
////	        }
//	        //final HtmlAnchor anchor = page.getAnchorByName("anchor_name");
//	    }
	}
	@Test
	public void xpath() throws Exception {
	    try (final WebClient webClient = new WebClient()) {
	        final HtmlPage page = webClient.getPage("http://www.mfa.org/node/9638");

	        //get list of all divs
	        final List<?> divs = page.getByXPath("//div[@class='slide-title']");
	        for(Object x:divs){
//	        	DBConnection DB = DBConnection();
	    		Connection con = DBConnection.connect();
	    		Statement statement = con.createStatement();
	    	      statement.setQueryTimeout(30);  // set timeout to 30 sec.

	    	      
	    	      
	    	      
	        		DomElement y = ((DomElement)x);
	        		
	        		DomElement articleName = (DomElement)(y.getByXPath(".//h2").get(0));
	        		//System.out.println("Article name: " + articleName.getTextContent());
	        		String article = articleName.getTextContent();
	        		
	        		DomElement brief = (DomElement)(y.getByXPath(".//p").get(0));
	        		
	        		//System.out.println("Country: "+brief.getChildNodes().get(0).getTextContent());
	        		String country = brief.getChildNodes().get(0).getTextContent();
	        		//System.out.println("Era: "+brief.getChildNodes().get(2).getTextContent());
	        		String era = brief.getChildNodes().get(2).getTextContent();
	        		//System.out.println("Year: "+brief.getChildNodes().get(4).getTextContent());
	        		String year = brief.getChildNodes().get(4).getTextContent();
	        		//System.out.println("Artist: "+brief.getChildNodes().get(6).getTextContent());
	        		String artist = brief.getChildNodes().get(6).getTextContent();
	        		String artist_year = null;
	        		if(brief.getChildNodes().size()>7){
	        			//System.out.println("Artist's year: "+brief.getChildNodes().get(7).getTextContent());
	        			artist_year = brief.getChildNodes().get(7).getTextContent();
	        		}
	        		String q = "insert into art values(\'"+article.replaceAll("'", "''")+"\',\'"+country.replaceAll("'", "''")+"\',\'"+era.replaceAll("'", "''")+"\',\'"+year.replaceAll("'", "''")+"\',\'"+artist.replaceAll("'", "''")+"\',\'"+artist_year+"\')";
	        		System.out.println(q);
	        		statement.executeUpdate(q);
	        		
	        		System.out.println();
	        	
	        	
	        }
	        //get div which has a 'name' attribute of 'John'
	        //final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
	    }
	}
//	@Test
//	public void homePage_proxy() throws Exception {
//	    try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38, "myproxyserver", myProxyPort)) {
//
//	        //set proxy username and password 
//	        final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
//	        credentialsProvider.addCredentials("username", "password");
//
//	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
//	        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
//	    }
//	}
//	@Test
//	public void submittingForm() throws Exception {
//	    try (final WebClient webClient = new WebClient()) {
//
//	        // Get the first page
//	        final HtmlPage page1 = webClient.getPage("http://some_url");
//
//	        // Get the form that we are dealing with and within that form, 
//	        // find the submit button and the field that we want to change.
//	        final HtmlForm form = page1.getFormByName("myform");
//
//	        final HtmlSubmitInput button = form.getInputByName("submitbutton");
//	        final HtmlTextInput textField = form.getInputByName("userid");
//
//	        // Change the value of the text field
//	        textField.setValueAttribute("root");
//
//	        // Now submit the form by clicking the button and get back the second page.
//	        final HtmlPage page2 = button.click();
//	    }
//	}
}
