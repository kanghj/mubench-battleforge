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
package de.battleforge.security;

import de.battleforge.jdo.BFUser;
import de.battleforge.jdo.DBWrapper;

public class Security {

    public static boolean getPrivilege(PRIVILEGES priv) {

        BFUser user = DBWrapper.getCurrentGameUser().getUser(); // BF_User.getCurrentUser();

        // Is user admin?
        if (user.getAdminFlag()) {
            return true;
        }

        // TODO: ArraysListen (?) der Rollen des Users durchlaufen,
        // ob der das Recht schon über eine Rolle hat!

        // look if the user is allowed to do what he is trying to do
        switch (priv) {
        case CHANGE_SYSTEM_BASISDATA:
            // find out, if the user has got the right
            // and return value
            return true;

        default:
            // default is that you do not have the right
            return false;
        }
    }
}
