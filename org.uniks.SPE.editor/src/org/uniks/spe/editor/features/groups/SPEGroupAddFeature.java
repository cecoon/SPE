package org.uniks.spe.editor.features.groups;

import model.MatchTag;
import model.SPEGroup;
import model.SPEObject;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.uniks.spe.editor.features.CommonFeatureStyles;

public class SPEGroupAddFeature extends AbstractAddFeature implements IAddFeature { 
 
    protected IColorConstant backgrounColor = IColorConstant.LIGHT_LIGHT_GRAY; 
    
    private static final int width = 300; 
    private static final int height = 300;
    
    public SPEGroupAddFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public boolean canAdd(IAddContext context) {
        return context.getTargetContainer() instanceof Diagram;
    }

    protected SPEGroup getObject(IAddContext context){
        return (SPEGroup) context.getNewObject();        
    }
    
    @Override
    public PictogramElement add(IAddContext context) {        
        SPEGroup object = getObject(context);

        ContainerShape container = (ContainerShape) context.getTargetContainer();  
        ContainerShape containerShape = createBaseContainerShape(context, container, object.getTag());         
        link(containerShape, object); 
        
        Graphiti.getPeCreateService().createChopboxAnchor(containerShape);        
        return containerShape;
    }
    

    protected ContainerShape createBaseContainerShape(IAddContext context, ContainerShape container, MatchTag tag) {
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape = peCreateService.createContainerShape(container, true);
              
        IGaService gaService = Graphiti.getGaService();
        Rectangle rec = gaService.createRectangle(containerShape);         
        rec.setBackground(manageColor(backgrounColor));
        rec.setLineStyle(CommonFeatureStyles.getLineStyleByTag(tag));  
        rec.setForeground(manageColor(CommonFeatureStyles.getForegroundByTag(tag)));          
        rec.setLineVisible(true);
        rec.setLineWidth(2);      
        gaService.setLocationAndSize(rec, context.getX(), context.getY(), width, height);  
        return containerShape;
    }
}
