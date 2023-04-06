package Util;

import java.io.File;

public class PackageFile extends File {
    public PackageFile(String file, Class c) {
        super("src/" + getPackagePath(c) + "/" + file);
    }

    private static String getPackagePath(Class c) {
        String x = c.getPackage().toString().split(" ")[1];
        return x.replaceAll("\\.", "/");
    }
}
