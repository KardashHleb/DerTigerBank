    package org.example.service.DTO;

    public class ReportData {
        private int customerCount;
        private int totalAccounts;
        private long debitCount;
        private long creditCount;
        private double totalBalance;
        private double totalCreditUsed;

        public ReportData() {}


        public ReportData(int customerCount, int totalAccounts,
                          double totalBalance, double averageBalance) {
            this.customerCount = customerCount;
            this.totalAccounts = totalAccounts;
            this.totalBalance = totalBalance;
            this.debitCount = 0;
            this.creditCount = 0;
            this.totalCreditUsed = 0;
        }

        // Геттеры
        public int getCustomerCount() { return customerCount; }
        public int getTotalAccounts() { return totalAccounts; }
        public long getDebitCount() { return debitCount; }
        public long getCreditCount() { return creditCount; }
        public double getTotalBalance() { return totalBalance; }
        public double getTotalCreditUsed() { return totalCreditUsed; }

        // Сеттеры (нужны для десериализации)
        public void setCustomerCount(int customerCount) { this.customerCount = customerCount; }
        public void setTotalAccounts(int totalAccounts) { this.totalAccounts = totalAccounts; }
        public void setDebitCount(long debitCount) { this.debitCount = debitCount; }
        public void setCreditCount(long creditCount) { this.creditCount = creditCount; }
        public void setTotalBalance(double totalBalance) { this.totalBalance = totalBalance; }
        public void setTotalCreditUsed(double totalCreditUsed) { this.totalCreditUsed = totalCreditUsed; }

    }