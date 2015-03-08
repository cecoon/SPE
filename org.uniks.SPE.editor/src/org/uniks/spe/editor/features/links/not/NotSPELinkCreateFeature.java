package org.uniks.spe.editor.features.links.not;

import model.ModelFactory;
import model.SPELink; 
import model.Tag;

import org.eclipse.graphiti.features.IFeatureProvider; 
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;

public class NotSPELinkCreateFeature extends SPELinkCreateFeature {

	public NotSPELinkCreateFeature(IFeatureProvider fp) {
		super(fp, "Not Link", "Creates a new not link between two objects");
	}
	
	   protected SPELink createBusinessObject() {
	        SPELink link = ModelFactory.eINSTANCE.createSPELink();
	        link.setTag(Tag.NOT);
            return link;
	    }
}
