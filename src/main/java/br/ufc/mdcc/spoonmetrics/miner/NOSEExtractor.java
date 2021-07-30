package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class NOSEExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		// TODO Auto-generated method stub
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int count = 0;

			for(CtMethod<?> method : element.getMethods())
				count += method.getThrownTypes().size();

			Dataset.store(qualifiedName, new Measure(Metric.NoSE, count));
		}
	}

}
