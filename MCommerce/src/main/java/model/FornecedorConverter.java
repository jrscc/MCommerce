package model;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import services.FornecedorService;
import services.ServiceDacException;

@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter {
	
	
		@Inject
		private FornecedorService fornecedor;

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String value) {
			if (value == null || value.trim().isEmpty()) {
				return null;
			}

			try {
				int id = Integer.parseInt(value);
				return fornecedor.getByID(id);
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

			if (!(value instanceof Fornecedor)) {
				return null;
			}
			
			Integer id = ((Fornecedor) value).getId();
			return (id != null) ? id.toString() : null;
		}


}
