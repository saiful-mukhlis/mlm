package org.basic.comp.abst;

import org.basic.comp.base.SplitButton;
import org.basic.comp.base.TextFieldSearch;

public interface FactorySearch {
	TextFieldSearch getFieldSearch();
	SplitButton getItemSearch();
}
