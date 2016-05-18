package nl.programit.people.domain;

import nl.programit.people.visitor.Visitor;

public class TextLineBreak extends Node {
	

	@Override
	public void addChildNode(Node node) {
		//no op
		
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
