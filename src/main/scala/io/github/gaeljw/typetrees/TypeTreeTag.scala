package io.github.gaeljw.typetrees

import scala.reflect.ClassTag

case class TypeTreeTag(self: ClassTag[_], args: List[TypeTreeTag] = Nil)
