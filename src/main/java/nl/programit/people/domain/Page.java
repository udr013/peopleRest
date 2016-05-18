package nl.carpago.parser.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.carpago.parser.visitor.Visitor;

public class Page extends Node implements Iterable<Node> {

	private String name;
	
	private List <Node> subnodes = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public void addChildNode(Node node) {
		this.subnodes.add(node);
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof Page)) {
			return false;
		}
		Page other = (Page) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		return true;
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
