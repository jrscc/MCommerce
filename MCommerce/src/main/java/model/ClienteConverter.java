package model;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import services.ClienteService;
import services.ServiceDacException;


@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	// XXX: Injeção de dependência possível graças ao Omnifaces, pois 
	// CDI 1.1 e conversores do JSF 2.2 não conversam.
	// Referências: http://showcase.omnifaces.org/cdi/FacesConverter
	// http://showcase.omnifaces.org/cdi/FacesValidator
	@Inject
	private ClienteService cliente;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			return cliente.getByID(id);
		} catch (ServiceDacException | NumberFormatException e) {
			String msgErroStr = String.format(
					"Erro de conversão! Não foi possível realizar a conversão da string '%s' para o tipo esperado.",
					value);
			FacesMessage msgErro = new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErroStr, msgErroStr);
			throw new ConverterException(msgErro);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (!(value instanceof Cliente)) {
			return null;
		}
		
		Integer id = ((Cliente) value).getId();
		return (id != null) ? id.toString() : null;
	}

}
