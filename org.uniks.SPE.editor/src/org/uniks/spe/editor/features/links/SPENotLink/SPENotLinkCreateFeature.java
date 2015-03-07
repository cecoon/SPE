package org.uniks.spe.editor.features.links.SPENotLink;

import model.ModelFactory;
import model.SPELink; 
import org.eclipse.graphiti.features.IFeatureProvider; 
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;

public class SPENotLinkCreateFeature extends SPELinkCreateFeature {

	public SPENotLinkCreateFeature(IFeatureProvider fp) {
		super(fp, "Not Link", "Creates a new not link between two objects");
	}
	
	   protected SPELink createBusinessObject() {
	        return ModelFactory.eINSTANCE.createSPENotLink();
	    }
}
