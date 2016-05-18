package nl.programit.people.visitor;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import nl.programit.people.domain.CustomShape;
import nl.programit.people.domain.Frame;
import nl.programit.people.domain.Node;
import nl.programit.people.domain.Page;
import nl.programit.people.domain.Paragraph;
import nl.programit.people.domain.Presentation;
import nl.programit.people.domain.PresentationNote;
import nl.programit.people.domain.TextBox;
import nl.programit.people.domain.TextLineBreak;
import nl.programit.people.domain.TextList;
import nl.programit.people.domain.TextListItem;
import nl.programit.people.domain.TextSpan;

public class AsciidocGeneratingVisitor extends Visitor {

	private boolean renderPresentationNotes;
	private String fileName;
	
	private String result;

	public AsciidocGeneratingVisitor(String fileName, boolean renderPresentationNotes) {
		this.renderPresentationNotes = renderPresentationNotes;
		this.fileName = fileName;
	}

	@Override
	public void visit(Presentation presentation) {

		// save current string (console) for resetting later
		PrintStream console = System.out;

		// set to byte out
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));
		
		// do the job
		System.out.println("= "+fileName);
		System.out.println(":pagenums:");
		System.out.println(":revealjs_slideNumber: true");
		System.out.println();
		System.out.println(":width: 100%");
		System.out.println(":height: 100%");
		
		System.out.println();
		
		for (Node node : presentation) {
			node.accept(this);
		}
		
		String result = baos.toString();
		
		result = result.replaceAll(".*\n\\.?<number>", "");
		
		try {
			FileOutputStream outputStream = new FileOutputStream("src/main/resources/output.adoc");
			PrintStream ps = new PrintStream(outputStream);
			System.setOut(ps);
			
			System.out.println(result);

			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			System.setOut(console);
	//		System.out.println("Done");
			
//			System.out.println(result);
			this.result = result;
		}

	}

	@Override
	public void visit(Page page) {
		// set to byte out
		PrintStream current = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));

		System.out.println();
		System.out.printf("== %s%n", page.getName());
		System.out.print(".");
		for (Node node : page) {
			node.accept(this);
		}

		String result = baos.toString();
		result = result.replaceAll("\\.\\*", "*");

		System.setOut(current);
		System.out.println(result);
	}

	@Override
	public void visit(Frame frame) {
		// System.out.printf("=== %s%n", frame.getName());
		for (Node node : frame) {
			node.accept(this);
		}
	}

	@Override
	public void visit(PresentationNote presentationNote) {
		if (this.renderPresentationNotes) {
			System.out.println();
			System.out.println("=== Explanation");
//			System.out.print(".");
			for (Node node : presentationNote) {
				node.accept(this);
			}
		}
	}

	@Override
	public void visit(Paragraph paragraph) {
		// System.out.printf(".Paragraph %n");
		for (Node node : paragraph) {
			node.accept(this);
		}
		System.out.println();
	}

	@Override
	public void visit(TextBox textBox) {
		// System.out.println(".TextBox");
		for (Node node : textBox) {
			node.accept(this);
		}
	}

	@Override
	public void visit(TextSpan textSpan) {
		for (Node node : textSpan) { // for line break for now.
			node.accept(this);
		}
		String content = textSpan.getContent();
		if (content != null && !content.isEmpty()) {
			System.out.print(content);
		}
	}

	// rloman hier eerst een String maken door postorder te lopen door de loop
	public void visit(TextList textList) {
		// set to byte out
		PrintStream current = System.out;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));

		for (Node node : textList) {
			System.out.print("* ");
			node.accept(this);
		}
		// System.out.print(" ");
		String result = baos.toString();
		result = result.replaceAll("\\*\\s\\*", "**");

		System.setOut(current);
		System.out.println(result);
	}

	// rloman nog regelen dat de twee sterretjes niet direct worden gevolgd door
	// tekst maar eerst een spatie. van later zorg ?
	@Override
	public void visit(TextListItem textListItem) {

		for (Node node : textListItem) {
			node.accept(this);
		}

	}

	@Override
	public void visit(TextLineBreak textLineBreak) {
		System.out.println();
	}

	// mostly this is a code snippet
	@Override
	public void visit(CustomShape customShape) {
		System.out.println("== "+customShape.getName());
		System.out.println("[source,java]");
		System.out.println("----");
		for(Node node : customShape) {
			node.accept(this);
		}
		System.out.println("----");
		System.out.println();
		
	}
	
	public String getResult() {
		return this.result;
	}

}
