package kr.or.youth.policy.vo;

public class codeVO {
	
	private String codeId;
	private String perentId;
	private String conditionVal;
	private String outVal;
	
	@Override
	public String toString() {
		return "codeVO [codeId=" + codeId + ", perentId=" + perentId + ", conditionVal=" + conditionVal + ", outVal="
				+ outVal + "]";
	}

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getPerentId() {
		return perentId;
	}

	public void setPerentId(String perentId) {
		this.perentId = perentId;
	}

	public String getConditionVal() {
		return conditionVal;
	}

	public void setConditionVal(String conditionVal) {
		this.conditionVal = conditionVal;
	}

	public String getOutVal() {
		return outVal;
	}

	public void setOutVal(String outVal) {
		this.outVal = outVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
