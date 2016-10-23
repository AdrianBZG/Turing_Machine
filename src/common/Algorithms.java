package common;

public final class Algorithms {
  public static String stringArrayToString(String[] strArray) {
    StringBuilder builder = new StringBuilder();
    for(String s : strArray) {
      builder.append(s);
    }
    return builder.toString();
  }
}
