package com.ouzhx.common;

import java.util.List;

/**
 * 使用comboTree的辅助类
 * 
 * @author ozx
 *
 */
public class ComboTreeBean {
	private Object id;
	private Object Text;
	private List<ComboTreeBean> children;

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getText() {
		return Text;
	}

	public void setText(Object text) {
		Text = text;
	}

	public List<ComboTreeBean> getChildren() {
		return children;
	}

	public void setChildren(List<ComboTreeBean> children) {
		this.children = children;
	}
}
