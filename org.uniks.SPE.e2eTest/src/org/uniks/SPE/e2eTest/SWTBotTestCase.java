package org.uniks.SPE.e2eTest;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.widgetOfType;

import java.util.List;

import model.IHasMatchTag;
import model.SPEAttribute;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
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
         setFileAssociations();         
    }
    
    
    @Test 
    public void createNewTestProject(){ 
        SWTBotTreeItem project = createProject();
        
        bot.sleep(1000);       
        project.getNode("e2eTestDiagram.spe").click();

        bot.sleep(1000);
        SWTBotGefEditor editor = bot.gefEditor("e2eTestDiagram.spe");
        editor.show();        

        SWTBotGefEditPart path = getAClassEditParts(editor);        
        assertEquals(path.children().size(), 0);  
        
        editor.activateTool("Object");
        editor.click(40, 120);
        editor.directEditType("this : Store");  

        editor.activateTool("Attribute");
        editor.click(60, 120);
        editor.directEditType("name ==\"MyStore\"");        

        editor.activateTool("Object");
        editor.click(340, 120);
        editor.directEditType("item1 : Item");  

        editor.activateTool("Attribute");
        editor.click(360, 120);
        editor.directEditType("value ==3");

        editor.activateTool("Link");
        editor.click(50, 160);
        editor.click(350, 160);

        editor.save();
        bot.sleep(500);
        
        project.getNode("e2eTestDiagram.spemodel") .select().contextMenu("Generate Code").click();
        
        bot.sleep(500);
        project.contextMenu("Refresh").click();       
        
        SWTBotTreeItem generatedFolder = project.expandNode("spe-gen").expandNode("matcher");
         
        assertTrue(generatedFolder != null);
        assertTrue(generatedFolder.getItems().length == 1);
        assertEquals(generatedFolder.getItems()[0].getText(), "MatchClasse2eTestDiagram.java");  
    }
    
    

    protected SWTBotTreeItem createProject() {
        bot.perspectiveByLabel("Graphiti").activate();
        bot.menu("File").menu("New").menu("Project...").click();        
        SWTBotShell shell = bot.shell("New Project");        
        shell.activate();         
        bot.tree().expandNode("Other").select("Graphiti Example Project");
        bot.button("Next >").click(); 
        bot.textWithLabel("Project name:").setText("SPE-e2eTest");  
        bot.button("Finish").click();
        bot.sleep(1000);
        
        SWTBotTreeItem project = bot.tree().getTreeItem("SPE-e2eTest");
        project.expand();
        project.getNode("src").expand();
        project.getNode("src").select();
        bot.menu("File").menu("New").menu("Other...").click(); 
        bot.tree().expandNode("Story Pattern Editor").select("Story Pattern Editor File");
        bot.button("Next >").click(); 
        bot.button("Browse...").click();
        project.select(); 
        project.expand();
        project.getNode("src").select();
        bot.button("OK").click(); 
        bot.textWithLabel("&Diagram name:").setText("e2eTestDiagram");
        bot.button("Finish").click();
        return project;
    }
    
    public static SWTBotGefEditPart getAClassEditParts(SWTBotGefEditor editor) { 
        List<SWTBotGefEditPart> editParts = editor.editParts(new AbstractMatcher<EditPart>() {          
            @SuppressWarnings({ "restriction"})
            @Override
            protected boolean doMatch(Object item) {
                if(item instanceof ContainerShapeEditPart && ((ContainerShapeEditPart) item).getModel() instanceof ContainerShapeImpl){
                    ContainerShapeImpl container = (ContainerShapeImpl) ((ContainerShapeEditPart) item).getModel();         
                    if(container == null || container.getLink() == null ) return false;
                    EList<EObject> businessObjects = container.getLink().getBusinessObjects();
                    
                    for(EObject obj : businessObjects){
                        if(obj instanceof IHasMatchTag || obj instanceof SPEAttribute){
                            return true;
                        }
                    }
                    
                    return false;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                // TODO Auto-generated method stub
                
            }
        });
        
        
        return  editParts.get(0); 
    }
    
    private static void setFileAssociations() {
        bot.menu("Window").menu("Preferences").click();
        bot.shell("Preferences").activate();
        bot.tree().expandNode("General").expandNode("Editors").select("File Associations");
        bot.button(0).click();
        bot.shell("Add File Type").activate();
        bot.textWithLabel("File type:").setText("*.spe");
        bot.button("OK").click();
        bot.shell("Preferences").activate();
        bot.button(2).click();
        bot.shell("Editor Selection").activate();
        bot.radio(0).click();
        bot.table(0).select("Graphiti Diagram Editor");
        bot.button("OK").click();
        bot.shell("Preferences").activate();
        bot.button("OK").click();
    }
  
}
