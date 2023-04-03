package Util;

import java.io.File;

public class PackageFile extends File {
    public PackageFile(String file, Class<Object> c) {
        super("src/" + c.getPackage().toString().split(" ")[1] + "/" + file);
    }
}
