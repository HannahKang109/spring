package kr.green.springtest.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springtest.service.BoardService;
import kr.green.springtest.vo.AccountVo;
import kr.green.springtest.vo.BoardVo;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;
	@RequestMapping(value = "/bbs/list")
	public String list(Model model) {
		List<BoardVo> list = boardService.getBoards();
		model.addAttribute("list",list);
		return "bbs/list";
	}
	@RequestMapping(value = "/bbs/register", method=RequestMethod.GET)
	public String registerGet(Model model) {
		List<BoardVo> list = boardService.getBoards();
		model.addAttribute("list",list);
		return "bbs/register";
	}
	@RequestMapping(value = "/bbs/register", method=RequestMethod.POST)
	public String registerPost(Model model, BoardVo board) {
		boardService.registerBoard(board);
		return "redirect:/bbs/list";
	}
	@RequestMapping(value = "/bbs/detail", method=RequestMethod.GET)
	public String detailGet(Model model, Integer id) {
		//1.전달받은 id값을 콘솔에 출력
		//System.out.println(id);
		//2.서비스에서 id값을 이용하여 해당 게시글을 가져오는 메소드 호출
		BoardVo board = boardService.readBoard(id);
		if(board == null)
			return "redirect:/bbs/list";
		//System.out.println(board);
		model.addAttribute("board",board);
		//3.해당 게시글을 jsp로 전달
			return "bbs/detail";
	}
	@RequestMapping(value = "/bbs/delete", method=RequestMethod.GET)
	public String deleteGet(Model model, Integer id, HttpServletRequest request) {
		//request에서 session 정보를 가져와 session에 저장된 user 정보를 가져옴
		HttpSession session = request.getSession();
		AccountVo user = (AccountVo)session.getAttribute("user");
		//삭제 권한이 없는 user가 URI를 통해 삭제를 시도할 수 있기 때문에 권한이 없는 user가 삭제를 시도하면 
		//막아주기 위해 게시판 id와 현재 로그인한 유저 정보를 전달
		boardService.deleteBoard(id,user);
		return "redirect:/bbs/list";
	}
	@RequestMapping(value = "/bbs/modify", method=RequestMethod.GET)
	public String modifyGet(Model model, Integer id) {
		BoardVo board = boardService.readBoard(id);
		if(board == null)
			return "redirect:/bbs/list";
		model.addAttribute("board", board);
		return "bbs/modify";
	}
	@RequestMapping(value = "/bbs/modify", method=RequestMethod.POST)
	public String modifyPost(Model model, BoardVo board, HttpServletRequest request) {

		HttpSession session = request.getSession();
		AccountVo user = (AccountVo)session.getAttribute("user");
		
		if(!boardService.modifyBoard(board,user))
			return "redirect:/bbs/list";
		
		model.addAttribute("id", board.getId());
		return "redirect:/bbs/detail";
	}
}
