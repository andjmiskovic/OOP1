package functions;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldCharLimit extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private int limit;

	public JTextFieldCharLimit(int limit) {
		this.limit = limit;
	}

	public void insertString(int offset, String str, javax.swing.text.AttributeSet set) throws BadLocationException {
		if (str == null) {
			return;
		} else if (getLength() + str.length() <= limit) {
			super.insertString(offset, str, set);
		}
	}
}
