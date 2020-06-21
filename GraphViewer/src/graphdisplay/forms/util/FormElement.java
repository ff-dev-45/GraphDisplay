package graphdisplay.forms.util;

/**
 * A FormElement is a part of a form
 * 
 * an element is a collection of named fields and for each of the fields there is a type and value,
 * the type is the java class and the value is an instance of the type/class
 * 
 * interface provides required responsibility to allow for operations to add/updated field data as well
 * as query values from fields
 */

public interface FormElement {
	
	/**
	 * adds a field to the form element of specified name with type and optional default
	 * @param name the name of the field being added
	 * @param defaultValue the optional default value for the field if there is one
	 * @param type the type of the field being added
	 */
	<T> void addField(String name, T defaultValue, Class<T> type);
	
	/**
	 * Updates the given field
	 * 
	 * @param name the name of the field wanting to be updated
	 * @param value the value for field we want to update
	 * @throws IllegalArgumentException if the type is incompatiable with the type of the value parameter
	 * exception is thrown
	 */
	void putFieldValue(String name, Object value) throws IllegalArgumentException;
	
	/**
	 * Returns the stored value in specified field
	 * @param type the type of field that is being requested
	 * @param name the name of the field that is being requested
	 * @return the value of the requested field, null returned if no matching field
	 */
	<T> T getFieldValue(Class<? extends T> type, String name);
}
