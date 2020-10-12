package com.github.meshter.textrobot;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Ignore;
import org.junit.Test;

public class TestCharToKeyStrokeUtils {
  @Test
  @Ignore
  public void testInputEquality() throws IOException, AWTException, InterruptedException {
    CharToKeyStrokeUtils keyStroke = new CharToKeyStrokeUtils();
    keyStroke.setRobot(new Robot());
    long seconds = 10;
    System.out.println("You have " + seconds + " seconds to open the test file and select it...");
    Thread.sleep(seconds * 1000);
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("test-keys.txt")) {
      keyStroke.strokeKeysFromInputStream(is);
    } catch (EOFException e) {
      e.printStackTrace();
    }
  }
}
