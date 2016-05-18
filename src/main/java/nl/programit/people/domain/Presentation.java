package nl.programit.people.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.programit.people.visitor.Visitor;

public class Presentation extends Node implements Iterable<Node> {
	
	private String name;
	
	private List<Node> subNodes = new ArrayList<>();

	public Presentation(String name) {
		this.name = name;
	}

	@Override
	public void addChildNode(Node node) {
		this.subNodes.add(node);
		
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}
	
	@Override
	public Iterator<Node> iterator() {
		return this.subNodes.iterator();
	}

	public String getName() {
		return name;
	}

}
