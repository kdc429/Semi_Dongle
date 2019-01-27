package com.dongle.member.model.vo;

public class ReportReason {
   
   private int reportCode; //REPORT_CODE
   private String reportReason;//REPORT_REASON;;
   
   public ReportReason() {
      
   }
   
   public ReportReason(int reportCode, String reportReason) {
      super();
      this.reportCode = reportCode;
      this.reportReason = reportReason;
   }

   public int getReportCode() {
      return reportCode;
   }

   public void setReportCode(int reportCode) {
      this.reportCode = reportCode;
   }

   public String getReportReason() {
      return reportReason;
   }

   public void setReportReason(String reportReason) {
      this.reportReason = reportReason;
   }

   @Override
   public String toString() {
      return "ReportReason [reportCode=" + reportCode + ", reportReason=" + reportReason + "]";
   }
   
   
   
   

}