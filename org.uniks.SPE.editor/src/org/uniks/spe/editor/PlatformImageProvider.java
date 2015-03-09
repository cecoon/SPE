package org.uniks.spe.editor;
 
import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class PlatformImageProvider extends AbstractImageProvider {
    private static final String ROOT_FOLDER_FOR_IMG = "icons/";
    
    public final static String ID = "org.uniks.spe.editor.PlatformImageProvider";   
    public final static String IMG_DELETE_OPERATION = "imgDeleteOperation";
    public final static String IMG_CREATE_OPERATION = "imgCreateOperation";
    public final static String IMG_NOP_OPERATION = "imgNopOperation";
    

    /**
     * Creates a new {@link PlatformImageProvider}.
     */
    public PlatformImageProvider() {
        super();
    }

    @Override
    protected void addAvailableImages() {
        addImageFilePath(IMG_DELETE_OPERATION, ROOT_FOLDER_FOR_IMG + "remove.png"); 
        addImageFilePath(IMG_NOP_OPERATION, ROOT_FOLDER_FOR_IMG + "undo.png"); 
        addImageFilePath(IMG_CREATE_OPERATION, ROOT_FOLDER_FOR_IMG + "add.png"); 
    }

}
