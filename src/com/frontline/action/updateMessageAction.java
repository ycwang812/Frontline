package com.frontline.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.frontline.actionform.MessageActionForm;
import com.frontline.commom.Constants;
import com.frontline.commom.Replacement;
import com.frontline.dao.util.FabricateSQL;
import com.frontline.model.Guestbook;
import com.frontline.service.GuestbookService;
import com.frontline.service.GuestbookServiceImpl;

public class updateMessageAction extends DispatchAction {

	public ActionForward updateMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MessageActionForm mf = (MessageActionForm) form;

		// 1.Retrieve From Data
		int id = mf.getId();
		String msg = mf.getMessage();
		String queryName = mf.getQueryName();
		String startDate = mf.getStartDate();
		String endDate = mf.getEndDate();
		String queryMessage = mf.getQueryMessage();

		// 2.Validate Data
		// 移至createMessageForm內

		// 3.Convert Data
		// 將訊息內容置換
		String message = new Replacement().replaceMessage(msg);

		try {
			// 4.Invoke Biz Logic
			// 處理SQL指令
			String condition = new FabricateSQL().modifyCondition(queryName,
					startDate, endDate, queryMessage);
			ArrayList<String> psValues = new FabricateSQL().modifyPsValues(
					queryName, startDate, endDate, queryMessage);

			Guestbook gb = new Guestbook();
			gb.setId(id);
			gb.setMessage(message);

			// 呼叫Service
			GuestbookService gs = new GuestbookServiceImpl();
			gs.updateMessage(gb);
			ArrayList<Guestbook> result = gs.queryMessage(condition, psValues);

			// 5.Select Next view
			// 查詢條件
			request.setAttribute("queryName", queryName);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("queryMessage", queryMessage);

			// 無任何資料處理
			if (result.isEmpty()) {
				ActionMessages am = new ActionMessages();
				am.add("resultMessage", new ActionMessage("result.nodata"));
				saveMessages(request, am);
				return mapping.findForward(Constants.SUCCESS);
			}

			// 查詢結果
			request.setAttribute("result", result);
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

	public ActionForward goBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MessageActionForm mf = (MessageActionForm) form;

		// 1.Retrieve From Data
		String queryName = mf.getQueryName();
		String startDate = mf.getStartDate();
		String endDate = mf.getEndDate();
		String queryMessage = mf.getQueryMessage();

		// 2.Validate Data
		// N/A

		// 3.Convert Data
		// N/A

		try {
			// 4.Invoke Biz Logic
			// 處理SQL指令
			String condition = new FabricateSQL().modifyCondition(queryName,
					startDate, endDate, queryMessage);
			ArrayList<String> psValues = new FabricateSQL().modifyPsValues(
					queryName, startDate, endDate, queryMessage);

			// 呼叫Service
			GuestbookService gs = new GuestbookServiceImpl();
			ArrayList<Guestbook> result = gs.queryMessage(condition, psValues);

			// 5.Select Next view
			// 查詢條件
			request.setAttribute("queryName", queryName);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("queryMessage", queryMessage);

			// 查詢結果
			request.setAttribute("result", result);
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
