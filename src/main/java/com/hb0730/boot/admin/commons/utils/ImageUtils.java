package com.hb0730.boot.admin.commons.utils;

import net.sf.image4j.codec.ico.ICODecoder;
import org.springframework.lang.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class ImageUtils {
    public static final String EXTENSION_ICO = "ico";

    @Nullable
    public static BufferedImage getImageFromFile(InputStream is, String extension) throws IOException {
        if (EXTENSION_ICO.equals(extension)) {
            return ICODecoder.read(is).get(0);
        } else {
            return ImageIO.read(is);
        }
    }
}
