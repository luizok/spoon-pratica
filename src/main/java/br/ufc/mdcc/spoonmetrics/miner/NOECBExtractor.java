package br.ufc.mdcc.spoonmetrics.miner;

import java.util.Collection;
import java.util.List;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtComment;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NOECBExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		// TODO Auto-generated method stub
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			System.out.println("-----------" + qualifiedName + "----------");
			System.out.println(element);
			
			List<CtCatch> catchStmts = element.getElements(new TypeFilter<CtCatch>(CtCatch.class));
			int emptyCatches = 0;
			for(CtCatch cStmt : catchStmts) {
				Collection<CtStatement> statements = cStmt.getBody().getStatements();
				boolean isEmpty = true; 
				for(CtStatement stmt : statements) {
					if(!(stmt instanceof CtComment)) {
						isEmpty = false;
						break;
					}
				}
				
				if(isEmpty)
					emptyCatches++;
			}

			Dataset.store(qualifiedName, new Measure(Metric.NoECB, emptyCatches));
		}
	}

}
