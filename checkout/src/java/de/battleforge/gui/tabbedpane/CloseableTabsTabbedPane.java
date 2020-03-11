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
package de.battleforge.gui.tabbedpane;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;

/**
 * @author ziegenbalg
 * 
 */
public class CloseableTabsTabbedPane extends JTabbedPane {

    private static final String uiClassID = "CloseableTabTabbedPaneUI";

    private ArrayList<Boolean> mCloseableMarks = new ArrayList<Boolean>();

    private EventListenerList mListenerList = new EventListenerList();

    /**
     * 
     */
    public CloseableTabsTabbedPane() {
        super();

    } /* CloseableTabsTabbedPane */

    /**
     * @param tabPlacement
     */
    public CloseableTabsTabbedPane(int tabPlacement) {
        super(tabPlacement);

    } /* CloseableTabsTabbedPane */

    /**
     * @param tabPlacement
     * @param tabLayoutPolicy
     */
    public CloseableTabsTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);

    } /* CloseableTabsTabbedPane */

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        mCloseableMarks.add(index, Boolean.TRUE);

        super.insertTab(title, icon, component, tip, index);

    }

    @Override
    public void removeTabAt(int index) {
        // try {
        // fireTabWillBeRemoved( index );
        //	        
        mCloseableMarks.remove(index);

        super.removeTabAt(index);
        //
        // fireTabRemoved( index );
        //			
        // } catch (TabCloseVetoException e) {
        //
        // }

    }

    // private void fireTabRemoved(int index) {
    // TabCloseListener[] listeners = mListenerList.getListeners(
    // TabCloseListener.class );
    //		
    // TabCloseEvent event = new TabCloseEvent( this, index );
    //		
    // for (TabCloseListener listener : listeners) {
    // listener.tabClosed( event );
    //			
    // } // for
    // }

    // private void fireTabWillBeRemoved(int index) throws TabCloseVetoException
    // {
    // TabCloseListener[] listeners = mListenerList.getListeners(
    // TabCloseListener.class );
    //		
    // TabCloseEvent event = new TabCloseEvent( this, index );
    //		
    // for (TabCloseListener listener : listeners) {
    // listener.tabWillBeClosed( event );
    //			
    // } // for
    // }
    //

    public void setTabClosable(int index, boolean closeable) {
        if (index >= getTabCount()) {
            throw new IllegalArgumentException("index >= getTabCount()");

        } // if

        mCloseableMarks.set(index, closeable);

    } // setTabClosable

    public boolean isTabCloseable(int index) {
        if (index >= getTabCount()) {
            throw new IllegalArgumentException("index >= getTabCount()");

        } // if

        return mCloseableMarks.get(index);

    }

    public void addTab(String title, Component component, boolean isCloseable) {
        super.addTab(title, component);

        setTabClosable(indexOfComponent(component), isCloseable);

    }

    public void addTabCloseListener(TabCloseListener listener) {
        mListenerList.add(TabCloseListener.class, listener);

    }

    public void removeTabCloseListener(TabCloseListener listener) {
        mListenerList.remove(TabCloseListener.class, listener);

    }

    public TabCloseListener[] getTabCloseListeners() {
        return mListenerList.getListeners(TabCloseListener.class);

    }

    /**
     * Returns the name of the UI class that implements the L&F for this
     * component.
     * 
     * @return the string "TabbedPaneUI"
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     */
    @Override
    public String getUIClassID() {
        return uiClassID;

    } /* getUIClassID */

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#updateUI()
     */
    @Override
    public void updateUI() {
        checkAndregisterUIClass();

        super.updateUI();

    } /* updateUI */

    /**
     * 
     */
    private static void checkAndregisterUIClass() {
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        String uiClassName = CloseableTabsTabbedPane.class.getPackage().getName() + ".ui." + UIManager.getLookAndFeel().getID() + uiClassID;

        if (defaults.get(uiClassID) == null) {
            defaults.putDefaults(new Object[] { uiClassID, uiClassName });

        } /* if */
    } /* checkAndRegisterUIClass */
} /* end of class CloseableTabsTabbedPane */