package kr.or.youth.policy.vo;

import kr.or.youth.common.vo.PagingVO;

public class SearchVO extends PagingVO{

	private String ageInfo;
	private String empmSttsCn;	
	private String accrRqisCn;	
	private String majrRqisCn;
	

	@Override
	public String toString() {
		return "SearchVO [ageInfo=" + ageInfo + ", empmSttsCn=" + empmSttsCn + ", accrRqisCn=" + accrRqisCn
				+ ", majrRqisCn=" + majrRqisCn + ", getCurPage()=" + getCurPage() + ", getRowSizePerPage()="
				+ getRowSizePerPage() + ", getPageSize()=" + getPageSize() + ", getTotalRowCount()="
				+ getTotalRowCount() + ", getFirstRow()=" + getFirstRow() + ", getLastRow()=" + getLastRow()
				+ ", getTotalPageCount()=" + getTotalPageCount() + ", getFirstPage()=" + getFirstPage()
				+ ", getLastPage()=" + getLastPage() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
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
		
	
	


}
