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
package de.battleforge.action;

import java.lang.ref.SoftReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections.map.AbstractReferenceMap;
import org.apache.commons.collections.map.ReferenceMap;

public abstract class ActionManager {

    private static ReferenceMap sActions = new ReferenceMap(AbstractReferenceMap.SOFT, AbstractReferenceMap.SOFT);

    private static ReferenceMap sActionCallbackListener = new ReferenceMap(AbstractReferenceMap.SOFT, AbstractReferenceMap.HARD);

    private static Set<SoftReference<ActionCallbackListener>> sActionCallbackListenerSet = new HashSet<SoftReference<ActionCallbackListener>>();

    private ActionManager() {
        // only to prevent inheritance

    } // ActionManager

    public static BFAction getAction(String name, ACTIONS actionEnum) {
        return getAction(name, null, null, actionEnum);

    } // getAction

    public static BFAction getAction(ACTIONS actionEnum) {
        return getAction(null, null, null, actionEnum);

    } // getAction

    public static BFAction getAction(String name, String tooltip, ACTIONS actionEnum) {
        return getAction(name, tooltip, null, actionEnum);

    } // getAction

    public static BFAction getAction(String name, String tooltip, String icon, ACTIONS actionEnum) {
        BFAction action = (BFAction) sActions.get(actionEnum);

        if (action == null) {
            action = new BFAction(name, tooltip, icon, actionEnum);

            sActions.put(actionEnum, action);

        } // if

        return action;

    } // getAction

    public static boolean addActionCallbackListener(ACTIONS actions, ActionCallbackListener listener) {
        Set<SoftReference<ActionCallbackListener>> set = (Set<SoftReference<ActionCallbackListener>>) sActionCallbackListener.get(actions);

        if (set == null) {
            set = new HashSet<SoftReference<ActionCallbackListener>>();

            sActionCallbackListener.put(actions, set);

        } // if

        return set.add(new SoftReference<ActionCallbackListener>(listener));

    } // addActionCallbackListener

    public static boolean removeActionCallbackListener(ACTIONS actions, ActionCallbackListener listener) {
        Set<SoftReference<ActionCallbackListener>> set = (Set<SoftReference<ActionCallbackListener>>) sActionCallbackListener.get(actions);

        if (set == null) {
            return false;

        } // if
        
        Iterator<SoftReference<ActionCallbackListener>> iterator = set.iterator();
        
        while (iterator.hasNext()) {
            SoftReference<ActionCallbackListener> element = iterator.next();
            
            if (element.get() == null || element.get() == listener) {
                iterator.remove();

                return true;

            }
        }

        return false;

    } // removeActionCallbackListener

    public static boolean addActionCallbackListener(ActionCallbackListener listener) {
        return sActionCallbackListenerSet.add(new SoftReference<ActionCallbackListener>(listener));

    } // addActionCallbackListener

    public static boolean removeActionCallbackListener(ActionCallbackListener listener) {
        Iterator<SoftReference<ActionCallbackListener>> iterator = sActionCallbackListenerSet.iterator();
        
        while (iterator.hasNext()) {
            SoftReference<ActionCallbackListener> element = iterator.next();
            
            if (element.get() == null || element.get() == listener) {
                iterator.remove();

                return true;

            }
        }
        
        return false;

    } // removeActionCallbackListener

    public static ActionCallbackListener[] getActionCallbackListener() {
        return setToArray(sActionCallbackListenerSet);

    } // getActionCallbackListener

    public static ActionCallbackListener[] getActionCallbackListener(ACTIONS actions) {
        return setToArray((Set<SoftReference<ActionCallbackListener>>)sActionCallbackListener.get(actions));

    } // getActionCallbackListener
    
    
    private static ActionCallbackListener[] setToArray(Set<SoftReference<ActionCallbackListener>> set) {
        if (set == null) {
            return new ActionCallbackListener[0];

        } // if

        Iterator<SoftReference<ActionCallbackListener>> iterator = set.iterator();

        ActionCallbackListener[] array = new ActionCallbackListener[set.size()];
        int i = 0;
        
        while (iterator.hasNext()) {
            SoftReference<ActionCallbackListener> element = iterator.next();
            
            if (element.get() == null) {
                iterator.remove();

            } else {
                array[i++] = element.get();
                
            }
        }
        int size = i+1;
        
        if ( array.length > size ) {
            ActionCallbackListener[] newArray = new ActionCallbackListener[size];
            System.arraycopy(array, 0, newArray, 0, size);
            
            array = newArray;

        }

        return array;

    }
} // end of class ActionManager
