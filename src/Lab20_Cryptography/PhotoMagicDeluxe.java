package Lab20_Cryptography;

import Util.PackageFile;

public class PhotoMagicDeluxe extends PhotoMagic{
    private static final String BASE_64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static String getSeed(String encryptionKey) {
        String seed = "";
        for(char c : encryptionKey.toCharArray()) {
            seed += String.format("%6s", Integer.toBinaryString(BASE_64.indexOf(c))).replaceAll(" ", "0");
        }
        return seed;
    }

    public static void main(String[] args) {
        Picture pic = new Picture(new PackageFile("data/mystery.png", PhotoMagicDeluxe.class));
        pic.show();

        LFSR lfsr = new LFSR(getSeed("OPENSESAME"), 58);
        Picture transformed = transform(pic, lfsr);
        transformed.show();
        transformed.save(new PackageFile("data/mystery-transformed.png", PhotoMagicDeluxe.class));
    }
}
