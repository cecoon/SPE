package org.uniks.spe.editor.features;

import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class CommonFeatureColors {
    public final static IColorConstant TEXT_FOREGROUND = IColorConstant.BLACK;
    
    public final static IColorConstant NORMAL_FOREGROUND =  new ColorConstant(98, 131, 167);
    public final static IColorConstant NORMAL_BACKGROUND =   new ColorConstant(187, 218, 247);
    
    public final static IColorConstant OPTIONAL_FOREGROUND =   new ColorConstant(164, 164, 164);
    public final static IColorConstant OPTIONAL_BACKGROUND =    new ColorConstant(210, 210, 210);

    public static final IColorConstant NOT_FOREGROUND = new ColorConstant(227, 75, 75);
    public static final IColorConstant NOT_BACKGROUND = new ColorConstant(235, 145, 145);
}
