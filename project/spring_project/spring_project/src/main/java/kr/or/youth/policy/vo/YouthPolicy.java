package kr.or.youth.policy.vo;

public class YouthPolicy {
	
    private String rownum;		
    private String bizId; 			
    private String polyBizTy; 		
    private String polyBizSjnm; 		
    private String polyItcnCn; 		
    private String plcyTpNm; 		
    private String sporScvl; 		
    private String sporCn; 			
    private String ageInfo; 			
    private String empmSttsCn; 		
    private String accrRqisCn; 		
    private String majrRqisCn; 		
    private String cnsgNmor; 		
    private String rqutPrdCn; 		
    private String rqutUrla;
    
    
    // 필요없는 데이터
    private String pageIndex;
    private String totalCnt;
    private String polyBizSecd;
    private String splzRlmRqisCn;
    private String rqutProcCn;
    private String jdgnPresCn;
    
    
    
    
    
    
    
    /*
    <rownum>	Number	row 번호
    <bizId>	String	정책 ID
    <polyBizTy>	String	기관 및 지자체 구분
    <polyBizSjnm>	String	정책명
    <polyItcnCn>	String	정책소개
    <plcyTpNm>	String	정책유형
    <sporScvl>	String	지원규모
    <sporCn>	String	지원내용
    <ageInfo>	String	참여요건 - 연령
    <empmSttsCn>	String	참여요건 - 취업상태
    <accrRqisCn>	String	참여요건 - 학력
    <majrRqisCn>	String	참여요건 - 전공

    <cnsgNmor>	String	신청기관명
    <rqutPrdCn>	String	신청기간
    <rqutUrla>	String	사이트 링크 주소
    */
    



	
	
	
	public String getPageIndex() {
		return pageIndex;
	}





	@Override
	public String toString() {
		return "YouthPolicy [rownum=" + rownum + ", bizId=" + bizId + ", polyBizTy=" + polyBizTy + ", polyBizSjnm="
				+ polyBizSjnm + ", polyItcnCn=" + polyItcnCn + ", plcyTpNm=" + plcyTpNm + ", sporScvl=" + sporScvl
				+ ", sporCn=" + sporCn + ", ageInfo=" + ageInfo + ", empmSttsCn=" + empmSttsCn + ", accrRqisCn="
				+ accrRqisCn + ", majrRqisCn=" + majrRqisCn + ", cnsgNmor=" + cnsgNmor + ", rqutPrdCn=" + rqutPrdCn
				+ ", rqutUrla=" + rqutUrla + ", pageIndex=" + pageIndex + ", totalCnt=" + totalCnt + ", polyBizSecd="
				+ polyBizSecd + ", splzRlmRqisCn=" + splzRlmRqisCn + ", rqutProcCn=" + rqutProcCn + ", jdgnPresCn="
				+ jdgnPresCn + "]";
	}





	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}





	public String getTotalCnt() {
		return totalCnt;
	}





	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}





	public String getPolyBizSecd() {
		return polyBizSecd;
	}





	public void setPolyBizSecd(String polyBizSecd) {
		this.polyBizSecd = polyBizSecd;
	}





	public String getSplzRlmRqisCn() {
		return splzRlmRqisCn;
	}





	public void setSplzRlmRqisCn(String splzRlmRqisCn) {
		this.splzRlmRqisCn = splzRlmRqisCn;
	}





	public String getRqutProcCn() {
		return rqutProcCn;
	}





	public void setRqutProcCn(String rqutProcCn) {
		this.rqutProcCn = rqutProcCn;
	}





	public String getJdgnPresCn() {
		return jdgnPresCn;
	}





	public void setJdgnPresCn(String jdgnPresCn) {
		this.jdgnPresCn = jdgnPresCn;
	}





	public String getRownum() {
		return rownum;
	}


	public void setRownum(String rownum) {
		this.rownum = rownum;
	}


	public String getBizId() {
		return bizId;
	}


	public void setBizId(String bizId) {
		this.bizId = bizId;
	}


	public String getPolyBizTy() {
		return polyBizTy;
	}


	public void setPolyBizTy(String polyBizTy) {
		this.polyBizTy = polyBizTy;
	}


	public String getPolyBizSjnm() {
		return polyBizSjnm;
	}


	public void setPolyBizSjnm(String polyBizSjnm) {
		this.polyBizSjnm = polyBizSjnm;
	}


	public String getPolyItcnCn() {
		return polyItcnCn;
	}


	public void setPolyItcnCn(String polyItcnCn) {
		this.polyItcnCn = polyItcnCn;
	}


	public String getPlcyTpNm() {
		return plcyTpNm;
	}


	public void setPlcyTpNm(String plcyTpNm) {
		this.plcyTpNm = plcyTpNm;
	}


	public String getSporScvl() {
		return sporScvl;
	}


	public void setSporScvl(String sporScvl) {
		this.sporScvl = sporScvl;
	}


	public String getSporCn() {
		return sporCn;
	}


	public void setSporCn(String sporCn) {
		this.sporCn = sporCn;
	}


	public String getAgeInfo() {
		return ageInfo;
	}


	public void setAgeInfo(String ageInfo) {
		this.ageInfo = ageInfo;
	}


	public String getEmpmSttsCn() {
		return empmSttsCn;
	}


	public void setEmpmSttsCn(String empmSttsCn) {
		this.empmSttsCn = empmSttsCn;
	}


	public String getAccrRqisCn() {
		return accrRqisCn;
	}


	public void setAccrRqisCn(String accrRqisCn) {
		this.accrRqisCn = accrRqisCn;
	}


	public String getMajrRqisCn() {
		return majrRqisCn;
	}


	public void setMajrRqisCn(String majrRqisCn) {
		this.majrRqisCn = majrRqisCn;
	}


	public String getCnsgNmor() {
		return cnsgNmor;
	}


	public void setCnsgNmor(String cnsgNmor) {
		this.cnsgNmor = cnsgNmor;
	}


	public String getRqutPrdCn() {
		return rqutPrdCn;
	}


	public void setRqutPrdCn(String rqutPrdCn) {
		this.rqutPrdCn = rqutPrdCn;
	}


	public String getRqutUrla() {
		return rqutUrla;
	}


	public void setRqutUrla(String rqutUrla) {
		this.rqutUrla = rqutUrla;
	}
    
	
	
    
	
	
	

}
