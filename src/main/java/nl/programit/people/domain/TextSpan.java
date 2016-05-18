package nl.carpago.parser.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.carpago.parser.visitor.Visitor;

public class TextSpan extends Node implements Iterable<Node> {
	
	private String content;
	
	private List<Node> subNodes = new ArrayList<>(); // in fact for the line breaker child nodes.

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "TextSpan [content=" + content + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TextSpan)) {
			return false;
		}
		TextSpan other = (TextSpan) obj;
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		}
		else if (!content.equals(other.content)) {
			return false;
		}
		return true;
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
}
