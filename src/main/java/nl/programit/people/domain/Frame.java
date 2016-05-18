package nl.carpago.parser.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.carpago.parser.visitor.Visitor;

public class Frame extends Node implements Iterable<Node>{

	private String presentationClass;
	private String name;
	
	private List<Node> subnodes = new ArrayList<>();

	public String getPresentationClass() {
		return presentationClass;
	}

	public void setPresentationClass(String presentationClass) {
		this.presentationClass = presentationClass;
	}

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
		result = prime * result + ((presentationClass == null) ? 0 : presentationClass.hashCode());
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
		if (!(obj instanceof Frame)) {
			return false;
		}
		Frame other = (Frame) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		}
		else if (!name.equals(other.name)) {
			return false;
		}
		if (presentationClass == null) {
			if (other.presentationClass != null) {
				return false;
			}
		}
		else if (!presentationClass.equals(other.presentationClass)) {
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