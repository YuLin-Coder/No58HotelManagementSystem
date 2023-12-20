package com.util;

import java.util.List;

public class Pagination {
	private int totle;
	private int pageSize;
	private int totlePage;
	private int index;
	private List data;
	private String path;

	public void setTotle(int totle) {
		this.totle = totle;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTotle() {
		return this.totle;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getTotlePage() {
		return (this.totle + this.pageSize - 1) / this.pageSize;
	}

	public int getIndex() {
		return this.index;
	}

	public List getData() {
		return this.data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public String getPageDisplay() {
		StringBuffer displayInfo = new StringBuffer();
		if ((this.index == 0) || (this.pageSize == 0)) {
			displayInfo.append("û�з�ҳ����Ϣ!");
		} else {
			displayInfo.append("<div>");
			displayInfo.append("��" + this.totle + "����¼&nbsp;&nbsp;&nbsp;&nbsp;ÿҳ<span style='color:#FF0000'>"
					+ this.pageSize + "</span>��&nbsp;&nbsp;&nbsp;&nbsp;");
			displayInfo.append("��<span style='color:#FF0000'>" + this.index + "</span>ҳ/��" + getTotlePage()
					+ "ҳ&nbsp;&nbsp;&nbsp;&nbsp;");
			if (this.index == 1) {
				displayInfo.append("��ҳ&nbsp;&nbsp;&nbsp;&nbsp;");
				displayInfo.append("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
			} else {
				displayInfo.append("<a href='" + this.path + "index=1'>��ҳ&nbsp;&nbsp;&nbsp;&nbsp;</a>");
				displayInfo.append("<a href='" + this.path + "index=" + (this.index - 1)
						+ "'>��һҳ&nbsp;&nbsp;&nbsp;&nbsp;</a>&nbsp;");
			}
			if (this.index >= getTotlePage()) {
				displayInfo.append("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
				displayInfo.append("ĩҳ&nbsp;&nbsp;&nbsp;&nbsp;");
			} else {
				displayInfo.append(
						"<a href='" + this.path + "index=" + (this.index + 1) + "'>��һҳ&nbsp;&nbsp;&nbsp;&nbsp;</a>");
				displayInfo.append(
						"<a href='" + this.path + "index=" + getTotlePage() + "'>ĩҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			displayInfo.append("</div>");
		}
		return displayInfo.toString();
	}
}
