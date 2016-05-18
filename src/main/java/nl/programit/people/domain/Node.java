package nl.programit.people.domain;

import nl.programit.people.visitor.Visitor;

public abstract class Node {
	
	public abstract void addChildNode(Node node);
	
	public abstract void accept(Visitor visitor);
	
}
