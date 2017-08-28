package com.michal.mqtt.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileUtils {

    public static byte[] toByteArray(File file) throws IOException {
        byte[] bytesArray = new byte[(int) file.length()];

        FileInputStream fis = new FileInputStream(file);
        fis.read(bytesArray);
        fis.close();
        return bytesArray;
    }
}
