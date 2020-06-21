package graphdisplay.views;

import java.awt.Color;

import java.awt.Graphics;


import javax.swing.JPanel;

import graphdisplay.Graph;
import graphdisplay.GraphModelEvent;
import graphdisplay.GraphModelListener;
import graphdisplay.GraphicsPainter;
import graphdisplay.Painter;

/**
 * Used to provide a view for displaying the graphs stored within a GraphModel.
 * instances of this class are able to listen to graphModelEvents and update by redrawing
 * the graph on screen after changes occur 
 * @author Flynn
 *
 */

@SuppressWarnings("serial")
public class GraphView extends JPanel implements GraphModelListener { 
		
		//reference to a graph object within the graphModel (root)
		private Graph _graph;
		
		/**
		 * Creates a GraphView object
		 */
		public GraphView() {
			setBackground(Color.BLACK);
			_graph = null;
			

		}
		/**
		 * Uses custom painting to display the nodes and edges of graphs + text
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g);//Calls to handle background painting
			
			//create a graphics painter to handle painting of swing components for graph
			Painter painter = new GraphicsPainter(g);
			
			//as long as there is a graph set, recursively paint the components that make it up
			//starting at the root graph/container
			if(_graph != null) {
				_graph.paint(painter);
			}
		}
		//Set the graph to be painted
		public void setGraph(Graph graph) {
			_graph = graph;
			repaint();
		}
		/**
		 * Updates the graph view after any graphModelEvents are sent out to represent the new graphModel
		 */
		@Override
		public void update(GraphModelEvent event) {
			_graph = event.source().root();
			repaint();
			
		}
}
