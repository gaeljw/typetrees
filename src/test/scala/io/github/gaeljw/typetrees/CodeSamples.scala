package io.github.gaeljw.typetrees

import io.github.gaeljw.typetrees.TypeTreeTagMacros.typeTreeTag

import scala.reflect.ClassTag

class CodeSamples {

  inline def mainUsage[T] = {
    // tag::mainusage[]
    val tag: TypeTreeTag = typeTreeTag[T] // <1>
    
    val classTag: ClassTag[_] = tag.self // <2>
    val actualClass: Class[_] = classTag.runtimeClass
    
    val typeParameters: List[TypeTreeTag] = tag.args // <3>
    // end::mainusage[]
  }
  
}
