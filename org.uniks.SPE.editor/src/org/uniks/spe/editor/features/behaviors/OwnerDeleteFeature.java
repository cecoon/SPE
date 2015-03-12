package org.uniks.spe.editor.features.behaviors;

import model.SPEGroup;
import model.SPELink;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeService;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

public class OwnerDeleteFeature extends DefaultDeleteFeature {
    protected IPeService peService;

    public OwnerDeleteFeature(IFeatureProvider fp) {
        super(fp);
        peService = Graphiti.getPeService();
    }

    @Override
    public void delete(IDeleteContext context) {
        PictogramElement pe = context.getPictogramElement();

        Object bo = getBusinessObjectForPictogramElement(pe);
       if(bo instanceof SPELink){
           SPELink link = (SPELink) bo;
            link.setSource(null);
           link.setTarget(null);           
           getRootGroup().getLinks().remove(link);                    
       }
        super.delete(context);
    }

    protected SPEGroup getRootGroup() {
        return (SPEGroup) getBusinessObjectForPictogramElement(getDiagram().getLink().getPictogramElement());
    }
   
}