package com.haier.service;

import io.sigpipe.jbsdiff.*;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by niuxinghua on 2018/12/25.
 */
public class DiffService {
    public static void diff(File oldFile, File newFile, File patchFile)
            throws CompressorException, InvalidHeaderException, IOException {
        diff(oldFile, newFile, patchFile, CompressorStreamFactory.BZIP2);
    }

    public static void diff(File oldFile, File newFile, File patchFile,
                            String compression)
            throws CompressorException, InvalidHeaderException, IOException {
        FileInputStream oldIn = new FileInputStream(oldFile);
        byte[] oldBytes = new byte[(int) oldFile.length()];
        oldIn.read(oldBytes);
        oldIn.close();

        FileInputStream newIn = new FileInputStream(newFile);
        byte[] newBytes = new byte[(int) newFile.length()];
        newIn.read(newBytes);
        newIn.close();

        FileOutputStream out = new FileOutputStream(patchFile);
        DiffSettings settings = new DefaultDiffSettings(compression);
        Diff.diff(oldBytes, newBytes, out, settings);
        out.close();
    }

//    public static Boolean ispatchExit(String appName,String patchName)
//            throws CompressorException, InvalidHeaderException, IOException {
//        Patch.patch(oldFile, newFile, patchFile);
//    }

}
