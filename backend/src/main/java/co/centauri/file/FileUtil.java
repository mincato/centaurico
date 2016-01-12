package co.centauri.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FileUtil {

    private static final Logger LOGGER = Logger.getLogger(FileUtil.class);

    public String saveToFile(String absolutePath, String fileName, InputStream is) {

        String file = getCompleteFileName(absolutePath, fileName);

        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);

            IOUtils.copy(is, outputStream);

        } catch (Exception e) {
            LOGGER.error(MessageFormat.format("Ocurrio un error al intentar guardar el archivo {0}", file), e);
            throw new RuntimeException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return file;
    }

    private String getCompleteFileName(String absolutePath, String fileName) {
        return absolutePath + File.separator + fileName;
    }

}
