package graphdisplay.forms;


import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import graphdisplay.forms.util.FormElementComponent;
import graphdisplay.forms.util.FormUtility;

/**
 * GraphFormElement is a FormElement allowing the user to specify the type of graph they would like to create
 * the data will then be sent off to a handler where it will be processed
 * and the edge graph will be created in the graphDisplay
 * 
 * This uses radio buttons to select the type of graph to make
 * @author Flynn
 *
 */

@SuppressWarnings("serial")
public class GraphFormElement extends FormElementComponent {
	//String key identifier for fields
	public static final String NAME = "name";
	public static final String TYPE = "type";
	
	//enum used to specify the graph type
	public enum GraphType {NormalGraph, DiGraph, WeightedGraph};
	
	private static final String DEFAULT_NAME = "";


	
/**
 * Creates a GraphFormElement with the fields to store the name and type of new graph
 */
	public GraphFormElement() {
		addField(NAME,DEFAULT_NAME, java.lang.String.class);
		addField(TYPE,null, GraphType.class);
		
		
		setLayout( new GridBagLayout());
		FormUtility formUtility = new FormUtility();
		
	
		final JTextField name = new JTextField(DEFAULT_NAME);
		formUtility.addLabel("Graph Name: ", this);
		formUtility.addLastField(name, this);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton normalGraph = new JRadioButton("Normal graph");

		buttonGroup.add(normalGraph);
		
		JRadioButton diGraph = new JRadioButton("Directed graph");
		
		buttonGroup.add(diGraph);
		
		JRadioButton weightedGraph = new JRadioButton("Weighted graph");
		
		buttonGroup.add(weightedGraph);
			
		formUtility.addLabel("Graph Type: ", this);
		formUtility.addLabel(normalGraph,this);
		formUtility.addLabel(diGraph,this);
		formUtility.addLabel(weightedGraph,this);

		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder( new CompoundBorder(BorderFactory.createTitledBorder("Graph Properties"),border));
		

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

		normalGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				putFieldValue(TYPE, GraphType.NormalGraph);
				
			}
			
		});
		diGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				putFieldValue(TYPE, GraphType.DiGraph);
				
			}
			
		});
		weightedGraph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				putFieldValue(TYPE, GraphType.WeightedGraph);
				
			}
			
		});
	}
}





