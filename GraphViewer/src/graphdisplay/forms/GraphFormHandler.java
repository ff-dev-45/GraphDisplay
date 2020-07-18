package graphdisplay.forms;



import graphdisplay.DiGraph;
import graphdisplay.Graph;
import graphdisplay.GraphModel;
import graphdisplay.NormalGraph;
import graphdisplay.forms.GraphFormElement.GraphType;
import graphdisplay.forms.util.Form;
import graphdisplay.forms.util.FormHandler;

/**
 * GraphFormHandler is a formHandler that reads the data and creates a new graph instance
 * 
 * @author Flynn
 *
 */
public class GraphFormHandler implements FormHandler {
	private GraphModel _model;

	
	/**
	 * Create a GraphFormHandler
	 * @param model the GraphModel this new graph will be added under
	 */
	public GraphFormHandler(GraphModel model) {
		_model = model;
	}
	
	/**
	 * reads the given form data describing the new graph,
	 * A new graph instance will be created and added into the model
	 * using the GraphModel so that events are sent out and listeners are notified
	 * 
	 * @param form containing the edge data
	 */
	@Override
	public void processForm(Form form) {
		try {

			String name = form.getFieldValue(String.class, GraphFormElement.NAME);
			GraphType type = form.getFieldValue(GraphType.class, GraphFormElement.TYPE);
			
			Graph newGraph;
			switch(type) {
				case NormalGraph :
					newGraph = new NormalGraph(name);
					break;
				case DiGraph :
					newGraph = new DiGraph(name);
					break;
				case WeightedGraph :
					newGraph = new NormalGraph(name);//NOT IMPLEMENTED YET
					break;
					
				default :
					newGraph = new NormalGraph(name);
			}
			
		
			_model.addGraph(newGraph, _model.root());
		
		} catch (IllegalArgumentException e) {
			System.err.println(e);//thrown if arguments are incompatible
		} catch (SecurityException e) {
			e.printStackTrace();//thrown if security manager is set and program not permitted to load class at runtime
		}
	}

	}
