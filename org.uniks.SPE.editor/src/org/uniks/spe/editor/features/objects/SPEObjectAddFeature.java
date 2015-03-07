package org.uniks.spe.editor.features.objects;

import model.SPEObject;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService; 
import org.eclipse.graphiti.util.IColorConstant;
import org.uniks.spe.editor.features.CommonFeatureColors;

public class SPEObjectAddFeature extends AbstractAddFeature implements IAddFeature {

    protected IColorConstant objectTextColor = CommonFeatureColors.TEXT_FOREGROUND;
    protected IColorConstant objectForeground = CommonFeatureColors.NORMAL_FOREGROUND;
    protected IColorConstant objectBackground = CommonFeatureColors.NORMAL_BACKGROUND;
    protected LineStyle lineStyle = LineStyle.SOLID;
    
    
    private static final int width = 100; 
    private static final int height = 150;
    
    public SPEObjectAddFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public boolean canAdd(IAddContext context) {
        return context.getTargetContainer() instanceof Diagram;
    }

    protected SPEObject getObject(IAddContext context){
        return (SPEObject) context.getNewObject();        
    }
    
    @Override
    public PictogramElement add(IAddContext context) {        
        SPEObject object = getObject(context);
        
        Diagram targetDiagram = (Diagram) context.getTargetContainer();        
        ContainerShape containerShape = createBaseContainerShape(context, targetDiagram);         
        link(containerShape, object);
        
        String headerText = object.getName() + " : " + object.getClass_();        
        Shape shape = createHeaderShape(containerShape, headerText);
        link(shape, object);
        
        createHeaderSeperationLine(containerShape);
        
        Graphiti.getPeCreateService().createChopboxAnchor(containerShape);        
        return containerShape;
    }

    protected ContainerShape createBaseContainerShape(IAddContext context, Diagram targetDiagram) {
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);
              
        IGaService gaService = Graphiti.getGaService();
        RoundedRectangle roundedRectangle; // need to access it later
        roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
        roundedRectangle.setForeground(manageColor(objectForeground));
        roundedRectangle.setBackground(manageColor(objectBackground));
        roundedRectangle.setLineWidth(2);
        roundedRectangle.setLineStyle(lineStyle);
        
        gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);        
      
        return containerShape;
    }

    protected void createHeaderSeperationLine(ContainerShape containerShape) {
        IGaService gaService = Graphiti.getGaService();        
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        
        Shape shape = peCreateService.createShape(containerShape, false);
        
        // create and set graphics algorithm
        Polyline polyline = gaService.createPolyline(shape, new int[] { 0, 20, width, 20 });
        polyline.setForeground(manageColor(objectForeground));
        polyline.setLineWidth(2);
        polyline.setLineStyle(lineStyle);        
        
     
    }

    protected Shape createHeaderShape(ContainerShape containerShape, String headerText) {      
        IGaService gaService = Graphiti.getGaService();        
        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        
        // create shape for text
        Shape shape =  peCreateService.createShape(containerShape, false);

        // create and set text graphics algorithm
        Text text = gaService.createText(shape, headerText);
        text.setForeground(manageColor(objectTextColor));
        text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER); 
        text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
        gaService.setLocationAndSize(text, 0, 0, width, 20);
        
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
        directEditingInfo.setMainPictogramElement(containerShape);
        directEditingInfo.setPictogramElement(shape);
        directEditingInfo.setGraphicsAlgorithm(text);
        directEditingInfo.setActive(true); 
        
        return shape;
    }
     
}
