package nl.programit.people.handler;


import nl.programit.people.domain.Frame;
import nl.programit.people.domain.Node;
import nl.programit.people.domain.Page;
import nl.programit.people.domain.Paragraph;
import nl.programit.people.domain.PresentationNote;
import nl.programit.people.domain.TextBox;
import nl.programit.people.domain.TextSpan;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class FodpHandlerTest {
	
	private FodpHandler handler;
	
	private final String uri = "";
	private final String localName = "";
	
	@Before
	public void setUp() {
		this.handler = new FodpHandler();
	}
	
	@Test
	public void testStyleMasterPage() {
		String qName ="style:master-page";
		AttributesImpl attr = new AttributesImpl();
		attr.addAttribute("","", "draw:name", "String", "JSON Processing testing data");
		try {
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			
			Assert.assertNotNull(node);
			Assert.assertTrue(node instanceof Page);
			Assert.assertEquals("JSON Processing testing data", ((Page) node).getName());
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testDrawPage() {
		String qName ="draw:page";
		AttributesImpl attr = new AttributesImpl();
		attr.addAttribute("","", "draw:name", "String", "JSON Processing testing data");
		try {
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			
			Assert.assertNotNull(node);
			Assert.assertTrue(node instanceof Page);
			Assert.assertEquals("JSON Processing testing data", ((Page) node).getName());
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testDrawFrame() {
		String qName ="draw:frame";
		AttributesImpl attr = new AttributesImpl();
		attr.addAttribute("","", "draw:name", "String", "JSON Processing testing data");
		attr.addAttribute("","", "presentation:class", "String", "presentationClassContentTitle");
		try {
			Assert.assertTrue(this.handler.nodes.isEmpty());
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			
			Assert.assertNotNull(node);
			Assert.assertTrue(node instanceof Frame);
			Frame frame = (Frame) node;
			Assert.assertEquals("JSON Processing testing data", frame.getName());
			Assert.assertEquals("presentationClassContentTitle", frame.getPresentationClass());
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testPresentationNotes() {
		String qName = "presentation:notes";
		
		AttributesImpl attr = null;
		try {
			Assert.assertTrue(this.handler.nodes.isEmpty());
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			
			Assert.assertNotNull(node);
			
			Assert.assertTrue(node instanceof PresentationNote);
			
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testTextBox() {
		String qName = "draw:text-box";
		
		AttributesImpl attr = null;
		try {
			Assert.assertTrue(this.handler.nodes.isEmpty());
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			Assert.assertTrue(node instanceof TextBox);
			
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testTextParagraph() {
		String qName ="text:p";
		AttributesImpl attr = new AttributesImpl();
		attr.addAttribute("","", "text:style-name", "String", "JSON Processing testing data");
		try {
			Assert.assertTrue(this.handler.nodes.isEmpty());
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			
			Assert.assertTrue(node instanceof Paragraph);
			
			Paragraph paragraph = (Paragraph) node;
			
			Assert.assertEquals("JSON Processing testing data", paragraph.getTextStyleName());
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	@Test
	public void testTextSpan() {
		String qName ="text:span";
		AttributesImpl attr = null;
		try {
			Assert.assertTrue(this.handler.nodes.isEmpty());
			handler.startElement(uri, localName, qName, attr);
			
			Node node = this.handler.nodes.pop();
			
			Assert.assertNotNull(node);
			
			Assert.assertTrue(node instanceof TextSpan);
			
			TextSpan span = (TextSpan) node;
			
			
			Assert.assertNull(span.getContent());
			
			
			
		}
		catch (SAXException e) {
			Assert.fail();
		}
	}
	
	
	

}
