package graphdisplay.forms;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import graphdisplay.Graph;
import graphdisplay.GraphModel;
import graphdisplay.Vertex;
import graphdisplay.forms.util.Form;
import graphdisplay.forms.util.FormHandler;

/**
 * VertexFormHandler is a formHandler that reads the data and creates a new vertex instance
 * @author Flynn
 *
 */
public class VertexFormHandler implements FormHandler {
	private Class<Vertex> _vertexToInstantiate;
	private GraphModel _model;
	private Graph _graph;
	
	/**
	 * Create a VertexFormHandler
	 * @param cls the Vertex Class to instantiate
	 * @param model the GraphModel this new vertex is added under
	 * @param graph the graph this new vertex is added into
	 */
	public VertexFormHandler(Class<Vertex> cls, GraphModel model, Graph graph) {
		_vertexToInstantiate = cls;
		_model = model;
		_graph = graph;
	}
	
	/**
	 * Reads the given form data describing the new Vertex,
	 * A new instance will be created and then added into the supplied graph using
	 * the GraphModel so that events are sent out and listeners are notified
	 * 
	 * @param form contains the Vertex data
	 */
	@Override
	public void processForm(Form form) {
		try {
			Constructor<Vertex> cons = _vertexToInstantiate.getConstructor(
					java.lang.Integer.TYPE,java.lang.Integer.TYPE,
					java.lang.Integer.TYPE,java.lang.Integer.TYPE,
					java.lang.String.class);
			
		
			int width = form.getFieldValue(Integer.class, VertexFormElement.WIDTH);
			int height = form.getFieldValue(Integer.class, VertexFormElement.HEIGHT);
			int x = form.getFieldValue(Integer.class, VertexFormElement.X_POS);
			int y = form.getFieldValue(Integer.class, VertexFormElement.Y_POS);
			String name = form.getFieldValue(String.class, VertexFormElement.NAME);

			Vertex newVertex = (Vertex)cons.newInstance(x*100+20,y*100+20,width,height,name);
			_model.addVertex(_graph, newVertex);
		
		} catch (IllegalArgumentException e) {
			System.err.println(e);//thrown if arguments are incompatible
		} catch (InstantiationException e) {
			e.printStackTrace();//thrown if loaded class cannot be instatiated
		} catch (IllegalAccessException e) {
			e.printStackTrace();//thrown if class' constructor is hidden
		} catch (InvocationTargetException e) {
			e.printStackTrace();//thrown if constructor throws an exception
		} catch (NoSuchMethodException e) {
			e.printStackTrace();//thrown if constructor with specified arguments not defined by class
		} catch (SecurityException e) {
			e.printStackTrace();//thrown if security manager is set and program not permitted to load class at runtime
		}

	}

}
