package com.github.meshter.textrobot;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/**
 * Utility class that allows typing of the common characters. It was used primarily to do a
 * file-to-typing automation. To use the API:
 * <ul>
 * <li>Instantiate it using the default constructor.
 * <li>Set a robot for it.
 * <li>Call one of the <code>strokeKeys</code> methods accordinly to your needs.
 * </ul>
 * 
 * Also, the class provides the {@link #getStrokesAssociation()} method that will return an
 * unmodifiable list of characters to key strokes association.
 * 
 * @author ChrisT
 *
 */
public class CharToKeyStrokeUtils {
  /**
   * The way this list is populated is by considering at all the valid characters as you would look
   * at a standard QWERTY American keyboard (left to right, top to bottom). Obviously this is not
   * comprehensive, but we are open for extensions. Some notes on special characters:
   * <ul>
   * <li>You will have to press Tab in the text file you use as input.
   * </ul>
   * 
   */
  private static final Map<Character, int[]> STROKES_ASSOCIATION;
  static {
    STROKES_ASSOCIATION = Map.ofEntries(Map.entry('`', new int[] {KeyEvent.VK_BACK_QUOTE}),
        Map.entry('~', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE}),
        Map.entry('1', new int[] {KeyEvent.VK_1}),
        Map.entry('!', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_1}),
        Map.entry('2', new int[] {KeyEvent.VK_2}),
        Map.entry('@', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_2}),
        Map.entry('3', new int[] {KeyEvent.VK_3}),
        Map.entry('#', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_3}),
        Map.entry('4', new int[] {KeyEvent.VK_4}),
        Map.entry('$', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_4}),
        Map.entry('5', new int[] {KeyEvent.VK_5}),
        Map.entry('%', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_5}),
        Map.entry('6', new int[] {KeyEvent.VK_6}),
        Map.entry('^', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_6}),
        Map.entry('7', new int[] {KeyEvent.VK_7}),
        Map.entry('&', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_7}),
        Map.entry('8', new int[] {KeyEvent.VK_8}),
        Map.entry('*', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_8}),
        Map.entry('9', new int[] {KeyEvent.VK_9}),
        Map.entry('(', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_9}),
        Map.entry('0', new int[] {KeyEvent.VK_0}),
        Map.entry(')', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_0}),
        Map.entry('-', new int[] {KeyEvent.VK_MINUS}),
        Map.entry('_', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_MINUS}),
        Map.entry('=', new int[] {KeyEvent.VK_EQUALS}),
        Map.entry('+', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_EQUALS}),
        Map.entry('\t', new int[] {KeyEvent.VK_TAB}), Map.entry('q', new int[] {KeyEvent.VK_Q}),
        Map.entry('Q', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_Q}),
        Map.entry('w', new int[] {KeyEvent.VK_W}),
        Map.entry('W', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_W}),
        Map.entry('e', new int[] {KeyEvent.VK_E}),
        Map.entry('E', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_E}),
        Map.entry('r', new int[] {KeyEvent.VK_R}),
        Map.entry('R', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_R}),
        Map.entry('t', new int[] {KeyEvent.VK_T}),
        Map.entry('T', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_T}),
        Map.entry('y', new int[] {KeyEvent.VK_Y}),
        Map.entry('Y', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_Y}),
        Map.entry('u', new int[] {KeyEvent.VK_U}),
        Map.entry('U', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_U}),
        Map.entry('i', new int[] {KeyEvent.VK_I}),
        Map.entry('I', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_I}),
        Map.entry('o', new int[] {KeyEvent.VK_O}),
        Map.entry('O', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_O}),
        Map.entry('p', new int[] {KeyEvent.VK_P}),
        Map.entry('P', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_P}),
        Map.entry('[', new int[] {KeyEvent.VK_OPEN_BRACKET}),
        Map.entry('{', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET}),
        Map.entry(']', new int[] {KeyEvent.VK_CLOSE_BRACKET}),
        Map.entry('}', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET}),
        Map.entry('\\', new int[] {KeyEvent.VK_BACK_SLASH}),
        Map.entry('|', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH}),
        Map.entry('a', new int[] {KeyEvent.VK_A}),
        Map.entry('A', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_A}),
        Map.entry('s', new int[] {KeyEvent.VK_S}),
        Map.entry('S', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_S}),
        Map.entry('d', new int[] {KeyEvent.VK_D}),
        Map.entry('D', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_D}),
        Map.entry('f', new int[] {KeyEvent.VK_F}),
        Map.entry('F', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_F}),
        Map.entry('g', new int[] {KeyEvent.VK_G}),
        Map.entry('G', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_G}),
        Map.entry('h', new int[] {KeyEvent.VK_H}),
        Map.entry('H', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_H}),
        Map.entry('j', new int[] {KeyEvent.VK_J}),
        Map.entry('J', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_J}),
        Map.entry('k', new int[] {KeyEvent.VK_K}),
        Map.entry('K', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_K}),
        Map.entry('l', new int[] {KeyEvent.VK_L}),
        Map.entry('L', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_L}),
        Map.entry(';', new int[] {KeyEvent.VK_SEMICOLON}),
        Map.entry(':', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_SEMICOLON}),
        Map.entry('\'', new int[] {KeyEvent.VK_QUOTE}),
        Map.entry('"', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_QUOTE}),
        Map.entry('\n', new int[] {KeyEvent.VK_ENTER}), Map.entry('z', new int[] {KeyEvent.VK_Z}),
        Map.entry('Z', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_Z}),
        Map.entry('x', new int[] {KeyEvent.VK_X}),
        Map.entry('X', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_X}),
        Map.entry('c', new int[] {KeyEvent.VK_C}),
        Map.entry('C', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_C}),
        Map.entry('v', new int[] {KeyEvent.VK_V}),
        Map.entry('V', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_V}),
        Map.entry('b', new int[] {KeyEvent.VK_B}),
        Map.entry('B', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_B}),
        Map.entry('n', new int[] {KeyEvent.VK_N}),
        Map.entry('N', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_N}),
        Map.entry('m', new int[] {KeyEvent.VK_M}),
        Map.entry('M', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_M}),
        Map.entry(',', new int[] {KeyEvent.VK_COMMA}),
        Map.entry('<', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_COMMA}),
        Map.entry('.', new int[] {KeyEvent.VK_PERIOD}),
        Map.entry('>', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_PERIOD}),
        Map.entry('/', new int[] {KeyEvent.VK_SLASH}),
        Map.entry('?', new int[] {KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH}),
        Map.entry('\r', new int[] {KeyEvent.VK_ENTER}),
        Map.entry(' ', new int[] {KeyEvent.VK_SPACE}));
  }

  private Robot robot;

  public static final Map<Character, int[]> getStrokesAssociation() {
    return Collections.unmodifiableMap(STROKES_ASSOCIATION);
  }

  public Robot getRobot() {
    return robot;
  }

  public void setRobot(Robot robot) {
    this.robot = robot;
  }


  /**
   * Type a character.
   * 
   * @param c the char the robot should type.
   */
  public void strokeKey(char c) {
    try {
      int[] keyCombination = STROKES_ASSOCIATION.get(c);
      if (keyCombination == null) {
        System.err.println("Could not find key combination for character '" + c + "'");
        return;
      }
      for (int i = 0; i < keyCombination.length; i++) {
        robot.keyPress(keyCombination[i]);
      }
      for (int i = keyCombination.length - 1; i >= 0; i--) {
        robot.keyRelease(keyCombination[i]);
      }
    } catch (RuntimeException e) {
      System.err.println("Exception caught for character '" + c + "':");
      e.printStackTrace();
    }
  }

  /**
   * Type all the characters coming from a stream.
   * 
   */
  public void strokeKeysFromInputStream(InputStream is) throws IOException {
    DataInputStream dis = new DataInputStream(is);
    char c;
    while ((c = (char) dis.read()) != (char) -1) {
      strokeKey(c);
    }
  }

  /**
   * Type all the characters coming from a string.
   * 
   */
  public void strokeKeysFromString(String s) throws IOException {
    char[] c = s.toCharArray();
    for (int i = 0; i < c.length; i++) {
      strokeKey(c[i]);
    }
  }


  /**
   * Type all the characters coming from a file.
   * 
   */
  public void strokeKeysFromFile(File f) throws IOException {
    try (FileInputStream fis = new FileInputStream(f)) {
      strokeKeysFromInputStream(fis);
    }
  }

  /**
   * Type all the characters coming from a resource available in the classpath.
   * 
   * @param c the char the robot should type.
   */
  public void strokeKeysFromClassPathResource(String resource) throws IOException {
    try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource)) {
      strokeKeysFromInputStream(is);
    }
  }

  /**
   * Main method that can be used as utility...
   * 
   * @param args The list of arguments:
   *        <ol>
   *        <li>args[0] is the delay.
   *        <li>args[1] is the file the characters will be loaded from.
   *        </ol>
   * 
   */
  public static void main(String[] args) {
    try {
      long delay = Long.parseLong(args[0]);
      File file = new File(args[1]);

      Robot r = new Robot();
      CharToKeyStrokeUtils strokeUtils = new CharToKeyStrokeUtils();
      strokeUtils.setRobot(r);

      System.out.println("Waiting for " + delay + " milliseconds...");
      Thread.sleep(delay);
      strokeUtils.strokeKeysFromFile(file);
      System.out.println("Done...");
    } catch (Throwable e) {
      // horrible exception handling
      e.printStackTrace();
    }
  }
}
