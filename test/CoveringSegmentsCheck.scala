import CoveringSegments.Segment
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object CoveringSegmentsCheck extends Properties("CoveringSegments"){

  var genSegment = for {
    a <- Gen.choose(0,1000)
    b <- Gen.choose(a, 1000)
  } yield new Segment(a,b)

  val listOfSegments =
    for {
      size <- Gen.choose(1,10)
      elems <- Gen.containerOfN[Array, Segment](size, genSegment)
    } yield elems

  property("minimum points should be only less than total segments points") =
    forAll(listOfSegments){segments  =>
      val assertion = CoveringSegments.optimalPointsFast(segments).length <= segments.length * 2
      if (!assertion)
        System.out.println(s"ERROR for segments -> ${segments.mkString(",")} ")
      assertion
    }

  property("both solutions shoub be equivalent") =
    forAll(listOfSegments){segments  =>
      val naive = CoveringSegments.optimalPoints(segments)
      val fast = CoveringSegments.optimalPointsFast(segments)
      val assertion = naive sameElements fast
      if (!assertion)
        System.out.println(s"ERROR for segments -> ${segments.mkString(",")} ")
      assertion
    }

  property("there is no repeated point") =
    forAll(listOfSegments){segments  =>
      val result = CoveringSegments.optimalPointsFast(segments)
      val assertion = result.toSet.size == result.length
      if (!assertion)
        System.out.println(s"ERROR for segments -> ${segments.mkString(",")} ")
      assertion
    }

}
