package graphdisplay.forms;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;


import graphdisplay.Graph;
import graphdisplay.Vertex;
import graphdisplay.forms.util.FormElementComponent;
import graphdisplay.forms.util.FormUtility;

/**
 * EdgeFormElement is a FormElement allowing the user to specify the vertices
 * and edge will be between, the data will then be sent off to a handler where it will be processed
 * and the edge will be added into the graph
 * 
 * The GUI uses a JComboBox to select the other vertex the edge will be between and the original
 * vertex the add Edge was selected on
 * @author Flynn
 *
 */

@SuppressWarnings("serial")
public class EdgeFormElement extends FormElementComponent {
	//String key identifier for fields
	public static final String PARENT_ONE = "parent1";
	public static final String PARENT_TWO = "parent2";
	private VertexComboBoxModel _vertices;

	
/**
 * Creates a EdgeFormElement with the fields to store the vertex parents of the new edge
 * @param graph
 * @param vertex
 */
	public EdgeFormElement(Graph graph, Vertex vertex) {
		addField(PARENT_ONE,vertex, graphdisplay.Vertex.class);
		addField(PARENT_TWO,null, graphdisplay.Vertex.class);
		
		_vertices = new VertexComboBoxModel(graph);

		
		setLayout( new GridBagLayout());
		FormUtility formUtility = new FormUtility();
		
		//Create the labels and JComboBox for vertex selection
		final JTextField parent1 = new JTextField(3);
		parent1.setEditable(false);
		
		formUtility.addLabel("Parent 1: ", this);
		formUtility.addLabel(vertex.toString(), this);
		formUtility.addLastField(parent1, this);
		
		
		final JComboBox<Vertex> parent2 = new JComboBox<Vertex>(_vertices);
		formUtility.addLabel("Parent 2: ", this);
		formUtility.addLastField(parent2, this);
		

		if(parent2.getItemCount()==2) {
			if(parent2.getItemAt(0).equals(vertex)) {
				putFieldValue(PARENT_TWO, parent2.getItemAt(1));
			}
			else {
				putFieldValue(PARENT_TWO, parent2.getItemAt(0));
			}
		}
		
		
		Border border = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder( new CompoundBorder(BorderFactory.createTitledBorder("Edge Properties"),border));
		
		//Add listeners to the comboBox for when a selection is made
		parent2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vertex vertex2 = (Vertex) parent2.getSelectedItem();
				putFieldValue(PARENT_TWO, vertex2);
				
			}
			
		});
	}
	
	/**
	 * Helper class for a custom model storing vertex objects in the graph
	 * for a combo box
	 * @author Flynn
	 *
	 */
	private class VertexComboBoxModel extends DefaultComboBoxModel<Vertex>{
		
		public VertexComboBoxModel(Graph graph) {
			List<Vertex> vertices = new ArrayList<Vertex>();
			
			for(int i=0;i<graph.countVertices();i++) {
				vertices.add(graph.vertexAt(i));
			}
			for(Vertex vertex: vertices) {
				addElement(vertex);
			}
		}
	}
}
