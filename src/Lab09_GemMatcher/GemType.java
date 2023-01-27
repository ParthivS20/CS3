package Lab09_GemMatcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.TreeSet;

enum GemType {
    GREEN(5),
    BLUE(5),
    ORANGE(5),
    BOMB(1, true);

    int chance;
    boolean isSpecial;
    private GemType(int chance) {
        this.chance = chance;
    }
    private GemType(int chance, boolean isSpecial) {
        this(chance);
        this.isSpecial = isSpecial;
    }
}

