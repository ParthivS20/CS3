package Utils;

import java.io.File;

public class MyFile extends File {
    public MyFile(String file, Class c) {
        super("src/" + c.getPackage().toString().split(" ")[1] + "/" +file);
    }
}
