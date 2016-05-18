package nl.programit.people.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.programit.people.visitor.Visitor;

public class Paragraph extends Node implements Iterable <Node>{

	private String textStyleName;
	
	private List <Node> subnodes = new ArrayList<>();
	
	public String getTextStyleName() {
		return textStyleName;
	}

	public void setTextStyleName(String textStyleName) {
		this.textStyleName = textStyleName;
	}

	@Override
	public void addChildNode(Node node) {
		this.subnodes.add(node);
		
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}

	@Override
	public Iterator<Node> iterator() {
		return this.subnodes.iterator();
	}

}
