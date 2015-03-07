package org.uniks.spe.editor.diagram;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.uniks.spe.editor.features.behaviors.ToolBehaviorProvider;

public class EditorDiagramTypeProvider extends AbstractDiagramTypeProvider {
    
    private IToolBehaviorProvider[] toolBehaviorProviders;
    
	public EditorDiagramTypeProvider() {
		super();
		setFeatureProvider(new EditorFeatureProvider(this));
	}
	
	  @Override
	    public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
	        if (toolBehaviorProviders == null) {
	            toolBehaviorProviders = new IToolBehaviorProvider[] {  new ToolBehaviorProvider(this) };
	        }
	        return toolBehaviorProviders;
	    }
}
