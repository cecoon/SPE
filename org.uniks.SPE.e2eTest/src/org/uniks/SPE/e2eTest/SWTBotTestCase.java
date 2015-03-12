package org.uniks.SPE.e2eTest;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swtbot.eclipse.finder.SWTBotEclipseTestCase;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.ui.PlatformUI;
import org.junit.BeforeClass;

public class SWTBotTestCase extends SWTBotEclipseTestCase {
    
    protected static SWTGefBot bot = new SWTGefBot();
    
    @BeforeClass
    public static void init() throws Exception{
        Thread.sleep(1000);
        
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
    
    public static void createNewTestProject(){
        bot.perspectiveByLabel("Graphiti").activate();
        bot.menu("File").menu("New").menu("Project...").click();
        
        SWTBotShell shell = bot.shell("New Project");        
        shell.activate();
        
     
        
    }

}
