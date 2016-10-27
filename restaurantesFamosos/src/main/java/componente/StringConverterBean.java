package componente;

import javafx.util.StringConverter;

public class StringConverterBean<T extends RenderizaCombo> extends StringConverter<T> {

	@Override
	public String toString(T object) {
		if (object == null) {
			return null;
		} else {
			return object.getText();
		}
	}

	@Override
	public T fromString(String string) {
		// converte uma string para objeto
		return null;
	}

	
}
