package org.uniks.spe.editor.features.links;
 
import model.SPELink;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.uniks.spe.editor.features.CommonFeatureStyles;

public class SPELinkUpdateFeature extends AbstractUpdateFeature {

    public SPELinkUpdateFeature(IFeatureProvider fp) {
        super(fp); 
    }

    @Override
    public boolean canUpdate(IUpdateContext context) {
        PictogramElement pictorgram = context.getPictogramElement();
        EObject container = pictorgram.eContainer();
        if(container instanceof FreeFormConnection
                && getBusinessObjectForPictogramElement((FreeFormConnection) container) instanceof SPELink
                && pictorgram.getGraphicsAlgorithm() instanceof Text){
            return true;
        }
        return false;
    }

    @Override
    public IReason updateNeeded(IUpdateContext context) {
        FreeFormConnection connection = (FreeFormConnection) context.getPictogramElement().eContainer(); 
        SPELink bo = (SPELink) getBusinessObjectForPictogramElement(connection);
        
        String textItShouldHave = bo.getName();   
        String textItHas =  getTextItHas(context.getPictogramElement());
        
        Color foregoundIsShouldHave = manageColor(CommonFeatureStyles.getForegroundByTag(bo.getTag()));
        Color foregroundItHas = connection.getGraphicsAlgorithm().getForeground();
        
        if(foregoundIsShouldHave.equals(foregroundItHas) && textItShouldHave.equals(textItHas)) {
            return Reason.createFalseReason();    
        }
      
        return Reason.createTrueReason("Element is out of date");        
    }

    protected String getTextItHas(PictogramElement pictogramElement) {     
        String value = ((Text)pictogramElement.getGraphicsAlgorithm()).getValue();
        value = value.replaceAll(" ", "");
        return value;
    }

    @Override
    public boolean update(IUpdateContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        FreeFormConnection connection = (FreeFormConnection) context.getPictogramElement().eContainer(); 
        SPELink bo = (SPELink) getBusinessObjectForPictogramElement(connection);
        
        //update text
        String textItShouldHave = SPELinkAddFeature.getNameOfLink(bo);          
        Text text = (Text) pictogramElement.getGraphicsAlgorithm();
        text.setValue("   " + textItShouldHave + "   ");

        //update colors        
        Color foregoundIsShouldHave = manageColor(CommonFeatureStyles.getForegroundByTag(bo.getTag()));
        LineStyle lineStyleItShouldHave = CommonFeatureStyles.getLineStyleByTag(bo.getTag());        
        connection.getGraphicsAlgorithm().setForeground(foregoundIsShouldHave);
        connection.getGraphicsAlgorithm().setLineStyle(lineStyleItShouldHave);
        
        EList<ConnectionDecorator> connectionDecorators = connection.getConnectionDecorators();
        for(ConnectionDecorator cd : connectionDecorators){
            if(cd.getGraphicsAlgorithm() instanceof Text){
                cd.getGraphicsAlgorithm().setForeground(manageColor(CommonFeatureStyles.NORMAL_FOREGROUND));
            } else {
                cd.getGraphicsAlgorithm().setForeground(foregoundIsShouldHave);
            }            
        }
        
        return true;
    }

}
