import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;

public class Homework12 {

	/* Finish the constructor and create any necessary instance
	 * variables. The constructor should create and save an
	 * ArrayList of RectangularShape values
	 */
	 private ArrayList<RectangularShape> shapes;
	public Homework12() {
		shapes = new ArrayList<RectangularShape>();
	}

	/* Adds the parameter s to the ArrayList
	 */
	public void addShape(RectangularShape s) {
		shapes.add(s);
	}

	/* Returns the number of RectangularShape objects
	 * in the ArrayLists that intersect the
	 * parameter r
	 */
	public int problem1(Rectangle2D r) {
		int ans = 0;
		for(int i = 0; i < shapes.size(); i++){
			if(shapes.get(i).intersects(r)){
				ans++;
			}
		}
		return ans;
	}

	/* Returns the number of RectangularShape objects
	 * in the ArrayLists that do NOT contain the
	 * parameter p
	 */
	public int problem2(Point2D p) {
		int ans = 0;
		for(int i = 0; i < shapes.size(); i++){
			if(shapes.get(i).contains(p) == false){
				ans++;
			}
		}
		return ans;
	}

	/* Returns the number of Ellipse2D objects that
	 * are in the ArrayList
	 */
	public int problem3() {
		int size = 0;
		Ellipse2D ell = new Ellipse2D.Double();
		for (int i = 0; i < shapes.size(); i++){
			RectangularShape r = shapes.get(i);
			if (r.getClass() == ell.getClass()){
				size++;
			}
		}
		return size;
	}

	/* Returns true if any of the Rectangle2D objects
	 * in the ArrayList intersects the parameter Line2D
	 */
	public boolean problem4(Line2D l) {
		for (int i = 0; i < shapes.size(); i++){
			if(shapes.get(i).contains(l.getX1(), l.getY1())){
				return true;
			}
		}
		return false;
	}

	/* Return the total area of the RectangularShape objects in
	 * the AraryList
	 * For this problem, you may assume that the only shapes
	 * that have area are rectangles and ellipses
	 * Note that not all ellipses are circles
	 * If two objects intersect, the intersected area
	 * counts for both (i.e. do not account for intersection)
	 */
	public double problem5() {
		Rectangle2D r = new Rectangle2D.Double();
		Ellipse2D e = new Ellipse2D.Double();
		double totalArea = 0;
		for (int i = 0; i < shapes.size(); i++){
			if (shapes.get(i).getClass() == r.getClass()){
				double w = shapes.get(i).getWidth();
				double h = shapes.get(i).getHeight();
				double area = w * h;
				totalArea += area;
			}
			if (shapes.get(i) instanceof Ellipse2D){
				totalArea += Math.PI * ((shapes.get(i).getHeight()/2) * (shapes.get(i).getWidth()/2));
			}
		}
		return totalArea;
	}

	public static void main(String[] args) {
		boolean passed = true;

		Homework12 hw12 = new Homework12();
		hw12.addShape(new Ellipse2D.Double(20, 20, 10, 10));
		hw12.addShape(new Rectangle2D.Double(30, 30, 20, 20));
		hw12.addShape(new Rectangle2D.Double(40, 20, 20, 40));

		Rectangle2D  r = new Rectangle2D.Double(25, 25, 10, 10);
		if (hw12.problem1(r) == 2) {
			System.out.println("Pass 1");
		} else {
			System.out.println("Fail 1");
			passed = false;
		}

		Point2D p = new Point2D.Double(45, 35);
		if (hw12.problem2(p) == 1) {
			System.out.println("Pass 2");
		} else {
			System.out.println("Fail 2");
			passed = false;
		}

		if (hw12.problem3() == 1) {
			System.out.println("Pass 3");
		} else {
			System.out.println("Fail 3");
			passed = false;
		}

		Line2D l1 = new Line2D.Double(0, 0, 10, 10);
		Line2D l2 = new Line2D.Double(35, 35, 45, 45);
		if (hw12.problem4(l1)) {
			System.out.println("Fail 4.1");
			passed = false;
		} else if (!hw12.problem4(l2)) {
			System.out.println("Fail 4.2");
			passed = false;
		} else {
			System.out.println("Pass 4");
		}

		if (closeEnough(hw12.problem5(), 1278.53981634)) {
			System.out.println("Pass 5");
		} else {
			System.out.println("Fail 5");
			passed = false;
		}

		if (passed) {
			System.out.println("All Pass");
		} else {
			System.out.println("Fail");
		}

	}

	public static boolean closeEnough(double a, double b) {
		return Math.abs(a - b) < 0.00001;
	}
}
