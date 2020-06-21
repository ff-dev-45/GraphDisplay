package graphdisplay.forms;


import graphdisplay.Graph;
import graphdisplay.GraphModel;
import graphdisplay.Vertex;
import graphdisplay.forms.util.Form;
import graphdisplay.forms.util.FormHandler;

/**
 * EdgeFormHandler is a formHandler that reads the data and creates a new edge instance
 * 
 * @author Flynn
 *
 */
public class EdgeFormHandler implements FormHandler {
	private GraphModel _model;
	private Graph _graph;
	
	/**
	 * Create a EdgeFormHandler
	 * @param model the GraphModel this new edge will be added under
	 * @param graph the graph this new edge will be added into
	 */
	public EdgeFormHandler(GraphModel model, Graph graph) {
		_model = model;
		_graph = graph;
	}
	
	/**
	 * reads the given form data describing the new edge,
	 * A new edge instance will be created and added into the supplied graph
	 * using the GraphModel so that events are sent out and listeners are notified
	 * 
	 * @param form containing the edge data
	 */
	@Override
	public void processForm(Form form) {
		try {

			Vertex parent1 = form.getFieldValue(Vertex.class, EdgeFormElement.PARENT_ONE);
			Vertex parent2 = form.getFieldValue(Vertex.class, EdgeFormElement.PARENT_TWO);
			
			_model.addEdge(_graph, parent1, parent2);
		} catch (IllegalArgumentException e) {
			System.err.println(e);//thrown if arguments are incompatible
		} catch (SecurityException e) {
			e.printStackTrace();//thrown if security manager is set and program not permitted to load class at runtime
		}
	}

	}
