package io.github.gaeljw.typetrees

import io.github.gaeljw.typetrees.TypeTreeTagMacros.typeTreeTag
import org.junit.Assert.assertEquals
import org.junit.Test

class TestBehavior {

  @Test
  def testSomeGenericMethod(): Unit = {
    val output = BusinessClass.someGenericMethod(Map[String, Int]())
    assertEquals("I have been called with a parameter of type TypeTreeTag(scala.collection.immutable.Map,List(TypeTreeTag(java.lang.String,List()), TypeTreeTag(Int,List())))", output)
  }
  
  @Test
  def testSomeGenericMapMethod(): Unit = {
    val output = BusinessClass.someGenericMapMethod(Map[String, Int]())
    assertEquals("I have been called with a Map where key is of type TypeTreeTag(java.lang.String,List()) and value is of type TypeTreeTag(Int,List())", output)
  }
  
}

object BusinessClass {

  // tag::exampleInlineDef[]
  inline def someGenericMethod[T](t: T): String = {
    val tag: TypeTreeTag = typeTreeTag[T]
    s"I have been called with a parameter of type $tag"
  }
  // end::exampleInlineDef[]

  // tag::exampleInlineDefMap[]
  inline def someGenericMapMethod[K,V](map: Map[K,V]): String = {
    val keyTag: TypeTreeTag = typeTreeTag[K]
    val valueTag: TypeTreeTag = typeTreeTag[V]
    s"I have been called with a Map where key is of type $keyTag and value is of type $valueTag"
  }
  // end::exampleInlineDefMap[]
  
}
