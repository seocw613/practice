package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentSerivce;
import service.CommentServiceImpl;

@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CommentController.class);
	private CommentSerivce csvc;
	private int isOk;
       
    public CommentController() {
    	csvc = new CommentServiceImpl();
    }
    
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	res.setCharacterEncoding("UTF-8");
    	
    	String uri = req.getRequestURI();  // /cmt/list/10  -> /cmt/post나 /cmt/modify 등인 경우는 제대로 나오지 않는다.
    	String pathUri = uri.substring("/cmt/".length());  // list/10
    	String path = pathUri;  // path에 미리 값을 주고 if문에서 list인 경우에 다시 값을 저장
    	String pathVar = "";
    	if(pathUri.contains("/")) {
    		path = pathUri.substring(0,pathUri.lastIndexOf("/"));  // list
    		pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1);  // 10
    	}
    	
    	log.info("uri >> "+uri);
    	log.info("pathUri >> "+pathUri);
    	log.info("path >> "+path);
    	log.info("pathVar >> "+pathVar);
    	
    	switch(path) {
    	case "post":
    		// comment 내용이 JSON 형태이기 때문에 req.getParameter()로 가져올 수가 없다.
    		// -> StringBuffer를 사용해 가져온다.
    		try {
    			log.info("Comment post check 1");
				StringBuffer sb = new StringBuffer();  // 생성
				String line = null;
				BufferedReader br = req.getReader();  // 댓글 객체
				// br에서 한 줄을 가져와서 line에 저장
				while((line = br.readLine())!=null) {  // (line = br.readLine())은 line으로 인식되어 line에 한 줄을 입력하고 null인지 비교하는 구문이 된다.
					sb.append(line);  // 문자열 추가 - sb에 line 추가
				}
				log.info("sb >>> "+sb.toString());  // sb에 잘 저장됐는지 확인
				
				// 객체로 옮기기
				// JSONParser : JSON 형태의 객체로 변환할 때 사용되는 객체
				JSONParser parser = new JSONParser();  // import는 org.json.simple.parser.JSONParser가 맞는지 확인!
				// JSONObject는 key : value 형태로 저장된다.
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());  // sb를 JSONObject로 변환
				
				// JSONObject 내의 value는 object 형태이므로 .toString()으로 가져올 것
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				isOk = csvc.post(new CommentVO(bno,writer,content));
				log.info("Comment post check 4 >> "+(isOk>0?"success":"fail"));

				// board_detail.js의 postCommentToServer의 result에 값을 반환
				PrintWriter out = res.getWriter();  // PrintWriter를 사용해 response에 쓰기
				out.print(isOk);
			} catch (Exception e) {
				// io 객체를 사용하는 경우, JSONParser를 사용하는 경우는 exception이 필요하므로 try~catch문 사용
				log.info("Comment post error");
				e.printStackTrace();
			}
    		break;
    	case "list":
    		try {
				log.info("Comment list check 1");
				List<CommentVO> list = csvc.getList(Integer.parseInt(pathVar));
				log.info("Comment list DB ok");
				// JSON 형태로 변환
				// 해당하는 comment 수만큼 JSON 객체 생성하고 list에 저장
				JSONObject jsonObj = new JSONObject();
				JSONArray jsonObjList = new JSONArray();
				
				for(int i=0; i<list.size(); i++) {
					jsonObj = new JSONObject();
					// 아래는 각각이 객체가 되어 추가된다.
					jsonObj.put("cno", list.get(i).getCno());
					jsonObj.put("bno", list.get(i).getBno());
					jsonObj.put("writer", list.get(i).getWriter());
					jsonObj.put("content", list.get(i).getContent());
					jsonObj.put("reg_at", list.get(i).getReg_at());
					
					jsonObjList.add(jsonObj);
				}
				
				// 보낼 때는 하나의 String으로 보내야하기 때문에 위에서 작성한 list 전체 내용을 String으로 변환
				String jsonData = jsonObjList.toJSONString();
				
				PrintWriter out = res.getWriter();
				out.print(jsonData);
				log.info("Comment list check 4 >> "+(jsonData.length()>0?"success":"fail"));
			} catch (Exception e) {
				log.info("Comment list error");
				e.printStackTrace();
			}
    		break;
    	case "modify":
    		try {
    			log.info("Comment modify check 1");
				StringBuffer sb = new StringBuffer();  // 생성
				String line = null;
				BufferedReader br = req.getReader();  // 댓글 객체
				// br에서 한 줄을 가져와서 line에 저장
				while((line = br.readLine())!=null) {  // (line = br.readLine())은 line으로 인식되어 line에 한 줄을 입력하고 null인지 비교하는 구문이 된다.
					sb.append(line);  // 문자열 추가 - sb에 line 추가
				}
				log.info("sb >>> "+sb.toString());  // sb에 잘 저장됐는지 확인
				
				// 객체로 옮기기
				// JSONParser : JSON 형태의 객체로 변환할 때 사용되는 객체
				JSONParser parser = new JSONParser();  // import는 org.json.simple.parser.JSONParser가 맞는지 확인!
				// JSONObject는 key : value 형태로 저장된다.
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());  // sb를 JSONObject로 변환
				
				// JSONObject 내의 value는 object 형태이므로 .toString()으로 가져올 것
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				String content = jsonObj.get("content").toString();
				isOk = csvc.modify(new CommentVO(cno,content));
				log.info("Comment modify check 4 >> "+(isOk>0?"success":"fail"));

				// board_detail.js의 updateCommentToServer의 result에 값을 반환
				PrintWriter out = res.getWriter();  // PrintWriter를 사용해 response에 쓰기
				out.print(isOk);
			} catch (Exception e) {
				// io 객체를 사용하는 경우, JSONParser를 사용하는 경우는 exception이 필요하므로 try~catch문 사용
				log.info("Comment modify error");
				e.printStackTrace();
			}
    		break;
    	case "remove":
    		try {
    			log.info("Comment remove check 1");
				isOk = csvc.remove(Integer.parseInt(pathVar));  // pathVar는 cno 값
				log.info("Comment remove check 4 >> "+(isOk>0?"success":"fail"));
				
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info("Comment remove error");
				e.printStackTrace();
			}
    		break;
    	}
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

}
