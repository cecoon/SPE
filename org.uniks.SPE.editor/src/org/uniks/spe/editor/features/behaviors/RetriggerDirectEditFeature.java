package org.uniks.spe.editor.features.behaviors;

import model.SPEAttribute;
import model.SPEObject;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
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
            
            if(mainPictogram instanceof ConnectionDecorator 
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
        
        PictogramElement textPE;
        Text textGA;                
        if (mainPictogram instanceof ConnectionDecorator) {      
             textPE = mainPictogram;
            textGA = (Text) textPE.getGraphicsAlgorithm();            
        } else {            
            textPE = context.getInnerPictogramElement();            
            textGA = (Text) textPE.getGraphicsAlgorithm();            
        }  
        
       
        if (textPE != null && textGA != null) {
            IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
            directEditingInfo.setMainPictogramElement(mainPictogram);
            directEditingInfo.setPictogramElement(textPE);
            directEditingInfo.setGraphicsAlgorithm(textGA);
            directEditingInfo.setActive(true);
            getDiagramBehavior().refresh();
        }
        
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
