import java.util.*;

public class CoveringSegments {

    public static int[] optimalPoints(Segment[] segments) {

        Set<Integer> points = new LinkedHashSet<>();
        Arrays.sort(segments, Comparator.comparingInt(o -> o.start));

        for (int i= 0; i < segments.length; i++) {
            int[] candidatePoint = new int[]{-1 ,0};
            int current = i;
            for (int j = segments[current].start; j <= segments[current].end; j++) {
                int segmentsCovered = 1;
                for (int s = current + 1; s < segments.length; s++) {
                        if (isSegmentCovered(j, segments[s])) {
                            segmentsCovered++;
                            i = s;
                        } else {
                            break;
                        }
                }
                updateCandidatePointIfMax(candidatePoint, j, segmentsCovered);
            }
            points.add(candidatePoint[0]);
        }

        return convertToArrayFromSet(points);
    }

    public static int[] optimalPointsList(Segment[] segments) {

        List<Integer> points = new ArrayList<>();
        Arrays.sort(segments, Comparator.comparingInt(o -> o.start));

        for (int i= 0; i < segments.length; i++) {
            int[] candidatePoint = new int[]{-1 ,0};
            int current = i;
            for (int j = segments[current].start; j <= segments[current].end; j++) {
                int segmentsCovered = 1;
                for (int s = current + 1; s < segments.length; s++) {
                    if (isSegmentCovered(j, segments[s])) {
                        segmentsCovered++;
                        i = s;
                    } else {
                        break;
                    }
                }
                updateCandidatePointIfMax(candidatePoint, j, segmentsCovered);
            }
            if (points.size() == 0 || points.get(points.size() - 1) != candidatePoint[0])
                points.add(candidatePoint[0]);
        }

        return points.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void updateCandidatePointIfMax(int[] candidatePoint, int point, int segmentsCovered) {
        if (candidatePoint[1] <= segmentsCovered) {
            candidatePoint[0] = point;
            candidatePoint[1] = segmentsCovered;
        }
    }

    private static boolean isSegmentCovered(int point, Segment segment) {
        return point >= segment.start && point <= segment.end;
    }

    private static int[] convertToArrayFromSet(Set<Integer> array) {
        int[] result = new int[array.size()];
        int i = 0;
        for (Integer point: array) {
            result[i++] = point;
        }
        return result;
    }

    public static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
