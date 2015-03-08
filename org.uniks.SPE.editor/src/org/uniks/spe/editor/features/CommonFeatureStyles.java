package org.uniks.spe.editor.features;

import java.util.HashMap;
import java.util.Map;

import model.Tag;

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class CommonFeatureStyles {
    public final static IColorConstant TEXT_FOREGROUND = IColorConstant.BLACK;
    
    public final static IColorConstant NORMAL_FOREGROUND =  new ColorConstant(98, 131, 167);
    public final static IColorConstant NORMAL_BACKGROUND =   new ColorConstant(187, 218, 247);    

    public static final IColorConstant NOT_FOREGROUND = new ColorConstant(227, 75, 75);
    public static final IColorConstant NOT_BACKGROUND = new ColorConstant(235, 145, 145);
    public static final IColorConstant LIGTHEN_NOT_BACKGROUND = new ColorConstant(255, 210, 210);

    public final static IColorConstant OPTIONAL_FOREGROUND =  new ColorConstant(164, 164, 164);
    public final static IColorConstant OPTIONAL_BACKGROUND =    new ColorConstant(210, 210, 210);
    
    public static Map<Tag, LineStyle> LINESTYLE_PER_TAG = new HashMap<Tag, LineStyle>();  
    public static Map<Tag, IColorConstant> FOREGROUND_PER_TAG = new HashMap<Tag, IColorConstant>();  
    public static Map<Tag, IColorConstant> BACKGROUND_PER_TAG = new HashMap<Tag, IColorConstant>();  
    static {
        LINESTYLE_PER_TAG.put(Tag.DEFAULT, LineStyle.SOLID);
        LINESTYLE_PER_TAG.put(Tag.NOT, LineStyle.SOLID);
        LINESTYLE_PER_TAG.put(Tag.OPTIONAL, LineStyle.DASH);

        FOREGROUND_PER_TAG.put(Tag.DEFAULT, NORMAL_FOREGROUND);
        FOREGROUND_PER_TAG.put(Tag.NOT, NOT_FOREGROUND);
        FOREGROUND_PER_TAG.put(Tag.OPTIONAL, OPTIONAL_FOREGROUND);

        BACKGROUND_PER_TAG.put(Tag.DEFAULT, NORMAL_BACKGROUND);
        BACKGROUND_PER_TAG.put(Tag.NOT, NOT_BACKGROUND);
        BACKGROUND_PER_TAG.put(Tag.OPTIONAL, OPTIONAL_BACKGROUND);
    }
    
    public static LineStyle getLineStyleByTag(Tag tag) {
        return LINESTYLE_PER_TAG.getOrDefault(tag, LineStyle.SOLID);
    }
    
    public static IColorConstant getForegroundByTag(Tag tag) {
        return FOREGROUND_PER_TAG.getOrDefault(tag, CommonFeatureStyles.NORMAL_FOREGROUND);
    }
    
    public static IColorConstant getBackgroundByTag(Tag tag) {
        return BACKGROUND_PER_TAG.getOrDefault(tag, CommonFeatureStyles.NORMAL_BACKGROUND);
    }
  
    //more static = more better
}
