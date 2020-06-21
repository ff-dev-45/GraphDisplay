package graphdisplay;

/**
 * Interface to be implemented by classes who need to be notified of events generated
 * by the graph model, graphModel will call update method on all registered listeners
 * @author Flynn
 *
 */
public interface GraphModelListener {
	
	/**
	 * Notified shapeModelListener that a graphModel has changed
	 * @param event that described the change to the particular graphModel object
	 */
	public void update(GraphModelEvent event);
}
