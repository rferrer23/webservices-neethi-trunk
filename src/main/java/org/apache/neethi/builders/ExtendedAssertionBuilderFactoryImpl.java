package org.apache.neethi.builders;

import java.util.ArrayList;
import java.util.List;

import org.apache.neethi.builders.converters.impl.ConverterRegistry;
import org.apache.neethi.service.impl.AssertionBuilderFactoryImpl;
import org.apache.neethi.service.impl.PolicyBuilder;

public class ExtendedAssertionBuilderFactoryImpl extends AssertionBuilderFactoryImpl {
	private boolean dynamicLoaded;
	@SuppressWarnings("rawtypes")
	private List<AssertionBuilder> assertBuilder = new ArrayList<>();
	
	
	public ExtendedAssertionBuilderFactoryImpl(PolicyBuilder eng, ConverterRegistry reg) {
		super(eng==null ? new PolicyBuilder():eng, reg);
		dynamicLoaded=false;
	}
	@Override
    protected synchronized void loadDynamic() {
        if (!dynamicLoaded) {
            dynamicLoaded = true;
            registerPathAssertBuilders();
        }
    }
	@SuppressWarnings("rawtypes")
	private void registerPathAssertBuilders() {
		for ( AssertionBuilder clazz : assertBuilder) {
			super.registerBuilder((AssertionBuilder<?>) clazz);
        }
	}
	@SuppressWarnings("rawtypes")
	public void addBuilder(AssertionBuilder e){
		removeFirstBindingBuilder(e);
		assertBuilder.add(e);
	}
	@SuppressWarnings("rawtypes")
	private void removeFirstBindingBuilder(AssertionBuilder e) {
		if(!assertBuilder.isEmpty()) {
			for(int i=0; i< assertBuilder.size(); i++) {
				if(assertBuilder.get(i).getClass().equals(e.getClass())) {
					assertBuilder.remove(i);
					break;
				}
			}
		}
	}
}
