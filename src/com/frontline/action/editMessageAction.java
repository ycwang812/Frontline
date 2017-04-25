package com.frontline.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.LookupDispatchAction;

import com.frontline.actionform.MessageActionForm;
import com.frontline.commom.Constants;
import com.frontline.dao.util.FabricateSQL;
import com.frontline.model.Guestbook;
import com.frontline.service.GuestbookService;
import com.frontline.service.GuestbookServiceImpl;

public class editMessageAction extends LookupDispatchAction {

	@Override
	protected Map<String, String> getKeyMethodMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title.update", "update");
		map.put("title.delete", "delete");
		return map;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MessageActionForm mf = (MessageActionForm) form;

		// 1.Retrieve From Data
		int id = mf.getId();
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
			// 呼叫Service
			GuestbookService gs = new GuestbookServiceImpl();
			Guestbook gb = gs.queryMessage(id);

			// 5.Select Next view
			// id查詢結果
			request.setAttribute("gb", gb);

			// 查詢條件
			request.setAttribute("queryName", queryName);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			request.setAttribute("queryMessage", queryMessage);
		} catch (Exception e) {
			e.printStackTrace();

			ActionMessages errors = new ActionMessages();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"errors.exception", e.getMessage()));
			saveErrors(request, errors);

			return mapping.findForward(Constants.FAIL);
		}
		return mapping.findForward(Constants.UPDATE);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MessageActionForm mf = (MessageActionForm) form;

		// 1.Retrieve From Data
		int id = mf.getId();
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

			Guestbook gb = new Guestbook();
			gb.setId(id);

			// 呼叫Service
			GuestbookService gs = new GuestbookServiceImpl();
			gs.deleteMessage(gb);
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
				return mapping.findForward(Constants.DELETE);
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
		return mapping.findForward(Constants.DELETE);
	}
}
