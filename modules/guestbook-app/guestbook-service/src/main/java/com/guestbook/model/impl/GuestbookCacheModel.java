/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.guestbook.model.impl;

import com.guestbook.model.Guestbook;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Guestbook in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class GuestbookCacheModel
	implements CacheModel<Guestbook>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof GuestbookCacheModel)) {
			return false;
		}

		GuestbookCacheModel guestbookCacheModel = (GuestbookCacheModel)object;

		if (guestbookId == guestbookCacheModel.guestbookId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, guestbookId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", guestbookId=");
		sb.append(guestbookId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Guestbook toEntityModel() {
		GuestbookImpl guestbookImpl = new GuestbookImpl();

		if (uuid == null) {
			guestbookImpl.setUuid("");
		}
		else {
			guestbookImpl.setUuid(uuid);
		}

		guestbookImpl.setGuestbookId(guestbookId);
		guestbookImpl.setGroupId(groupId);
		guestbookImpl.setStatus(status);
		guestbookImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			guestbookImpl.setStatusByUserName("");
		}
		else {
			guestbookImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			guestbookImpl.setStatusDate(null);
		}
		else {
			guestbookImpl.setStatusDate(new Date(statusDate));
		}

		guestbookImpl.setCompanyId(companyId);
		guestbookImpl.setUserId(userId);

		if (userName == null) {
			guestbookImpl.setUserName("");
		}
		else {
			guestbookImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			guestbookImpl.setCreateDate(null);
		}
		else {
			guestbookImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			guestbookImpl.setModifiedDate(null);
		}
		else {
			guestbookImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			guestbookImpl.setName("");
		}
		else {
			guestbookImpl.setName(name);
		}

		guestbookImpl.resetOriginalValues();

		return guestbookImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		guestbookId = objectInput.readLong();

		groupId = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(guestbookId);

		objectOutput.writeLong(groupId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public String uuid;
	public long guestbookId;
	public long groupId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;

}