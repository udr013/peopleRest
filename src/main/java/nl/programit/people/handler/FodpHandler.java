package nl.programit.people.handler;

import java.util.EmptyStackException;
import java.util.Stack;

import nl.programit.people.domain.CustomShape;
import nl.programit.people.domain.Frame;
import nl.programit.people.domain.Node;
import nl.programit.people.domain.Page;
import nl.programit.people.domain.Paragraph;
import nl.programit.people.domain.Presentation;
import nl.programit.people.domain.PresentationNote;
import nl.programit.people.domain.TextBox;
import nl.programit.people.domain.TextLineBreak;
import nl.programit.people.domain.TextList;
import nl.programit.people.domain.TextListItem;
import nl.programit.people.domain.TextSpan;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FodpHandler extends DefaultHandler {

	protected Stack<Node> nodes = new Stack<>();

	private String stringContent = new String();

	public void pp() {
		for (Node node : this.nodes) {
			System.out.println(node.toString());
		}
	}
	
	public Presentation getResult() {
		Presentation result = new Presentation("name of presesntation");		
		for(Node node : nodes) {
			result.addChildNode(node);
		}
		
		return result;
	}

	@Override
	// A start tag is encountered.
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		switch (qName) {
			
			case "draw:page-thumbnail":
				// no op (yet)
				break;

			case "style:master-page":
			case "draw:page":

				Page page = new Page();
				page.setName(attributes.getValue("draw:name"));
				this.nodes.push(page);

				break;

			case "presentation:notes":
				PresentationNote note = new PresentationNote();
				this.nodes.push(note);

				break;

			case "draw:frame":
				Frame frame = new Frame();
				frame.setName(attributes.getValue("draw:name"));
				frame.setPresentationClass(attributes.getValue("presentation:class"));

				this.nodes.push(frame);

				break;
			case "draw:text-box":
				TextBox textBox = new TextBox();

				this.nodes.push(textBox);

				break;
				
			case "text:list":
				
				TextList textList = new TextList();
				
				this.nodes.push(textList);
				
				break;
				
			case "text:list-item":
				TextListItem item = new TextListItem();
				this.nodes.push(item);
				
				break;

			// Create a new Paragraph
			case "text:p":
				Paragraph paragraph = new Paragraph();
				paragraph.setTextStyleName(attributes.getValue("text:style-name"));

				this.nodes.push(paragraph);

				break;

			// Create a new TextSpan
			case "text:span":
				TextSpan span = new TextSpan();
				stringContent = new String();

				this.nodes.push(span);

				break;
				
			case "text:line-break":
				TextLineBreak breaker = new TextLineBreak();
				this.nodes.push(breaker);
				
				break;

			case "draw:custom-shape":
				CustomShape customShape = new CustomShape();
				Node node = this.nodes.peek();
				if(node instanceof Page) {
					Page p = (Page) node;
					customShape.setName(p.getName()+" - Code");
				}
				this.nodes.push(customShape);
				
				break;
				
			default:
//				System.err.println("unknown opening tag: " + qName);

				break;

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {

			case "draw:page":
				// do nothing (yet). Maybe later move content to list
				break;
				
			case "draw:page-thumbnail":
				// no op (yet)
				break;
				
			case "presentation:notes":
			case "draw:frame":
			case "draw:text-box":
			case "text:list":
			case "text:list-item":
			case "draw:custom-shape":
			case "text:p":

				try {
					Node node = this.nodes.pop();
					this.nodes.peek().addChildNode(node);
				}
				catch (EmptyStackException ese) {
					System.out.println(ese);
				}
				
				break;
				
				

			case "text:span":

				TextSpan span = (TextSpan) this.nodes.pop();

				span.setContent(stringContent);

				this.nodes.peek().addChildNode(span);

				break;
				
			case "text:line-break":
				
				TextLineBreak breaker = (TextLineBreak) this.nodes.pop();
				
				this.nodes.peek().addChildNode(breaker);
				
				break;

			default:
//				System.err.println("unknown closing tag: " + qName);

				break;

		}
	}

	// rloman nb: CR/LF worden niet gecontinueerd. Daarom moet hier een += staan
	// en niet =
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		stringContent += String.copyValueOf(ch, start, length);//.trim();
	}
}
