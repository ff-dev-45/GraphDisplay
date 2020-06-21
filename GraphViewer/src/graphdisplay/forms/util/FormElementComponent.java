package graphdisplay.forms.util;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

/**
 * This class is an implementation of a FormElement interface, it is a visual formElement
 * due to extending JPanel, instances of this are added to FormComponents
 * 
 * 
 * @author Flynn
 *
 */
@SuppressWarnings("serial")
public class FormElementComponent extends JPanel implements FormElement {
	private Map<String,Object> _fields; //key is the field Name and value is the object stored
	private Map<String,Class<?>> _fieldTypes;//key is the field name and value is the class
	
	/**
	 * Creates a new FormElementComponent
	 */
	public FormElementComponent() {
		_fields = new HashMap<String, Object>();
		_fieldTypes = new HashMap<String,Class<?>>();
	}
	
	
	
	@Override
	public <T> void addField(String name, T defaultValue, Class<T> type) {
		_fields.put(name, defaultValue);
		_fieldTypes.put(name, type);

	}

	@Override
	public void putFieldValue(String name, Object value) throws IllegalArgumentException {
		Class<?> fieldType = _fieldTypes.get(name);
		
		if(fieldType.isAssignableFrom(value.getClass())) {
			_fields.put(name, value);//used to store/update value for the field
		} else {
			throw new IllegalArgumentException();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getFieldValue(Class<? extends T> type, String name) {
		T result = null;
		
		Object obj = _fields.get(name);
		if((obj != null)&& (type.isAssignableFrom(obj.getClass()))) {
			result = (T)obj;
		}
		return result;
	}

}
