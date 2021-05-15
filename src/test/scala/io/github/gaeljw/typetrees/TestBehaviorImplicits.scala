package io.github.gaeljw.typetrees

import org.junit.Assert.assertEquals
import org.junit.Test

class TestBehaviorImplicits {

  @Test
  def testLevel0(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(12)
    assertEquals("I have been called with a parameter of type TypeTreeTag(Int,List())", output)
  }

  @Test
  def testLevel1_1arg(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(List[String]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(java.lang.String,List())))", output)
  }

  @Test
  def testLevel1_2args(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(Map[String, Int]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.Map,List(TypeTreeTag(java.lang.String,List()), TypeTreeTag(Int,List())))", output)
  }

  @Test
  def testLevel1_3args(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(ThreeArg[String, Int, Float]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(io.github.gaeljw.typetrees.ThreeArg,List(TypeTreeTag(java.lang.String,List()), TypeTreeTag(Int,List()), TypeTreeTag(Float,List())))", output)
  }

  @Test
  def testLevel1_4args(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(FourArg[String, Int, Float, String]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(io.github.gaeljw.typetrees.FourArg,List(TypeTreeTag(java.lang.String,List()), TypeTreeTag(Int,List()), TypeTreeTag(Float,List()), TypeTreeTag(java.lang.String,List())))", output)
  }

  @Test
  def testLevel2_1arg(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(List[List[String]]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(java.lang.String,List())))))", output)
  }

  @Test
  def testLevel2_2args(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(List[Map[String, Int]]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(scala.collection.immutable.Map,List(TypeTreeTag(java.lang.String,List()), TypeTreeTag(Int,List())))))", output)
  }

  @Test
  def testLevel2_2args_bis(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(Map[String, List[Int]]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.Map,List(TypeTreeTag(java.lang.String,List()), TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(Int,List())))))", output)
  }

  @Test
  def testLevel3_1arg(): Unit = {
    val output = BusinessClassTypeClass.someGenericMethod(List[List[List[String]]]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(scala.collection.immutable.List,List(TypeTreeTag(java.lang.String,List())))))))", output)
  }

  @Test
  def testSomeGenericMapMethod(): Unit = {
    val output = BusinessClassTypeClass.someGenericMapMethod(Map[String, Int]())
    assertEquals("I have been called with a Map where key is of type TypeTreeTag(java.lang.String,List()) and value is of type TypeTreeTag(Int,List())", output)
  }
  
}

object BusinessClassTypeClass {

  def someGenericMethod[T](t: T)(using tt: TypeTree[T]): String = {
    val tag: TypeTreeTag = tt.tag
    s"I have been called with a parameter of type $tag"
  }

  def someGenericMapMethod[T <: Map[_,_]](map: T)(using tt: TypeTree[T]): String = {
    val mapTag: TypeTreeTag = tt.tag
    val keyTag: TypeTreeTag = mapTag.args(0)
    val valueTag: TypeTreeTag = mapTag.args(1)
    s"I have been called with a Map where key is of type $keyTag and value is of type $valueTag"
  }

}

class ThreeArg[X,Y,Z] {}

class FourArg[X,Y,Z,ZZ] {}
