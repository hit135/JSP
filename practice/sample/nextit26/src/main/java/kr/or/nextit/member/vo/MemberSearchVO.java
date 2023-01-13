package kr.or.nextit.member.vo;

import java.util.List;

import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.common.vo.PagingVO;

public class MemberSearchVO extends PagingVO{

	
	private String searchType;
	private String searchWord;
	private String searchJob;
	private String searchHobby;
	
	// 멤버리스트 생성 >> 그리드를 위한
	private List<MemberVO> memberList;
	private List<CodeVO> jobList;
	private List<CodeVO> hobbyList;
	
	
	
	@Override
	public String toString() {
		return "MemberSearchVO [searchType=" + searchType + ", searchWord=" + searchWord + ", searchJob=" + searchJob
				+ ", searchHobby=" + searchHobby + ", memberList=" + memberList + ", jobList=" + jobList
				+ ", hobbyList=" + hobbyList + "]";
	}
	public List<MemberVO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberVO> memberList) {
		this.memberList = memberList;
	}
	public List<CodeVO> getJobList() {
		return jobList;
	}
	public void setJobList(List<CodeVO> jobList) {
		this.jobList = jobList;
	}
	public List<CodeVO> getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List<CodeVO> hobbyList) {
		this.hobbyList = hobbyList;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getSearchJob() {
		return searchJob;
	}
	public void setSearchJob(String searchJob) {
		this.searchJob = searchJob;
	}
	public String getSearchHobby() {
		return searchHobby;
	}
	public void setSearchHobby(String searchHobby) {
		this.searchHobby = searchHobby;
	}

	
	
	
}
