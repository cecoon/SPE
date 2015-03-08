package org.uniks.spe.editor.diagram;

import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink; 
import model.SPEObject; 

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.ILayoutFeature; 
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape; 
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;
import org.uniks.spe.editor.features.LayoutDomainObjectFeature;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeAddFeature;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeCreateFeature;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeDirectEditFeature;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeUpdateFeature;
import org.uniks.spe.editor.features.behaviors.RetriggerDirectEditFeature;
import org.uniks.spe.editor.features.groups.SPEGroupAddFeature;
import org.uniks.spe.editor.features.groups.SPEGroupCreateFeature;
import org.uniks.spe.editor.features.groups.not.NotSPEGroupCreateFeature;
import org.uniks.spe.editor.features.groups.optional.OptionalSPEGroupCreateFeature;
import org.uniks.spe.editor.features.links.SPELinkAddFeature;
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;
import org.uniks.spe.editor.features.links.SPELinkDirectEditFeature;
import org.uniks.spe.editor.features.links.SPELinkUpdateFeature; 
import org.uniks.spe.editor.features.links.not.NotSPELinkCreateFeature; 
import org.uniks.spe.editor.features.links.optional.OptionalSPELinkCreateFeature;
import org.uniks.spe.editor.features.objects.SPEObjectAddFeature;
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;
import org.uniks.spe.editor.features.objects.SPEObjectDirectEditFeature;
import org.uniks.spe.editor.features.objects.SPEObjectUpdateFeature; 
import org.uniks.spe.editor.features.objects.not.NotSPEObjectCreateFeature; 
import org.uniks.spe.editor.features.objects.optional.OptionalSPEObjectCreateFeature;


public class EditorFeatureProvider extends DefaultFeatureProvider {

	public EditorFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	  
	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { 
				new SPEAttributeCreateFeature(this),			
				new NotSPEObjectCreateFeature(this),			
				new OptionalSPEObjectCreateFeature(this),	
				new SPEObjectCreateFeature(this),			
				new NotSPEGroupCreateFeature(this),			
				new OptionalSPEGroupCreateFeature(this)				
		};
	}
			
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
		        new SPELinkCreateFeature(this),
		        new NotSPELinkCreateFeature(this),
		        new OptionalSPELinkCreateFeature(this)
		};
	}
	
	@Override
    public ICustomFeature[] getCustomFeatures(ICustomContext context) {
        return new ICustomFeature[] { new RetriggerDirectEditFeature(this) };
    } 

    @Override
    public IUpdateFeature getUpdateFeature(IUpdateContext context) {
        PictogramElement pictogramElement = context.getPictogramElement(); 
        
        if(pictogramElement instanceof ConnectionDecorator
                && pictogramElement.getGraphicsAlgorithm() instanceof Text ){
            return new SPELinkUpdateFeature(this); 
        }
        
        if (pictogramElement instanceof ContainerShape) {            
            Object bo = getBusinessObjectForPictogramElement(pictogramElement);
            if (bo instanceof SPEAttribute)
                return new SPEAttributeUpdateFeature(this);
            if (bo instanceof SPEObject)
                return new SPEObjectUpdateFeature(this);
            
        }

        return super.getUpdateFeature(context);
    }

    @Override
    public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
        Object businessObject = getBusinessObjectForPictogramElement(context.getPictogramElement());

        if (businessObject instanceof SPEAttribute) 
            return new SPEAttributeDirectEditFeature(this);
        
        if (context.getPictogramElement() instanceof ConnectionDecorator) 
            return new SPELinkDirectEditFeature(this);
        
        if (businessObject instanceof SPEObject) 
            return new SPEObjectDirectEditFeature(this);
        

        return super.getDirectEditingFeature(context);
	 } 
    
	@Override
	public IAddFeature getAddFeature(IAddContext context) { 
		Object newObject = context.getNewObject();
		
        if (newObject instanceof SPELink)
            return new SPELinkAddFeature(this);    
        
		if (newObject instanceof SPEObject) 
		    return new SPEObjectAddFeature(this);              
		
        if (newObject instanceof SPEAttribute)         
            return new SPEAttributeAddFeature(this);  
        
        if (newObject instanceof SPEGroup)            
            return new SPEGroupAddFeature(this);     
		
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
