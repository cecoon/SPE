package org.uniks.spe.editor.features.behaviors.contextButtons;

import model.Operations;
import model.SPEObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IPlatformImageConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.uniks.spe.editor.PlatformImageProvider;

public class SetDeleteOperationFeature extends AOperationFeature {
    public SetDeleteOperationFeature(IFeatureProvider fp) {
        super(fp, "Mark as Delete Object", "This Object will be deleted after matching",
                PlatformImageProvider.IMG_DELETE_OPERATION, Operations.DELETE);
    }
}
