package org.uniks.spe.editor.diagram;

import model.SPEAttribute;
import model.SPENotObject;
import model.SPEObject;
import model.SPEOptionalObject;

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
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeAddFeature;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeCreateFeature;
import org.uniks.spe.editor.features.links.SPELinkAddFeature;
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;
import org.uniks.spe.editor.features.objects.SPEObjectAddFeature;
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;
import org.uniks.spe.editor.features.objects.SPENotObject.SPENotObjectAddFeature;
import org.uniks.spe.editor.features.objects.SPENotObject.SPENotObjectCreateFeature;
import org.uniks.spe.editor.features.objects.SPEOptionalObject.SPEOptionalObjectAddFeature;
import org.uniks.spe.editor.features.objects.SPEOptionalObject.SPEOptionalObjectCreateFeature;


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
		return new ICreateConnectionFeature[] {
		        new SPELinkCreateFeature(this)		       
		};
	} 

    
	@Override
	public IAddFeature getAddFeature(IAddContext context) { 
		if (context instanceof IAddConnectionContext) {		    
			return new SPELinkAddFeature(this);			
		} 
		
		if (context instanceof IAddContext  && context.getNewObject() instanceof SPEAttribute) {           
            return new SPEAttributeAddFeature(this);         
        } 
		
		if (context instanceof IAddContext && context.getNewObject() instanceof SPEObject) {
		    return getFeatureForSPEObject(context);
		}		
		
		return super.getAddFeature(context);
	}	

    private IAddFeature getFeatureForSPEObject(IAddContext context) {
        Object newObject = context.getNewObject();
        
        if(newObject instanceof SPEOptionalObject){
            return new SPEOptionalObjectAddFeature(this);             
        } 
        
        if((newObject instanceof SPENotObject)){
            return new SPENotObjectAddFeature(this);             
        }
        
        //default value
        return new SPEObjectAddFeature(this); 
             
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
