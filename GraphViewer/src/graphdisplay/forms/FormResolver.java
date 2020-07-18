package graphdisplay.forms;

import graphdisplay.Edge;
import graphdisplay.Graph;
import graphdisplay.GraphModel;
import graphdisplay.NormalGraph;
import graphdisplay.Vertex;
import graphdisplay.forms.util.FormComponent;
import graphdisplay.forms.util.FormHandler;

/**
 * Used in determining which form/formHandler will be required by the given requests
 * @author Flynn
 *
 */
public class FormResolver {
	
	/**
	 * Returns a FormComponent for a new Graph creation GraphFormElement
	 * @return the new GraphForm element
	 */
	public static FormComponent getForm() {
		FormComponent form = new FormComponent();
		
		form.addFormElement(new GraphFormElement());
		
		return form;
	}
	
	/**
	 * Returns the FormComponent for a Vertex creation, in which data can be entered
	 * for the specified vertex to be created
	 * @param vertex the class of the instance to be created
	 * @return
	 */
	public static FormComponent getForm(Class<Vertex> vertex) {
		FormComponent form = new FormComponent();
		
		form.addFormElement(new VertexFormElement());
		
		return form;
	}
	
	/**
	 * Returns the FormComponent object for an Edge creation, in which an edge can be created
	 * by the information entered into the created form
	 * @param edge
	 * @param graph
	 * @param vertex
	 * @return
	 */
	public static FormComponent getForm(Class<Edge> edge, Graph graph, Vertex vertex) {
		FormComponent form = new FormComponent();
		
		form.addFormElement(new EdgeFormElement(graph, vertex));
		return form;
	}
	
	
	/**
	 * Returns a FormHandler for creating the desired instance of the specified class, either Graph/Vertex/Edge,
	 * the returned handler is able to process the given form and retrieve required data
	 * @param cls the specified class to be instantiated
	 * @param model the GraphModel for which the new object will be added into
	 * @param graph the graph the new vertex/edge shall be added to
	 * @return
	 */
	public static FormHandler getFormHandler(Class<?> cls, GraphModel model, Graph graph) {
		FormHandler handler = null;
		
		if(cls==Vertex.class) {
			handler = new VertexFormHandler(Vertex.class, model, graph);
		}
		if(cls==Edge.class) {
			handler = new EdgeFormHandler(model, graph);
		}
		if(cls==NormalGraph.class) {
			handler = new GraphFormHandler(model);
		}
		return handler;
	}
	
}
