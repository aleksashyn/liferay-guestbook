package com.guestbook.service.impl;

import com.guestbook.exception.EntryEmailException;
import com.guestbook.exception.EntryMessageException;
import com.guestbook.exception.EntryNameException;
import com.guestbook.model.Entry;
import com.guestbook.service.base.EntryLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.guestbook.model.Entry",
        service = AopService.class
)
public class EntryLocalServiceImpl extends EntryLocalServiceBaseImpl {

    @Override
    public List<Entry> getEntries(long groupId, long guestbookId) {
        return entryPersistence.findByG_G(groupId, guestbookId);
    }

    @Override
    public List<Entry> getEntries(long groupId, long guestbookId, int start, int end) throws SystemException {
        return entryPersistence.findByG_G(groupId, guestbookId, start, end);
    }

    @Override
    public List<Entry> getEntries(long groupId, long guestbookId, int start, int end, OrderByComparator<Entry> obc) {
        return entryPersistence.findByG_G(groupId, guestbookId, start, end, obc);
    }

    @Override
    public int getEntriesCount(long groupId, long guestbookId) {
        return entryPersistence.countByG_G(groupId, guestbookId);
    }

    @Override
    public Entry addEntry(long userId,
                          long guestbookId,
                          String name,
                          String email,
                          String message,
                          ServiceContext serviceContext) throws PortalException {

        long groupId = serviceContext.getScopeGroupId();

        User user = userLocalService.getUserById(userId);

        Date now = new Date();

        validate(name, email, message);

        long entryId = counterLocalService.increment();

        Entry entry = entryPersistence.create(entryId);

        entry.setUuid(serviceContext.getUuid());
        entry.setUserId(userId);
        entry.setGroupId(groupId);
        entry.setCompanyId(user.getCompanyId());
        entry.setUserName(user.getFullName());
        entry.setCreateDate(serviceContext.getCreateDate(now));
        entry.setModifiedDate(serviceContext.getModifiedDate(now));
        entry.setExpandoBridgeAttributes(serviceContext);
        entry.setGuestbookId(guestbookId);
        entry.setName(name);
        entry.setEmail(email);
        entry.setMessage(message);

        entryPersistence.update(entry);

        return entry;
    }

    @Override
    public Entry updateEntry(long userId,
                             long guestbookId,
                             long entryId,
                             String name,
                             String email,
                             String message,
                             ServiceContext serviceContext) throws PortalException, SystemException {

        Date now = new Date();

        validate(name, email, message);

        Entry entry = getEntry(entryId);

        User user = userLocalService.getUserById(userId);

        entry.setUserId(userId);
        entry.setUserName(user.getFullName());
        entry.setModifiedDate(serviceContext.getModifiedDate(now));
        entry.setName(name);
        entry.setEmail(email);
        entry.setMessage(message);
        entry.setExpandoBridgeAttributes(serviceContext);

        entryPersistence.update(entry);

        return entry;
    }

    @Override
    public Entry deleteEntry(long entryId, ServiceContext serviceContext) throws PortalException {

        Entry entry = getEntry(entryId);

        entry = deleteEntry(entryId);

        return entry;
    }

    protected void validate(String name, String email, String entry)
            throws PortalException {

        if (Validator.isNull(name)) {
            throw new EntryNameException();
        }

        if (!Validator.isEmailAddress(email)) {
            throw new EntryEmailException();
        }

        if (Validator.isNull(entry)) {
            throw new EntryMessageException();
        }
    }
}
