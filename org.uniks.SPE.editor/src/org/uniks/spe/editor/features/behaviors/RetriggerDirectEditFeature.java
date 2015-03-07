package org.uniks.spe.editor.features.behaviors;

import model.SPEAttribute;
import model.SPELink;
import model.SPEObject;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class RetriggerDirectEditFeature extends AbstractCustomFeature {
    public RetriggerDirectEditFeature(IFeatureProvider fp) {
        super(fp);
    }
    
    @Override
    public boolean canExecute(ICustomContext context) {

        if(context.getPictogramElements().length > 0){
            PictogramElement mainPictogram = context.getPictogramElements()[0]; 
            Object businessObject = getBusinessObjectForPictogramElement(mainPictogram);
            
            if(businessObject instanceof SPELink 
                    || businessObject instanceof SPEAttribute
                    || businessObject instanceof SPEObject){
                return true;
            }            
        }
        
        return  false;
    }
    
    
    @Override
    public void execute(ICustomContext context) {        
        PictogramElement mainPictogram = context.getPictogramElements()[0]; 
        Object businessObject = getBusinessObjectForPictogramElement(mainPictogram);
        
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();        
        directEditingInfo.setMainPictogramElement(mainPictogram);
        
        if (businessObject instanceof SPELink) { 
            
            ConnectionDecorator linkDecorator = getLinkTextDecorator(mainPictogram);             
            directEditingInfo.setPictogramElement(linkDecorator);       
            directEditingInfo.setGraphicsAlgorithm(linkDecorator.getGraphicsAlgorithm());            
        } else {
            
            PictogramElement shape = context.getInnerPictogramElement();            
            directEditingInfo.setPictogramElement(shape);
            directEditingInfo.setGraphicsAlgorithm(shape.getGraphicsAlgorithm());            
        }  

        directEditingInfo.setActive(true);        
        getDiagramBehavior().refresh(); 
        
    }

    protected ConnectionDecorator getLinkTextDecorator(PictogramElement mainPictogram) {
        FreeFormConnection con = (FreeFormConnection) mainPictogram;
        for (int i = 0; i < con.getConnectionDecorators().size(); i++) {
            ConnectionDecorator connectionDecorator = con.getConnectionDecorators().get(i);
            
            if(connectionDecorator.getGraphicsAlgorithm() instanceof Text){
                return connectionDecorator;
            }
        }
        return null;
    }

}
