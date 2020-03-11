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
package de.battleforge.util;

public class BFException extends Exception {

    /**
     * Our message-id.
     */
    private int mMessageID;

    public BFException(int messageID) {
        mMessageID = messageID;

    } // BFException

    public BFException(int messageID, Throwable cause) {
        mMessageID = messageID;

        super.initCause(cause);

    } // BFException

    public int getMessageID() {
        return mMessageID;

    } // getMessage

    public String getDescription() {
        return Internationalization.getErrorDescription(mMessageID);

    }

    @Override
    public String getMessage() {
        return Internationalization.getErrorMessage(mMessageID);

    }

    public String getSolution() {
        return Internationalization.getErrorSolution(mMessageID);

    }

    @Override
    public String toString() {
        return getMessage();

    } // toString
} // end of class BFException
