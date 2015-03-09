package org.uniks.spe.editor.features.behaviors.contextButtons;

import model.Operations;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.uniks.spe.editor.PlatformImageProvider;

public class SetNopOperationFeature extends AOperationFeature {
    public SetNopOperationFeature(IFeatureProvider fp) {
        super(fp, "Unmark Object", "removes any operation on this object",
                PlatformImageProvider.IMG_NOP_OPERATION, Operations.NOP);
    } 
}
