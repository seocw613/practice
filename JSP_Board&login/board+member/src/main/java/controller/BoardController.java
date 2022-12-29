package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	private BoardService bsvc;
	private String destPage;
	private RequestDispatcher rdp;
	private int isOk;
	private String savePath;  // 파일 경로를 저장할 변수
	private final String UTF8 = "utf-8";
       
    public BoardController() {
    	bsvc = new BoardServiceImpl();
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
		case "regiPage": 
			try {
				log.info("regiPage 1");
				HttpSession ses = req.getSession();
				System.out.println("pageCount : "+req.getParameter("pageCount"));
				if((MemberVO) ses.getAttribute("ses")!=null) {
					MemberVO mvo = (MemberVO) ses.getAttribute("ses");
					req.setAttribute("mvo", mvo);
					destPage = "/board/regi.jsp";
				} else {
					destPage = "/mem/loginPage";
					req.setAttribute("msg_write", 0);
				}
				log.info("regiPage 2");
			} catch (Exception e) {
				log.info("regiPage error");
				e.printStackTrace();
			}
			break;
		case "regi":
			try {
				// 파일을 업로드할 물리적인 경로 설정
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);  // 파일 실제 저장 경로
				log.info("저장위치"+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);  // 저장할 위치를 file 객체로 지정
				fileItemFactory.setSizeThreshold(2*1024*1024);  // 저장을 위한 임시 메모리 저장 용량 설정 : byte 단위
				
				BoardVO bvo = new BoardVO();
				
				// multipart/form 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);  //req에서 가져온 데이터를 
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString(UTF8));  // 인코딩 형식을 담아서 변환
						break;
					case "writer":
						bvo.setWriter(item.getString(UTF8));
						break;
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file":
						// 이미지가 있는지 없는지 체크
						if(item.getSize() > 0) {  // .getSize() : 데이터의 크기를 바이트 단위로 반환
							String fileName = item.getName()  // getName() : 경로가 포함된 파일 이름 반환 - D:/폴더/폴더/어쩌구/dog.jsp
									.substring(item.getName().lastIndexOf("/")+1);  // 파일 이름만 분리 - dog.jsp
							// 파일을 입력할 때 같은 이름의 파일이 생기지 않도록 파일이름에 업로드 시간이 들어가도록 설정 - 현재시간_dog.jsp
							fileName = System.currentTimeMillis()+"_"+fileName;  // System.currentTimeMillis() : 현재 시간을 밀리초 단위로 나타내는 함수
							File UploadFilePath = new File(fileDir+File.separator+fileName);  // File.separator : 파일을 구분하는 구분인자 - ppt의 39페이지 참조
							log.info("파일경로+이름 : "+UploadFilePath);
							
							// 저장
							try {
								item.write(UploadFilePath);  // 자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);  // 파일네임만 적어도 시간이 길이서 길게 나온다
								
								// 썸네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(UploadFilePath)  // of에 있는 파일을 사이즈만큼의 크기로 썸네일로 만든다
								.size(75, 75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));  // th는 썸네일이란 거 표시하는 용도
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
						}
						break;
					}
					
				}
				
				isOk = bsvc.regi(bvo);
				log.info(">>> insert > "+(isOk>0?"success":"fail"));
				
//				log.info("regi 1");
//				String title = req.getParameter("title");
//				String writer = req.getParameter("writer");
//				String content = req.getParameter("content");
//				BoardVO bvo = new BoardVO(title, writer, content);
//				if(title.equals("")) {
//					req.setAttribute("msg_write", 0);
//					destPage = "regiPage";
//				}else {
//					isOk = bsvc.regi(bvo);
					destPage = "pageList";
//				}
			} catch (Exception e) {
				log.info("regi error");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				log.info("list 1");
//				int pageSize = 20;
				List<BoardVO> list = bsvc.getList();
				log.info("list 4 >> "+(list.size()>0?"success":"fail"));
				
//				int pageCount = Integer.parseInt(req.getParameter("pageCount"));
//				int totalPage = (list.size()/pageSize)+((list.size()%20>0)?1:0);
//				List<BoardVO> printList = new ArrayList<BoardVO>();
//				for(int i=pageSize*(pageCount-1); i<pageSize*pageCount; i++) {
//					printList.add(list.get(i));
//				}
//				req.setAttribute("list", printList);
//				req.setAttribute("totalPage", totalPage);
//				req.setAttribute("pageCount", pageCount);

				req.setAttribute("list", list);
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("list error");
				e.printStackTrace();
			}
			break;
		case "page":
			try {
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				PagingVO pgvo = new PagingVO(pageNo,qty);
				int totCount = bsvc.getPageCnt();  // 전체 카운트 호출
				List<BoardVO> list = bsvc.getListPage(pgvo);  // limit을 이용해 해당 페이지의 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				System.out.println(ph.getPageQty());
				req.setAttribute("list", list);  // 한 페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);  // 페이지 정보 보내기
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("page error");
				e.printStackTrace();
			}
			break;
		case "pageList":
			try {
				PagingVO pgvo = new PagingVO();
				int totCount = bsvc.getPageCnt();  // 전체 카운트 호출
				List<BoardVO> list = bsvc.getListPage(pgvo);  // limit을 이용해 해당 페이지의 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);  // 한 페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);  // 페이지 정보 보내기
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("pageList error");
				e.printStackTrace();
			}
			break;
		case "myList":
			try {
				log.info("myList 1");
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				List<BoardVO> list = bsvc.getMyList(id);
				req.setAttribute("list", list);
				log.info("myList 4 >> "+(list.size()>0?"success":"fail"));
				
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("myList error");
				e.printStackTrace();
			}
			break;
		case "detail":
			try {
				log.info("detail 1");
				int bno = Integer.parseInt(req.getParameter("bno"));
				BoardVO bvo = bsvc.getDetail(bno);
				bvo.setRead_count(bvo.getRead_count()+1);
				if(bvo!=null) bsvc.addView(bvo);
				req.setAttribute("bvo", bvo);
				log.info("detail 4");
				
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				log.info("detail error");
				e.printStackTrace();
			}
			break;
		case "modifyPage":
			try {
				log.info("modifyPage 1");
				int bno = Integer.parseInt(req.getParameter("bno"));
				String writer = req.getParameter("writer");
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO) ses.getAttribute("ses");
				if(mvo==null) {
					req.setAttribute("msg_modify_login_null", 0);
					destPage = "/mem/loginPage";
				}else {
					BoardVO bvo = bsvc.checkModify(new BoardVO(bno, writer));
					if(bvo.getWriter().equals(mvo.getNick_name())) {
						req.setAttribute("mvo", mvo);
						req.setAttribute("bvo", bvo);
						destPage = "/board/modify.jsp";
					}else {
						req.setAttribute("msg_modify_diff_writer", 0);
						destPage = "detail";
					}
				}
				log.info("modifyPage 4");
			} catch (Exception e) {
				log.info("modifyPage error");
				e.printStackTrace();
			}
			break;
		case "modify":
			try {
				log.info("modify 1");
				int bno = Integer.parseInt(req.getParameter("bno"));
				String title = req.getParameter("title");
				String writer = req.getParameter("writer");
				String content = req.getParameter("content");
				BoardVO bvo = new BoardVO(bno, title, writer, content);
				System.out.println(bvo);
				isOk = bsvc.modify(bvo);
				System.out.println(isOk);
				log.info("modify 4 >> "+(isOk>0?"success":"fail"));
				
				if(isOk>0) req.setAttribute("bno", bno);
				
				destPage = "detail";
			} catch (Exception e) {
				log.info("modify error");
				e.printStackTrace();
			}
			break;
		case "remove":
			try {
				log.info("remove 1");
				int bno = Integer.parseInt(req.getParameter("bno"));
				isOk = bsvc.remove(bno);
				log.info("remove 4 >> "+(isOk>0?"success":"fail"));
				
				destPage = "pageList";
			} catch (Exception e) {
				log.info("remove error");
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
