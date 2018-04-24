package lab7

class Foo {
  // a method that takes a function and a string, and passes the string into
  // the function, and then executes the function
  def exec(f: String => Unit, intro: String) {
    //print(name) --> variable 'name' is out of scope
    f(intro)
  }
}