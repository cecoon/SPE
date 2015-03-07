package org.uniks.spe.editor.diagram;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.uniks.spe.editor.features.LayoutDomainObjectFeature;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeCreateFeature;
import org.uniks.spe.editor.features.SPELink.SPELinkAddFeature;
import org.uniks.spe.editor.features.SPELink.SPELinkCreateFeature;
import org.uniks.spe.editor.features.SPENotObject.SPENotObjectCreateFeature;
import org.uniks.spe.editor.features.SPEObject.SPEObjectAddFeature;
import org.uniks.spe.editor.features.SPEObject.SPEObjectCreateFeature;
import org.uniks.spe.editor.features.SPEOptionalObject.SPEOptionalObjectCreateFeature;


public class EditorFeatureProvider extends DefaultFeatureProvider {

	public EditorFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { 
				new SPEAttributeCreateFeature(this),			
				new SPENotObjectCreateFeature(this),			
				new SPEOptionalObjectCreateFeature(this),	
				new SPEObjectCreateFeature(this)				
		};
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {new SPELinkCreateFeature(this)};
	} 
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) { 
		if (context instanceof IAddConnectionContext /* && context.getNewObject() instanceof <DomainObject> */) {
			return new SPELinkAddFeature(this);
		} else if (context instanceof IAddContext /* && context.getNewObject() instanceof <DomainObject> */) {
			return new SPEObjectAddFeature(this);
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		// TODO: check for right domain object instances below
		if (context.getPictogramElement() instanceof ContainerShape /* && getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof <DomainObject> */) {
			return  new LayoutDomainObjectFeature(this);
		}
	
		return super.getLayoutFeature(context);
	}
}
