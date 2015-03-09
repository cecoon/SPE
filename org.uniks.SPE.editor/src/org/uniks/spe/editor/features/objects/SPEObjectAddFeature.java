package org.uniks.spe.editor.features.objects;

import model.MatchTag;
import model.SPEGroup;
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
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;
import org.uniks.spe.editor.features.CommonFeatureStyles;

public class SPEObjectAddFeature extends AbstractAddFeature implements IAddFeature {    
    public static final String THIS = "this";
    
    private static final int width = 100; 
    private static final int height = 150;   
    
    public SPEObjectAddFeature(IFeatureProvider fp) {        
        super(fp);
    }
    
    @Override
    public boolean canAdd(IAddContext context) {
        return getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof SPEGroup;
    }

    protected SPEObject getObject(IAddContext context){
        return (SPEObject) context.getNewObject();        
    }
    
    public static String createHeaderTextOfObject(SPEObject object){    
        return object.getName() + " : " + object.getType();      
    }
    
    @Override
    public PictogramElement add(IAddContext context) {        
        SPEObject object = getObject(context);  
        ContainerShape container = (ContainerShape) context.getTargetContainer(); 
        
        MatchTag tag = object.getTag();
        IColorConstant fColor = CommonFeatureStyles.getForegroundByTag(tag);
        IColorConstant bColor = CommonFeatureStyles.getBackgroundByTag(tag);
        LineStyle linestyle = CommonFeatureStyles.getLineStyleByTag(tag); 
            
        ContainerShape containerShape = createBaseContainerShape(context, container, fColor, bColor, linestyle);         
        link(containerShape, object);       
        
        Shape shape = createHeaderShape(containerShape, createHeaderTextOfObject(object));
        link(shape, object);        
        createHeaderSeperationLine(containerShape, fColor, linestyle);        
        Graphiti.getPeCreateService().createChopboxAnchor(containerShape);        
        return containerShape;
    }

    protected ContainerShape createBaseContainerShape(IAddContext context, ContainerShape container, IColorConstant fColor,
            IColorConstant bColor, LineStyle linestyle) {

        IPeCreateService peCreateService = Graphiti.getPeCreateService();
        ContainerShape containerShape = peCreateService.createContainerShape(container, true);

        IGaService gaService = Graphiti.getGaService();
        RoundedRectangle roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
        roundedRectangle.setForeground(manageColor(fColor));
        roundedRectangle.setBackground(manageColor(bColor));
        roundedRectangle.setLineWidth(2);
        roundedRectangle.setLineStyle(linestyle);

        gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);

        return containerShape;
    }

    protected void createHeaderSeperationLine(ContainerShape containerShape, IColorConstant fColor, LineStyle lineStyle) {
        IGaService gaService = Graphiti.getGaService();        
        IPeCreateService peCreateService = Graphiti.getPeCreateService(); 
        
        Shape shape = peCreateService.createShape(containerShape, false);
        
        // create and set graphics algorithm
        Polyline polyline = gaService.createPolyline(shape, new int[] { 0, 20, width, 20 });
        polyline.setForeground(manageColor(fColor));
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
        text.getStyleRegions().add(createUnderlinedStyleregion(headerText));
        
        text.setForeground(manageColor(CommonFeatureStyles.TEXT_FOREGROUND));
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

    public static TextStyleRegion createUnderlinedStyleregion(String headerText) {
        //... rly? to underline a freakin' text? 
        TextStyle textStyle = StylesFactory.eINSTANCE.createTextStyle();         
        textStyle.setUnderline(true);   
        
        TextStyleRegion textStyleRegion = StylesFactory.eINSTANCE.createTextStyleRegion(); 
        textStyleRegion.setStart(0);
        textStyleRegion.setEnd(headerText.length());
        textStyleRegion.setStyle(textStyle);
        return textStyleRegion;
    }
     
}
