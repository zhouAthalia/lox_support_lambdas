package com.craftinginterpreters.lox;

import java.util.List;

class LoxFunction implements LoxCallable {
  //ch 10 q 2
  private final String name;

  private final Expr.Function declaration; //ch 10 q 2

  private final Environment closure;


  //ch 10 q 2
  LoxFunction(String name, Expr.Function declaration, Environment closure) {
    this.name = name;
    this.closure = closure;
    this.declaration = declaration;
  }



  @Override
  public int arity() {
    return declaration.parameters.size(); //ch 10 q 2
  }



  @Override
  public Object call(Interpreter interpreter,
                     List<Object> arguments) {
    Environment environment = new Environment(closure);
    for (int i = 0; i < declaration.parameters.size(); i++) { //ch 10 q 2
      environment.define(declaration.parameters.get(i).lexeme, //ch 10 q 2
          arguments.get(i));
    }

    try {
      interpreter.executeBlock(declaration.body, environment); //ch 10 q 2
    } catch (Return returnValue) {
      return returnValue.value;
    }

    return null;
  }

  //ch 10 q 2
  @Override
  public String toString() {
    if (name == null) return "<fn>";
    return "<fn " + name + ">";
  }



}
