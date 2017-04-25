package com.frontline.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.frontline.actionform.MessageActionForm;
import com.frontline.commom.Constants;
import com.frontline.dao.util.FabricateSQL;
import com.frontline.model.Guestbook;
import com.frontline.service.GuestbookService;
import com.frontline.service.GuestbookServiceImpl;

public class queryMessageAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MessageActionForm mf = (MessageActionForm) form;

		// 1.Retrieve From Data
		String name = mf.getName();
		String startDate = mf.getStartDate();
		String endDate = mf.getEndDate();
		String message = mf.getMessage();

		// 2.Validate Data
		// 移至queryMessageForm內

		// 3.Convert Data
		// N/A

		try {
			// 4.Invoke Biz Logic
			// 處理SQL指令
			String condition = new FabricateSQL().modifyCondition(name,
					startDate, endDate, message);
			ArrayList<String> psValues = new FabricateSQL().modifyPsValues(
					name, startDate, endDate, message);

			// 呼叫Service
			GuestbookService gs = new GuestbookServiceImpl();
			ArrayList<Guestbook> result = gs.queryMessage(condition, psValues);

			// 5.Select Next view
			// 無任何資料處理
			if (result.isEmpty()) {
				ActionMessages am = new ActionMessages();
				am.add("resultMessage", new ActionMessage("result.nodata"));
				saveMessages(request, am);
				return mapping.findForward(Constants.SUCCESS);
			}

			// 查詢結果
			request.setAttribute("result", result);

			// 查詢條件
			request.setAttribute("queryName", name);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("queryMessage", message);
		} catch (Exception e) {
			e.printStackTrace();

			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.exception", e.getMessage()));
			saveErrors(request, errors);

			return mapping.findForward(Constants.FAIL);
		}
		return mapping.findForward(Constants.SUCCESS);
	}
}
