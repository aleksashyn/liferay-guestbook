package com.guestbook.job;

import com.guestbook.model.Entry;
import com.guestbook.model.Guestbook;
import com.guestbook.service.EntryLocalService;
import com.guestbook.service.GuestbookLocalService;
import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "dispatch.task.executor.name=Refresh Guestbook Scheduled Job",
                "dispatch.task.executor.type=refreshguestbook.job-01"
        },
        service = DispatchTaskExecutor.class
)
public class RefreshGuestbookScheduledJob extends BaseDispatchTaskExecutor {
    @Override
    public void doExecute(DispatchTrigger dispatchTrigger,
                          DispatchTaskExecutorOutput dispatchTaskExecutorOutput) throws Exception {

        log.info("Refresh Guestbook Scheduled Job");
        UnicodeProperties props = dispatchTrigger.getDispatchTaskSettingsUnicodeProperties();

        if (props != null) {
            log.info("Refresh Guestbook Scheduled Job using Unicode Properties");

            long guestbookId = GetterUtil.getLong(props.getProperty("guestbookId"), 0);
            log.info("Resolve guestbook Id: " + guestbookId);

            Guestbook guestbook = guestbookLocalService.getGuestbook(guestbookId);
            if (guestbook != null) {
                long groupId = guestbook.getGroupId();
                log.info("Resolve guestbook groupId: " + groupId);

                List<Entry> entries = entryLocalService.getEntries(groupId, guestbookId);
                Date date = new Date();

                log.info("Attempting to update guestbook entries");
                for (Entry entry : entries) {
                    log.info("Processing Guestbook entry ID: " + entry.getEntryId());
                    String message = entry.getMessage();
                    String entryMessage = message.substring(0, message.indexOf("(updatedAt="));
                    entry.setMessage(entryMessage.trim() + " (updatedAt=" + date + ")");
                    entryLocalService.updateEntry(entry);
                    log.info("Guestbook entry with ID " + entry.getEntryId() + " updated");
                }
                String message = "Successfully updated " + entries.size() + " guestbook entries";
                log.info(message);
                dispatchTaskExecutorOutput.setOutput(message);
            } else {
                String message = "No guestbook found by ID " + guestbookId;
                log.info(message);
                dispatchTaskExecutorOutput.setOutput(message);
            }
        } else {
            String message = "Guestbook Scheduled Job execute successfully";
            log.info(message);
            dispatchTaskExecutorOutput.setOutput(message);
        }
    }

    @Override
    public String getName() {
        return "Refresh Guestbook Scheduled Job";
    }

    @Reference(unbind = "-")
    protected void setEntryService(EntryLocalService entryLocalService) {
        this.entryLocalService = entryLocalService;
    }

    @Reference(unbind = "-")
    protected void setGuestbookService(GuestbookLocalService guestbookLocalService) {
        this.guestbookLocalService = guestbookLocalService;
    }

    private EntryLocalService entryLocalService;
    private GuestbookLocalService guestbookLocalService;

    private static final Log log = LogFactoryUtil.getLog(RefreshGuestbookScheduledJob.class);
}
