package org.uniks.spe.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.uniks.spe.generator.GeneratorHelper;

public class GeneratorAction implements IObjectActionDelegate{
    private Shell shell;
    private IStructuredSelection selection;

    @Override 
    public void run(IAction action) {        
        IFile file = (IFile) selection.getFirstElement();
        GeneratorHelper.doGenerate(file.getLocationURI().toASCIIString(), file.getProject().getLocation().toOSString());       
        
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
        this.selection = (IStructuredSelection) selection;        
    }

    @Override
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
       shell = targetPart.getSite().getShell();        
    }

}
