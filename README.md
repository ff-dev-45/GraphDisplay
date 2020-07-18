# GraphDisplay 1.1 !!
A simple project inspired by a university course about displaying shapes. This program completely changes how it is implements and is rewritten to create/display basic graph data structures of nodes and vertices

How to Use:
- To start up the main application GUI, run the GraphDisplay file in the graphdisplay package, --Currently you can either run it through java console or use an ide---
- There will be a blank screen on the right showing the graph view, and a tree only containing a GraphContainer and Normalgraph on the tree view on the left
- To add a new vertex select the NormalGraph and click the 'Add Vertex' button that will become visible along the bottom, A form will appear in which you can set the attributes of the new vertex including the size, position on the graph display and name.
- hit submimt to create the new vertex and it will appear on the graph view and under the tree
- you can create more vertices and when you have at least 2, you can select a vertex and select the 'Add Edge' button to create and edge between the selected vertex and a drop down menu showing the other possible vertices you could connect to, hit submit to create the edge.

-you can also remove any graph/vertex/edge by selecting it in the tree and clicking on the corresponding remove button at the bottom.
-you can add new graphs that are either normal/directional/weighted (weighted not currently implemented)


###NOTE####
-currently there is a graphical error in which if you only have 2 vertices and you add an edge you will be unable to select the vertex you want to add the edge between, you can hit submit and it will create an edge between the only two vertices.
-weighted graph is implemented as a normal graph for now as no implementation for weighted graphs are currently implemented.
####

Planned features for a later time:

-Add better graphical representation of directed edges for digraphs.
-Add support for weighted graphs.
-Add simple graph algorithms that can be run on created graphs.


!!Add support for directional graphs- THIS HAS BEEN IMPLEMENTED!!

version 1.1.0
