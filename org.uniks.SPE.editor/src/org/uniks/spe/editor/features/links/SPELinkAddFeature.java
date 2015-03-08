package org.uniks.spe.editor.features.links;
 
import model.SPELink; 
import model.Tag;

import org.eclipse.graphiti.features.IAddFeature; 
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature; 
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService; 
import org.eclipse.graphiti.util.IColorConstant;
import org.uniks.spe.editor.features.CommonFeatureStyles;

public class SPELinkAddFeature extends AbstractAddFeature implements IAddFeature {  
    
    public SPELinkAddFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public boolean canAdd(IAddContext context) {
        return context.getNewObject() instanceof SPELink;
    }

    protected SPELink createBusinessObject(IAddContext context) {
        return (SPELink) context.getNewObject();
    }

    @Override
    public PictogramElement add(IAddContext context) {
        IAddConnectionContext addConContext = (IAddConnectionContext) context;
        SPELink speLink = createBusinessObject(context);        
        Tag tag = speLink.getTag();
        IColorConstant fColor = CommonFeatureStyles.getForegroundByTag(tag); 
        LineStyle linestyle = CommonFeatureStyles.getLineStyleByTag(tag); 

        Connection connection = createConnectionLine(addConContext, fColor, linestyle);
        addLabel(connection, speLink.getName());
        addDirectionArrow(connection, fColor);

        link(connection, speLink);
        return connection;
    }

  
    protected Connection createConnectionLine(IAddConnectionContext addConContext, IColorConstant fColor, LineStyle linestyle) {
        IGaService gaService = Graphiti.getGaService();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();   
        
        Connection connection = peCreateService.createFreeFormConnection(getDiagram());
        connection.setStart(addConContext.getSourceAnchor());
        connection.setEnd(addConContext.getTargetAnchor());
 
        Polyline polyline = gaService.createPolyline(connection);
        polyline.setLineStyle(linestyle);
        polyline.setForeground(manageColor(fColor));
        polyline.setLineWidth(2);
        return connection;
    }
 
    protected void addLabel(Connection connection, String labelText) {
        IGaService gaService = Graphiti.getGaService();
        IPeCreateService peCreateService = Graphiti.getPeCreateService(); 
        
        // add dynamic text decorator for the association name 
        ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(connection, true, 0.5, true);
        Text text = gaService.createDefaultText(getDiagram(),textDecorator);
        text.setForeground(manageColor(IColorConstant.BLACK));        
        text.setValue("     " + labelText + "    "); //text wont accept any size increase nor updates its size on overflow...
        gaService.setLocationAndSize(text, 0, 0, 0, 0);
             
        IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
        directEditingInfo.setMainPictogramElement(connection);
        directEditingInfo.setPictogramElement(textDecorator);
        directEditingInfo.setGraphicsAlgorithm(text);
    }
    
     
    protected void addDirectionArrow(Connection connection, IColorConstant fColor) { 
        IGaService gaService = Graphiti.getGaService();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();   
        
        ConnectionDecorator cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);          
        Polyline polyline = gaService.createPolyline(cd, new int[] { -15, 10, 0, 0, -15, -10 });
        polyline.setForeground(manageColor(fColor));
        polyline.setLineStyle(LineStyle.SOLID);
        polyline.setLineWidth(2);
    }

}
