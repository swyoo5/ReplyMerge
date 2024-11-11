package org.lsh.teamthreeproject.service;

import org.lsh.teamthreeproject.dto.BoardDTO;
import org.lsh.teamthreeproject.dto.ReplyDTO;
import org.lsh.teamthreeproject.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Optional<ReplyDTO> readReply(Long id);
    public List<ReplyDTO> readAllReplies();
    public List<ReplyDTO> readRepliesByUserId(Long userId);

    ReplyDTO updateReply(ReplyDTO replyDTO, String content);
    List<ReplyDTO> readRepliesByBoardId(Long boardId);
    ReplyDTO createReply(BoardDTO board, UserDTO user, String content);
    void saveReply(ReplyDTO replyDTO);

    List<ReplyDTO> getRepliesByBoardId(Long boardId, int page, int size);
}
