package nl.carpago.parser.domain;

import nl.carpago.parser.visitor.Visitor;

public abstract class Node {
	
	public abstract void addChildNode(Node node);
	
	public abstract void accept(Visitor visitor);
	
}
