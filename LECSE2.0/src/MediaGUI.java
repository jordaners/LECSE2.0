import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

//https://stackoverflow.com/questions/12481698/highlighting-few-of-the-words-of-a-text-file-opened-in-a-frame
/**
 * @author sigrid
 *
 */
public class MediaGUI {
	// An instance of the subclass of the default highlight painter
	static MyHighlightPainter myHighlightPainter = new MyHighlightPainter(Color.yellow);

	public static void main(String[] args) {
		displayText("textFile.txt", "and");
	}
	
	//Display the text file in a JEditorPane
	/**
	 * @param fileName is the name of the file to display
	 * @param search is the searched string to highlight
	 * @ This methods displays the content of a text file and highlights a searched string
	 */
	public static void displayText(String fileName, String search) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				File file = new File(fileName);
				// if file doesn't exists, then create it
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JEditorPane jep = new JEditorPane();
				
				//Can't edit
				jep.setEditable(false);
				jep.setFont(new Font("Segoe UI", Font.PLAIN, 30));
				try {
					jep.setPage(file.toURI().toURL());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				

				//Put the editor pane in a scroll pane.
				JScrollPane editorScrollPane = new JScrollPane(jep);
				editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				editorScrollPane.setPreferredSize(new Dimension(800, 600));
				editorScrollPane.setMinimumSize(new Dimension(10, 10));
			
				//Add the scroll pane to the frame
				frame.add(editorScrollPane);
				frame.pack();
				frame.setVisible(true);
				
				//Highlight the string that was searched
				highlight(jep, search);
			}
		});
	}

	// Creates highlights around all occurrences of pattern in textComp
	/**
	 * @param textComp is JTextComponent
	 * @param pattern is the string to highlight
	 * @ This method creates highlights around all occurrences of pattern in textComp
	 */
	public static void highlight(JTextComponent textComp, String pattern) {
		// First remove all old highlights
		removeHighlights(textComp);

		try {
			Highlighter hilite = textComp.getHighlighter();
			Document doc = textComp.getDocument();
			String text = doc.getText(0, doc.getLength());
			int pos = 0;

			// Search for pattern
			while ((pos = text.indexOf(pattern, pos)) >= 0) {
				// Create highlighter using private painter and apply around pattern
				hilite.addHighlight(pos, pos + pattern.length(), myHighlightPainter);
				pos += pattern.length();
			}
		} catch (BadLocationException e) {
		}
	}

	// Removes only our private highlights
	/**
	 * @param textComp is a JTextComponent
	 * @ This method Removes only highlights from the textComp
	 */
	public static void removeHighlights(JTextComponent textComp) {
		Highlighter hilite = textComp.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();

		for (int i = 0; i < hilites.length; i++) {
			if (hilites[i].getPainter() instanceof MyHighlightPainter) {
				hilite.removeHighlight(hilites[i]);
			}
		}
	}
}
