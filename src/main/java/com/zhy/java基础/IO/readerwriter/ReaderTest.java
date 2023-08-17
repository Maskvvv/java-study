package com.zhy.java基础.IO.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * <p> ReaderTest </p>
 *
 * @author zhouhongyin
 * @since 2023/8/17 17:34
 */
public class ReaderTest {

    @Test
    public void bufferedReaderTest() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("")));

    }

}
