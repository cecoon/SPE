package org.uniks.spe.editor.features.SPEObject;

import model.SPEObject;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.IColorConstant;

public class SPEObjectAddFeature extends AbstractAddFeature implements	IAddFeature {

	public SPEObjectAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
			SPEObject object = (SPEObject) context.getNewObject();
			object.setName("asd");
	        // CONTAINER SHAPE WITH ROUNDED RECTANGLE
	        IPeCreateService peCreateService = Graphiti.getPeCreateService();
	        ContainerShape containerShape = peCreateService.createContainerShape(context.getTargetContainer(), true);

	        // define a default size for the shape
	        int width = 50;
	        int height = 25;
	        IGaService gaService = Graphiti.getGaService();
	        RoundedRectangle roundedRectangle; // need to access it later

	        // create and set graphics algorithm
	        roundedRectangle = gaService.createRoundedRectangle(containerShape, 5, 5);
	        roundedRectangle.setForeground(manageColor(IColorConstant.DARK_GREEN));
	        roundedRectangle.setBackground(manageColor(IColorConstant.WHITE));
	        roundedRectangle.setLineWidth(2);
	        gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);

	        // create link and wire it
	        link(containerShape, object);

	        // create shape for text
	        Shape shape = peCreateService.createShape(containerShape, false);

	        // create and set text graphics algorithm
	        Text text = gaService.createText(shape, object.getName());
	        text.setForeground(manageColor(IColorConstant.BLACK));
	        text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
	        
	        // vertical alignment has as default value "center"
	        text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
	        gaService.setLocationAndSize(text, 0, 0, width, 20);

	        // create link and wire it
	        link(shape, object);
	        return containerShape;
	}
}
