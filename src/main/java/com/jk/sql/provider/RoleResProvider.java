package com.jk.sql.provider;

import java.util.List;
import java.util.Map;

import com.jk.tb.pojo.TRoleRes;

public class RoleResProvider {

	/**
	 * 根据资源id查询资源详情
	 * 
	 * @param map
	 * @return
	 */
	public String selectTResourcesByIds(Map<String, Object> map) {
		List<TRoleRes> list = (List<TRoleRes>) map.get("paras");
		String sql = "SELECT * FROM t_resources WHERE id in (";
		// 只有1个
		if (list.size() == 1) {
			sql = sql + "'" + list.get(0).getResId() + "')";
			return sql;
		}
		// 2以上
		for (int i = 0; i < list.size(); i++) {
			TRoleRes item = list.get(i);
			if (i == list.size() - 1) {
				sql = sql + "'" + item.getResId() + "'" + ")";
				continue;
			}
			sql = sql + "'" + item.getResId() + "'" + ",";
		}
		return sql;
	}
}
