package com.guestbook.service.impl;

import com.guestbook.exception.GuestbookNameException;
import com.guestbook.model.Guestbook;
import com.guestbook.service.base.GuestbookLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import java.util.Date;
import java.util.List;

@Component(
        property = "model.class.name=com.guestbook.model.Guestbook",
        service = AopService.class
)
public class GuestbookLocalServiceImpl extends GuestbookLocalServiceBaseImpl {

    @Override
    public List<Guestbook> getGuestbooks(long groupId) {
        return guestbookPersistence.findByGroupId(groupId);
    }

    @Override
    public List<Guestbook> getGuestbooks(long groupId, int start, int end, OrderByComparator<Guestbook> obc) {
        return guestbookPersistence.findByGroupId(groupId, start, end, obc);
    }

    @Override
    public List<Guestbook> getGuestbooks(long groupId, int start, int end) {
        return guestbookPersistence.findByGroupId(groupId, start, end);
    }

    @Override
    public int getGuestbooksCount(long groupId) {
        return guestbookPersistence.countByGroupId(groupId);
    }

    @Override
    public Guestbook addGuestbook(long userId, String name, ServiceContext serviceContext) throws PortalException {

        long groupId = serviceContext.getScopeGroupId();

        User user = userLocalService.getUserById(userId);

        Date now = new Date();

        validate(name);

        long guestbookId = counterLocalService.increment();

        Guestbook guestbook = guestbookPersistence.create(guestbookId);

        guestbook.setUuid(serviceContext.getUuid());
        guestbook.setUserId(userId);
        guestbook.setGroupId(groupId);
        guestbook.setCompanyId(user.getCompanyId());
        guestbook.setUserName(user.getFullName());
        guestbook.setCreateDate(serviceContext.getCreateDate(now));
        guestbook.setModifiedDate(serviceContext.getModifiedDate(now));
        guestbook.setName(name);
        guestbook.setExpandoBridgeAttributes(serviceContext);

        guestbookPersistence.update(guestbook);

        return guestbook;

    }

    protected void validate(String name) throws PortalException {
        if (Validator.isNull(name)) {
            throw new GuestbookNameException();
        }
    }
}
