package com.jaegarsaun.finance.DTO;

import java.util.Date;
import java.util.List;

public class AccountHistoryDTO {
    private Integer accountId;
    private List<AccountChangeEntry> changes;

    public AccountHistoryDTO() {
    }

    public AccountHistoryDTO(Integer accountId, List<AccountChangeEntry> changes) {
        this.accountId = accountId;
        this.changes = changes;
    }

    // Getters and Setters
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public List<AccountChangeEntry> getChanges() {
        return changes;
    }

    public void setChanges(List<AccountChangeEntry> changes) {
        this.changes = changes;
    }

    public static class AccountChangeEntry {
        private Date changeDate;
        private String changeType;
        private Float oldValue;
        private Float newValue;
        private String description;

        public AccountChangeEntry() {
        }

        public AccountChangeEntry(Date changeDate, String changeType, Float oldValue, Float newValue, String description) {
            this.changeDate = changeDate;
            this.changeType = changeType;
            this.oldValue = oldValue;
            this.newValue = newValue;
            this.description = description;
        }

        // Getters and Setters
        public Date getChangeDate() {
            return changeDate;
        }

        public void setChangeDate(Date changeDate) {
            this.changeDate = changeDate;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public Float getOldValue() {
            return oldValue;
        }

        public void setOldValue(Float oldValue) {
            this.oldValue = oldValue;
        }

        public Float getNewValue() {
            return newValue;
        }

        public void setNewValue(Float newValue) {
            this.newValue = newValue;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

