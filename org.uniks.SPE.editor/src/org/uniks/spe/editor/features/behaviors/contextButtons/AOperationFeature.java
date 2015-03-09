package org.uniks.spe.editor.features.behaviors.contextButtons;

import model.IHasOperation;
import model.Operations;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public abstract class AOperationFeature extends AbstractCustomFeature {

    private String name;
    private String description;
    private String imageId;
    private Operations Operation;  

    public AOperationFeature(IFeatureProvider fp, String name, String description, String imageId, Operations operation) {
        super(fp);
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        Operation = operation;
    }

    @Override
    public void execute(ICustomContext context) {
        PictogramElement pictogramElement = context.getPictogramElements()[0];
        IHasOperation bo = (IHasOperation) getBusinessObjectForPictogramElement(pictogramElement);
        bo.setOperation(Operation);
        
        getDiagramBehavior().refreshRenderingDecorators((Shape) pictogramElement); 
    }
    
    @Override
    public boolean canExecute(ICustomContext context) {
        if (context.getPictogramElements().length > 0) {
            PictogramElement pictogramElement = context.getPictogramElements()[0];
            Object bo = getBusinessObjectForPictogramElement(pictogramElement);
            if (bo instanceof IHasOperation) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean isAvailable(IContext context) {
        return true;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getImageId() {
        return imageId;
    }

}
