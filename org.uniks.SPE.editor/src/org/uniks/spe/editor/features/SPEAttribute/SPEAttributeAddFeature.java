package org.uniks.spe.editor.features.SPEAttribute;
 
import model.SPEAttribute;
import model.SPEObject;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm; 
import org.eclipse.graphiti.mm.algorithms.Rectangle; 
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape; 
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class SPEAttributeAddFeature extends AbstractAddFeature implements IAddFeature {
    
    protected IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK;
    protected IColorConstant E_CLASS_FOREGROUND = new ColorConstant(0, 0, 0);
     
    private static final int padding = 5;
    private static final int height = 20;
    
	public SPEAttributeAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof SPEAttribute
		        && getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof SPEObject;
	}

    @Override
    public PictogramElement add(IAddContext context) {        
        SPEAttribute attribute = (SPEAttribute) context.getNewObject(); 
        ContainerShape target = context.getTargetContainer();
        ContainerShape containerShape = createBaseContainerShape(context, target);         
        link(containerShape, attribute);
        
        String contentText = attribute.getName() + " "  + attribute.getOperation(); 
        Shape shape = createContentShape(containerShape, contentText);
        link(shape, attribute);        

        return containerShape;
    }
 
    protected ContainerShape createBaseContainerShape(IAddContext context, ContainerShape target) {
        IPeCreateService peCreateService = Graphiti.getPeCreateService();          
        IGaService gaService = Graphiti.getGaService();
        
        GraphicsAlgorithm tgtGraphic = target.getGraphicsAlgorithm(); 
        SPEObject tgtObject = (SPEObject) getBusinessObjectForPictogramElement(target);
        
        ContainerShape containerShape = peCreateService.createContainerShape(target, true);    
        Rectangle base = gaService.createInvisibleRectangle(containerShape);
        
        int x = padding;  
        int y = tgtObject.getAttributes().size() * height;    
        int width = tgtGraphic.getWidth() - 2*padding;
        
        gaService.setLocationAndSize(base, x, y, width, height);        
        
        return containerShape;
    }

    protected Shape createContentShape(ContainerShape containerShape, String headerText) {      
        IGaService gaService = Graphiti.getGaService();        
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        
        // create shape for text
        Shape shape =  peCreateService.createShape(containerShape, false);

        // create and set text graphics algorithm
        Text text = gaService.createText(shape, headerText);
        text.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
        text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
        // vertical alignment has as default value "center"
        text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
        gaService.setLocationAndSize(text, 0, 0, containerShape.getGraphicsAlgorithm().getWidth(), height);  
        
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
        directEditingInfo.setMainPictogramElement(containerShape);
        directEditingInfo.setPictogramElement(shape);
        directEditingInfo.setGraphicsAlgorithm(text);
        directEditingInfo.setActive(true); 
        
        return shape;
    }
}
