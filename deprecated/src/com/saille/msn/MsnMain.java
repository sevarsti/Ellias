package com.saille.msn;

import com.GlobalConstant;
import net.sf.jml.*;
import net.sf.jml.event.MsnAdapter;
import net.sf.jml.impl.MsnMessengerFactory;
import net.sf.jml.message.MsnEmailInitEmailData;
import net.sf.jml.message.MsnInstantMessage;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsnMain extends MsnAdapter implements Serializable {
    private final Logger LOGGER = Logger.getLogger(MsnMain.class);
    private MsnMessenger messenger;
    private List<String[]> unreadMessage = new ArrayList<String[]>();

    private List<String[]> unreadContactStatusChanged = new ArrayList<String[]>();

    private List<String[]> unreadContact = new ArrayList<String[]>();

    public boolean login(String username, String password) {
        try {
            this.messenger = MsnMessengerFactory.createMsnMessenger(username, password);
            this.messenger.setSupportedProtocol(new MsnProtocol[]{MsnProtocol.MSNP12});
            this.messenger.getOwner().setInitStatus(MsnUserStatus.ONLINE);
            this.messenger.setLogIncoming(true);
            this.messenger.setLogOutgoing(true);
            this.messenger.addListener(this);
            this.messenger.login();

            MsnContact[] cons = this.messenger.getContactList().getContactsInList(MsnList.AL);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return this.messenger != null;
    }

    public void sendMessage(String target, String content) {
        for(MsnContact cont : this.messenger.getContactList().getContacts()) {
            if(!cont.getEmail().getEmailAddress().equals(target)) {
                continue;
            }
            try {
                this.messenger.sendText(cont.getEmail(), content);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void initialEmailDataReceived(MsnSwitchboard switchboard, MsnEmailInitEmailData message, MsnContact contact) {
        this.LOGGER.info("MSN: Got init email data " + message.getInboxUnread() + " unread message(s)");
    }

    public void loginCompleted(MsnMessenger messenger) {
        this.LOGGER.info("login completed");
    }

    public void contactListInitCompleted(MsnMessenger messenger) {
        for(MsnContact cont : this.messenger.getContactList().getContacts()) {
            updateContactList(cont.getEmail().toString(), cont.getStatus().toString(), cont.getDisplayName(), cont.getPersonalMessage(), 1);
        }

        this.LOGGER.info("contact list init completed");
    }

    public void contactListSyncCompleted(MsnMessenger messenger) {
        this.LOGGER.info("contact list synchronise completed");
    }

    public void instantMessageReceived(MsnSwitchboard switchboard, MsnInstantMessage message, MsnContact friend) {
        try {
            System.out.println(switchboard + " recv instant message " + message);
            updateMessage(friend.getEmail().toString(), message.getContent(), friend.getDisplayName(), friend.getPersonalMessage(), 1);
            switchboard.sendMessage(message, false);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void contactStatusChanged(MsnMessenger messenger, MsnContact contact) {
        try {
            updateContactStatusChanged(contact.getEmail().toString(), contact.getStatus().toString(), contact.getDisplayName(), contact.getPersonalMessage(), 1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public MsnContactList getContacts() {
        if(this.messenger == null) {
            return null;
        }
        return this.messenger.getContactList();
    }

    public synchronized void updateMessage(String email, String message, String displayName, String personalMessage, int type) {
        if(type == 1) {
            this.unreadMessage.add(new String[]{email, message, displayName, personalMessage, GlobalConstant.DF_YYYY_MM_DD_HH_MM_SS.format(new Date())});
        } else if(type == 2) {
            for(int i = this.unreadMessage.size() - 1; i >= 0; i--) {
                this.unreadMessage.remove(i);
            }
        }
    }

    public synchronized void updateContactStatusChanged(String email, String status, String displayName, String personalMessage, int type) {
        if(type == 1) {
            this.unreadContactStatusChanged.add(new String[]{email, status, displayName, personalMessage, GlobalConstant.DF_YYYY_MM_DD_HH_MM_SS.format(new Date())});
        } else if(type == 2) {
            for(int i = this.unreadContactStatusChanged.size() - 1; i >= 0; i--) {
                this.unreadContactStatusChanged.remove(i);
            }
        }
    }

    public synchronized void updateContactList(String email, String status, String displayName, String personalMessage, int type) {
        if(type == 1) {
            this.unreadContact.add(new String[]{email, status, displayName, personalMessage, GlobalConstant.DF_YYYY_MM_DD_HH_MM_SS.format(new Date())});
        } else if(type == 2) {
            for(int i = this.unreadContact.size() - 1; i >= 0; i--) {
                this.unreadContact.remove(i);
            }
        }
    }

    public MsnMessenger getMessenger() {
        return this.messenger;
    }

    public void setMessenger(MsnMessenger messenger) {
        this.messenger = messenger;
    }

    public List<String[]> getUnreadMessage() {
        return this.unreadMessage;
    }

    public void setUnreadMessage(List<String[]> unreadMessage) {
        this.unreadMessage = unreadMessage;
    }

    public List<String[]> getUnreadContactStatusChanged() {
        return this.unreadContactStatusChanged;
    }

    public void setUnreadContactStatusChanged(List<String[]> unreadContactStatusChanged) {
        this.unreadContactStatusChanged = unreadContactStatusChanged;
    }

    public List<String[]> getUnreadContact() {
        return this.unreadContact;
    }

    public void setUnreadContact(List<String[]> unreadContact) {
        this.unreadContact = unreadContact;
    }
}