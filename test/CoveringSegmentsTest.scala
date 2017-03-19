import CoveringSegments.Segment
import org.scalatest.FlatSpec

class CoveringSegmentsTest extends FlatSpec {

  behavior of "CoveringSegments"

  it should "compute covering segments for a case" in {
    val segments = Array( new Segment(1,3), new Segment(2,5), new Segment(3,6))
    assert(CoveringSegments.optimalPointsFast(segments) === Array(3))
  }

  it should "compute covering segments for b case" in {
    val segments = Array( new Segment(4,7), new Segment(1,3), new Segment(2,5), new Segment(5,6))
    assert(CoveringSegments.optimalPointsFast(segments) === Array(3,6))
  }

  it should "compute covering segments for c case" in {
    val segments = Array(new Segment(1,1), new Segment(1,3), new Segment(1,2), new Segment(4,4), new Segment(0,1),
      new Segment(3,4), new Segment(8,8), new Segment(2,4), new Segment(5,5), new Segment(7,8), new Segment(2,4),
      new Segment(9,9))
    assert(CoveringSegments.optimalPointsFast(segments) === Array(1,4,5,8,9))
  }

  it should "not duplicate points" in {
    val segments = Array(new Segment(105,678), new Segment(628,734), new Segment(638,651), new Segment(704,821), new Segment(720,818),
      new Segment(758,773))
    val result = CoveringSegments.optimalPointsFast(segments)
    assert( result.toSet.size === result.length)
  }

  it should " compute same result" in {
    val naive = CoveringSegments.optimalPoints(Array(new Segment(77,114)))
    val fast = CoveringSegments.optimalPointsFast(Array(new Segment(77,114)))
    assert( naive sameElements fast)
  }

}
