package org.uniks.spe.editor.features.SPENotLink;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class SPENotLinkCreateFeature extends AbstractCreateConnectionFeature
		implements ICreateConnectionFeature {

	public SPENotLinkCreateFeature(IFeatureProvider fp) {
		super(fp, "Not Link", "Creates a new not link between two objects");
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {	
		return true;
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		PictogramElement sourcePictogramElement = context.getSourcePictogramElement();
		PictogramElement targetPictogramElement = context.getTargetPictogramElement();	
		return sourcePictogramElement != null && targetPictogramElement != null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		// TODO: create the domain object connection here
		Object newDomainObjectConnetion = null;

		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(newDomainObjectConnetion);
		newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);

		return newConnection;
	}
}
