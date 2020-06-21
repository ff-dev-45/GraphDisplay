package graphdisplay.forms;


import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import graphdisplay.forms.util.FormElementComponent;
import graphdisplay.forms.util.FormUtility;

/**
 * VertexFormElement is a formElement allowing the user to specify the field attributes
 * of a new vertex instance (width/height/x_pos,y_pos/name) the data will then be sent to a handler
 * for a new vetex instance to be added into the graph
 * 
 * The GUI users sliders and a text field for allowing user input for required fields
 * @author Flynn
 *
 */
@SuppressWarnings("serial")
public class VertexFormElement extends FormElementComponent {
	//String key identifers for fields
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String X_POS = "xpos";
	public static final String Y_POS = "ypos";
	public static final String NAME = "name";
	
	//default values for the fields of a vertex
	private static final int DEFAULT_WIDTH = 50;
	private static final int DEFAULT_HEIGHT = 30;
	private static final int DEFAULT_X = 0;
	private static final int DEFAULT_Y = 0;
	private static final String DEFAULT_NAME = "";
	
	//min/max values for the fields
	private static final int MIN_WIDTH = 20;
	private static final int MAX_WIDTH = 70;
	private static final int MIN_HEIGHT = 20;
	private static final int MAX_HEIGHT = 70;
	private static final int MIN_X = 0;
	private static final int MAX_X = 7;
	private static final int MIN_Y = 0;
	private static final int MAX_Y = 7;
	
	/**
	 * Create a VertexFormElement with the fields to store Vertex fields
	 */
	public VertexFormElement() {
		addField(WIDTH, DEFAULT_WIDTH, java.lang.Integer.class);
		addField(HEIGHT, DEFAULT_HEIGHT, java.lang.Integer.class);
		addField(X_POS, DEFAULT_X, java.lang.Integer.class);
		addField(Y_POS, DEFAULT_Y, java.lang.Integer.class);
		addField(NAME, DEFAULT_NAME, java.lang.String.class);
		
		setLayout(new GridBagLayout());
		FormUtility formUtility = new FormUtility();
		
		//create the sliders/fields associated labels for GUI
		final JTextField width = new JTextField(3);
		width.setEditable(false);
		final JSlider slideWidth = new JSlider(MIN_WIDTH,MAX_WIDTH);
		formUtility.addLabel("Width: ", this);
		formUtility.addLabel(width, this);
		formUtility.addLastField(slideWidth, this);
		
		final JTextField height = new JTextField(3);
		height.setEditable(false);
		final JSlider slideHeight = new JSlider(MIN_HEIGHT,MAX_HEIGHT);
		formUtility.addLabel("Height: ", this);
		formUtility.addLabel(height, this);
		formUtility.addLastField(slideHeight, this);
		
		final JTextField xPos = new JTextField(3);
		xPos.setEditable(false);
		final JSlider slideXPos = new JSlider(MIN_X,MAX_X);
		formUtility.addLabel("Vertex X position: ", this);
		formUtility.addLabel(xPos, this);
		formUtility.addLastField(slideXPos, this);
		
		final JTextField yPos = new JTextField(3);
		yPos.setEditable(false);
		final JSlider slideYPos = new JSlider(MIN_Y,MAX_Y);
		formUtility.addLabel("Vertex Y position: ", this);
		formUtility.addLabel(yPos, this);
		formUtility.addLastField(slideYPos, this);
		
		final JTextField name = new JTextField(DEFAULT_NAME);
		formUtility.addLabel("Vertex Name: ", this);
		formUtility.addLastField(name, this);
		
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder( new CompoundBorder(BorderFactory.createTitledBorder("Vertex Properties"),border));
		
		//Add listeners that respond to changes of sliders and these changes are stored and update the fields
		slideWidth.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int widthValue = slideWidth.getValue();
				putFieldValue(WIDTH, widthValue);
				width.setText(Integer.toString(widthValue));
			}
			
		});
		
		slideHeight.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int heightValue = slideHeight.getValue();
				putFieldValue(HEIGHT, heightValue);
				height.setText(Integer.toString(heightValue));
			}
			
		});
		
		slideXPos.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int xValue = slideXPos.getValue();
				putFieldValue(X_POS, xValue);
				xPos.setText(Integer.toString(xValue));
			}
			
		});
		
		slideYPos.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int yValue = slideYPos.getValue();
				putFieldValue(Y_POS, yValue);
				yPos.setText(Integer.toString(yValue));
			}
			
		});
		//When text is changed the document listener will update the field value for name
		name.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					putFieldValue(NAME, e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					putFieldValue(NAME, e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
				}	
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					putFieldValue(NAME, e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
				}
				
			}
		});
		//Set the sliders to the default values
		slideWidth.setValue(DEFAULT_WIDTH);
		slideHeight.setValue(DEFAULT_HEIGHT);
		slideXPos.setValue(DEFAULT_X);
		slideYPos.setValue(DEFAULT_Y);
	}
}
