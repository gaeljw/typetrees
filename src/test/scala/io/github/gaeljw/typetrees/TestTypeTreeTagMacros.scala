package io.github.gaeljw.typetrees

import org.junit.Assert._
import org.junit.Test
import io.github.gaeljw.typetrees.TypeTreeTagMacros.typeTreeTag

class TestTypeTreeTagMacros {

  @Test
  def testBasic(): Unit = {
    val tag = typeTreeTag[String]

    assertEquals(classOf[String], tag.self.runtimeClass)
    assertEquals(Nil, tag.args)
  }

  @Test
  def testOneLevelArgs(): Unit = {
    val tag = typeTreeTag[Map[String, Int]]

    assertEquals(classOf[Map[_, _]], tag.self.runtimeClass)
    assertEquals(2, tag.args.size)

    assertEquals(classOf[String], tag.args(0).self.runtimeClass)
    assertEquals(Nil, tag.args(0).args)

    assertEquals(classOf[Int], tag.args(1).self.runtimeClass)
    assertEquals(Nil, tag.args(1).args)
  }

  @Test
  def testTwoLevelArgs(): Unit = {
    val tag = typeTreeTag[Seq[Map[String, Int]]]

    assertEquals(classOf[Seq[_]], tag.self.runtimeClass)
    assertEquals(1, tag.args.size)

    val tagMap = tag.args(0)

    assertEquals(classOf[Map[_, _]], tagMap.self.runtimeClass)
    assertEquals(2, tagMap.args.size)

    assertEquals(classOf[String], tagMap.args(0).self.runtimeClass)
    assertEquals(Nil, tagMap.args(0).args)

    assertEquals(classOf[Int], tagMap.args(1).self.runtimeClass)
    assertEquals(Nil, tagMap.args(1).args)
  }

  @Test
  def testInnerClass(): Unit = {
    val tag = typeTreeTag[OuterObj.InnerClass]

    assertEquals(classOf[OuterObj.InnerClass], tag.self.runtimeClass)
    assertEquals(0, tag.args.size)
  }

  @Test
  def testObject(): Unit = {
    val tag = typeTreeTag[OuterObj.type]

    assertEquals(classOf[OuterObj.type], tag.self.runtimeClass)
    assertEquals(0, tag.args.size)
  }

}

object OuterObj {

  class InnerClass {}

}
