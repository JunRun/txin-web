package cc.txin.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 列表页面数据包装类
 * @author 印修河
 * @date 2017年9月12日 下午6:25:41
 */
@Data
public class PageModel<T> implements Serializable{

	private Long total = 0L;
	private List<T> rows = new ArrayList<>();

}
