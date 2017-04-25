package com.frontline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.springframework.web.struts.ActionSupport;

import com.frontline.actionform.MessageActionForm;
import com.frontline.commom.Constants;
import com.frontline.commom.Replacement;
import com.frontline.model.Guestbook;
import com.frontline.service.GuestbookService;
import com.frontline.service.GuestbookServiceImpl;

public class createMessageAction extends ActionSupport {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MessageActionForm mf = (MessageActionForm) form;

		// 1.Retrieve From Data
		String name = mf.getName();
		String date = mf.getDate();
		String msg = mf.getMessage();

		// 2.Validate Data
		// 移至createMessageForm內

		// 3.Convert Data
		// 將訊息內容置換
		String message = new Replacement().replaceMessage(msg);

		try {
			// 4.Invoke Biz Logic
			Guestbook gb = new Guestbook();
			gb.setName(name);
			gb.setDate(date);
			gb.setMessage(message);

			// 呼叫Service
			GuestbookService gs = new GuestbookServiceImpl();
			gs.createMessage(gb);

			// 5.Select Next view
			ActionMessages successMsg = new ActionMessages();
			successMsg.add("successful", new ActionMessage("post.successful"));
			saveMessages(request, successMsg);
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
