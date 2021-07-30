package br.ufc.mdcc.spoonmetrics.miner;

import java.util.Collection;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;

public class NOPRMExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		// TODO Auto-generated method stub
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			System.out.println("-----------" + qualifiedName + "----------------");
			Collection<CtMethod<?>> fields = element.getMethods();
			int count = 0;
			for(CtMethod<?> field : fields)
				if(field.getModifiers().contains(ModifierKind.PRIVATE))
					count++;
			 Dataset.store(qualifiedName, new Measure(Metric.NoPrM, count));
		}
	}

}
