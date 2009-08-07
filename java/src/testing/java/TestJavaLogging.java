package testing.java;

import org.apache.log4j.Logger;

public class TestJavaLogging {
  private static Logger log = Logger.getLogger(TestJavaLogging.class);
  
  public static void main(String[] args) {
    System.err.println("Starting Java test...");
    int i = 0;
    int runs = 10000;
    long startTime = System.currentTimeMillis();
    for(;i<runs;++i) {
      log.info("Info.");
      log.error("Error.");
    }
    long stopTime = System.currentTimeMillis();
    long runTime = stopTime - startTime;
    System.err.println("Run time for " + (runs * 2) + " log messages: " + runTime);
  }
}
