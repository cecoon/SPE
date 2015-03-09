package org.uniks.spe.editor.features.links;

import model.ModelFactory;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;

public class SPELinkCreateFeature extends AbstractCreateConnectionFeature implements ICreateConnectionFeature {

    public SPELinkCreateFeature(IFeatureProvider fp) {
        this(fp, "Link", "Creates a new Link between two Objects");
    }

    public SPELinkCreateFeature(IFeatureProvider fp, String name, String description) {
        super(fp, name, description);
    }

    @Override
    public boolean canStartConnection(ICreateConnectionContext context) {
        return getBusinessObjectForPictogramElement(context.getSourcePictogramElement()) instanceof SPEObject;
    }

    @Override
    public boolean canCreate(ICreateConnectionContext context) {
        Object src = getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
        Object tgt = getBusinessObjectForPictogramElement(context.getTargetPictogramElement());

        return src instanceof SPEObject && tgt instanceof SPEObject;
    }

    protected SPELink createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPELink();
    }

    @Override
    public Connection create(ICreateConnectionContext context) {
        SPEObject src = (SPEObject) getBusinessObjectForPictogramElement(context.getSourcePictogramElement());
        SPEObject tgt = (SPEObject) getBusinessObjectForPictogramElement(context.getTargetPictogramElement());
        
        AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
        
        SPELink speLink = createBusinessObject();
        src.getOutboundLinks().add(speLink);
        tgt.getInboundLinks().add(speLink);           
        getRootGroup().getLinks().add(speLink);         
       
        addContext.setNewObject(speLink);        
        Connection newConnection = (Connection) getFeatureProvider().addIfPossible(addContext); 
        getFeatureProvider().getDirectEditingInfo().setActive(true);
        return newConnection;
    } 
    
    protected SPEGroup getRootGroup() {
        return (SPEGroup) getBusinessObjectForPictogramElement(getDiagram().getLink().getPictogramElement());
    }

}
