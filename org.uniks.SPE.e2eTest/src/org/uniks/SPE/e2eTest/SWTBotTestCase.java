package org.uniks.SPE.e2eTest;

import java.util.List;

import model.IHasMatchTag;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.impl.ContainerShapeImpl;
import org.eclipse.graphiti.ui.internal.parts.ContainerShapeEditPart;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.matchers.AbstractMatcher;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SWTBotJunit4ClassRunner.class)
public class SWTBotTestCase extends SWTBotEclipseTestCase {
    
    protected static SWTGefBot bot = new SWTGefBot();
    
    @BeforeClass
    public static void init() throws Exception{
       
        
        UIThreadRunnable.syncExec(new VoidResult() {            
            @Override
            public void run() {
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
            }
        });
        
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        
        try{
            bot.viewByTitle("Welcome").close();               
        } catch (Exception e) {}
         
         
         UIThreadRunnable.syncExec(new VoidResult() {            
             @Override
             public void run() {
                 PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().forceActive();
             }
         });
        
    }
    
    @Test 
    public void createNewTestProject(){
        System.out.println("createNewTestProject started");    
        
        bot.perspectiveByLabel("Graphiti").activate();
        bot.menu("File").menu("New").menu("Project...").click();        
        SWTBotShell shell = bot.shell("New Project");        
        shell.activate();         
        bot.tree().expandNode("Other").select("Graphiti Example Project");
        bot.button("Next >").click(); 
        bot.textWithLabel("Project name:").setText("SPE-e2eTest");  
        bot.button("Finish").click();
        bot.sleep(1000);
        
        bot.tree().getTreeItem("SPE-e2eTest").expand();
        bot.tree().getTreeItem("SPE-e2eTest").getNode("src").expand();
        bot.tree().getTreeItem("SPE-e2eTest").getNode("src").select();
        bot.menu("File").menu("New").menu("Other...").click(); 
        bot.tree().expandNode("Story Pattern Editor").select("Story Pattern Editor File");
        bot.button("Next >").click(); 
        bot.button("Browse...").click();
        bot.tree().getTreeItem("SPE-e2eTest").select(); 
        bot.tree().getTreeItem("SPE-e2eTest").expand();
        bot.tree().getTreeItem("SPE-e2eTest").getNode("src").select();
        bot.button("OK").click();
        bot.textWithLabel("&Diagram name:").setText("e2eTestDiagram");
        bot.button("Finish").click();
        bot.sleep(1000);
        
        bot.tree().getTreeItem("SPE-e2eTest").getNode("src").getNode("e2eTestDiagram.spe").select();
        bot.menu("Open With").menu("Other...").click();

        bot.sleep(1000);
        
        SWTBotGefEditor editor = bot.gefEditor("e2eTestDiagram.spe");
        editor.show();
        
        
        List<SWTBotGefEditPart> path = getAClassEditParts(editor);
        
        
        
        
        bot.sleep(2000);
        bot.sleep(2000);
        bot.sleep(2000);
    }
    
    public static List<SWTBotGefEditPart> getAClassEditParts(SWTBotGefEditor editor) { 
        List<SWTBotGefEditPart> editParts = editor.editParts(new AbstractMatcher<EditPart>() {          
            @SuppressWarnings({ "restriction"})
            @Override
            protected boolean doMatch(Object item) {
                if( ! (item instanceof ContainerShapeEditPart))
                    return false;
                
                Object model = ((ContainerShapeEditPart) item).getModel();
                if( ! (model instanceof ContainerShapeImpl)) 
                    return false;
                
                ContainerShapeImpl container = (ContainerShapeImpl) model;
                if( container == null || container.getLink() == null)
                    return false;
                
               return container.getLink().getBusinessObjects().stream()
                                         .anyMatch(it -> it instanceof IHasMatchTag ||  it instanceof SPEAttribute);
            }

            @Override
            public void describeTo(Description description) {
                // TODO Auto-generated method stub
                
            }
        });
        
        
        return editParts;
    }
    
    @AfterClass
    public static void sleep() {
        bot.sleep(222000);
    }
}
