package utils;
import java.util.Scanner;

public class Scan {
  static Scanner scanner = new Scanner(System.in);

  public static Scanner getScanner() {
    return Scan.scanner;
  }

  public static void close() {
    Scan.scanner.close();
  }
}
