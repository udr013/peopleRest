package nl.carpago.parser.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.carpago.parser.visitor.Visitor;

public class TextListItem extends Node implements Iterable<Node> {
	
	private List<Node> subNodes = new ArrayList<>();

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
	

}
