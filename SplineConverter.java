import java.util.ArrayList;
import java.util.List;

/**
 * I asked Google Gemini to write this code.  My prompt was:
 *
 * <blockquote>
 * Write a Java method that accepts a list of (X, Y) coordinates representing points on a parametric cubic
 * spline curve and returns a list of cubic Bézier control point pairs.
 * </blockquote>
 *
 * Gemini replied:
 * <blockquote>
 * Before we dive into the Java code, let's clarify the task:
 *
 * Parametric Cubic Spline Curve: A smooth curve defined by a set of control points and a mathematical function.
 * Cubic Bézier Curve: A curve defined by four control points.
 * Conversion: We aim to approximate the parametric cubic spline curve using a series of connected cubic Bézier curves.
 * </blockquote>
 *
 * It's roughly the same algorithm I had in mind, except:
 * - I wasn't sure what approximation to use when calculating the tangent vectors
 * - I wasn't sure I hadn't overlooked anything!
 *
 * The second point is still not guaranteed!
 *
 * I converted the code to my style, but the guts are the same.
 *
 * Gemini notes:
 * <blockquote>
 * Explanation:
 *
 * Tangent Approximation: We approximate the tangent vectors at the control points of the spline. This approximation can be refined using more sophisticated techniques,
 * but for many practical applications, this simple approach works well.
 * Bézier Control Point Calculation: Using the tangent vectors, we calculate the intermediate control points for each Bézier curve segment.
 * Bezier Curve Segment: Each pair of consecutive control points on the spline generates a Bézier curve segment with the calculated control points.
 *
 * Key Points and Considerations:
 *
 * Accuracy: The accuracy of the approximation depends on the curvature of the spline and the number of control points. More control points generally lead
 * to a better approximation.
 * Tangent Vector Approximation: While the simple approach used here is effective, more advanced techniques like Catmull-Rom splines or Kochanek-Bartels
 * splines can provide more precise tangent vector calculations.
 * Endpoint Continuity: Ensure that the endpoint of one Bézier curve segment coincides with the start point of the next to maintain continuity.
 * Curve Smoothing: For smoother curves, consider techniques like tension, bias, and continuity parameters in the tangent vector calculation.
 *
 * By following these guidelines and customizing the tangent vector approximation, you can effectively convert
 * parametric cubic spline curves to a series of connected cubic Bézier curves in Java.
 * </blockquote>
 */
public class SplineToBezier {

    public static List<Point[]> splineToBezier(List<Point> controlPoints) {
        final List<Point[]> bezierControlPoints = new LinkedList<>();

        // For each pair of consecutive control points on the spline:
        for (int i = 0; i < controlPoints.size() - 1; i++) {
            final Point p0 = controlPoints.get(i);
            final Point p1 = controlPoints.get(i + 1);

            // Calculate the tangent vectors at P0 and P1 (approximated)
            final Point t0 = new Point((p1.x - p0.x) / 3, (p1.y - p0.y) / 3);
            final Point t1 = new Point((p1.x - p0.x) / -3, (p1.y - p0.y) / -3);

            // Calculate the Bézier control points
            final Point b1 = new Point(p0.x + t0.x, p0.y + t0.y);
            final Point b2 = new Point(p1.x + t1.x, p1.y + t1.y);

            bezierControlPoints.add(new Point[] {p0, b1, b2, p1});
        }

        return bezierControlPoints;
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}