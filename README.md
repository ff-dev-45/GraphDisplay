# GraphDisplay
A simple project inspired by a university course about displaying shapes. This program completely changes how it is implements and is rewritten to create/display basic graph data structures of nodes and vertices

How to Use:
- To start up the main application GUI, run the GraphDisplay file in the graphdisplay package,
- There will be a blank screen on the right showing the graph view, and a tree only containing a GraphContainer and Normalgraph on the tree view on the left
- To add a new vertex select the NormalGraph and click the 'Add Vertex' button that will become visible along the bottom, A form will appear in which you can set the attributes of the new vertex including the size, position on the graph display and name.
- hit submimt to create the new vertex and it will appear on the graph view and under the tree
- you can create more vertices and when you have at least 2, you can select a vertex and select the 'Add Edge' button to create and edge between the selected vertex and a drop down menu showing the other possible vertices you could connect to, hit submit to create the edge.
###NOTE####
currently there is a graphical error in which if you only have 2 vertices and you add an edge you will be unable to select the vertex you want to add the edge between, you can hit submit and it will create an edge between the only two vertices
####
- Vertices/edges can be removed by selecting them from the tree view and selecting the corresponding remove button at the bottom.

Planned features for a later time:
-Add support for directional graphs 
-Add support for weighted graphs
-Add simple graph algorithms that can be run on created graphs
