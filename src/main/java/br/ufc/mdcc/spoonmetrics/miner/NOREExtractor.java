package br.ufc.mdcc.spoonmetrics.miner;

import java.util.List;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.reflect.code.CtThrow;

public class NOREExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		// TODO Auto-generated method stub
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			System.out.println("-----------" + qualifiedName + "----------");
			System.out.println(element);
			
			List<CtThrow> throwStmts = element.getElements(new TypeFilter<CtThrow>(CtThrow.class));

			Dataset.store(qualifiedName, new Measure(Metric.NoRE, throwStmts.size()));
		}
	}

}
