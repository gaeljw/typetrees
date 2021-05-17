package io.github.gaeljw.typetrees

import io.github.gaeljw.typetrees.TypeTreeTag
import io.github.gaeljw.typetrees.TypeTreeTagMacros.getTypeTree

import scala.reflect.ClassTag

trait TypeTree[T] {
  def tag: TypeTreeTag
}

object TypeTree {

  given typeTree4[T[
      A,
      B,
      C,
      D
  ], A: TypeTree, B: TypeTree, C: TypeTree, D: TypeTree](using
      ClassTag[T[A, B, C, D]]
  ): TypeTree[T[A, B, C, D]] with {
    def tag = TypeTreeTag(
      summon[ClassTag[T[A, B, C, D]]],
      List(
        summon[TypeTree[A]].tag,
        summon[TypeTree[B]].tag,
        summon[TypeTree[C]].tag,
        summon[TypeTree[D]].tag
      )
    )
  }

  given typeTree3[T[A, B, C], A: TypeTree, B: TypeTree, C: TypeTree](using
      ClassTag[T[A, B, C]]
  ): TypeTree[T[A, B, C]] with {
    def tag = TypeTreeTag(
      summon[ClassTag[T[A, B, C]]],
      List(
        summon[TypeTree[A]].tag,
        summon[TypeTree[B]].tag,
        summon[TypeTree[C]].tag
      )
    )
  }

  given typeTree2[T[A, B], A: TypeTree, B: TypeTree](using
      ClassTag[T[A, B]]
  ): TypeTree[T[A, B]] with {
    def tag = TypeTreeTag(
      summon[ClassTag[T[A, B]]],
      List(summon[TypeTree[A]].tag, summon[TypeTree[B]].tag)
    )
  }

  given typeTree1[T[A], A: TypeTree](using ClassTag[T[A]]): TypeTree[T[A]]
    with {
    def tag = TypeTreeTag(summon[ClassTag[T[A]]], List(summon[TypeTree[A]].tag))
  }

  given typeTree0[T](using ClassTag[T]): TypeTree[T] with {
    def tag = TypeTreeTag(summon[ClassTag[T]])
  }

}
