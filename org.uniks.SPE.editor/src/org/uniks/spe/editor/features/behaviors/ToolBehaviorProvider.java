package org.uniks.spe.editor.features.behaviors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.IHasOperation;
import model.Operations;
import model.SPEGroup;
import model.SPEObject;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.impl.ObjectCreationToolEntry;
import org.eclipse.graphiti.palette.impl.PaletteCompartmentEntry;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.ContextButtonEntry;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.ContextMenuEntry;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.uniks.spe.editor.PlatformImageProvider;
import org.uniks.spe.editor.features.SPEAttribute.SPEAttributeCreateFeature;
import org.uniks.spe.editor.features.behaviors.contextButtons.SetCreateOperationFeature;
import org.uniks.spe.editor.features.behaviors.contextButtons.SetDeleteOperationFeature;
import org.uniks.spe.editor.features.behaviors.contextButtons.SetNopOperationFeature;
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
    public IContextButtonPadData getContextButtonPad(IPictogramElementContext context) {
        IContextButtonPadData data = super.getContextButtonPad(context);
        PictogramElement pe = context.getPictogramElement();
        setGenericContextButtons(data, pe, CONTEXT_BUTTON_DELETE | CONTEXT_BUTTON_UPDATE | CONTEXT_BUTTON_REMOVE);

        Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(pe);
        if (bo instanceof IHasOperation) {
            IHasOperation operationBo = (IHasOperation) bo;            
            createAndAddOperationButtons(data, pe, operationBo);
        }
        
        return data;
    }

    protected void createAndAddOperationButtons(IContextButtonPadData data, PictogramElement pe, IHasOperation operationBo) {
        CustomContext cc = new CustomContext(new PictogramElement[] { pe });
        ICustomFeature[] cf = getFeatureProvider().getCustomFeatures(cc);
        for (int i = 0; i < cf.length; i++) {
            ICustomFeature iCustomFeature = cf[i];
            
            if (iCustomFeature instanceof SetCreateOperationFeature 
                    && !operationBo.getOperation().equals(Operations.CREATE)) {
                           
                IContextButtonEntry button = ContextEntryHelper.createCollapseContextButton(true, iCustomFeature, cc);
                button.setText("Mark as Create Object");
                button.setIconId(PlatformImageProvider.IMG_CREATE_OPERATION);
                data.getDomainSpecificContextButtons().add(button);
            }

            if (iCustomFeature instanceof SetDeleteOperationFeature 
                    && !operationBo.getOperation().equals(Operations.DELETE)) {
                
                IContextButtonEntry button = ContextEntryHelper.createCollapseContextButton(true, iCustomFeature, cc);
                button.setText("Mark as Delete Object");
                button.setIconId(PlatformImageProvider.IMG_DELETE_OPERATION);
                data.getDomainSpecificContextButtons().add(button);
            } 
            
            if (iCustomFeature instanceof SetNopOperationFeature 
                    && !operationBo.getOperation().equals(Operations.NOP)) {
                
                IContextButtonEntry button = ContextEntryHelper.createCollapseContextButton(true, iCustomFeature, cc);
                button.setText("Unmark Object");
                button.setIconId(PlatformImageProvider.IMG_NOP_OPERATION);
                data.getDomainSpecificContextButtons().add(button);
            }
        }
    }


    
    @Override
    public IDecorator[] getDecorators(PictogramElement pe) {
        IFeatureProvider featureProvider = getFeatureProvider();
        Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);        
        if(bo instanceof IHasOperation){  
            IHasOperation operationObject = (IHasOperation) bo;            
            if(operationObject.getOperation().equals(Operations.DELETE)){                
                IDecorator imageRenderingDecorator = new ImageDecorator(PlatformImageProvider.IMG_DELETE_OPERATION);
                imageRenderingDecorator.setMessage("This object will be deleted after matching");
                return new IDecorator[] { imageRenderingDecorator };
            }
            
            if(operationObject.getOperation().equals(Operations.CREATE)){                
                IDecorator imageRenderingDecorator = new ImageDecorator(PlatformImageProvider.IMG_CREATE_OPERATION);
                imageRenderingDecorator.setMessage("this object will be created after matching");
                return new IDecorator[] { imageRenderingDecorator };
            }                  
        }
        return super.getDecorators(pe); 
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
