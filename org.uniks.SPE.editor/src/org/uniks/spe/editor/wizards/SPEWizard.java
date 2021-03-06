package org.uniks.spe.editor.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import model.MatchTag;
import model.ModelFactory;
import model.SPEGroup;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.graphiti.examples.common.FileService;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "spe". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class SPEWizard extends Wizard implements INewWizard {
	private static final String DIAGRAM_TYPE_ID = "spe";
	
    private SPEWizardPage page;
	private ISelection selection;

	public SPEWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	public void addPages() {
		page = new SPEWizardPage(selection);
		addPage(page);
	}

	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String modelPackage = page.getModelPackage();
		final String diagramName = page.getDiagramName();
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, diagramName, modelPackage, monitor);

				} catch (CoreException e) {				    
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 * @param modelPackage 
	 */

    private void doFinish(String containerName, String fileName, String modelPackage, IProgressMonitor monitor) throws CoreException {
        // create a sample file
        monitor.beginTask("Creating " + fileName, 2);
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (!resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException("Container \"" + containerName + "\" does not exist.");
        }
        IContainer container = (IContainer) resource;
        final IFile file = container.getFile(new Path(fileName + "." + DIAGRAM_TYPE_ID)); 
        createDiagram(file, monitor, modelPackage);
        monitor.worked(1);
        
        monitor.setTaskName("Opening file for editing...");
        getShell().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    IDE.openEditor(page, file, true);
                } catch (PartInitException e) {
                }
            }
        });
        monitor.worked(1);
	}	
    
    private void createDiagram(IFile file, IProgressMonitor monitor, String modelPackage){
        Diagram diagram = Graphiti.getPeCreateService().createDiagram(DIAGRAM_TYPE_ID, file.getName(), true);
        URI diagramUri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
            
        SPEGroup modelRoot = ModelFactory.eINSTANCE.createSPEGroup();
        modelRoot.setTag(MatchTag.DEFAULT); 
        String rootName = file.getName();
        if(file.getName().contains(".")){
            String[] split = file.getName().split("\\.");
            rootName = split[0];
        }               
        
        modelRoot.setName(rootName); 
        modelRoot.setModel(modelPackage); 
        
        URI modelUri = URI.createPlatformResourceURI(file.getFullPath().toString() + "model", true);
        
        FileService.createEmfFileForDiagram(diagramUri, diagram);
        
        diagram.setLink(PictogramsFactory.eINSTANCE.createPictogramLink());
        diagram.getLink().getBusinessObjects().add(modelRoot);
                
        ResourceSet resourceSet = diagram.eResource().getResourceSet();
        Resource diagramResource = resourceSet.getResource(diagramUri, true);        
        diagramResource.setTrackingModification(true);
        diagramResource.getContents().add(diagram);
        
        Resource modelResource = resourceSet.createResource(modelUri);
        modelResource.setTrackingModification(true);
        modelResource.getContents().add(modelRoot);
        
        try {
            modelResource.save(Collections.emptyMap());
            diagramResource.save(Collections.emptyMap());
        } catch (IOException e) { 
            e.printStackTrace();
        }        
    }
	
	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "org.uniks.SPE.editor", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}