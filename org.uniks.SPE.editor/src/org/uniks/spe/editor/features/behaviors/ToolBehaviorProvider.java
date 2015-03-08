package org.uniks.spe.editor.features.behaviors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.StackEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeCreateFeature;
import org.uniks.spe.editor.features.groups.SPEGroupCreateFeature;
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;

public class ToolBehaviorProvider extends DefaultToolBehaviorProvider {

    public ToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
        super(diagramTypeProvider);
    }

    @Override
    public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {   
        RetriggerDirectEditFeature customFeature = new RetriggerDirectEditFeature(getFeatureProvider());
        
        if (customFeature.canExecute(context)) {
            return customFeature;
        }

        return super.getDoubleClickFeature(context);      
    }
    
    @Override
    public IPaletteCompartmentEntry[] getPalette() {
        List<IPaletteCompartmentEntry> ret = new ArrayList<IPaletteCompartmentEntry>();

        // add compartments from super class
        IPaletteCompartmentEntry[] superCompartments = super.getPalette();
        for (int i = 0; i < superCompartments.length; i++){            
            if("Connections".equals(superCompartments[i].getLabel())){
                ret.add(superCompartments[i]);
            }
        }
        
        // add new compartment at the end of the existing compartments
     
        PaletteCompartmentEntry objPalett = new PaletteCompartmentEntry("Objects", null);
        ret.add(objPalett);
    
        PaletteCompartmentEntry attPalett = new PaletteCompartmentEntry("Attributes", null);
        ret.add(attPalett);
        
        PaletteCompartmentEntry groupPalett = new PaletteCompartmentEntry("Groups", null);
        ret.add(groupPalett);
    
        IFeatureProvider featureProvider = getFeatureProvider();
        ICreateFeature[] createFeatures = featureProvider.getCreateFeatures();   
        for (ICreateFeature cf : createFeatures) { 
            ObjectCreationToolEntry entry = featureToEntry(cf);
            if(cf instanceof SPEGroupCreateFeature){
                groupPalett.addToolEntry(entry);
            } 
            
            if(cf instanceof SPEObjectCreateFeature){
                objPalett.addToolEntry(entry);
            }
            
            if(cf instanceof SPEAttributeCreateFeature){
                attPalett.addToolEntry(entry);
            }  
        }

        ret.forEach(it -> it.getToolEntries().sort((x, y) -> x.getLabel().length() - y.getLabel().length()));
        return ret.toArray(new IPaletteCompartmentEntry[ret.size()]);
    }

    protected ObjectCreationToolEntry featureToEntry(ICreateFeature cf) {
        return new ObjectCreationToolEntry(cf.getCreateName(),
                                           cf.getCreateDescription(),
                                           cf.getCreateImageId(),
                                           cf.getCreateLargeImageId(),
                                           cf);    
    } 
    
}
