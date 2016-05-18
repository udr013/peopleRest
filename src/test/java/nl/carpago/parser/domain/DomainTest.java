package nl.carpago.parser.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Ignore;
import org.junit.Test;

public class DomainTest {

	@Test
	@Ignore
	// rloman dit slaat volgens mij nergens op. dit doet niets!
	public void testBeans() {
		EqualsVerifier.forClass(Frame.class);
		EqualsVerifier.forClass(Page.class);
		EqualsVerifier.forClass(Paragraph.class);
		EqualsVerifier.forClass(PresentationNote.class);
		EqualsVerifier.forClass(TextBox.class);
		EqualsVerifier.forClass(TextSpan.class);
	}

	@Test
	@Ignore
	public void testEqualsEquality() {
		EqualsVerifier.forClass(Frame.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();

		EqualsVerifier.forClass(Frame.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
		EqualsVerifier.forClass(Page.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
		EqualsVerifier.forClass(Paragraph.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
		EqualsVerifier.forClass(PresentationNote.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE)				.verify();
		EqualsVerifier.forClass(TextBox.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
		EqualsVerifier.forClass(TextSpan.class).suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
	}

}
