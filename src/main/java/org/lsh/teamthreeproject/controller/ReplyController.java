package org.lsh.teamthreeproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.lsh.teamthreeproject.dto.BoardDTO;
import org.lsh.teamthreeproject.dto.ReplyDTO;
import org.lsh.teamthreeproject.dto.UserDTO;
import org.lsh.teamthreeproject.service.BoardService;
import org.lsh.teamthreeproject.service.ReplyService;
import org.lsh.teamthreeproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ReplyController {
    private final ReplyService replyService;
    private final BoardService boardService;
    private final UserService userService;

    //    @GetMapping("/test")
//    public String test() {
//        log.info("test");
//        return "/my/test";
//    }
    // 특정 사용자의 댓글
    @GetMapping("/myReplyList/{userId}")
    public String readUserReplies(@PathVariable Long userId, Model model) {
        List<ReplyDTO> replies = replyService.readRepliesByUserId(userId);
        model.addAttribute("replies", replies);
        return "/my/myReplyList";
    }

    // 특정 게시물의 댓글 조회
    @GetMapping("/board/{boardId}")
    @ResponseBody // json 형식으로 반환
    public List<ReplyDTO> getRepliesByBoard(@PathVariable Long boardId, Model model) {
//        List<ReplyDTO> replies = replyService.readRepliesByBoardId(boardId);
//        Long userId = 1L; // 테스트시 replyController와 맞추기
//        model.addAttribute("UID", userId);
//        model.addAttribute("replies", replies);
//        return "/my/myBoard";
        return replyService.readRepliesByBoardId(boardId);
    }

    // 댓글 생성

    @PostMapping("/create")
    public ResponseEntity<ReplyDTO> createReply(@RequestParam Long boardId,
                                                @RequestParam Long userId,
                                                @RequestParam String content,
                                                Model model) {
//        Long uid = 1L; // 테스트시 replyController와 맞추기
//        model.addAttribute("UID", uid);
        BoardDTO board = boardService.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found"));
        UserDTO user = userService.readUser(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        ReplyDTO createdReply = replyService.createReply(board, user, content);
        return ResponseEntity.ok(createdReply);
    }
}
