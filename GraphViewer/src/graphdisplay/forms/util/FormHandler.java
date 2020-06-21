package graphdisplay.forms.util;

/**
 * Interface to be implemented by classes that are required to process the data in a form
 * @author Flynn
 *
 */
public interface FormHandler {
	/**
	 * Processing of the form takes place here, and what happens to the data and how it is used
	 * by the program occurs by classes that implement this interface
	 * @param form the given form containing data needing to be processed
	 */
	void processForm(Form form);
}
