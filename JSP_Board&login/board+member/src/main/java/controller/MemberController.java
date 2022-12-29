package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private MemberService msvc;
	private String destPage;
	private RequestDispatcher rdp;
	private int isOk;
       
    public MemberController() {
    	msvc = new MemberServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		log.info("uri >> "+uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path >> "+path);
		
		switch(path) {
		case "signupPage": destPage = "/member/signup.jsp"; break;
		case "signup":
			try {
				log.info("signup 1");
				String id = req.getParameter("id");
				String pwd = req.getParameter("pwd");
				String nick_name = req.getParameter("nick_name");
				if(nick_name.equals("")) nick_name = id.substring(0,id.indexOf("@"));
				MemberVO mvo = new MemberVO(id, pwd, nick_name);
				isOk = msvc.signup(mvo);
				log.info("signup 4 >> "+(isOk>0?"success":"fail"));
				
				destPage = "/";
			} catch (Exception e) {
				log.info("signup error");
				e.printStackTrace();
			}
			break;
		case "loginPage": destPage = "/member/login.jsp"; break;
		case "login":
			try {
				log.info("login 1");
				String id = req.getParameter("id");
				String pwd = req.getParameter("pwd");
				System.out.println("id"+id+"pwd"+pwd);
				System.out.println(new MemberVO(id, pwd).getId());
				MemberVO mvo = msvc.login(new MemberVO(id, pwd));
				log.info("login 4 >> "+(mvo!=null?"success":"fail"));
				
				if(mvo!=null) {
					HttpSession ses = req.getSession();
					ses.setAttribute("ses", mvo);
					ses.setMaxInactiveInterval(30*60);
					isOk = msvc.lastLog(mvo.getId());
					destPage = "/";
				}else {
					req.setAttribute("msg_login", 0);
					destPage = "/member/login.jsp";
				}
			} catch (Exception e) {
				log.info("login error");
				e.printStackTrace();
			}
			break;
		case "logout":
			try {
				HttpSession ses = req.getSession();
				ses.invalidate();
			} catch (Exception e) {
				log.info("logout error");
				e.printStackTrace();
			}
			break;
		case "modifyPage": destPage = "/member/modify.jsp"; break;
		case "modify":
			try {
				log.info("modify 1");
				String id = req.getParameter("id");
				String pwd = req.getParameter("pwd");
				String nick_name = req.getParameter("nick_name");
				MemberVO mvo = new MemberVO(id,pwd,nick_name);
				int isOk = msvc.modify(mvo);
				log.info("modify 4 >> "+(isOk>0?"success":"fail"));
				
				if(isOk>0) {
					HttpSession ses = req.getSession();
					ses.setAttribute("ses", mvo);
					ses.setMaxInactiveInterval(30*60);
				}
				
				destPage = "/";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
		case "withdrawPage": destPage = "/member/withdraw.jsp"; break;
		case "withdraw":
			try {
				log.info("withdraw 1");
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				String checkPwd = req.getParameter("pwd");
				int isOk = 0;
				if(mvo.getPwd().equals(checkPwd)) {
					isOk = msvc.withdraw(mvo.getId());
					destPage = "/";
				}else {
					req.setAttribute("msg_withdraw", 0);
					destPage = "/member/withdraw.jsp";
				}
				log.info("withdraw 4 >> "+(isOk>0?"success":"fail"));
				
				if(isOk>0) ses.invalidate();
			} catch (Exception e) {
				log.info("withdraw error");
				e.printStackTrace();
			}
			break;
		}
		
		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

}
