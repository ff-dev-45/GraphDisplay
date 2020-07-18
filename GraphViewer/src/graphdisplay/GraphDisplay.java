package graphdisplay;

import java.awt.BorderLayout;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import graphdisplay.forms.FormResolver;
import graphdisplay.forms.util.FormComponent;
import graphdisplay.forms.util.FormHandler;
import graphdisplay.views.GraphTreeAdapter;
import graphdisplay.views.GraphView;

/**
 * the main program for the GraphDisplay application. A program for creating and visualising
 * simple graph data structures. An instance of GraphDisplay contains two main views,
 * one of the graph and one of the tree view in which components of the graph can be selected
 * including vertices/edges to add/remove such components.
 * 
 * This program was inspired by a university course project and modified and changed from the ground
 * to display graphs instead of the original project about displaying different shapes,
 * 
 * Authors of SpaceShape project ParamvirSing and Ian Warren
 * 
 * @author Flynn
 *
 */
@SuppressWarnings("serial")
public class GraphDisplay extends JPanel {

	private static final int DEFAULT_GRAPH_WIDTH = 800;
	private static final int DEFAULT_GRAPH_HEIGHT = 800;

	private Dimension _dimensions;


	//model that represents the graphs in graph view
	private GraphModel _model;

	//view objects
	private GraphView _graphView;
	private JTree _treeView;

	//Adapter objects for transforming GraphModelEvents into TreeModel events
	private GraphTreeAdapter _treeModelAdapter;

	//UserInput components
	private JButton _newGraph;
	private JButton _deleteGraph;
	private JButton _newVertex;
	private JButton _deleteVertex;
	private JButton _newEdge;
	private JButton _deleteEdge;

	//represented selected component in tree view
	private Graph _graphSelected;
	private Vertex _vertexSelected;
	private Edge _edgeSelected;

	/**
	 * Create a GraphDisplay object
	 */
	public GraphDisplay() {
		//use default graph width/height for display
		_dimensions = new Dimension(DEFAULT_GRAPH_WIDTH,DEFAULT_GRAPH_HEIGHT);


		//create the model the views will rely on 
		_model = new GraphModel(_dimensions);

		//POPULATE STEP
		populateModel();

		//setup the buttons/views for gui
		createGUI();

		//add the graph/tree views to listen to the graph model for any events
		_model.addGraphModelListener(_graphView);
		_model.addGraphModelListener(_treeModelAdapter);

		//set the container graph in the model as the root for the graph
		_graphView.setGraph(_model.root());

		//create eventhandlers for buttons and tree
		setupEventHandlers();


		//set the default selection path for the first graph in the tree
		_treeView.setSelectionPath(new TreePath(_model.root().graphAt(0)));
	}
	/*
	 * Sets up base model, can add new vertices/edges but it is recommended to build
	 * the model through the program
	 */
	private void populateModel() {
		
		//creates a normal graph object as the default
		Graph graph = new NormalGraph();
		//uses the model container graph as the container for graph object
		GraphContainer container = _model.root();
		
		//add the normal graph into the model
		_model.addGraph(graph, container);

	}
	/*
	 * The buttons and Tree require event handling code to implement features such as;
	 *  - adding new vertices, this creates a form pop up in which vertex properties are filled out
	 * and the new vertex is added to the model
	 *  - deleting vertex, the selected vertex node in a tree to removed from the model and any
	 *  edges that are connected to the vertex
	 *  - add new edge, a form is created for the selected vertex and the choice to created an edge
	 *  joining to another vertex is provided, after submission the edge is added to the model and tree under each vertex
	 */
	private void setupEventHandlers() {
		
		//event handling code for a new graph instance, must select the root/graph container to add a new graph into
		_newGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormComponent form = FormResolver.getForm();
				
				FormHandler handler = FormResolver.getFormHandler(NormalGraph.class,_model , null);
				form.setFormHandler(handler);
				form.prepare();
				form.setLocationRelativeTo(null);
				form.setVisible(true);
				
				_treeView.updateUI();
			}
			
		});
		
		//event handling code for deleting a graph. must select the graph to be deleted
		_deleteGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = _treeView.getSelectionPath();
				_graphSelected = (Graph)selectionPath.getLastPathComponent();
				_model.removeGraph(_graphSelected);
				_treeView.updateUI();
				_treeView.setSelectionRow(0);
			}
			
		});
		
		//event handling code for new vertex button, must have selected graph you wish vertex to be added to
		_newVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = _treeView.getSelectionPath();
				_graphSelected = (Graph) selectionPath.getLastPathComponent();
				FormComponent form = FormResolver.getForm(Vertex.class);
				FormHandler handler = FormResolver.getFormHandler(Vertex.class, _model,_graphSelected);
				form.setFormHandler(handler);
				form.prepare();

				form.setLocationRelativeTo(null);
				form.setVisible(true);
				_treeView.updateUI();
			}
		});
		//Event handling code for deleting a vertex from the graph, must select vertex you would like to remove from graph,
		//any edges joining at this vertex are also removed from the graph
		_deleteVertex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vertex selected = _vertexSelected;

				Graph graph = selected.getGraph();
				_treeView.setSelectionPath(new TreePath(graph.path().toArray()));
				_model.removeVertex(graph, selected);
				_treeView.updateUI();
			}
		});
		//Event handling code for adding a new edge, must select the first vertex you would like the edge to form from,
		//form is generated to select second vertex you would like to connect edge to
		_newEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = _treeView.getSelectionPath();
				_vertexSelected = (Vertex) selectionPath.getLastPathComponent();
				_graphSelected = _vertexSelected.getGraph();
				FormComponent form = FormResolver.getForm(Edge.class, _graphSelected, _vertexSelected);
				FormHandler handler = FormResolver.getFormHandler(Edge.class, _model, _graphSelected);
				form.setFormHandler(handler);
				form.prepare();

				form.setLocationRelativeTo(null);
				form.setVisible(true);
				_treeView.updateUI();
			}
		});
		//Event handling code for removing an edge, must select edge you would like to remove, and it is removed from each vertex
		_deleteEdge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edge selected = _edgeSelected;

				Graph graph = selected.from().getGraph();
				_treeView.setSelectionPath(new TreePath(selected.path().toArray()));
				_model.removeEdge(graph, selected);
				_treeView.updateUI();
			}
		});
		//Event handling code for tree selection, selecting different node types in tree will result in 
		//different buttons being enabled/disabled
		//GraphContainer will have no buttons enabled
		//Graphs will have add Vertex/ remove vertex enabled, and both edge buttons disabled
		//Vertex nodes will have add edge buttons enabled and both vertex buttons and remove edge disabled
		//Edge nodes will only have remove edge button enabled
		_treeView.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath selectionPath = _treeView.getSelectionPath();
				if(selectionPath.getLastPathComponent() instanceof GraphContainer) {
					_newGraph.setEnabled(true);
					_deleteGraph.setEnabled(false);
					_newVertex.setEnabled(false);
					_deleteVertex.setEnabled(false);
					_newEdge.setEnabled(false);
					_deleteEdge.setEnabled(false);
				}
				if(selectionPath.getLastPathComponent() instanceof Graph &&!(selectionPath.getLastPathComponent() instanceof GraphContainer)) {
					_graphSelected = (Graph) selectionPath.getLastPathComponent();
					
					_newGraph.setEnabled(false);
					_deleteGraph.setEnabled(true);
					_newVertex.setEnabled(_graphSelected instanceof Graph && !(_graphSelected instanceof GraphContainer));
					_deleteVertex.setEnabled(false);
					_newEdge.setEnabled(false);
					_deleteEdge.setEnabled(false);
				}
				else if(selectionPath.getLastPathComponent() instanceof Vertex ) {
					_vertexSelected = (Vertex)	selectionPath.getLastPathComponent();
					
					_newGraph.setEnabled(false);
					_deleteGraph.setEnabled(false);
					_newEdge.setEnabled(_vertexSelected instanceof Vertex);
					_deleteEdge.setEnabled(false);
					_newVertex.setEnabled(false);
					_deleteVertex.setEnabled(_vertexSelected instanceof Vertex);
				}
				else if (selectionPath.getLastPathComponent() instanceof Edge ) {
					_edgeSelected = (Edge) selectionPath.getLastPathComponent();
					
					_newGraph.setEnabled(false);
					_deleteGraph.setEnabled(false);
					_newEdge.setEnabled(false);
					_deleteEdge.setEnabled(_edgeSelected instanceof Edge);
					_newVertex.setEnabled(false);
					_deleteVertex.setEnabled(false);
				}
			}
		});
	}
	/*
	 * Used the create the different GUI components and then out on the screen
	 */
	private void createGUI() {
		//TREE MODEL ADAPTER 
		_treeModelAdapter = new GraphTreeAdapter(_model);
		
		//Create the main views
		_treeView = new JTree(_treeModelAdapter);
		_treeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		_graphView = new GraphView();
		
		//Set up a panel to house the JTree component representing the graph
		JPanel treePanel = new JPanel();
		treePanel.setBorder(BorderFactory.createTitledBorder("Graphs"));
		JScrollPane treeGraph = new JScrollPane(_treeView);
		treeGraph.setPreferredSize(new Dimension(300,500));
		treePanel.add(treeGraph);

		//Set up a panel to house the graph view component displaying the graph
		JPanel graphPanel = new JPanel();
		graphPanel.setBorder(BorderFactory.createTitledBorder("Graph Display"));
		JScrollPane graphDisplay = new JScrollPane(_graphView);
		graphPanel.add(graphDisplay);
		_graphView.setPreferredSize(_dimensions);
		
		//Set up a panel to hold the buttons to control addition/removal of nodes
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Graph Options"));
		_newGraph = new JButton("New Graph");
		_deleteGraph = new JButton("Delete Graph");
		_newVertex = new JButton("add Vertex");
		_deleteVertex = new JButton("remove Vertex");
		_newEdge = new JButton("add Edge");
		_deleteEdge = new JButton("remove Edge");
		
		buttonPanel.add(_newGraph);
		buttonPanel.add(_deleteGraph);
		buttonPanel.add(_newVertex);
		buttonPanel.add(_deleteVertex);
		buttonPanel.add(_newEdge);
		buttonPanel.add(_deleteEdge);

		//Setup the layout using the border layout manager
		setLayout(new BorderLayout());
		add(graphPanel, BorderLayout.CENTER);
		add(treePanel, BorderLayout.WEST);
		add(buttonPanel,BorderLayout.SOUTH);


	}


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Graph Display");
				JComponent content = new GraphDisplay();
				frame.add(content);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
