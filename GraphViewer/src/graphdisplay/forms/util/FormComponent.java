package graphdisplay.forms.util;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;

/**
 * this displays a visual form for which a user can enter in information to be handled
 * and processed to usable information by the program,
 * 
 * There are two main parts, a list of element components and a handler, the element components
 * are groups of GUI fields and a Form component can contain multiple element components
 * 
 * The handler is what processes the element field data, and is used to create implementations
 * for vertices and edges in a graph based on the user given data
 * 
 * 
 * @author Flynn, (form classes in this package are based off work from University instructor Ian Warren & Paramvir)
 *
 */
@SuppressWarnings("serial")
public class FormComponent extends JDialog implements Form {
	private List<FormElementComponent> _elements;
	private FormHandler _handler;
	private JButton _submit;
	
	/**
	 * Creates a FormComponent instance
	 */
	public FormComponent() {
		_elements = new ArrayList<FormElementComponent>();
		_submit = new JButton("Submit");
		_submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_handler.processForm(FormComponent.this);
				FormComponent.this.dispose();
				
			}
		});
		//GUI properties for the form
		setRootPaneCheckingEnabled(false);
		setLayout(new GridBagLayout());
		setModalityType(ModalityType.APPLICATION_MODAL);
	}
	
	/**
	 * Adds a FormElement to the form
	 */
	@Override
	public void addFormElement(FormElement element) {
		//Expected to store Element components rather than just elements
		if(element instanceof FormElementComponent) {
			_elements.add((FormElementComponent)element);
		} else {
			throw new IllegalArgumentException();
		}

	}
	
	/**
	 * Sets the FormHandler of this form
	 */
	@Override
	public void setFormHandler(FormHandler handler) {
		_handler = handler;

	}
	/**
	 * Sets up the form comprising of its FormElement parts + submit button
	 */
	@Override
	public void prepare() {
		//clear any components added previously
		removeAll();
		
		//populating the form with element fields
		FormUtility formUtility = new FormUtility();
		for(FormElementComponent element : _elements) {
			formUtility.addLastField(element, this);
		}
		formUtility.addLastField(_submit, this);
		
		pack();
	}

	/**
	 * Returns the value for a specified field within the Form,
	 * @param type the type of field being requested
	 * @param name the name of field being requested
	 * @return the value of the requested field, null if no field matching name/type
	 */
	@Override
	public <T> T getFieldValue(Class<? extends T> type, String name) {
		for(FormElement element: _elements) {
			T fieldValue = element.getFieldValue(type, name);
			
			if(fieldValue!=null) {
				return fieldValue;
			}
		}
		return null;
	}

}
