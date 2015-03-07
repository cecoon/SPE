package org.uniks.spe.editor.features.custom;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;

public class RetriggerDirectEditFeature extends AbstractCustomFeature {
    public RetriggerDirectEditFeature(IFeatureProvider fp) {
        super(fp);
    }
    
    @Override
    public boolean canExecute(ICustomContext context) {
        return true;
    }
    
    
    @Override
    public void execute(ICustomContext context) {
        getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().refresh();
    }

}
