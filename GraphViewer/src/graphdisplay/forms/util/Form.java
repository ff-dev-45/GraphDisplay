package graphdisplay.forms.util;

/**
 *Form interface represents a form containing one or more FormElements, each FormElement
 *contains fields that can store data values, formHandlers process the stored data in these fields 
 */
public interface Form {
	/**
	 * Add a FormElement to a form
	 * @param element the element to add
	 */
	void addFormElement(FormElement element);
	
	/**
	 * Sets the FormHandler of the form
	 * @param handler
	 */
	void setFormHandler(FormHandler handler);
	
	/**
	 * prepares the form to be used, this is where processing of layout for visual form takes place,
	 * before any data is entered
	 */
	void prepare();
	
	/**
	 * Returns the value for a requested field in a form
	 * @param type the type of the field requested from
	 * @param name the name of the field requested from
	 * @return the value of requested field, null if no matching field from type/name
	 */
	<T> T getFieldValue(Class<? extends T> type, String name);
}
