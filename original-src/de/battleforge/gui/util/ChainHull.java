/* This software is free; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package de.battleforge.gui.util;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.ArrayStack;

public class ChainHull {

    private Point2D[] mPoints;

    private GeneralPath mPolygon;

    public ChainHull(Point2D[] points, Point2D[] otherPoints) {
        build(points, otherPoints);

        mPolygon = new GeneralPath();

        for (int i = 0; i < mPoints.length - 1; i++) {
            mPolygon.append(new Line2D.Double(mPoints[i].getX(), -mPoints[i].getY(), mPoints[++i].getX(), -mPoints[i].getY()), true);

        } // for

        mPolygon.closePath();

    } // ChainHull

    public Point2D[] getPoints() {
        return mPoints;

    }

    public Shape getPolygon() {
        return mPolygon;

    }

    private void build(Point2D[] points, Point2D[] otherPoints) {
        TreeSet sortedPointsX = new TreeSet(new PointComparatorX());
        TreeSet sortedPointsY = new TreeSet(new PointComparatorY());

        TreeSet sortedOtherPointsX = new TreeSet(new PointComparatorX());
        TreeSet sortedOtherPointsY = new TreeSet(new PointComparatorY());

        for (Point2D element : points) {
            sortedPointsX.add(element);
            sortedPointsY.add(element);

        } // for

        for (Point2D element : otherPoints) {
            sortedOtherPointsX.add(element);
            sortedOtherPointsY.add(element);

        } // for

        Point2D p = (Point2D) sortedPointsX.first();

        double minX = p.getX();

        p = (Point2D) sortedPointsX.last();

        double maxX = p.getX();

        p = (Point2D) sortedPointsY.first();

        double minY = p.getY();

        p = (Point2D) sortedPointsY.last();

        double maxY = p.getY();

        Point2D min = new Point2D.Double(minX, minY);
        Point2D max = new Point2D.Double(maxX, maxY);

        SortedSet sortedOtherPointsX1 = sortedOtherPointsX.subSet(min, max);
        SortedSet sortedOtherPointsY1 = sortedOtherPointsY.subSet(min, max);

        System.out.println(sortedOtherPointsY1.retainAll(sortedOtherPointsX1));
        System.out.println(sortedOtherPointsX1.retainAll(sortedOtherPointsY1));
        System.out.println(sortedOtherPointsY1.retainAll(sortedOtherPointsX1));
        System.out.println(sortedOtherPointsX1.retainAll(sortedOtherPointsY1));

        // check for runaways

        // Iterator iter = sortedPointsX.iterator();
        //        
        // Point2D previousPoint = null;
        //        
        // ArrayList pointsToRemove = new ArrayList();
        //        
        // while ( iter.hasNext() ) {
        // Point2D p = (Point2D) iter.next();
        //            
        // if ( previousPoint != null && previousPoint.getX() + MAX_DELTA <
        // p.getX() ) {
        // pointsToRemove.add( previousPoint );
        //                
        // } // if
        //            
        // previousPoint = p;
        //            
        // } // while
        //
        // iter = sortedPointsY.iterator();
        //
        // while ( iter.hasNext() ) {
        // Point2D p = (Point2D) iter.next();
        //            
        // if ( previousPoint != null && previousPoint.getY() + MAX_DELTA <
        // p.getY() ) {
        // pointsToRemove.add( p );
        //                
        // } // if
        //            
        // previousPoint = p;
        //            
        // } // while
        //        
        // sortedPointsX.removeAll( pointsToRemove );

        Point2D[] P = new Point2D[sortedPointsX.size()];

        sortedPointsX.toArray(P);

        // the output array H[] will be used as the stack
        // int bot=0, top=(-1); // indices for bottom and top of the stack
        int i; // array scan index
        int n = P.length;

        // Get the indices of points with min x-coord and min|max y-coord
        int minmin = 0;
        int minmax;
        double xmin = P[0].getX();

        for (i = 1; i < P.length; i++) {
            if (P[i].getX() != xmin) {
                break;

            }
        }

        minmax = i - 1;

        ArrayStack lowerStack = new ArrayStack();

        if (minmax == P.length - 1) { // degenerate case: all x-coords == xmin
            lowerStack.push(P[minmin]);

            if (P[minmax].getY() != P[minmin].getY()) { // a nontrivial segment
                lowerStack.push(P[minmax]);

            } // if

            lowerStack.push(P[minmin]); // add polygon endpoint

            mPoints = new Point2D[lowerStack.size()];

            lowerStack.toArray(mPoints);

            return;

        }

        // Get the indices of points with max x-coord and min|max y-coord
        int maxmin, maxmax = P.length - 1;
        double xmax = P[n - 1].getX();

        for (i = n - 2; i >= 0; i--) {
            if (P[i].getX() != xmax) {
                break;

            } // if
        } // for

        maxmin = i + 1;

        // Compute the lower hull on the stack H
        lowerStack.push(P[minmin]); // push minmin point onto stack
        i = minmax;

        TreeSet tmpSortedX = new TreeSet(new PointComparatorX());

        while (++i <= maxmin) {
            // the lower line joins P[minmin] with P[maxmin]
            if (isLeft(P[minmin], P[maxmin], P[i]) && (i < maxmin)) {
                continue; // ignore P[i] above or on the lower line

            }

            while (lowerStack.size() > 1) { // there are at least 2 points on
                                            // the stack
                // test if P[i] is left of the line at the stack top
                if (isLeft((Point2D) lowerStack.peek(1), (Point2D) lowerStack.peek(), P[i])) {
                    break; // P[i] is a new hull vertex

                }

                lowerStack.pop(); // pop top point off stack

            }

            lowerStack.push(P[i]); // push P[i] onto stack

        }

        // Next, compute the upper hull on the stack H above the bottom hull
        if (maxmax != maxmin) {
            // if distinct xmax points
            lowerStack.push(P[maxmax]); // push maxmax point onto stack

        } // if

        // bot = top; // the bottom point of the upper hull stack

        i = maxmin;

        ArrayStack upperStack = new ArrayStack();

        while (--i >= minmax) {
            // the upper line joins P[maxmax] with P[minmax]
            if (isLeft(P[maxmax], P[minmax], P[i]) && (i > minmax)) {
                continue; // ignore P[i] below or on the upper line
            }

            while (upperStack.size() > 1) // at least 2 points on the upper
                                            // stack
            {
                // test if P[i] is left of the line at the stack top
                if (isLeft((Point2D) upperStack.peek(1), (Point2D) upperStack.peek(), P[i])) {
                    break; // P[i] is a new hull vertex

                } // if

                upperStack.pop(); // pop top point off stack

            }

            upperStack.push(P[i]); // push P[i] onto stack

        }

        if (minmax != minmin) {
            upperStack.push(P[minmin]); // push joining endpoint onto stack

        }

        mPoints = new Point2D[lowerStack.size() + upperStack.size()];

        Point2D[] lower = new Point2D[lowerStack.size()];
        Point2D[] upper = new Point2D[upperStack.size()];

        lowerStack.toArray(lower);
        upperStack.toArray(upper);

        System.arraycopy(lower, 0, mPoints, 0, lower.length);
        System.arraycopy(upper, 0, mPoints, lower.length, upper.length);

        return;

    }

    private boolean isLeft(Point2D p0, Point2D p1, Point2D testPoint) {
        return (p1.getX() - p0.getX()) * (testPoint.getY() - p0.getY()) - (testPoint.getX() - p0.getX()) * (p1.getY() - p0.getY()) > 0;

    } // isLeft

    private class PointComparatorX implements Comparator {

        public int compare(Object arg0, Object arg1) {
            Point2D p1 = (Point2D) arg0;
            Point2D p2 = (Point2D) arg1;

            if (p1.getX() < p2.getX()) {
                return -1;

            } else if ((p1.getX() == p2.getX()) && (p1.getY() == p2.getY())) {
                return 0;

            } // if

            return 1;

        } // compare
    } // end of inner-class PointComparatorX

    private class PointComparatorY implements Comparator {

        public int compare(Object arg0, Object arg1) {
            Point2D p1 = (Point2D) arg0;
            Point2D p2 = (Point2D) arg1;

            if (p1.getY() < p2.getY()) {
                return -1;

            } else if ((p1.getX() == p2.getX()) && (p1.getY() == p2.getY())) {
                return 0;

            } // if

            return 1;

        } // compare
    } // end of inner-class PointComparatorY
} // end of class QuickHull
