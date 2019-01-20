package coffeepartner.complextype.example.common;

/**
 * Created by dieyi on 2019/1/20.
 */
public class Teacher extends Person {

  private String subject;

  public Teacher(String name, int age, String subject) {
    super(name, age);
    this.subject = subject;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public String toString() {
    return "Teacher{" +
      "subject='" + subject + '\'' +
      ", name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
