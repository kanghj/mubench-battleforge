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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import de.battleforge.gui.util.ImageFactory;
import de.battleforge.util.ExceptionHandler;
import de.battleforge.util.Internationalization;

public class BFAction extends AbstractAction {

    private ACTIONS mAction;

    private ActionCallbackListener mCallbackListener;

    public BFAction(ACTIONS action) {
        this(action.name(), null, null, action, null);

    } // BFAction

    public BFAction(String name, ACTIONS action) {
        this(name, null, null, action, null);

    } // BFAction

    public BFAction(String name, String tooltip, ACTIONS action) {
        this(name, tooltip, null, action, null);

    } // BFAction

    public BFAction(String name, String tooltip, String icon, ACTIONS action) {
        this(name, tooltip, icon, action, null);

    } // BFAction

    public BFAction(ACTIONS action, ActionCallbackListener listener) {
        this(action.name(), null, null, action, listener);

    } // BFAction

    public BFAction(String name, ACTIONS action, ActionCallbackListener listener) {
        this(name, null, null, action, listener);

    } // BFAction

    public BFAction(String name, String tooltip, ACTIONS action, ActionCallbackListener listener) {
        this(name, tooltip, null, action, listener);

    } // BFAction

    public BFAction(String name, String tooltip, String icon, ACTIONS action, ActionCallbackListener listener) {
        super(Internationalization.getString(name));

        if (action == null) {
            throw new IllegalArgumentException("action must not be null");

        } // if

        mAction = action;
        mCallbackListener = listener;

        setToolTip(Internationalization.getString(tooltip));
        setIcon(icon);

    } // BFAction

    public void setIcon(String name) {
        if (name == null) {
            putValue(SMALL_ICON, null);

        } else {
            putValue(SMALL_ICON, ImageFactory.getIcon(name));

        } // if
    } // setIcon

    public void setToolTip(String tip) {
        putValue(SHORT_DESCRIPTION, tip);

    } // setToolTip

    public void execute() {
        execute(new ActionObject(this));

    } // execute

    public void execute(Object o) {
        execute(new ActionObject(this, o));

    } // execute

    public void execute(ActionObject o) {
        if (mCallbackListener != null) {
            mCallbackListener.handleAction(mAction, o);

        } // if

        for (ActionCallbackListener listener : ActionManager.getActionCallbackListener(mAction)) {
            if (!handleActionForListener(listener, mAction, o)) {
                break;

            } // if
        } // for

        for (ActionCallbackListener listener : ActionManager.getActionCallbackListener()) {
            if (!handleActionForListener(listener, mAction, o)) {
                break;

            } // if
        } // for
    } // execute

    private boolean handleActionForListener(ActionCallbackListener listener, ACTIONS action, ActionObject o) {
        try {
            return listener.handleAction(action, o);

        } catch (Throwable th) {
            ExceptionHandler.handle(th);

        } // try

        return false;

    } // handleActionForListener

    public void actionPerformed(ActionEvent e) {
        execute(e.getActionCommand());

    } // actionPerformed
} // end of class BFAction
