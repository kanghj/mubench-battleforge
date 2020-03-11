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
package de.battleforge.gui.map;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import de.battleforge.action.ACTIONS;
import de.battleforge.action.ActionCallbackListener;
import de.battleforge.action.ActionManager;
import de.battleforge.action.ActionObject;
import de.battleforge.gui.StatusBar;
import de.battleforge.gui.dialog.admin.editSystem.EditSystem;
import de.battleforge.gui.map.route.BuildRoute;
import de.battleforge.gui.util.ImageFactory;
import de.battleforge.gui.util.PaintUtils;
import de.battleforge.gui.util.ResizeThread;
import de.battleforge.jdo.BFSystem;
import de.battleforge.jdo.DBWrapper;
import de.battleforge.sound.SoundPlayer;
import de.battleforge.sound.SoundPlayer.SOUNDTYPE;
import de.battleforge.util.Tools;

/**
 * <p>
 * Title: <b>Panel2DView</b><br>
 * Description: <i>The 2D Map</i><br>
 * Copyright: Copyright (c) 2004<br>
 * Company: BattleForge<br>
 * <br>
 * This class is preparing and painting the single frames of the map-animations.
 * Not only the single drawing of the map but also the drawing of all the
 * different effects that appear on it (like snow, radar-like rectangles and all
 * that). The class is implemented as a thread to perform the task of
 * repeatingly repaint the map (for animation). The class has its own
 * ActionListener and MouseListener. The ComponentListener is used to catch a
 * resize-event.
 * </p>
 * 
 * @author Meldric
 * @author kotzbrocken2
 * @version 1.0
 */
public class Panel2DView extends JPanel implements ComponentListener, MouseListener, MouseMotionListener, ActionCallbackListener {

    private class MenuAction extends AbstractAction {

        private BFSystem mSystem;

        private MenuAction(BFSystem system) {
            super(system.getName());

            mSystem = system;

        }

        public void actionPerformed(ActionEvent e) {
            setSelectedSystem(mSystem);

        }

    }

    /**
     * The offset of the map.
     */
    Point2D.Double mOffset;

    /**
     * This value represents the actual zoom value. It is used to calculate the
     * coordinates to display systems within the map.
     */
    private float mZoom;

    /**
     * the InfoFont
     */
    private Font mInfoFont;

    /**
     * FontMetrics for the InfoFont
     */
    private FontMetrics infoFontfm;

    /**
     * Stores the height of the infoFont
     */
    private int infoFontfmHeight;

    /**
     * Prepared Sound
     */
    private Stroke mBasicStroke3Float;

    private Color mColor3;

    private Color mColor1;

    private Stroke mBasicStroke1Float;

    private Stroke mBasicStroke18Float;

    private Stroke mBasicStroke4FloatDotted;

    private Color mColor2;

    private Color mColor4;

    private HashMap mSystemColors;

    private Stroke mBasicStroke2Float;

    private BufferedImage mMapImage;

    private BufferedImage mBackBuffer;

    private Image mBackgroundImage;

    private BufferedImage mScaledBackgroundImage;

    private Stroke mBasicStroke4Float;

    private Color mColor6;

    private boolean mNeedFullRepaint;

    private boolean mBackBufferNeedUpdate;

    private MouseEvent mLastMouseEvent;

    private Point mDistanceStartPoint;

    private boolean mPaintScramble;

    private int mScrambleCounter;

    private ResizeThread mResizeImagesThread;

    private TexturePaint mTextureSnow1;

    private TexturePaint mTextureSnow2;

    private TexturePaint mTextureSnow3;

    private BFSystem mSelectedSystem;

    private boolean mIsDragging;

    private Font mSystemFont[];

    private Component mOldGlassPane;

    private JPanel mGlassPane;

    private double mCatchZone;

    private DecimalFormat mDistanceFormat;

    // private ShapeMultiPath multiPath = new ShapeMultiPath();

    private boolean mMouseOver;

    public Panel2DView() {
        super(null);

        prepareFonts();
        prepareSnow();

        setNewCursor();

        initLocalVars();

        // add listeners
        addMouseListener(this);
        addMouseMotionListener(this);
        addComponentListener(this);

        ActionManager.addActionCallbackListener(ACTIONS.CHANGE_CURRENT_SYSTEM, this);

        setBackground(Color.BLACK);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setZoom(2, false);

        // start the animation
        Thread t = new Thread(new Animator());

        t.setName("Animator");
        t.setPriority(Thread.MIN_PRIORITY);
        t.setDaemon(true);
        t.start();

    } /* Panel2DView */

    public BFSystem getCurrentSystem() {
        return mSelectedSystem;
    }

    private void initLocalVars() {
        mColor1 = new Color(117, 117, 195, 60);
        mColor2 = new Color(118, 190, 118);
        mColor3 = new Color(255, 0, 0, 127);
        mColor4 = new Color(117, 117, 195);
        mColor6 = new Color(118, 190, 118, 60);

        float[] dotArray = new float[] { 10, 15 };

        mBasicStroke1Float = new BasicStroke();
        mBasicStroke2Float = new BasicStroke(2.0f);
        mBasicStroke3Float = new BasicStroke(3.0f);
        mBasicStroke4Float = new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        mBasicStroke18Float = new BasicStroke(18.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        mBasicStroke4FloatDotted = new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, dotArray, 0f);

        mSystemFont = new Font[16];

        mSystemFont[0] = new Font("Sanserif", Font.BOLD, 18);
        mSystemFont[1] = new Font("Sanserif", Font.BOLD, 16);
        mSystemFont[2] = new Font("Sanserif", Font.BOLD, 14);
        mSystemFont[3] = new Font("Sanserif", Font.BOLD, 12);
        mSystemFont[4] = new Font("Sanserif", Font.BOLD, 11);
        mSystemFont[5] = new Font("Sanserif", Font.BOLD, 10);
        mSystemFont[6] = new Font("Sanserif", Font.BOLD, 9);
        mSystemFont[7] = mSystemFont[6];
        mSystemFont[8] = new Font("Sanserif", Font.BOLD, 8);
        mSystemFont[9] = mSystemFont[8];
        mSystemFont[10] = new Font("Sanserif", Font.BOLD, 7);
        mSystemFont[11] = mSystemFont[10];
        mSystemFont[12] = mSystemFont[10];
        mSystemFont[13] = new Font("Sanserif", Font.BOLD, 6);
        mSystemFont[14] = mSystemFont[13];
        mSystemFont[15] = mSystemFont[13];

        mSystemColors = new HashMap();
        mDistanceFormat = new DecimalFormat("#0.00");

        mOffset = new Point2D.Double();

        mBackBufferNeedUpdate = true;
        mNeedFullRepaint = true;

        mResizeImagesThread = new ResizeThread(this);
        mResizeImagesThread.start();

        mGlassPane = new JPanel();
        mGlassPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        mBackgroundImage = ImageFactory.getImage("mapBackground.jpg");

    }

    private void prepareSnow() {
        Rectangle r = new Rectangle(0, 0, 32, 32);

        Image snow1 = ImageFactory.getImage("snow_1.png");
        Image snow2 = ImageFactory.getImage("snow_2.png");
        Image snow3 = ImageFactory.getImage("snow_3.png");

        BufferedImage subSnow1 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        BufferedImage subSnow2 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        BufferedImage subSnow3 = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);

        PaintUtils.drawScaledImageHQ(snow1, subSnow1);
        PaintUtils.drawScaledImageHQ(snow2, subSnow2);
        PaintUtils.drawScaledImageHQ(snow3, subSnow3);

        mTextureSnow1 = new TexturePaint(subSnow1, r);
        mTextureSnow2 = new TexturePaint(subSnow2, r);
        mTextureSnow3 = new TexturePaint(subSnow3, r);

    }

    /**
     * Makes up the used fonts
     */
    private void prepareFonts() {
        mInfoFont = new Font("Arial", Font.BOLD, 16);
        infoFontfm = getFontMetrics(mInfoFont);
        infoFontfmHeight = infoFontfm.getHeight();

    } /* prepareFonts */

    private void setNewCursor() {
        // set cursor for component
        Image cursorImage = ImageFactory.getImage("pointer.png");

        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(15, 15), "");
        setCursor(blankCursor);

    } /* setNewCursor */

    public void setZoom(float value) {
        setZoom(value, true);

    } // setZoom

    /**
     * setZoom
     * 
     * @param value
     *            dfsdf
     * 
     * @uml.property name="zoom"
     */
    private void setZoom(float value, boolean playSound) {
        double transformedCenterX = transformFromScreen(getWidth() / 2d) + mOffset.getX();
        double transformedCenterY = mOffset.getY() - transformFromScreen(getHeight() / 2d);

        mZoom = value;

        setOffsetFromCenter(transformedCenterX, transformedCenterY);

        mBackBufferNeedUpdate = true;

        if (playSound) {
            SoundPlayer.play(SOUNDTYPE.FX, "zoom");

        } // if
    } /* setZoom */

    /**
     * Getter for the zoom value
     * 
     * @return zoom as int
     * 
     * @uml.property name="zoom"
     */
    public float getZoom() {
        return mZoom;

    } /* getZoom */

    /**
     * Draw one point (for each system)
     * 
     * @param g
     *            graphics context
     * @param color
     *            color of the point (representing a system) --> factioncolor
     * @param x
     *            horizontal coordinate for the point
     * @param y
     *            vertical coordinate for the point
     * @param s
     *            system to be printed on the map
     */
    private void drawSystem(Graphics2D g, BFSystem s) {
        int radius = calculateRadius();

        int x = (int) transformToScreen(s.getX() - mOffset.x);
        int y = (int) transformToScreen(mOffset.y - s.getY());

        int x2 = x - (radius / 2);
        int y2 = y - (radius / 2);
        int offset = 3;
        int ro2 = radius + offset * 2;

        Rectangle2D bounds = new Rectangle(ro2, ro2);

        if (mZoom < 16) {
            bounds = mSystemFont[Math.round(mZoom)].getStringBounds(s.getName(), g.getFontRenderContext());
        }

        if ((x2 + ro2 + bounds.getWidth() < 0) || (y + y2 + ro2 < 0) || (x2 > getWidth()) || (y2 > getHeight())) {
            return;

        } /* if */

        // TODO: draw circle around Terra
        if (s.getName().equals("Terra")) {
            g.setStroke(mBasicStroke3Float);
            g.setColor(mColor3);
            g.drawOval(x2 - offset, y2 - offset, ro2, ro2);
            g.setStroke(mBasicStroke1Float);

        } /* if */

        // TODO: draw image instead of
        Color color = s.getOwner().getColor().getJavaColor();
        g.setColor(color);
        g.fillOval(x2, y2, radius, radius);

        // TODO: Display Radar
        // if ( s.hasRadar() ) {
        // g.drawString( "Has Radar", x + 10, y + 10 );
        //
        // } /* if */

        // TODO: Display Alert
        // if ( s.isAlarm() ) {
        // g.drawString( "Is on Alert", x + 10, y + 20 );
        //
        // } /* if */

        // draw some details if close enough
        if (mZoom < 16) {
            // set font
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g.setFont(mSystemFont[Math.round(mZoom)]);
            g.drawString(s.getName(), x + radius, y + radius / 2);

        } /* if */
    }

    private int calculateRadius() {
        return (int) (16 - (mZoom / 2 + 3));

    } // calculateRadius

    @Override
    protected void paintComponent(Graphics g) {
        updateBackBuffer();

        if (mNeedFullRepaint) {
            Graphics2D g2b = (Graphics2D) mBackBuffer.getGraphics();

            g2b.drawImage(mMapImage, 0, 0, this);

            paintEffects(g2b);

            if (mPaintScramble) {
                AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                g2b.setComposite(myAlpha);

                if (mScrambleCounter % 3 == 0) {
                    g2b.setPaint(mTextureSnow1);

                } else if (mScrambleCounter % 3 == 1) {
                    g2b.setPaint(mTextureSnow2);

                } else {
                    g2b.setPaint(mTextureSnow3);

                } // if

                g2b.fillRect(0, 0, getWidth(), getHeight());

                if (mScrambleCounter++ > 11) {
                    mPaintScramble = false;

                } /* if */
            } else {
                mNeedFullRepaint = false;

            } // if
        } // if

        g.drawImage(mBackBuffer, 0, 0, this);

    } /* paintComponent */

    private void updateBackBuffer() {
        if (mBackBufferNeedUpdate) {
            if ((mBackBuffer == null) || (getWidth() != mBackBuffer.getWidth(null)) || (getHeight() != mBackBuffer.getHeight(null))) {
                mMapImage = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());
                mBackBuffer = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());
                mScaledBackgroundImage = getGraphicsConfiguration().createCompatibleImage(getWidth(), getHeight());

                PaintUtils.drawScaledImageLQ(mBackgroundImage, mScaledBackgroundImage);

                mResizeImagesThread.addImage(mBackgroundImage, mScaledBackgroundImage);

            } /* if */

            paintMap(mMapImage.createGraphics());

            mBackBufferNeedUpdate = false;
            mNeedFullRepaint = true;

        } /* if */
    } /* updateBackBuffer */

    /**
     * Method paintEffects Paint effects over imageBuffer. The method gets a
     * buffered image from the caller (paintComponent) and draws all the effects
     * on it for the specific frame.
     * 
     * @param imageBuffer
     *            BufferedImage
     */
    private void paintEffects(Graphics2D g2) {
        if (!mIsDragging) {
            paintMouseFollow((Graphics2D) g2.create());

        } /* if */

        // paintSystemImage( (Graphics2D) g2.create() );

    } /* paintEffects */

    /**
     * @param g2
     */
    private void paintMouseFollow(Graphics2D g2) {
        if (!mMouseOver) {
            return;

        } // if

        int mx = mLastMouseEvent == null ? 0 : mLastMouseEvent.getX();
        int my = mLastMouseEvent == null ? 0 : mLastMouseEvent.getY();

        g2.setFont(mInfoFont);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // paint mouse follower
        g2.setColor(mColor6);
        g2.setStroke(mBasicStroke2Float);

        g2.drawLine(0, my, getWidth(), my);
        g2.drawLine(mx, 0, mx, getHeight());

        // paint distanceline
        if (mDistanceStartPoint != null) {
            g2.setColor(Color.YELLOW);
            g2.setStroke(mBasicStroke4Float);

            GeneralPath routePath = new GeneralPath();

            routePath.moveTo(mx, my);
            routePath.lineTo(mDistanceStartPoint.x, mDistanceStartPoint.y);

            g2.draw(routePath);

            // write the distance to the mousefollower
            double distance = BuildRoute.GetDiagonal(mSelectedSystem.getX(), mSelectedSystem.getY(), 0, transformFromScreen(mDistanceStartPoint.x)
                    + mOffset.x, transformFromScreen(mDistanceStartPoint.y) - mOffset.y, 0);

            double distance2 = BuildRoute.GetDiagonal(transformFromScreen(mx) + mOffset.x, +transformFromScreen(my) - mOffset.y, 0,
                    transformFromScreen(mDistanceStartPoint.x) + mOffset.x, transformFromScreen(mDistanceStartPoint.y) - mOffset.y, 0);

            g2.drawString("dist[" + mDistanceFormat.format(distance) + "]", mDistanceStartPoint.x, mDistanceStartPoint.y);

            if (distance2 > 2) {
                g2.drawString("dist[" + mDistanceFormat.format(distance2) + "]", mx, my);

            } /* if */
        } /* if */

        // paint the coordinates to the followers lines
        String xValue = "x[" + (Math.round(mOffset.x + transformFromScreen(mx))) + "]";
        String yValue = "y[" + (Math.round(mOffset.y - transformFromScreen(my))) + "]";

        // draw shadow text
        int shadowOffset = 2;

        int xValueXPos = mx - (infoFontfm.stringWidth(xValue) / 2);
        int xValueYPos = infoFontfmHeight + 5;
        int yValueXPos = 15;
        int yValueYPos = my + (infoFontfm.getAscent() / 2);

        g2.setColor(Color.BLACK);
        g2.drawString(xValue, xValueXPos + shadowOffset, xValueYPos + shadowOffset);
        g2.drawString(yValue, yValueXPos + shadowOffset, yValueYPos + shadowOffset);

        // draw text
        g2.setColor(mColor2);
        g2.drawString(xValue, xValueXPos, xValueYPos);
        g2.drawString(yValue, yValueXPos, yValueYPos);

    } /* paintMouseFollow */

    /**
     * Paint the Map completly. All stars and texts are displayed. This method
     * is called only, if a complete repaint of the whole map is necessary
     * 
     * @param g2
     *            the graphics to paint the map on
     */
    private void paintMap(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        // draw panel here
        g2.setColor(mColor2);

        // draw background
        g2.drawImage(mScaledBackgroundImage, 0, 0, this);

        // update statusbar
        StatusBar.gibExemplar().setZoom(Math.round(105 - (mZoom * 5)));

        // the route paints behind the systems
        // therefore first of all
        // TODO change the way a jumpship is selected
        // ArrayList myArray =
        // Tools.getSelectedJumpship().getUnitMovementsHist();

        ArrayList myArray = new ArrayList();

        g2.setStroke(mBasicStroke1Float);

        // draw the systems
        // myArray = Universe.alUniverse;
        //
        // HashMap systemBoundaries = new HashMap();
        //        
        // for ( int j = 0, unitCount = myArray.size(); j < unitCount; j++ ) {
        // final Systems s = (Systems) myArray.get( j );
        //
        // String[] sa = colorString.split( "," );
        //
        // Color systemColor = (Color) mSystemColors.get( colorString );
        //
        // if ( systemColor == null ) {
        // systemColor = new Color( Integer.parseInt( sa[0] ), Integer.parseInt(
        // sa[1] ), Integer.parseInt( sa[2] ) );
        //
        // mSystemColors.put( colorString, systemColor );
        //
        // } /* if */
        //
        // Owner owner = s.getOwnerClass();
        //            
        // ArrayList bounds = (ArrayList) systemBoundaries.get( owner );
        //            
        // if ( bounds == null ) {
        //                
        // bounds = new ArrayList( 700 );
        //                
        // systemBoundaries.put( owner, bounds );
        //                
        // } // if
        //            
        // bounds.add( new Point2D.Double( s.getXCor(), s.getYCor() ) );
        //            
        // } /* for */
        //        
        // Iterator iter = systemBoundaries.values().iterator();
        //        
        // HashMap hulls = new HashMap();
        //        
        // ArrayList allSystemLocations = new ArrayList( 3000 );
        //        
        // while ( iter.hasNext() ) {
        // ArrayList bounds = (ArrayList) iter.next();
        //            
        // allSystemLocations.addAll( bounds );
        //        
        // } // while
        //
        // iter = systemBoundaries.entrySet().iterator();
        //        
        // while ( iter.hasNext() ) {
        // Map.Entry entry = (Entry) iter.next();
        //            
        // ArrayList bounds = (ArrayList) entry.getValue();
        //            
        // if ( bounds.size() > 5 ) {
        // ArrayList otherSystems = new ArrayList( allSystemLocations );
        //                
        // otherSystems.removeAll( bounds );
        //                
        // Point2D[] points = new Point2D[ bounds.size() ];
        // Point2D[] otherPoints = new Point2D[ otherSystems.size() ];
        //                
        // bounds.toArray( points );
        // otherSystems.toArray( otherPoints );
        //                
        // hulls.put( entry.getKey(), new ChainHull( points, otherPoints ) );
        //                
        // } /* if */
        // } // while
        //        
        // System.out.println( "x: " + mSelectedSystem.getXCor() );
        // System.out.println( "y: " + mSelectedSystem.getYCor() );
        //
        // iter = hulls.entrySet().iterator();
        //        
        // ChainHull[] hullsArray = (ChainHull[]) hulls.values().toArray( new
        // ChainHull[hulls.size()] );
        //        
        // while ( iter.hasNext() ) {
        // Map.Entry entry = (Entry) iter.next();
        //            
        // ChainHull currentHull = (ChainHull) entry.getValue();
        //            
        // Point2D[] points = currentHull.getPoints();
        //            
        // ControlPath cp = new ControlPath();
        //            
        // Polygon polygon = new Polygon();
        //
        // for ( int j = 0; j < points.length; j++ ) {
        //                
        // final double x = points[j].getX();
        // final double y = points[j].getY();
        //                
        // polygon.addPoint( (int) transformToScreen( x - mOffset.x ), (int)
        // transformToScreen( mOffset.y - y ) );
        //                
        // cp.addPoint( new com.graphbuilder.curve.Point() {
        //              
        // private double[] loc = new double[] { transformToScreen( x -
        // mOffset.x ), transformToScreen( mOffset.y - y ) };
        //
        // public void setLocation( double[] arg0 ) {
        // loc = arg0;
        //                  
        // }
        //
        // public double[] getLocation() {
        // return loc;
        // }
        // });
        // } // for
        //            
        // ParametricCurve spline = new NaturalCubicSpline(cp, new
        // GroupIterator("0:n-1", cp.numPoints()));//NaturalCubicSpline(cp, new
        // GroupIterator("0:n-1", cp.numPoints()));
        //            
        // cp.addCurve( spline );
        //            
        // multiPath.setNumPoints(0);
        //            
        // spline.appendTo( multiPath );
        //            
        //            
        // Graphics2D g2a = (Graphics2D) g2.create();
        //
        // AlphaComposite myAlpha = AlphaComposite.getInstance(
        // AlphaComposite.SRC_OVER, 0.2f );
        //
        // g2a.setComposite( myAlpha );
        //            
        // g2a.setColor( (Color) mSystemColors.get( ((Owner) entry.getKey()
        // ).getColor() ) );
        // g2.setColor( (Color) mSystemColors.get( ((Owner) entry.getKey()
        // ).getColor() ) );
        //            
        // g2.draw( polygon );
        //
        // g2a.fill(multiPath);
        // g2.draw(multiPath);
        //            
        // g2a.dispose();
        //
        // }

        // for ( int j = 0, unitCount = myArray.size(); j < unitCount; j++ ) {
        // Systems s = (Systems) myArray.get( j );

        BFSystem[] systems = DBWrapper.getAllSystems();

        for (BFSystem system : systems) {
            // Color systemColor = (Color) mSystemColors.get(
            // s.getOwnerClass().getColor() );

            drawSystem(g2, system);

        } /* for */

        // draw some radar lines
        g2.setColor(mColor2);

        int x = (int) transformToScreen(mSelectedSystem.getX() - mOffset.getX());
        int y = (int) transformToScreen(mOffset.getY() - mSelectedSystem.getY());

        g2.drawLine(x, 0, x, getHeight());
        g2.drawLine(0, y, getWidth(), y);

        int catchZoneDisplay = (int) (3 + (20 - mZoom));
        mCatchZone = transformFromScreen(catchZoneDisplay);

        // define the catchzone
        int catchWidth = catchZoneDisplay * 2;
        int catchHeight = catchWidth;
        int catchUpLeftX = x - catchZoneDisplay;
        int catchUpLeftY = y - catchZoneDisplay;

        // draw the catchzone
        g2.drawRect(catchUpLeftX, catchUpLeftY, catchWidth, catchHeight);

        if (mZoom < 12) {
            // draw jump radius 30 LJ
            int jrX = (int) transformToScreen(60);
            int jrY = jrX;

            g2.setColor(mColor2);
            g2.drawOval(x - (jrX / 2), y - (jrY / 2), jrX, jrY);

            // draw jump radius 60 LJ
            jrX = (int) transformToScreen(120);
            jrY = jrX;

            g2.setColor(mColor2);
            g2.drawOval(x - (jrX / 2), y - (jrY / 2), jrX, jrY);

        } // if
    } // paintMap

    /**
     * @inheritDoc
     */
    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        if ((arg0 == mBackgroundImage) || (arg0 == mScaledBackgroundImage)) {
            mBackBufferNeedUpdate = true;

        } /* if */

        return super.imageUpdate(arg0, arg1, arg2, arg3, arg4, arg5);

    } // imageUpdate

    /**
     * Interface MouseListener: mouseClicked
     * 
     * @param me
     *            MouseEvent
     */
    public void mouseClicked(final MouseEvent me) {
        if (me.getButton() == 1) {
            if (me.getClickCount() == 1) {
                double translatedX = transformFromScreen(me.getX()) + mOffset.getX();
                double translatedY = mOffset.getY() - transformFromScreen(me.getY());

                ArrayList selectedSystems = grabSystems(translatedX, translatedY);

                if ((me.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == InputEvent.SHIFT_DOWN_MASK) {

                    // TODO HACK
                    if (!selectedSystems.isEmpty()) {
                        BFSystem s = (BFSystem) selectedSystems.get(0);

                        firePropertyChange("waypoint", null, s);

                    } /* if */
                } else if (selectedSystems.size() == 1) {
                    // exactly one system was chosen, so no need for drawing 2
                    // times
                    // set the offsetvalues directly to the target system
                    // we do not want a repaint, so we do not call
                    // setOffset(x,y);
                    setSelectedSystem((BFSystem) selectedSystems.get(0));

                    // if ctrl was pressed, open the edit dialog
                    if ((me.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) == InputEvent.CTRL_DOWN_MASK) {

                        // Edit the system with control-click
                        EditSystem mySystemEditor = new EditSystem(Tools.getFirstShowingFrame(), mSelectedSystem);
                        mySystemEditor.setVisible(true);

                        fullRepaint();
                    }

                } else if (selectedSystems.size() > 1) {
                    // Create the submenu items
                    JPopupMenu popupMenu = new JPopupMenu();

                    Iterator it02 = selectedSystems.iterator();

                    while (it02.hasNext()) {
                        BFSystem s = (BFSystem) it02.next();

                        JMenuItem dummy = new JMenuItem(new MenuAction(s));
                        popupMenu.add(dummy);

                    } /* while */

                    popupMenu.show(this, me.getX(), me.getY());

                } else {
                    setOffsetFromCenter(translatedX, translatedY);

                } /* if */

            } else if (me.getClickCount() == 2) {
                // Doppelclick here
            }
        } /* if */
    } /* mouseClicked */

    /**
     * Returns a <code>ArrayList</code> with the systems at the given point.
     * The systems will be grabbed if they are in the range of the point +/- a
     * catch-area.
     * 
     * @param x
     *            the x-value in world-coordinates
     * @param y
     *            the y-value in world-coordinates
     * 
     * @return the <code>ArrayList</code> with the catched systems, it would
     *         be empty if no system where catched
     */
    private ArrayList grabSystems(double x, double y) {
        ArrayList selectedSystems = new ArrayList();

        // ArrayList systems = Universe.alUniverse;

        BFSystem[] systems = DBWrapper.getAllSystems();

        // for ( int i = 0, count = systems.size(); i < count; i++ ) {
        // BFSystem s = (BFSystem) systems.get( i );
        for (BFSystem system : systems) {
            if (isSystemGrabbed(system, x, y)) {
                selectedSystems.add(system);

            } /* if */
        } /* for */

        return selectedSystems;

    } // grabSystems

    /**
     * Returns <code>true</code> if the given system lies within the
     * catchzone.
     * 
     * @param s
     *            the system to check
     * @param x
     *            the x-value in world-coordinates
     * @param y
     *            the y-value in world-coordinates
     * 
     * @return <code>true</code> if the given system is catched
     */
    private boolean isSystemGrabbed(BFSystem s, double x, double y) {
        return (Math.abs(s.getX() - x) < mCatchZone) && (Math.abs(s.getY() - y) < mCatchZone);

    } // isSystemGrabbed

    /**
     * Sets the given system as selected system. The map is moved that way so
     * the new selected system will be in the middle of the map.
     * 
     * @param s
     *            the system to select
     */
    public void setSelectedSystem(BFSystem s) {
        if ((s != null) && (s != mSelectedSystem)) {
            mSelectedSystem = s;

            setOffsetFromCenter(mSelectedSystem.getX(), mSelectedSystem.getY());

            ActionManager.getAction(ACTIONS.CHANGE_CURRENT_SYSTEM).execute(s);

        } /* if */
    } /* setSelectedSystem */

    /**
     * Calculates and sets the new offset of the map. This is calulated in
     * respect of the current zoom-level.
     * 
     * @param centerX
     *            the
     * @param centerY
     */
    private void setOffsetFromCenter(double centerX, double centerY) {
        double offsetX = centerX - transformFromScreen(((double) getWidth()) / 2);
        double offsetY = centerY + transformFromScreen(((double) getHeight()) / 2);

        mOffset.setLocation(offsetX, offsetY);

        mBackBufferNeedUpdate = true;

    } // setOffsetFromCenter

    /**
     * Applies the zoom to the given world-coordinate so the new value is the
     * screen-value of the world-coordinate.
     * 
     * @param cor
     *            the world-coordinate to transform
     * 
     * @return the calculated screen-coordinate
     */
    double transformToScreen(double cor) {
        if (mZoom > 15) {
            return cor / (mZoom - 14);

        } // if

        return cor * (15 / mZoom);

    } // transformToScreen

    /**
     * Applies the zoom to the given screen-coordinate so the new value is the
     * world-value of the screen-coordinate.
     * 
     * @param cor
     *            the screen-coordinate to transform
     * 
     * @return the calculated world-coordinate
     */
    private double transformFromScreen(double cor) {
        if (mZoom > 15) {
            return cor * (mZoom - 14);

        } // if

        return cor * (mZoom / 15);

    } // transformFromScreen

    /**
     * Interface MouseListener: mouseEntered
     * 
     * @param me
     *            MouseEvent
     */
    public final void mouseEntered(final MouseEvent me) {
        mMouseOver = true;

        mNeedFullRepaint = true;

    } /* mouseEntered */

    /**
     * Interface MouseListener: mouseExited
     * 
     * @param me
     *            MouseEvent
     */
    public final void mouseExited(final MouseEvent me) {
        mMouseOver = false;

        mNeedFullRepaint = true;

    } /* mouseExited */

    /**
     * Interface MouseListener: mousePressed
     * 
     * @param me
     *            MouseEvent
     */
    public final void mousePressed(MouseEvent me) {
        mLastMouseEvent = me;

        if ((me.getModifiersEx() & InputEvent.BUTTON3_DOWN_MASK) == InputEvent.BUTTON3_DOWN_MASK) {
            mDistanceStartPoint = me.getPoint();

        } else {
            mDistanceStartPoint = null;

        } /* if */
    } /* mousePressed */

    /**
     * Interface MouseListener: mouseReleased
     * 
     * @param me
     *            MouseEvent
     */
    public final void mouseReleased(final MouseEvent me) {
        mLastMouseEvent = me;

        if (mIsDragging) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

            mGlassPane.setVisible(false);

            frame.setGlassPane(mOldGlassPane);

            mNeedFullRepaint = true;

        } /* if */

        mIsDragging = false;

        mNeedFullRepaint = mNeedFullRepaint ? mNeedFullRepaint : mDistanceStartPoint != null;

        mDistanceStartPoint = null;

    } /* mouseReleased */

    /**
     * Interface MouseListener: mouseReleased
     * 
     * @param me
     *            MouseEvent
     */
    public final void mouseDragged(final MouseEvent me) {
        if (mIsDragging || (me.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK)) {
            if (!mIsDragging) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

                mOldGlassPane = frame.getGlassPane();

                frame.setGlassPane(mGlassPane);

                mGlassPane.setVisible(true);
                mGlassPane.setOpaque(false);

            } /* if */

            double deltaX = transformFromScreen(me.getX() - mLastMouseEvent.getX());
            double deltaY = transformFromScreen(me.getY() - mLastMouseEvent.getY());

            mLastMouseEvent = me;

            mIsDragging = true;

            mOffset.x -= deltaX;
            mOffset.y += deltaY;

            mBackBufferNeedUpdate = true;

        } else {
            mLastMouseEvent = me;

            mNeedFullRepaint = true;

        } // if
    } /* mouseDragged */

    public void fullRepaint() {
        mBackBufferNeedUpdate = true;
        repaint();
    }

    /**
     * Interface MouseListener: mouseReleased
     * 
     * @param me
     *            MouseEvent
     */
    public final void mouseMoved(final MouseEvent me) {
        mLastMouseEvent = me;

        mNeedFullRepaint = true;

    } // mouseMoved

    /**
     * Interface ActionListener: actionPerformed
     * 
     * @param e
     *            ActionEvent
     */
    // public final void actionPerformed( final ActionEvent e ) {
    // setSelectedSystem( Tools.getSystemByString( e.getActionCommand() ) );
    //
    // } // actionPerformed

    /**
     * Interface ComponentListener: componentMoved
     * 
     * @param ce
     *            ComponentHidden
     */
    public final void componentHidden(final ComponentEvent ce) {
        // do nothing

    } // componentHidden

    /**
     * Interface ComponentListener: componentMoved
     * 
     * @param ce
     *            ComponentEvent
     */
    public final void componentMoved(final ComponentEvent ce) {
        // do nothing

    } // componentMoved

    /**
     * Interface ComponentListener: componentResized
     * 
     * @param ce
     *            ComponentEvent
     */
    public final void componentResized(final ComponentEvent ce) {
        setOffsetFromCenter(mSelectedSystem.getX(), mSelectedSystem.getY());

        mBackBufferNeedUpdate = true;

    } // componentResized

    /**
     * Interface ComponentListener: componentShown
     * 
     * @param ce
     *            ComponentEvent
     */
    public final void componentShown(final ComponentEvent ce) {
        // do nothing

    } // componentShown

    /**
     * Sets the given list as the new waypoints.
     * 
     * @param list
     *            a list with the new waypoints, can be <code>null</code> if
     *            there are no waypoints
     */
    public void setWayPoints(ArrayList list) {
        mBackBufferNeedUpdate = true;

    } // setWayPoints

    private class Animator implements Runnable {

        private long mLastScrambleTimeStamp;

        private int mScrambleDelay;

        /**
         * The actual fps-counter
         */
        private int mFps = 0;

        /**
         * Help counter for frame calculation
         */
        private int mFpsCounter = 0;

        private long mLastPaintTimeStamp;

        public void run() {
            while (true) {
                try {
                    Thread.sleep(30);

                } catch (InterruptedException e) {
                    // ignore

                }

                long timestamp = System.currentTimeMillis();

                if (timestamp - mLastPaintTimeStamp < 1000) {
                    mFpsCounter++;

                } else {
                    mLastPaintTimeStamp = timestamp;

                    mFps = mFpsCounter;

                    mFpsCounter = 0;

                } /* if */

                if (mLastScrambleTimeStamp + mScrambleDelay < timestamp) {
                    mScrambleDelay = (int) Math.round(Math.random() * 20000) + 5000;
                    mLastScrambleTimeStamp = timestamp;
                    mScrambleCounter = 0;

                    if (!mIsDragging) {
                        mPaintScramble = true;
                        mNeedFullRepaint = true;

                    } /* if */
                } /* if */

                repaint();

                // print the fps to statusbar
                StatusBar.gibExemplar().setFps(mFps);

            } // while
        } // run
    } // end of inner-class Animator

    public boolean handleAction(ACTIONS action, ActionObject object) {
        switch (action) {
        case CHANGE_CURRENT_SYSTEM:
            setSelectedSystem((BFSystem) object.getObject());
        }

        return true;

    } // handleAction
} /* end of class Panel2DView */