package com.game.base.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.game.constant.D;
import com.game.exception.LogicException;

/**
 * 多路消息发送器 采用无界线程池进行消息发送
 */
@SuppressWarnings("unchecked")
public class MsgUtil {
	private static Log log = LogFactory.getLog(MsgUtil.class);
	public static final String _CODE = "_code"; // 标识码
	public static final String _INFO = "_info"; // 描述信息
	public static final Integer _CODE_SUC = 200; // 标识码:成功
	public static final Integer _CODE_FAL = 400; // 标识码:失败
	public static volatile boolean working = true;

	public final static Map brmCmdAndReturn(Map ret, int cmd, int code) {
		Map result = GameUtil.createSimpleMap();
		result.put(D.DEF_CODE, code);
		result.put(D.DEF_COMMAND, cmd);
		if (ret != null)
			result.put(D.DEF_RETURN, ret);
		return result;
	}

	public final static Map brmAll(Map ret, int cmd, int code) {
		return brmCmdAndReturn(ret, cmd, code);
	}

	public static Map<?, ?> dealLogicException(int cmd, LogicException e) {
		String info = e.getMessage();
		log.debug(info);
		return MsgUtil.brmAll(null, cmd, e.getCode());
	}

}