package com.manager.system;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by zpy on 2024/7/10.
 */
public class Utils {
    public static String readFile() throws MyException {
        try {
            File file = ResourceUtils.getFile(Constants.FILE_NAME);
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        }  catch (FileNotFoundException e){
            //will create new file
        }  catch (IOException e) {
            throw new MyException("Error reading data, please contact system administrator.");
        }
        return null;
    }


}
