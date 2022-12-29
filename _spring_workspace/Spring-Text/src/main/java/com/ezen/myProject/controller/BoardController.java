package com.ezen.myProject.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myProject.domain.BoardDTO;
import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.FileVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.domain.UserVO;
import com.ezen.myProject.handler.FileHandler;
import com.ezen.myProject.handler.PagingHandler;
import com.ezen.myProject.repository.UserDAO;
import com.ezen.myProject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	@Inject
	private BoardService bsv;
	@Inject
	private UserDAO userDao;
	@Inject
	private FileHandler fhd;
	
	private int isOk;
	
	@GetMapping("/register")  // 등록 페이지로 이동
	public String boardRegisterGet() {
		return "/board/register";
	}
	
	@PostMapping("/register")  // 글 등록
	// RedirectAttributes 클래스 : redirect를 하는 경우에만 사용 가능
	public String register(BoardVO bvo, RedirectAttributes reAttr, @RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info("bvo register : "+bvo.toString());
		log.info("files register : "+files.toString());
		List<FileVO> fList = null;  // 파일 객체를 담을 리스트 생성
		if(files[0].getSize()>0) {  // 파일이 있는 경우만 실행
			fList = fhd.uploadFiles(files);
		}else {
			log.info("파일이 없습니다.");
		}
		BoardDTO bdto = new BoardDTO(bvo, fList);
		isOk = bsv.register(bdto);
		
//		isOk = bsv.register(bvo);
		// .addFlashAttribute : 받은 페이지에만 값이 있다.
		// .addAttribute : 페이지를 이동해도 값이 남아있다.
		reAttr.addFlashAttribute("msg_register",isOk>0?"1":"0");
		log.info("board register >> "+(isOk>0?"ok":"fail"));
		return "redirect:/board/list";  // redirect로 인해 BoardController의 list로 보낸다.
	}
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {
		List<BoardVO> list = bsv.getList(pgvo);
		int totalCount = bsv.getBoardCount(pgvo);
		PagingHandler pgh = new PagingHandler(pgvo, totalCount);
		model.addAttribute("list", list);
		model.addAttribute("pgh", pgh);
		
		return "/board/list";
	}
	
	@GetMapping({"/detail","/modify"})
	// @RequestParam(파라미터명) 타입 변수명 : 파라미터를 변수로 입력 
	public void detail(Model model, @RequestParam("bno") int bno) {
		BoardDTO bdto = bsv.getDetailFile(bno);
		log.info("bdto.bvo : "+bdto.getBvo().toString());
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("fList", bdto.getFList());
	}
	
	@PostMapping("/modify")  // 글 수정
	public String modify(RedirectAttributes reAttr, BoardVO bvo, @RequestParam(name="files", required = false) MultipartFile[] files) {
		log.info("modify >>> "+bvo.toString());

		List<FileVO> fList = null;  // 파일 객체를 담을 리스트 생성
		if(files[0].getSize()>0) {  // 파일이 있는 경우만 실행
			fList = fhd.uploadFiles(files);
		}else {
			log.info("파일이 없습니다.");
		}
		BoardDTO bdto = new BoardDTO(bvo, fList);
		
		UserVO user = userDao.getUser(bvo.getWriter());
		int isOk = bsv.modify(bdto, user);
		log.info("board modify >> "+(isOk>0?"ok":"fail"));
		reAttr.addFlashAttribute("msg_modify",isOk>0?"1":"0");
		
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	@GetMapping("/remove")  // 글 삭제
	public String remove(RedirectAttributes reAttr, @RequestParam("bno") int bno) {
		log.info("remove >>> ");
		int isOk = bsv.remove(bno);
		log.info("board remove >> "+(isOk>0?"ok":"fail"));
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value = "/fileRemove", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})  // 파일 삭제
	public ResponseEntity<String> fileRemove(@RequestBody String uuid){
		isOk = bsv.deleteFile(uuid);
		return isOk>0? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}