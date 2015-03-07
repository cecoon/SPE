package org.uniks.spe.editor.features.behaviors;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.uniks.spe.editor.features.custom.RetriggerDirectEditFeature;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider {

    public ToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
        super(diagramTypeProvider);
    }

    @Override
    public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {   
        RetriggerDirectEditFeature customFeature = new RetriggerDirectEditFeature(getFeatureProvider());
        
        if (customFeature.canExecute(context)) {
            return customFeature;
        }

        return super.getDoubleClickFeature(context);      
    }
}
