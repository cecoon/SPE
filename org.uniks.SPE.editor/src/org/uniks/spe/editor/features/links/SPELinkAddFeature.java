package org.uniks.spe.editor.features.links;
 
import model.SPELink; 

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature; 
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService; 
import org.eclipse.graphiti.util.IColorConstant;
import org.uniks.spe.editor.features.CommonFeatureColors;

public class SPELinkAddFeature extends AbstractAddFeature implements IAddFeature {

    protected IColorConstant forgroundColor = CommonFeatureColors.NORMAL_FOREGROUND;

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

        Connection connection = createConnectionLine(addConContext);
        addLabel(connection, speLink.getName());
        addDirectionArrow(connection);

        // create link and wire it
        link(connection, speLink);

        return connection;
    }

    protected Connection createConnectionLine(IAddConnectionContext addConContext) {
        IGaService gaService = Graphiti.getGaService();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();   
        
        Connection connection = peCreateService.createFreeFormConnection(getDiagram());
        connection.setStart(addConContext.getSourceAnchor());
        connection.setEnd(addConContext.getTargetAnchor());
 
        Polyline polyline = gaService.createPolyline(connection);
        polyline.setLineWidth(2);
        polyline.setForeground(manageColor(forgroundColor));
        return connection;
    }

    protected void addLabel(Connection connection, String labelText) {
        IGaService gaService = Graphiti.getGaService();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();   
        
        // add dynamic text decorator for the association name
        ConnectionDecorator textDecorator = peCreateService.createConnectionDecorator(connection, true, 0.5, true);
        Text text = gaService.createDefaultText(getDiagram(), textDecorator);
        text.setForeground(manageColor(IColorConstant.BLACK));
        gaService.setLocation(text, 10, 0);
        text.setValue(labelText);
    }

    protected void addDirectionArrow(Connection connection) { 
        IGaService gaService = Graphiti.getGaService();
        IPeCreateService peCreateService = Graphiti.getPeCreateService();   
        
        ConnectionDecorator cd = peCreateService.createConnectionDecorator(connection, false, 1.0, true);          
        Polyline polyline = gaService.createPolyline(cd, new int[] { -15, 10, 0, 0, -15, -10 });
        polyline.setForeground(manageColor(forgroundColor));
        polyline.setLineWidth(2);
    }

}
