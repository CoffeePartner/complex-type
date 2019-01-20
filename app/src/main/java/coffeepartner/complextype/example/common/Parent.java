package coffeepartner.complextype.example.common;

import java.util.Arrays;

import coffeepartner.complextype.example.common.Person;

/**
 * Created by dieyi on 2019/1/20.
 */
public class Parent extends Person {

  protected Person[] children;

  public Parent(String name, int age, Person[] children) {
    super(name, age);
    this.children = children;
  }

  public Person[] getChildren() {
    return children;
  }

  public void setChildren(Person[] children) {
    this.children = children;
  }

  @Override
  public String toString() {
    return "Father{" +
      "children=" + Arrays.toString(children) +
      ", name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
