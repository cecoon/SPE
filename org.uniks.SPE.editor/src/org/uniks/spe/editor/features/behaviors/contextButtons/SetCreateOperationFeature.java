package org.uniks.spe.editor.features.behaviors.contextButtons;

import model.Operations;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.PlatformImageProvider;

public class SetCreateOperationFeature extends AOperationFeature {
    public SetCreateOperationFeature(IFeatureProvider fp) {
        super(fp, "Mark as Create Object", "This Object will be created after matching",
                PlatformImageProvider.IMG_CREATE_OPERATION, Operations.CREATE);
    }
}
