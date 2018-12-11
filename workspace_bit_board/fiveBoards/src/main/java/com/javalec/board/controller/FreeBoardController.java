package com.javalec.board.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.javalec.board.dto.BoardDto;
import com.javalec.board.service.BoardService;

@Controller
@RequestMapping("/freeboard")
public class FreeBoardController {

	private static final Logger logger = LoggerFactory.getLogger(FreeBoardController.class);
	
//	@Autowired
	@Resource(name="freeBoardService")
	private BoardService freeBoardService;
	
	// 사용자가 board/freeBoard 로 url 요청
	@RequestMapping("")
	public String list(Model model) {
		logger.info("freeboard_list()");
		model.addAttribute("list", freeBoardService.list());
		return "freeboard/list";
	}
	
	@RequestMapping(value="", params="select")
	public String list(HttpServletRequest request, Model model) {
		logger.info("freeboard_list() with search");
		System.out.println(request.getParameter("select"));
		System.out.println(request.getParameter("search"));
		model.addAttribute("list", freeBoardService.searchtwo(request.getParameter("select"), request.getParameter("search")));
		return "freeboard/list";
	}	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request) {
		logger.info("freeboard_delete()");
		freeBoardService.delete(Integer.parseInt(request.getParameter("bId")));
		return "redirect:./";
	}
	
	@RequestMapping("/writeview")
	public String writeView() {
		logger.info("freeboard_write_view()");
		return "freeboard/writeview";
	}
	
	@RequestMapping("/write")
	public String write(BoardDto dto, MultipartFile uploadfile) {
		logger.info("freeboard_write()");
		
		logger.info("dto: {}", dto);
		logger.info("MultipartFile: {}", uploadfile);

		logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
		logger.info("파일 크기: {}", uploadfile.getSize());
		
		saveFile(uploadfile); // 경로에 파일을 저장
		
		dto.setbFile(uploadfile.getOriginalFilename());
		
		logger.info("dto: {}", dto);
		
		System.out.println(dto.getbFile());
		
		freeBoardService.write(dto);
		return "redirect:./";
	}
	
	private String saveFile(MultipartFile file) {
		// 파일 이름 변경
		/*UUID uuid = UUID.randomUUID();*/
		/*String saveName = uuid + "_" + file.getOriginalFilename();*/
		
		String saveName = file.getOriginalFilename();
		logger.info("saveName: {}", saveName);
		// 저장할 파일 객체 생성
		File saveFile = new File("D:\\works\\sts_web\\fiveBoards\\src\\main\\webapp\\resources\\files",saveName); // 저장할 폴더, 파일이름
		try {
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return saveName;
	}

	// board/freeboard/1 처럼 요청 -> contentView 메서드 실행 -> contentview.jsp 와 연결
	@RequestMapping("/{bId}")
	public String contentView(@PathVariable int bId, Model model) {
		logger.info("freeboard_contentView()");
		freeBoardService.contentCount(bId);
		model.addAttribute("list", freeBoardService.contentView(bId));
		return "freeboard/contentview";
	}
	
	// board/freeBoard/update/1 -> updateView 메서드 실행 -> 1번글 수정 view로 이동
	@RequestMapping("update/{bId}")
	public String updateView(@PathVariable int bId, Model model) {
		logger.info("freeboard_updateView()");
		model.addAttribute("list", freeBoardService.updateView(bId));
		return "freeboard/updateview";
	}
	
	// board/freeBoard/updateAction 으로 요청시 -> update 메서드 실행 -> board/freeBoard 로 돌아감
	@RequestMapping("/updateAction")
	public String update(BoardDto dto, MultipartFile uploadfile) {
		logger.info("freeboard_update()");
		logger.info("dto: {}", dto);
		logger.info("MultipartFile: {}", uploadfile);
		logger.info("파일 이름: {}", uploadfile.getOriginalFilename());
		logger.info("파일 크기: {}", uploadfile.getSize());
		
		saveFile(uploadfile);
		dto.setbFile(uploadfile.getOriginalFilename());
		
		freeBoardService.update(dto);
		return "redirect:./";
	}
	
}
