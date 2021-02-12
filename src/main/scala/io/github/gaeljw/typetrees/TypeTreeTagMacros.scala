package io.github.gaeljw.typetrees

import scala.quoted._
import scala.reflect.ClassTag

object TypeTreeTagMacros {

  inline def typeTreeTag[T]: TypeTreeTag = {
    ${ getTypeTree[T] }
  }

  private def getTypeTree[T](using Type[T], Quotes): Expr[TypeTreeTag] = {
    import quotes.reflect._

    def getTypeTreeRec(tpr: TypeRepr)(using Quotes): Expr[TypeTreeTag] = {
      tpr.asType match {
        case '[t] => getTypeTree[t]
      }
    }
    
    val tpr: TypeRepr = TypeRepr.of[T]
    val typeParams: List[TypeRepr] = tpr match {
      case a: AppliedType => a.args
      case _ => Nil
    }

    val selfTag: Expr[ClassTag[T]] = getClassTag[T]
    val argsTrees: Expr[List[TypeTreeTag]] = Expr.ofList(typeParams.map(getTypeTreeRec))

    '{ TypeTreeTag($selfTag, $argsTrees) }
  }

  private def getClassTag[T](using Type[T], Quotes): Expr[ClassTag[T]] = {
    import quotes.reflect._

    val searchResult : ImplicitSearchResult = Implicits.search(TypeRepr.of[ClassTag[T]])

    searchResult match {
      case x : ImplicitSearchSuccess =>
        // println(x.tree)
        x.tree.asExprOf[ClassTag[T]]
      case x: ImplicitSearchFailure =>
        report.error("ImplicitSearchFailure\n" + x.explanation, Position.ofMacroExpansion)
        throw new Exception("Error when applying macro")
    }
  }
  
}