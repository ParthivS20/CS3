package Lab20_Cryptography;

import Util.PackageFile;

import java.awt.*;

public class PhotoMagic {
    public static Picture transform(Picture pic, LFSR lfsr) {
        Picture transformed = new Picture(pic.width(), pic.height());

        for(int x = 0; x < transformed.width(); x++) {
            for(int y = 0; y < transformed.height(); y++) {
                transformed.set(x, y, new Color(pic.get(x, y).getRed() ^ lfsr.generate(8), pic.get(x, y).getGreen() ^ lfsr.generate(8), pic.get(x, y).getBlue() ^ lfsr.generate(8)));
            }
        }

        return transformed;
    }

    public static void main(String[] args) {
        Picture pic = new Picture(new PackageFile("data/pipe.png", PhotoMagic.class));
        pic.show();

        LFSR lfsr = new LFSR("01101000010100010000", 16);
        Picture transformed = transform(pic, lfsr);
        transformed.show();
        transformed.save(new PackageFile("data/pipe-transformed.png", PhotoMagic.class));
    }
}
