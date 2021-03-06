package com.javalec.bbs_prac.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javalec.bbs_prac.dao.IDao;

@Controller
	public class BController {
		
		private static final Logger logger = LoggerFactory.getLogger(BController.class);
		
		@Autowired
		private SqlSession sqlSession;
		
		//list구현부분
		@RequestMapping("/list")
		public String list(Model model) {
			logger.info("list()");
			
			IDao dao = sqlSession.getMapper(IDao.class);
			model.addAttribute("list", dao.list());
			return "list";
		}
		
		// list랑 비교하면서 보자
		// delete는 view에 정보를 넘겨줄 필요가 없고 dao를 통해 db를 조작만 하고 끝난다
		// bId가 Int 인 것에 유의하자
		// 조작이 끝나면 list 페이지로 넘어가게 한다

		//delete구현부분
		@RequestMapping("/delete")
		public String list(HttpServletRequest request) {
			logger.info("delete()");
			
			IDao dao = sqlSession.getMapper(IDao.class);
			dao.delete(Integer.parseInt(request.getParameter("bId")));
			
			return "redirect:list";
		}
		
		//글을 작성할 수 있는 Form 이 있는 공간
		@RequestMapping("write_view")
		public String write_view() {
			logger.info("write_view()");
			return "write_view";
		}
		
		//Write_view에서 작성한 글이 DB에 저장되는 공간
		@RequestMapping("/write")
		public String write(HttpServletRequest request) {
			logger.info("write()");
			
			IDao dao = sqlSession.getMapper(IDao.class);
			dao.write(request.getParameter("bUsername"), request.getParameter("bTitle"), request.getParameter("bContent"), request.getParameter("bImage"));
			
			return "redirect:list";
		}
		
		@RequestMapping("/content_view")
		public String content_view(HttpServletRequest request, Model model) {
			logger.info("content_view");
			
			IDao dao = sqlSession.getMapper(IDao.class);
			
			dao.content_count(Integer.parseInt(request.getParameter("bId"))); //조회수 기능
			
			model.addAttribute("list", dao.content_view(Integer.parseInt(request.getParameter("bId"))));
			
			return "content_view";
		}
		
		//update는 update를 실행하는 메소드이고, updateView는 update실행후 보여지는 뷰페이지 메소드이다.
		// bId얻어와서 해당되는 쿼리 실행후 뷰로 전달
		@RequestMapping("/update_view")
		public String update_view(HttpServletRequest request, Model model) {
			logger.info("update_view()");
			
			IDao dao = sqlSession.getMapper(IDao.class);
			
			model.addAttribute("list", dao.update_view(Integer.parseInt(request.getParameter("bId"))));
			
			return "update_view";
		}
		
		// form 으로 받아온 정보를 얻어서 db에 update 쿼리 실행
		// model에 담을 필요없고 list로 리다이렉트
		@RequestMapping("/update")
		public String update(HttpServletRequest request) {
			logger.info("update()");
			
			IDao dao = sqlSession.getMapper(IDao.class);

			dao.update(request.getParameter("bUsername"), request.getParameter("bTitle"), request.getParameter("bContent"), request.getParameter("bImage"), Integer.parseInt(request.getParameter("bId")));
			
			return "redirect:list";
		}
		
//		@RequestMapping("/uploadAjax")
//		public String uploadAjax(HttpServletRequest request) {
//			logger.info("uploadAjax()");
//			
//			IDao dao = sqlSession.getMapper(IDao.class);
//			
//			return uploadAjax(null);
//		}
	}