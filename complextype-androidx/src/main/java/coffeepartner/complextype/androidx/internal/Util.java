package coffeepartner.complextype.androidx.internal;

/**
 * Created by dieyi on 2019/1/20.
 */
public class Util {


  public static <T> T requireNonNull(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    return t;
  }
}
