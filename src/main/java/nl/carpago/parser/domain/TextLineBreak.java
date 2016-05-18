package nl.carpago.parser.domain;

import nl.carpago.parser.visitor.Visitor;

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
